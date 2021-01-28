package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * HCS预约修改 对象 hcs_modification
 * 
 * @author sino
 * @date 2021-01-21
 */
public class HcsModification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单号 */
    private String workOrderNo;

    /** 修改序列号 */
    private String alterId;

    /** 修改人 */
    @Excel(name = "修改人")
    private String alterBy;

    /** 修改人UM编号 */
    @Excel(name = "修改人UM编号")
    private String umCode;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date alterTime;

    /** 修改渠道 */
    @Excel(name = "修改渠道")
    private String alterChannel;

    /** 修改内容描述 */
    @Excel(name = "修改内容描述")
    private String alterContent;

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

    public void setWorkOrderNo(String workOrderNo) 
    {
        this.workOrderNo = workOrderNo;
    }

    public String getWorkOrderNo() 
    {
        return workOrderNo;
    }
    public void setAlterId(String alterId) 
    {
        this.alterId = alterId;
    }

    public String getAlterId() 
    {
        return alterId;
    }
    public void setAlterBy(String alterBy) 
    {
        this.alterBy = alterBy;
    }

    public String getAlterBy() 
    {
        return alterBy;
    }
    public void setUmCode(String umCode) 
    {
        this.umCode = umCode;
    }

    public String getUmCode() 
    {
        return umCode;
    }
    public void setAlterTime(Date alterTime) 
    {
        this.alterTime = alterTime;
    }

    public Date getAlterTime() 
    {
        return alterTime;
    }
    public void setAlterChannel(String alterChannel) 
    {
        this.alterChannel = alterChannel;
    }

    public String getAlterChannel() 
    {
        return alterChannel;
    }
    public void setAlterContent(String alterContent) 
    {
        this.alterContent = alterContent;
    }

    public String getAlterContent() 
    {
        return alterContent;
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
            .append("workOrderNo", getWorkOrderNo())
            .append("alterId", getAlterId())
            .append("alterBy", getAlterBy())
            .append("umCode", getUmCode())
            .append("alterTime", getAlterTime())
            .append("alterChannel", getAlterChannel())
            .append("alterContent", getAlterContent())
            .append("status", getStatus())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
