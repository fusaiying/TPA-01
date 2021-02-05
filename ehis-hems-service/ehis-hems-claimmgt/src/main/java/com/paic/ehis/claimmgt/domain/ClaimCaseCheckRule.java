package com.paic.ehis.claimmgt.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件抽检规则对象 claim_case_check_rule
 * 
 * @author sino
 * @date 2021-01-22
 */
public class ClaimCaseCheckRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 抽检规则编码 */
    private String checkRuleNo;

    /** 案件类型 */
    @Excel(name = "案件类型")
    private String caseType;

    /** 赔付结论 */
    @Excel(name = "赔付结论")
    private String payConclusion;

    /** 金额类型 */
    @Excel(name = "金额类型")
    private String amountType;

    /** 抽检金额 */
    @Excel(name = "抽检金额")
    private BigDecimal checkAmount;

    /** 抽检比例 */
    @Excel(name = "抽检比例")
    private BigDecimal rate;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setCheckRuleNo(String checkRuleNo) 
    {
        this.checkRuleNo = checkRuleNo;
    }

    public String getCheckRuleNo() 
    {
        return checkRuleNo;
    }
    public void setCaseType(String caseType) 
    {
        this.caseType = caseType;
    }

    public String getCaseType() 
    {
        return caseType;
    }
    public void setPayConclusion(String payConclusion) 
    {
        this.payConclusion = payConclusion;
    }

    public String getPayConclusion() 
    {
        return payConclusion;
    }
    public void setAmountType(String amountType) 
    {
        this.amountType = amountType;
    }

    public String getAmountType() 
    {
        return amountType;
    }
    public void setCheckAmount(BigDecimal checkAmount) 
    {
        this.checkAmount = checkAmount;
    }

    public BigDecimal getCheckAmount() 
    {
        return checkAmount;
    }
    public void setRate(BigDecimal rate) 
    {
        this.rate = rate;
    }

    public BigDecimal getRate() 
    {
        return rate;
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
            .append("checkRuleNo", getCheckRuleNo())
            .append("caseType", getCaseType())
            .append("payConclusion", getPayConclusion())
            .append("amountType", getAmountType())
            .append("checkAmount", getCheckAmount())
            .append("rate", getRate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
