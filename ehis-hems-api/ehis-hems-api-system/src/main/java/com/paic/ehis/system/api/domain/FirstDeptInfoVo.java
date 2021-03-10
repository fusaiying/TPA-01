package com.paic.ehis.system.api.domain;

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
public class FirstDeptInfoVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 医院编码 */
    private String hospitalCode;

    /** 一级科室名称 */
    @Excel(name = "一级科室名称")
    private String firstDeptName;

    /** 二级科室数据 */
    @Excel(name = "二级科室数据")
    private List<SecondDeptInfoVo> secondDeptInfos;

    /** 一级科室名称(B端) */
    private String deptName;

}
