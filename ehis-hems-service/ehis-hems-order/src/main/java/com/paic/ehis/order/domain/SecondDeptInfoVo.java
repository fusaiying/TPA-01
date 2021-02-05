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
public class SecondDeptInfoVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 二级科室名称 */
    private String SecondDeptName;

    /** 一级科室名称 */
    @Excel(name = "一级科室名称")
    private String firstDeptName;



}
