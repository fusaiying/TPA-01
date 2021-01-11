package com.paic.ehis.base.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_contacts（联系人信息）对象 base_contacts
 * 
 * @author sino
 * @date 2020-12-31
 */
public class BaseContacts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 所属单位编码 */
    @Excel(name = "所属单位编码")
    private String supplierCode;

    /** 所属单位类型 */
    @Excel(name = "所属单位类型")
    private String supplierType;

    /** 联系人类型 */
    @Excel(name = "联系人类型")
    private String placeType;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    /** 手机 */
    @Excel(name = "手机")
    private String phone;

    /** 电话 */
    @Excel(name = "电话")
    private String mobile;

    /** 工作开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工作开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workingStart;

    /** 工作结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "工作结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workingEnd;

    /** 账号 */
    @Excel(name = "账号")
    private String accountNo;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo()
    {
        return serialNo;
    }
    public void setSupplierCode(String supplierCode)
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode()
    {
        return supplierCode;
    }
    public void setSupplierType(String supplierType)
    {
        this.supplierType = supplierType;
    }

    public String getSupplierType()
    {
        return supplierType;
    }
    public void setPlaceType(String placeType)
    {
        this.placeType = placeType;
    }

    public String getPlaceType()
    {
        return placeType;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setRole(String role) 
    {
        this.role = role;
    }

    public String getRole() 
    {
        return role;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setWorkingStart(Date workingStart)
    {
        this.workingStart = workingStart;
    }

    public Date getWorkingStart()
    {
        return workingStart;
    }
    public void setWorkingEnd(Date workingEnd)
    {
        this.workingEnd = workingEnd;
    }

    public Date getWorkingEnd()
    {
        return workingEnd;
    }
    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }

    public String getAccountNo()
    {
        return accountNo;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
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
            .append("serialNo", getSerialNo())
            .append("supplierCode", getSupplierCode())
            .append("supplierType", getSupplierType())
            .append("placeType", getPlaceType())
            .append("name", getName())
            .append("email", getEmail())
            .append("role", getRole())
            .append("phone", getPhone())
            .append("mobile", getMobile())
            .append("workingStart", getWorkingStart())
            .append("workingEnd", getWorkingEnd())
            .append("accountNo", getAccountNo())
            .append("password", getPassword())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
