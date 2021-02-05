package com.paic.ehis.product.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_product_manager_log(服务产品管理记录)对象 product_manager_log
 * 
 * @author sino
 * @date 2021-01-15
 */
public class ProductManagerLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 业务状态 */
    @Excel(name = "业务状态")
    private String bussinessStatus;

    /** 下线原因 */
    @Excel(name = "下线原因")
    private String reason;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String status;

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
    public void setBussinessStatus(String bussinessStatus) 
    {
        this.bussinessStatus = bussinessStatus;
    }

    public String getBussinessStatus() 
    {
        return bussinessStatus;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
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
            .append("bussinessStatus", getBussinessStatus())
            .append("reason", getReason())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
