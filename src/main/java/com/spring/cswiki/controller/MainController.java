package com.spring.cswiki.controller;

import com.spring.cswiki.service.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Inject
    private DocService service;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/")
    public String main(Model model){
        List<Map<String, Object>> jsonData = service.generateCategoryTreeJson();
        model.addAttribute("jsonData", jsonData);
        return "/main";
    }

    // json 테스트
    /*@GetMapping("/test")
    @ResponseBody
    public List<Map<String, Object>> test(Model model){
        List<Map<String, Object>> jsonData = service.generateCategoryTreeJson();
        model.addAttribute("jsonData", jsonData);
        return jsonData;
    }*/

    @GetMapping("/test")
    public String test(Model model){
        List<Map<String, Object>> jsonData = service.generateCategoryTreeJson();
        model.addAttribute("jsonData", jsonData);
        return "test";
    }

    // 1단계 카테고리 삽입
    @PostMapping(value="/addcategory")
    public String addCategory(@RequestParam("name") String name) throws Exception{
        String id ="";
        service.addFirstCategory(id, name);
        return "redirect:/test";
    }
}

