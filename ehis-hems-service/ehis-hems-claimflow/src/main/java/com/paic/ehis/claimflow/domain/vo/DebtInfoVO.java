package com.paic.ehis.claimflow.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 追讨信息
 */
@Data
public class DebtInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 批次号 */
    private String batchNo;

    /** 交单来源 */
    @Excel(name = "交单来源",readConverterExp="01=PBW-在线理赔,02=PBW-E结算,03=机构交单,04=寿险app")
    private String source;

    /** 就诊医院 */
    @Excel(name = "就诊医院")
    private String hospitalCode;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String insuredName;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idNo;

    /** 就诊日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "就诊日期",dateFormat = "yyyy-MM-dd")
    private Date treatmentEndDate;

    /** 投保人 */
    @Excel(name = "投保人")
    private String appntName;

    /** 保单号 */
    @Excel(name = "保单号")
    private String contNo;

    /** 追讨金额 */
    @Excel(name = "追讨金额")
    private BigDecimal debtAmount;

    /** 收款金额 */
    @Excel(name = "收款金额")
    private BigDecimal colAmount;

    /** 剩余金额 */
    @Excel(name = "剩余金额")
    private BigDecimal residualAmount;

    /** 结案日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结案日期",dateFormat = "yyyy-MM-dd")
    private Date endCaseTime;

    /** 白名单标记 */
    @Excel(name = "白名单标记",readConverterExp = "01=是,02=否")
    private String whiteStatus;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "01=已付款,02=未付款")
    private String colStatus;

}
