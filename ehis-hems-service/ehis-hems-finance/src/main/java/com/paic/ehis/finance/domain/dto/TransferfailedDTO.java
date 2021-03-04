package com.paic.ehis.finance.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import java.util.Date;

@Data
public class TransferfailedDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 付款批次号 */
    @Excel(name = "案件批次号")
    private String batchNo;

    /** 支付日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转账开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payStartDate;

    /** 支付日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "转账结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payEndDate;

}
