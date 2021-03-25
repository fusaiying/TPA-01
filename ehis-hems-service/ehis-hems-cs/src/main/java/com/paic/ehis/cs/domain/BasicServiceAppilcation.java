package com.paic.ehis.cs.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasicServiceAppilcation implements Serializable {

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

}
