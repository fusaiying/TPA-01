package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * base_provider_info(服务商基本信息)对象 base_provider_info
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseProviderInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 服务商编码 */
    private String providerCode;

    /** 是否互联网医院 */
    @Excel(name = "是否互联网医院")
    private String interHosp;

    /** 是否虚拟机构 */
    @Excel(name = "是否虚拟机构")
    private String virtualOrg;

    /** 中文展示名称 */
    @Excel(name = "中文展示名称")
    private String chname1;

    /** 英文展示名称 */
    @Excel(name = "英文展示名称")
    private String enname1;

    /** 别名 */
    @Excel(name = "别名")
    private String otherName;

    /** 中文工商注册名称 */
    @Excel(name = "中文工商注册名称")
    private String chregister;

    /** 英文工商注册名称 */
    @Excel(name = "英文工商注册名称")
    private String enregister;

    /** 官方网址 */
    @Excel(name = "官方网址")
    private String website;

    /** 洲 */
    @Excel(name = "洲")
    private String continent;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 中文地址 */
    @Excel(name = "中文地址")
    private String chaddreess;

    /** 英文地址 */
    @Excel(name = "英文地址")
    private String enaddress;

    /** 一级属性 */
    @Excel(name = "一级属性")
    private String firstAttribute;

    /** 二级属性 */
    @Excel(name = "二级属性")
    private String secondAttribute;

    /** 官方电话 */
    @Excel(name = "官方电话")
    private String phone;

    /** 综专科类型 */
    @Excel(name = "综专科类型")
    private String type;

    /** 曾用名 */
    @Excel(name = "曾用名")
    private String usedname;

    /** 医院咨询电话 */
    @Excel(name = "医院咨询电话")
    private String consultphone;

    /** 医院公共邮箱 */
    @Excel(name = "医院公共邮箱")
    private String email;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipcode;

    /** 传真 */
    @Excel(name = "传真")
    private String portraiture;

    /** 床位数 */
    @Excel(name = "床位数")
    private String beds;

    /** 是否国际部 */
    @Excel(name = "是否国际部")
    private String international;

    /** 是否全国前十 */
    @Excel(name = "是否全国前十")
    private String topten;

    /** 是否专科医院 */
    @Excel(name = "是否专科医院")
    private String specializedHospital;

    /** 是否当地前三 */
    @Excel(name = "是否当地前三")
    private String topthird;

    /** 是否特定医院 */
    @Excel(name = "是否特定医院")
    private String appointHospital;

    /** 是否全国专科前五 */
    @Excel(name = "是否全国专科前五")
    private String topfive;

    /** 是否部队医院 */
    @Excel(name = "是否部队医院")
    private String armyhosptial;

    /** 是否有住院 */
    @Excel(name = "是否有住院")
    private String inhosptial;

    /** 是否特需部 */
    @Excel(name = "是否特需部")
    private String special;

    /** 社保医院定点标志 */
    @Excel(name = "社保医院定点标志")
    private String flag;

    /** 是否康复医院 */
    @Excel(name = "是否康复医院")
    private String rehabilitationHospital;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 标志性建筑 */
    @Excel(name = "标志性建筑")
    private String landmarks;

    /** 停车场 */
    @Excel(name = "停车场")
    private String carpark;

    /** 交通路线 */
    @Excel(name = "交通路线")
    private String transitroute;

    /** 机构简介 */
    @Excel(name = "机构简介")
    private String introduction;

    /** 备注（中文） */
    @Excel(name = "备注", readConverterExp = "中=文")
    private String chremarks;

    /** 备注（英文） */
    @Excel(name = "备注", readConverterExp = "英=文")
    private String enremarks;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    @Excel(name = "所属地区")
    private String addressdetail;

    /** 状态 */
    @Excel(name = "状态名称")
    private String statusName;

    @Excel(name = "类型名称")
    private String typeName;

    @Excel(name = "理赔医院编码")
    private String claimHospitalCode;

    @Excel(name = "理赔医院名称")
    private String claimHospitalName;

    @Excel(name = "修改标志")
    private String updateFlag;

    @Excel(name = "服务机构类型")
    private String orgFlag;

    @Excel(name = "业务状态")
    private String bussinessStatus;

    private String websitecName;

    private String websitecCode;

    @Excel(name = "区号")
    private String areacode;


    @Excel(name = "专科子类型")
    private List<String> type2;


    private String type2Str;

    @Excel(name = "开户行名称")
    private String bankName;

    /** 开户银行编码 */
    @Excel(name = "开户银行编码")
    private String bankCode;

    /** 银行信息描述 */
    @Excel(name = "银行信息描述")
    private String bankDetail;

    /** 银行账户名称 */
    @Excel(name = "银行账户名称")
    private String accountName;


    public void setProviderCode(String providerCode) 
    {
        this.providerCode = providerCode;
    }

    public String getProviderCode() 
    {
        return providerCode;
    }
    public void setInterHosp(String interHosp) 
    {
        this.interHosp = interHosp;
    }

    public String getInterHosp() 
    {
        return interHosp;
    }
    public void setVirtualOrg(String virtualOrg) 
    {
        this.virtualOrg = virtualOrg;
    }

    public String getVirtualOrg() 
    {
        return virtualOrg;
    }
    public void setChname1(String chname1) 
    {
        this.chname1 = chname1;
    }

    public String getChname1() 
    {
        return chname1;
    }
    public void setEnname1(String enname1) 
    {
        this.enname1 = enname1;
    }

    public String getEnname1() 
    {
        return enname1;
    }
    public void setOtherName(String otherName) 
    {
        this.otherName = otherName;
    }

    public String getOtherName() 
    {
        return otherName;
    }
    public void setChregister(String chregister) 
    {
        this.chregister = chregister;
    }

    public String getChregister() 
    {
        return chregister;
    }
    public void setEnregister(String enregister) 
    {
        this.enregister = enregister;
    }

    public String getEnregister() 
    {
        return enregister;
    }
    public void setWebsite(String website) 
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
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
    public void setChaddreess(String chaddreess) 
    {
        this.chaddreess = chaddreess;
    }

    public String getChaddreess() 
    {
        return chaddreess;
    }
    public void setEnaddress(String enaddress) 
    {
        this.enaddress = enaddress;
    }

    public String getEnaddress() 
    {
        return enaddress;
    }
    public void setFirstAttribute(String firstAttribute) 
    {
        this.firstAttribute = firstAttribute;
    }

    public String getFirstAttribute() 
    {
        return firstAttribute;
    }
    public void setSecondAttribute(String secondAttribute) 
    {
        this.secondAttribute = secondAttribute;
    }

    public String getSecondAttribute() 
    {
        return secondAttribute;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setUsedname(String usedname) 
    {
        this.usedname = usedname;
    }

    public String getUsedname() 
    {
        return usedname;
    }
    public void setConsultphone(String consultphone) 
    {
        this.consultphone = consultphone;
    }

    public String getConsultphone() 
    {
        return consultphone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setZipcode(String zipcode) 
    {
        this.zipcode = zipcode;
    }

    public String getZipcode() 
    {
        return zipcode;
    }
    public void setPortraiture(String portraiture) 
    {
        this.portraiture = portraiture;
    }

    public String getPortraiture() 
    {
        return portraiture;
    }
    public void setBeds(String beds) 
    {
        this.beds = beds;
    }

    public String getBeds() 
    {
        return beds;
    }
    public void setInternational(String international) 
    {
        this.international = international;
    }

    public String getInternational() 
    {
        return international;
    }
    public void setTopten(String topten) 
    {
        this.topten = topten;
    }

    public String getTopten() 
    {
        return topten;
    }
    public void setSpecializedHospital(String specializedHospital) 
    {
        this.specializedHospital = specializedHospital;
    }

    public String getSpecializedHospital() 
    {
        return specializedHospital;
    }
    public void setTopthird(String topthird) 
    {
        this.topthird = topthird;
    }

    public String getTopthird() 
    {
        return topthird;
    }
    public void setAppointHospital(String appointHospital) 
    {
        this.appointHospital = appointHospital;
    }

    public String getAppointHospital() 
    {
        return appointHospital;
    }
    public void setTopfive(String topfive) 
    {
        this.topfive = topfive;
    }

    public String getTopfive() 
    {
        return topfive;
    }
    public void setArmyhosptial(String armyhosptial) 
    {
        this.armyhosptial = armyhosptial;
    }

    public String getArmyhosptial() 
    {
        return armyhosptial;
    }
    public void setInhosptial(String inhosptial) 
    {
        this.inhosptial = inhosptial;
    }

    public String getInhosptial() 
    {
        return inhosptial;
    }
    public void setSpecial(String special) 
    {
        this.special = special;
    }

    public String getSpecial() 
    {
        return special;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
    }
    public void setRehabilitationHospital(String rehabilitationHospital) 
    {
        this.rehabilitationHospital = rehabilitationHospital;
    }

    public String getRehabilitationHospital() 
    {
        return rehabilitationHospital;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }
    public void setLandmarks(String landmarks) 
    {
        this.landmarks = landmarks;
    }

    public String getLandmarks() 
    {
        return landmarks;
    }
    public void setCarpark(String carpark) 
    {
        this.carpark = carpark;
    }

    public String getCarpark() 
    {
        return carpark;
    }
    public void setTransitroute(String transitroute) 
    {
        this.transitroute = transitroute;
    }

    public String getTransitroute() 
    {
        return transitroute;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setChremarks(String chremarks) 
    {
        this.chremarks = chremarks;
    }

    public String getChremarks() 
    {
        return chremarks;
    }
    public void setEnremarks(String enremarks) 
    {
        this.enremarks = enremarks;
    }

    public String getEnremarks() 
    {
        return enremarks;
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
            .append("providerCode", getProviderCode())
            .append("interHosp", getInterHosp())
            .append("virtualOrg", getVirtualOrg())
            .append("chname1", getChname1())
            .append("enname1", getEnname1())
            .append("otherName", getOtherName())
            .append("chregister", getChregister())
            .append("enregister", getEnregister())
            .append("website", getWebsite())
            .append("continent", getContinent())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("chaddreess", getChaddreess())
            .append("enaddress", getEnaddress())
            .append("firstAttribute", getFirstAttribute())
            .append("secondAttribute", getSecondAttribute())
            .append("phone", getPhone())
            .append("type", getType())
            .append("usedname", getUsedname())
            .append("consultphone", getConsultphone())
            .append("email", getEmail())
            .append("zipcode", getZipcode())
            .append("portraiture", getPortraiture())
            .append("beds", getBeds())
            .append("international", getInternational())
            .append("topten", getTopten())
            .append("specializedHospital", getSpecializedHospital())
            .append("topthird", getTopthird())
            .append("appointHospital", getAppointHospital())
            .append("topfive", getTopfive())
            .append("armyhosptial", getArmyhosptial())
            .append("inhosptial", getInhosptial())
            .append("special", getSpecial())
            .append("flag", getFlag())
            .append("rehabilitationHospital", getRehabilitationHospital())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("landmarks", getLandmarks())
            .append("carpark", getCarpark())
            .append("transitroute", getTransitroute())
            .append("introduction", getIntroduction())
            .append("chremarks", getChremarks())
            .append("enremarks", getEnremarks())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
