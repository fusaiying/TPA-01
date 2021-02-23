package com.paic.ehis.system.api.domain;



import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 案件保单关联对象 claim_case_policy
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class ClaimCasePolicy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 被保人编码 */
    @Excel(name = "被保人编码")
    private String insuredNo;
    /** 关联ID */
    private Long relationId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    private  String policyItemNo;//分单号
    private  String name;//被保人姓名
    private  String appName;//投保人姓名
    private Date validStartDate;//有效起期
    private Date validEndDate;//有效止期
    private  String policyRiskType;//保单险类
    private  String policyStatus;//保单状态
    private  String specialAgreement;//特别约定
    private  String companyCode;//出单公司编码
    private  String companyName;//出单公司名称
    private  String policyManageCom;//承保机构
    private  String policyType;//保单类型
    private  String planCode;//计划编码
    private  String orgPolicyNo;//原保单号
    private  String orgPolicyItemNo;//原分单号
    private  String ssFlag;//社保标记

    @Override
    public String toString() {
        return "ClaimCasePolicy{" +
                "insuredNo='" + insuredNo + '\'' +
                ", relationId=" + relationId +
                ", rptNo='" + rptNo + '\'' +
                ", policyNo='" + policyNo + '\'' +
                ", status='" + status + '\'' +
                ", policyItemNo='" + policyItemNo + '\'' +
                ", name='" + name + '\'' +
                ", appName='" + appName + '\'' +
                ", validStartDate=" + validStartDate +
                ", validEndDate=" + validEndDate +
                ", policyRiskType='" + policyRiskType + '\'' +
                ", policyStatus='" + policyStatus + '\'' +
                ", specialAgreement='" + specialAgreement + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", policyManageCom='" + policyManageCom + '\'' +
                ", policyType='" + policyType + '\'' +
                ", planCode='" + planCode + '\'' +
                ", orgPolicyNo='" + orgPolicyNo + '\'' +
                ", orgPolicyItemNo='" + orgPolicyItemNo + '\'' +
                ", ssFlag='" + ssFlag + '\'' +
                '}';
    }
}
