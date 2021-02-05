package com.paic.ehis.claimcal.domain;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 福利规则 对象 claim_rule
 * 
 * @author sino
 * @date 2021-01-06
 */
public class ClaimRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则号 */
    private String ruleNo;

    /** 计划编码 */
    @Excel(name = "计划编码")
    private String planCode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String riskCode;

    /** 规则层级 */
    @Excel(name = "规则层级")
    private String ruleLevel;

    /** 规则要素 */
    @Excel(name = "规则要素")
    private String ruleElement;

    /** 要素值 */
    @Excel(name = "要素值")
    private BigDecimal elementValue;

    /** 要素单位 */
    @Excel(name = "要素单位")
    private String elementUnit;

    /** 累计方式 */
    @Excel(name = "累计方式")
    private String cumulativeMode;

    /** 规则说明 */
    @Excel(name = "规则说明")
    private String ruleDescription;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

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
    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setRuleLevel(String ruleLevel) 
    {
        this.ruleLevel = ruleLevel;
    }

    public String getRuleLevel() 
    {
        return ruleLevel;
    }
    public void setRuleElement(String ruleElement) 
    {
        this.ruleElement = ruleElement;
    }

    public String getRuleElement() 
    {
        return ruleElement;
    }
    public void setElementValue(BigDecimal elementValue) 
    {
        this.elementValue = elementValue;
    }

    public BigDecimal getElementValue() 
    {
        return elementValue;
    }
    public void setElementUnit(String elementUnit) 
    {
        this.elementUnit = elementUnit;
    }

    public String getElementUnit() 
    {
        return elementUnit;
    }
    public void setCumulativeMode(String cumulativeMode) 
    {
        this.cumulativeMode = cumulativeMode;
    }

    public String getCumulativeMode() 
    {
        return cumulativeMode;
    }
    public void setRuleDescription(String ruleDescription) 
    {
        this.ruleDescription = ruleDescription;
    }

    public String getRuleDescription() 
    {
        return ruleDescription;
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
            .append("ruleNo", getRuleNo())
            .append("planCode", getPlanCode())
            .append("riskCode", getRiskCode())
            .append("ruleLevel", getRuleLevel())
            .append("ruleElement", getRuleElement())
            .append("elementValue", getElementValue())
            .append("elementUnit", getElementUnit())
            .append("cumulativeMode", getCumulativeMode())
            .append("ruleDescription", getRuleDescription())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
