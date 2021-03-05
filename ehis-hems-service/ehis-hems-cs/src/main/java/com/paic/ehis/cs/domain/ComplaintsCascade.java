package com.paic.ehis.cs.domain;

import lombok.Data;

import java.util.List;
@Data
public class ComplaintsCascade {
    private static final long serialVersionUID = 1L;


    private String level1code;

    private String level1name;

    private List<com.paic.ehis.cs.domain.vo.Level2> level2;
}
