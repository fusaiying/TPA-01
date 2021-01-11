package com.paic.ehis.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.math.BigDecimal;
import java.util.Date;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;


/**
 * 产品定义对象 hp_product_information
 * 
 * @author sino
 * @date 2020-09-19
 */
public class HmpProdInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "产品编码")
    private String productno;

    /** $column.columnComment */
    @Excel(name = "产品名称")
    private String productname;

    /** $column.columnComment */
    @Excel(name = "产品类型")
    private String producttype;

    /** $column.columnComment */
    @Excel(name = "服务类型")
    private String servicetype;

    /** $column.columnComment */
    @Excel(name = "costprice")
    private BigDecimal costprice;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private BigDecimal normalprice;

    /** $column.columnComment */
    @Excel(name = "税价")
    private BigDecimal taxprice;

    /** $column.columnComment */
    @Excel(name = "服务限期")
    private String servicelimit;

    /** $column.columnComment */
    @Excel(name = "服务限期单位")
    private String servicelimitflag;

    /** $column.columnComment */
    @Excel(name = "callout")
    private String callout;

    /** $column.columnComment */
    @Excel(name = "sendcontract")
    private String sendcontract;

    /** $column.columnComment */
    @Excel(name = "usebyoneself")
    private String usebyoneself;

    /** $column.columnComment */
    @Excel(name = "allowedtocancel")
    private String allowedtocancel;

    /** $column.columnComment */
    @Excel(name = "销售开始时间")
    private Date salstartdate;

    /** $column.columnComment */
    @Excel(name = "销售结束时间")
    private Date salenddate;

    /** $column.columnComment */
    @Excel(name = "性别")
    private String applysex;

    /** $column.columnComment */
    @Excel(name = "最小年龄")
    private String agemin;

    /** $column.columnComment */
    @Excel(name = "最大年龄")
    private String agemax;

    /** $column.columnComment */
    @Excel(name = "discounttype")
    private String discounttype;

    /** $column.columnComment */
    @Excel(name = "cardtype")
    private String cardtype;

    /** $column.columnComment */
    @Excel(name = "prooftype")
    private String prooftype;

    /** $column.columnComment */
    @Excel(name = "产品状态")
    private String productstate;

    /** $column.columnComment */
    @Excel(name = "alternatefield1")
    private String alternatefield1;

    /** $column.columnComment */
    @Excel(name = "alternatefield2")
    private String alternatefield2;

    /** $column.columnComment */
    @Excel(name = "操作人")
    private String operator;

    /** $column.columnComment */
    @Excel(name = "创建日期")
    private Date makedate;

    /** $column.columnComment */
    @Excel(name = "创建时间")
    private String maketime;

    /** $column.columnComment */
    @Excel(name = "修改人")
    private String modifyoperator;

    /** $column.columnComment */
    @Excel(name = "修改日期")
    private Date modifydate;

    /** $column.columnComment */
    @Excel(name = "修改时间")
    private String modifytime;

    /** $column.columnComment */
    @Excel(name = "productcode")
    private String productcode;

    /** $column.columnComment */
    @Excel(name = "usertype")
    private String usertype;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String taxrate;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String refunddate;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private BigDecimal servicecost;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String productclass;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String productsubclass;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String exterproductname;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String amortizationflag;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String currency;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String isprovideservice;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String isprovideonline;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String isstandardproduct;

    /** $column.columnComment */
    @Excel(name = "myproduct")
    private String serviceimpletype;

    public void setProductno(String productno) 
    {
        this.productno = productno;
    }

    public String getProductno() 
    {
        return productno;
    }
    public void setProductname(String productname) 
    {
        this.productname = productname;
    }

    public String getProductname() 
    {
        return productname;
    }
    public void setProducttype(String producttype) 
    {
        this.producttype = producttype;
    }

    public String getProducttype() 
    {
        return producttype;
    }
    public void setServicetype(String servicetype) 
    {
        this.servicetype = servicetype;
    }

    public String getServicetype() 
    {
        return servicetype;
    }
    public void setCostprice(BigDecimal costprice) 
    {
        this.costprice = costprice;
    }

    public BigDecimal getCostprice() 
    {
        return costprice;
    }
    public void setNormalprice(BigDecimal normalprice) 
    {
        this.normalprice = normalprice;
    }

    public BigDecimal getNormalprice() 
    {
        return normalprice;
    }
    public void setTaxprice(BigDecimal taxprice) 
    {
        this.taxprice = taxprice;
    }

    public BigDecimal getTaxprice() 
    {
        return taxprice;
    }
    public void setServicelimit(String servicelimit) 
    {
        this.servicelimit = servicelimit;
    }

    public String getServicelimit() 
    {
        return servicelimit;
    }
    public void setServicelimitflag(String servicelimitflag) 
    {
        this.servicelimitflag = servicelimitflag;
    }

    public String getServicelimitflag() 
    {
        return servicelimitflag;
    }
    public void setCallout(String callout) 
    {
        this.callout = callout;
    }

    public String getCallout() 
    {
        return callout;
    }
    public void setSendcontract(String sendcontract) 
    {
        this.sendcontract = sendcontract;
    }

    public String getSendcontract() 
    {
        return sendcontract;
    }
    public void setUsebyoneself(String usebyoneself) 
    {
        this.usebyoneself = usebyoneself;
    }

    public String getUsebyoneself() 
    {
        return usebyoneself;
    }
    public void setAllowedtocancel(String allowedtocancel) 
    {
        this.allowedtocancel = allowedtocancel;
    }

    public String getAllowedtocancel() 
    {
        return allowedtocancel;
    }
    public void setSalstartdate(Date salstartdate) 
    {
        this.salstartdate = salstartdate;
    }

    public Date getSalstartdate() 
    {
        return salstartdate;
    }
    public void setSalenddate(Date salenddate) 
    {
        this.salenddate = salenddate;
    }

    public Date getSalenddate() 
    {
        return salenddate;
    }
    public void setApplysex(String applysex) 
    {
        this.applysex = applysex;
    }

    public String getApplysex() 
    {
        return applysex;
    }
    public void setAgemin(String agemin) 
    {
        this.agemin = agemin;
    }

    public String getAgemin() 
    {
        return agemin;
    }
    public void setAgemax(String agemax) 
    {
        this.agemax = agemax;
    }

    public String getAgemax() 
    {
        return agemax;
    }
    public void setDiscounttype(String discounttype) 
    {
        this.discounttype = discounttype;
    }

    public String getDiscounttype() 
    {
        return discounttype;
    }
    public void setCardtype(String cardtype) 
    {
        this.cardtype = cardtype;
    }

    public String getCardtype() 
    {
        return cardtype;
    }
    public void setProoftype(String prooftype) 
    {
        this.prooftype = prooftype;
    }

    public String getProoftype() 
    {
        return prooftype;
    }
    public void setProductstate(String productstate) 
    {
        this.productstate = productstate;
    }

    public String getProductstate() 
    {
        return productstate;
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
    public void setProductcode(String productcode) 
    {
        this.productcode = productcode;
    }

    public String getProductcode() 
    {
        return productcode;
    }
    public void setUsertype(String usertype) 
    {
        this.usertype = usertype;
    }

    public String getUsertype() 
    {
        return usertype;
    }
    public void setTaxrate(String taxrate) 
    {
        this.taxrate = taxrate;
    }

    public String getTaxrate() 
    {
        return taxrate;
    }
    public void setRefunddate(String refunddate) 
    {
        this.refunddate = refunddate;
    }

    public String getRefunddate() 
    {
        return refunddate;
    }
    public void setServicecost(BigDecimal servicecost) 
    {
        this.servicecost = servicecost;
    }

    public BigDecimal getServicecost() 
    {
        return servicecost;
    }
    public void setProductclass(String productclass) 
    {
        this.productclass = productclass;
    }

    public String getProductclass() 
    {
        return productclass;
    }
    public void setProductsubclass(String productsubclass) 
    {
        this.productsubclass = productsubclass;
    }

    public String getProductsubclass() 
    {
        return productsubclass;
    }
    public void setExterproductname(String exterproductname) 
    {
        this.exterproductname = exterproductname;
    }

    public String getExterproductname() 
    {
        return exterproductname;
    }
    public void setAmortizationflag(String amortizationflag) 
    {
        this.amortizationflag = amortizationflag;
    }

    public String getAmortizationflag() 
    {
        return amortizationflag;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }
    public void setIsprovideservice(String isprovideservice) 
    {
        this.isprovideservice = isprovideservice;
    }

    public String getIsprovideservice() 
    {
        return isprovideservice;
    }
    public void setIsprovideonline(String isprovideonline) 
    {
        this.isprovideonline = isprovideonline;
    }

    public String getIsprovideonline() 
    {
        return isprovideonline;
    }
    public void setIsstandardproduct(String isstandardproduct) 
    {
        this.isstandardproduct = isstandardproduct;
    }

    public String getIsstandardproduct() 
    {
        return isstandardproduct;
    }
    public void setServiceimpletype(String serviceimpletype) 
    {
        this.serviceimpletype = serviceimpletype;
    }

    public String getServiceimpletype() 
    {
        return serviceimpletype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productno", getProductno())
            .append("productname", getProductname())
            .append("producttype", getProducttype())
            .append("servicetype", getServicetype())
            .append("costprice", getCostprice())
            .append("normalprice", getNormalprice())
            .append("taxprice", getTaxprice())
            .append("servicelimit", getServicelimit())
            .append("servicelimitflag", getServicelimitflag())
            .append("callout", getCallout())
            .append("sendcontract", getSendcontract())
            .append("usebyoneself", getUsebyoneself())
            .append("allowedtocancel", getAllowedtocancel())
            .append("salstartdate", getSalstartdate())
            .append("salenddate", getSalenddate())
            .append("applysex", getApplysex())
            .append("agemin", getAgemin())
            .append("agemax", getAgemax())
            .append("discounttype", getDiscounttype())
            .append("cardtype", getCardtype())
            .append("prooftype", getProoftype())
            .append("productstate", getProductstate())
            .append("alternatefield1", getAlternatefield1())
            .append("alternatefield2", getAlternatefield2())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("modifyoperator", getModifyoperator())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .append("productcode", getProductcode())
            .append("usertype", getUsertype())
            .append("taxrate", getTaxrate())
            .append("refunddate", getRefunddate())
            .append("servicecost", getServicecost())
            .append("productclass", getProductclass())
            .append("productsubclass", getProductsubclass())
            .append("exterproductname", getExterproductname())
            .append("amortizationflag", getAmortizationflag())
            .append("currency", getCurrency())
            .append("isprovideservice", getIsprovideservice())
            .append("isprovideonline", getIsprovideonline())
            .append("isstandardproduct", getIsstandardproduct())
            .append("serviceimpletype", getServiceimpletype())
            .toString();
    }
}
