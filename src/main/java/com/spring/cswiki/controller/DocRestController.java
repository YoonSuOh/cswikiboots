package com.spring.cswiki.controller;

import com.spring.cswiki.service.DocService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/doc/*")
public class DocRestController {
    // Spring Bean DI
    @Autowired
    private final DocService service;

    /** 카테고리 수정 API
     * @param id
     * @param name
     * @return
     */
    @PutMapping("/categoryEdit")
    public Map<String, Object> editCategory(
            @RequestParam("id") String id,
            @RequestParam("name") String name){
        service.editCategory(id, name);

        Map<String, Object> result = new HashMap<>();

        result.put("code", 200);
        result.put("result", "성공");

        return result;
    }


    /** 카테고리 삭제 API
     * @param id
     * @return
     */
    @DeleteMapping("/categoryDelete")
    public Map<String, Object> deleteCategory(
            @RequestParam("id") String id){
        service.deleteCategory(id);

        Map<String, Object> result = new HashMap<>();

        result.put("code", 200);
        result.put("result", "성공");

        return result;
    }

}
