package com.paic.ehis.finance.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 对公支付-案件信息
 */
@Data
public class ClaimCasePayInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 批次号 */
    private String batchNo;

    /** 被保人客户号 */
    private String insuredNo;
    /** 交单来源/业务来源类型 */
    private String source;
    /* 部门 */
    /** 交单机构编码/业务受理机构 */
    private String organCode;
    /** 险种代码 */
    private String riskCode;
    /** 机构代码 */


    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;

    /** 理赔金额 */
    @Excel(name = "理赔金额")
    private BigDecimal payAmount;

    /** 就诊日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "就诊日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatmentDate;

    /** 出单公司 */
    @Excel(name = "出单公司")
    private String companyName;

    /** 账单金额 */
    @Excel(name = "账单金额")
    private BigDecimal billAmount;

    /** 折后金额 */
    @Excel(name = "折后金额")
    private BigDecimal discountedAmount;

    /** 先期给付 */
    @Excel(name = "先期给付")
    private BigDecimal advancePayment;

    /** 自付额 */
    @Excel(name = "自付额")
    private BigDecimal copay;

    /** 追讨金额 */
    @Excel(name = "追讨金额")
    private BigDecimal debtAmount;

    /** 借款金额 */
    @Excel(name = "借款金额")
    private BigDecimal borrowAmount;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String payStatus;

}
