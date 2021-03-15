package com.paic.ehis.claimmgt.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 案件申诉任务记录对象 claim_case_appeal_record
 * 
 * @author sino
 * @date 2021-03-15
 */
public class ClaimCaseAppealRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流程记录ID */
    private Long recordId;

    /** 申诉ID */
    @Excel(name = "申诉ID")
    private Long taskId;

    /** 流程节点 */
    @Excel(name = "流程节点")
    private String operation;

    /** 流程节点操作人 */
    @Excel(name = "流程节点操作人")
    private String operator;

    /** 是否历史节点（Y-是，N-否） */
    @Excel(name = "是否历史节点", readConverterExp = "Y=-是，N-否")
    private String historyFlag;

    /** 上一环节流程记录ID */
    @Excel(name = "上一环节流程记录ID")
    private Long orgRecordId;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setOperation(String operation) 
    {
        this.operation = operation;
    }

    public String getOperation() 
    {
        return operation;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setHistoryFlag(String historyFlag) 
    {
        this.historyFlag = historyFlag;
    }

    public String getHistoryFlag() 
    {
        return historyFlag;
    }
    public void setOrgRecordId(Long orgRecordId) 
    {
        this.orgRecordId = orgRecordId;
    }

    public Long getOrgRecordId() 
    {
        return orgRecordId;
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
            .append("recordId", getRecordId())
            .append("taskId", getTaskId())
            .append("operation", getOperation())
            .append("operator", getOperator())
            .append("historyFlag", getHistoryFlag())
            .append("orgRecordId", getOrgRecordId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
