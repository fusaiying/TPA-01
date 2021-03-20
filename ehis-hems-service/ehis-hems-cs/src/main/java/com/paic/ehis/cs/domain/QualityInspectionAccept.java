package com.paic.ehis.cs.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 质检受理对象 QualityInspectionAccept
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class QualityInspectionAccept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 质检流水号 */
    private Long inspectionId ;
    /** 工单号 */
    private String workOrderNo ;
    /** 质检结果;01-合格;
     02-不合格 */
    private String result ;
    /** 质检状态;01-待质检;
     02-质检中;
     03-质检完成; */
    private String status ;
    /** 创建人 */
    private String createdBy ;
    /** 创建时间 */
    private Date createdTime ;
    /** 更新人 */
    private String updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;

}
