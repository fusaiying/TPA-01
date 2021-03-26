package com.paic.ehis.cs.domain;

import lombok.Data;

@Data
public class LinePhone {
    /** 电话号码*/
    private String phoneNo;

    /** 国家代码*/
    private String phoneCountryCode;

    /** 分机号码*/
    private String extensionNo;

    /** 电话区号*/
    private String phoneAreaCode;


}
