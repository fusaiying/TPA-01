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
public class HospitalInfoVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 医院编码 */
    private String hospitalCode;

    /** 医院名称 */
    @Excel(name = "名称")
    private String hospitalName;

    /** 一级科室数据 */
    @Excel(name = "一级科室数据")
    private List<FirstDeptInfoVo> firstdeptInfos;

}
