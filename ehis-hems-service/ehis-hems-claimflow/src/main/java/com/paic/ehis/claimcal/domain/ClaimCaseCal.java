package com.paic.ehis.claimcal.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件赔付信息对象 claim_case_cal
 * 
 * @author sino
 * @date 2021-01-20
 */
public class ClaimCaseCal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 赔付ID */
    private Long calId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 账单币种 */
    @Excel(name = "账单币种")
    private String billCurrency;

    /** 理算金额 */
    @Excel(name = "理算金额")
    private BigDecimal calAmount;

    /** 赔付金额 */
    @Excel(name = "赔付金额")
    private BigDecimal payAmount;

    /** 拒赔金额 */
    @Excel(name = "拒赔金额")
    private BigDecimal refusedAmount;

    /** 追讨金额 */
    @Excel(name = "追讨金额")
    private BigDecimal debtAmount;

    /** 汇率 */
    @Excel(name = "汇率")
    private BigDecimal exchangeRate;

    /** 赔付金额（外币） */
    @Excel(name = "赔付金额", readConverterExp = "外=币")
    private BigDecimal payAmountForeign;

    /** 赔付结论 */
    @Excel(name = "赔付结论")
    private String payConclusion;

    /** 拒赔原因 */
    @Excel(name = "拒赔原因")
    private String refusedReason;

    /** 核赔依据 */
    @Excel(name = "核赔依据")
    private String claimCheck;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setCalId(Long calId) 
    {
        this.calId = calId;
    }

    public Long getCalId() 
    {
        return calId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setBillCurrency(String billCurrency) 
    {
        this.billCurrency = billCurrency;
    }

    public String getBillCurrency() 
    {
        return billCurrency;
    }
    public void setCalAmount(BigDecimal calAmount) 
    {
        this.calAmount = calAmount;
    }

    public BigDecimal getCalAmount() 
    {
        return calAmount;
    }
    public void setPayAmount(BigDecimal payAmount) 
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() 
    {
        return payAmount;
    }
    public void setRefusedAmount(BigDecimal refusedAmount) 
    {
        this.refusedAmount = refusedAmount;
    }

    public BigDecimal getRefusedAmount() 
    {
        return refusedAmount;
    }
    public void setDebtAmount(BigDecimal debtAmount) 
    {
        this.debtAmount = debtAmount;
    }

    public BigDecimal getDebtAmount() 
    {
        return debtAmount;
    }
    public void setExchangeRate(BigDecimal exchangeRate) 
    {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getExchangeRate() 
    {
        return exchangeRate;
    }
    public void setPayAmountForeign(BigDecimal payAmountForeign) 
    {
        this.payAmountForeign = payAmountForeign;
    }

    public BigDecimal getPayAmountForeign() 
    {
        return payAmountForeign;
    }
    public void setPayConclusion(String payConclusion) 
    {
        this.payConclusion = payConclusion;
    }

    public String getPayConclusion() 
    {
        return payConclusion;
    }
    public void setRefusedReason(String refusedReason) 
    {
        this.refusedReason = refusedReason;
    }

    public String getRefusedReason() 
    {
        return refusedReason;
    }
    public void setClaimCheck(String claimCheck) 
    {
        this.claimCheck = claimCheck;
    }

    public String getClaimCheck() 
    {
        return claimCheck;
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
            .append("calId", getCalId())
            .append("rptNo", getRptNo())
            .append("billCurrency", getBillCurrency())
            .append("calAmount", getCalAmount())
            .append("payAmount", getPayAmount())
            .append("refusedAmount", getRefusedAmount())
            .append("debtAmount", getDebtAmount())
            .append("exchangeRate", getExchangeRate())
            .append("payAmountForeign", getPayAmountForeign())
            .append("payConclusion", getPayConclusion())
            .append("refusedReason", getRefusedReason())
            .append("remark", getRemark())
            .append("claimCheck", getClaimCheck())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
