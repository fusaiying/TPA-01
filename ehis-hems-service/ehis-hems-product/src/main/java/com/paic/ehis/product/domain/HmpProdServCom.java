package com.paic.ehis.product.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品服务机构配置对象 hpproductservicecomselect
 * 
 * @author 李碧辉
 * @date 2020-09-23
 */
public class HmpProdServCom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String productno;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String serivcecomtype;

    /** $column.columnComment */
    private String serivcecomno;

    /** $column.columnComment */
    private String contactcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String alternatefield1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String alternatefield2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String operator;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date makedate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String maketime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date modifydate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String modifytime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal servicecomcost;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal payamount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String settlementtype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal serviceincomeamount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String invaild;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String currency;

    public void setProductno(String productno) 
    {
        this.productno = productno;
    }

    public String getProductno() 
    {
        return productno;
    }
    public void setSerivcecomtype(String serivcecomtype) 
    {
        this.serivcecomtype = serivcecomtype;
    }

    public String getSerivcecomtype() 
    {
        return serivcecomtype;
    }
    public void setSerivcecomno(String serivcecomno) 
    {
        this.serivcecomno = serivcecomno;
    }

    public String getSerivcecomno() 
    {
        return serivcecomno;
    }
    public void setContactcode(String contactcode) 
    {
        this.contactcode = contactcode;
    }

    public String getContactcode() 
    {
        return contactcode;
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
    public void setServicecomcost(BigDecimal servicecomcost) 
    {
        this.servicecomcost = servicecomcost;
    }

    public BigDecimal getServicecomcost() 
    {
        return servicecomcost;
    }
    public void setPayamount(BigDecimal payamount) 
    {
        this.payamount = payamount;
    }

    public BigDecimal getPayamount() 
    {
        return payamount;
    }
    public void setSettlementtype(String settlementtype) 
    {
        this.settlementtype = settlementtype;
    }

    public String getSettlementtype() 
    {
        return settlementtype;
    }
    public void setServiceincomeamount(BigDecimal serviceincomeamount) 
    {
        this.serviceincomeamount = serviceincomeamount;
    }

    public BigDecimal getServiceincomeamount() 
    {
        return serviceincomeamount;
    }
    public void setInvaild(String invaild) 
    {
        this.invaild = invaild;
    }

    public String getInvaild() 
    {
        return invaild;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("productno", getProductno())
            .append("serivcecomtype", getSerivcecomtype())
            .append("serivcecomno", getSerivcecomno())
            .append("contactcode", getContactcode())
            .append("alternatefield1", getAlternatefield1())
            .append("alternatefield2", getAlternatefield2())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .append("servicecomcost", getServicecomcost())
            .append("payamount", getPayamount())
            .append("settlementtype", getSettlementtype())
            .append("serviceincomeamount", getServiceincomeamount())
            .append("remark", getRemark())
            .append("invaild", getInvaild())
            .append("currency", getCurrency())
            .toString();
    }
}
