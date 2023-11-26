package com.spring.cswiki.dao;

import com.spring.cswiki.domain.Notice;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface noticeDAO {
    // 공지 보여주기

    List<Notice> selectNotice();
}
