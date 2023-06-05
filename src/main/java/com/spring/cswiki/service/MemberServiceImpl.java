package com.spring.cswiki.service;

import com.spring.cswiki.dao.MemberDAO;
import com.spring.cswiki.domain.Member;
import com.spring.cswiki.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
