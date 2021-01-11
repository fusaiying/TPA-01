package com.paic.ehis.base.domain;

import java.util.Arrays;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;

/**
 * 供应商基础信息 hpservcooperation
 * 
 * @author sino
 * @date 2020-09-21
 */
public class HmpServOpera extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机构编码 */
    private String servcomno;

    /** 服务机构中文名称 */
    @Excel(name = "服务机构中文名称")
    private String chname;

    /** 服务机构英文名称 */
    @Excel(name = "服务机构英文名称")
    private String enname;

    /** 机构类型 */
    @Excel(name = "机构类型")
    private String servcomtype;

    /** 机构属性 */
    @Excel(name = "机构属性")
    private String servcomattribute;

    /** 医疗类型 */
    @Excel(name = "医疗类型")
    private String medicaltype;

    /** 机构等级 */
    @Excel(name = "机构等级")
    private String medicallevel;

    /** 医院部门 */
    @Excel(name = "医院部门")
    private String department;

    /** constate */
    @Excel(name = "签约状态")
    private String constate;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String payway;

    /** 支付类型 */
    @Excel(name = "支付类型")
    private String paytype;

    /** 医疗资质 */
    @Excel(name = "医疗资质")
    private String qualifiedmdc;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String postcode;

    /** 是否昂贵 */
    @Excel(name = "是否昂贵")
    private String expensive;

    /** 简介 */
    @Excel(name = "简介")
    private String instructions;

    /** 交通路线 */
    @Excel(name = "交通路线")
    private String trafficroute;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /** 洲 */
    @Excel(name = "洲")
    private String continent;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /**
     * 地区数组
     */
    private String[] selectedOptions;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区县 */
    @Excel(name = "区县")
    private String district;

    /** 网站 */
    @Excel(name = "网站")
    private String website;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailedaddress;

    /** alternatefield1 */
    @Excel(name = "alternatefield1")
    private String alternatefield1;

    /** alternatefield2 */
    @Excel(name = "alternatefield2")
    private String alternatefield2;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operator;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date makedate;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String maketime;

    /** 修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifieddate;

    /** 修改时间 */
    @Excel(name = "修改时间")
    private String modifiedtime;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setServcomno(String servcomno)
    {
        this.servcomno = servcomno;
    }

    public String getServcomno() 
    {
        return servcomno;
    }
    public void setChname(String chname) 
    {
        this.chname = chname;
    }

    public String getChname() 
    {
        return chname;
    }
    public void setEnname(String enname) 
    {
        this.enname = enname;
    }

    public String getEnname() 
    {
        return enname;
    }
    public void setServcomtype(String servcomtype) 
    {
        this.servcomtype = servcomtype;
    }

    public String getServcomtype() 
    {
        return servcomtype;
    }
    public void setServcomattribute(String servcomattribute) 
    {
        this.servcomattribute = servcomattribute;
    }

    public String getServcomattribute() 
    {
        return servcomattribute;
    }
    public void setMedicaltype(String medicaltype) 
    {
        this.medicaltype = medicaltype;
    }

    public String getMedicaltype() 
    {
        return medicaltype;
    }
    public void setMedicallevel(String medicallevel) 
    {
        this.medicallevel = medicallevel;
    }

    public String getMedicallevel() 
    {
        return medicallevel;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setConstate(String constate) 
    {
        this.constate = constate;
    }

    public String getConstate() 
    {
        return constate;
    }
    public void setPayway(String payway) 
    {
        this.payway = payway;
    }

    public String getPayway() 
    {
        return payway;
    }
    public void setPaytype(String paytype) 
    {
        this.paytype = paytype;
    }

    public String getPaytype() 
    {
        return paytype;
    }
    public void setQualifiedmdc(String qualifiedmdc) 
    {
        this.qualifiedmdc = qualifiedmdc;
    }

    public String getQualifiedmdc() 
    {
        return qualifiedmdc;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setPostcode(String postcode) 
    {
        this.postcode = postcode;
    }

    public String getPostcode() 
    {
        return postcode;
    }
    public void setExpensive(String expensive) 
    {
        this.expensive = expensive;
    }

    public String getExpensive() 
    {
        return expensive;
    }
    public void setInstructions(String instructions) 
    {
        this.instructions = instructions;
    }

    public String getInstructions() 
    {
        return instructions;
    }
    public void setTrafficroute(String trafficroute) 
    {
        this.trafficroute = trafficroute;
    }

    public String getTrafficroute() 
    {
        return trafficroute;
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
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setDetailedaddress(String detailedaddress) 
    {
        this.detailedaddress = detailedaddress;
    }

    public String getDetailedaddress() 
    {
        return detailedaddress;
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
    public void setModifieddate(Date modifieddate) 
    {
        this.modifieddate = modifieddate;
    }

    public Date getModifieddate() 
    {
        return modifieddate;
    }
    public void setModifiedtime(String modifiedtime) 
    {
        this.modifiedtime = modifiedtime;
    }

    public String getModifiedtime() 
    {
        return modifiedtime;
    }


    public String[] getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(String[] selectedOptions) {
        this.selectedOptions = selectedOptions;
    }


    @Override
    public String toString() {
        return "HmpServOpera{" +
                "servcomno='" + servcomno + '\'' +
                ", chname='" + chname + '\'' +
                ", enname='" + enname + '\'' +
                ", servcomtype='" + servcomtype + '\'' +
                ", servcomattribute='" + servcomattribute + '\'' +
                ", medicaltype='" + medicaltype + '\'' +
                ", medicallevel='" + medicallevel + '\'' +
                ", department='" + department + '\'' +
                ", constate='" + constate + '\'' +
                ", payway='" + payway + '\'' +
                ", paytype='" + paytype + '\'' +
                ", qualifiedmdc='" + qualifiedmdc + '\'' +
                ", contact='" + contact + '\'' +
                ", postcode='" + postcode + '\'' +
                ", expensive='" + expensive + '\'' +
                ", instructions='" + instructions + '\'' +
                ", trafficroute='" + trafficroute + '\'' +
                ", remark='" + remark + '\'' +
                ", continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", selectedOptions=" + Arrays.toString(selectedOptions) +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", website='" + website + '\'' +
                ", detailedaddress='" + detailedaddress + '\'' +
                ", alternatefield1='" + alternatefield1 + '\'' +
                ", alternatefield2='" + alternatefield2 + '\'' +
                ", operator='" + operator + '\'' +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                ", modifieddate=" + modifieddate +
                ", modifiedtime='" + modifiedtime + '\'' +
                '}';
    }
}
