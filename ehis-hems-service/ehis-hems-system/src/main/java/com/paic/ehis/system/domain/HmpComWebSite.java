package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务网点配置对象 hpcomwebsite
 * 
 * @author sino
 * @date 2020-09-27
 */
public class HmpComWebSite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String servcomno;

    /** $column.columnComment */
    private String websitecode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String websitename;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String linkperson;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String phone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String email;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String worktime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String continent;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String country;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String province;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String city;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String district;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String address;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String alternatefield1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String alternatefield2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String operator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date makedate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String maketime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String supservcomno;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String supservcomname;

    public void setServcomno(String servcomno) 
    {
        this.servcomno = servcomno;
    }

    public String getServcomno() 
    {
        return servcomno;
    }
    public void setWebsitecode(String websitecode) 
    {
        this.websitecode = websitecode;
    }

    public String getWebsitecode() 
    {
        return websitecode;
    }
    public void setWebsitename(String websitename) 
    {
        this.websitename = websitename;
    }

    public String getWebsitename() 
    {
        return websitename;
    }
    public void setLinkperson(String linkperson) 
    {
        this.linkperson = linkperson;
    }

    public String getLinkperson() 
    {
        return linkperson;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setWorktime(String worktime) 
    {
        this.worktime = worktime;
    }

    public String getWorktime() 
    {
        return worktime;
    }
    public void setContinent(String continent) 
    {
        this.continent = continent;
    }

    public String getContinent() 
    {
        return continent;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
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
    public void setAlternatefield1(String alternatefield1) 
    {
        this.alternatefield1 = alternatefield1;
    }

    public String getAlternatefield1() 
    {
        return alternatefield1;
    }
    public void setAlternatefield2(String alternatefield2) 
    {
        this.alternatefield2 = alternatefield2;
    }

    public String getAlternatefield2() 
    {
        return alternatefield2;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setMakedate(Date makedate) 
    {
        this.makedate = makedate;
    }

    public Date getMakedate() 
    {
        return makedate;
    }
    public void setMaketime(String maketime) 
    {
        this.maketime = maketime;
    }

    public String getMaketime() 
    {
        return maketime;
    }
    public void setSupservcomno(String supservcomno) 
    {
        this.supservcomno = supservcomno;
    }

    public String getSupservcomno() 
    {
        return supservcomno;
    }
    public void setSupservcomname(String supservcomname) 
    {
        this.supservcomname = supservcomname;
    }

    public String getSupservcomname() 
    {
        return supservcomname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("servcomno", getServcomno())
            .append("websitecode", getWebsitecode())
            .append("websitename", getWebsitename())
            .append("linkperson", getLinkperson())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("worktime", getWorktime())
            .append("continent", getContinent())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("address", getAddress())
            .append("alternatefield1", getAlternatefield1())
            .append("alternatefield2", getAlternatefield2())
            .append("remark", getRemark())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("supservcomno", getSupservcomno())
            .append("supservcomname", getSupservcomname())
            .toString();
    }
}
