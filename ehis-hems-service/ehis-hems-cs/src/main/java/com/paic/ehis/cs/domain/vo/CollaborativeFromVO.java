package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.domain.UserInfo;
import lombok.Data;

import java.util.Date;

/**
 * 返回前端对象
 *
 * by Louis
 */
@Data
public class CollaborativeFromVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 协办流水号 */
    private Long collaborativeId;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private String fromUserId;

    /** 协办方UM账号 */
    @Excel(name = "协办方UM账号")
    private String umCode;

    /** 征求处理意见 */
    @Excel(name = "征求处理意见")
    private String solicitOpinion;

    /** 有无附件 */
    @Excel(name = "有无附件")
    private String attachmentFlag;

    @Excel(name = "处理状态")
    private String handleState;

    /** 状态 协办中；已经协办； */
    @Excel(name = "状态 协办中；已经协办；")
    private String status;

    @Excel(name="处理意见")
    private String opinion;

    @Excel(name="投诉是否成立")
    private String validFlag;

    @Excel(name="投诉不成立理由")
    private String nonReason;


    /** 处理方案 */
    @Excel(name = "处理方案")
    private String treatmentPlan;

    /** 处理依据 */
    @Excel(name = "处理依据")
    private String treatmentBasis;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    private String itemCode;
    private String organCode;
    private String businessType;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;


    @Excel(name = "时间差")
    private String processingTime;
}
