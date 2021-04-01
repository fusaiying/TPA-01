package com.paic.ehis.system.api.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ClaimCaseBillInfo {

    /** 账单ID */
    private Long billId;

    /** 报案号 */
    private String rptNo;

    /** 就诊医院编码 */
    private String hospitalCode;

    /** 科室 */
    private String department;

    /** 是否定点医院 */
    private String isDesHospital;

    /** 出险类型 */
    private String accType;

    /** 账单币种 */
    private String billCurrency;

    /** 账单金额 */
    private BigDecimal billAmount;

    /** 就诊次数 */
    private Long visNumber;

    /** 治疗类型 */
    private String treatmentType;

    /** 治疗起期 */
    private Date treatmentStartDate;

    /** 治疗止期 */
    private Date treatmentEndDate;

    /** 治疗天数 */
    private Long treatmentDays;

    /** 发票号 */
    private String invoiceNo;

    /** 账单号 */
    private String billNo;

    /** 账单类型 */
    private String billType;

    /** 社保先期给付 */
    private BigDecimal ssAdvancePayment;

    /** 第三方先期给付 */
    private BigDecimal tpAdvancePayment;

    /** 先期给付 */
    private BigDecimal advancePayment;

    /** 是否分摊先期给付 */
    private String isShareAp;

    /** 交易流水号 */
    private String transSerialNo;

    /** 交易流水自付额 */
    private BigDecimal transSerialCopay;

    /** 自付额 */
    private BigDecimal copay;

    /** 是否分摊自付额 */
    private String isShareCopay;

    /** 医院折扣金额 */
    private BigDecimal hosDiscountAmount;

    /** 是否分摊折扣金额 */
    private String isShareDisAmount;

    /** 自费金额 */
    private BigDecimal selfAmount;

    /** 部分自费金额 */
    private BigDecimal partSelfAmount;

    /** 不合理金额 */
    private BigDecimal unableAmount;

    /** 理算金额 */
    private BigDecimal calAmount;

    /** 拒赔金额 */
    private BigDecimal refusedAmount;

    /** 本次使用免赔额 */
    private BigDecimal deduUsed;

    /** 赔付比例 */
    private BigDecimal payRate;

    /** 赔付结论 */
    private String payConclusion;

    /** 备注 */
    private String remark;

    private List<ClaimCaseBillDetailInfo> claimCaseBillDetailInfoList;
}
