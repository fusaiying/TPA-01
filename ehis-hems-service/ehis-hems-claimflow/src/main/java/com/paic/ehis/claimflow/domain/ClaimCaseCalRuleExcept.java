package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 案件赔付规则例外对象 claim_case_cal_rule_except
 * 
 * @author sino
 * @date 2021-01-20
 */
public class ClaimCaseCalRuleExcept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 理算规则例外ID */
    private Long ruleExcId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 规则号 */
    @Excel(name = "规则号")
    private String ruleNo;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setRuleExcId(Long ruleExcId) 
    {
        this.ruleExcId = ruleExcId;
    }

    public Long getRuleExcId() 
    {
        return ruleExcId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setRuleNo(String ruleNo) 
    {
        this.ruleNo = ruleNo;
    }

    public String getRuleNo() 
    {
        return ruleNo;
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
            .append("ruleExcId", getRuleExcId())
            .append("rptNo", getRptNo())
            .append("ruleNo", getRuleNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
