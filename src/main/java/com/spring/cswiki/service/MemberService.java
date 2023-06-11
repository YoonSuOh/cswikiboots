package com.spring.cswiki.service;

import com.spring.cswiki.domain.Member;

import java.util.List;

public interface MemberService {
    void join(Member member);
    Member login(Member member);
    Member getUserByname(String name);
    List<Member> list();
//    PasswordEncoder passwordEncoder();
}
