package com.paic.ehis.order.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * order_info(工单信息)对象 order_info
 * 
 * @author sino
 * @date 2021-01-26
 */
@Data
public class ServiceInfoVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务编码 */
    private String serviceCode;

    /** 医院信息 */
    @Excel(name = "医院信息")
    private List<HospitalInfoVo> hospitalInfos;

}
