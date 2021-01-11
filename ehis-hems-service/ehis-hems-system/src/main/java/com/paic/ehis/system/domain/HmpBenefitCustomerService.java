package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户服务产品对象 hmp_benefit_customer_service
 * 
 * @author sino
 * @date 2020-11-17
 */
public class HmpBenefitCustomerService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private String customerid;

    /** 产品编码 */
    private String productcode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productname;

    /** 服务编码 */
    private String servicecode;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startdate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enddate;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String servicename;

    /** 服务来源 */
    @Excel(name = "服务来源")
    private String servicesource;

    /** 服务上限 */
    @Excel(name = "服务上限")
    private Long upperlimit;

    /** 上限单位 */
    @Excel(name = "上限单位")
    private String limitunit;

    /** 剩余 */
    @Excel(name = "剩余")
    private Long balance;

    public void setCustomerid(String customerid) 
    {
        this.customerid = customerid;
    }

    public String getCustomerid() 
    {
        return customerid;
    }
    public void setProductcode(String productcode) 
    {
        this.productcode = productcode;
    }

    public String getProductcode() 
    {
        return productcode;
    }
    public void setProductname(String productname) 
    {
        this.productname = productname;
    }

    public String getProductname() 
    {
        return productname;
    }
    public void setServicecode(String servicecode) 
    {
        this.servicecode = servicecode;
    }

    public String getServicecode() 
    {
        return servicecode;
    }
    public void setStartdate(Date startdate) 
    {
        this.startdate = startdate;
    }

    public Date getStartdate() 
    {
        return startdate;
    }
    public void setEnddate(Date enddate) 
    {
        this.enddate = enddate;
    }

    public Date getEnddate() 
    {
        return enddate;
    }
    public void setServicename(String servicename) 
    {
        this.servicename = servicename;
    }

    public String getServicename() 
    {
        return servicename;
    }
    public void setServicesource(String servicesource) 
    {
        this.servicesource = servicesource;
    }

    public String getServicesource() 
    {
        return servicesource;
    }
    public void setUpperlimit(Long upperlimit) 
    {
        this.upperlimit = upperlimit;
    }

    public Long getUpperlimit() 
    {
        return upperlimit;
    }
    public void setLimitunit(String limitunit) 
    {
        this.limitunit = limitunit;
    }

    public String getLimitunit() 
    {
        return limitunit;
    }
    public void setBalance(Long balance) 
    {
        this.balance = balance;
    }

    public Long getBalance() 
    {
        return balance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerid", getCustomerid())
            .append("productcode", getProductcode())
            .append("productname", getProductname())
            .append("servicecode", getServicecode())
            .append("startdate", getStartdate())
            .append("enddate", getEnddate())
            .append("servicename", getServicename())
            .append("servicesource", getServicesource())
            .append("upperlimit", getUpperlimit())
            .append("limitunit", getLimitunit())
            .append("balance", getBalance())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
