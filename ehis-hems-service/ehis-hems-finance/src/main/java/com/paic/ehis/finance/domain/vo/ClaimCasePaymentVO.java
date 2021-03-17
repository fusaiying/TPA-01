package com.paic.ehis.finance.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ClaimCasePaymentVO implements Serializable {

    /** 批次号 */
    private String batchNo;

    /** 支付币种 */
    private String currency;

    /** 支付总金额 */
    private BigDecimal payAmount;

    /** 理赔总金额 */
    private BigDecimal calAmount;

    /** 开户行 */
    private String bank;

    /** 账户名 */
    private String bankName;

    /** 账户号 */
    private String bankNumber;

    /** 是否仅结算理赔责任 */
    private String claimFlag;

    /** 外币支付总金额 */
    private BigDecimal foreignPayAmount;

    /** 交易编码 */
    private String transactionCode;

    /** 国际收支填报人 */
    private String internationalCompletedBy;

    /** 国际收支填报人电话 */
    private String internationalCompletedPhone;

    /** 交易附言 */
    private String transactionPostscript;
}
