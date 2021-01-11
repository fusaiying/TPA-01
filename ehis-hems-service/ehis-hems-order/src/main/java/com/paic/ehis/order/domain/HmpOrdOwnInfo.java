package com.paic.ehis.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 个人订单对象 hmpordoowninfo
 * 
 * @author sino
 * @date 2020-09-24
 */
public class HmpOrdOwnInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String appno;

    /** $column.columnComment */
    private String grouporderno;

    /** 申请书编号 */
    private String personalorderno;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String servicecardno;

    /** 业务员 */
    @Excel(name = "业务员")
    private String salesmanno;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applicationdate;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderstartdate;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderenddate;

    /** $column.columnComment */
    @Excel(name = "申请日期")
    private String advancepay;

    /** $column.columnComment */
    @Excel(name = "申请日期")
    private String issendcard;

    /** 购买人姓名 */
    @Excel(name = "购买人姓名")
    private String purname;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String pursex;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String idtype;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String idno;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买人姓名", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String phone;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String mobile;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String email;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String postcode;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String chargemethod;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String bankaccountname;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String bankname;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String bankbranch;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String bankaccount;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String bankaddress;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String billpattern;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String ordermark;

    /** $column.columnComment */
    @Excel(name = "购买人姓名")
    private String orderfrom;

    /** 状态 */
    @Excel(name = "状态")
    private String orderstatus;

    /** $column.columnComment */
    @Excel(name = "状态")
    private String actualcurrency;

    /** $column.columnComment */
    @Excel(name = "状态")
    private String actualmoney;

    /** $column.columnComment */
    @Excel(name = "状态")
    private String payintegral;

    /** $column.columnComment */
    @Excel(name = "状态")
    private String paycurrency;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "状态", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paydate;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "状态", width = 30, dateFormat = "yyyy-MM-dd")
    private Date accdate;

    /** $column.columnComment */
    @Excel(name = "状态")
    private String alternatefield1;

    /** $column.columnComment */
    @Excel(name = "状态")
    private String alternatefield2;

    /** 录入员 */
    @Excel(name = "录入员")
    private String operator;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入员", width = 30, dateFormat = "yyyy-MM-dd")
    private Date makedate;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String maketime;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String modifyoperator;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入员", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifydate;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String modifytime;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String contno;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String riskcode;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String supplier;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String conttel;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入员", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signdate;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String chargemethod2;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String worktime;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String relatedcode;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String taxpayertype;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String invoicetype;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String collectionflag;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String billnumber;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String channelno1;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String saleplatform;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String balanceflag;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String printtimes;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private String customerno;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private BigDecimal commission;

    /** $column.columnComment */
    @Excel(name = "录入员")
    private BigDecimal servicecharge;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入员", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enddate;

    public void setAppno(String appno) 
    {
        this.appno = appno;
    }

    public String getAppno() 
    {
        return appno;
    }
    public void setGrouporderno(String grouporderno) 
    {
        this.grouporderno = grouporderno;
    }

    public String getGrouporderno() 
    {
        return grouporderno;
    }
    public void setPersonalorderno(String personalorderno) 
    {
        this.personalorderno = personalorderno;
    }

    public String getPersonalorderno() 
    {
        return personalorderno;
    }
    public void setServicecardno(String servicecardno) 
    {
        this.servicecardno = servicecardno;
    }

    public String getServicecardno() 
    {
        return servicecardno;
    }
    public void setSalesmanno(String salesmanno) 
    {
        this.salesmanno = salesmanno;
    }

    public String getSalesmanno() 
    {
        return salesmanno;
    }
    public void setApplicationdate(Date applicationdate) 
    {
        this.applicationdate = applicationdate;
    }

    public Date getApplicationdate() 
    {
        return applicationdate;
    }
    public void setOrderstartdate(Date orderstartdate) 
    {
        this.orderstartdate = orderstartdate;
    }

    public Date getOrderstartdate() 
    {
        return orderstartdate;
    }
    public void setOrderenddate(Date orderenddate) 
    {
        this.orderenddate = orderenddate;
    }

    public Date getOrderenddate() 
    {
        return orderenddate;
    }
    public void setAdvancepay(String advancepay) 
    {
        this.advancepay = advancepay;
    }

    public String getAdvancepay() 
    {
        return advancepay;
    }
    public void setIssendcard(String issendcard) 
    {
        this.issendcard = issendcard;
    }

    public String getIssendcard() 
    {
        return issendcard;
    }
    public void setPurname(String purname) 
    {
        this.purname = purname;
    }

    public String getPurname() 
    {
        return purname;
    }
    public void setPursex(String pursex) 
    {
        this.pursex = pursex;
    }

    public String getPursex() 
    {
        return pursex;
    }
    public void setIdtype(String idtype) 
    {
        this.idtype = idtype;
    }

    public String getIdtype() 
    {
        return idtype;
    }
    public void setIdno(String idno) 
    {
        this.idno = idno;
    }

    public String getIdno() 
    {
        return idno;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPostcode(String postcode) 
    {
        this.postcode = postcode;
    }

    public String getPostcode() 
    {
        return postcode;
    }
    public void setChargemethod(String chargemethod) 
    {
        this.chargemethod = chargemethod;
    }

    public String getChargemethod() 
    {
        return chargemethod;
    }
    public void setBankaccountname(String bankaccountname) 
    {
        this.bankaccountname = bankaccountname;
    }

    public String getBankaccountname() 
    {
        return bankaccountname;
    }
    public void setBankname(String bankname) 
    {
        this.bankname = bankname;
    }

    public String getBankname() 
    {
        return bankname;
    }
    public void setBankbranch(String bankbranch) 
    {
        this.bankbranch = bankbranch;
    }

    public String getBankbranch() 
    {
        return bankbranch;
    }
    public void setBankaccount(String bankaccount) 
    {
        this.bankaccount = bankaccount;
    }

    public String getBankaccount() 
    {
        return bankaccount;
    }
    public void setBankaddress(String bankaddress) 
    {
        this.bankaddress = bankaddress;
    }

    public String getBankaddress() 
    {
        return bankaddress;
    }
    public void setBillpattern(String billpattern) 
    {
        this.billpattern = billpattern;
    }

    public String getBillpattern() 
    {
        return billpattern;
    }
    public void setOrdermark(String ordermark) 
    {
        this.ordermark = ordermark;
    }

    public String getOrdermark() 
    {
        return ordermark;
    }
    public void setOrderfrom(String orderfrom) 
    {
        this.orderfrom = orderfrom;
    }

    public String getOrderfrom() 
    {
        return orderfrom;
    }
    public void setOrderstatus(String orderstatus) 
    {
        this.orderstatus = orderstatus;
    }

    public String getOrderstatus() 
    {
        return orderstatus;
    }
    public void setActualcurrency(String actualcurrency) 
    {
        this.actualcurrency = actualcurrency;
    }

    public String getActualcurrency() 
    {
        return actualcurrency;
    }
    public void setActualmoney(String actualmoney) 
    {
        this.actualmoney = actualmoney;
    }

    public String getActualmoney() 
    {
        return actualmoney;
    }
    public void setPayintegral(String payintegral) 
    {
        this.payintegral = payintegral;
    }

    public String getPayintegral() 
    {
        return payintegral;
    }
    public void setPaycurrency(String paycurrency) 
    {
        this.paycurrency = paycurrency;
    }

    public String getPaycurrency() 
    {
        return paycurrency;
    }
    public void setPaydate(Date paydate) 
    {
        this.paydate = paydate;
    }

    public Date getPaydate() 
    {
        return paydate;
    }
    public void setAccdate(Date accdate) 
    {
        this.accdate = accdate;
    }

    public Date getAccdate() 
    {
        return accdate;
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
    public void setModifyoperator(String modifyoperator) 
    {
        this.modifyoperator = modifyoperator;
    }

    public String getModifyoperator() 
    {
        return modifyoperator;
    }
    public void setModifydate(Date modifydate) 
    {
        this.modifydate = modifydate;
    }

    public Date getModifydate() 
    {
        return modifydate;
    }
    public void setModifytime(String modifytime) 
    {
        this.modifytime = modifytime;
    }

    public String getModifytime() 
    {
        return modifytime;
    }
    public void setContno(String contno) 
    {
        this.contno = contno;
    }

    public String getContno() 
    {
        return contno;
    }
    public void setRiskcode(String riskcode) 
    {
        this.riskcode = riskcode;
    }

    public String getRiskcode() 
    {
        return riskcode;
    }
    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }
    public void setConttel(String conttel) 
    {
        this.conttel = conttel;
    }

    public String getConttel() 
    {
        return conttel;
    }
    public void setSigndate(Date signdate) 
    {
        this.signdate = signdate;
    }

    public Date getSigndate() 
    {
        return signdate;
    }
    public void setChargemethod2(String chargemethod2) 
    {
        this.chargemethod2 = chargemethod2;
    }

    public String getChargemethod2() 
    {
        return chargemethod2;
    }
    public void setWorktime(String worktime) 
    {
        this.worktime = worktime;
    }

    public String getWorktime() 
    {
        return worktime;
    }
    public void setRelatedcode(String relatedcode) 
    {
        this.relatedcode = relatedcode;
    }

    public String getRelatedcode() 
    {
        return relatedcode;
    }
    public void setTaxpayertype(String taxpayertype) 
    {
        this.taxpayertype = taxpayertype;
    }

    public String getTaxpayertype() 
    {
        return taxpayertype;
    }
    public void setInvoicetype(String invoicetype) 
    {
        this.invoicetype = invoicetype;
    }

    public String getInvoicetype() 
    {
        return invoicetype;
    }
    public void setCollectionflag(String collectionflag) 
    {
        this.collectionflag = collectionflag;
    }

    public String getCollectionflag() 
    {
        return collectionflag;
    }
    public void setBillnumber(String billnumber) 
    {
        this.billnumber = billnumber;
    }

    public String getBillnumber() 
    {
        return billnumber;
    }
    public void setChannelno1(String channelno1) 
    {
        this.channelno1 = channelno1;
    }

    public String getChannelno1() 
    {
        return channelno1;
    }
    public void setSaleplatform(String saleplatform) 
    {
        this.saleplatform = saleplatform;
    }

    public String getSaleplatform() 
    {
        return saleplatform;
    }
    public void setBalanceflag(String balanceflag) 
    {
        this.balanceflag = balanceflag;
    }

    public String getBalanceflag() 
    {
        return balanceflag;
    }
    public void setPrinttimes(String printtimes) 
    {
        this.printtimes = printtimes;
    }

    public String getPrinttimes() 
    {
        return printtimes;
    }
    public void setCustomerno(String customerno) 
    {
        this.customerno = customerno;
    }

    public String getCustomerno() 
    {
        return customerno;
    }
    public void setCommission(BigDecimal commission) 
    {
        this.commission = commission;
    }

    public BigDecimal getCommission() 
    {
        return commission;
    }
    public void setServicecharge(BigDecimal servicecharge) 
    {
        this.servicecharge = servicecharge;
    }

    public BigDecimal getServicecharge() 
    {
        return servicecharge;
    }
    public void setEnddate(Date enddate) 
    {
        this.enddate = enddate;
    }

    public Date getEnddate() 
    {
        return enddate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("appno", getAppno())
            .append("grouporderno", getGrouporderno())
            .append("personalorderno", getPersonalorderno())
            .append("servicecardno", getServicecardno())
            .append("salesmanno", getSalesmanno())
            .append("applicationdate", getApplicationdate())
            .append("orderstartdate", getOrderstartdate())
            .append("orderenddate", getOrderenddate())
            .append("advancepay", getAdvancepay())
            .append("issendcard", getIssendcard())
            .append("purname", getPurname())
            .append("pursex", getPursex())
            .append("idtype", getIdtype())
            .append("idno", getIdno())
            .append("birthday", getBirthday())
            .append("phone", getPhone())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("postcode", getPostcode())
            .append("chargemethod", getChargemethod())
            .append("bankaccountname", getBankaccountname())
            .append("bankname", getBankname())
            .append("bankbranch", getBankbranch())
            .append("bankaccount", getBankaccount())
            .append("bankaddress", getBankaddress())
            .append("billpattern", getBillpattern())
            .append("ordermark", getOrdermark())
            .append("orderfrom", getOrderfrom())
            .append("orderstatus", getOrderstatus())
            .append("actualcurrency", getActualcurrency())
            .append("actualmoney", getActualmoney())
            .append("payintegral", getPayintegral())
            .append("paycurrency", getPaycurrency())
            .append("paydate", getPaydate())
            .append("accdate", getAccdate())
            .append("alternatefield1", getAlternatefield1())
            .append("alternatefield2", getAlternatefield2())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("modifyoperator", getModifyoperator())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .append("contno", getContno())
            .append("riskcode", getRiskcode())
            .append("supplier", getSupplier())
            .append("conttel", getConttel())
            .append("signdate", getSigndate())
            .append("chargemethod2", getChargemethod2())
            .append("worktime", getWorktime())
            .append("relatedcode", getRelatedcode())
            .append("taxpayertype", getTaxpayertype())
            .append("invoicetype", getInvoicetype())
            .append("collectionflag", getCollectionflag())
            .append("billnumber", getBillnumber())
            .append("channelno1", getChannelno1())
            .append("saleplatform", getSaleplatform())
            .append("balanceflag", getBalanceflag())
            .append("printtimes", getPrinttimes())
            .append("customerno", getCustomerno())
            .append("commission", getCommission())
            .append("servicecharge", getServicecharge())
            .append("enddate", getEnddate())
            .append("remark", getRemark())
            .toString();
    }
}
