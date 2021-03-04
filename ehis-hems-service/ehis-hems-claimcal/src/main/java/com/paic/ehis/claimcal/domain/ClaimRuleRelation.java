package com.paic.ehis.claimcal.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 规则对象关联 对象 claim_rule_relation
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimRuleRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 对象关联号 */
    private String relationNo;

    /** 规则号 */
    @Excel(name = "规则号")
    private String ruleNo;

    /** 计划编码 */
    @Excel(name = "计划编码")
    private String planCode;

    /** 责任编码 */
    @Excel(name = "责任编码")
    private String dutyCode;

    /** 责任明细编码 */
    @Excel(name = "责任明细编码")
    private String dutyDetailCode;

    /** 费用项编码 */
    @Excel(name = "费用项编码")
    private String feeitemCode;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setRelationNo(String relationNo) 
    {
        this.relationNo = relationNo;
    }

    public String getRelationNo() 
    {
        return relationNo;
    }
    public void setRuleNo(String ruleNo) 
    {
        this.ruleNo = ruleNo;
    }

    public String getRuleNo() 
    {
        return ruleNo;
    }
    public void setPlanCode(String planCode) 
    {
        this.planCode = planCode;
    }

    public String getPlanCode() 
    {
        return planCode;
    }
    public void setDutyCode(String dutyCode) 
    {
        this.dutyCode = dutyCode;
    }

    public String getDutyCode() 
    {
        return dutyCode;
    }
    public void setDutyDetailCode(String dutyDetailCode) 
    {
        this.dutyDetailCode = dutyDetailCode;
    }

    public String getDutyDetailCode() 
    {
        return dutyDetailCode;
    }
    public void setFeeitemCode(String feeitemCode) 
    {
        this.feeitemCode = feeitemCode;
    }

    public String getFeeitemCode() 
    {
        return feeitemCode;
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
            .append("relationNo", getRelationNo())
            .append("ruleNo", getRuleNo())
            .append("planCode", getPlanCode())
            .append("dutyCode", getDutyCode())
            .append("dutyDetailCode", getDutyDetailCode())
            .append("feeitemCode", getFeeitemCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
