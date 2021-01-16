package com.paic.ehis.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductDTO   implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 同步开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date synchronizeStartTime;

    /** 同步结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date synchronizeEndTime;

    /** 操作时间 */
    @Excel(name = "操作结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateStartTime;
    @Excel(name = "操作结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateEndTime;

    /** 产品编码 */
    private String riskCode;
    /** 产品名称 */
    private String riskName;

    /** 状态 */
    private String riskStatus;
    /** 操作人 */
    private String updateBy;
}
