package com.paic.ehis.system.domain;

import java.util.Date;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务工单对象 hmp_service_order
 *
 * @author sino
 * @date 2020-11-17
 */
public class HmpServiceOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String ordercode;

    private String customerid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ordersource;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String productcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String servicecode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date appointmenttime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String supplier;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String supplieroutlets;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String supplierdoctor;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderstatus;

    private HmpServiceOrderApplication serviceOrderApplication;

    public HmpServiceOrderApplication getServiceOrderApplication() {
        return serviceOrderApplication;
    }

    public void setServiceOrderApplication(HmpServiceOrderApplication serviceOrderApplication) {
        this.serviceOrderApplication = serviceOrderApplication;
    }

    public void setOrdercode(String ordercode)
    {
        this.ordercode = ordercode;
    }

    public String getOrdercode()
    {
        return ordercode;
    }
    public void setOrdersource(String ordersource)
    {
        this.ordersource = ordersource;
    }

    public String getCustomerid() {
        return customerid;
    }
    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getOrdersource()
    {
        return ordersource;
    }
    public void setProductcode(String productcode)
    {
        this.productcode = productcode;
    }

    public String getProductcode()
    {
        return productcode;
    }
    public void setServicecode(String servicecode)
    {
        this.servicecode = servicecode;
    }

    public String getServicecode()
    {
        return servicecode;
    }
    public void setAppointmenttime(Date appointmenttime)
    {
        this.appointmenttime = appointmenttime;
    }

    public Date getAppointmenttime()
    {
        return appointmenttime;
    }
    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }

    public String getSupplier()
    {
        return supplier;
    }
    public void setSupplieroutlets(String supplieroutlets)
    {
        this.supplieroutlets = supplieroutlets;
    }

    public String getSupplieroutlets()
    {
        return supplieroutlets;
    }
    public void setSupplierdoctor(String supplierdoctor)
    {
        this.supplierdoctor = supplierdoctor;
    }

    public String getSupplierdoctor()
    {
        return supplierdoctor;
    }
    public void setOrderstatus(String orderstatus)
    {
        this.orderstatus = orderstatus;
    }

    public String getOrderstatus()
    {
        return orderstatus;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ordercode", getOrdercode())
            .append("customerid", getCustomerid())
            .append("ordersource", getOrdersource())
            .append("productcode", getProductcode())
            .append("servicecode", getServicecode())
            .append("appointmenttime", getAppointmenttime())
            .append("supplier", getSupplier())
            .append("supplieroutlets", getSupplieroutlets())
            .append("supplierdoctor", getSupplierdoctor())
            .append("orderstatus", getOrderstatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
