package com.paic.ehis.claimflow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 保单信息对象 policy_info
 *
 * @author sino
 * @date 2021-01-14
 */
@Data
public class PolicyInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**保单id*/
    private long policyId;
    /** 保单号 */
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

    public void setPolicyNo(String policyNo)
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo()
    {
        return policyNo;
    }
    public void setPolicyItemNo(String policyItemNo)
    {
        this.policyItemNo = policyItemNo;
    }

    public String getPolicyItemNo()
    {
        return policyItemNo;
    }
    public void setInsuredNo(String insuredNo)
    {
        this.insuredNo = insuredNo;
    }

    public String getInsuredNo()
    {
        return insuredNo;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setValidStartDate(Date validStartDate)
    {
        this.validStartDate = validStartDate;
    }

    public Date getValidStartDate()
    {
        return validStartDate;
    }
    public void setValidEndDate(Date validEndDate)
    {
        this.validEndDate = validEndDate;
    }

    public Date getValidEndDate()
    {
        return validEndDate;
    }
    public void setPolicyRiskType(String policyRiskType)
    {
        this.policyRiskType = policyRiskType;
    }

    public String getPolicyRiskType()
    {
        return policyRiskType;
    }
    public void setPolicyStatus(String policyStatus)
    {
        this.policyStatus = policyStatus;
    }

    public String getPolicyStatus()
    {
        return policyStatus;
    }
    public void setSpecialAgreement(String specialAgreement)
    {
        this.specialAgreement = specialAgreement;
    }

    public String getSpecialAgreement()
    {
        return specialAgreement;
    }
    public void setCompanyCode(String companyCode)
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode()
    {
        return companyCode;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setPolicyManageCom(String policyManageCom)
    {
        this.policyManageCom = policyManageCom;
    }

    public String getPolicyManageCom()
    {
        return policyManageCom;
    }
    public void setPolicyType(String policyType)
    {
        this.policyType = policyType;
    }

    public String getPolicyType()
    {
        return policyType;
    }
    public void setPlanCode(String planCode)
    {
        this.planCode = planCode;
    }

    public String getPlanCode()
    {
        return planCode;
    }
    public void setOrgPolicyNo(String orgPolicyNo)
    {
        this.orgPolicyNo = orgPolicyNo;
    }

    public String getOrgPolicyNo()
    {
        return orgPolicyNo;
    }
    public void setOrgPolicyItemNo(String orgPolicyItemNo)
    {
        this.orgPolicyItemNo = orgPolicyItemNo;
    }

    public String getOrgPolicyItemNo()
    {
        return orgPolicyItemNo;
    }
    public void setSsFlag(String ssFlag)
    {
        this.ssFlag = ssFlag;
    }

    public String getSsFlag()
    {
        return ssFlag;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("policyNo", getPolicyNo())
                .append("policyItemNo", getPolicyItemNo())
                .append("insuredNo", getInsuredNo())
                .append("name", getName())
                .append("validStartDate", getValidStartDate())
                .append("validEndDate", getValidEndDate())
                .append("policyRiskType", getPolicyRiskType())
                .append("policyStatus", getPolicyStatus())
                .append("specialAgreement", getSpecialAgreement())
                .append("companyCode", getCompanyCode())
                .append("companyName", getCompanyName())
                .append("policyManageCom", getPolicyManageCom())
                .append("policyType", getPolicyType())
                .append("planCode", getPlanCode())
                .append("orgPolicyNo", getOrgPolicyNo())
                .append("orgPolicyItemNo", getOrgPolicyItemNo())
                .append("ssFlag", getSsFlag())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}