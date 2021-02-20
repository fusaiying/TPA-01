package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 修改信息 对象 edit_info
 * 
 * @author sino
 * @date 2021-02-05
 */
public class EditInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编辑流水号 */
    private String editId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderId;

    /** 其他业务流水编号 */
    @Excel(name = "其他业务流水编号")
    private String otherId;

    /** 其他业务类型 */
    @Excel(name = "其他业务类型")
    private String otherType;

    /** 修改原因 */
    @Excel(name = "修改原因")
    private String editReason;

    /** 修改说明 */
    @Excel(name = "修改说明")
    private String editRemark;

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

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    public void setWorkOrderId(String workOrderId)
    {
        this.workOrderId = workOrderId;
    }

    public String getWorkOrderId() 
    {
        return workOrderId;
    }
    public void setOtherId(String otherId) 
    {
        this.otherId = otherId;
    }

    public String getOtherId() 
    {
        return otherId;
    }
    public void setOtherType(String otherType) 
    {
        this.otherType = otherType;
    }

    public String getOtherType() 
    {
        return otherType;
    }
    public void setEditReason(String editReason) 
    {
        this.editReason = editReason;
    }

    public String getEditReason() 
    {
        return editReason;
    }
    public void setEditRemark(String editRemark) 
    {
        this.editRemark = editRemark;
    }

    public String getEditRemark() 
    {
        return editRemark;
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
            .append("editId", getEditId())
            .append("workOrderId", getWorkOrderId())
            .append("otherId", getOtherId())
            .append("otherType", getOtherType())
            .append("editReason", getEditReason())
            .append("editRemark", getEditRemark())
            .append("status", getStatus())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
