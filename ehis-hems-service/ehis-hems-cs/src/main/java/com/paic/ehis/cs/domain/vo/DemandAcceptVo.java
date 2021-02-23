package com.paic.ehis.cs.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.cs.domain.EditInfo;
import com.paic.ehis.cs.domain.PersonInfo;
import com.paic.ehis.cs.domain.UserInfo;
import lombok.Data;


import java.util.Date;


public class DemandAcceptVo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    //电话中心业务流水号
    private String callCenterId;
    /** 工单编号 */
    private String workOrderNo;

    /** 业务类型 业务类型：区分信息需求，投诉，预约等类型；码值待定义； */
    @Excel(name = "业务类型 业务类型：区分信息需求，投诉，预约等类型；码值待定义；")
    private String businessType;

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

    /** 被保人 */
    @Excel(name = "被保人")
    private String insuredName;

    /** 投保人 */
    @Excel(name = "投保人")
    private String holderName;

    /** 受理人 */
    @Excel(name = "受理人")
    private String acceptBy;

    /** 受理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "受理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acceptTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
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

    /** 原操作人*/
    private UserInfo OperatorLast;

    /** 修改人 */
    private UserInfo Reviser;

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
    private String umCode;

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


    public String getCallCenterId() {
        return callCenterId;
    }

    public void setCallCenterId(String callCenterId) {
        this.callCenterId = callCenterId;
    }

    public String getWorkOrderNo() {
        return workOrderNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyItemNo() {
        return policyItemNo;
    }

    public void setPolicyItemNo(String policyItemNo) {
        this.policyItemNo = policyItemNo;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getAcceptBy() {
        return acceptBy;
    }

    public void setAcceptBy(String acceptBy) {
        this.acceptBy = acceptBy;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(String vipFlag) {
        this.vipFlag = vipFlag;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getCallRelationBy() {
        return callRelationBy;
    }

    public void setCallRelationBy(String callRelationBy) {
        this.callRelationBy = callRelationBy;
    }

    public String getContactsRelationBy() {
        return contactsRelationBy;
    }

    public void setContactsRelationBy(String contactsRelationBy) {
        this.contactsRelationBy = contactsRelationBy;
    }

    public String getCallPersonId() {
        return callPersonId;
    }

    public void setCallPersonId(String callPersonId) {
        this.callPersonId = callPersonId;
    }

    public String getContactsPersonId() {
        return contactsPersonId;
    }

    public void setContactsPersonId(String contactsPersonId) {
        this.contactsPersonId = contactsPersonId;
    }

    public String getComplaintPersonId() {
        return complaintPersonId;
    }

    public void setComplaintPersonId(String complaintPersonId) {
        this.complaintPersonId = complaintPersonId;
    }

    public PersonInfo getCallPerson() {
        return callPerson;
    }

    public void setCallPerson(PersonInfo callPerson) {
        this.callPerson = callPerson;
    }

    public PersonInfo getContactsPerson() {
        return contactsPerson;
    }

    public void setContactsPerson(PersonInfo contactsPerson) {
        this.contactsPerson = contactsPerson;
    }

    public UserInfo getOperatorLast() {
        return OperatorLast;
    }

    public void setOperatorLast(UserInfo operatorLast) {
        OperatorLast = operatorLast;
    }

    public UserInfo getReviser() {
        return Reviser;
    }

    public void setReviser(UserInfo reviser) {
        Reviser = reviser;
    }

    public String getBankTransfer() {
        return bankTransfer;
    }

    public void setBankTransfer(String bankTransfer) {
        this.bankTransfer = bankTransfer;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLocation() {
        return bankLocation;
    }

    public void setBankLocation(String bankLocation) {
        this.bankLocation = bankLocation;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankHolder() {
        return bankHolder;
    }

    public void setBankHolder(String bankHolder) {
        this.bankHolder = bankHolder;
    }

    public String getAttachmentFlag() {
        return attachmentFlag;
    }

    public void setAttachmentFlag(String attachmentFlag) {
        this.attachmentFlag = attachmentFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public String getCallMobilePhone() {
        return callMobilePhone;
    }

    public void setCallMobilePhone(String callMobilePhone) {
        this.callMobilePhone = callMobilePhone;
    }

    public long getContactsSex() {
        return contactsSex;
    }

    public void setContactsSex(long contactsSex) {
        this.contactsSex = contactsSex;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsLanguage() {
        return contactsLanguage;
    }

    public void setContactsLanguage(String contactsLanguage) {
        this.contactsLanguage = contactsLanguage;
    }

    public String getContactsMobilePhone() {
        return contactsMobilePhone;
    }

    public void setContactsMobilePhone(String contactsMobilePhone) {
        this.contactsMobilePhone = contactsMobilePhone;
    }

    public String getContactsCountry() {
        return contactsCountry;
    }

    public void setContactsCountry(String contactsCountry) {
        this.contactsCountry = contactsCountry;
    }

    public String getContactsQuhao() {
        return contactsQuhao;
    }

    public void setContactsQuhao(String contactsQuhao) {
        this.contactsQuhao = contactsQuhao;
    }

    public String getContactsNumber() {
        return contactsNumber;
    }

    public void setContactsNumber(String contactsNumber) {
        this.contactsNumber = contactsNumber;
    }

    public String getContactsSecondNumber() {
        return contactsSecondNumber;
    }

    public void setContactsSecondNumber(String contactsSecondNumber) {
        this.contactsSecondNumber = contactsSecondNumber;
    }

    public String getOfficeCountry() {
        return officeCountry;
    }

    public void setOfficeCountry(String officeCountry) {
        this.officeCountry = officeCountry;
    }

    public String getOfficeQuhao() {
        return officeQuhao;
    }

    public void setOfficeQuhao(String officeQuhao) {
        this.officeQuhao = officeQuhao;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getOfficeSecondNumber() {
        return officeSecondNumber;
    }

    public void setOfficeSecondNumber(String officeSecondNumber) {
        this.officeSecondNumber = officeSecondNumber;
    }

    public String getEditReason() {
        return editReason;
    }

    public void setEditReason(String editReason) {
        this.editReason = editReason;
    }

    public String getEditRemark() {
        return editRemark;
    }

    public void setEditRemark(String editRemark) {
        this.editRemark = editRemark;
    }

    public EditInfo getEditInfo() {
        return editInfo;
    }

    public void setEditInfo(EditInfo editInfo) {
        this.editInfo = editInfo;
    }

    public String getSolicitOpinion() {
        return solicitOpinion;
    }

    public void setSolicitOpinion(String solicitOpinion) {
        this.solicitOpinion = solicitOpinion;
    }

    public String getUmCode() {
        return umCode;
    }

    public void setUmCode(String umCode) {
        this.umCode = umCode;
    }

    public Long getCollaborativeId() {
        return CollaborativeId;
    }

    public void setCollaborativeId(Long collaborativeId) {
        CollaborativeId = collaborativeId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getNonReason() {
        return nonReason;
    }

    public void setNonReason(String nonReason) {
        this.nonReason = nonReason;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getTreatmentBasis() {
        return treatmentBasis;
    }

    public void setTreatmentBasis(String treatmentBasis) {
        this.treatmentBasis = treatmentBasis;
    }
}
