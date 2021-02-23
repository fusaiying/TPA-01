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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("workOrderNo", getWorkOrderNo())
            .append("channelCode", getChannelCode())
            .append("itemCode", getItemCode())
            .append("callPersonId", getCallPersonId())
            .append("callRelationBy", getCallRelationBy())
            .append("priorityLevel", getPriorityLevel())
            .append("contactsPersonId", getContactsPersonId())
            .append("contactsRelationBy", getContactsRelationBy())
            .append("callCenterId", getCallCenterId())
            .append("email", getEmail())
            .append("organCode", getOrganCode())
            .append("content", getContent())
            .append("complaintPersonId", getComplaintPersonId())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("prop0", getProp0())
            .append("prop1", getProp1())
            .append("prop2", getProp2())
            .append("prop3", getProp3())
            .append("prop4", getProp4())
            .append("prop5", getProp5())
            .append("prop6", getProp6())
            .append("prop7", getProp7())
            .append("prop8", getProp8())
            .append("prop9", getProp9())
            .append("prop10", getProp10())
            .append("prop11", getProp11())
            .append("prop12", getProp12())
            .append("prop13", getProp13())
            .append("prop14", getProp14())
            .append("prop15", getProp15())
            .append("prop16", getProp16())
            .append("prop17", getProp17())
            .append("prop18", getProp18())
            .append("prop19", getProp19())
            .append("prop20", getProp20())
            .append("prop21", getProp21())
            .append("prop22", getProp22())
            .append("prop23", getProp23())
            .append("prop24", getProp24())
            .append("prop25", getProp25())
            .toString();
    }
}
