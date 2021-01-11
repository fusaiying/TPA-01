package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品审核轨迹对象 hmp_audit_track
 * 
 * @author sino
 * @date 2020-11-04
 */
public class HmpAuditTrack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审核编码 */
    private String auditno;

    /** 产品编码 */
    @Excel(name = "产品编码")
    private String productno;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productname;

    /** 管理机构 */
    @Excel(name = "管理机构")
    private String managecom;

    /** 审核结果 */
    @Excel(name = "审核结果")
    private String auditresult;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String reason;

    /** 审核人 */
    @Excel(name = "审核人")
    private String operator;

    /** 审核日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date makedate;

    /** 审核时间 */
    @Excel(name = "审核时间")
    private String maketime;

    public void setAuditno(String auditno) 
    {
        this.auditno = auditno;
    }

    public String getAuditno() 
    {
        return auditno;
    }
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
    public void setManagecom(String managecom) 
    {
        this.managecom = managecom;
    }

    public String getManagecom() 
    {
        return managecom;
    }
    public void setAuditresult(String auditresult) 
    {
        this.auditresult = auditresult;
    }

    public String getAuditresult() 
    {
        return auditresult;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("auditno", getAuditno())
            .append("productno", getProductno())
            .append("productname", getProductname())
            .append("managecom", getManagecom())
            .append("auditresult", getAuditresult())
            .append("reason", getReason())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .toString();
    }
}
