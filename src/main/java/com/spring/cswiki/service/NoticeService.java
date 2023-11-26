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

    // 공지 보여주기
    public List<Notice> getNotice(){
        return dao.selectNotice();
    }
}
