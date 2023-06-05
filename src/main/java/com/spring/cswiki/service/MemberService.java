package com.spring.cswiki.service;

import com.spring.cswiki.domain.Member;

public interface MemberService {
    void join(Member member);
    Member login(Member member);
    Member getUserByname(String name);
//    PasswordEncoder passwordEncoder();
}
