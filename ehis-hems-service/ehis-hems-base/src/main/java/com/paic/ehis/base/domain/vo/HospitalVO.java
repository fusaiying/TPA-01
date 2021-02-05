package com.paic.ehis.base.domain.vo;

import com.paic.ehis.base.domain.BaseProviderDep;
import lombok.Data;

import java.util.List;

@Data
public class HospitalVO {

    /*中文名称*/
    private String hospitalNameCn;

    /*预约电话*/
    private String clinicBookPhone;

    /*服务特色*/
    private String specialInfo;

    /*服务商编码*/
    private String partnerId;

    /*服务特色简略*/
    private String specialInfoAbbr;

    /*是否可自行预约*/
    private String hospitalIsselCode;

    /*地址*/
    private String address;

    /*区县*/
    private String county;

    /*英文名称*/
    private String hospitalNameEn;

    /*直接类型*/
    private String hospitalDirectPayTypeCode;

    /*区域*/
    private String district;

    /*二证齐全是否发授权书*/
    private String isSendAuthorization;

    private List<BaseProviderDep> deptList;

    /*省、直辖市*/
    private String provinceCode;

    /*城市*/
    private String cityCode;

    /*区*/
    private String countyCode;
}
