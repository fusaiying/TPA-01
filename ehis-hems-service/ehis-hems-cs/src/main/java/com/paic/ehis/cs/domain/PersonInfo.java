package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人员信息 对象 person_info
 * 
 * @author sino
 * @date 2021-01-21
 */
public class PersonInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人员编号 本系统编号 */
    private String personId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private Long sex;

    /** 身份 */
    @Excel(name = "身份")
    private String identity;

    /** 移动电话 */
    @Excel(name = "移动电话")
    private String mobilePhone;

    /** 固定电话 国家区号-区号-号码-分机号;"-"不能丢 */
    @Excel(name = "固定电话")
    private String linePhone;

    /** 家庭电话 国家区号-区号-号码-分机号;"-"不能丢 */
    @Excel(name = "家庭电话")
    private String homePhone;

    /** 办公电话 国家区号-区号-号码-分机号;"-"不能丢 */
    @Excel(name = "办公电话")
    private String workPhone;

    /** 语言 */
    @Excel(name = "语言")
    private String language;

    /** E-MAIL */
    @Excel(name = "E-MAIL")
    private String email;

    /** 证件号 */
    @Excel(name = "证件号")
    private String idNumber;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idType;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;

    /** 客户编号 外部系统编号 */
    @Excel(name = "客户编号 外部系统编号")
    private String otherCustomerNo;

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

    public void setPersonId(String personId) 
    {
        this.personId = personId;
    }

    public String getPersonId() 
    {
        return personId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    public Long getSex() 
    {
        return sex;
    }
    public void setIdentity(String identity) 
    {
        this.identity = identity;
    }

    public String getIdentity() 
    {
        return identity;
    }
    public void setMobilePhone(String mobilePhone) 
    {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() 
    {
        return mobilePhone;
    }
    public void setLinePhone(String linePhone) 
    {
        this.linePhone = linePhone;
    }

    public String getLinePhone() 
    {
        return linePhone;
    }
    public void setHomePhone(String homePhone) 
    {
        this.homePhone = homePhone;
    }

    public String getHomePhone() 
    {
        return homePhone;
    }
    public void setWorkPhone(String workPhone) 
    {
        this.workPhone = workPhone;
    }

    public String getWorkPhone() 
    {
        return workPhone;
    }
    public void setLanguage(String language) 
    {
        this.language = language;
    }

    public String getLanguage() 
    {
        return language;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setIdNumber(String idNumber) 
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber() 
    {
        return idNumber;
    }
    public void setIdType(String idType) 
    {
        this.idType = idType;
    }

    public String getIdType() 
    {
        return idType;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setOtherCustomerNo(String otherCustomerNo) 
    {
        this.otherCustomerNo = otherCustomerNo;
    }

    public String getOtherCustomerNo() 
    {
        return otherCustomerNo;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("personId", getPersonId())
            .append("name", getName())
            .append("sex", getSex())
            .append("identity", getIdentity())
            .append("mobilePhone", getMobilePhone())
            .append("linePhone", getLinePhone())
            .append("homePhone", getHomePhone())
            .append("workPhone", getWorkPhone())
            .append("language", getLanguage())
            .append("email", getEmail())
            .append("idNumber", getIdNumber())
            .append("idType", getIdType())
            .append("birthday", getBirthday())
            .append("address", getAddress())
            .append("otherCustomerNo", getOtherCustomerNo())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("updatedBy", getUpdatedBy())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
