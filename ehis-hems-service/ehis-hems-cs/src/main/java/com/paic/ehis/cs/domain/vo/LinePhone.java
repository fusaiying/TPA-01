package com.paic.ehis.cs.domain.vo;

import lombok.Data;
import lombok.ToString;

/**
 * 接口固定电话对象
 */
@Data
@ToString
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
