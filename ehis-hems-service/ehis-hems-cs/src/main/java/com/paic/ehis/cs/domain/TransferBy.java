package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 转办信息 对象 transfer_by
 * 
 * @author sino
 * @date 2021-02-07
 */
public class TransferBy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 转办流水号 */
    private Long transferId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 接收人编号 */
    @Excel(name = "接收人编号")
    private String toUserId;

    /** 接收方UM账号 */
    @Excel(name = "接收方UM账号")
    private String umCode;

    /** 转交处理原因 */
    @Excel(name = "转交处理原因")
    private String reason;

    /** 附件标志 */
    @Excel(name = "附件标志")
    private String attachmentFlag;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setTransferId(Long transferId) 
    {
        this.transferId = transferId;
    }

    public Long getTransferId() 
    {
        return transferId;
    }
    public void setWorkOrderNo(String workOrderNo) 
    {
        this.workOrderNo = workOrderNo;
    }

    public String getWorkOrderNo() 
    {
        return workOrderNo;
    }
    public void setToUserId(String toUserId) 
    {
        this.toUserId = toUserId;
    }

    public String getToUserId() 
    {
        return toUserId;
    }
    public void setUmCode(String umCode) 
    {
        this.umCode = umCode;
    }

    public String getUmCode() 
    {
        return umCode;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
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
            .append("transferId", getTransferId())
            .append("workOrderNo", getWorkOrderNo())
            .append("toUserId", getToUserId())
            .append("umCode", getUmCode())
            .append("reason", getReason())
            .append("attachmentFlag", getAttachmentFlag())
            .append("status", getStatus())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
