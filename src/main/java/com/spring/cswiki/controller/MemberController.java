package com.spring.cswiki.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String login(HttpServletRequest req, Member member) throws Exception{
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
            LOG.info(String.valueOf(login.getP_id()));

            if (referer != null && !referer.contains("login")) {
                LOG.info("prev page is checked, return to check page.");
                return "redirect:" + referer;
            } else {
                LOG.info("prev page is not found, return to main page.");
                return "redirect:/";
            }
        }
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest req, HttpSession session){
        String referer = req.getHeader("referer");
        session.invalidate();
        LOG.info("logout success!");
        return "redirect:" + referer;
    }
}
