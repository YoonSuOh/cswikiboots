package com.spring.cswiki.service;

import com.spring.cswiki.dao.DocDAO;
import com.spring.cswiki.dao.MemberDAO;
import com.spring.cswiki.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocServiceImpl implements DocService{
    private final DocDAO dao;
    @Override
    public List<BigCategory> list() {
        return dao.list();
    }

    @Override
    public List<SmallCategory> s_category(int b_ca_num) {
        return dao.s_category(b_ca_num);
    }

    @Override
    public List<Doc> doc_list(int s_ca_num) {
        return dao.doc_list(s_ca_num);
    }

    @Override
    public void createbigcategory(BigCategory vo) {
        dao.createbigcategory(vo);
    }

    @Override
    public void createsmallcategory(SmallCategory vo) {
        dao.createsmallcategory(vo);
    }

    @Override
    public int create(Doc dto) {
        int result = dao.create(dto);
        if(result > 0) {
            DocHistory dh = new DocHistory();
            dh.setD_num(dto.getD_num());
            dh.setD_version(1);
            dh.setU_id(dto.getU_id());
            dh.setD_summary("새 문서");
            dh.setD_content(dto.getD_content());
            dao.createDocHistory(dh);
        }
        return result;
    }

    @Override
    public List<DocHistory> getDocHistory(int d_num) {
        return dao.getDocHistory(d_num);
    }

    @Override
    public Doc version(int d_num, String d_version) {
        return dao.version(d_num, d_version);
    }

    @Override
    public Doc doc(int d_num) {
        return dao.doc(d_num);
    }

    @Override
    public SmallCategory getcategory(int d_num) {
        return dao.getcategory(d_num);
    }

    @Override
    public int edit(Doc dto) {
        int result = dao.edit(dto);
        if(result > 0) {
            DocHistory dh = new DocHistory();
            dh.setD_num(dto.getD_num()); // d_num 값을 설정해줘야 함
            dh.setU_id(dto.getU_id());
            dh.setD_summary(dto.getD_summary());
            dh.setD_content(dto.getD_content());
            dao.edithistory(dh);
        }
        return result;
    }

    @Override
    public List<SmallCategory> selectcategory() {
        return dao.selectcategory();
    }

    @Override
    public void delete(int d_num) {
        dao.delete(d_num);
    }

    @Override
    public void acl(Doc dto) {
        dao.acl(dto);
    }

    @Override
    public Doc search(String d_title) {
        return dao.search(d_title);
    }

    @Override
    public int starin(Star vo) {
        return dao.starin(vo);
    }

    @Override
    public int starout(Star vo) {
        return dao.starout(vo);
    }

    @Override
    public List<Doc> userstar(String u_id) {
        return dao.userstar(u_id);
    }

    @Override
    public List<Doc> popular() {
        return dao.popular();
    }

    @Override
    public List<Doc> sidebar() {
        return dao.sidebar();
    }


    /* 카테고리 테스트 코드 출력 */
}
