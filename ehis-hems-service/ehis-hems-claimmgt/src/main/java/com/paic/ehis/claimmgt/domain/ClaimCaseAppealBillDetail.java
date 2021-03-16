package com.paic.ehis.claimmgt.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 案件账单费用项明细对象 claim_case_bill_detail
 * 
 * @author sino
 * @date 2021-01-21
 */
public class ClaimCaseAppealBillDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账单明细ID */
    private Long detailId;

    /** 账单ID */
    private Long billId;

    /** 旧账单ID */
    private Long oldBillId;

    /** 报案号 */
    private String rptNo;

    /** 费用项编码 */
    private String feeItemCode;

    /** 费用项名称 */
    private String feeItemName;

    /** 费用项账单金额 */
    private BigDecimal billDetailAmount;

    /** 自费金额 */
    private BigDecimal selfAmount;

    /** 部分自费金额 */
    private BigDecimal partSelfAmount;

    /** 费用项先期给付 */
    private BigDecimal advancePayment;

    /** 费用项自付额 */
    private BigDecimal billDetailCopay;

    /** 费用项医院折扣金额 */
    private BigDecimal hosDiscountAmount;

    /** 不合理金额 */
    private BigDecimal unableAmount;

    /** 就诊次数 */
    private Long visNumber;

    /** 数据状态（Y-有效，N-无效） */
    private String status;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getOldBillId() {
        return oldBillId;
    }

    public void setOldBillId(Long oldBillId) {
        this.oldBillId = oldBillId;
    }

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public String getFeeItemCode() {
        return feeItemCode;
    }

    public void setFeeItemCode(String feeItemCode) {
        this.feeItemCode = feeItemCode;
    }

    public String getFeeItemName() {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName) {
        this.feeItemName = feeItemName;
    }

    public BigDecimal getBillDetailAmount() {
        return billDetailAmount;
    }

    public void setBillDetailAmount(BigDecimal billDetailAmount) {
        this.billDetailAmount = billDetailAmount;
    }

    public BigDecimal getSelfAmount() {
        return selfAmount;
    }

    public void setSelfAmount(BigDecimal selfAmount) {
        this.selfAmount = selfAmount;
    }

    public BigDecimal getPartSelfAmount() {
        return partSelfAmount;
    }

    public void setPartSelfAmount(BigDecimal partSelfAmount) {
        this.partSelfAmount = partSelfAmount;
    }

    public BigDecimal getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(BigDecimal advancePayment) {
        this.advancePayment = advancePayment;
    }

    public BigDecimal getBillDetailCopay() {
        return billDetailCopay;
    }

    public void setBillDetailCopay(BigDecimal billDetailCopay) {
        this.billDetailCopay = billDetailCopay;
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

    public Long getVisNumber() {
        return visNumber;
    }

    public void setVisNumber(Long visNumber) {
        this.visNumber = visNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
