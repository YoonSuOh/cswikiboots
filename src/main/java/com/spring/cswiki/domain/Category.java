package com.spring.cswiki.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Category {
    private int idx;
    private String id;
    private String name;
    private String parent_id;
    private int d_num;
    private int disable;
    private List<Category> children;
}

