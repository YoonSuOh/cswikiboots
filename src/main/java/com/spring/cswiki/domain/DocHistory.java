package com.spring.cswiki.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocHistory {
    private int d_num;
    private int d_version;
    private String u_id;
    private Date d_date;
    private Time d_time;
    private String d_summary;
    private String d_content;
    private String d_title;
}
