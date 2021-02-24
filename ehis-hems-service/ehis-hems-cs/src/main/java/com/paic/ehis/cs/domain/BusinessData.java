package com.paic.ehis.cs.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BusinessData implements Serializable {

    /** 来电人与被保人关系代码*/
    private String callerInsRelation;

    /** 联系人性别*/
    private String contaGenderCode;

    /** 联系人姓名*/
    private String contaName;

    /** 无*/
    private String reportNo;

    /** 发生时间*/
    private Date occueDate;

    /** 服务受理人*/
    private String accepter;

    /** 城市代码*/
    private String cityCode;

    /** 服务类型 type = informationApplication */
    private String type;

    /** 服务受理渠道*/
    private String acceptChannel;

    /** app版本号*/
    private String appNo;

    /** 受理日期*/
    private Date acceptDate;

    /** 联系人与被保人关系*/
    private String contactsInsRelation;

    /** 上传图片的路径，大小限制不超过二十个;附件地址*/
    private String filePath;

    /** 开户行所在地*/
    private String bankLocation;

    /** 业务内容*/
    private String applicationContent;

    /** 开户行*/
    private String bankName;

    /** 账号*/
    private String accountNo;

    /** 联系人语言*/
    private String perferLan;

    /** 注册手机号*/
    private String enrolPhone;

    /** 来电人电话*/
    private String phoneNo;

    /** 主附约号*/
    private String brno;

    /** 无*/
    private String insRelationIdNo;

    /** 三级服务项目代码*/
    private String serviceThridItemCode;

    /** 联系人邮箱*/
    private String email;

    /** 发生问题页面*/
    private String questionPage;

    /** 无*/
    private String syncFailTimes;

    /** 来电人*/
    private String caller;

    /** 受理号*/
    private String appliecationCaseNo;

    /** 手机型号*/
    private String phoneType;

    /** 二级服务项目代码*/
    private String serviceSecondItemCode;

    /** 是否涉及银行转账*/
    private String bankTransfer;

    /** 省份代码*/
    private String provinceCode;

    /** 优先级*/
    private String priorityCode;

    /** 客户号*/
    private String customerNo;

    /** 分单号*/
    private String certno;

    /** 联系人手机号*/
    private String contaMobileNo;

    //private String policyList;

    /** 服务项目编码*/
    private String questionType;

    /** 预约优先级*/
    private String priorityType;

    /** 二级机构代码*/
    private String regionCode;

    /** 电话中心流水号*/
    private String businessSeqNo;

    /** 客户类型*/
    private String customerType;

    /** 无*/
    private String evacuationReason;

    /** 无*/
    private String adviTypeCode;

    /** 户名*/
    private String accountName;

    /** 家庭电话*/
    private ContaHomePhone contaHomePhone;

    /** 无*/
    private String dealDay;

    /** 手机系统版本号*/
    private String phoneSystemNo;

    /** 后送原因*/
    private String causeEvacuation;

    /** 无*/
    private String productName;

    /** 受理案件号*/
    private String applicationCaseNo;

    /** 投诉人性别*/
    private String complainGenderCode;

    /** 投诉内容*/
    private String complainContent;

    /** 投诉人姓名*/
    private String complainName;

    /** 是否已劝解*/
    private String isReconciled;

    /** 是否咨询前置*/
    private String isPreComplain;

    /** 投诉人身份*/
    private String complainRole;
}