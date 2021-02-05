package com.paic.ehis.finance.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 支付明细信息对象 finance_pay_detail_info
 * 
 * @author sino
 * @date 2021-01-30
 */
public class FinancePayDetailInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 支付明细ID */
    private Long payDetailId;

    /** 付款批次号 */
    @Excel(name = "付款批次号")
    private String payBatchNo;

    /** 案件批次号 */
    @Excel(name = "案件批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payAmount;

    /** 支付金额（外币） */
    @Excel(name = "支付金额", readConverterExp = "外=币")
    private BigDecimal payAmountForeign;

    /** 理算金额 */
    @Excel(name = "理算金额")
    private BigDecimal claimAmount;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

    public void setPayDetailId(Long payDetailId) 
    {
        this.payDetailId = payDetailId;
    }

    public Long getPayDetailId() 
    {
        return payDetailId;
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
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setPayAmount(BigDecimal payAmount) 
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() 
    {
        return payAmount;
    }
    public void setPayAmountForeign(BigDecimal payAmountForeign) 
    {
        this.payAmountForeign = payAmountForeign;
    }

    public BigDecimal getPayAmountForeign() 
    {
        return payAmountForeign;
    }
    public void setClaimAmount(BigDecimal claimAmount) 
    {
        this.claimAmount = claimAmount;
    }

    public BigDecimal getClaimAmount() 
    {
        return claimAmount;
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
            .append("payDetailId", getPayDetailId())
            .append("payBatchNo", getPayBatchNo())
            .append("batchNo", getBatchNo())
            .append("rptNo", getRptNo())
            .append("payAmount", getPayAmount())
            .append("payAmountForeign", getPayAmountForeign())
            .append("claimAmount", getClaimAmount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode", getDeptCode())
            .toString();
    }
}
