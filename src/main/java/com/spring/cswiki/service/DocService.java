package com.spring.cswiki.service;

import com.spring.cswiki.dao.DocDAO;
import com.spring.cswiki.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocService{
    private final DocDAO dao;

    public List<BigCategory> list() {
        return dao.list();
    }

     
    public List<SmallCategory> s_category(int b_ca_num) {
        return dao.s_category(b_ca_num);
    }

     
    public List<Doc> doc_list(int s_ca_num) {
        return dao.doc_list(s_ca_num);
    }

     
    public void createbigcategory(BigCategory vo) {
        dao.createbigcategory(vo);
    }

     
    public void createsmallcategory(SmallCategory vo) {
        dao.createsmallcategory(vo);
    }


    public int create(Doc dto) {
        int result = dao.create(dto);
        if (result > 0) {
            DocHistory dh = new DocHistory();
            dh.setD_num(dto.getD_num());
            dh.setD_version(1);
            dh.setU_id(dto.getU_id());
            dh.setD_summary("새 문서");
            dh.setD_content(dto.getD_content());

            // 새로운 문서가 추가될 때마다 parent_id를 증가시킴
            Category item = dao.selectThirdCategoryByParentId(dto.getId()); // 3단계 카테고리 가져오기


            String newId="";
            if(item != null){
                System.out.println("dto.getParent_id : " + item.getId());
                System.out.println("item 객체 : " + item);
                String numstr = item.getId().substring(item.getId().lastIndexOf("-")+ 1);
                int num= Integer.parseInt(numstr);
                System.out.println("추출된 문자 : " + num);
                num++;
                newId = dto.getId() + "-" + num;
            } else {
                newId = dto.getId() + "-1";
            }

            String id = newId;
            String name = dto.getD_title();
            String parent_id = dto.getId();
            int d_num = dto.getD_num();

            System.out.println("카테고리 ID : " + id);
            System.out.println("카테고리 이름 : " + name);
            System.out.println("카테고리 부모 ID : " + parent_id);
            System.out.println("문서 번호 : " + d_num);

            dao.insertThirdCategory(id, name, parent_id, d_num);
            dao.createDocHistory(dh);
        }
        return result;
    }

     
    public List<DocHistory> getDocHistory(int d_num) {
        return dao.getDocHistory(d_num);
    }

     
    public Doc version(int d_num, String d_version) {
        return dao.version(d_num, d_version);
    }

     
    public Doc doc(int d_num) {
        return dao.doc(d_num);
    }

     
    public void setDocTimeNum(int d_num, LocalDateTime lastVisit) {
        Doc doc = this.doc(d_num);
        int docNum = doc.getD_num();
        Timestamp timestamp = Timestamp.valueOf(lastVisit);
        dao.setDocTimeNum(d_num, timestamp);
    }

     
    public void setDocTimeTitle(String d_title, LocalDateTime lastVisit) {
        Doc doc = this.search(d_title);
        String docTitle = doc.getD_title();
        Timestamp timestamp = Timestamp.valueOf(lastVisit);
        dao.setDocTimeTitle(docTitle, timestamp);
    }

     
    public Timestamp getDocTimeTitle(String d_title) {
        Doc doc = this.search(d_title);
        String docTitle= doc.getD_title();
        return dao.getDocTimeTitle(docTitle);
    }

     
    public SmallCategory getcategory(int d_num) {
        return dao.getcategory(d_num);
    }


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

    public List<SmallCategory> selectcategory() {
        return dao.selectcategory();
    }

     
    public void delete(int d_num) {
        dao.delete(d_num);
    }

     
    public void acl(Doc dto) {
        dao.acl(dto);
    }

     
    public Doc search(String d_title) {
        return dao.search(d_title);
    }

     
    public int starin(Star vo) {
        return dao.starin(vo);
    }

     
    public int starout(Star vo) {
        return dao.starout(vo);
    }

     
    public List<Doc> userstar(String u_id) {
        return dao.userstar(u_id);
    }

     
    public List<Doc> popular() {
        return dao.popular();
    }

     
    public List<Doc> sidebar() {
        return dao.sidebar();
    }


    public List<Map<String, Object>> generateCategoryTreeJson() {
        List<Category> dblist = dao.selectAll();
        List<Map<String, Object>> jsonData = new ArrayList<>();

        for (Category parent : dblist) {
            if (parent.getParent_id() == null) {
                Map<String, Object> parentData = new HashMap<>();
                parentData.put("name", parent.getName());
                List<Map<String, Object>> childrenData = new ArrayList<>();

                for (Category child : dblist) {
                    if (child.getParent_id() != null && child.getParent_id().equals(parent.getId())) {
                        Map<String, Object> childData = new HashMap<>();
                        childData.put("name", child.getName());
                        List<Map<String, Object>> lastChildrenData = new ArrayList<>();

                        for (Category lastChild : dblist) {
                            if (lastChild.getParent_id() != null && lastChild.getParent_id().equals(child.getId())) {
                                Map<String, Object> lastChildData = new HashMap<>();
                                lastChildData.put("name", lastChild.getName());
                                lastChildData.put("d_num", lastChild.getD_num());
                                lastChildrenData.add(lastChildData);
                            }
                        }

                        if (!lastChildrenData.isEmpty()) {
                            childData.put("children", lastChildrenData);
                        }
                        childrenData.add(childData); // Modified this line
                    }
                }

                if (!childrenData.isEmpty()) {
                    parentData.put("children", childrenData);
                }

                jsonData.add(parentData);
            }
        }

        System.out.println("jsonData : " + jsonData);
        return jsonData;
    }

    public void addFirstCategory(String id, String name){
        dao.insertFirstCategory(id, name);
    } // 1단계 카테고리 삽입
    public void addSecondCategory(Category category){
        dao.insertSecondCategory(category);
    } // 2단계 카테고리 삽입

    public List<Category> selectSecondCategory() {
        List<Category> dblist = dao.selectAll(); // DB에서 조회
        List<Category> secondCategory = new ArrayList<>();

        for (Category item : dblist) {
            String parentId = item.getId();
            if (parentId != null && parentId.matches("[A-Z]-\\d")) {
                System.out.println("[문서 이름 : " + item.getName() + "], [부모 카테고리 ID : " + item.getParent_id() + "], [카테고리 ID : " + item.getId() + "]");
                secondCategory.add(item);
            } else {
                System.out.println("no data");
            }
        }

        return secondCategory;
    }

    public List<Category> selectThirdCategory(){
        List<Category> dblist = dao.selectAll();
        List<Category> thirdCategory = new ArrayList<>();

        for (Category item : dblist) {
            String parentId = item.getId();
            if (parentId != null && parentId.matches("[A-Z]-\\d-\\d")) {
                System.out.println("[문서 이름 : " + item.getName() + "], [부모 카테고리 ID : " + item.getParent_id() + "], [카테고리 ID : " + item.getId() + "]");
                thirdCategory.add(item);
            } else {
                System.out.println("no data");
            }
        }
        return thirdCategory;
    }

    public Category getByCategoryId(int d_num){
        return dao.selectByCategoryId(d_num);
    }

    // 문서에 댓글 추가하기
    public void writeComment(String u_id, int d_num, String cm_comment, LocalDateTime cm_time) {
        dao.writeComment(u_id, d_num, cm_comment, cm_time);
    }
    // 문서에 달린 댓글 읽어오기
    public List<Comment> readComment(int d_num) {
        return dao.readComment(d_num);
    }
}
