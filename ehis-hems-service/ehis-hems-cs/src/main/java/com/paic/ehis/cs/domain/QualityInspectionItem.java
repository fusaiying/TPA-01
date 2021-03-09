package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 质检项目 对象 quality_inspection_item
 * 
 * @author sino
 * @date 2021-02-25
 */
@Data
public class QualityInspectionItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水编号 */
    private String itemId;

    /** 质检编号 */
    @Excel(name = "质检编号")
    private String inspectionId;

    /** 组 */
    @Excel(name = "组")
    private String itemType;

    /** 名 */
    @Excel(name = "名")
    private String itemKey;

    /** 值 */
    @Excel(name = "值")
    private String value;

    /** 说明 */
    @Excel(name = "说明")
    private String itemRemark;

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
