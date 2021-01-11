package com.paic.ehis.product.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品包短信配置表对象 hmpkgsmsconfiginfo
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
public class Hmpkgsmsconfiginfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long serialno;

    /** 产品包编码 */
    @Excel(name = "产品包编码")
    private String productpackageno;

    /** 短信模板编码 */
    @Excel(name = "短信模板编码")
    private String smsmodeno;

    /** 短信类型 */
    @Excel(name = "短信类型")
    private String smstype;

    /** 入机日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入机日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date makedate;

    /** 入机时间 */
    @Excel(name = "入机时间")
    private String maketime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String operator;

    /** 最后一次修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后一次修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifydate;

    /** 最后一次修改时间 */
    @Excel(name = "最后一次修改时间")
    private String modifytime;

    /** 最后一次修改人 */
    @Excel(name = "最后一次修改人")
    private String modifyoperator;

    public void setSerialno(Long serialno) 
    {
        this.serialno = serialno;
    }

    public Long getSerialno() 
    {
        return serialno;
    }
    public void setProductpackageno(String productpackageno) 
    {
        this.productpackageno = productpackageno;
    }

    public String getProductpackageno() 
    {
        return productpackageno;
    }
    public void setSmsmodeno(String smsmodeno) 
    {
        this.smsmodeno = smsmodeno;
    }

    public String getSmsmodeno() 
    {
        return smsmodeno;
    }
    public void setSmstype(String smstype) 
    {
        this.smstype = smstype;
    }

    public String getSmstype() 
    {
        return smstype;
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
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
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
    public void setModifyoperator(String modifyoperator) 
    {
        this.modifyoperator = modifyoperator;
    }

    public String getModifyoperator() 
    {
        return modifyoperator;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serialno", getSerialno())
            .append("productpackageno", getProductpackageno())
            .append("smsmodeno", getSmsmodeno())
            .append("smstype", getSmstype())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("operator", getOperator())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .append("modifyoperator", getModifyoperator())
            .toString();
    }
}
