package com.paic.ehis.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * service_balance_detail(服务结算明细信息)对象 service_balance_detail
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
@Data
public class ServiceBalanceDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long serialNo;

    /** 任务号 */
    @Excel(name = "任务号")
    private String taskNo;

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String orderCode;

    /** 险种 */
    @Excel(name = "险种")
    private String riskName;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 供应商项目名称 */
    @Excel(name = "供应商项目名称")
    private String supplierServiceName;

    /** 投保人 */
    @Excel(name = "投保人")
    private String appntName;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String custName;

    /** 性别 */
    @Excel(name = "性别",readConverterExp="0=男,1=女")
    private String sex;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idNo;

    /** 证件号码 */
    @Excel(name = "备注")
    private String remark;

    /** 服务日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "服务日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date serviceDate;

    /** 服务费 */
    @Excel(name = "服务费")
    private BigDecimal amount;

    /** 实际服务费用 */
    @Excel(name = "实际服务费用")
    private BigDecimal actualAmount;

    /** 原因 */
    @Excel(name = "编辑原因")
    private String reason;

    /** 状态 */
    @Excel(name = "状态",readConverterExp="01=待确认,02=待审核,03=待确认(退回),04=待核销,05=已结算")
    private String bussinessStatus;

    /** 结算时间区间 */
    private String balanceInterval;

    /** 供应商编码 */
    private String supplierCode;

    /** 投保人客户号 */
    private String appntNo;

    /** 客户号 */
    private String custNo;

    /** 承保人数 */
    private Long insuredNum;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 扩展字段 */
    private String status;

    /** 产品期限日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nowDate;


    /** 产品期限日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beforeDate;

    //结算区间
    private String settlementTime;

    /** 服务名称 */
    private String serviceName;

    /** 供应商名称 */
    private String supplierName;

    private String serviceCode;

    /** 险种 */
    private String riskCode;

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
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    public void setBalanceInterval(String balanceInterval) 
    {
        this.balanceInterval = balanceInterval;
    }

    public String getBalanceInterval() 
    {
        return balanceInterval;
    }
    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setPolicyNo(String policyNo) 
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() 
    {
        return policyNo;
    }
    public void setPolicyItemNo(String policyItemNo) 
    {
        this.policyItemNo = policyItemNo;
    }

    public String getPolicyItemNo() 
    {
        return policyItemNo;
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
    public void setAppntNo(String appntNo) 
    {
        this.appntNo = appntNo;
    }

    public String getAppntNo() 
    {
        return appntNo;
    }
    public void setAppntName(String appntName) 
    {
        this.appntName = appntName;
    }

    public String getAppntName() 
    {
        return appntName;
    }
    public void setCustNo(String custNo) 
    {
        this.custNo = custNo;
    }

    public String getCustNo() 
    {
        return custNo;
    }
    public void setCustName(String custName) 
    {
        this.custName = custName;
    }

    public String getCustName() 
    {
        return custName;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setIdNo(String idNo) 
    {
        this.idNo = idNo;
    }

    public String getIdNo() 
    {
        return idNo;
    }
    public void setServiceDate(Date serviceDate) 
    {
        this.serviceDate = serviceDate;
    }

    public Date getServiceDate() 
    {
        return serviceDate;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setActualAmount(BigDecimal actualAmount) 
    {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getActualAmount() 
    {
        return actualAmount;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setInsuredNum(Long insuredNum) 
    {
        this.insuredNum = insuredNum;
    }

    public Long getInsuredNum() 
    {
        return insuredNum;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setBussinessStatus(String bussinessStatus)
    {
        this.bussinessStatus = bussinessStatus;
    }

    public String getBussinessStatus()
    {
        return bussinessStatus;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getSupplierServiceName() {
        return supplierServiceName;
    }

    public void setSupplierServiceName(String supplierServiceName) {
        this.supplierServiceName = supplierServiceName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serialNo", getSerialNo())
            .append("taskNo", getTaskNo())
            .append("status", getStatus())
            .append("orderCode", getOrderCode())
            .append("balanceInterval", getBalanceInterval())
            .append("riskCode", getRiskCode())
            .append("policyNo", getPolicyNo())
            .append("policyItemNo", getPolicyItemNo())
            .append("supplierCode", getSupplierCode())
            .append("serviceCode", getServiceCode())
            .append("appntNo", getAppntNo())
            .append("appntName", getAppntName())
            .append("custNo", getCustNo())
            .append("custName", getCustName())
            .append("sex", getSex())
            .append("idNo", getIdNo())
            .append("serviceDate", getServiceDate())
            .append("remark", getRemark())
            .append("amount", getAmount())
            .append("actualAmount", getActualAmount())
            .append("reason", getReason())
            .append("insuredNum", getInsuredNum())
            .append("unitPrice", getUnitPrice())
            .toString();
    }
}
