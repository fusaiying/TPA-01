package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.claimflow.domain.PolicyRiskRelation;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PolicyListVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 保单id */
    private long policyId;
    /** 保单号 */
    private String policyNo;
    /** 分单号 */
    private String policyItemNo;
    /** 投保人姓名 */
    private String appName;
    /** 有效起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validStartDate;
    /** 有效止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validEndDate;
    /** 保单险类 */
    private String policyRiskType;
    /** 保单状态 */
    private String policyStatus;
    /** 特别约定 */
    private String specialAgreement;
    /** 出单公司名称 */
    private String companyName;
    /** 出单公司名称 */
    private String companyCode;
    /** 计划编码 */
    private String planCode;
    /** 原保单号 */
    private String orgPolicyNo;
    /** 保单类型 */
    private String policyType;
    /** 原分单号 */
    private String orgPolicyItemNo;
    /**  社保标记 */
    private String ssFlag;
    /**  承保机构 */
    private String policyManageCom;

    /** 被保人客户号 */
    private String insuredNo;
    /** 被保人姓名 */
    private String name;
    /** 被保人性别 */
    private String sex;
    /** 被保人证件类型 */
    private String idType;
    /** 被保人证件号 */
    private String idNo;
    /** 被保人生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /** 职业编码 */
    private String occupation;
    /** 国籍 */
    private String nationality;
    /** 证件有效期起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date idStartDate;
    /** 证件有效期止期（长期=9999-12-31） */
    private Date idEndDate;
    /** 手机号码 */
    private String mobile;
    /** 邮箱 */
    private String email;
    /** 电话号 */
    private String phone;
    /** 省 */
    private String province;
    /** 市 */
    private String city;
    /** 区 */
    private String district;
    /** 详细地址 */
    private String address;
    /** 原被保人客户号 */
    private String orgInsuredNo;

    private String riskCodesStr;

    private List<PolicyRiskRelation> policyRiskRelations;

}
