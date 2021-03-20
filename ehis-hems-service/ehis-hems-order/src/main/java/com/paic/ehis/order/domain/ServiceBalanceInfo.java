package com.paic.ehis.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * service_balance_info(服务结算基本信息)对象 service_balance_info
 *
 * @author chenhaiming
 * @date 2021-01-25
 */
public class ServiceBalanceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long serialNo;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNo;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String supplierCode;

    /** 供应商项目编码 */
    @Excel(name = "供应商项目编码")
    private String serviceCode;

    /** 结算方式 */
    @Excel(name = "结算方式",readConverterExp="01=按case,02=按人头,03=按一口价")
    private String settleType;

    /** 结算币种 */
    @Excel(name = "结算币种",readConverterExp="01=加币,02=人民币,03=港币,04=美元,05=日元,06=欧元,07=英镑,08=澳元,09=新币")
    private String settleCurrency;

    /** 结算止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 发票类型 */
    @Excel(name = "发票类型",readConverterExp="01=有票,02=无票")
    private String balanceInvoiceType;

    /** 服务费总金额 */
    @Excel(name = "服务费总金额")
    private BigDecimal serviceAmount;

    /** 状态 */
    @Excel(name = "状态",readConverterExp="Y=有效,N=无效")
    private String status;

    /** 是否导入 */
    //@Excel(name = "是否导入")
    private String isImport;

    /** 业务状态 */
    //@Excel(name = "业务状态")
    private String bussinessStatus;

    /** 核销日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "核销日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date balanceDate;

    /** 日志id */
    //@Excel(name = "日志id")
    private Long logId;

    /** 发票状态 */
    //@Excel(name = "发票状态")
    private String invoiceStatus;

    /** 地区代码 */
    //@Excel(name = "地区代码")
    private String areaCode;

    /** 公司段 */
    //@Excel(name = "公司段")
    private String manageCom;

    /** 支付流水号 */
    //@Excel(name = "支付流水号")
    private String payNo;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    //@Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /** 支付金额 */
    //@Excel(name = "支付金额")
    private BigDecimal payAmount;

    public void setSerialNo(Long serialNo)
    {
        this.serialNo = serialNo;
    }

    public Long getSerialNo()
    {
        return serialNo;
    }
    public void setTaskNo(String taskNo)
    {
        this.taskNo = taskNo;
    }

    public String getTaskNo()
    {
        return taskNo;
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
    public void setSettleCurrency(String settleCurrency)
    {
        this.settleCurrency = settleCurrency;
    }

    public String getSettleCurrency()
    {
        return settleCurrency;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }
    public void setBalanceInvoiceType(String balanceInvoiceType) {
        this.balanceInvoiceType = balanceInvoiceType;
    }

    public String getBalanceInvoiceType() {
        return balanceInvoiceType;
    }
    public void setServiceAmount(BigDecimal serviceAmount)
    {
        this.serviceAmount = serviceAmount;
    }

    public BigDecimal getServiceAmount()
    {
        return serviceAmount;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setIsImport(String isImport)
    {
        this.isImport = isImport;
    }

    public String getIsImport()
    {
        return isImport;
    }
    public void setBussinessStatus(String bussinessStatus)
    {
        this.bussinessStatus = bussinessStatus;
    }

    public String getBussinessStatus()
    {
        return bussinessStatus;
    }
    public void setBalanceDate(Date balanceDate)
    {
        this.balanceDate = balanceDate;
    }

    public Date getBalanceDate()
    {
        return balanceDate;
    }
    public void setLogId(Long logId)
    {
        this.logId = logId;
    }

    public Long getLogId()
    {
        return logId;
    }
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode()
    {
        return areaCode;
    }
    public void setManageCom(String manageCom)
    {
        this.manageCom = manageCom;
    }

    public String getManageCom()
    {
        return manageCom;
    }
    public void setPayNo(String payNo)
    {
        this.payNo = payNo;
    }

    public String getPayNo()
    {
        return payNo;
    }
    public void setPayDate(Date payDate)
    {
        this.payDate = payDate;
    }

    public Date getPayDate()
    {
        return payDate;
    }
    public void setPayAmount(BigDecimal payAmount)
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount()
    {
        return payAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("serialNo", getSerialNo())
                .append("taskNo", getTaskNo())
                .append("supplierCode", getSupplierCode())
                .append("serviceCode", getServiceCode())
                .append("settleType", getSettleType())
                .append("settleCurrency", getSettleCurrency())
                .append("endDate", getEndDate())
                .append("balanceInvoiceType", getBalanceInvoiceType())
                .append("serviceAmount", getServiceAmount())
                .append("status", getStatus())
                .append("isImport", getIsImport())
                .append("bussinessStatus", getBussinessStatus())
                .append("balanceDate", getBalanceDate())
                .append("logId", getLogId())
                .append("invoiceStatus", getInvoiceStatus())
                .append("areaCode", getAreaCode())
                .append("manageCom", getManageCom())
                .append("payNo", getPayNo())
                .append("payDate", getPayDate())
                .append("payAmount", getPayAmount())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
