package com.paic.ehis.cs.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 受理详情 对象 accept_detail_info
 * 
 * @author sino
 * @date 2021-01-22
 */
public class AcceptDetailInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单编号 */
    private String workOrderNo;

    /** 受理渠道 信息需求受理渠道：电话中心；在线客服；邮箱；网站；柜面；寿险；微信；监管；媒体；
预约受理渠道：电话；邮箱；网站；柜面；APP；寿险；微信；媒体；在线客服；监管部门；
投诉受理渠道：电话；邮箱；APP；监管部门；媒体；信函；来访；官网；其他 */
    @Excel(name = "受理渠道 信息需求受理渠道")
    private String channelCode;

    /** 服务项目 */
    @Excel(name = "服务项目")
    private String itemCode;

    /** 来电人编号 person_info中的编号 */
    @Excel(name = "来电人编号 person_info中的编号")
    private String callPersonId;

    /** 来电人与被保人关系 */
    @Excel(name = "来电人与被保人关系")
    private String callRelationBy;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priorityLevel;

    /** 联系人编号 person_info中的编号 */
    @Excel(name = "联系人编号 person_info中的编号")
    private String contactsPersonId;

    /** 联系人与被保人关系 */
    @Excel(name = "联系人与被保人关系")
    private String contactsRelationBy;

    /** 电话中心业务流水号 */
    @Excel(name = "电话中心业务流水号")
    private String callCenterId;

    /** E-MAIL */
    @Excel(name = "E-MAIL")
    private String email;

    /** 出单机构 */
    @Excel(name = "出单机构")
    private String organCode;

    /** 业务内容 */
    @Excel(name = "业务内容")
    private String content;

    /** 投诉人编号 person_info中的编号 */
    @Excel(name = "投诉人编号 person_info中的编号")
    private String complaintPersonId;

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

    /*是否已劝解*/
    private  String persuasionFlag;

    /*门诊直接结算服务项目*/
    private String outpatientSettlement;

    /*就诊类型*/
    private String visitType;

    /*疾病名称*/
    private String disease;

    /*症状或体征*/
    private String symptomsSigns;

    /*本次疾病/症状起病时间*/
    private String symptomTimes;

    /*时间单位*/
    private String timeUnit;

    /*是否意外*/
    private String accidentFlag;

    /*意外原因*/
    private String accidentReason;

    /*是否持有效证件*/
    private String validCertificate;

    /*是否持有直结卡*/
    private String settlementCard;

    /*预约时间*/
    private String complaintTime;

    /*预计住院天数*/
    private String hospitalDays;

    /*医疗机构*/
    private String medicalInstitution;

    /*科室*/
    private String department;

    /*床位*/
    private String bunk;

    /*医院赔付比例*/
    private String compensationRatio;

    /**预约日期*/
    private String appointmentDate;

    /**医院工作来电*/
    private String hospitalWorkCall;

    /**类似疾病症状最早发生时间*/
    private String earliestTime;

    /**是否首次就诊*/
    private String firstFlag;

    /**就诊日期*/
    private String clinicDate;

    /**就诊时间*/
    private String clinicTime;

    /**就诊医院省*/
    private String province;

    /**就诊医院市*/
    private String city;

    /**是否申请直结*/
    private String settlementFlag;


    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop0;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop1;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop2;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop3;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop4;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop5;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop6;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop7;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop8;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop9;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop10;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop11;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop12;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop13;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop14;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop15;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop16;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop17;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop18;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop19;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop20;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop21;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop22;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop23;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop24;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop25;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop26;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop27;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop28;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop29;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop30;

    /** 见转意表 */
    @Excel(name = "见转意表")
    private String prop31;

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getHospitalWorkCall() {
        return hospitalWorkCall;
    }

    public void setHospitalWorkCall(String hospitalWorkCall) {
        this.hospitalWorkCall = hospitalWorkCall;
    }

    public String getEarliestTime() {
        return earliestTime;
    }

    public void setEarliestTime(String earliestTime) {
        this.earliestTime = earliestTime;
    }

    public String getFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(String firstFlag) {
        this.firstFlag = firstFlag;
    }

    public String getClinicDate() {
        return clinicDate;
    }

    public void setClinicDate(String clinicDate) {
        this.clinicDate = clinicDate;
    }

    public String getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(String clinicTime) {
        this.clinicTime = clinicTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSettlementFlag() {
        return settlementFlag;
    }

    public void setSettlementFlag(String settlementFlag) {
        this.settlementFlag = settlementFlag;
    }

    public String getOutpatientSettlement() {
        return outpatientSettlement;
    }

    public void setOutpatientSettlement(String outpatientSettlement) {
        this.outpatientSettlement = outpatientSettlement;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSymptomsSigns() {
        return symptomsSigns;
    }

    public void setSymptomsSigns(String symptomsSigns) {
        this.symptomsSigns = symptomsSigns;
    }

    public String getSymptomTimes() {
        return symptomTimes;
    }

    public void setSymptomTimes(String symptomTimes) {
        this.symptomTimes = symptomTimes;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getValidCertificate() {
        return validCertificate;
    }

    public void setValidCertificate(String validCertificate) {
        this.validCertificate = validCertificate;
    }

    public String getSettlementCard() {
        return settlementCard;
    }

    public void setSettlementCard(String settlementCard) {
        this.settlementCard = settlementCard;
    }

    public String getHospitalDays() {
        return hospitalDays;
    }

    public void setHospitalDays(String hospitalDays) {
        this.hospitalDays = hospitalDays;
    }

    public String getMedicalInstitution() {
        return medicalInstitution;
    }

    public void setMedicalInstitution(String medicalInstitution) {
        this.medicalInstitution = medicalInstitution;
    }

    public String getAccidentFlag() {
        return accidentFlag;
    }

    public void setAccidentFlag(String accidentFlag) {
        this.accidentFlag = accidentFlag;
    }

    public String getAccidentReason() {
        return accidentReason;
    }

    public void setAccidentReason(String accidentReason) {
        this.accidentReason = accidentReason;
    }

    public String getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(String complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBunk() {
        return bunk;
    }

    public void setBunk(String bunk) {
        this.bunk = bunk;
    }

    public String getCompensationRatio() {
        return compensationRatio;
    }

    public void setCompensationRatio(String compensationRatio) {
        this.compensationRatio = compensationRatio;
    }

    public String getPersuasionFlag() {
        return persuasionFlag;
    }

    public void setPersuasionFlag(String persuasionFlag) {
        this.persuasionFlag = persuasionFlag;
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

    public void setWorkOrderNo(String workOrderNo)
    {
        this.workOrderNo = workOrderNo;
    }

    public String getWorkOrderNo() 
    {
        return workOrderNo;
    }
    public void setChannelCode(String channelCode) 
    {
        this.channelCode = channelCode;
    }

    public String getChannelCode() 
    {
        return channelCode;
    }
    public void setItemCode(String itemCode) 
    {
        this.itemCode = itemCode;
    }

    public String getItemCode() 
    {
        return itemCode;
    }
    public void setCallPersonId(String callPersonId) 
    {
        this.callPersonId = callPersonId;
    }

    public String getCallPersonId() 
    {
        return callPersonId;
    }
    public void setCallRelationBy(String callRelationBy) 
    {
        this.callRelationBy = callRelationBy;
    }

    public String getCallRelationBy() 
    {
        return callRelationBy;
    }
    public void setPriorityLevel(String priorityLevel) 
    {
        this.priorityLevel = priorityLevel;
    }

    public String getPriorityLevel() 
    {
        return priorityLevel;
    }
    public void setContactsPersonId(String contactsPersonId) 
    {
        this.contactsPersonId = contactsPersonId;
    }

    public String getContactsPersonId() 
    {
        return contactsPersonId;
    }
    public void setContactsRelationBy(String contactsRelationBy) 
    {
        this.contactsRelationBy = contactsRelationBy;
    }

    public String getContactsRelationBy() 
    {
        return contactsRelationBy;
    }
    public void setCallCenterId(String callCenterId) 
    {
        this.callCenterId = callCenterId;
    }

    public String getCallCenterId() 
    {
        return callCenterId;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setOrganCode(String organCode) 
    {
        this.organCode = organCode;
    }

    public String getOrganCode() 
    {
        return organCode;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setComplaintPersonId(String complaintPersonId) 
    {
        this.complaintPersonId = complaintPersonId;
    }

    public String getComplaintPersonId() 
    {
        return complaintPersonId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setProp0(String prop0) 
    {
        this.prop0 = prop0;
    }

    public String getProp0() 
    {
        return prop0;
    }
    public void setProp1(String prop1) 
    {
        this.prop1 = prop1;
    }

    public String getProp1() 
    {
        return prop1;
    }
    public void setProp2(String prop2) 
    {
        this.prop2 = prop2;
    }

    public String getProp2() 
    {
        return prop2;
    }
    public void setProp3(String prop3) 
    {
        this.prop3 = prop3;
    }

    public String getProp3() 
    {
        return prop3;
    }
    public void setProp4(String prop4) 
    {
        this.prop4 = prop4;
    }

    public String getProp4() 
    {
        return prop4;
    }
    public void setProp5(String prop5) 
    {
        this.prop5 = prop5;
    }

    public String getProp5() 
    {
        return prop5;
    }
    public void setProp6(String prop6) 
    {
        this.prop6 = prop6;
    }

    public String getProp6() 
    {
        return prop6;
    }
    public void setProp7(String prop7) 
    {
        this.prop7 = prop7;
    }

    public String getProp7() 
    {
        return prop7;
    }
    public void setProp8(String prop8) 
    {
        this.prop8 = prop8;
    }

    public String getProp8() 
    {
        return prop8;
    }
    public void setProp9(String prop9) 
    {
        this.prop9 = prop9;
    }

    public String getProp9() 
    {
        return prop9;
    }
    public void setProp10(String prop10) 
    {
        this.prop10 = prop10;
    }

    public String getProp10() 
    {
        return prop10;
    }
    public void setProp11(String prop11) 
    {
        this.prop11 = prop11;
    }

    public String getProp11() 
    {
        return prop11;
    }
    public void setProp12(String prop12) 
    {
        this.prop12 = prop12;
    }

    public String getProp12() 
    {
        return prop12;
    }
    public void setProp13(String prop13) 
    {
        this.prop13 = prop13;
    }

    public String getProp13() 
    {
        return prop13;
    }
    public void setProp14(String prop14) 
    {
        this.prop14 = prop14;
    }

    public String getProp14() 
    {
        return prop14;
    }
    public void setProp15(String prop15) 
    {
        this.prop15 = prop15;
    }

    public String getProp15() 
    {
        return prop15;
    }
    public void setProp16(String prop16) 
    {
        this.prop16 = prop16;
    }

    public String getProp16() 
    {
        return prop16;
    }
    public void setProp17(String prop17) 
    {
        this.prop17 = prop17;
    }

    public String getProp17() 
    {
        return prop17;
    }
    public void setProp18(String prop18) 
    {
        this.prop18 = prop18;
    }

    public String getProp18() 
    {
        return prop18;
    }
    public void setProp19(String prop19) 
    {
        this.prop19 = prop19;
    }

    public String getProp19() 
    {
        return prop19;
    }
    public void setProp20(String prop20) 
    {
        this.prop20 = prop20;
    }

    public String getProp20() 
    {
        return prop20;
    }
    public void setProp21(String prop21) 
    {
        this.prop21 = prop21;
    }

    public String getProp21() 
    {
        return prop21;
    }
    public void setProp22(String prop22) 
    {
        this.prop22 = prop22;
    }

    public String getProp22() 
    {
        return prop22;
    }
    public void setProp23(String prop23) 
    {
        this.prop23 = prop23;
    }

    public String getProp23() 
    {
        return prop23;
    }
    public void setProp24(String prop24) 
    {
        this.prop24 = prop24;
    }

    public String getProp24() 
    {
        return prop24;
    }
    public void setProp25(String prop25) 
    {
        this.prop25 = prop25;
    }

    public String getProp25() 
    {
        return prop25;
    }

    public String getProp26() {
        return prop26;
    }

    public void setProp26(String prop26) {
        this.prop26 = prop26;
    }

    public String getProp27() {
        return prop27;
    }

    public void setProp27(String prop27) {
        this.prop27 = prop27;
    }

    public String getProp28() {
        return prop28;
    }

    public void setProp28(String prop28) {
        this.prop28 = prop28;
    }

    public String getProp29() {
        return prop29;
    }

    public void setProp29(String prop29) {
        this.prop29 = prop29;
    }

    public String getProp30() {
        return prop30;
    }

    public void setProp30(String prop30) {
        this.prop30 = prop30;
    }

    public String getProp31() {
        return prop31;
    }

    public void setProp31(String prop31) {
        this.prop31 = prop31;
    }

    @Override
    public String toString() {
        return "AcceptDetailInfo{" +
                "workOrderNo='" + workOrderNo + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", callPersonId='" + callPersonId + '\'' +
                ", callRelationBy='" + callRelationBy + '\'' +
                ", priorityLevel='" + priorityLevel + '\'' +
                ", contactsPersonId='" + contactsPersonId + '\'' +
                ", contactsRelationBy='" + contactsRelationBy + '\'' +
                ", callCenterId='" + callCenterId + '\'' +
                ", email='" + email + '\'' +
                ", organCode='" + organCode + '\'' +
                ", content='" + content + '\'' +
                ", complaintPersonId='" + complaintPersonId + '\'' +
                ", bankTransfer='" + bankTransfer + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankLocation='" + bankLocation + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", bankHolder='" + bankHolder + '\'' +
                ", persuasionFlag='" + persuasionFlag + '\'' +
                ", outpatientSettlement='" + outpatientSettlement + '\'' +
                ", visitType='" + visitType + '\'' +
                ", disease='" + disease + '\'' +
                ", symptomsSigns='" + symptomsSigns + '\'' +
                ", symptomTimes='" + symptomTimes + '\'' +
                ", timeUnit='" + timeUnit + '\'' +
                ", accidentFlag='" + accidentFlag + '\'' +
                ", accidentReason='" + accidentReason + '\'' +
                ", validCertificate='" + validCertificate + '\'' +
                ", settlementCard='" + settlementCard + '\'' +
                ", complaintTime='" + complaintTime + '\'' +
                ", hospitalDays='" + hospitalDays + '\'' +
                ", medicalInstitution='" + medicalInstitution + '\'' +
                ", department='" + department + '\'' +
                ", bunk='" + bunk + '\'' +
                ", compensationRatio='" + compensationRatio + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", hospitalWorkCall='" + hospitalWorkCall + '\'' +
                ", earliestTime='" + earliestTime + '\'' +
                ", firstFlag='" + firstFlag + '\'' +
                ", clinicDate='" + clinicDate + '\'' +
                ", clinicTime='" + clinicTime + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", settlementFlag='" + settlementFlag + '\'' +
                ", status='" + status + '\'' +
                ", prop0='" + prop0 + '\'' +
                ", prop1='" + prop1 + '\'' +
                ", prop2='" + prop2 + '\'' +
                ", prop3='" + prop3 + '\'' +
                ", prop4='" + prop4 + '\'' +
                ", prop5='" + prop5 + '\'' +
                ", prop6='" + prop6 + '\'' +
                ", prop7='" + prop7 + '\'' +
                ", prop8='" + prop8 + '\'' +
                ", prop9='" + prop9 + '\'' +
                ", prop10='" + prop10 + '\'' +
                ", prop11='" + prop11 + '\'' +
                ", prop12='" + prop12 + '\'' +
                ", prop13='" + prop13 + '\'' +
                ", prop14='" + prop14 + '\'' +
                ", prop15='" + prop15 + '\'' +
                ", prop16='" + prop16 + '\'' +
                ", prop17='" + prop17 + '\'' +
                ", prop18='" + prop18 + '\'' +
                ", prop19='" + prop19 + '\'' +
                ", prop20='" + prop20 + '\'' +
                ", prop21='" + prop21 + '\'' +
                ", prop22='" + prop22 + '\'' +
                ", prop23='" + prop23 + '\'' +
                ", prop24='" + prop24 + '\'' +
                ", prop25='" + prop25 + '\'' +
                ", prop26='" + prop26 + '\'' +
                ", prop27='" + prop27 + '\'' +
                ", prop28='" + prop28 + '\'' +
                ", prop29='" + prop29 + '\'' +
                ", prop30='" + prop30 + '\'' +
                ", prop31='" + prop31 + '\'' +
                '}';
    }
}
