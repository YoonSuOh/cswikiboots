package com.spring.cswiki.service;

import com.spring.cswiki.dao.MemberDAO;
import com.spring.cswiki.domain.Member;
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
}
