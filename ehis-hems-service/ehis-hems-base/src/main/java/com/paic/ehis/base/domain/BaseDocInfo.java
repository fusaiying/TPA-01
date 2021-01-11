package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_doctor(医生信息)对象 base_doc_info
 * 
 * @author sino
 * @date 2020-12-31
 */
public class BaseDocInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 医生编码 */
    private String docCode;

    /** 医生姓名 */
    @Excel(name = "医生姓名")
    private String docName;

    /** 所属单位编码 */
    @Excel(name = "所属单位编码")
    private String supplierCode;

    /** 一级科室 */
    @Excel(name = "一级科室")
    private String fiestDept;

    /** 二级科室 */
    @Excel(name = "二级科室")
    private String secondDept;

    /** 就诊起始时间 */
    @Excel(name = "就诊起始时间")
    private String startTime;

    /** 就诊截止时间 */
    @Excel(name = "就诊截止时间")
    private String endTime;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String docPhone;

    /** 所在科室 */
    @Excel(name = "所在科室")
    private String docDept;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** Column_18 */
    @Excel(name = "Column_18")
    private String column18;

    public void setDocCode(String docCode) 
    {
        this.docCode = docCode;
    }

    public String getDocCode() 
    {
        return docCode;
    }
    public void setDocName(String docName) 
    {
        this.docName = docName;
    }

    public String getDocName() 
    {
        return docName;
    }
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setFiestDept(String fiestDept) 
    {
        this.fiestDept = fiestDept;
    }

    public String getFiestDept() 
    {
        return fiestDept;
    }
    public void setSecondDept(String secondDept) 
    {
        this.secondDept = secondDept;
    }

    public String getSecondDept() 
    {
        return secondDept;
    }
    public void setStartTime(String startTime) 
    {
        this.startTime = startTime;
    }

    public String getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(String endTime) 
    {
        this.endTime = endTime;
    }

    public String getEndTime() 
    {
        return endTime;
    }
    public void setDocPhone(String docPhone) 
    {
        this.docPhone = docPhone;
    }

    public String getDocPhone() 
    {
        return docPhone;
    }
    public void setDocDept(String docDept) 
    {
        this.docDept = docDept;
    }

    public String getDocDept() 
    {
        return docDept;
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
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setColumn18(String column18) 
    {
        this.column18 = column18;
    }

    public String getColumn18() 
    {
        return column18;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("docCode", getDocCode())
            .append("docName", getDocName())
            .append("supplierCode", getSupplierCode())
            .append("fiestDept", getFiestDept())
            .append("secondDept", getSecondDept())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("docPhone", getDocPhone())
            .append("docDept", getDocDept())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("column18", getColumn18())
            .toString();
    }
}
