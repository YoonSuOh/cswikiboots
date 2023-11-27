package com.spring.cswiki.controller;

import com.spring.cswiki.domain.Notice;
import com.spring.cswiki.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {
    private final NoticeService noticeService;
    // 공지 만들기 창 불러오기
    @GetMapping("/create")
    public String getNotice(){
        return "notice/create";
    }

    // 공지 만들기
    @PostMapping("/create")
    public String addNotice(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("u_id") String u_id
    ){
        noticeService.addNotice(title, content, u_id);
        return "notice/list";
    }

    // 공지 목록 확인
    @GetMapping("")
    public String getNotoices(Model model){
        List<Notice> list = noticeService.getNoticeDesc();
        List<Notice> notice = noticeService.getNotice();
        model.addAttribute("notice", notice);
        model.addAttribute("list", list);
        return "notice/list";
    }

    // 공지 띄우기
    @GetMapping("/{noticeIdx}")
    public String getNotice(@PathVariable("noticeIdx") int id, Model model){
        Notice list = noticeService.getNoticeById(id);
        List<Notice> notice = noticeService.getNotice();
        model.addAttribute("notice", notice);
        model.addAttribute("list", list);
        return "notice/notice";
    }
}
