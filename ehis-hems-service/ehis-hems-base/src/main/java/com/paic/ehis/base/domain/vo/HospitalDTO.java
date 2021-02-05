package com.paic.ehis.base.domain.vo;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

@Data
public class HospitalDTO extends BaseEntity {

    /*险种代码*/
    private String planCodes;

    /*险种名称*/
    private String planName;

    /*医疗网络类型*/
    private String hospitalTypeCodes;

    /*类型*/
    private String hospitalSuperTypeCode;

    /*省、直辖市*/
    private String provinceCode;

    /*城市*/
    private String cityCode;

    /*区*/
    private String countyCode;

    /*详细地址*/
    private String address;

    /*中文名称*/
    private String hospitalNameCn;

    /*英文名称*/
    private String hospitalNameEn;

    /*一级属性*/
    private String hospitalPropertyCode;

    /*二级属性*/
    private String hospitalGradeCode;

    /*三级属性*/
    private String hospitalLevel;

    /*四级属性*/
    private String hospitalLevelfour;

    /*医院类别*/
    private String hospitalInnerGrade;

    /*社保医院种类*/
    private String hospitalSocialCode;

    /*是否可自行预约*/
    private String hospitalIsselCode;

    /*预约电话*/
    private String clinicBookPhone;

    /*对非直结客户是否提供门诊预约*/
    private String isSupplyForNodirectbilling;

    /*是否提供VIP门诊预约*/
    private String isSupplyVipClinic;

    /*网络医院直结类型*/
    private String hospitalDirectPayTypeCode;

    /*二证齐全是否发授权书*/
    private String isSendAuthorization;

    /*流程是否顺畅*/
    private String hospitalIsprocessokCode;

    /*是否推荐*/
    private String hospitalIscommandCode;

    /*服务特色*/
    private String specialInfo;

    /*科室名称*/
    private String departmentName;

    /*特殊服务语种*/
    private String specialLanguageCode;

    /*是否周末营业*/
    private String isHolidayOpen;

    /*是否周六营业*/
    private String isSaturdayOpen;

    /*是否周日营业*/
    private String isSundayOpen;

    /*是否有效*/
    private String isValid;

    /*是否有急诊*/
    private String isHaveEmergency;

    private int pageNum;

    private int pageSize;


}
