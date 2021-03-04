package com.paic.ehis.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实收信息明细对象 finance_collection_info
 * 
 * @author sino
 * @date 2021-01-30
 */
public class FinanceCollectionInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 实收ID */
    private Long collectionId;

    /** 收据号 */
    @Excel(name = "收据号")
    private String receiptNo;

    /** 收款账户名 */
    @Excel(name = "收款账户名")
    private String receiptAccName;

    /** 收款公司名称 */
    @Excel(name = "收款公司名称")
    private String receiptCompanyName;

    /** 收款金额 */
    @Excel(name = "收款金额")
    private BigDecimal receiptAmount;

    /** 收款日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptDate;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setCollectionId(Long collectionId) 
    {
        this.collectionId = collectionId;
    }

    public Long getCollectionId() 
    {
        return collectionId;
    }
    public void setReceiptNo(String receiptNo) 
    {
        this.receiptNo = receiptNo;
    }

    public String getReceiptNo() 
    {
        return receiptNo;
    }
    public void setReceiptAccName(String receiptAccName) 
    {
        this.receiptAccName = receiptAccName;
    }

    public String getReceiptAccName() 
    {
        return receiptAccName;
    }
    public void setReceiptCompanyName(String receiptCompanyName) 
    {
        this.receiptCompanyName = receiptCompanyName;
    }

    public String getReceiptCompanyName() 
    {
        return receiptCompanyName;
    }
    public void setReceiptAmount(BigDecimal receiptAmount) 
    {
        this.receiptAmount = receiptAmount;
    }

    public BigDecimal getReceiptAmount() 
    {
        return receiptAmount;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("collectionId", getCollectionId())
            .append("receiptNo", getReceiptNo())
            .append("receiptAccName", getReceiptAccName())
            .append("receiptCompanyName", getReceiptCompanyName())
            .append("receiptAmount", getReceiptAmount())
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
