package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 出单公司开票信息 对象 base_issuingcompany_invoice
 *
 * @author sino
 * @date 2021-01-05
 */
public class BaseIssuingcompanyInvoice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 出单公司编码
     */
    private String companycode;

    /**
     * 开票名称
     */
    @Excel(name = "开票名称")
    private String invoicename;

    /**
     * 纳税人识别号
     */
    @Excel(name = "纳税人识别号")
    private String ratepayernumber;

    /**
     * 账号
     */
    @Excel(name = "账号")
    private String account;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 电话
     */
    @Excel(name = "电话")
    private String telephone;

    /**
     * 状态（Y-有效，N-无效）
     */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getInvoicename() {
        return invoicename;
    }

    public void setInvoicename(String invoicename) {
        this.invoicename = invoicename;
    }

    public String getRatepayernumber() {
        return ratepayernumber;
    }

    public void setRatepayernumber(String ratepayernumber) {
        this.ratepayernumber = ratepayernumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("companycode", getCompanycode())
                .append("invoicename", getInvoicename())
                .append("ratepayernumber", getRatepayernumber())
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
