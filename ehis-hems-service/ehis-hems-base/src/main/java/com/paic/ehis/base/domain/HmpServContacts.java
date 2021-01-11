package com.paic.ehis.base.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 联系人
 *
 * @author sino
 * @date 2020-11-2
 */
public class HmpServContacts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 联系人编号 */
    private String contactno;
    /** 合作机构编码 */
    private String servcomno;
    /** 姓名 */
    private String name;
    /** 邮箱 */
    private String email;
    /** 职务 */
    private String title;
    /** 电话或手机1 */
    private String phone;
    /** 电话或手机2 */
    private String mobile;
    /** 传真 */
    private String fax;
    /** 备用字段1 */
    private String alternatefield1;
    /** 备用字段2 */
    private String alternatefield2;
    /** 操作员 */
    private String operator;
    /** 入机日期 */
    private Date makedate;
    /** 入机时间 */
    private String maketime;

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getServcomno() {
        return servcomno;
    }

    public void setServcomno(String servcomno) {
        this.servcomno = servcomno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAlternatefield1() {
        return alternatefield1;
    }

    public void setAlternatefield1(String alternatefield1) {
        this.alternatefield1 = alternatefield1;
    }

    public String getAlternatefield2() {
        return alternatefield2;
    }

    public void setAlternatefield2(String alternatefield2) {
        this.alternatefield2 = alternatefield2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    public String getMaketime() {
        return maketime;
    }

    public void setMaketime(String maketime) {
        this.maketime = maketime;
    }

    @Override
    public String toString() {
        return "HmpServContacts{" +
                "contactno='" + contactno + '\'' +
                ", servcomno='" + servcomno + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", fax='" + fax + '\'' +
                ", alternatefield1='" + alternatefield1 + '\'' +
                ", alternatefield2='" + alternatefield2 + '\'' +
                ", operator='" + operator + '\'' +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                '}';
    }
}
