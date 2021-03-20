package com.paic.ehis.cs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
/**
 * 质检处理 对象 quality_inspection_handle
 * 
 * @author sino
 * @date 2021-02-25
 */
@Data
public class QualityInspectionHandle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String inspectionHandlerId;

    /** 质检编号 */
    private String inspectionId;

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String workOrderNo;

    /** 质检评分 */
    @Excel(name = "质检评分")
    private String score;

    /** 是否申诉 */
    @Excel(name = "是否申诉")
    private String appealFlag;

    /** 申诉理由 */
    @Excel(name = "申诉理由")
    private String appealReason;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

}
