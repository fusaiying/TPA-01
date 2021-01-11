package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_supplier_outlets（服务网点配置）对象 base_supplier_outlets
 * 
 * @author sino
 * @date 2020-12-31
 */
public class BaseSupplierOutlets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商编码 */
    private String servcomNo;

    /** 服务机构编码 */
    private String websiteCode;

    /** 服务机构类型 */
    @Excel(name = "服务机构类型")
    private String websitecType;

    /** 服务机构中文名称 */
    @Excel(name = "服务机构中文名称")
    private String websitecName;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setServcomNo(String servcomNo) 
    {
        this.servcomNo = servcomNo;
    }

    public String getServcomNo() 
    {
        return servcomNo;
    }
    public void setWebsiteCode(String websiteCode) 
    {
        this.websiteCode = websiteCode;
    }

    public String getWebsiteCode() 
    {
        return websiteCode;
    }
    public void setWebsitecType(String websitecType) 
    {
        this.websitecType = websitecType;
    }

    public String getWebsitecType() 
    {
        return websitecType;
    }
    public void setWebsitecName(String websitecName) 
    {
        this.websitecName = websitecName;
    }

    public String getWebsitecName() 
    {
        return websitecName;
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
            .append("servcomNo", getServcomNo())
            .append("websiteCode", getWebsiteCode())
            .append("websitecType", getWebsitecType())
            .append("websitecName", getWebsitecName())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
