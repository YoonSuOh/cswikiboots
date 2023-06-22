package com.spring.cswiki.service;

import com.spring.cswiki.dao.MemberDAO;
import com.spring.cswiki.domain.DocHistory;
import com.spring.cswiki.domain.Member;
import com.spring.cswiki.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO dao;
    @Override
    public void join(Member member) {
        dao.join(member);
    }

    @Override
    public Member login(Member member) {
        return dao.login(member);
    }

    @Override
    public Member getUserByname(String name) {
        return null;
    }

    @Override
    public int infomodify(Member member) {
        return dao.infomodify(member);
    }

    @Override
    public List<Member> list() {
        return dao.list();
    }

    @Override
    public int ban(Member member) {
        return dao.ban(member);
    }

    @Override
    public int removeban(Member member){
        return dao.removeban(member);
    }

    @Override
    public List<Member> banlist() {
        return dao.banlist();
    }

    @Override
    public List<Member> adminlist() {
        return dao.adminlist();
    }

    @Override
    public int grant(Member member) {
        return dao.grant(member);
    }
}
