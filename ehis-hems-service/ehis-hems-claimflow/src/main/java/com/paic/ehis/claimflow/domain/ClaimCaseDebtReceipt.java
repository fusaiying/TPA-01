package com.paic.ehis.claimflow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 案件追讨收款明细对象 claim_case_debt_receipt
 * 
 * @author sino
 * @date 2021-01-20
 */
public class ClaimCaseDebtReceipt extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 收据号 */
    private String receiptNo;

    /** 被保人客户号 */
    @Excel(name = "被保人客户号")
    private String insuredNo;

    /** 收款金额 */
    @Excel(name = "收款金额")
    private BigDecimal receiptAmount;

    /** 收款币种 */
    @Excel(name = "收款币种")
    private String receiptCurrency;

    /** 收胯日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收胯日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptDate;

    /** 余额 */
    private BigDecimal residual;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setReceiptNo(String receiptNo) 
    {
        this.receiptNo = receiptNo;
    }

    public String getReceiptNo() 
    {
        return receiptNo;
    }
    public void setInsuredNo(String insuredNo) 
    {
        this.insuredNo = insuredNo;
    }

    public String getInsuredNo() 
    {
        return insuredNo;
    }
    public void setReceiptAmount(BigDecimal receiptAmount) 
    {
        this.receiptAmount = receiptAmount;
    }

    public BigDecimal getReceiptAmount() 
    {
        return receiptAmount;
    }
    public void setReceiptCurrency(String receiptCurrency) 
    {
        this.receiptCurrency = receiptCurrency;
    }

    public String getReceiptCurrency() 
    {
        return receiptCurrency;
    }
    public void setReceiptDate(Date receiptDate) 
    {
        this.receiptDate = receiptDate;
    }

    public Date getReceiptDate() 
    {
        return receiptDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public BigDecimal getResidual() {
        return residual;
    }

    public void setResidual(BigDecimal residual) {
        this.residual = residual;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("receiptNo", getReceiptNo())
            .append("insuredNo", getInsuredNo())
            .append("receiptAmount", getReceiptAmount())
            .append("receiptCurrency", getReceiptCurrency())
            .append("receiptDate", getReceiptDate())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
