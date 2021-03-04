package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * base_provider_newtworktype_risklog(医疗网络类型险种记录)对象 base_provider_newtworktype_risklog
 * 
 * @author sino
 * @date 2021-01-04
 */
public class BaseProviderNewtworktypeRisklog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serNo;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String supplierCode;

    /** 险种 */
    @Excel(name = "险种")
    private String networktypeCode;

    /** 当前选项 */
    @Excel(name = "当前选项")
    private String oldChoose;

    /** 新选项生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "新选项生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date newDate;

    /** 失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date failureTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setSerNo(String serNo) 
    {
        this.serNo = serNo;
    }

    public String getSerNo() 
    {
        return serNo;
    }
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setNetworktypeCode(String networktypeCode) 
    {
        this.networktypeCode = networktypeCode;
    }

    public String getNetworktypeCode() 
    {
        return networktypeCode;
    }
    public void setOldChoose(String oldChoose) 
    {
        this.oldChoose = oldChoose;
    }

    public String getOldChoose() 
    {
        return oldChoose;
    }
    public void setNewDate(Date newDate) 
    {
        this.newDate = newDate;
    }

    public Date getNewDate() 
    {
        return newDate;
    }
    public void setFailureTime(Date failureTime) 
    {
        this.failureTime = failureTime;
    }

    public Date getFailureTime() 
    {
        return failureTime;
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
            .append("serNo", getSerNo())
            .append("supplierCode", getSupplierCode())
            .append("networktypeCode", getNetworktypeCode())
            .append("oldChoose", getOldChoose())
            .append("newDate", getNewDate())
            .append("failureTime", getFailureTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
