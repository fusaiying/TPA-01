package com.paic.ehis.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * order_info(工单信息)对象 order_info
 * 
 * @author sino
 * @date 2021-01-26
 */
@Data
public class OrderInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 工单编码 */
    @Excel(name = "工单编码")
    private String orderCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productChname;

    /** 服务名称 */
    @Excel(name = "服务项目")
    private String serviceName;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String chname;

    /** 姓名 */
    @Excel(name = "客户姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 证件 */
    @Excel(name = "证件类型")
    private String idType;

    /** 证件类型 */
    @Excel(name = "证件号码")
    private String idCode;

    @Excel(name = "手机号码")
    private String phone;

    /** 服务预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;


    /** 业务状态 */
    @Excel(name = "工单状态")
    private String bussinessStatus;

    /** 生日 */
    private String birthday;

    /** 年龄 */
    private String age;

    /** 保单号 */
    private String policyNo;

    /** 分单号 */
    private String policyCertificateNo;

    /** 产品编码 */
    private String productCode;

    /** 服务项目编码 */
    private String serviceCode;

    /** 供应商编码 */
    private String supplierCode;

    /** 客户号 */
    private String customerNo;

    /** 联系人 */
    private String contactName;

    /** 住院地区 */
    private String inpatientArea;

    /** 住院地区 */
    private List<String> inpatientAreaList;

    /** 期望地区 */
    private String expectedArea;

    /** 期望地区 */
    private List<String> expectedAreaList;

    /** 就诊地区 */
    private String visitingArea;

    /** 就诊地区 */
    private List<String> visitingAreaList;

    /** 所在城市 */
    private String city;

    /** 所在城市 */
    private List<String> cityList;

    /** 服务类型 */
    private String serviceType;



    /** 期望医院 */
    private String expectationHospital;

    /** 期望科室 */
    private String expectedDepartment;

    /** 重疾名称 */
    private String seriousDisease;

    /** 手术名称 */
    private String operation;

    /** 出院诊断 */
    private String finalDiagnosis;

    /** 症状描述 */
    private String symptomDescription;

    /** 原因 */
    private String reason;

    /** 节点状态 */
    private String nodeStatus;


    /** 数据状态 */
    private String status;

    /** 变更标志 */
    private String updateFlag;

    /** 一级科室 */
    private String firstDept;

    /** 二级科室 */
    private String secondDept;

    /** 服务预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyStartTime;

    /** 服务预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyEndTime;

    /** 住院时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inpatientTime;

    /** 主任 */
    private String director;

    /** 取消时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String cancelTime;

    /** 接单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receivingTime;

    /** 预约成功/失败时间 */
    private Date applyResultTime;

    /** 订单完成时间 */
    private Date orderCompleteTime;

    /** 医生会诊意见 */
    private String consultation;

    /** 会诊医生介绍 */
    private String consultant;

    /** 取消原因 */
    private String cancelReason;

    /** B端具体状态 */
    private String detailStatus;

    /** 住院医院 */
    private String inpatientHospital;

    /** 住院地址 */
    private String inpatientAddress;

    /** 预约失败原因 */
    private String failureReason;

    /** 未到诊原因 */
    private String noVisitReason;

    /** 结算价格 */
    private String settlePrice;

    /** 寄送方式 */
    private String dealingMethod;

    /** 寄送说明 */
    private String dealingShow;

    /** 不通过原因 */
    private String refuseReason;

    /** 循环次数 */
    private int forNum;

    /** 毫秒数 */
    private long millisecond;

    private String idtypeName;

    private String chname1;

    private String sexName;

    private String role;

    private String detailInfo;

    /** 当前日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "当前时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date nowDate;


    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    public Date getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(Date beforeDate) {
        this.beforeDate = beforeDate;
    }

    /** 三个月前日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "三个月前日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beforeDate;


    public void setSerialNo(String serialNo) 
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo() 
    {
        return serialNo;
    }
    public void setOrderCode(String orderCode) 
    {
        this.orderCode = orderCode;
    }

    public String getOrderCode() 
    {
        return orderCode;
    }
    public void setPolicyNo(String policyNo) 
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() 
    {
        return policyNo;
    }
    public void setPolicyCertificateNo(String policyCertificateNo) 
    {
        this.policyCertificateNo = policyCertificateNo;
    }

    public String getPolicyCertificateNo() 
    {
        return policyCertificateNo;
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
    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setInpatientArea(String inpatientArea) 
    {
        this.inpatientArea = inpatientArea;
    }

    public String getInpatientArea() 
    {
        return inpatientArea;
    }
    public void setExpectedArea(String expectedArea) 
    {
        this.expectedArea = expectedArea;
    }

    public String getExpectedArea() 
    {
        return expectedArea;
    }
    public void setVisitingArea(String visitingArea) 
    {
        this.visitingArea = visitingArea;
    }

    public String getVisitingArea() 
    {
        return visitingArea;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setServiceType(String serviceType) 
    {
        this.serviceType = serviceType;
    }

    public String getServiceType() 
    {
        return serviceType;
    }
    public void setApplyTime(Date applyTime) 
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() 
    {
        return applyTime;
    }
    public void setExpectationHospital(String expectationHospital) 
    {
        this.expectationHospital = expectationHospital;
    }

    public String getExpectationHospital() 
    {
        return expectationHospital;
    }
    public void setExpectedDepartment(String expectedDepartment) 
    {
        this.expectedDepartment = expectedDepartment;
    }

    public String getExpectedDepartment() 
    {
        return expectedDepartment;
    }
    public void setSeriousDisease(String seriousDisease) 
    {
        this.seriousDisease = seriousDisease;
    }

    public String getSeriousDisease() 
    {
        return seriousDisease;
    }
    public void setOperation(String operation) 
    {
        this.operation = operation;
    }

    public String getOperation() 
    {
        return operation;
    }
    public void setFinalDiagnosis(String finalDiagnosis) 
    {
        this.finalDiagnosis = finalDiagnosis;
    }

    public String getFinalDiagnosis() 
    {
        return finalDiagnosis;
    }
    public void setSymptomDescription(String symptomDescription) 
    {
        this.symptomDescription = symptomDescription;
    }

    public String getSymptomDescription() 
    {
        return symptomDescription;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setNodeStatus(String nodeStatus) 
    {
        this.nodeStatus = nodeStatus;
    }

    public String getNodeStatus() 
    {
        return nodeStatus;
    }
    public void setBussinessStatus(String bussinessStatus) 
    {
        this.bussinessStatus = bussinessStatus;
    }

    public String getBussinessStatus() 
    {
        return bussinessStatus;
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
            .append("orderCode", getOrderCode())
            .append("policyNo", getPolicyNo())
            .append("policyCertificateNo", getPolicyCertificateNo())
            .append("productCode", getProductCode())
            .append("serviceCode", getServiceCode())
            .append("supplierCode", getSupplierCode())
            .append("customerNo", getCustomerNo())
            .append("contactName", getContactName())
            .append("phone", getPhone())
            .append("inpatientArea", getInpatientArea())
            .append("expectedArea", getExpectedArea())
            .append("visitingArea", getVisitingArea())
            .append("city", getCity())
            .append("serviceType", getServiceType())
            .append("applyTime", getApplyTime())
            .append("expectationHospital", getExpectationHospital())
            .append("expectedDepartment", getExpectedDepartment())
            .append("seriousDisease", getSeriousDisease())
            .append("operation", getOperation())
            .append("finalDiagnosis", getFinalDiagnosis())
            .append("symptomDescription", getSymptomDescription())
            .append("remark", getRemark())
            .append("reason", getReason())
            .append("nodeStatus", getNodeStatus())
            .append("bussinessStatus", getBussinessStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
