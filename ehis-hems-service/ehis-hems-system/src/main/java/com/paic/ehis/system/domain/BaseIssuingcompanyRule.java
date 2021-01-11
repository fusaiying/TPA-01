package com.paic.ehis.system.domain;

import java.math.BigDecimal;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 出单公司规则 对象 base_issuingcompany_rule
 * 
 * @author sino
 * @date 2021-01-05
 */
public class BaseIssuingcompanyRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则编码 */
    private String ruleNo;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String riskCode;

    /** 结算类型 01-服务费 02-保费比例% */
    @Excel(name = "结算类型 01-服务费 02-保费比例%")
    private String settlementType;

    /** 结算值 */
    @Excel(name = "结算值")
    private BigDecimal settlementValue;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setRuleNo(String ruleNo) 
    {
        this.ruleNo = ruleNo;
    }

    public String getRuleNo() 
    {
        return ruleNo;
    }
    public void setCompanyCode(String companyCode) 
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() 
    {
        return companyCode;
    }
    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setSettlementType(String settlementType) 
    {
        this.settlementType = settlementType;
    }

    public String getSettlementType() 
    {
        return settlementType;
    }
    public void setSettlementValue(BigDecimal settlementValue) 
    {
        this.settlementValue = settlementValue;
    }

    public BigDecimal getSettlementValue() 
    {
        return settlementValue;
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
            .append("companyCode", getCompanyCode())
            .append("riskCode", getRiskCode())
            .append("settlementType", getSettlementType())
            .append("settlementValue", getSettlementValue())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
