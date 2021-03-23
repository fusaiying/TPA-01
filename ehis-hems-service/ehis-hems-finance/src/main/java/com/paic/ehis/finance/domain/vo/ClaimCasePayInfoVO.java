package com.paic.ehis.finance.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    //  对公支付接口所需数据——flint
    /** 交单来源/业务来源类型 */
    private String source;
    /** 承保机构 */
    private String organCode;
    /** 险种代码 */
    private List<String> riskCode;
    /** 彼此交单币种 */
    private String currency;
    /** 保单号 */
    private List<String> policyNo;
    /** 分单号 */
    private List<String> policyItemNo;


    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 案件状态 */
    @Excel(name = "案件状态",readConverterExp = "00=挂起,01=交单复核,02=交单退回,03=交单失败,04=交单完成,05=受理,30=问题件,97=撤件可申诉,98=撤件,06=录入,07=审核,31=协谈,32=调查,08=抽检,99=结案")
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
    @Excel(name = "支付状态",readConverterExp = "01=可支付,02=支付中,03=已支付,04=转账失败,05=退票")
    private String payStatus;

}
