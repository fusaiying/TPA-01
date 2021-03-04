package com.paic.ehis.finance.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 代垫费结算明细对象 finance_advance_settle_detail
 * 
 * @author sino
 * @date 2021-01-30
 */
public class FinanceAdvanceSettleDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务明细ID */
    private Long detailId;

    /** 结算任务号 */
    @Excel(name = "结算任务号")
    private String settleTaskNo;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 代垫款金额 */
    @Excel(name = "代垫款金额")
    private BigDecimal advanceAmount;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
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
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setAdvanceAmount(BigDecimal advanceAmount) 
    {
        this.advanceAmount = advanceAmount;
    }

    public BigDecimal getAdvanceAmount() 
    {
        return advanceAmount;
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
            .append("detailId", getDetailId())
            .append("settleTaskNo", getSettleTaskNo())
            .append("companyCode", getCompanyCode())
            .append("rptNo", getRptNo())
            .append("batchNo", getBatchNo())
            .append("advanceAmount", getAdvanceAmount())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode", getDeptCode())
            .toString();
    }
}
