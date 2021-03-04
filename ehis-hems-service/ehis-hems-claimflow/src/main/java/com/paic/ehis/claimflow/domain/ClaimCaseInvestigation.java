package com.paic.ehis.claimflow.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 案件调查任务对象 claim_case_investigation
 * 
 * @author sino
 * @date 2021-01-15
 */
public class ClaimCaseInvestigation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 调查任务号 */
    private String invNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 提调类型 */
    @Excel(name = "提调类型")
    private String invType;

    /** 提调原因 */
    @Excel(name = "提调原因")
    private String invReason;

    /** 调查机构编码 */
    @Excel(name = "调查机构编码")
    private String invOrganCode;

    /** 提调机构编码 */
    @Excel(name = "提调机构编码")
    private String organCode;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 提调事项 */
    @Excel(name = "提调事项")
    private String invView;

    /** 提调时间 */
    @Excel(name = "提调时间")
    private Date invDate ;

    /** 是否历史任务 Y-是、N-否 */
    @Excel(name = "是否历史任务 Y-是、N-否")
    private String isHistory;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public String getInvNo() {
        return invNo;
    }

    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    public void setRptNo(String rptNo)
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setInvType(String invType) 
    {
        this.invType = invType;
    }

    public String getInvType() 
    {
        return invType;
    }
    public void setInvReason(String invReason) 
    {
        this.invReason = invReason;
    }

    public String getInvReason() 
    {
        return invReason;
    }
    public void setInvOrganCode(String invOrganCode) 
    {
        this.invOrganCode = invOrganCode;
    }

    public String getInvOrganCode() 
    {
        return invOrganCode;
    }
    public void setOrganCode(String organCode) 
    {
        this.organCode = organCode;
    }

    public String getOrganCode() 
    {
        return organCode;
    }
    public void setPolicyNo(String policyNo) 
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() 
    {
        return policyNo;
    }
    public void setInvView(String invView) 
    {
        this.invView = invView;
    }

    public String getInvView() 
    {
        return invView;
    }
    public void setIsHistory(String isHistory) 
    {
        this.isHistory = isHistory;
    }

    public String getIsHistory() 
    {
        return isHistory;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("invNo", getInvNo())
            .append("rptNo", getRptNo())
            .append("invType", getInvType())
            .append("invReason", getInvReason())
            .append("invOrganCode", getInvOrganCode())
            .append("organCode", getOrganCode())
            .append("policyNo", getPolicyNo())
            .append("invView", getInvView())
            .append("isHistory", getIsHistory())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
