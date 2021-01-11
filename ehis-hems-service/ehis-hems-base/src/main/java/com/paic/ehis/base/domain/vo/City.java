package com.paic.ehis.base.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class City {

    private String citycode;

    private String cityname;

    private List<AddressInfo> district;
}
