package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品与销售地址关联对象 hpproductsalcomselect
 * 
 * @author sino
 * @date 2020-09-27
 */
public class HmpProdSalCom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String productno;

    /** $column.columnComment */
    private String salprovince;

    /** $column.columnComment */
    private String salcity;

    /** $column.columnComment */
    private String salarea;

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

    public void setProductno(String productno) 
    {
        this.productno = productno;
    }

    public String getProductno() 
    {
        return productno;
    }
    public void setSalprovince(String salprovince) 
    {
        this.salprovince = salprovince;
    }

    public String getSalprovince() 
    {
        return salprovince;
    }
    public void setSalcity(String salcity) 
    {
        this.salcity = salcity;
    }

    public String getSalcity() 
    {
        return salcity;
    }
    public void setSalarea(String salarea) 
    {
        this.salarea = salarea;
    }

    public String getSalarea() 
    {
        return salarea;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("productno", getProductno())
            .append("salprovince", getSalprovince())
            .append("salcity", getSalcity())
            .append("salarea", getSalarea())
            .append("alternatefield1", getAlternatefield1())
            .append("alternatefield2", getAlternatefield2())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .toString();
    }
}
