package com.paic.ehis.system.api.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by xicc on 2021/3/30
 */
@Data
public class ClaimCaseRuleInfo {

    /** 赔付规则ID */
    private Long calRuleId;

    /** 报案号 */
    private String rptNo;

    /** 赔付费用明细ID */
    private Long calItemId;

    /** 规则号 */
    private String ruleNo;

    /** 本次使用值 */
    private BigDecimal usedValue;

    /** 本次计算前剩余值 */
    private BigDecimal surplusValue;

}
