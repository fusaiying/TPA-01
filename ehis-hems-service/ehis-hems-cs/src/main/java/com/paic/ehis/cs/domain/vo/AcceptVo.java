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
    @Excel(name = "工单号")
    private String workOrderNo;
    private String workOrderNos;
    /** 质检编号 */
    private String inspectionId;
    private String workOrderId;
    /** 业务类型 */
    private String businessType;

    /** 拼接显示 业务-服务项目 */
    private String businessService;
    /** 受理渠道 */
    @Excel(name = "受理渠道",readConverterExp = "01=电话中心,02=在线客服,03=邮箱,04=网站,05=柜面,06=寿险,07=微信,08=监管,09=媒体,10=APP")
    private String channelCode;
    /** 服务项目 */
    @Excel(name = "服务项目",readConverterExp = "01=增值服务类,02=理赔类,03=其他,04=VIP门诊预约,05=特殊门诊直接结算服务,06=门诊直接结算,07=住院直接结算,08=门诊中医治疗直接结算,B00006=投诉,B00034=根因改善")
    private String serviceItem;
    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;
    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;
    /** 险种号 */
    @Excel(name = "险种代码")
    private String riskCode;
    /** 被保险人信息 */
    private String insuredPersonId;
    @Excel(name = "被保人")
    private String insuredName;
    private PersonInfo insuredPerson=new PersonInfo();
    /** 投保人信息 */
    private String holderPersonId;
    @Excel(name = "投保人")
    private String holderName;
    private PersonInfo holderPerson=new PersonInfo();
    /** 受理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "受理时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date acceptTime;
    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
    /** 受理人信息 */
   // @Excel(name = "受理人")
    private String acceptBy;
    @Excel(name = "受理人")
    private String createBy;
    /** 目前用不上,受理人信息以登录名称显示 */
    private UserInfo acceptUser=new UserInfo();
    /** 处理人信息 */
    @Excel(name = "处理人")
    private String updateBy;

    private String modifyUserId;
    /** 目前用不上,处理人信息以登录名称显示 */
    private UserInfo modifyUser=new UserInfo();

    /** 是否VIP */
    @Excel(name = "VIP标识",readConverterExp = "01=是,02=否")
    private String vipFlag;
    /** 优先级 */
    @Excel(name = "优先级",readConverterExp = "01=加急,02=普通")
    private String priorityLevel;
    /** 出单机构 */
    @Excel(name = "出单机构",readConverterExp = "0000001=上海分公司本部,00000011=上海分公司")
    private String organCode;

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

    //禁用工单拆查询获取操作
    private String flag;
    private Integer activationNum;

    private String[] workOrderNoMany;
}
