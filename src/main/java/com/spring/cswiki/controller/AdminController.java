package com.spring.cswiki.controller;

import com.spring.cswiki.domain.Member;
import com.spring.cswiki.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberService service;

    // 회원목록 출력
    @RequestMapping(value="/memberlist", method= RequestMethod.GET) //url mapping
    public String getList(Model model) throws Exception{
        List<Member> list = service.list();
        model.addAttribute("list", list);
        return "admin/memberlist";
    }

    // 사용자 차단 메뉴 이동
    @GetMapping("/ban")
    public String ban(@RequestParam(value = "user", required = false) String id, Model model) throws Exception {
        if (id != null) {
            Member user = service.findByBan(id);
            model.addAttribute("list", user);
        } else {
            List<Member> list = service.banlist();
            model.addAttribute("list", list);
        }
        return "admin/ban";
    }

    // 사용자 차단
    @PostMapping(value="/ban")
    public String banProcess(Member member) throws Exception {
        service.ban(member);
        return "redirect:/admin/ban";
    }

    // 사용자 차단 해제
    @RequestMapping(value="/unban")
    public String unban(Member member) throws Exception {
        service.removeban(member);
        return "redirect:/admin/ban";
    }

    // 권한 부여 / 회수 메뉴 이동
    @GetMapping(value="/grant")
    public String grant(Model model) throws Exception{
        List<Member> list = service.adminlist();
        model.addAttribute("list", list);
        return "admin/grant";
    }

    // 권한 부여 / 회수
    @PostMapping(value="/grant")
    public String grantprocess(Member member) throws Exception {
        service.grant(member);
        return "redirect:/admin/grant";
    }
}
