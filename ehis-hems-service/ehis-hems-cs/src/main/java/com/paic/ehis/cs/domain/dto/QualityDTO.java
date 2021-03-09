package com.paic.ehis.cs.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data
public class QualityDTO {

    /** 结案日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 质检日期*/
    private Date updatedTime;

    /** 出单机构 */
    @Excel(name = "出单机构")
    private String organCode;

    /** 质检人*/
    private String updatedBy;

    /** 服务项目*/
    private String itemCode;

    /** 质检状态*/
    private String status;

    /** 质检结果*/
    private String result;
}
