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
public class AcceptVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单号 */
    private String workOrderNo;
    private String workOrderNos;
    private String workOrderId;
    /** 业务类型 */
    private String businessType;
    /** 服务项目 */
    private String serviceItem;
    /** 拼接显示 业务-服务项目 */
    private String businessService;
    /** 保单号 */
    private String policyNo;
    /** 分单号 */
    private String policyItemNo;
    /** 险种号 */
    private String riskCode;
    /** 被保险人信息 */
    private String insuredPersonId;
    private PersonInfo insuredPerson=new PersonInfo();
    /** 投保人信息 */
    private String holderPersonId;
    private PersonInfo holderPerson=new PersonInfo();
    /** 受理人信息 */
    private String acceptUserId;
    /** 目前用不上,受理人信息以登录名称显示 */
    private UserInfo acceptUser=new UserInfo();
    /** 处理人信息 */
    private String modifyUserId;
    /** 目前用不上,处理人信息以登录名称显示 */
    private UserInfo modifyUser=new UserInfo();
    /** 受理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acceptTime;
    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    /** 是否VIP */
    private String vipFlag;
    /** 优先级 */
    private String priorityLevel;
    /** 出单机构 */
    private String organCode;
    /** 受理渠道 */
    private String channelCode;
    /** 流程状态 */
    private String status;
    /** 工单个数 */
    private Integer count;

    /** 响应时间 */

    /** 响应内容 */

    /** 来电人 */
    private String callPersonId;
    private PersonInfo callPerson=new PersonInfo();
    /** 来电人与被保人关系 */
    private String callRelationBy;
    /** 联系人 */
    private String contactsPersonId;
    private PersonInfo contactsPerson=new PersonInfo();
    /** 联系人与被保人关系 */
    private String contactsRelationBy;
    /** E-MAIL */
    private String email;
    /** 业务内容/投诉内容 */
    private String content;
    /** 有无附件 */
    private String attachmentFlag;
    /** 电话中心业务流水号 */
    private String callCenterId;

    /** 投诉人 */
    private String complaintPersonId;
    private PersonInfo complaintPerson=new PersonInfo();
    /** 是否已劝解 */
    private String persuasionFlag;
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
     * 安抚或通融发生费用成本
     */
    private String costsIncurred;



    /**
     * 结案类型
     */
    private String closeType;

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
