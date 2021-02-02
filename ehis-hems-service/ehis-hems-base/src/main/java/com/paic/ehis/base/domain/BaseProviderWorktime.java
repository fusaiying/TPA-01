package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * base_provider_worktime(服务商服务时间信息)对象 base_provider_worktime
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseProviderWorktime extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String SerialNo;
    private String endTime;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String providerCode;

    /** 时间节点 */
    @Excel(name = "时间节点")
    private String timeNode;

    /** 起始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String startTime;


    /** 节点信息 */
    @Excel(name = "节点信息")
    private String nodeRemark;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 状态 */
    @Excel(name = "变更标志")
    private String updateFlag;



    public void setProviderCode(String providerCode) 
    {
        this.providerCode = providerCode;
    }

    public String getProviderCode() 
    {
        return providerCode;
    }
    public void setTimeNode(String timeNode) 
    {
        this.timeNode = timeNode;
    }

    public String getTimeNode() 
    {
        return timeNode;
    }
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getStartTime()
    {
        return startTime;
    }
    public void setNodeRemark(String nodeRemark) 
    {
        this.nodeRemark = nodeRemark;
    }

    public String getNodeRemark() 
    {
        return nodeRemark;
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
            .append("providerCode", getProviderCode())
            .append("timeNode", getTimeNode())
            .append("startTime", getStartTime())
            .append("nodeRemark", getNodeRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
