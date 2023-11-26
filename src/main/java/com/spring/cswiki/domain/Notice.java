package com.spring.cswiki.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private int noticeIdx;
    private String title;
    private String content;
    private String uId;
    private Date createdAt;
    private Date updatedAt;
}
