package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件赔付规则记录对象 claim_case_cal_rule
 * 
 * @author sino
 * @date 2021-01-20
 */
public class ClaimCaseCalRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 赔付费用明细ID */
    private Long calRuleId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 赔付费用明细ID */
    @Excel(name = "赔付费用明细ID")
    private Long calItemId;

    /** 规则号 */
    @Excel(name = "规则号")
    private String ruleNo;

    /** 本次使用值 */
    @Excel(name = "本次使用值")
    private BigDecimal usedValue;

    /** 本次计算前剩余值 */
    @Excel(name = "本次计算前剩余值")
    private BigDecimal surplusValue;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setCalRuleId(Long calRuleId) 
    {
        this.calRuleId = calRuleId;
    }

    public Long getCalRuleId() 
    {
        return calRuleId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setCalItemId(Long calItemId) 
    {
        this.calItemId = calItemId;
    }

    public Long getCalItemId() 
    {
        return calItemId;
    }
    public void setRuleNo(String ruleNo) 
    {
        this.ruleNo = ruleNo;
    }

    public String getRuleNo() 
    {
        return ruleNo;
    }
    public void setUsedValue(BigDecimal usedValue) 
    {
        this.usedValue = usedValue;
    }

    public BigDecimal getUsedValue() 
    {
        return usedValue;
    }
    public void setSurplusValue(BigDecimal surplusValue) 
    {
        this.surplusValue = surplusValue;
    }

    public BigDecimal getSurplusValue() 
    {
        return surplusValue;
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
            .append("calRuleId", getCalRuleId())
            .append("rptNo", getRptNo())
            .append("calItemId", getCalItemId())
            .append("ruleNo", getRuleNo())
            .append("usedValue", getUsedValue())
            .append("surplusValue", getSurplusValue())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
