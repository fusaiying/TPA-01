package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 业务流转信息 对象 flow_log
 * 
 * @author sino
 * @date 2021-01-29
 */
public class FlowLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String flowId;

    /** 业务号 */
    @Excel(name = "业务号")
    private String workOrderNo;

    /** 子业务号 */
    @Excel(name = "子业务号")
    private String subId;

    /** 子业务类型 */
    @Excel(name = "子业务类型")
    private String subType;

    /** 状态 */
    @Excel(name = "状态")
    private String linkCode;

    /** 操作 */
    @Excel(name = "操作")
    private String operateCode;

    /** 处理人 */
    @Excel(name = "处理人")
    private String makeBy;

    /** UM账号 */
    @Excel(name = "UM账号")
    private String umNum;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date makeTime;

    /** 说明 */
    @Excel(name = "说明")
    private String remarks;

    /** 处理意见 */
    @Excel(name = "处理意见")
    private String opinion;

    /** 流转部门 针对当前操作有转办或协办的操作 */
    @Excel(name = "流转部门 针对当前操作有转办或协办的操作")
    private String toDepartment;

    /** 流转原因 */
    @Excel(name = "流转原因")
    private String toReason;

    /** 是否有附件 */
    @Excel(name = "是否有附件")
    private String attachmentFlag;

    /** 备用状态 */
    @Excel(name = "备用状态")
    private String status;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    public void setFlowId(String flowId) 
    {
        this.flowId = flowId;
    }

    public String getFlowId() 
    {
        return flowId;
    }
    public void setWorkOrderNo(String workOrderNo) 
    {
        this.workOrderNo = workOrderNo;
    }

    public String getWorkOrderNo() 
    {
        return workOrderNo;
    }
    public void setSubId(String subId) 
    {
        this.subId = subId;
    }

    public String getSubId() 
    {
        return subId;
    }
    public void setSubType(String subType) 
    {
        this.subType = subType;
    }

    public String getSubType() 
    {
        return subType;
    }
    public void setLinkCode(String linkCode) 
    {
        this.linkCode = linkCode;
    }

    public String getLinkCode() 
    {
        return linkCode;
    }
    public void setOperateCode(String operateCode) 
    {
        this.operateCode = operateCode;
    }

    public String getOperateCode() 
    {
        return operateCode;
    }
    public void setMakeBy(String makeBy) 
    {
        this.makeBy = makeBy;
    }

    public String getMakeBy() 
    {
        return makeBy;
    }
    public void setUmNum(String umNum) 
    {
        this.umNum = umNum;
    }

    public String getUmNum() 
    {
        return umNum;
    }
    public void setMakeTime(Date makeTime) 
    {
        this.makeTime = makeTime;
    }

    public Date getMakeTime() 
    {
        return makeTime;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setOpinion(String opinion) 
    {
        this.opinion = opinion;
    }

    public String getOpinion() 
    {
        return opinion;
    }
    public void setToDepartment(String toDepartment) 
    {
        this.toDepartment = toDepartment;
    }

    public String getToDepartment() 
    {
        return toDepartment;
    }
    public void setToReason(String toReason) 
    {
        this.toReason = toReason;
    }

    public String getToReason() 
    {
        return toReason;
    }
    public void setAttachmentFlag(String attachmentFlag) 
    {
        this.attachmentFlag = attachmentFlag;
    }

    public String getAttachmentFlag() 
    {
        return attachmentFlag;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("flowId", getFlowId())
            .append("workOrderNo", getWorkOrderNo())
            .append("subId", getSubId())
            .append("subType", getSubType())
            .append("linkCode", getLinkCode())
            .append("operateCode", getOperateCode())
            .append("makeBy", getMakeBy())
            .append("umNum", getUmNum())
            .append("makeTime", getMakeTime())
            .append("remarks", getRemarks())
            .append("opinion", getOpinion())
            .append("toDepartment", getToDepartment())
            .append("toReason", getToReason())
            .append("attachmentFlag", getAttachmentFlag())
            .append("status", getStatus())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
