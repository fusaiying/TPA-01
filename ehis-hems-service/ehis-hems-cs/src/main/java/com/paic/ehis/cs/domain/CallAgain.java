package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 再次来电信息 对象 call_again
 * 
 * @author sino
 * @date 2021-01-21
 */
@Data
public class CallAgain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 来电流水号 */
    private String callSerialNum;

    private String businessType;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    /** 受理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "受理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date acceptTime;
    /** 被保人 */
    @Excel(name = "被保人")
    private String insuredName;
    public void setCallSerialNum(String callSerialNum)
    {
        this.callSerialNum = callSerialNum;
    }

    public String getCallSerialNum()
    {
        return callSerialNum;
    }
    public void setWorkOrderNo(String workOrderNo)
    {
        this.workOrderNo = workOrderNo;
    }

    public String getWorkOrderNo()
    {
        return workOrderNo;
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
                .append("callSerialNum", getCallSerialNum())
                .append("workOrderNo", getWorkOrderNo())
                .append("remark", getRemark())
                .append("status", getStatus())
                .append("createdBy", getCreatedBy())
                .append("createdTime", getCreatedTime())
                .append("updatedBy", getUpdatedBy())
                .append("updatedTime", getUpdatedTime())
                .toString();
    }

}
