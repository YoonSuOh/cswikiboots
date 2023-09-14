package com.spring.cswiki.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int idx;
    private String id;
    private String name;
    private int parent_id;
    private List<Category> children;
}
