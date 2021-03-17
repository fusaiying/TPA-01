package com.paic.ehis.order.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * order_evaluate_info(服务评价信息)对象 order_evaluate_info
 * 
 * @author sino
 * @date 2021-01-26
 */
@Data
public class OrderEvaluateInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 服务编码 */
    @Excel(name = "服务编码")
    private String serviceCode;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 客户号 */
    @Excel(name = "客户号")
    private String customerNo;

    /** 客户评价 */
    @Excel(name = "客户评价")
    private String customerEvaluation;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String evaluationContent;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String status;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idCode;

    //保单号
    private String policyNo;


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
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setCustomerNo(String customerNo) 
    {
        this.customerNo = customerNo;
    }

    public String getCustomerNo() 
    {
        return customerNo;
    }
    public void setCustomerEvaluation(String customerEvaluation) 
    {
        this.customerEvaluation = customerEvaluation;
    }

    public String getCustomerEvaluation() 
    {
        return customerEvaluation;
    }
    public void setEvaluationContent(String evaluationContent) 
    {
        this.evaluationContent = evaluationContent;
    }

    public String getEvaluationContent() 
    {
        return evaluationContent;
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
            .append("supplierCode", getSupplierCode())
            .append("customerNo", getCustomerNo())
            .append("customerEvaluation", getCustomerEvaluation())
            .append("evaluationContent", getEvaluationContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
