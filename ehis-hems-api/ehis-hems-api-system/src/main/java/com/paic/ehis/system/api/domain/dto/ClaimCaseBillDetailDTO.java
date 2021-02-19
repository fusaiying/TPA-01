package com.paic.ehis.system.api.domain.dto;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 案件账单费用项明细对象 claim_case_bill_detail
 * 
 * @author sino
 * @date 2021-01-21
 */
@Data
public class ClaimCaseBillDetailDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账单明细ID */
    private Long detailId;

    /** 账单ID */
    @Excel(name = "账单ID")
    private Long billId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 费用项编码 */
    @Excel(name = "费用项编码")
    private String feeItemCode;

    /** 费用项名称 */
    @Excel(name = "费用项名称")
    private String feeItemName;

    /** 费用项账单金额 */
    @Excel(name = "费用项账单金额")
    private BigDecimal billDetailAmount;

    /** 自费金额 */
    @Excel(name = "自费金额")
    private BigDecimal selfAmount;

    /** 部分自费金额 */
    @Excel(name = "部分自费金额")
    private BigDecimal partSelfAmount;

    /** 费用项先期给付 */
    @Excel(name = "费用项先期给付")
    private BigDecimal advancePayment;

    /** 费用项自付额 */
    @Excel(name = "费用项自付额")
    private BigDecimal billDetailCopay;

    /** 费用项医院折扣金额 */
    @Excel(name = "费用项医院折扣金额")
    private BigDecimal hosDiscountAmount;

    /** 不合理金额 */
    @Excel(name = "不合理金额")
    private BigDecimal unableAmount;

    /** 就诊次数 */
    @Excel(name = "就诊次数")
    private Long visNumber;
}
