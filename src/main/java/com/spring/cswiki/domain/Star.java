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
public class Star {
    private int star;
    private String u_id;
    private int d_num;
    private LocalDateTime lastVisit;
}
