package com.paic.ehis.claimflow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 被保人信息对象 policy_insured
 * 
 * @author sino
 * @date 2021-01-09
 */
@Data
public class PolicyInsured extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 保单号 */
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;
    /** 被保人客户号 */
    @Excel(name = "被保人编码")
    private String insuredNo;


    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    /** 被保人性别 */
    @Excel(name = "被保人性别")
    private String sex;

    /** 被保人证件类型 */
    @Excel(name = "被保人证件类型")
    private String idType;

    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;

    /** 被保人生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "被保人生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 职业编码 */
    @Excel(name = "职业编码")
    private String occupation;

    /** 国籍 */
    @Excel(name = "国籍")
    private String nationality;

    /** 证件有效期起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "证件有效期起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date idStartDate;

    /** 证件有效期止期（长期=9999-12-31） */
    @Excel(name = "证件有效期止期", readConverterExp = "长=期=9999-12-31")
    private Date idEndDate;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String mobile;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 电话号 */
    @Excel(name = "电话号")
    private String phone;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 原被保人客户号 */
    @Excel(name = "原被保人客户号")
    private String orgInsuredNo;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setInsuredNo(String insuredNo) 
    {
        this.insuredNo = insuredNo;
    }

    public String getInsuredNo() 
    {
        return insuredNo;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setIdType(String idType) 
    {
        this.idType = idType;
    }

    public String getIdType() 
    {
        return idType;
    }
    public void setIdNo(String idNo) 
    {
        this.idNo = idNo;
    }

    public String getIdNo() 
    {
        return idNo;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setOccupation(String occupation) 
    {
        this.occupation = occupation;
    }

    public String getOccupation() 
    {
        return occupation;
    }
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setIdStartDate(Date idStartDate) 
    {
        this.idStartDate = idStartDate;
    }

    public Date getIdStartDate() 
    {
        return idStartDate;
    }
    public void setIdEndDate(Date idEndDate) 
    {
        this.idEndDate = idEndDate;
    }

    public Date getIdEndDate() 
    {
        return idEndDate;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setOrgInsuredNo(String orgInsuredNo) 
    {
        this.orgInsuredNo = orgInsuredNo;
    }

    public String getOrgInsuredNo() 
    {
        return orgInsuredNo;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("insuredNo", getInsuredNo())
            .append("name", getName())
            .append("sex", getSex())
            .append("idType", getIdType())
            .append("idNo", getIdNo())
            .append("birthday", getBirthday())
            .append("occupation", getOccupation())
            .append("nationality", getNationality())
            .append("idStartDate", getIdStartDate())
            .append("idEndDate", getIdEndDate())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("phone", getPhone())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("address", getAddress())
            .append("orgInsuredNo", getOrgInsuredNo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
