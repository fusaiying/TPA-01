package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_supplier_receipt(供应商开票信息)对象 base_supplier_receip
 * 
 * @author sino
 * @date 2020-12-31
 */
public class BaseSupplierReceip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 供应商编码 */
    private String supplierCode;

    /** 发票抬头 */
    @Excel(name = "发票抬头")
    private String companyTitle;

    /** 纳税人识别号 */
    @Excel(name = "纳税人识别号")
    private String dutynum;

    /** 开票信息 */
    @Excel(name = "开票信息")
    private String billInfo;

    /** 账号 */
    @Excel(name = "账号")
    private String accNum;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setCompanyTitle(String companyTitle) 
    {
        this.companyTitle = companyTitle;
    }

    public String getCompanyTitle() 
    {
        return companyTitle;
    }
    public void setDutynum(String dutynum) 
    {
        this.dutynum = dutynum;
    }

    public String getDutynum() 
    {
        return dutynum;
    }
    public void setBillInfo(String billInfo) 
    {
        this.billInfo = billInfo;
    }

    public String getBillInfo() 
    {
        return billInfo;
    }
    public void setAccNum(String accNum) 
    {
        this.accNum = accNum;
    }

    public String getAccNum() 
    {
        return accNum;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
            .append("supplierCode", getSupplierCode())
            .append("companyTitle", getCompanyTitle())
            .append("dutynum", getDutynum())
            .append("billInfo", getBillInfo())
            .append("accNum", getAccNum())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
