package com.paic.ehis.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransferfailedVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 付款批次号 */
    @Excel(name = "案件批次号")
    private String batchNo;

    /*报案号*/
    @Excel(name="报案号")
    private String rptNo;

    /** 账号 */
    @Excel(name = "账号")
    private String accNo;

    /** 账户名 */
    @Excel(name = "账户名")
    private String accName;

    /** 开户行编码 */
    @Excel(name = "开户行编码")
    private String payeeBank;

    /** 转账金额 */
    @Excel(name = "转账金额")
    private BigDecimal sumPayAmount;

    /** 支付状态 (01-待支付、02-支付中、03-支付成功、04-支付失败、05-退票) */
    @Excel(name = "支付状态 (01-待支付、02-支付中、03-支付成功、04-支付失败、05-退票)")
    private String payStatus;

    /** 支付日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转账日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /** 转账失败日期（退票） */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转账失败日期", readConverterExp = "退票")
    private Date payFailDate;

    /** 收据号 */
    @Excel(name = "收据号")
    private String receiptNo;

    /** 转账失败原因 */
    @Excel(name = "转账失败原因")
    private String payFailReason;

    /** 状态*/
    @Excel(name = "状态")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;
}
