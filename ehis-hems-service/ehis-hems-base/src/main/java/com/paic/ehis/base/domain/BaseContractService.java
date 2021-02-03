package com.paic.ehis.base.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * base_contract_service（合约服务项目）对象 base_contract_service
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseContractService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 合约编码 */
    @Excel(name = "合约编码")
    private String contractNo;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 服务项目编码 */
    @Excel(name = "服务项目名称")
    private String serviceCode;

    /** 结算方式 */
    @Excel(name = "结算方式",readConverterExp = "01=按case(工单)，02=按人头(保单)，03=按一口价")
    private String settleType;

    /** 服务项目价格下限 */
    @Excel(name = "服务项目价格下限")
    private BigDecimal minPrice;

    /** 服务项目价格上限 */
    @Excel(name = "服务项目价格上限")
    private BigDecimal maxPrice;

    /** 结算币种 */
    @Excel(name = "结算币种",readConverterExp = "01-加币，02-港币，03-美元，04-日元，05-欧元，06-英镑，07-澳元，08-新币")
    private String settleCurrency;

    /** 日限次数 */
    @Excel(name = "日限次数")
    private String limitnum;

    /** 备注 */
    @Excel(name = "备注")
    private String remak;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "Y=有效，N=无效")
    private String status;

    /** 供应商项目名称 */
    @Excel(name = "供应商项目名称")
    private String supplierServiceName;


    public void setContractNo(String contractNo) 
    {
        this.contractNo = contractNo;
    }

    public String getContractNo() 
    {
        return contractNo;
    }
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setServiceCode(String serviceCode) 
    {
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() 
    {
        return serviceCode;
    }
    public void setSettleType(String settleType) 
    {
        this.settleType = settleType;
    }

    public String getSettleType() 
    {
        return settleType;
    }
    public void setMinPrice(BigDecimal minPrice) 
    {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinPrice() 
    {
        return minPrice;
    }
    public void setMaxPrice(BigDecimal maxPrice) 
    {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMaxPrice() 
    {
        return maxPrice;
    }
    public void setSettleCurrency(String settleCurrency) 
    {
        this.settleCurrency = settleCurrency;
    }

    public String getSettleCurrency() 
    {
        return settleCurrency;
    }
    public void setLimitnum(String limitnum) 
    {
        this.limitnum = limitnum;
    }

    public String getLimitnum() 
    {
        return limitnum;
    }
    public void setRemak(String remak) 
    {
        this.remak = remak;
    }

    public String getRemak() 
    {
        return remak;
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
            .append("contractNo", getContractNo())
            .append("supplierCode", getSupplierCode())
            .append("serviceCode", getServiceCode())
            .append("settleType", getSettleType())
            .append("minPrice", getMinPrice())
            .append("maxPrice", getMaxPrice())
            .append("settleCurrency", getSettleCurrency())
            .append("limitnum", getLimitnum())
            .append("remak", getRemak())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
