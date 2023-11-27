package com.spring.cswiki.service;

import com.spring.cswiki.dao.noticeDAO;
import com.spring.cswiki.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    @Inject
    private noticeDAO dao;

    // 공지 목록 보여주기
    public List<Notice> getNotice(){
        return dao.selectNotice();
    }
    public List<Notice> getNoticeDesc(){
        return dao.selectNoticeDesc();
    }

    // 공지 보여주기
    public Notice getNoticeById(int noticeIdx){
        return dao.selectNoticeById(noticeIdx);
    }

    // 공지 생성
    public void addNotice(String title, String content, String u_id){
        dao.insertNotice(title, content, u_id);
    }
}
