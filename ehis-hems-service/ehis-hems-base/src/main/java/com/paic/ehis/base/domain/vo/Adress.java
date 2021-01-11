package com.paic.ehis.base.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class Adress {
    private static final long serialVersionUID = 1L;

    private String provincecode;

    private String provincename;

    private List<City> cities;

}
