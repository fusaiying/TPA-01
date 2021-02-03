package com.paic.ehis.base.domain;


import lombok.Data;

@Data
public class hospitalInfo {
    /*医院Id*/
    private  String partnerId;

    /*医疗机构名称*/
    private String hospitalNameCn;

    /*地址*/
    private String address;

    /*医院类型*/
    private String hospitalType;

    /*门诊直结*/
    private String directPatientFlag;

    /*住院直结*/
    private String directHospitalFlag;

    /*国家*/
    private String countryName;

    /*邮箱*/
    private String email;

    /*传真*/
    private String fax;
}
