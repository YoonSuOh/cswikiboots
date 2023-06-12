package com.spring.cswiki.dao;

import com.spring.cswiki.domain.DocHistory;
import com.spring.cswiki.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO {
    void join(Member member); // 회원가입
    Member login(Member member); // 로그인
    List<Member> list(); // (관리자 전용) 회원 목록 조회
    int ban(Member member); // (관리자 전용) 회원 차단
    int removeban(Member member); // (관리자 전용) 회원 차단 해제
    List<Member> banlist(); // (관리자 전용) 차단된 회원 목록 조회
}
