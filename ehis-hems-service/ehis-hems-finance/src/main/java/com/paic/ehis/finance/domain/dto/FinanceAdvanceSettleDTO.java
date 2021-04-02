package com.paic.ehis.finance.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FinanceAdvanceSettleDTO extends BaseEntity {
    /** 结算任务号 */
    @Excel(name = "结算任务号")
    private String settleTaskNo;

    /** 结算止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleEndDate;

    /*结算止期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settleStartDate;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 结算状态 (01-待确认、02-待结算、03-已结算) */
    @Excel(name = "结算状态 (01-待确认、02-待结算、03-已结算)")
    private String settleStatus;

    /** 批次总金额*/
    @Excel(name = "批次总金额")
    private BigDecimal batchTotal;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 出单公司名称 */
    @Excel(name = "出单公司名称")
    private String companyName;

    @Excel(name = "接口数据类型")
    private String pageType;

    /** 开始日期 */
    private String startDate;

    /** 结束日期 */
    private String endDate;

    private String status;


}
