package com.paic.ehis.finance.domain.dto;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 对公支付页面-查询
 */
@Data
public class ClaimCasePayDTO extends BaseEntity {

    /** 批次号 */
    private String batchNo;

    /** 医院编码 */
    private String hospitalCode;

    /** 是否申诉 */
    private String complainStatus;

    /** 开始日期 */
    private Date startDate;

    /** 结束日期 */
    private Date endDate;

    /** 交单机构 */
    private String organCode;
}
