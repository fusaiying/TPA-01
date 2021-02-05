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
public class ServcieInfoVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务编码 */
    private String serviceCode;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String serviceName;

}
