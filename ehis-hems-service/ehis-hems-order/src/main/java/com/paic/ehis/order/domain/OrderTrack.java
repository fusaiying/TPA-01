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
public class OrderTrack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 工单编码 */
    @Excel(name = "工单编码")
    private String orderCode;

    /** 节点状态 */
    @Excel(name = "节点状态")
    private String nodeStatus;

    /** 详细状态 */
    @Excel(name = "详细状态")
    private String detailStatus;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String status;

    /** B端备注 */
    @Excel(name = "B端备注")
    private String remark_B;

    /** 原因 */
    @Excel(name = "原因")
    private String reason;


}
