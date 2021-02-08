package com.paic.ehis.cs.domain.vo;

import lombok.Data;

@Data
public class ComplaintDealVo {

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

}
