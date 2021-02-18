package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.domain.UserInfo;
import lombok.Data;

import java.util.Date;

/**
 * 信息需求查询对象 demand_query
 * 
 * @author sino
 * @date 2021-01-14
 */
@Data
public class AcceptVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 工单号
     */
    private String workOrderNo;
    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 受理渠道
     */
    private String channel;

    /**
     * 服务项目
     */
    private String serviceItem;

    /**
     * 业务-服务项目
     */
    private String businessService;

    @Excel(name = "保单号")
    private String policyNo;

    @Excel(name = "分单号")
    private String policyItemNo;

    @Excel(name = "险种号")
    private String riskCode;

    private PersonInfo insuredPerson;

    private PersonInfo holderPerson;

    private UserInfo acceptUser;

    private UserInfo modifyUser;

    private String vipFlag;

    private String priorityLevel;

    private String organCode;

    private String channelCode;

    private String status;


    private PersonInfo callPerson;

    private String callRelationBy;

    private PersonInfo contactsPerson;

    private String contactsRelationBy;

    private String callCenterId;

    private String email;

    private String content;

    private PersonInfo complaintPerson;

    private String attachmentFlag;
    /**
     * 投诉和预约中预约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date complaintTime;

    /**
     * 门诊直接结算服务项目
     */
    private String outpatientSettlement;
    /**
     * 就诊类型
     */
    private String visitType;
    /**
     * 疾病名称
     */
    private String disease;
    /**
     * 症状或体征
     */
    private String symptomsSigns;
    /**
     * 本次疾病/症状起病时间
     */
    private String symptomTimes;
    /**
     * 时间单位
     */
    private String timeUnit;
    /**
     * 是否意外
     */
    private String accidentFlag;
    /**
     * 意外原因
     */
    private String accidentReason;
    /**
     * 是否持有效证件
     */
    private String validCertificate;
    /**
     * 是否持有直结卡
     */
    private String settlementCard;
    /**
     * 预计住院天数
     */
    private String hospitalDays;
    /**
     * 医疗机构
     */
    private String medicalInstitution;
    /**
     * 科室
     */
    private String department;
    /**
     * 床位
     */
    private String bunk;
    /**
     * 医院赔付比例
     */
    private String compensationRatio;
    /**
     * 业务处理情况
     */
    private String businessProcess;
    /**
     * 客户反馈
     */
    private String customerFeedback;
    /**
     * 结案类型
     */
    private String closeType;
    /**
     * 安抚或通融发生费用成本
     */
    private String costsIncurred;

    /** 受理日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "受理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date acceptTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 结案日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 是否再次来电提醒记录
     */
    private Boolean isRedWord=false;




}
