package com.paic.ehis.claimflow.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 案件追讨白名单对象 claim_debt_whitelist
 *
 * @author sino
 * @date 2021-01-20
 */
@Data
public class ClaimDebtWhitelist extends BaseEntity
{

    private static final long serialVersionUID = 1L;

    /** 追讨白名单ID */
    private Long debtWhitelistId;

    /** 被保人客户号 */
    @Excel(name = "被保人客户号")
    private String insuredNo;

    /** 等级 */
    @Excel(name = "等级")
    private String level;

    /** 追缴通知标记 */
    @Excel(name = "追缴通知标记")
    private String recMessageFlag;

    /** 追讨金额上限 */
    @Excel(name = "追讨金额上限")
    private BigDecimal debtAmountUp;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String  idType;

    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;

    /*性别*/
    @Excel(name = "性别")
    private String sex;
    /*生日*/
    @Excel(name = "生日")
    private Date birthday;

    /** 剩余金额 */
    @Excel(name = "剩余金额")
    private BigDecimal residualAmount;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;
}
