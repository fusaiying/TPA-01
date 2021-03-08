package com.paic.ehis.claimflow.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 案件归档明细对象 claim_case_filing_detail
 * 
 * @author sino
 * @date 2021-03-05
 */
public class ClaimCaseFilingDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 案件归档明细ID */
    private Long filingDetailId;

    /** 盒号 */
    @Excel(name = "盒号")
    private String caseBoxNo;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

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

    /**
     * 登录机构编码
     */
    private String deptCode;
    /**
     * 备注
     */
    private String remark;

    public void setFilingDetailId(Long filingDetailId) 
    {
        this.filingDetailId = filingDetailId;
    }

    public Long getFilingDetailId() 
    {
        return filingDetailId;
    }
    public void setCaseBoxNo(String caseBoxNo) 
    {
        this.caseBoxNo = caseBoxNo;
    }

    public String getCaseBoxNo() 
    {
        return caseBoxNo;
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

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("filingDetailId", getFilingDetailId())
            .append("caseBoxNo", getCaseBoxNo())
            .append("batchNo", getBatchNo())
            .append("rptNo", getRptNo())
            .append("isFiling", getIsFiling())
            .append("isInvoiceBack", getIsInvoiceBack())
            .append("isSingle", getIsSingle())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
