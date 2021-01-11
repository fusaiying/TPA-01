package com.paic.ehis.system.domain;

import java.util.Date;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户信息对象 hmp_benefit_customer
 * 
 * @author sino
 * @date 2020-11-17
 */
public class HmpBenefitCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户ID */
    private String customerid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sex;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date birthday;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String idtype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String idcardno;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String occupation;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String socialsecurityno;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String nationality;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date effectivedate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date expirationdate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String longterm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String occupation2;

    public void setCustomerid(String customerid) 
    {
        this.customerid = customerid;
    }

    public String getCustomerid() 
    {
        return customerid;
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
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setIdtype(String idtype) 
    {
        this.idtype = idtype;
    }

    public String getIdtype() 
    {
        return idtype;
    }
    public void setIdcardno(String idcardno) 
    {
        this.idcardno = idcardno;
    }

    public String getIdcardno() 
    {
        return idcardno;
    }
    public void setOccupation(String occupation) 
    {
        this.occupation = occupation;
    }

    public String getOccupation() 
    {
        return occupation;
    }
    public void setSocialsecurityno(String socialsecurityno) 
    {
        this.socialsecurityno = socialsecurityno;
    }

    public String getSocialsecurityno() 
    {
        return socialsecurityno;
    }
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setEffectivedate(Date effectivedate) 
    {
        this.effectivedate = effectivedate;
    }

    public Date getEffectivedate() 
    {
        return effectivedate;
    }
    public void setExpirationdate(Date expirationdate) 
    {
        this.expirationdate = expirationdate;
    }

    public Date getExpirationdate() 
    {
        return expirationdate;
    }
    public void setLongterm(String longterm) 
    {
        this.longterm = longterm;
    }

    public String getLongterm() 
    {
        return longterm;
    }
    public void setOccupation2(String occupation2) 
    {
        this.occupation2 = occupation2;
    }

    public String getOccupation2() 
    {
        return occupation2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("customerid", getCustomerid())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("idtype", getIdtype())
            .append("idcardno", getIdcardno())
            .append("occupation", getOccupation())
            .append("socialsecurityno", getSocialsecurityno())
            .append("nationality", getNationality())
            .append("effectivedate", getEffectivedate())
            .append("expirationdate", getExpirationdate())
            .append("longterm", getLongterm())
            .append("occupation2", getOccupation2())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
