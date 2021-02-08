package com.paic.ehis.finance.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

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
    @Excel(name = "规则编码")
    private String ruleno;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companycode;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String riskcode;

    /** 结算类型 01-服务费 02-保费比例% */
    @Excel(name = "结算类型 01-服务费 02-保费比例%")
    private String settlementtype;

    /** 结算值 */
    @Excel(name = "结算值")
    private BigDecimal settlementvalue;

    /** 出单公司名称 */
    private String companyname;

    /** 出单公司名称 */
    private String riskName;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public void setRuleno(String ruleno)
    {
        this.ruleno = ruleno;
    }

    public String getRuleno()
    {
        return ruleno;
    }
    public void setCompanycode(String companyCode)
    {
        this.companycode = companyCode;
    }

    public String getCompanycode()
    {
        return companycode;
    }
    public void setRiskcode(String riskCode)
    {
        this.riskcode = riskCode;
    }

    public String getRiskcode()
    {
        return riskcode;
    }
    public void setSettlementtype(String settlementType)
    {
        this.settlementtype = settlementType;
    }

    public String getSettlementtype()
    {
        return settlementtype;
    }
    public void setSettlementvalue(BigDecimal settlementValue)
    {
        this.settlementvalue = settlementValue;
    }

    public BigDecimal getSettlementvalue()
    {
        return settlementvalue;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("ruleNo", getRuleno())
            .append("companyCode", getCompanycode())
            .append("riskCode", getRiskcode())
            .append("settlementType", getSettlementtype())
            .append("settlementValue", getSettlementvalue())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
