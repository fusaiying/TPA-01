package com.paic.ehis.base.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

import java.util.Date;
import java.util.List;

/**
 * base_supplier_info（供应商基础信息）对象 base_supplier_info
 * 
 * @author sino
 * @date 2020-12-31
 */

public class BaseSupplierInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 前一个月日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dBefore1;

    /** 现在日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dNow1;

    /** 供应商编码 */
    private String servcomNo;

    /** 服务机构中文名称 */
    @Excel(name = "服务机构中文名称")
    private String chname;

    /** 服务机构英文名称 */
    @Excel(name = "服务机构英文名称")
    private String enname;

    /** 供应商类型 */
    @Excel(name = "供应商类型")
    private String servcomType;

    /** 洲 */
    @Excel(name = "洲")
    private String continent;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区县 */
    @Excel(name = "区县")
    private String district;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detailedAddress;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    private List<BaseBank> baseBankList;

    private List<BaseContacts> baseContactsList;

    private List<BaseSupplierOutlets> baseSupplierOutletsList;

    private List<BaseSupplierReceip> baseSupplierReceipList;

    public Date getdBefore1() {
        return dBefore1;
    }

    public void setdBefore1(Date dBefore1) {
        this.dBefore1 = dBefore1;
    }

    public Date getdNow1() {
        return dNow1;
    }

    public void setdNow1(Date dNow1) {
        this.dNow1 = dNow1;
    }

    public List<BaseBank> getBaseBankList() {
        return baseBankList;
    }

    public void setBaseBankList(List<BaseBank> baseBankList) {
        this.baseBankList = baseBankList;
    }

    public List<BaseContacts> getBaseContactsList() {
        return baseContactsList;
    }

    public void setBaseContactsList(List<BaseContacts> baseContactsList) {
        this.baseContactsList = baseContactsList;
    }

    public List<BaseSupplierOutlets> getBaseSupplierOutletsList() {
        return baseSupplierOutletsList;
    }

    public void setBaseSupplierOutletsList(List<BaseSupplierOutlets> baseSupplierOutletsList) {
        this.baseSupplierOutletsList = baseSupplierOutletsList;
    }

    public List<BaseSupplierReceip> getBaseSupplierReceipList() {
        return baseSupplierReceipList;
    }

    public void setBaseSupplierReceipList(List<BaseSupplierReceip> baseSupplierReceipList) {
        this.baseSupplierReceipList = baseSupplierReceipList;
    }

    public void setServcomNo(String servcomNo)
    {
        this.servcomNo = servcomNo;
    }

    public String getServcomNo() 
    {
        return servcomNo;
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
    public void setServcomType(String servcomType) 
    {
        this.servcomType = servcomType;
    }

    public String getServcomType() 
    {
        return servcomType;
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
    public void setDetailedAddress(String detailedAddress) 
    {
        this.detailedAddress = detailedAddress;
    }

    public String getDetailedAddress() 
    {
        return detailedAddress;
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
            .append("servcomNo", getServcomNo())
            .append("chname", getChname())
            .append("enname", getEnname())
            .append("servcomType", getServcomType())
            .append("continent", getContinent())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("detailedAddress", getDetailedAddress())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
