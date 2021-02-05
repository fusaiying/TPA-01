package com.paic.ehis.product.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_product_check_info(服务产品审核信息)对象 product_check_info
 * 
 * @author sino
 * @date 2021-01-15
 */
public class ProductCheckInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productCode;

    /** 审核结果 */
    @Excel(name = "审核结果")
    private String checkResult;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String checkAdvice;

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
    public void setCheckResult(String checkResult) 
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult() 
    {
        return checkResult;
    }
    public void setCheckAdvice(String checkAdvice) 
    {
        this.checkAdvice = checkAdvice;
    }

    public String getCheckAdvice() 
    {
        return checkAdvice;
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
            .append("checkResult", getCheckResult())
            .append("checkAdvice", getCheckAdvice())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
