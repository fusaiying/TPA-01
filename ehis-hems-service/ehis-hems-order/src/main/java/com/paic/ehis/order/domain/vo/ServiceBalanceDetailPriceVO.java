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
public class ServiceBalanceDetailPriceVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 供应商编码 */
    @Excel(name = "供应商名称")
    private String supplierCode;

    /** 供应商项目名称 */
    @Excel(name = "供应商项目名称")
    private String serviceCode;

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
