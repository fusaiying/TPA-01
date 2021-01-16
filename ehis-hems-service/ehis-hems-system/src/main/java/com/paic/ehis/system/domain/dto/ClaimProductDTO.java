package com.paic.ehis.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ClaimProductDTO implements Serializable {
    private static final long serialVersionUID = 8022314889135323327L;
    /** 同步开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date synchronizeStartTime;

    /** 同步结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date synchronizeEndTime;

    /** 操作开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateStartTime;

    /** 操作结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateEndTime;


    /** 产品编码 */
    private String riskCode;
    /** 产品状态 */
    @Excel(name = "产品状态")
    private String riskStatus;
    /** 产品名称 */
    private String riskName;
    /** 操作人 */
    private String updateBy;
    /** 状态（Y-有效，N-无效） */
    private String status;
}
