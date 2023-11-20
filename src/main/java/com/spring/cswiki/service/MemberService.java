package com.spring.cswiki.service;

import com.spring.cswiki.dao.MemberDAO;
import com.spring.cswiki.domain.Doc;
import com.spring.cswiki.domain.Member;
import com.spring.cswiki.domain.Star;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService{
    private final MemberDAO dao;
     
    public void join(Member member) {
        dao.join(member);
    }

     
    public Member login(Member member) {
        return dao.login(member);
    }

     
    public Member getUserByname(String name) {
        return null;
    }

     
    public int infomodify(Member member) {
        return dao.infomodify(member);
    }

     
    public List<Member> list() {
        return dao.list();
    }

     
    public int ban(Member member) {
        return dao.ban(member);
    }

     
    public int removeban(Member member){
        return dao.removeban(member);
    }

     
    public List<Member> banlist() {
        return dao.banlist();
    }

     
    public List<Member> adminlist() {
        return dao.adminlist();
    }

     
    public int grant(Member member) {
        return dao.grant(member);
    }

    // 로그인한 유저의 즐겨찾기 문서 중 변경된 문서 조회
    public List<Star> findStars(String u_id) { return dao.findStars(u_id); }

    // 문서번호로 내용 추적
    public Doc findDoc(int d_num) { return dao.findDoc(d_num); };

    // 알람 전송 후 editdoc 컬럼 변경
    public void finishAlarm(String u_id) { dao.finishAlarm(u_id); };
}
