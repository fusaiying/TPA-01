package com.paic.ehis.claimmgt.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 案件申诉任务对象 claim_case_appeal_task
 * 
 * @author sino
 * @date 2021-03-15
 */
public class ClaimCaseAppealTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申诉ID */
    private Long taskId;

    /** 被申诉报案号 */
    @Excel(name = "被申诉报案号")
    private String appealRptNo;

    /** 新报案号 */
    @Excel(name = "新报案号")
    private String newRptNo;

    /** 申诉状态 */
    @Excel(name = "申诉状态")
    private String appealStatus;

    /** 申诉类型 */
    @Excel(name = "申诉类型")
    private String appealType;

    /** 申诉原因 */
    @Excel(name = "申诉原因")
    private String appealReason;

    /** 申诉原因细类 */
    @Excel(name = "申诉原因细类")
    private String appealSubReason;

    /** 申诉说明 */
    @Excel(name = "申诉说明")
    private String appealRemark;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applyOperator;

    /** 审核人 */
    @Excel(name = "审核人")
    private String auditor;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

    /**处理意见*/
    private String conclusionRemark;

    private String isAgree;

    private String dealType;


    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setAppealRptNo(String appealRptNo) 
    {
        this.appealRptNo = appealRptNo;
    }

    public String getAppealRptNo() 
    {
        return appealRptNo;
    }
    public void setNewRptNo(String newRptNo) 
    {
        this.newRptNo = newRptNo;
    }

    public String getNewRptNo() 
    {
        return newRptNo;
    }
    public void setAppealStatus(String appealStatus) 
    {
        this.appealStatus = appealStatus;
    }

    public String getAppealStatus() 
    {
        return appealStatus;
    }
    public void setAppealType(String appealType) 
    {
        this.appealType = appealType;
    }

    public String getAppealType() 
    {
        return appealType;
    }
    public void setAppealReason(String appealReason) 
    {
        this.appealReason = appealReason;
    }

    public String getAppealReason() 
    {
        return appealReason;
    }
    public void setAppealSubReason(String appealSubReason) 
    {
        this.appealSubReason = appealSubReason;
    }

    public String getAppealSubReason() 
    {
        return appealSubReason;
    }
    public void setAppealRemark(String appealRemark) 
    {
        this.appealRemark = appealRemark;
    }

    public String getAppealRemark() 
    {
        return appealRemark;
    }
    public void setApplyOperator(String applyOperator) 
    {
        this.applyOperator = applyOperator;
    }

    public String getApplyOperator() 
    {
        return applyOperator;
    }
    public void setAuditor(String auditor) 
    {
        this.auditor = auditor;
    }

    public String getAuditor() 
    {
        return auditor;
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

    public String getConclusionRemark() {
        return conclusionRemark;
    }

    public void setConclusionRemark(String conclusionRemark) {
        this.conclusionRemark = conclusionRemark;
    }

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("appealRptNo", getAppealRptNo())
            .append("newRptNo", getNewRptNo())
            .append("appealStatus", getAppealStatus())
            .append("appealType", getAppealType())
            .append("appealReason", getAppealReason())
            .append("appealSubReason", getAppealSubReason())
            .append("appealRemark", getAppealRemark())
            .append("applyOperator", getApplyOperator())
            .append("auditor", getAuditor())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("deptCode", getDeptCode())
            .toString();
    }
}
