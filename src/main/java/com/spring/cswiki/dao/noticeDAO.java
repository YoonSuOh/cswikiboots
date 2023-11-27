package com.spring.cswiki.dao;

import com.spring.cswiki.domain.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface noticeDAO {
    // 공지 목록 보여주기
    List<Notice> selectNotice();
    List<Notice> selectNoticeDesc();

    // 공지 보여주기
    Notice selectNoticeById(int noticeIdx);

    // 공지 생성
    void insertNotice(
            @Param("title") String title,
            @Param("content") String content,
            @Param("u_id") String u_id
    );
}
