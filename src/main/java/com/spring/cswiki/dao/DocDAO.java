package com.spring.cswiki.dao;

import com.spring.cswiki.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DocDAO {
    public int create(Doc dto); // 새 문서 작성
    public void createDocHistory(DocHistory dto); // 문서 역사 생성
    public List<DocHistory> getDocHistory(int d_num); // 문서 역사 보기
    public Doc version(@Param("d_num") int d_num, @Param("d_version")String d_version); // 문서 버전별 내용 확인
    public Doc doc(int d_num); // 문서 본문 보기
    public void setDocTimeNum(@Param("d_num")int d_num, @Param("lastVisit")Timestamp lastVisit); // 문서번호 방문시간 업데이트
    public void setDocTimeTitle(@Param("d_title")String d_title, @Param("lastVisit")Timestamp lastVisit); // 문서제목 방문시간 업데이트
    public Timestamp getDocTimeTitle(String d_title); // 문서 제목으로 방문시간 가져오기
    public SmallCategory getcategory(int d_num);
    public int edit(Doc dto); // 문서 편집
    public void edithistory(DocHistory dto); // 문서 편집시 역사 생성
    public void delete(int d_num); // 문서 삭제
    public void acl(Doc dto); // acl 수정
    public void aclwipe(Doc dto); // acl 삭제
    public Doc search(String d_title); // 문서 검색
    public int starin(Star vo); // 즐겨찾기 등록
    public int starout(Star vo); // 즐겨찾기 삭제
    public List<Doc> userstar(String u_id); // 즐겨찾기 한 문서 목록 조회
<<<<<<< HEAD
    public List<Doc> popular(); // 즐겨찾기가 가장 많이 된 문서 목록 출력
    public List<Doc> sidebar(); // 사이드바 카테고리 출력

    /* 카테고리 트리뷰 테스트 코드 */
=======
    public List<Doc> popular(); // 즐겨찾기가 가장 많이 된 문서 목록 조회
    public List<DocHistory> selectRecent(); // 최근에 수정된 문서 목록 조회
    public List<Doc> sidebar(); // 사이드바 카테고리 조회
>>>>>>> 58563f3f5880b014dca50c3d2ddd339499e48c8f
    List<Category> selectAll(); // 카테고리 조회
    public void insertFirstCategory(@Param("id") String id, @Param("name") String name); // 1단계 카테고리 삽입
    public void insertSecondCategory(Category category); // 2단계 카테고리 삽입
    public void insertThirdCategory(@Param("id") String id, @Param("name") String name, @Param("parent_id") String parent_id, @Param("d_num") int d_num); // 3단계 카테고리 삽입
    public Category selectByCategoryId(int d_num); // 편집 시 해당하는 카테고리 가져오기
<<<<<<< HEAD
    public Category selectThirdCategoryByParentId(String parent_id); // 부모 ID와 일치하는 3단계 카테고리 가져오기

    // 특정 문서 댓글 추가 기능
    public void writeComment(@Param("u_id") String u_id, @Param("d_num") int d_num, @Param("cm_comment") String cm_comment, @Param("cm_time")LocalDateTime cm_time);
    // 문서에 달린 댓글 읽어오기 기능
    public List<Comment> readComment(@Param("d_num")int d_num);
=======
    public Category selectFirstCategory(); // 제일 최근에 추가된 1단계 카테고리 가져오기
    public Category selectSecondCategory(String parent_id); // 부모 id와 일치되는 2단계 카테고리 중 제일 마지막에 추가된 카테고리 가져오기
    public Category selectThirdCategoryByParentId(String parent_id); // 부모 ID와 일치하는 3단계 카테고리 가져오기
    public void updateCategory(@Param("id") String id, @Param("name") String name);
    public void deleteCategory(String id); // 카테고리 삭제
    public void writeComment(@Param("u_id") String u_id, @Param("d_num") int d_num, @Param("cm_comment") String cm_comment, @Param("cm_time")LocalDateTime cm_time); // 특정 문서 댓글 추가 기능
    public List<Comment> readComment(int d_num); // 문서에 달린 댓글 읽어오기 기능
>>>>>>> 58563f3f5880b014dca50c3d2ddd339499e48c8f
    // 댓글 번호로 댓글 찾기
    public Comment selectComment(@Param("cm_num")int cm_num);
    // 댓글 수정 기능
    public void updateComment(@Param("cm_num")int cm_num, @Param("u_id")String u_id, @Param("cm_comment")String cm_comment, @Param("cm_time")LocalDateTime cm_time);
    // 댓글 삭제
    public void deleteComment(@Param("cm_num")int cm_num, @Param("u_id")String u_id, @Param("d_num")int d_num);
<<<<<<< HEAD
}
=======

    // 즐겨찾기 등록한 유저 찾기
    public List<Star> starUsers(@Param("d_num")int d_num);
    // 문서 변경시 테이블에 반영
    public void editDoc(@Param("d_num")int d_num);
}
>>>>>>> 58563f3f5880b014dca50c3d2ddd339499e48c8f
