package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * base_service_applyorimpl（服务项目申请或实施信息显示配置）对象 base_service_applyorimpl
 * 
 * @author sino
 * @date 2020-12-31
 */
public class BaseServiceApplyorimpl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 服务项目编码 */
    private String serviceCode;

    /** 流程节点 */
    @Excel(name = "流程节点")
    private String processNode;

    /** 字段名称 */
    @Excel(name = "字段名称")
    private String businessName;

    /** 字段编码 */
    @Excel(name = "字段编码")
    private String businessCode;

    /** 是否必输 */
    @Excel(name = "是否必输")
    private String isrecond;

    /** 录入方式 */
    @Excel(name = "录入方式")
    private String recondType;

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
    public void setBusinessName(String businessName) 
    {
        this.businessName = businessName;
    }

    public String getBusinessName() 
    {
        return businessName;
    }
    public void setBusinessCode(String businessCode) 
    {
        this.businessCode = businessCode;
    }

    public String getBusinessCode() 
    {
        return businessCode;
    }
    public void setIsrecond(String isrecond) 
    {
        this.isrecond = isrecond;
    }

    public String getIsrecond() 
    {
        return isrecond;
    }
    public void setRecondType(String recondType) 
    {
        this.recondType = recondType;
    }

    public String getRecondType() 
    {
        return recondType;
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
            .append("businessName", getBusinessName())
            .append("businessCode", getBusinessCode())
            .append("isrecond", getIsrecond())
            .append("recondType", getRecondType())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
