package com.paic.ehis.system.api.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xicc on 2021/3/25
 */
@Data
public class ClaimCaseBillDetailInfo {
    /** 账单明细ID */
    private Long detailId;

    /** 账单ID */
    private Long billId;

    /** 报案号 */
    private String rptNo;

    /** 费用项编码 */
    private String feeItemCode;


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


    /** 保单号 */
    private String policyNo;

    /** 分单号 */
    private String policyItemNo;

    /** 产品编码 */
    private String riskCode;

    /** 计划编码 */
    private String planCode;

    /** 责任明细编码 */
    private String dutyDetailCode;

    /** 责任编码 */
    private String dutyCode;

    /** 理算金额 */
    private BigDecimal calAmount;

    /** 拒赔金额 */
    private BigDecimal refusedAmount;

    /** 本次使用免赔额 */
    private BigDecimal deduUsed;

    /** 赔付比例 */
    private BigDecimal payRate;

    /** 有效起期 */
    private Date validStartDate;

    /** 有效止期 */
    private Date validEndDate;

    private List<ClaimCaseRuleInfo> claimCaseRuleInfoList;
}
