package com.paic.ehis.finance.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinanceAdvanceSettleVO implements Serializable {
    /** 结算任务号 */
    @Excel(name = "结算任务号")
    private String settleTaskNo;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 出单公司名称 */
    @Excel(name = "出单公司名称")
    private String companyName;

    /** 任务总金额 */
    @Excel(name = "任务总金额")
    private BigDecimal sumPayAmount;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 结算状态 (01-待确认、02-待结算、03-已结算) */
    @Excel(name = "结算状态 (01-待确认、02-待结算、03-已结算)")
    private String settleStatus;

    /** 结算止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleEndDate;

    /** 核销日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "核销日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date colDate;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batchNo;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 被保人客户号*/
    @Excel(name = "客户号")
    private String insuredNo;

    /**被保人姓名*/
    @Excel(name = "就诊人")
    private String name;

    /** 就诊医院编码 */
    @Excel(name = "就诊医院")
    private String hospitalCode;

    /** 治疗起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "就诊日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatmentStartDate;

    /** 账单号 */
    @Excel(name = "账单号")
    private String billNo;

    /** 发票号 */
    @Excel(name = "发票号")
    private String invoiceNo;

    /** 案件状态 */
    @Excel(name = "案件状态")
    private String caseStatus;

    /** 账单总金额 */
    @Excel(name = "账单总金额")
    private BigDecimal billAmount;

    /** 折后金额 */
    @Excel(name = "折后金额")
    private BigDecimal discountedAmount;

    /** 赔付结论 */
    @Excel(name = "赔付结论")
    private String payConclusion;

    /**交单日期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitDate;

    /** 结案日期 */
    @Excel(name = "结案日期")
    private Date endCaseTime;

    /** 交单来源 */
    @Excel(name = "交单来源")
    private String source;

    /**给付金额*/
    @Excel(name = "给付金额")
    private String payAmount;

    /** 支付日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payDate;

    /** 结算金额 */
    @Excel(name = "结算金额CNY")
    private BigDecimal advanceAmount;

    /** 垫付款备注 */
    @Excel(name = "备注")
    private String remark;

    /** 收款公司名称 */
    @Excel(name = "收款公司名称")
    private String receiptCompanyName;

    /** 收款金额 */
    @Excel(name = "收款金额")
    private BigDecimal receiptAmount;

    /** 收款日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiptDate;

    /** 收款备注 */
    @Excel(name = "收款备注")
    private String creditRemark;

    /** 操作人 */
    @Excel(name = "操作人")
    private String createBy;


    @Excel(name = "接口数据类型")
    private String pageType;
}
