package com.paic.ehis.claimcal.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SyncExchangeRate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 汇率ID */
    private long rateId;

    /** 之前币种 */
    private String beforeMoney;

    /** 之后币种 */
    private String afterMoney;

    /** 汇率 */
    private BigDecimal parities;

    /** 汇率日期 */
    private Date dateConvert;

    /** 数据状态 */
    private String status;
}
