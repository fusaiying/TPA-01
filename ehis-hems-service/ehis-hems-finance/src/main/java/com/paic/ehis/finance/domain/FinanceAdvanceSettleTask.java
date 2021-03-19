package com.paic.ehis.finance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 代垫费结算任务对象 finance_advance_settle_task
 * 
 * @author sino
 * @date 2021-01-30
 */
@Data
public class FinanceAdvanceSettleTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 结算任务号 */
    @Excel(name = "结算任务号")
    private String settleTaskNo;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 结算总金额 */
    @Excel(name = "结算总金额")
    private BigDecimal advanceAmount;

    /** 结算止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleEndDate;

    /*上一次结算止期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleStartDate;

    /** 结算状态 (01-待确认、02-待结算、03-已结算) */
    @Excel(name = "结算状态 (01-待确认、01-待结算、02-已结算)")
    private String settleStatus;

    /** 核销日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "核销日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date colDate;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 登录机构编码 */
    @Excel(name = "登录机构编码")
    private String deptCode;

}
