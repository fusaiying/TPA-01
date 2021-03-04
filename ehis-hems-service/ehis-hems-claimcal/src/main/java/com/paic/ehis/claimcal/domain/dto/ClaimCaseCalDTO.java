package com.paic.ehis.claimcal.domain.dto;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 案件赔付信息对象 claim_case_cal
 * 
 * @author sino
 * @date 2021-01-20
 */
@Data
public class ClaimCaseCalDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 账单币种 */
    @Excel(name = "账单币种")
    private String billCurrency;

    /** 理算金额 */
    @Excel(name = "理算金额")
    private BigDecimal calAmount;

    /** 赔付金额 */
    @Excel(name = "赔付金额")
    private BigDecimal payAmount;

    /** 拒赔金额 */
    @Excel(name = "拒赔金额")
    private BigDecimal refusedAmount;

    /** 追讨金额 */
    @Excel(name = "追讨金额")
    private BigDecimal debtAmount;

    /** 汇率 */
    @Excel(name = "汇率")
    private BigDecimal exchangeRate;

    /** 赔付金额（外币） */
    @Excel(name = "赔付金额", readConverterExp = "外=币")
    private BigDecimal payAmountForeign;
}
