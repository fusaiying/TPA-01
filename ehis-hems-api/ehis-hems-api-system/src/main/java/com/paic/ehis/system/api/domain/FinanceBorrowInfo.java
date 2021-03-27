package com.paic.ehis.system.api.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件借款明细对象 finance_borrow_info
 * 
 * @author sino
 * @date 2021-02-03
 */
public class FinanceBorrowInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借款ID */
    private Long borrowId;

    /** 付款批次号 */
    @Excel(name = "付款批次号")
    private String payBatchNo;

    /** 案件批次号 */
    @Excel(name = "案件批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 借款金额 */
    @Excel(name = "借款金额")
    private BigDecimal borrowAmount;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

    public void setBorrowId(Long borrowId) 
    {
        this.borrowId = borrowId;
    }

    public Long getBorrowId() 
    {
        return borrowId;
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
    public void setBorrowAmount(BigDecimal borrowAmount) 
    {
        this.borrowAmount = borrowAmount;
    }

    public BigDecimal getBorrowAmount() 
    {
        return borrowAmount;
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
            .append("borrowId", getBorrowId())
            .append("payBatchNo", getPayBatchNo())
            .append("batchNo", getBatchNo())
            .append("rptNo", getRptNo())
            .append("borrowAmount", getBorrowAmount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode", getDeptCode())
            .toString();
    }
}
