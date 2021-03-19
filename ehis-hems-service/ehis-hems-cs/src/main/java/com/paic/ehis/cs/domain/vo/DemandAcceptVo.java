package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.cs.domain.EditInfo;
import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.domain.UserInfo;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
public class DemandAcceptVo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    //电话中心业务流水号
    private String callCenterId;
    /** 工单编号 */
    @Excel(name="工单号")
    private String workOrderNo;

    /** 业务类型 业务类型：区分信息需求，投诉，预约等类型；码值待定义； */
    private String businessType;
    /** 拼接显示 业务-服务项目 */

    private String businessService;
    @Excel(name = "受理渠道 信息需求受理渠道")
    private String channelCode;

    /** 服务项目 */
    @Excel(name = "服务项目")
    private String itemCode;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 险种号 */
    @Excel(name = "险种号")
    private String riskCode;

    /**被保人客户号*/
    @Excel(name = "被保人客户号")
    private String insuredNo;

    /** 被保人 */
    @Excel(name = "被保人")
    private String insuredName;

    /** 投保人 */
    @Excel(name = "投保人客户号")
    private String holderNo;
    /** 投保人 */
    @Excel(name = "投保人")
    private String holderName;

    /** 受理人 */
    @Excel(name = "受理人")
    private String acceptBy;

    /** 受理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "受理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date acceptTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /** VIP标识 */
    @Excel(name = "VIP标识")
    private String vipFlag;

    /** 出单机构 */
    @Excel(name = "出单机构")
    private String organCode;

    /** 状态 每个业务流程中的状态；如：待处理、处理中（&协办表=协办中）、处理完成、质检中、质检完成；
     信息需求公共查询中状态：01-待处理；02-处理中；03-已处理；04-已完成；05-已取消； */
    @Excel(name = "状态 每个业务流程中的状态；如：待处理、处理中", readConverterExp = "&=协办表=协办中")
    private String status;

    /** 处理人 仅争对获取操作的人 */
    @Excel(name = "处理人 仅争对获取操作的人")
    private String modifyBy;

    /** E-MAIL */
    @Excel(name = "E-MAIL")
    private String email;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priorityLevel;

    /** 来电人与被保人关系 */
    @Excel(name = "来电人与被保人关系")
    private String callRelationBy;

    /** 联系人与被保人关系 */
    @Excel(name = "联系人与被保人关系")
    private String contactsRelationBy;

    /** 来电人编号 person_info中的编号 */
    @Excel(name = "来电人编号 person_info中的编号")
    private String callPersonId;

    /** 联系人编号 person_info中的编号 */
    @Excel(name = "联系人编号 person_info中的编号")
    private String contactsPersonId;

    /** 投诉人编号 person_info中的编号 */
    @Excel(name = "投诉人编号 person_info中的编号")
    private String complaintPersonId;

    /** 来电人*/
    private PersonInfo callPerson;

    /** 联系人*/
    private PersonInfo contactsPerson;

//    /** 原操作人*/
//    private UserInfo OperatorLast;
//
//    /** 修改人 */
//    private UserInfo Reviser;

    /** 是否涉及银行转账*/
    private String bankTransfer;

    /** 开户行*/
    private String bankName;

    /** 开户地*/
    private String bankLocation;

    /** 账号*/
    private String accountNumber;

    /** 户名*/
    private String bankHolder;

    /** 有无附件*/
    private String attachmentFlag;
    /** 业务内容*/
    private String content;
    /** 来电人姓名*/
    private String callName;
    /** 来电人号码*/
    private String callMobilePhone;
    /** 联系人性别*/
    private String contactsSex;
    /** 联系人姓名*/
    private String contactsName;
    /** 联系人语言*/
    private String contactsLanguage;
    /** 联系人电话*/
    private String contactsMobilePhone;
    /** 联系人国家*/
    private String contactsCountry;
    /** 联系人区号*/
    private String contactsQuhao;
    /** 联系人号码*/
    private String contactsNumber;
    /** 联系人分机号*/
    private String contactsSecondNumber;
    /** 联系人国家*/
    private String officeCountry;
    /** 联系人区号*/
    private String officeQuhao;
    /** 联系人号码*/
    private String officeNumber;
    /** 联系人分机号*/
    private String officeSecondNumber;
    /**
     * 修改原因
     */
    private String  editReason;
    /**
     * 修改说明
     */
    private String editRemark;
    private EditInfo editInfo;

    //征求处理意见
    private String solicitOpinion;
    //协办方UM帐号
    private List<UmCode> umCode;

    private String reason;


    private Long CollaborativeId;
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

    private String[] alterId;
    /** 是否再次来电提醒记录 */
    private int isRedWord;

    /** 原处理人 */
    @Excel(name = "原处理人")
    private String oldmodifyBy;

    /** 修改时间 */
    @Excel(name = "修改时间")
    private String changeTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createBy;

    /** 最后一次修改人 */
    @Excel(name = "最后一次修改人")
    private String updateBy;
}
