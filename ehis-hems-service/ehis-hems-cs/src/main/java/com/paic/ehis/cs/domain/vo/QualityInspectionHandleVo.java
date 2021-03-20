package com.paic.ehis.cs.domain.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QualityInspectionHandleVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 质检处理流水号 */
    private String inspectionHandlerId;

    /** 质检编号 */
    private String inspectionId;

    private String businessType;

    /** 工单编号 */
    private String workOrderId;

    /** 质检评分 */
    private String score;

    /** 是否申诉 */
    private String appealFlag;

    /** 申诉理由 */
    private String appealReason;

    /** 状态 */
    private String status;

    /** 质检项目集合 */
    private List<QualityInspectionItemVo> items;

    /** 创建人 */
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新人 */
    private String updatedBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

    /** 工单号 */
    @Excel(name = "工单号")
    String workOrderNo;

    /** 服务项目 */
    @Excel(name = "服务项目",readConverterExp = "01=增值服务类,02=理赔类,03=其他,04=VIP门诊预约,05=特殊门诊直接结算服务,06=门诊直接结算,07=住院直接结算,08=门诊中医治疗直接结算,B00006=投诉,B00034=根因改善")
    String itemCode;

    /** 结案处理人 */
    @Excel(name = "结案处理人")
    String modifyBy;
}
