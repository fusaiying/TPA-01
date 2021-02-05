package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 案件归档对象 claim_case_filing
 * 
 * @author sino
 * @date 2021-01-20
 */
public class ClaimCaseFiling extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 盒号 */
    @Excel(name = "盒号")
    private String caseBoxNo;

    /** 批次号 */
    private String batchNo;

    /** 报案号起 */
    @Excel(name = "报案号起")
    private String rptStartNo;

    /** 报案号止 */
    @Excel(name = "报案号止")
    private String rptEndNo;

    /** 理赔类型 */
    @Excel(name = "理赔类型")
    private String claimType;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /**
     * 登录机构编码
     */
    @Excel(name = "登录机构编码")
    private String deptCode;

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
    public void setRptStartNo(String rptStartNo) 
    {
        this.rptStartNo = rptStartNo;
    }

    public String getRptStartNo() 
    {
        return rptStartNo;
    }
    public void setRptEndNo(String rptEndNo) 
    {
        this.rptEndNo = rptEndNo;
    }

    public String getRptEndNo() 
    {
        return rptEndNo;
    }
    public void setClaimType(String claimType) 
    {
        this.claimType = claimType;
    }

    public String getClaimType() 
    {
        return claimType;
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
            .append("caseBoxNo", getCaseBoxNo())
            .append("batchNo", getBatchNo())
            .append("rptStartNo", getRptStartNo())
            .append("rptEndNo", getRptEndNo())
            .append("claimType", getClaimType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("deptCode", getDeptCode())
            .toString();
    }
}
