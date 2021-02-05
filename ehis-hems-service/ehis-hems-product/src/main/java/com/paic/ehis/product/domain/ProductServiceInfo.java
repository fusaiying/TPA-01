package com.paic.ehis.product.domain;

import java.math.BigDecimal;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_product_service_info(服务产品项目关联)对象 product_service_info
 * 
 * @author sino
 * @date 2021-01-15
 */
@Data
public class ProductServiceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 服务项目编码 */
    @Excel(name = "服务项目编码")
    private String serviceCode;

    /** 服务项目名称 */
    @Excel(name = "服务项目名称")
    private String serviceName;

    /** 类别编码 */
    @Excel(name = "类别编码")
    private String categoryCode;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String categoryName;

    /** 种类编码 */
    @Excel(name = "种类编码")
    private String typeCode;

    /** 种类名称 */
    @Excel(name = "种类名称")
    private String typeName;

    /** 扣减方式 */
    @Excel(name = "扣减方式")
    private String deductWay;

    /** 成本价格 */
    @Excel(name = "成本价格")
    private BigDecimal costPrice;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String status;

    /** 服务项目编码名称 */
    @Excel(name = "服务项目编码名称")
    private String serviceCodeName;

    /** 种类编码名称 */
    @Excel(name = "种类编码名称")
    private String typeCodeName;

    /** 类别编码名称 */
    @Excel(name = "类别编码名称")
    private String categoryCodeName;

    /** 供应商数量 */
    @Excel(name = "供应商数量")
    private String number;

    public void setSerialNo(String serialNo) 
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo() 
    {
        return serialNo;
    }
    public void setProductCode(String productCode) 
    {
        this.productCode = productCode;
    }

    public String getProductCode() 
    {
        return productCode;
    }
    public void setServiceCode(String serviceCode) 
    {
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() 
    {
        return serviceCode;
    }
    public void setServiceName(String serviceName) 
    {
        this.serviceName = serviceName;
    }

    public String getServiceName() 
    {
        return serviceName;
    }
    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setTypeCode(String typeCode) 
    {
        this.typeCode = typeCode;
    }

    public String getTypeCode() 
    {
        return typeCode;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setDeductWay(String deductWay) 
    {
        this.deductWay = deductWay;
    }

    public String getDeductWay() 
    {
        return deductWay;
    }
    public void setCostPrice(BigDecimal costPrice) 
    {
        this.costPrice = costPrice;
    }

    public BigDecimal getCostPrice() 
    {
        return costPrice;
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
            .append("productCode", getProductCode())
            .append("serviceCode", getServiceCode())
            .append("serviceName", getServiceName())
            .append("categoryCode", getCategoryCode())
            .append("categoryName", getCategoryName())
            .append("typeCode", getTypeCode())
            .append("typeName", getTypeName())
            .append("deductWay", getDeductWay())
            .append("costPrice", getCostPrice())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
