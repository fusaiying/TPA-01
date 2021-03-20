package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 质检项目 对象 quality_inspection_item
 *
 */
@Data
public class QualityInspectionItemVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 流水编号 */
    private String itemId;
    /** 质检编号 */
    private String inspectionHandlerId;
    private String inspectionId;

    private String classType;

    private String className;

    private String itemType;

    private String itemName;

    private String itemValue;

    private String itemRemark;

    private String status;


}
