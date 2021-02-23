package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 协办信息 对象 collaborative_from
 * 
 * @author sino
 * @date 2021-02-05
 */
@Data
public class CollaborativeFrom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 协办流水号 */
    private Long collaborativeId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private String fromUserId;

    /** 协办方UM账号 */
    @Excel(name = "协办方UM账号")
    private String umCode;

    /** 征求处理意见 */
    @Excel(name = "征求处理意见")
    private String solicitOpinion;

    /** 有无附件 */
    @Excel(name = "有无附件")
    private String attachmentFlag;

    @Excel(name = "处理状态")
    private String handleState;

    /** 状态 协办中；已经协办； */
    @Excel(name = "状态 协办中；已经协办；")
    private String status;

    @Excel(name="处理意见")
    private String opinion;

    @Excel(name="投诉是否成立")
    private String validFlag;

    @Excel(name="投诉不成立理由")
    private String nonReason;


    /** 处理方案 */
    @Excel(name = "处理方案")
    private String treatmentPlan;

    /** 处理依据 */
    @Excel(name = "处理依据")
    private String treatmentBasis;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    private String itemCode;
    private String organCode;
    private String businessType;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setCollaborativeId(Long collaborativeId) 
    {
        this.collaborativeId = collaborativeId;
    }

    public Long getCollaborativeId() 
    {
        return collaborativeId;
    }
    public void setWorkOrderNo(String workOrderNo) 
    {
        this.workOrderNo = workOrderNo;
    }

    public String getWorkOrderNo() 
    {
        return workOrderNo;
    }
    public void setFromUserId(String fromUserId) 
    {
        this.fromUserId = fromUserId;
    }

    public String getFromUserId() 
    {
        return fromUserId;
    }
    public void setUmCode(String umCode) 
    {
        this.umCode = umCode;
    }

    public String getUmCode() 
    {
        return umCode;
    }
    public void setSolicitOpinion(String solicitOpinion) 
    {
        this.solicitOpinion = solicitOpinion;
    }

    public String getSolicitOpinion() 
    {
        return solicitOpinion;
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
    public void setTreatmentPlan(String treatmentPlan) 
    {
        this.treatmentPlan = treatmentPlan;
    }

    public String getTreatmentPlan() 
    {
        return treatmentPlan;
    }
    public void setTreatmentBasis(String treatmentBasis) 
    {
        this.treatmentBasis = treatmentBasis;
    }

    public String getTreatmentBasis() 
    {
        return treatmentBasis;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("collaborativeId", getCollaborativeId())
            .append("workOrderNo", getWorkOrderNo())
            .append("fromUserId", getFromUserId())
            .append("umCode", getUmCode())
            .append("solicitOpinion", getSolicitOpinion())
            .append("attachmentFlag", getAttachmentFlag())
            .append("status", getStatus())
            .append("treatmentPlan", getTreatmentPlan())
            .append("treatmentBasis", getTreatmentBasis())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
