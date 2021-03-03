package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CaseCalBillVo
 * @Description: TODO  理算审核账单费用项详情
 * @Author 硠君
 * @Date create in 15:36 2021/1/21
 * @Version 1.0
 **/
@Data
public class CaseCalBillVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 报案号 */
    @Excel(name = "报案号",combo = "")
    private String rptNo;

    /** 账单ID */
    @Excel(name = "账单ID",combo = "")
    private Long billId;

    /** 赔付ID */
    private Long calBillId;

    /** 理算金额 */
    @Excel(name = "理算金额",combo = "")
    private BigDecimal calAmount;   //         cccb.cal_amount,

    /** 赔付金额 */
    @Excel(name = "理算金额",combo = "")
    private BigDecimal payAmount;   //         cccb.pay_amount,

    /** 拒赔金额 */
    @Excel(name = "拒赔金额",combo = "")
    private BigDecimal refusedAmount;   //         cccb.refused_amount,

    /** 本次使用免赔额 */
    @Excel(name = "本次使用免赔额",combo = "")
    private BigDecimal deduUsed;    //         cccb.dedu_used,

    /** 赔付结论 */
    @Excel(name = "赔付结论",combo = "")
    private String payConclusion;   //         cccb.pay_conclusion

    /** 备注 */
    private String remark;      //         cccb.remark,

    /** 账单金额 */
    @Excel(name = "账单金额",combo = "")
    private BigDecimal billAmount;  //         ccb.bill_amount,

    /** 治疗起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "治疗起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatmentStartDate;    //         ccb.treatment_start_date,

    /** 治疗止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "治疗止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatmentEndDate;      //         ccb.treatment_end_date,

    /** 发票号 */
    @Excel(name = "发票号",combo = "")
    private String invoiceNo;       // ccb.invoice_no,

    /** 账单号 */
    @Excel(name = "账单号",combo = "")
    private String billNo;      //         ccb.bill_no,

    /** 自付额 */
    @Excel(name = "自付额",combo = "")
    private BigDecimal copay;           //         ccb.copay,

    /** 医院折扣金额 */
    @Excel(name = "医院折扣金额",combo = "")
    private BigDecimal hosDiscountAmount;   //         ccb.hos_discount_amount,

    /** 不合理金额 */
    @Excel(name = "不合理金额",combo = "")
    private BigDecimal unableAmount;    //         ccb.unable_amount,

    /**  账单费用详情列表 */
    @Excel(targetAttr="")
    List<CaseCalBillItemVo> minData;

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getCalBillId() {
        return calBillId;
    }

    public void setCalBillId(Long calBillId) {
        this.calBillId = calBillId;
    }

    public BigDecimal getCalAmount() {
        return calAmount;
    }

    public void setCalAmount(BigDecimal calAmount) {
        this.calAmount = calAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getRefusedAmount() {
        return refusedAmount;
    }

    public void setRefusedAmount(BigDecimal refusedAmount) {
        this.refusedAmount = refusedAmount;
    }

    public BigDecimal getDeduUsed() {
        return deduUsed;
    }

    public void setDeduUsed(BigDecimal deduUsed) {
        this.deduUsed = deduUsed;
    }

    public String getPayConclusion() {
        return payConclusion;
    }

    public void setPayConclusion(String payConclusion) {
        this.payConclusion = payConclusion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public Date getTreatmentStartDate() {
        return treatmentStartDate;
    }

    public void setTreatmentStartDate(Date treatmentStartDate) {
        this.treatmentStartDate = treatmentStartDate;
    }

    public Date getTreatmentEndDate() {
        return treatmentEndDate;
    }

    public void setTreatmentEndDate(Date treatmentEndDate) {
        this.treatmentEndDate = treatmentEndDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BigDecimal getCopay() {
        return copay;
    }

    public void setCopay(BigDecimal copay) {
        this.copay = copay;
    }

    public BigDecimal getHosDiscountAmount() {
        return hosDiscountAmount;
    }

    public void setHosDiscountAmount(BigDecimal hosDiscountAmount) {
        this.hosDiscountAmount = hosDiscountAmount;
    }

    public BigDecimal getUnableAmount() {
        return unableAmount;
    }

    public void setUnableAmount(BigDecimal unableAmount) {
        this.unableAmount = unableAmount;
    }

    public List<CaseCalBillItemVo> getMinData() {
        return minData;
    }

    public void setMinData(List<CaseCalBillItemVo> minData) {
        this.minData = minData;
    }
}

