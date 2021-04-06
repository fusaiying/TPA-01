package com.paic.ehis.base.domain.dto;

import lombok.Data;

/**
 *
 * subject:医院基础信息维护
 */
@Data
public class BasicHospitalInformationDTO {
    /**医院ID*/
    private String hospitlId;
    /**医院英文名*/
    private String hospitlEnName;
    /**国家编码*/
    private String countryCode;
    /**身份编码*/
    private String provinceCode;
    /**城市编码*/
    private String cityCode;
    /**区县编码*/
    private String countyCode;
    /**医院中文地址*/
    private String znAddress;
    /**医院英文地址*/
    private String enAddress;
    /**医院邮编*/
    private String zipCode;
    /**医院电话*/
    private String phoneNo;
    /**医院网址*/
    private String website;
    /**医院性质*/
    private String propertyCode;
    /**医院等级*/
    private String gradeCode;
    /**医院综专科类型*/
    private String levelCode;
    /**医院综专科子类型*/
    private String levelChildCode;
    /**四级属性*/
    private String levelFourCode;
    /**是否可住院*/
    private String isHasBed;
    /**床位数*/
    private String bedNum;
    /**服务定位*/
    private String serviceLocatorCode;
    /**社保定点医院*/
    private String socialHospitalSort;
    /**部队医院*/
    private String isForcesHospital;
    /**康复医院*/
    private String isRecoveryHospital;
    /**医院简介*/
    private String briefIntroduction;
    /**交通路线*/
    private String Roadline;
    /**信息类型*/
    private String operateType;
    /**修改申请人*/
    private String modifyApplicant;
}
