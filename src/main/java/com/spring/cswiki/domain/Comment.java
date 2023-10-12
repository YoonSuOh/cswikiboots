package com.spring.cswiki.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int cm_num;
    private String u_id;
    private int d_num;
    private String cm_comment;
    private LocalDateTime cm_time;
}