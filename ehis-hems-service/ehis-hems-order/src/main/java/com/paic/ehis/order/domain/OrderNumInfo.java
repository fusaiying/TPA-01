package com.paic.ehis.order.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * order_info(工单信息)对象 order_info
 * 
 * @author sino
 * @date 2021-01-26
 */
@Data
public class OrderNumInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 接单次数 */
    private int receivedTimes;

    /** 进行中次数 */
    @Excel(name = "进行中次数")
    private int progressTimes;

    /** 完成次数 */
    @Excel(name = "完成次数")
    private int completionTimes;

}
