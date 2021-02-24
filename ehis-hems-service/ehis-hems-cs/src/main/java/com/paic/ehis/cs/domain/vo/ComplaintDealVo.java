package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ComplaintDealVo extends BaseEntity {

    /** 处理流水号 */
    private Long handleId;

    /** 处理类型 处理；取消；转办；协办；质检 */
    @Excel(name = "处理类型 处理；取消；转办；协办；质检")
    private String handleType;

    /** 工单号 */
    @Excel(name = "工单号")
    private String workOrderNo;

    /** 标志 */
   // @Excel(name = "标志")
   // private String flag;

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
   // @Excel(name="业务说明")
    //private String remark;
    /*一级投诉分类*/
    private String level1;

    /*二级投诉分类*/
    private String level2;

    /*是否计件*/
    private String pieceworkFlag;

    /*投诉状态*/
    private String complaintStatus;

    /*投诉是否成立*/
    private String complaintTenable;

    /*投诉不成立理由*/
    private String faseReason;

    /*重复投诉*/
    private String repeatedComplaint;

    /*一级投诉原因*/
    private String reason1;

    /*二级投诉原因*/
    private String reason2;

    /*三级投诉原因*/
    private String reason3;

    /*投诉环节(报保监)*/
    private String complaintLink;

    /*投诉问题(报保监)*/
    private String complaintQuestion;

    /*行协调解或外部鉴定状态*/
    private String outsideState;

   /*险种类型*/
    private String riskType;

    /*营销渠道*/
    private String marketChannel;

    /*投诉业务类别*/
    private String complaintCategory;

    /*投诉根因部门*/
    private String rootDepartment;

    /*致诉原因*/
    private String actionCause;

    /*处理进展*/
    private String treatmentProgress;

   /*处理结果*/
    private String treatmentResult;

    /*客户反馈*/
    private String customerFeedback;

    /*根因改善*/
    private String rootImprovement;

    /*投诉损失*/
    private String actPromptly;

    /*改善措施*/
    private String improvementMeasures;

    /*业务处理情况*/
    private String businessProcess;

    private String sign;

    private Long CollaborativeId;
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
}
