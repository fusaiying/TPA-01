package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 发票归档对象 claim_batch_invoice_filing
 * 
 * @author sino
 * @date 2021-01-20
 */
public class ClaimBatchInvoiceFiling extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 批次号 */
    private String batchNo;

    /** 发票盒号 */
    @Excel(name = "发票盒号")
    private String invoiceBoxNo;

    /** 发票是否归档 */
    @Excel(name = "发票是否归档")
    private String isFiling;

    /** 发票是否退回 */
    @Excel(name = "发票是否退回")
    private String isInvoiceBack;

    /** 批次是否单张发票 */
    @Excel(name = "批次是否单张发票")
    private String isSingle;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setInvoiceBoxNo(String invoiceBoxNo) 
    {
        this.invoiceBoxNo = invoiceBoxNo;
    }

    public String getInvoiceBoxNo() 
    {
        return invoiceBoxNo;
    }
    public void setIsFiling(String isFiling) 
    {
        this.isFiling = isFiling;
    }

    public String getIsFiling() 
    {
        return isFiling;
    }
    public void setIsInvoiceBack(String isInvoiceBack) 
    {
        this.isInvoiceBack = isInvoiceBack;
    }

    public String getIsInvoiceBack() 
    {
        return isInvoiceBack;
    }
    public void setIsSingle(String isSingle) 
    {
        this.isSingle = isSingle;
    }

    public String getIsSingle() 
    {
        return isSingle;
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
            .append("batchNo", getBatchNo())
            .append("invoiceBoxNo", getInvoiceBoxNo())
            .append("isFiling", getIsFiling())
            .append("isInvoiceBack", getIsInvoiceBack())
            .append("isSingle", getIsSingle())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
