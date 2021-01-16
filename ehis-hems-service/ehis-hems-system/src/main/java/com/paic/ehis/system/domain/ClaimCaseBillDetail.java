package com.paic.ehis.system.domain;

import java.math.BigDecimal;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 案件账单费用项明细对象 claim_case_bill_detail
 * 
 * @author sino
 * @date 2021-01-15
 */
public class ClaimCaseBillDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账单明细ID */
    private Long detailId;

    /** 账单ID */
    @Excel(name = "账单ID")
    private Long billId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 费用项账单金额 */
    @Excel(name = "费用项账单金额")
    private BigDecimal billDetailAmount;

    /** 自费金额 */
    @Excel(name = "自费金额")
    private BigDecimal selfAmount;

    /** 部分自费金额 */
    @Excel(name = "部分自费金额")
    private BigDecimal partSelfAmount;

    /** 费用项先期给付 */
    @Excel(name = "费用项先期给付")
    private BigDecimal advancePayment;

    /** 费用项自付额 */
    @Excel(name = "费用项自付额")
    private BigDecimal billDetailCopay;

    /** 费用项医院折扣金额 */
    @Excel(name = "费用项医院折扣金额")
    private BigDecimal hosDiscountAmount;

    /** 不合理金额 */
    @Excel(name = "不合理金额")
    private BigDecimal unableAmount;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }
    public void setBillId(Long billId) 
    {
        this.billId = billId;
    }

    public Long getBillId() 
    {
        return billId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setBillDetailAmount(BigDecimal billDetailAmount) 
    {
        this.billDetailAmount = billDetailAmount;
    }

    public BigDecimal getBillDetailAmount() 
    {
        return billDetailAmount;
    }
    public void setSelfAmount(BigDecimal selfAmount) 
    {
        this.selfAmount = selfAmount;
    }

    public BigDecimal getSelfAmount() 
    {
        return selfAmount;
    }
    public void setPartSelfAmount(BigDecimal partSelfAmount) 
    {
        this.partSelfAmount = partSelfAmount;
    }

    public BigDecimal getPartSelfAmount() 
    {
        return partSelfAmount;
    }
    public void setAdvancePayment(BigDecimal advancePayment) 
    {
        this.advancePayment = advancePayment;
    }

    public BigDecimal getAdvancePayment() 
    {
        return advancePayment;
    }
    public void setBillDetailCopay(BigDecimal billDetailCopay) 
    {
        this.billDetailCopay = billDetailCopay;
    }

    public BigDecimal getBillDetailCopay() 
    {
        return billDetailCopay;
    }
    public void setHosDiscountAmount(BigDecimal hosDiscountAmount) 
    {
        this.hosDiscountAmount = hosDiscountAmount;
    }

    public BigDecimal getHosDiscountAmount() 
    {
        return hosDiscountAmount;
    }
    public void setUnableAmount(BigDecimal unableAmount) 
    {
        this.unableAmount = unableAmount;
    }

    public BigDecimal getUnableAmount() 
    {
        return unableAmount;
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
            .append("detailId", getDetailId())
            .append("billId", getBillId())
            .append("rptNo", getRptNo())
            .append("billDetailAmount", getBillDetailAmount())
            .append("selfAmount", getSelfAmount())
            .append("partSelfAmount", getPartSelfAmount())
            .append("advancePayment", getAdvancePayment())
            .append("billDetailCopay", getBillDetailCopay())
            .append("hosDiscountAmount", getHosDiscountAmount())
            .append("unableAmount", getUnableAmount())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
