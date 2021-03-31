package com.paic.ehis.finance.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName TpaSettleDetailInfo
 * @Description: TODO TPA服务费结算保单明细信息
 * @Author 硠君
 * @Date create in 10:59 2021/2/1
 * @Version 1.0
 **/
@Data
public class TpaSettleDetailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String detailId;

    private String settleTaskNo;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 投保人姓名 */
    @Excel(name = "投保人")
    private String appName;

    /** 被保人编码 */
    private String insuredNo;
    /** 被保人姓名 */
    @Excel(name = "被保人")
    private String name;

    /** 险种编码 */
    @Excel(name = "险种编码")
    private String riskCode;

    /** 险种名称 */
    @Excel(name = "险种")
    private String riskName;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date validStartDate;

    @Excel(name = "保费比例")
    private BigDecimal premiumRatio;

    /** 保费 */
    @Excel(name = "保费")
    private BigDecimal prem;

    /** 服务费 */
    @Excel(name = "服务费")
    private BigDecimal serviceAmount;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /* 结算类型 */
    private String settlementType ;

    /* 结算值 */
    private String settlementValue ;
}
