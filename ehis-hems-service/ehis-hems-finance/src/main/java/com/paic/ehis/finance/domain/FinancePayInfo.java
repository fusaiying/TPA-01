package com.paic.ehis.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付信息对象 finance_pay_info
 * 
 * @author sino
 * @date 2021-02-01
 */
public class FinancePayInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 支付ID */
    private Long payId;

    /** 付款批次号 */
    @Excel(name = "付款批次号")
    private String payBatchNo;

    /** 案件批次号 */
    @Excel(name = "案件批次号")
    private String batchNo;

    /** 支付币种 */
    @Excel(name = "支付币种")
    private String payCurrency;

    /** 支付总金额 */
    @Excel(name = "支付总金额")
    private BigDecimal sumPayAmount;

    /** 支付总金额（外币） */
    @Excel(name = "支付总金额", readConverterExp = "外=币")
    private BigDecimal sumPayAmountForeign;

    /** 理算总金额 */
    @Excel(name = "理算总金额")
    private BigDecimal sumClaimAmount;

    /** 开户行编码 */
    @Excel(name = "开户行编码")
    private String payeeBank;

    /** 账号 */
    @Excel(name = "账号")
    private String accNo;

    /** 账户名 */
    @Excel(name = "账户名")
    private String accName;

    /** 支付状态 (01-待支付、02-支付中、03-支付成功、04-支付失败、05-退票) */
    @Excel(name = "支付状态 (01-待支付、02-支付中、03-支付成功、04-支付失败、05-退票)")
    private String payStatus;

    /** 支付日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /** 转账失败日期（退票） */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转账失败日期", readConverterExp = "退=票")
    private Date payFailDate;

    /** 转账失败原因 */
    @Excel(name = "转账失败原因")
    private String payFailReason;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

    public void setPayId(Long payId) 
    {
        this.payId = payId;
    }

    public Long getPayId() 
    {
        return payId;
    }
    public void setPayBatchNo(String payBatchNo) 
    {
        this.payBatchNo = payBatchNo;
    }

    public String getPayBatchNo() 
    {
        return payBatchNo;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setPayCurrency(String payCurrency) 
    {
        this.payCurrency = payCurrency;
    }

    public String getPayCurrency() 
    {
        return payCurrency;
    }
    public void setSumPayAmount(BigDecimal sumPayAmount) 
    {
        this.sumPayAmount = sumPayAmount;
    }

    public BigDecimal getSumPayAmount() 
    {
        return sumPayAmount;
    }
    public void setSumPayAmountForeign(BigDecimal sumPayAmountForeign) 
    {
        this.sumPayAmountForeign = sumPayAmountForeign;
    }

    public BigDecimal getSumPayAmountForeign() 
    {
        return sumPayAmountForeign;
    }
    public void setSumClaimAmount(BigDecimal sumClaimAmount) 
    {
        this.sumClaimAmount = sumClaimAmount;
    }

    public BigDecimal getSumClaimAmount() 
    {
        return sumClaimAmount;
    }
    public void setPayeeBank(String payeeBank) 
    {
        this.payeeBank = payeeBank;
    }

    public String getPayeeBank() 
    {
        return payeeBank;
    }
    public void setAccNo(String accNo) 
    {
        this.accNo = accNo;
    }

    public String getAccNo() 
    {
        return accNo;
    }
    public void setAccName(String accName) 
    {
        this.accName = accName;
    }

    public String getAccName() 
    {
        return accName;
    }
    public void setPayStatus(String payStatus) 
    {
        this.payStatus = payStatus;
    }

    public String getPayStatus() 
    {
        return payStatus;
    }
    public void setPayDate(Date payDate) 
    {
        this.payDate = payDate;
    }

    public Date getPayDate() 
    {
        return payDate;
    }
    public void setPayFailDate(Date payFailDate) 
    {
        this.payFailDate = payFailDate;
    }

    public Date getPayFailDate() 
    {
        return payFailDate;
    }
    public void setPayFailReason(String payFailReason) 
    {
        this.payFailReason = payFailReason;
    }

    public String getPayFailReason() 
    {
        return payFailReason;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDeptCode(String deptCode) 
    {
        this.deptCode = deptCode;
    }

    public String getDeptCode() 
    {
        return deptCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("payId", getPayId())
            .append("payBatchNo", getPayBatchNo())
            .append("batchNo", getBatchNo())
            .append("payCurrency", getPayCurrency())
            .append("sumPayAmount", getSumPayAmount())
            .append("sumPayAmountForeign", getSumPayAmountForeign())
            .append("sumClaimAmount", getSumClaimAmount())
            .append("payeeBank", getPayeeBank())
            .append("accNo", getAccNo())
            .append("accName", getAccName())
            .append("payStatus", getPayStatus())
            .append("payDate", getPayDate())
            .append("payFailDate", getPayFailDate())
            .append("payFailReason", getPayFailReason())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode", getDeptCode())
            .toString();
    }
}
