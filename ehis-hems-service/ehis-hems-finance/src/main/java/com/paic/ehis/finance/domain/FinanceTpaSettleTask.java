package com.paic.ehis.finance.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TPA服务费结算任务对象 finance_tpa_settle_task
 * 
 * @author sino
 * @date 2021-01-30
 */
public class FinanceTpaSettleTask extends BaseEntity
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

    /** 出单公司 */
    private String companyName;

    /** 结算类型 01-服务费 02-保费比例% */
    @Excel(name = "结算类型 01-服务费 02-保费比例%")
    private String settlementType;

    /** 服务费结算总金额 */
    @Excel(name = "服务费结算总金额")
    private BigDecimal serviceSettleAmount;

    /** 结算起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleStartDate;

    /** 结算止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleEndDate;

    /** 结算状态 (01-待确认、02-待结算、03-已结算) */
    @Excel(name = "结算状态 (01-待确认、02-待结算、03-已结算)")
    private String settleStatus;

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
    public void setSettlementType(String settlementType) 
    {
        this.settlementType = settlementType;
    }

    public String getSettlementType() 
    {
        return settlementType;
    }
    public void setServiceSettleAmount(BigDecimal serviceSettleAmount) 
    {
        this.serviceSettleAmount = serviceSettleAmount;
    }

    public BigDecimal getServiceSettleAmount() 
    {
        return serviceSettleAmount;
    }
    public void setSettleEndDate(Date settleEndDate) 
    {
        this.settleEndDate = settleEndDate;
    }

    public Date getSettleEndDate() 
    {
        return settleEndDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getSettleStartDate() {
        return settleStartDate;
    }

    public void setSettleStartDate(Date settleStartDate) {
        this.settleStartDate = settleStartDate;
    }

    public void setSettleStatus(String settleStatus)
    {
        this.settleStatus = settleStatus;
    }

    public String getSettleStatus() 
    {
        return settleStatus;
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
            .append("taskId", getTaskId())
            .append("settleTaskNo", getSettleTaskNo())
            .append("companyCode", getCompanyCode())
            .append("settlementType", getSettlementType())
            .append("serviceSettleAmount", getServiceSettleAmount())
            .append("settleEndDate", getSettleEndDate())
            .append("settleStartDate",getSettleStartDate())
            .append("settleStatus", getSettleStatus())
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
