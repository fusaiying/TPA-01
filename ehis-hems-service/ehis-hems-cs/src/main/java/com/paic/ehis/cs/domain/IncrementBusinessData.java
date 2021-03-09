package com.paic.ehis.cs.domain;

import lombok.Data;

@Data
public class IncrementBusinessData {
    /** 意外情况描述*/
    private String accidentDescr;
    /** 联系人语言*/
    private String perferLan;
    /** 受理人*/
    private String accepter;
    /** 受理日期*/
    private String acceptDate;
    /** 疾病名称*/
    private String diseaseName;
    /** 来电人关系*/
    private String callerRelationCode;
    /** 是否有直结卡*/
    private String hasInsuranceCard;
    /** 是否意外*/
    private String isAccident;
    /** 预约日期*/
    private String preengagementDate;
    /** 联系人性别*/
    private String contaGenderCode;
    /** 联系人名字*/
    private String contaName;
    /** 是否有有效证件*/
    private String hasValidCertificate;
    /** 医院id*/
    private String hospitalId;
    /** 就诊时间*/
    private String clinicTime;
    /** 病历号*/
    private String treatmentCertificateNo;
    /** 数据类型*/
    private String type;
    /** 病人名字*/
    private String treatmentName;
    /** 支付比例*/
    private String payOutRate;
    /** 是否直接支付*/
    private String directPayFlag;
    /** 就诊类型*/
    private String clinicType;
    /** 来电人姓名*/
    private String callerName;
    /** 发病时间*/
    private String onsetDate;
    /** 最早发病日期*/
    private String earliestOnsetDate;
    /** 内容*/
    private String content;
    /** 联系人电话*/
    private String phoneNo;
    /** 主副合约号*/
    private String brno;
    /** 联系人邮箱*/
    private String email;
    /** 医院名字*/
    private String hospitalName;
    /** 医院科室名称*/
    private String hospitalDepartmentName;
    /** 传真号*/
    private String faxNo;
    /** 怀孕日期单位*/
    private String gravidityDateUnit;
    /** 同步失败时间*/
    private String syncFailTimes;
    /** 案件号*/
    private String applicationCaseNo;
    /** 减免名称*/
    private String immunizationName;
    /** 最早发病时间单位*/
    private String earliestOnsetDateUnit;
    /** 客户号*/
    private String customerNo;
    /** 疾病*/
    private String disease;
    /** 婚姻状况*/
    private String maritalStatus;
    /** 分单号数组*/
    private String certno;
    /** 联系人电话*/
    private String contaMobileNo;
    /** 就诊原因*/
    private String clinicReason;
    /** 保单号*/
    private String policyList;
    /** 怀孕日期*/
    private String gravidityDate;
    /** 优先级*/
    private String priorityCode;
    /** 地区编码*/
    private String regionCode;
    /** 电话中心流水号*/
    private String businessSeqNo;
    /** 客户类型*/
    private String customerType;
    /** 预约时间*/
    private String preengagementTime;
    /** 医院来电号码*/
    private String hospitalCallPhoneNo;
    /** 疾病症状*/
    private String diseaseFeature;
    /** 申请人姓名*/
    private String applyName;
    /** 发病时间单位*/
    private String onsetDateUnit;
    /** 联系人家庭电话*/
    private ContaHomePhone contaHomePhone;
    /** */
    private String isHasDb;
    /** 就诊日期*/
    private String clinicDate;
    /** 申请人证件类型*/
    private String applyCertificateType;
    /** 就诊时间1*/
    private String clinicTime1;
    /** 就诊时间2*/
    private String clinicTime2;
    /** 申请人证件号*/
    private String applyCertificateNo;
    /** 是否是医院来电*/
    private String isCallFromHospital;
    /** 结算日期*/
    private String dealDay;
    /** 意外地点*/
    private String accidentPlace;
    /** */
    private String connectingFlag;
    /** 意外日期*/
    private String accidentDate;
    /** 意外内容*/
    private String accidentContent;

}
