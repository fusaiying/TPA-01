package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName CalBillSummaryVo
 * @Description: TODO 理算审核账单汇总信息
 * @Author 硠君
 * @Date create in 13:49 2021/1/26
 * @Version 1.0
 **/
@Data
public class CalBillSummaryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 账单金额 */
    private BigDecimal sumBillAmount;

    /** 医院折扣金额 */
    private BigDecimal sumHosDiscountAmount;

    /** 不合理金额 */
    private BigDecimal sumUnableAmount;

    /** 免赔额 */
    private BigDecimal sumDeduUsed;

    /** 理算金额(原始) */
    private BigDecimal calAmount;

    /** 拒赔金额 */
    private BigDecimal sumRefusedAmount;

    /** 理算金额(最终) */
    private BigDecimal sumPayAmount;
}


