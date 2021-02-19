package com.paic.ehis.system.api.domain.dto;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 案件赔付账单明细对象 claim_case_cal_bill
 * 
 * @author sino
 * @date 2021-01-20
 */
@Data
public class ClaimCaseCalBillDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 账单ID */
    @Excel(name = "账单ID")
    private Long billId;

    /** 理算金额 */
    @Excel(name = "理算金额")
    private BigDecimal calAmount;

    /** 赔付金额 */
    @Excel(name = "理算金额")
    private BigDecimal payAmount;

    /** 拒赔金额 */
    @Excel(name = "拒赔金额")
    private BigDecimal refusedAmount;

    /** 本次使用免赔额 */
    @Excel(name = "本次使用免赔额")
    private BigDecimal deduUsed;

    /** 赔付结论 */
    @Excel(name = "本次使用免赔额")
    private String payConclusion;
}
