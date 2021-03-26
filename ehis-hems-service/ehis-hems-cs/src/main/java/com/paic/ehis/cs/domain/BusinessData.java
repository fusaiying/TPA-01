package com.paic.ehis.cs.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class BusinessData implements Serializable {

    /** 来电人与被保人关系代码*/
    private String callerInsRelation;
    /** 联系人性别*/
    private String contaGenderCode;
    /** 联系人姓名*/
    private String contaName;
    /** 无*/
    private String reportNo;

//    /** 发生时间 沟通删除*/
//    private Date occueDate;

    /** 服务受理人*/
    private String accepter;

//    /** 城市代码 沟通删除*/
//    private String cityCode;

    /** 服务类型 type = informationApplication */
    private String type;
    /** 服务受理渠道*/
    private String acceptChannel;

//    /** app版本号 沟通删除*/
//    private String appNo;

    /** 受理日期*/
    private Date acceptDate;
    /** 联系人与被保人关系*/
    private String contactsInsRelation;
    /** 上传图片的路径，大小限制不超过二十个;附件地址*/
    private String filePath;

//    /** 开户行所在地 沟通删除*/
//    private String bankLocation;

    /** 业务内容*/
    private String applicationContent;

//    /** 开户行 沟通删除*/
//    private String bankName;

//    /** 账号 沟通删除*/
//    private String accountNo;

    /** 联系人语言*/
    private String perferLan;

//    /** 注册手机号 沟通删除*/
//    private String enrolPhone;

    /** 来电人电话*/
    private String phoneNo;

//    /** 主附约号 沟通删除*/
//    private String brno;

    /** 被保人证件号码*/
    private String insRelationIdNo;

//    /** 三级服务项目代码 沟通删除*/
//    private String serviceThridItemCode;

    /** 联系人邮箱*/
    private String email;

//    /** 发生问题页面 沟通删除*/
//    private String questionPage;
//    /** 无 沟通删除*/
//    private String syncFailTimes;

    /** 来电人*/
    private String caller;
    /** 受理案件号*/
    private String applicationCaseNo;

//    /** 手机型号 沟通删除*/
//    private String phoneType;

    /** 二级服务项目代码 */
    private String serviceSecondItemCode;

//    /** 是否涉及银行转账 沟通删除*/
//    private String bankTransfer;
//    /** 省份代码 沟通删除*/
//    private String provinceCode;

    /** 优先级*/
    private String priorityCode;
    /** 客户号*/
    private String customerNo;
    /** 分单号*/
    private String certno;
    /** 联系人手机号*/
    private String contaMobileNo;
    /** 保单号*/
    private String policyList;

//    /** 服务项目编码 沟通删除*/
//    private String questionType;

    /** 二级机构代码 默认TPA的机构即总部*/
    private String regionCode;
    /** 电话中心流水号*/
    private String businessSeqNo;

//    /** 客户类型 沟通删除*/
//    private String customerType;
//    /** 无 沟通删除*/
//    private String evacuationReason;
//    /** 无 沟通删除*/
//    private String adviTypeCode;
//    /** 户名 沟通删除*/
//    private String accountName;

    /** 联系人家庭电话*/
    private LinePhone contaHomePhone;
    /** 处理时效*/
    private String dealDay;

//    /** 手机系统版本号 沟通删除*/
//    private String phoneSystemNo;
//
//    /** 后送原因 沟通删除*/
//    private String causeEvacuation;

    /** 产品名称 */
    private String productName;


    //############################## 投诉 受理对象 属性 ##########################
    /** 受理案件号*/
//    private String applicationCaseNo;
    /** 联系人姓名*/
//    private String contaName;
    /** 联系人性别*/
//    private String contaGenderCode;
    /** 服务受理人*/
//    private String accepter;
    /** 投诉人性别*/
    private String complainGenderCode;

//    /** 省份代码 沟通删除*/
//    private String provinceCode;
//    /** 城市代码 沟通删除*/
//    private String cityCode;

    /** 客户号*/
//    private String customerNo;
    /** 服务类型 type = complainApplication */
//    private String type;
    /** 分单号*/
//    private String certno;
    /** 联系人手机号*/
//    private String contaMobileNo;

    /** 投诉内容*/
    private String complainContent;
    /** 保单号*/
//    private String policyList;
    /** 受理日期*/
//    private Date acceptDate;
    /** 投诉人姓名*/
    private String complainName;

    /** 是否已劝解 沟通删除*/
    private String isReconciled;

    /** 优先级*/
//    private String priorityCode;
    /** 二级机构代码 默认TPA的机构即总部*/
//    private String regionCode;

//    /** 是否咨询前置 沟通删除*/
//    private String isPreComplain;

    /** 电话中心流水号*/
//    private String businessSeqNo;

//    /** 客户类型 沟通删除*/
//    private String customerType;

    /** 上传图片的路径，大小限制不超过二十个;附件地址*/
//    private String filePath;
    /** 联系人语言*/
//    private String perferLan;

//    /** 无 沟通删除*/
//    private String adviTypeCode;

    /** 来电人电话*/
//    private String phoneNo;

//    /** 主附约号 沟通删除*/
//    private String brno;

    /** 联系人家庭电话*/
//    private LinePhone contaHomePhone;
    /** 联系人邮箱*/
//    private String email;
    /** 投诉人身份*/
    private String complainRole;

//    /** 无 沟通删除*/
//    private String syncFailTimes;

    /** 处理时效*/
//    private String dealDay;


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
//    private String contaGenderCode;
    /** 联系人名字*/
//    private String contaName;
    /** 受理人*/
//    private String accepter;
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
//    private String type;
    /** 受理日期*/
//    private String acceptDate;

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
//    private String perferLan;
    /** 业务内容*/
    private String content;

    /** 来电人电话*/
//    private String phoneNo;

//    /** 主副合约号 沟通删除*/
//    private String brno;

    /** 联系人邮箱*/
//    private String email;
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
//    private String applicationCaseNo;
    /** 疫苗名称 */
    private String immunizationName;
    /** 最早发病时间单位*/
    private String earliestOnsetDateUnit;
    /** 客户号*/
//    private String customerNo;
    /** 疾病代码*/
    private String disease;
    /** 婚姻状况*/
    private String maritalStatus;
    /** 分单号*/
//    private String certno;
    /** 联系人电话*/
//    private String contaMobileNo;
    /** 就诊原因*/
    private String clinicReason;
    /** 保单号*/
//    private String policyList;
    /** 怀孕日期*/
    private String gravidityDate;
    /** 优先级*/
//    private String priorityCode;
    /** 地区编码*/
//    private String regionCode;
    /** 电话中心流水号*/
//    private String businessSeqNo;

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
//    private LinePhone contaHomePhone;
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
//    private String isCallFromHospital;
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
//    private String dealDay;



}