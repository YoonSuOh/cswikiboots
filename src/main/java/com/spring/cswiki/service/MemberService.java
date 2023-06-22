package com.spring.cswiki.service;

import com.spring.cswiki.domain.Member;

import java.util.List;

public interface MemberService {
    void join(Member member);
    Member login(Member member);
    Member getUserByname(String name);

    int infomodify(Member member);
    List<Member> list();
    int ban(Member member);
    int removeban(Member member);
    List<Member> banlist(); // (관리자 전용) 차단된 회원 목록 조회
//    PasswordEncoder passwordEncoder();

    List<Member> adminlist();
    int grant(Member member);
}
