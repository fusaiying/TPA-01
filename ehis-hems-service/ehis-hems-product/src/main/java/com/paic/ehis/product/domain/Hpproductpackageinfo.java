package com.paic.ehis.product.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品包信息对象 hpproductpackageinfo
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
public class Hpproductpackageinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品包编码 */
    private String productpackageno;

    /** 产品包名称 */
    @Excel(name = "产品包名称")
    private String productpackagename;

    /** 标准价格 */
    @Excel(name = "标准价格")
    private BigDecimal normalprice;

    /** 税价 */
    @Excel(name = "税价")
    private BigDecimal taxprice;

    /** 是否推送电子凭证 */
    @Excel(name = "是否推送电子凭证")
    private String sendcontract;

    /** 销售起期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销售起期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date salstartdate;

    /** 销售止期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销售止期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date salenddate;

    /** 卡单属性 */
    @Excel(name = "卡单属性")
    private String cardtype;

    /** 电子凭证类型 */
    @Excel(name = "电子凭证类型")
    private String prooftype;

    /** 备用字段1 */
    @Excel(name = "备用字段1")
    private String alternatefield1;

    /** 备用字段2 */
    @Excel(name = "备用字段2")
    private String alternatefield2;

    /** 操作员 */
    @Excel(name = "操作员")
    private String operator;

    /** 入机日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入机日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date makedate;

    /** 入机时间 */
    @Excel(name = "入机时间")
    private String maketime;

    /** 最后一次操作员 */
    @Excel(name = "最后一次操作员")
    private String modifyoperator;

    /** 最后一次修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后一次修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifydate;

    /** 最后一次修改时间 */
    @Excel(name = "最后一次修改时间")
    private String modifytime;

    /** 使用人标记 */
    @Excel(name = "使用人标记")
    private String userflag;

    /** 产品包状态 */
    @Excel(name = "产品包状态")
    private String productstate2;

    /** 是否发送短信 */
    @Excel(name = "是否发送短信")
    private String issendsms;

    /** 最多使用人数 */
    @Excel(name = "最多使用人数")
    private String maxusernum;

    /** 是否可更换使用人 */
    @Excel(name = "是否可更换使用人")
    private String isreplaceuser;

    /** 对外产品包名字 */
    @Excel(name = "对外产品包名字")
    private String exterpackagename;

    public void setProductpackageno(String productpackageno) 
    {
        this.productpackageno = productpackageno;
    }

    public String getProductpackageno() 
    {
        return productpackageno;
    }
    public void setProductpackagename(String productpackagename) 
    {
        this.productpackagename = productpackagename;
    }

    public String getProductpackagename() 
    {
        return productpackagename;
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
    public void setSendcontract(String sendcontract) 
    {
        this.sendcontract = sendcontract;
    }

    public String getSendcontract() 
    {
        return sendcontract;
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
    public void setUserflag(String userflag) 
    {
        this.userflag = userflag;
    }

    public String getUserflag() 
    {
        return userflag;
    }
    public void setProductstate2(String productstate2) 
    {
        this.productstate2 = productstate2;
    }

    public String getProductstate2() 
    {
        return productstate2;
    }
    public void setIssendsms(String issendsms) 
    {
        this.issendsms = issendsms;
    }

    public String getIssendsms() 
    {
        return issendsms;
    }
    public void setMaxusernum(String maxusernum) 
    {
        this.maxusernum = maxusernum;
    }

    public String getMaxusernum() 
    {
        return maxusernum;
    }
    public void setIsreplaceuser(String isreplaceuser) 
    {
        this.isreplaceuser = isreplaceuser;
    }

    public String getIsreplaceuser() 
    {
        return isreplaceuser;
    }
    public void setExterpackagename(String exterpackagename) 
    {
        this.exterpackagename = exterpackagename;
    }

    public String getExterpackagename() 
    {
        return exterpackagename;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("productpackageno", getProductpackageno())
            .append("productpackagename", getProductpackagename())
            .append("normalprice", getNormalprice())
            .append("taxprice", getTaxprice())
            .append("sendcontract", getSendcontract())
            .append("salstartdate", getSalstartdate())
            .append("salenddate", getSalenddate())
            .append("cardtype", getCardtype())
            .append("prooftype", getProoftype())
            .append("alternatefield1", getAlternatefield1())
            .append("alternatefield2", getAlternatefield2())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("modifyoperator", getModifyoperator())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .append("userflag", getUserflag())
            .append("productstate2", getProductstate2())
            .append("issendsms", getIssendsms())
            .append("maxusernum", getMaxusernum())
            .append("isreplaceuser", getIsreplaceuser())
            .append("exterpackagename", getExterpackagename())
            .toString();
    }
}
