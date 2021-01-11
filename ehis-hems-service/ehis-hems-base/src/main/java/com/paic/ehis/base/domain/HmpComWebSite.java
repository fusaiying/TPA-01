package com.paic.ehis.base.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;


import java.util.Arrays;
import java.util.Date;

/**
 * 服务网点配置对象 hpcomwebsite
 * 
 * @author sino
 * @date 2020-09-27
 */
public class HmpComWebSite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    // type = 字段类型（0：导出导入；1：仅导出；2：仅导入） ALL(0), EXPORT(1), IMPORT(2); 默认为all
    //readConverterExp = 读取内容转换表达式

    /** 供应商编码 */
    private String servcomno;

    /** 网点编码 */
    private String websitecode;

    /** 网点名称 */
    @Excel(name = "网点名称", sort = 2)
    private String websitename;

    /** 联系人 */
    @Excel(name = "网点联系人", sort = 3)
    private String linkperson;

    /** 联系电话 */
    @Excel(name = "联系人电话", sort = 4)
    private String phone;

    /** 联系邮箱 */
    @Excel(name = "联系人邮箱", sort = 5)
    private String email;

    /** 工作时间 */
    @Excel(name = "工作时间", sort = 6)
    private String worktime;

    /** 州 */
    @Excel(name = "州", sort = 7)
    private String continent;

    /** 国家*/
    @Excel(name = "国家", sort = 8)
    private String country;

    /** 省 */
    @Excel(name = "省", sort = 9)
    private String province;

    /** 市 */
    @Excel(name = "市", sort = 10)
    private String city;

    /** 地区 */
    @Excel(name = "区", sort = 11)
    private String district;

    /**
     * 地区数组
     */
    private String[] selectedOptions;


    /** 详细地址 */
    @Excel(name = "详细地址", sort = 12)
    private String address;

    /** 备用字段1 */
    private String alternatefield1;

    /** 备用字段2 */
    private String alternatefield2;

    /** 备注 */
    @Excel(name = "网点备注", sort = 13)
    private String remark;

    /** 操作员 */
    private String operator;

    /** 创建日期 */
    private Date makedate;

    /** 创建时间 */
    private String maketime;

    /** */
    private String supservcomno;

    /** */
    @Excel(name = "上级机构", sort = 1)
    private String supservcomname;

    public String[] getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(String[] selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public String getServcomno() {
        return servcomno;
    }

    public void setServcomno(String servcomno) {
        this.servcomno = servcomno;
    }

    public String getWebsitecode() {
        return websitecode;
    }

    public void setWebsitecode(String websitecode) {
        this.websitecode = websitecode;
    }

    public String getWebsitename() {
        return websitename;
    }

    public void setWebsitename(String websitename) {
        this.websitename = websitename;
    }

    public String getLinkperson() {
        return linkperson;
    }

    public void setLinkperson(String linkperson) {
        this.linkperson = linkperson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlternatefield1() {
        return alternatefield1;
    }

    public void setAlternatefield1(String alternatefield1) {
        this.alternatefield1 = alternatefield1;
    }

    public String getAlternatefield2() {
        return alternatefield2;
    }

    public void setAlternatefield2(String alternatefield2) {
        this.alternatefield2 = alternatefield2;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    public String getMaketime() {
        return maketime;
    }

    public void setMaketime(String maketime) {
        this.maketime = maketime;
    }

    public String getSupservcomno() {
        return supservcomno;
    }

    public void setSupservcomno(String supservcomno) {
        this.supservcomno = supservcomno;
    }

    public String getSupservcomname() {
        return supservcomname;
    }

    public void setSupservcomname(String supservcomname) {
        this.supservcomname = supservcomname;
    }


    @Override
    public String toString() {
        return "HmpComWebSite{" +
                "servcomno='" + servcomno + '\'' +
                ", websitecode='" + websitecode + '\'' +
                ", websitename='" + websitename + '\'' +
                ", linkperson='" + linkperson + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", worktime='" + worktime + '\'' +
                ", continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", selectedOptions=" + Arrays.toString(selectedOptions) +
                ", address='" + address + '\'' +
                ", alternatefield1='" + alternatefield1 + '\'' +
                ", alternatefield2='" + alternatefield2 + '\'' +
                ", remark='" + remark + '\'' +
                ", operator='" + operator + '\'' +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                ", supservcomno='" + supservcomno + '\'' +
                ", supservcomname='" + supservcomname + '\'' +
                '}';
    }
}
