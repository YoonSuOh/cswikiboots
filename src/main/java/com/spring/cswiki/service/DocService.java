package com.spring.cswiki.service;

import com.spring.cswiki.domain.*;

import java.util.List;

public interface DocService {
    public List<BigCategory> list(); // xx`1단계 분류 조회

    public List<SmallCategory> s_category(int b_ca_num); // 2단계 분류 조회

    public List<Doc> doc_list(int s_ca_num); // 분류별 문서 조회

    public void createbigcategory(BigCategory vo);

    public void createsmallcategory(SmallCategory vo);

    public int create(Doc dto); // 새 문서 작성

    public List<DocHistory> getDocHistory(int d_num); // 문서 역사 보기

    public Doc version(int d_num, String d_version); // 문서 버전별 내용 확인

    public Doc doc(int d_num); // 문서 본문 보기

    public SmallCategory getcategory(int d_num);

    public int edit(Doc dto); // 문서 편집

    public List<SmallCategory> selectcategory();

    public void delete(int d_num); // 문서 삭제

    public void acl(Doc dto); // acl 수정

    public Doc search(String d_title); // 문서 검색

    public int starin(Star vo); // 즐겨찾기 등록

    public int starout(Star vo); // 즐겨찾기 삭제

    public List<Doc> userstar(String u_id); // 즐겨찾기 한 문서 목록 조회

    public List<Doc> popular(); // 즐겨찾기가 가장 많은 순대로 문서 목록 조회

    public List<Doc> sidebar(); // 사이드바 카테고리 출력
}
