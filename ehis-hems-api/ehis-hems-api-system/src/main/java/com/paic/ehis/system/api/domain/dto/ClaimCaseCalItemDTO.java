package com.paic.ehis.system.api.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 案件赔付费用项明细对象 claim_case_cal_item
 * 
 * @author sino
 * @date 2021-01-20
 */
@Data
public class ClaimCaseCalItemDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 账单明细ID */
    @Excel(name = "账单明细ID")
    private Long  billDetailId;

    /** 账单ID */
    @Excel(name = "账单ID")
    private Long billId;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String riskCode;

    /** 计划编码 */
    @Excel(name = "计划编码")
    private String planCode;

    /** 责任明细编码 */
    @Excel(name = "责任明细编码")
    private String dutyDetailCode;

    /** 责任编码 */
    @Excel(name = "责任编码")
    private String dutyCode;

    /** 费用项编码 */
    @Excel(name = "费用项编码")
    private String feeItemCode;

    /** 理算金额 */
    @Excel(name = "理算金额")
    private BigDecimal calAmount;

    /** 拒赔金额 */
    @Excel(name = "拒赔金额")
    private BigDecimal refusedAmount;

    /** 本次使用免赔额 */
    @Excel(name = "本次使用免赔额")
    private BigDecimal deduUsed;

    /** 赔付比例 */
    @Excel(name = "赔付比例")
    private BigDecimal payRate;
}
