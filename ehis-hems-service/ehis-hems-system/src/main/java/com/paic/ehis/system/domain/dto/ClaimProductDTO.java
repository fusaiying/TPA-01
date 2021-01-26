package com.paic.ehis.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ClaimProductDTO extends BaseEntity implements Serializable {
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
    /** 状态（Y-有效，N-无效） */
    private String status;

    /** 审核结论 */
    @Excel(name = "审核结论")
    private String examineConclusion;

    /** 产品性质 */
    @Excel(name = "产品性质")
    private String riskNature;

    /** 产品分类 */
    @Excel(name = "产品分类")
    private String riskClass;

    /** 是否历史 */
    @Excel(name = "是否历史")
    private String isHistory;

    /** 操作人 */
    private String updateBy;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 同步日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "同步日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date synchronizeTime;



}
