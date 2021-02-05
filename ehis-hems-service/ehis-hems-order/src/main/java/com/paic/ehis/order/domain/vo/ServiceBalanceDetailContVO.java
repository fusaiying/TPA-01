package com.paic.ehis.order.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * service_balance_detail(服务结算明细信息)对象 service_balance_detail
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
@Data
public class ServiceBalanceDetailContVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 险种 */
    @Excel(name = "险种")
    private String riskCode;

    /** 承保人数 */
    @Excel(name = "承保人数")
    private Long insuredNum;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 服务费 */
    @Excel(name = "服务费")
    private BigDecimal amount;

    /** 实际服务费用 */
    @Excel(name = "实际服务费用")
    private BigDecimal actualAmount;

    /** 原因 */
    @Excel(name = "编辑原因")
    private String reason;

}
