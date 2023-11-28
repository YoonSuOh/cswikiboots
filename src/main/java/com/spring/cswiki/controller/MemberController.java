package com.spring.cswiki.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.cswiki.domain.Doc;
import com.spring.cswiki.domain.Star;
import com.spring.cswiki.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.cswiki.domain.Member;

@Controller
@RequestMapping("/member/*")

public class MemberController {
    private final Logger LOG = LoggerFactory.getLogger(MemberController.class.getName());
    @Inject
    private MemberService service;

    @GetMapping
    public String root() {
        return "redirect:/";
    }

    // 회원가입 페이지로 이동
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String getjoin() throws Exception {
        return "member/join";
    }

    // 회원 가입 처리 후 메인페이지로 이동
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String postjoin(Member member) throws Exception {
        service.join(member);
        return "redirect:/";
    }

    // 로그인 페이지로 이동
    @GetMapping(value = "/login")
    public String getlogin(HttpServletRequest req) throws Exception {
        String referer = req.getHeader("Referer");
        LOG.info(referer);
        req.getSession().setAttribute("referer", referer);
        return "member/login";
    }

    // 로그인 처리, 값이 일치하면 로그인 수행 후 메인 페이지로 이동, 일치하지 않으면 다시 로그인 페이지로 이동
    @RequestMapping(value="/login")
    public String login(HttpServletRequest req, Member member, Model model) throws Exception{
        HttpSession session = req.getSession();
        Member login = service.login(member);
        String referer = (String) session.getAttribute("referer");
        LOG.info(referer);

        if (login == null) {
            session.setAttribute("member", null);
            session.setAttribute("p_id", null);
            LOG.info("login failed......");
            return "redirect:/member/login";
        } else {
            LOG.info("login success!");
            session.setAttribute("member", login);
            session.setAttribute("p_id", login.getP_id());
            session.setAttribute("u_id", login.getU_id());
            session.setAttribute("email", login.getEmail());
            session.setAttribute("regdate", login.getReg_date());
            session.setAttribute("ban", login.getBan());
            LOG.info(String.valueOf(login.getP_id()));
            LOG.info((login.getU_id()));
            LOG.info((login.getEmail()));
            LOG.info(String.valueOf((login.getReg_date())));
            LOG.info(String.valueOf((login.getBan())));

            List<Star> stars = service.findStars(login.getU_id());
            List<String> docs = new ArrayList<>();
            if (stars == null) {
                LOG.info("즐겨찾기 등록된 문서 중 수정사항이 없습니다.");
                String comment = "수정된 즐겨찾기 문서가 없습니다.";
                session.setAttribute("docs", comment);
            } else {
                for (int i = 0; i < stars.size(); i++) {
                    docs.add("즐겨찾기 등록하신 '" + service.findDoc(stars.get(i).getD_num()).getD_title() + "'문서가 수정되었습니다.");
                }
            }
            session.setAttribute("docs", docs);

            if (referer != null && !referer.contains("login")) {
                LOG.info("prev page is checked, return to check page.");
                service.finishAlarm(login.getU_id());
                return "redirect:" + referer;
            } else {
                LOG.info("prev page is not found, return to main page.");
                service.finishAlarm(login.getU_id());
                return "redirect:/";
            }
        }
    }

    // 로그아웃
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest req, HttpSession session){
        String referer = req.getHeader("referer");
        session.invalidate();
        LOG.info("logout success!");
        return "redirect:" + referer;
    }

    // 사용자 정보 수정
    @RequestMapping(value="/infomodify")
    public String postinfomodify(Member member) throws Exception{
        service.infomodify(member);
        return "redirect:/";

    }

    // 회원목록 출력
    @RequestMapping(value="/memberlist", method=RequestMethod.GET) //url mapping
    public String getList(Model model) throws Exception{
        List<Member> list = service.list();
        model.addAttribute("list", list);
        return "member/memberlist";
    }

    // 사용자 차단 메뉴 이동
    @RequestMapping(value = "/ban", method = RequestMethod.GET)
    public String ban(Model model) throws Exception {
        List<Member> list = service.banlist();
        model.addAttribute("list", list);
        return "member/ban";
    }

    // 사용자 차단
    @PostMapping(value="/ban")
    public String banProcess(Member member) throws Exception {
        service.ban(member);
        return "redirect:/member/ban";
    }

    // 사용자 차단 해제
    @RequestMapping(value="/unban")
    public String unban(Member member) throws Exception {
        service.removeban(member);
        return "redirect:/member/ban";
    }

    // 권한 부여 / 회수 메뉴 이동
    @GetMapping(value="/grant")
    public String grant(Model model) throws Exception{
        List<Member> list = service.adminlist();
        model.addAttribute("list", list);
        return "member/grant";
    }

    // 권한 부여 / 회수
    @PostMapping(value="/grant")
    public String grantprocess(Member member) throws Exception {
        service.grant(member);
        return "redirect:/member/grant";
    }
}