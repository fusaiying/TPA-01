package com.paic.ehis.order.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * service_balance_detail(服务结算明细信息)对象 service_balance_detail
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
@Data
public class ServiceBalanceDetailCaseVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String orderCode;

    /** 险种 */
    @Excel(name = "险种")
    private String riskName;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 供应商项目名称 */
    @Excel(name = "供应商项目名称")
    private String supplierServiceName;

    /** 投保人 */
    @Excel(name = "投保人")
    private String appntName;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String custName;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idNo;

    /** 服务日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "服务日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date serviceDate;

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
