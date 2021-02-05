package com.paic.ehis.cs.domain;


import lombok.Data;

@Data
public class Front {
    /** 是否涉及银行转账*/
    private String bankTransfer;

    /** 开户行*/
    private String bankName;

    /** 开户地*/
    private String bankLocation;

    /** 账号*/
    private String accountNumber;

    /** 户名*/
    private String bankHolder;

    /** 有无附件*/
    private String attachmentFlag;

    /** 预约时间*/
    private String complaintTime;

    /** 门诊直接结算服务项目*/
    private String outpatientSettlement;

    /** 就诊类型*/
    private String visitType;

    /** 疾病名称*/
    private String disease;

    /** 症状或体征*/
    private String symptomsSigns;

    /** 本次疾病/症状起病时间*/
    private String symptomTimes;

    /** 时间单位*/
    private String timeUnit;

    /** 是否意外*/
    private String accidentFlag;

    /** 意外原因*/
    private String accidentReason;

    /** 是否持有效证件*/
    private String validCertificate;

    /** 是否持有直结卡*/
    private String settlementCard;

    /** 预计住院天数*/
    private String hospitalDays;

    /** 医疗机构*/
    private String medicalInstitution;

    /** 科室*/
    private String department;

    /** 床位*/
    private String bunk;

    /** 医院赔付比例*/
    private String compensationRatio;



}
