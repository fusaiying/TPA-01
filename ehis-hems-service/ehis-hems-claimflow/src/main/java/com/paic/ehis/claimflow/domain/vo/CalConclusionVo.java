package com.paic.ehis.claimflow.domain.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CalConclusionVo
 * @Description: TODO 理算审核赔付结论
 * @Author 硠君
 * @Date create in 14:51 2021/1/25
 * @Version 1.0
 **/
@Data
public class CalConclusionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 赔付ID */
    private Long calId;

    /** 报案号 */
    private String rptNo;

    /** 账单币种 */
    private String billCurrency;

    /** 理算金额 */
    private BigDecimal calAmount;

    /** 赔付金额 */
    private BigDecimal payAmount;

    /** 拒赔金额 */
    private BigDecimal refusedAmount;

    private String isAppeal;

    /** 追讨金额 */
    private BigDecimal debtAmount;

    /** 汇率 */
    private BigDecimal exchangeRate;

    /** 赔付金额（外币） */
    private BigDecimal payAmountForeign;

    /** 账单金额 */
    private BigDecimal sumBillAmount;

    /** 医院折扣金额 */
    private BigDecimal sumHosDiscountAmount;

    /** 本次支付差额 */
    private BigDecimal paymentDifference;
}
