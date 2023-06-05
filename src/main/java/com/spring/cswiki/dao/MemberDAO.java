package com.spring.cswiki.dao;

import com.spring.cswiki.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
    void join(Member member);
    Member login(Member member);
}
