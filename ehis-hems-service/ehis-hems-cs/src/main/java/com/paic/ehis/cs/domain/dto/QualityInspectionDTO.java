package com.paic.ehis.cs.domain.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.QualityInspectionItemVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 前端页面入参对象
 */
@Data
public class QualityInspectionDTO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String inspectionHandlerId;
    /** 业务类型 */
    private String businessType;
    /**类型编码*/
    private String codeType;
    /**父类型编码*/
    private String pCodeType;
    /** 质检编号 */
    private String inspectionId;

    /** 工单编号 */
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

    private String itemStatus;

    private List<QualityInspectionItemVo> items;
    /**
     * 投诉受理信息
     */
    private ComplaintAcceptVo complaintAcceptVo;
    /**
     * 投诉处理信息
     */
    private ComplaintDealVo complaintDealVo;

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

    /** 质检确认模块查询入参 */

    /**案件首次结案开始日期 */
    private String firstEndCaseStartTime;

    /*案件首次结案结束日期 */
    private String firstEndCaseEndTime;

    /** 服务项目 */
    private String serviceItemCode;


}
