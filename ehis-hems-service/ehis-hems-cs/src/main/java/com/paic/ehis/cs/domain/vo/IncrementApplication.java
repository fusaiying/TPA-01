package com.paic.ehis.cs.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 增值服务- 客服模块预约受理
 * TODO 没有渠道信息？
 */
@Data
@ToString
public class IncrementApplication implements Serializable {

//    ############################## 增值服务 vipClinic 受理对象 属性 ##########################

    /** 意外原因*/
    private String accidentDescr;
    /** 疾病名称*/
    private String diseaseName;
    /** 来电人与被保险人关系*/
    private String callerRelationCode;
    /** 是否持有保险卡*/
    private String hasInsuranceCard;
    /** 是否意外*/
    private String isAccident;
    /** 预约日期*/
    private String preengagementDate;

    /** 联系人性别*/
    private String contaGenderCode;
    /** 联系人名字*/
    private String contaName;
    /** 受理人*/
    private String accepter;
    /** 是否有有效证件*/
    private String hasValidCertificate;
    /** 医院id*/
    private String hospitalId;
    /** 就诊时间*/
    private String clinicTime;
//    /** 就诊时间1 沟通删除 合并至就诊时间中*/
//    private String clinicTime1;
//    /** 就诊时间2 沟通删除 合并至就诊时间中*/
//    private String clinicTime2;

    /** 就诊人证件号码*/
    private String treatmentCertificateNo;

    /** 数据类型 vipClinic*/
    private String type;
    /** 受理日期*/
    private String acceptDate;

    /** 就诊人姓名*/
    private String treatmentName;
    /** 赔付比例*/
    private String payOutRate;
    /** 是否申请直结*/
    private String directPayFlag;
    /** 就诊类型 牙科、生育、*/
    private String clinicType;
    /** 来电人姓名*/
    private String callerName;
    /** 本次疾病起病时间*/
    private String onsetDate;
    /** 类似疾病起病日期*/
    private String earliestOnsetDate;

    /** 联系人语言*/
    private String perferLan;
    /** 业务内容*/
    private String content;

    /** 来电人电话*/
    private String phoneNo;

//    /** 主副合约号 沟通删除*/
//    private String brno;

    /** 联系人邮箱*/
    private String email;
    /** 医院名称*/
    private String hospitalName;
    /** 医院科室名称*/
    private String hospitalDepartmentName;
    /** 传真号*/
    private String faxNo;
    /** 怀孕时间单位 */
    private String gravidityDateUnit;

//    /** 同步失败时间 沟通删除*/
//    private String syncFailTimes;

    /** 案件号*/
    private String applicationCaseNo;
    /** 疫苗名称 */
    private String immunizationName;
    /** 最早发病时间单位*/
    private String earliestOnsetDateUnit;
    /** 客户号*/
    private String customerNo;
    /** 疾病代码*/
    private String disease;
    /** 婚姻状况*/
    private String maritalStatus;
    /** 分单号*/
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

//    /** 客户类型 沟通删除*/
//    private String customerType;

    /** 预约时间段 上午 , 下午, 全天*/
    private String preengagementTime;
    /** 医院来电号码*/
    private String hospitalCallPhoneNo;
    /** 症状或体征*/
    private String diseaseFeature;
    /** 申请人姓名*/
    private String applyName;
    /** 联系人家庭电话*/
    private LinePhone contaHomePhone;
    /** 本次疾病起病时间单位*/
    private String onsetDateUnit;
    /** 是否有直结资格 */
    private String isHasDb;


//    ############################## 增值服务 直结结算 门诊直接结算 clinicDirectPay 受理对象 属性 ##########################

    /** 意外原因*/
//    private String accidentDescr;
    /** 疾病名称*/
//    private String diseaseName;
    /** 来电人与被保险人关系*/
//    private String callerRelationCode;
    /** 是否持有保险卡*/
//    private String hasInsuranceCard;
    /** 是否意外*/
//    private String isAccident;
    /** 联系人性别*/
//    private String contaGenderCode;
    /** 联系人名字*/
//    private String contaName;
    /** 受理人*/
//    private String accepter;
    /** 是否有有效证件*/
//    private String hasValidCertificate;
    /** 医院id*/
//    private String hospitalId;
    /** 就诊时间*/
//    private String clinicTime;

//    /** 就诊时间1 沟通删除 合并至就诊时间中*/
//    private String clinicTime1;
//    /** 就诊时间2 沟通删除 合并至就诊时间中*/
//    private String clinicTime2;

    /** 数据类型 clinicDirectPay*/
//    private String type;
    /** 就诊日期 合并到 就诊时间中*/
//    private String clinicDate;
    /** 申请人证件类型*/
    private String applyCertificateType;
    /** 受理日期*/
//    private String acceptDate;
    /** 赔付比例*/
//    private String payOutRate;
    /** 就诊类型 牙科、生育、*/
//    private String clinicType;
    /** 就诊人姓名*/
//    private String callerName;
    /** 本次疾病起病时间*/
//    private String onsetDate;
    /** 类似疾病起病日期*/
//    private String earliestOnsetDate;
    /** 联系人语言*/
//    private String perferLan;
    /** 业务内容*/
//    private String content;
    /** 来电人电话*/
//    private String phoneNo;

//    /** 主副合约号 沟通删除*/
//    private String brno;

    /** 联系人邮箱*/
//    private String email;
    /** 医院名称*/
//    private String hospitalName;
    /** 医院科室名称*/
//    private String hospitalDepartmentName;
    /** 传真号*/
//    private String faxNo;
    /** 怀孕时间单位 */
//    private String gravidityDateUnit;

//    /** 同步失败时间 沟通删除*/
//    private String syncFailTimes;

    /** 案件号*/
//    private String applicationCaseNo;
    /** 申请人证件号*/
    private String applyCertificateNo;
    /** 疫苗名称 */
//    private String immunizationName;
    /** 客户号*/
//    private String customerNo;
    /** 最早发病时间单位*/
//    private String earliestOnsetDateUnit;
    /** 疾病代码*/
//    private String disease;
    /** 婚姻状况*/
//    private String maritalStatus;
    /** 分单号*/
//    private String certno;
    /** 联系人电话*/
//    private String contaMobileNo;
    /** 就诊原因*/
//    private String clinicReason;
    /** 保单号*/
//    private String policyList;
    /** 是否是医院来电*/
    private String isCallFromHospital;
    /** 怀孕日期*/
//    private String gravidityDate;
    /** 优先级*/
//    private String priorityCode;
    /** 地区编码*/
//    private String regionCode;
    /** 电话中心流水号*/
//    private String businessSeqNo;

//    /** 客户类型 沟通删除*/
//    private String customerType;
    /** 症状或体征*/
//    private String diseaseFeature;
    /** 申请人姓名*/
//    private String applyName;
    /** 联系人家庭电话*/
//    private LinePhone contaHomePhone;
    /** 本次疾病起病时间单位*/
//    private String onsetDateUnit;
    /** 处理时效*/
    private String dealDay;

}