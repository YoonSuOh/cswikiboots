package com.spring.cswiki.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;

import com.spring.cswiki.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring.cswiki.service.DocService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;


@Controller
@RequestMapping("/doc/*")

public class DocController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Inject
    private DocService service;
    // ?? ???
    @RequestMapping(value="/ckUpload", method=RequestMethod.POST)
    @ResponseBody
    public String fileUpload(HttpServletRequest req, HttpServletResponse resp, MultipartHttpServletRequest multiFile) throws Exception {
        JsonObject json = new JsonObject();
        PrintWriter printWriter = null;
        OutputStream out = null;
        MultipartFile file = multiFile.getFile("upload");
        if (file != null) {
            if (file.getSize() > 0 && StringUtils.isNotBlank(file.getName())) {
                if (file.getContentType().toLowerCase().startsWith("image/")) {
                    try {
                        String fileName = file.getName();
                        byte[] bytes = file.getBytes();
                        String uploadPath = req.getServletContext().getRealPath("/ckimage/");
                        File uploadFile = new File(uploadPath);
                        if (!uploadFile.exists()) {
                            uploadFile.mkdirs();
                        }
                        fileName = UUID.randomUUID().toString();
                        uploadPath = uploadPath + "/" + fileName;
                        out = new FileOutputStream(new File(uploadPath));
                        out.write(bytes);

                        printWriter = resp.getWriter();
                        resp.setContentType("text/html");
                        String fileUrl = req.getContextPath() + "/ckimage/" + fileName;

                        // json ???? ??
                        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
                        // ?? ??? ??? ????.
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", fileName);
                        json.addProperty("url", fileUrl);

                        printWriter.println(json);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                        if (printWriter != null) {
                            printWriter.close();
                        }
                    }
                }
            }
        }
        return null;
    }

    // 1?? ?? ???? ?? ? 1?? ?? ??
    @RequestMapping(value="/category", method=RequestMethod.GET) //url mapping
    public ModelAndView getBigCategoryList() {
        ModelAndView modelAndView = new ModelAndView("doc/category");
        List<BigCategory> category = service.list();
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    // 1?? ?? ?? ?? ???
    @RequestMapping(value="/createbigcategory", method=RequestMethod.GET)
    public ModelAndView getcreatebigcategory() throws Exception{
        ModelAndView modelAndView = new ModelAndView("doc/createbigcategory");
        return modelAndView;
    }

    // 1?? ?? ?? 
    @RequestMapping(value="/createbigcategory", method=RequestMethod.POST)
    public String postcreatebigcategory(BigCategory vo) throws Exception {
        service.createbigcategory(vo);
        return "redirect:list";
    }

    // 2?? ?? ???? ?? ? 2?? ?? ??
    @RequestMapping(value = "/scategory", method = RequestMethod.GET)
    public String gets_category(Model model, @RequestParam("b_ca_num") int b_ca_num) throws Exception {
        List<SmallCategory> scategory = service.s_category(b_ca_num);
        model.addAttribute("scategory", scategory);
        model.addAttribute("b_ca_num", b_ca_num);
        System.out.println("?? ?? : " + b_ca_num);
        return "doc/scategory";
    }

    // ??? ?? ??
    @RequestMapping(value="/list", method=RequestMethod.GET) //url mapping
    public String getdoc_list(Model model, @RequestParam("s_ca_num") int s_ca_num) throws Exception{
        List<Doc> list = service.doc_list(s_ca_num);
        model.addAttribute("list", list);
        model.addAttribute("s_ca_num", s_ca_num);
        return "doc/list";
    }

    // 2?? ?? ?? ?? ???
    @RequestMapping(value="/createsmallcategory", method=RequestMethod.GET)
    public String getcreatesmallcategory(Model model, @RequestParam("b_ca_num") int b_ca_num) throws Exception{
        model.addAttribute("b_ca_num", b_ca_num);
        return "doc/createsmallcategory";
    }

    // 2?? ?? ?? 
    @RequestMapping(value="/createsmallcategory", method=RequestMethod.POST)
    public String postsmallbigcategory(SmallCategory vo) throws Exception {
        service.createsmallcategory(vo);
        return "redirect:list";
    }

    // ?? ???? ??
    @RequestMapping(value = "/doc", method = RequestMethod.GET)
    public String getdoc(Model model, @RequestParam(required = false) Integer d_num, @RequestParam(required = false) String d_title) throws Exception {
        LocalDateTime lastVisit = LocalDateTime.now();
        if (d_num != null) {
            Doc doc = service.doc(d_num);
            service.setDocTimeNum(d_num, lastVisit);
            log.info(String.valueOf(doc.getB_ca_name()));
            log.info(String.valueOf(doc.getS_ca_name()));
            log.info(String.valueOf(doc.getLastVisit()));
            log.info(String.valueOf(doc.getP_read()));
            model.addAttribute("doc", doc);
        } else if (d_title != null) {
            Doc doc = service.search(d_title);
            service.setDocTimeTitle(d_title, lastVisit);
            log.info(String.valueOf(doc.getB_ca_name()));
            log.info(String.valueOf(doc.getLastVisit()));
            log.info(String.valueOf(doc.getP_read()));
            model.addAttribute("doc", doc);
            model.addAttribute("d_title", d_title);
        }
        return "doc/doc";
    }

    // ?? ?? ???? ??
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getcreate(Model model) throws Exception {
        List<SmallCategory> list = service.selectcategory();
        model.addAttribute("list", list);
        return "doc/create";
    }

    // ?? ??
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postcreate(Doc domain) throws Exception {
        log.info(String.valueOf("??? ?" + domain.getS_ca_num()));
        int result = service.create(domain);
        if(result > 0) {
            return "redirect:doc?d_num=" + domain.getD_num();
        } else {
            return "redirect:list";
        }
    }

    // ?? ????? ??
    @RequestMapping(value= "/edit", method = RequestMethod.GET)
    public String getedit(int d_num, Model model) throws Exception{
        Doc doc = service.doc(d_num);
        SmallCategory ctg = service.getcategory(d_num);
        List<SmallCategory> list = service.selectcategory();
        model.addAttribute("ctg", ctg);
        model.addAttribute("list", list);
        model.addAttribute("doc", doc);
        return "doc/edit";
    }

    // ?? ?? ?? ? ?? ??
    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public String postedit(Doc domain) throws Exception{
        int result = service.edit(domain);
        if(result > 0) {
            return "redirect:doc?d_num=" + domain.getD_num();
        } else {
            return "redirect:list";
        }
    }

    // ?? ?? ? ?? ??
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String postdelete(int d_num) throws Exception{
        service.delete(d_num);
        return "redirect:/";
    }

    // ?? ACL ?? ??? ??(??? ??)
    @RequestMapping(value="/acl", method=RequestMethod.GET)
    public String getacl(Model model, int d_num) throws Exception{
        Doc doc = service.doc(d_num);
        model.addAttribute("doc", doc);
        return "doc/acl";
    }

    // ?? ACL ?? ? ACL?? ??(??? ??)
    @RequestMapping(value="/acl", method=RequestMethod.POST)
    public String postacl(Doc domain, int d_num) throws Exception{
        service.acl(domain);
        return "redirect:acl?d_num=" + d_num;
    }

    // ?? ?? ??
    @RequestMapping("/doc_history")
    public String getDocumentHistory(@RequestParam("d_num") int d_num, Model model) {
        List<DocHistory> historyList = service.getDocHistory(d_num);
        Doc doc = service.doc(d_num);
        model.addAttribute("historyList", historyList);
        System.out.println(historyList);
        model.addAttribute("doc", doc);
        return "doc/doc_history";
    }

    // ?? ? ?? ?? ??
    @RequestMapping("/doc_version")
    public String version(@RequestParam("d_num") int d_num, @RequestParam("d_version") String d_version, Model model) {
        Doc version = service.version(d_num, d_version);
        model.addAttribute("doc", version);
        return "doc/doc_version";
    }

    // ???? ??
    @RequestMapping(value="/starcheck")
    public String starin(Model model, Star vo, int d_num, String u_id) {
        Doc doc = service.doc(d_num);
        LocalDateTime docDate = doc.getLastVisit();
        log.info(String.valueOf(docDate));
        model.addAttribute("doc", doc);
        service.starin(vo);
        return "redirect:doc?d_num=" + d_num;
    }

    // ???? ??
    @RequestMapping(value="/starout")
    public String starout(Model model, Star vo, @RequestParam("d_num")int d_num, @RequestParam("u_id")String u_id) {
        service.starout(vo);
        model.addAttribute("u_id", u_id);
        return "redirect:userstar?u_id=" + u_id;
    }

    // ???? ??
    @RequestMapping(value="/userstar")
    public String userstar(Model model, @RequestParam("u_id") String u_id) throws Exception{
        List<Doc> star = service.userstar(u_id);
        model.addAttribute("star", star);
        model.addAttribute("u_id", u_id);
        return "doc/userstar";
    }

    // ????? ?? ?? ? ???? ??
    @GetMapping(value="/popular")
    public String popular(Model model) throws Exception{
        List<Doc> list = service.popular();
        model.addAttribute("list", list);
        return "doc/popular";
    }

    // ???? ??
}
