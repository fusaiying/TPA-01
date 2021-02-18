package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 信息需求查询对象 demand_query
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class WorkOrderAccept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Excel(name = "工单号")
    private String workOrderNo;

    @Excel(name = "保单号")
    private String policyNo;

    /** 同步日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "受理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date acceptTime;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

}
