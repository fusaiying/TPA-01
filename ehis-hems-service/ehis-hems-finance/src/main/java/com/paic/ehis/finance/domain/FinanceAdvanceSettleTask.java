package com.paic.ehis.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代垫费结算任务对象 finance_advance_settle_task
 * 
 * @author sino
 * @date 2021-01-30
 */
public class FinanceAdvanceSettleTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 结算任务号 */
    @Excel(name = "结算任务号")
    private String settleTaskNo;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 结算总金额 */
    @Excel(name = "结算总金额")
    private BigDecimal advanceAmount;

    /** 结算止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleEndDate;

    /** 结算状态 (01-待结算、02-已结算) */
    @Excel(name = "结算状态 (01-待结算、02-已结算)")
    private String settleStatus;

    /** 核销日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "核销日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date colDate;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setSettleTaskNo(String settleTaskNo) 
    {
        this.settleTaskNo = settleTaskNo;
    }

    public String getSettleTaskNo() 
    {
        return settleTaskNo;
    }
    public void setCompanyCode(String companyCode) 
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() 
    {
        return companyCode;
    }
    public void setAdvanceAmount(BigDecimal advanceAmount) 
    {
        this.advanceAmount = advanceAmount;
    }

    public BigDecimal getAdvanceAmount() 
    {
        return advanceAmount;
    }
    public void setSettleEndDate(Date settleEndDate) 
    {
        this.settleEndDate = settleEndDate;
    }

    public Date getSettleEndDate() 
    {
        return settleEndDate;
    }
    public void setSettleStatus(String settleStatus) 
    {
        this.settleStatus = settleStatus;
    }

    public String getSettleStatus() 
    {
        return settleStatus;
    }
    public void setColDate(Date colDate) 
    {
        this.colDate = colDate;
    }

    public Date getColDate() 
    {
        return colDate;
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
            .append("taskId", getTaskId())
            .append("settleTaskNo", getSettleTaskNo())
            .append("companyCode", getCompanyCode())
            .append("advanceAmount", getAdvanceAmount())
            .append("settleEndDate", getSettleEndDate())
            .append("settleStatus", getSettleStatus())
            .append("colDate", getColDate())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode", getDeptCode())
            .toString();
    }
}
