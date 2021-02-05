package com.paic.ehis.order.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * customer_info(客户信息)对象 customer_info
 * 
 * @author sino
 * @date 2021-01-26
 */
@Data
public class CustomerInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 客户号 */
    @Excel(name = "客户号")
    private String customerNo;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 出生日期 */
    @Excel(name = "出生日期")
    private String birthday;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idCode;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String status;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String orderCode;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String policyNo;

    /** 数据状态 */
    @Excel(name = "数据状态")
    private String policyCertificateNo;


    public void setSerialNo(String serialNo) 
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo() 
    {
        return serialNo;
    }
    public void setCustomerNo(String customerNo) 
    {
        this.customerNo = customerNo;
    }

    public String getCustomerNo() 
    {
        return customerNo;
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
    public void setBirthday(String birthday) 
    {
        this.birthday = birthday;
    }

    public String getBirthday() 
    {
        return birthday;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setIdType(String idType) 
    {
        this.idType = idType;
    }

    public String getIdType() 
    {
        return idType;
    }
    public void setIdCode(String idCode) 
    {
        this.idCode = idCode;
    }

    public String getIdCode() 
    {
        return idCode;
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
            .append("customerNo", getCustomerNo())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("phone", getPhone())
            .append("idType", getIdType())
            .append("idCode", getIdCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
