package com.paic.ehis.claimflow.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件追讨信息对象 claim_case_debt
 * 
 * @author sino
 * @date 2021-01-20
 */
public class ClaimCaseDebt extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 追讨ID */
    private Long debtId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 被保人客户号 */
    @Excel(name = "被保人客户号")
    private String insuredNo;

    /** 追讨金额 */
    @Excel(name = "追讨金额")
    private BigDecimal debtAmount;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setDebtId(Long debtId) 
    {
        this.debtId = debtId;
    }

    public Long getDebtId() 
    {
        return debtId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setInsuredNo(String insuredNo) 
    {
        this.insuredNo = insuredNo;
    }

    public String getInsuredNo() 
    {
        return insuredNo;
    }
    public void setDebtAmount(BigDecimal debtAmount) 
    {
        this.debtAmount = debtAmount;
    }

    public BigDecimal getDebtAmount() 
    {
        return debtAmount;
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
            .append("debtId", getDebtId())
            .append("rptNo", getRptNo())
            .append("insuredNo", getInsuredNo())
            .append("debtAmount", getDebtAmount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
