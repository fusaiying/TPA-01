package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 信息需求查询对象 demand_query
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class DemandQuery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String riskName;

    /** 同步日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date synchronizeTime;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;


    @Override
    public String toString() {
        return "DemandQuery{" +
                "riskName='" + riskName + '\'' +
                ", synchronizeTime=" + synchronizeTime +
                ", status='" + status + '\'' +
                '}';
    }
}
