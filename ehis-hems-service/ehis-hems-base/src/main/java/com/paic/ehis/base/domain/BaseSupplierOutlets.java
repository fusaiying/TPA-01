package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * base_supplier_outlets（服务网点配置）对象 base_supplier_outlets
 * 
 * @author sino
 * @date 2020-12-31
 */

public class BaseSupplierOutlets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 供应商编码 */
    private String supplierCode;

    /** 服务机构编码 */
    private String websiteCode;


    /** 服务机构类型 */
    @Excel(name = "服务机构类型")
    private String websitecType;

    /** 服务机构中文名称 */
    @Excel(name = "服务机构中文名称")
    private String websitecName;


    /** 所属地区 */
    @Excel(name = "所属地区")
    private String addressdetail;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "Y=-有效，N-无效")
    private String status;

    public String getSerialNo() {
        return serialNo;
    }

    public String getWebsiteCode() {
        return websiteCode;
    }

    public String getWebsitecName() {
        return websitecName;
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo(String serialNo)
    {
        return this.serialNo;
    }
    public void setWebsiteCode(String websiteCode) 
    {
        this.websiteCode = websiteCode;
    }

    public String getWebsiteCode(String providerCode)
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

    public String getWebsitecName(String chname1)
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
                .append("serialNo", getSerialNo())
                .append("supplierCode", getSupplierCode())
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
