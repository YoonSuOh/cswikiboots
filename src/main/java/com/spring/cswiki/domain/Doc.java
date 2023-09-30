package com.spring.cswiki.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    private int d_num;
    private int s_ca_num;
    private int p_read;
    private int p_insert;
    private int p_update;
    private int p_delete;
    private String d_title;
    private String d_content;
    private String d_summary;
    private String u_id;
    private String b_ca_name;
    private String s_ca_name;
    private int d_available;
    private LocalDateTime lastVisit;
    private String parent_id;
    private String id;
}
