package com.paic.ehis.system.domain;

import java.util.Date;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务申请信息对象 hmp_service_order_application
 * 
 * @author sino
 * @date 2020-11-17
 */
public class HmpServiceOrderApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String applicationcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ordercode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String user;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String useridtype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String useridcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String usersex;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date userbirthday;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String usermobile;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String contacts;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String contactsidtype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String contactsidcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String contactsmobile;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;

    public void setApplicationcode(String applicationcode) 
    {
        this.applicationcode = applicationcode;
    }

    public String getApplicationcode() 
    {
        return applicationcode;
    }
    public void setOrdercode(String ordercode) 
    {
        this.ordercode = ordercode;
    }

    public String getOrdercode() 
    {
        return ordercode;
    }
    public void setUser(String user) 
    {
        this.user = user;
    }

    public String getUser() 
    {
        return user;
    }
    public void setUseridtype(String useridtype) 
    {
        this.useridtype = useridtype;
    }

    public String getUseridtype() 
    {
        return useridtype;
    }
    public void setUseridcode(String useridcode) 
    {
        this.useridcode = useridcode;
    }

    public String getUseridcode() 
    {
        return useridcode;
    }
    public void setUsersex(String usersex) 
    {
        this.usersex = usersex;
    }

    public String getUsersex() 
    {
        return usersex;
    }
    public void setUserbirthday(Date userbirthday) 
    {
        this.userbirthday = userbirthday;
    }

    public Date getUserbirthday() 
    {
        return userbirthday;
    }
    public void setUsermobile(String usermobile) 
    {
        this.usermobile = usermobile;
    }

    public String getUsermobile() 
    {
        return usermobile;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setContactsidtype(String contactsidtype) 
    {
        this.contactsidtype = contactsidtype;
    }

    public String getContactsidtype() 
    {
        return contactsidtype;
    }
    public void setContactsidcode(String contactsidcode) 
    {
        this.contactsidcode = contactsidcode;
    }

    public String getContactsidcode() 
    {
        return contactsidcode;
    }
    public void setContactsmobile(String contactsmobile) 
    {
        this.contactsmobile = contactsmobile;
    }

    public String getContactsmobile() 
    {
        return contactsmobile;
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
            .append("applicationcode", getApplicationcode())
            .append("ordercode", getOrdercode())
            .append("user", getUser())
            .append("useridtype", getUseridtype())
            .append("useridcode", getUseridcode())
            .append("usersex", getUsersex())
            .append("userbirthday", getUserbirthday())
            .append("usermobile", getUsermobile())
            .append("contacts", getContacts())
            .append("contactsidtype", getContactsidtype())
            .append("contactsidcode", getContactsidcode())
            .append("contactsmobile", getContactsmobile())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
