package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 出单公司开票信息 对象 base_issuingcompany_invoice
 * 
 * @author sino
 * @date 2021-01-05
 */
public class BaseIssuingcompanyInvoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出单公司编码 */
    private String companyCode;

    /** 开票名称 */
    @Excel(name = "开票名称")
    private String invoiceName;

    /** 纳税人识别号 */
    @Excel(name = "纳税人识别号")
    private String ratepayerNumber;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setCompanyCode(String companyCode) 
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() 
    {
        return companyCode;
    }
    public void setInvoiceName(String invoiceName) 
    {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceName() 
    {
        return invoiceName;
    }
    public void setRatepayerNumber(String ratepayerNumber) 
    {
        this.ratepayerNumber = ratepayerNumber;
    }

    public String getRatepayerNumber() 
    {
        return ratepayerNumber;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }

    public String getTelephone() 
    {
        return telephone;
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
            .append("companyCode", getCompanyCode())
            .append("invoiceName", getInvoiceName())
            .append("ratepayerNumber", getRatepayerNumber())
            .append("account", getAccount())
            .append("address", getAddress())
            .append("telephone", getTelephone())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
