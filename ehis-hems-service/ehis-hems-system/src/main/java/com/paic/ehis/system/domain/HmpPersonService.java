package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 就诊人服务信息对象 hmp_person_service
 * 
 * @author sino
 * @date 2020-11-11
 */
public class HmpPersonService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 就诊人编码 */
    private String personcode;

    /** 就诊人名称 */
    @Excel(name = "就诊人名称")
    private String name;

    /** 就诊人性别 */
    @Excel(name = "就诊人性别")
    private String sax;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idcardtype;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idcardno;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phone;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productcode;

    /** 服务编码 */
    @Excel(name = "服务编码")
    private String servicecode;

    /** 服务机构编码 */
    @Excel(name = "服务机构编码")
    private String servicecomcode;

    /** 保单号 */
    @Excel(name = "保单号")
    private String contno;

    /** 服务卡号码 */
    @Excel(name = "服务卡号码")
    private String servcardno;

    /** 申请方式 */
    @Excel(name = "申请方式")
    private String applymode;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applydate;

    /** 管理机构 */
    @Excel(name = "管理机构")
    private String managecom;

    /** 当前状态 */
    @Excel(name = "当前状态")
    private String status;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date makedate;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String maketime;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifydate;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String modifytime;

    public void setPersoncode(String personcode) 
    {
        this.personcode = personcode;
    }

    public String getPersoncode() 
    {
        return personcode;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSax(String sax) 
    {
        this.sax = sax;
    }

    public String getSax() 
    {
        return sax;
    }
    public void setIdcardtype(String idcardtype) 
    {
        this.idcardtype = idcardtype;
    }

    public String getIdcardtype() 
    {
        return idcardtype;
    }
    public void setIdcardno(String idcardno) 
    {
        this.idcardno = idcardno;
    }

    public String getIdcardno() 
    {
        return idcardno;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setProductcode(String productcode) 
    {
        this.productcode = productcode;
    }

    public String getProductcode() 
    {
        return productcode;
    }
    public void setServicecode(String servicecode) 
    {
        this.servicecode = servicecode;
    }

    public String getServicecode() 
    {
        return servicecode;
    }
    public void setServicecomcode(String servicecomcode) 
    {
        this.servicecomcode = servicecomcode;
    }

    public String getServicecomcode() 
    {
        return servicecomcode;
    }
    public void setContno(String contno) 
    {
        this.contno = contno;
    }

    public String getContno() 
    {
        return contno;
    }
    public void setServcardno(String servcardno) 
    {
        this.servcardno = servcardno;
    }

    public String getServcardno() 
    {
        return servcardno;
    }
    public void setApplymode(String applymode) 
    {
        this.applymode = applymode;
    }

    public String getApplymode() 
    {
        return applymode;
    }
    public void setApplydate(Date applydate) 
    {
        this.applydate = applydate;
    }

    public Date getApplydate() 
    {
        return applydate;
    }
    public void setManagecom(String managecom) 
    {
        this.managecom = managecom;
    }

    public String getManagecom() 
    {
        return managecom;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("personcode", getPersoncode())
            .append("name", getName())
            .append("sax", getSax())
            .append("idcardtype", getIdcardtype())
            .append("idcardno", getIdcardno())
            .append("phone", getPhone())
            .append("productcode", getProductcode())
            .append("servicecode", getServicecode())
            .append("servicecomcode", getServicecomcode())
            .append("contno", getContno())
            .append("servcardno", getServcardno())
            .append("applymode", getApplymode())
            .append("applydate", getApplydate())
            .append("managecom", getManagecom())
            .append("status", getStatus())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .toString();
    }
}
