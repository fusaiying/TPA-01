package com.paic.ehis.cs.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class Level2 {
    private String Level2code;

    private String Level2name;

    private List<Level3> level3;
}
