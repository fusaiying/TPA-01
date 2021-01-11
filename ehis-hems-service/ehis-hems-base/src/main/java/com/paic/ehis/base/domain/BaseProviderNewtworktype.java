package com.paic.ehis.base.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_provider_newtworktypet(医疗网络类型)对象 base_provider_newtworktype
 * 
 * @author sino
 * @date 2021-01-04
 */
@Data
public class BaseProviderNewtworktype extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serNo;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String supplierCode;

    /** 网络类型编码 */
    @Excel(name = "网络类型编码")
    private String networktypeCode;

    /** 产品代码 */
    @Excel(name = "产品代码")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 当前选项 */
    @Excel(name = "当前选项")
    private String oldChoose;

    /** 新选项 */
    @Excel(name = "新选项")
    private String newChoose;

    /** 新选项生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "新选项生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date newDate;

    @Excel(name = "网络类型名称")
    private String networktypeName;


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
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setOldChoose(String oldChoose) 
    {
        this.oldChoose = oldChoose;
    }

    public String getOldChoose() 
    {
        return oldChoose;
    }
    public void setNewChoose(String newChoose) 
    {
        this.newChoose = newChoose;
    }

    public String getNewChoose() 
    {
        return newChoose;
    }
    public void setNewDate(Date newDate) 
    {
        this.newDate = newDate;
    }

    public Date getNewDate() 
    {
        return newDate;
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
            .append("productCode", getProductCode())
            .append("productName", getProductName())
            .append("oldChoose", getOldChoose())
            .append("newChoose", getNewChoose())
            .append("newDate", getNewDate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
