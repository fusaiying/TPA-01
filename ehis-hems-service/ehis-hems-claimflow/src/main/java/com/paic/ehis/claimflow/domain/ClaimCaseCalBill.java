package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件赔付账单明细对象 claim_case_cal_bill
 * 
 * @author sino
 * @date 2021-01-20
 */
public class ClaimCaseCalBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 赔付ID */
    private Long calBillId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 账单ID */
    @Excel(name = "账单ID")
    private Long billId;

    /** 理算金额（初始） */
    @Excel(name = "理算金额")
    private BigDecimal calAmount;

    /** 赔付金额（最终） */
    @Excel(name = "理算金额")
    private BigDecimal payAmount;

    /** 拒赔金额 */
    @Excel(name = "拒赔金额")
    private BigDecimal refusedAmount;

    /** 本次使用免赔额 */
    @Excel(name = "本次使用免赔额")
    private BigDecimal deduUsed;

    /** 赔付结论 */
    @Excel(name = "本次使用免赔额")
    private String payConclusion;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setCalBillId(Long calBillId) 
    {
        this.calBillId = calBillId;
    }

    public Long getCalBillId() 
    {
        return calBillId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setBillId(Long billId) 
    {
        this.billId = billId;
    }

    public Long getBillId() 
    {
        return billId;
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
    public void setDeduUsed(BigDecimal deduUsed) 
    {
        this.deduUsed = deduUsed;
    }

    public BigDecimal getDeduUsed() 
    {
        return deduUsed;
    }
    public void setPayConclusion(String payConclusion) 
    {
        this.payConclusion = payConclusion;
    }

    public String getPayConclusion() 
    {
        return payConclusion;
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
            .append("calBillId", getCalBillId())
            .append("rptNo", getRptNo())
            .append("billId", getBillId())
            .append("calAmount", getCalAmount())
            .append("payAmount", getPayAmount())
            .append("refusedAmount", getRefusedAmount())
            .append("deduUsed", getDeduUsed())
            .append("payConclusion", getPayConclusion())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
