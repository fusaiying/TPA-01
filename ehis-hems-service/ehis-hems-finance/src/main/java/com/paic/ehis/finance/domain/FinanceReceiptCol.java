package com.paic.ehis.finance.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收款核销对象 finance_receipt_col
 * 
 * @author sino
 * @date 2021-01-30
 */
public class FinanceReceiptCol extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 核销ID */
    private Long colId;

    /** 收据号 */
    @Excel(name = "收据号")
    private String receiptNo;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNo;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

    public void setColId(Long colId) 
    {
        this.colId = colId;
    }

    public Long getColId() 
    {
        return colId;
    }
    public void setReceiptNo(String receiptNo) 
    {
        this.receiptNo = receiptNo;
    }

    public String getReceiptNo() 
    {
        return receiptNo;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("colId", getColId())
            .append("receiptNo", getReceiptNo())
            .append("taskNo", getTaskNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode", getDeptCode())
            .toString();
    }
}
