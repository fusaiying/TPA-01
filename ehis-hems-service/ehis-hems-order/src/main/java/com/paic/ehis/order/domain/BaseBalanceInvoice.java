package com.paic.ehis.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * base_balance_invoice(服务结算发票信息)对象 base_balance_invoice
 * 
 * @author sino
 * @date 2021-01-27
 */
public class BaseBalanceInvoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long serialNo;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNo;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 发票类型 */
    @Excel(name = "发票类型")
    private String invoiceType;

    /** 发票代码 */
    @Excel(name = "发票代码")
    private String invoiceCode;

    /** 发票号码 */
    @Excel(name = "发票号码")
    private String invoiceNo;

    /** 发票金额 */
    @Excel(name = "发票金额")
    private BigDecimal amount;

    /** 发票金额（不含税） */
    @Excel(name = "发票金额", readConverterExp = "不含税")
    private BigDecimal amountTax;

    /** 发票税额 */
    @Excel(name = "发票税额")
    private BigDecimal tax;

    /** 认证结果 */
    @Excel(name = "认证结果")
    private String auth;

    /** 开票日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开票日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invoiceDate;

    public void setSerialNo(Long serialNo) 
    {
        this.serialNo = serialNo;
    }

    public Long getSerialNo() 
    {
        return serialNo;
    }
    public void setTaskNo(String taskNo) 
    {
        this.taskNo = taskNo;
    }

    public String getTaskNo() 
    {
        return taskNo;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setInvoiceType(String invoiceType) 
    {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceType() 
    {
        return invoiceType;
    }
    public void setInvoiceCode(String invoiceCode) 
    {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceCode() 
    {
        return invoiceCode;
    }
    public void setInvoiceNo(String invoiceNo) 
    {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceNo() 
    {
        return invoiceNo;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setAmountTax(BigDecimal amountTax) 
    {
        this.amountTax = amountTax;
    }

    public BigDecimal getAmountTax() 
    {
        return amountTax;
    }
    public void setTax(BigDecimal tax) 
    {
        this.tax = tax;
    }

    public BigDecimal getTax() 
    {
        return tax;
    }
    public void setAuth(String auth) 
    {
        this.auth = auth;
    }

    public String getAuth() 
    {
        return auth;
    }
    public void setInvoiceDate(Date invoiceDate) 
    {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() 
    {
        return invoiceDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serialNo", getSerialNo())
            .append("taskNo", getTaskNo())
            .append("status", getStatus())
            .append("invoiceType", getInvoiceType())
            .append("invoiceCode", getInvoiceCode())
            .append("invoiceNo", getInvoiceNo())
            .append("amount", getAmount())
            .append("amountTax", getAmountTax())
            .append("tax", getTax())
            .append("auth", getAuth())
            .append("invoiceDate", getInvoiceDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
