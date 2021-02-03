package com.paic.ehis.base.domain.vo;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class HSDTO extends BaseEntity {

    /*医疗机构名称*/
    private String hospitalName;

    /*医疗机构所属城市名称*/
    private String CityCode;

    /*医疗机构所属城市名称*/
    private String cityName;

    /*医疗机构所属城市国家*/
    private String countryCode;

    /*医疗机构所属城市国家*/
    private String countryName;

    /*医疗机构所属国家标示*/
    private String country;
}
