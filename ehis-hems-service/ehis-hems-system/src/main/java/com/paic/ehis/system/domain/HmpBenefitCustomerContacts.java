package com.paic.ehis.system.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户联系信息对象 hmp_benefit_customer_contacts
 * 
 * @author sino
 * @date 2020-11-23
 */
public class HmpBenefitCustomerContacts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    @Excel(name = "客户ID")
    private String customerid;

    /** 联系编码 */
    private String contactscode;

    /** 手机 */
    @Excel(name = "手机")
    private String mobilephone;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 邮箱 */
    @Excel(name = "客户ID")
    private String email;

    /** 地址（省） */
    @Excel(name = "地址（省）")
    private String province;

    /** 地址（市） */
    @Excel(name = "地址（市）")
    private String city;

    /** 地址（区/县） */
    @Excel(name = "地址（区/县）")
    private String district;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    public void setCustomerid(String customerid) 
    {
        this.customerid = customerid;
    }

    public String getCustomerid() 
    {
        return customerid;
    }
    public void setContactscode(String contactscode) 
    {
        this.contactscode = contactscode;
    }

    public String getContactscode() 
    {
        return contactscode;
    }
    public void setMobilephone(String mobilephone) 
    {
        this.mobilephone = mobilephone;
    }

    public String getMobilephone() 
    {
        return mobilephone;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("customerid", getCustomerid())
            .append("contactscode", getContactscode())
            .append("mobilephone", getMobilephone())
            .append("telephone", getTelephone())
            .append("email", getEmail())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("address", getAddress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
