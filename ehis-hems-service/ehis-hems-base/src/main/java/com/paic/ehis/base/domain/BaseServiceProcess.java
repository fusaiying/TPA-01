package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * base_service_process（服务项目流程配置）对象 base_service_process
 * 
 * @author sino
 * @date 2020-12-31
 */
public class BaseServiceProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 服务项目编码 */
    private String serviceCode;

    /** 流程节点 */
    @Excel(name = "流程节点")
    private String processNode;

    /** 是否可取消 */
    @Excel(name = "是否可取消")
    private String cancelFlag;

    /** 是否发送消息 */
    @Excel(name = "是否发送消息")
    private String sendmessageFlag;

    /** 是否可变更 */
    @Excel(name = "是否可变更")
    private String updateFlag;

    /** 变更次数 */
    @Excel(name = "变更次数")
    private String updateNum;

    /** 是否自动分配 */
    @Excel(name = "是否自动分配")
    private String distribute;

    /** 时效 */
    @Excel(name = "时效")
    private String aging;

    /** 是否本人使用 */
    @Excel(name = "是否本人使用")
    private String selfused;

    /** 是否记录信息 */
    @Excel(name = "是否记录信息")
    private String recordFlag;

    /** 记录次数限制 */
    @Excel(name = "记录次数限制")
    private String frequency;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public void setServiceCode(String serviceCode)
    {
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() 
    {
        return serviceCode;
    }
    public void setProcessNode(String processNode) 
    {
        this.processNode = processNode;
    }

    public String getProcessNode() 
    {
        return processNode;
    }
    public void setCancelFlag(String cancelFlag) 
    {
        this.cancelFlag = cancelFlag;
    }

    public String getCancelFlag() 
    {
        return cancelFlag;
    }
    public void setSendmessageFlag(String sendmessageFlag) 
    {
        this.sendmessageFlag = sendmessageFlag;
    }

    public String getSendmessageFlag() 
    {
        return sendmessageFlag;
    }
    public void setUpdateFlag(String updateFlag) 
    {
        this.updateFlag = updateFlag;
    }

    public String getUpdateFlag() 
    {
        return updateFlag;
    }
    public void setUpdateNum(String updateNum) 
    {
        this.updateNum = updateNum;
    }

    public String getUpdateNum() 
    {
        return updateNum;
    }
    public void setDistribute(String distribute) 
    {
        this.distribute = distribute;
    }

    public String getDistribute() 
    {
        return distribute;
    }
    public void setAging(String aging) 
    {
        this.aging = aging;
    }

    public String getAging() 
    {
        return aging;
    }
    public void setSelfused(String selfused) 
    {
        this.selfused = selfused;
    }

    public String getSelfused() 
    {
        return selfused;
    }
    public void setRecordFlag(String recordFlag) 
    {
        this.recordFlag = recordFlag;
    }

    public String getRecordFlag() 
    {
        return recordFlag;
    }
    public void setFrequency(String frequency) 
    {
        this.frequency = frequency;
    }

    public String getFrequency() 
    {
        return frequency;
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
            .append("serviceCode", getServiceCode())
            .append("processNode", getProcessNode())
            .append("cancelFlag", getCancelFlag())
            .append("sendmessageFlag", getSendmessageFlag())
            .append("updateFlag", getUpdateFlag())
            .append("updateNum", getUpdateNum())
            .append("distribute", getDistribute())
            .append("aging", getAging())
            .append("selfused", getSelfused())
            .append("recordFlag", getRecordFlag())
            .append("frequency", getFrequency())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
