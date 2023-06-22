package com.spring.cswiki.dao;

import com.spring.cswiki.domain.DocHistory;
import com.spring.cswiki.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO {
    void join(Member member); // 회원가입
    Member login(Member member); // 로그인
    int infomodify(Member member); // 개인 정보 수정
    List<Member> list(); // (관리자 전용) 회원 목록 조회
    int ban(Member member); // (관리자 전용) 회원 차단
    int removeban(Member member); // (관리자 전용) 회원 차단 해제
    List<Member> banlist(); // (관리자 전용) 차단된 회원 목록 조회
    List<Member> adminlist(); // (개발자 전용) 관리자 및 개발자 목록 조회
    int grant(Member member); // (개발자 전용) 권한 부여 / 회수
}
