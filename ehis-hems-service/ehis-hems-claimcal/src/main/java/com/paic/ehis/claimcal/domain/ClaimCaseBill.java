package com.paic.ehis.claimcal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.claimcal.domain.vo.IcdCode;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 案件账单明细对象 claim_case_bill
 * 
 * @author sino
 * @date 2021-01-15
 */
@Data
public class ClaimCaseBill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账单ID */
    private Long billId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 就诊医院编码 */
    @Excel(name = "就诊医院编码")
    private String hospitalCode;

    /** 科室 */
    @Excel(name = "科室")
    private String department;

    /** 是否定点医院 */
    @Excel(name = "是否定点医院")
    private String isDesHospital;

    /** 出险类型 */
    @Excel(name = "出险类型")
    private String accType;

    /** 账单币种 */
    @Excel(name = "账单币种")
    private String billCurrency;

    /** 账单金额 */
    @Excel(name = "账单金额")
    private BigDecimal billAmount;

    /** 就诊次数 */
    @Excel(name = "就诊次数")
    private Long visNumber;

    /** 治疗类型 */
    @Excel(name = "治疗类型")
    private String treatmentType;

    /** 治疗起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "治疗起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatmentStartDate;

    /** 治疗止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "治疗止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatmentEndDate;

    /** 治疗天数 */
    @Excel(name = "治疗天数")
    private Long treatmentDays;

    /** 发票号 */
    @Excel(name = "发票号")
    private String invoiceNo;

    /** 账单号 */
    @Excel(name = "账单号")
    private String billNo;

    /** 账单类型 */
    @Excel(name = "账单类型")
    private String billType;

    /** 社保先期给付 */
    @Excel(name = "社保先期给付")
    private BigDecimal ssAdvancePayment;

    /** 第三方先期给付 */
    @Excel(name = "第三方先期给付")
    private BigDecimal tpAdvancePayment;

    /** 是否分摊先期给付 */
    @Excel(name = "是否分摊先期给付")
    private String isShareAp;

    /** 交易流水号 */
    @Excel(name = "交易流水号")
    private String transSerialNo;

    /** 交易流水自付额 */
    @Excel(name = "交易流水自付额")
    private BigDecimal transSerialCopay;

    /** 自付额 */
    @Excel(name = "自付额")
    private BigDecimal copay;

    /** 是否分摊自付额 */
    @Excel(name = "是否分摊自付额")
    private String isShareCopay;

    /** 医院折扣金额 */
    @Excel(name = "医院折扣金额")
    private BigDecimal hosDiscountAmount;

    /** 是否分摊折扣金额 */
    @Excel(name = "是否分摊折扣金额")
    private String isShareDisAmount;

    /** 自费金额 */
    @Excel(name = "自费金额")
    private BigDecimal selfAmount;

    /** 部分自费金额 */
    @Excel(name = "部分自费金额")
    private BigDecimal partSelfAmount;

    /** 不合理金额 */
    @Excel(name = "不合理金额")
    private BigDecimal unableAmount;

    /** 临床诊断 */
    @Excel(name = "临床诊断")
    private String clinicalDiagnosis;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 主要诊断 */
    private String icdCode;

    /**
     * 给付金额
     */
    @Excel(name = "给付金额")
    private String paymentAmount;

    /** 次要诊断 */
    private List<IcdCode> icdCodes;

    public List<IcdCode> getIcdCodes() {
        return icdCodes;
    }

    public void setIcdCodes(List<IcdCode> icdCodes) {
        this.icdCodes = icdCodes;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public void setBillId(Long billId)
    {
        this.billId = billId;
    }

    public Long getBillId() 
    {
        return billId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setHospitalCode(String hospitalCode) 
    {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalCode() 
    {
        return hospitalCode;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setIsDesHospital(String isDesHospital) 
    {
        this.isDesHospital = isDesHospital;
    }

    public String getIsDesHospital() 
    {
        return isDesHospital;
    }
    public void setAccType(String accType) 
    {
        this.accType = accType;
    }

    public String getAccType() 
    {
        return accType;
    }
    public void setBillCurrency(String billCurrency) 
    {
        this.billCurrency = billCurrency;
    }

    public String getBillCurrency() 
    {
        return billCurrency;
    }
    public void setBillAmount(BigDecimal billAmount) 
    {
        this.billAmount = billAmount;
    }

    public BigDecimal getBillAmount() 
    {
        return billAmount;
    }
    public void setVisNumber(Long visNumber) 
    {
        this.visNumber = visNumber;
    }

    public Long getVisNumber() 
    {
        return visNumber;
    }
    public void setTreatmentType(String treatmentType) 
    {
        this.treatmentType = treatmentType;
    }

    public String getTreatmentType() 
    {
        return treatmentType;
    }
    public void setTreatmentStartDate(Date treatmentStartDate) 
    {
        this.treatmentStartDate = treatmentStartDate;
    }

    public Date getTreatmentStartDate() 
    {
        return treatmentStartDate;
    }
    public void setTreatmentEndDate(Date treatmentEndDate) 
    {
        this.treatmentEndDate = treatmentEndDate;
    }

    public Date getTreatmentEndDate() 
    {
        return treatmentEndDate;
    }
    public void setTreatmentDays(Long treatmentDays) 
    {
        this.treatmentDays = treatmentDays;
    }

    public Long getTreatmentDays() 
    {
        return treatmentDays;
    }
    public void setInvoiceNo(String invoiceNo) 
    {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceNo() 
    {
        return invoiceNo;
    }
    public void setBillNo(String billNo) 
    {
        this.billNo = billNo;
    }

    public String getBillNo() 
    {
        return billNo;
    }
    public void setBillType(String billType) 
    {
        this.billType = billType;
    }

    public String getBillType() 
    {
        return billType;
    }
    public void setSsAdvancePayment(BigDecimal ssAdvancePayment) 
    {
        this.ssAdvancePayment = ssAdvancePayment;
    }

    public BigDecimal getSsAdvancePayment() 
    {
        return ssAdvancePayment;
    }
    public void setTpAdvancePayment(BigDecimal tpAdvancePayment) 
    {
        this.tpAdvancePayment = tpAdvancePayment;
    }

    public BigDecimal getTpAdvancePayment() 
    {
        return tpAdvancePayment;
    }
    public void setIsShareAp(String isShareAp) 
    {
        this.isShareAp = isShareAp;
    }

    public String getIsShareAp() 
    {
        return isShareAp;
    }
    public void setTransSerialNo(String transSerialNo) 
    {
        this.transSerialNo = transSerialNo;
    }

    public String getTransSerialNo() 
    {
        return transSerialNo;
    }
    public void setTransSerialCopay(BigDecimal transSerialCopay) 
    {
        this.transSerialCopay = transSerialCopay;
    }

    public BigDecimal getTransSerialCopay() 
    {
        return transSerialCopay;
    }
    public void setCopay(BigDecimal copay) 
    {
        this.copay = copay;
    }

    public BigDecimal getCopay() 
    {
        return copay;
    }
    public void setIsShareCopay(String isShareCopay) 
    {
        this.isShareCopay = isShareCopay;
    }

    public String getIsShareCopay() 
    {
        return isShareCopay;
    }
    public void setHosDiscountAmount(BigDecimal hosDiscountAmount) 
    {
        this.hosDiscountAmount = hosDiscountAmount;
    }

    public BigDecimal getHosDiscountAmount() 
    {
        return hosDiscountAmount;
    }
    public void setIsShareDisAmount(String isShareDisAmount) 
    {
        this.isShareDisAmount = isShareDisAmount;
    }

    public String getIsShareDisAmount() 
    {
        return isShareDisAmount;
    }
    public void setSelfAmount(BigDecimal selfAmount) 
    {
        this.selfAmount = selfAmount;
    }

    public BigDecimal getSelfAmount() 
    {
        return selfAmount;
    }
    public void setPartSelfAmount(BigDecimal partSelfAmount) 
    {
        this.partSelfAmount = partSelfAmount;
    }

    public BigDecimal getPartSelfAmount() 
    {
        return partSelfAmount;
    }
    public void setUnableAmount(BigDecimal unableAmount) 
    {
        this.unableAmount = unableAmount;
    }

    public BigDecimal getUnableAmount() 
    {
        return unableAmount;
    }
    public void setClinicalDiagnosis(String clinicalDiagnosis) 
    {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public String getClinicalDiagnosis() 
    {
        return clinicalDiagnosis;
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
            .append("billId", getBillId())
            .append("rptNo", getRptNo())
            .append("hospitalCode", getHospitalCode())
            .append("department", getDepartment())
            .append("isDesHospital", getIsDesHospital())
            .append("accType", getAccType())
            .append("billCurrency", getBillCurrency())
            .append("billAmount", getBillAmount())
            .append("visNumber", getVisNumber())
            .append("treatmentType", getTreatmentType())
            .append("treatmentStartDate", getTreatmentStartDate())
            .append("treatmentEndDate", getTreatmentEndDate())
            .append("treatmentDays", getTreatmentDays())
            .append("invoiceNo", getInvoiceNo())
            .append("billNo", getBillNo())
            .append("billType", getBillType())
            .append("ssAdvancePayment", getSsAdvancePayment())
            .append("tpAdvancePayment", getTpAdvancePayment())
            .append("isShareAp", getIsShareAp())
            .append("transSerialNo", getTransSerialNo())
            .append("transSerialCopay", getTransSerialCopay())
            .append("copay", getCopay())
            .append("isShareCopay", getIsShareCopay())
            .append("hosDiscountAmount", getHosDiscountAmount())
            .append("isShareDisAmount", getIsShareDisAmount())
            .append("selfAmount", getSelfAmount())
            .append("partSelfAmount", getPartSelfAmount())
            .append("unableAmount", getUnableAmount())
            .append("clinicalDiagnosis", getClinicalDiagnosis())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
