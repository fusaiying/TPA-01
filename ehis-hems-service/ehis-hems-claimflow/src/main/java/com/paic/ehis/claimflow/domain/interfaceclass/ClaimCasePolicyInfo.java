package com.paic.ehis.claimflow.domain.interfaceclass;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 案件保单关联对象 claim_case_policy
 * 
 * @author sino
 * @date 2021-02-24
 */
@Data
public class ClaimCasePolicyInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联ID */
    private Long relationId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 被保人编码 */
    @Excel(name = "被保人编码")
    private String insuredNo;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    /** 投保人姓名 */
    @Excel(name = "投保人姓名")
    private String appName;

    /** 有效起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validStartDate;

    /** 有效止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validEndDate;

    /** 保单险类 */
    @Excel(name = "保单险类")
    private String policyRiskType;

    /** 保单状态 */
    @Excel(name = "保单状态")
    private String policyStatus;

    /** 特别约定 */
    @Excel(name = "特别约定")
    private String specialAgreement;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 出单公司名称 */
    @Excel(name = "出单公司名称")
    private String companyName;

    /** 承保机构 */
    @Excel(name = "承保机构")
    private String policyManageCom;

    /** 保单类型 */
    @Excel(name = "保单类型")
    private String policyType;

    /** 计划编码 */
    @Excel(name = "计划编码")
    private String planCode;

    /** 原保单号 */
    @Excel(name = "原保单号")
    private String orgPolicyNo;

    /** 原分单号 */
    @Excel(name = "原分单号")
    private String orgPolicyItemNo;

    /** 社保标记 */
    @Excel(name = "社保标记")
    private String ssFlag;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

}
