package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 服务项目对象 hmp_serv_project
 * 
 * @author sino
 * @date 2020-11-09
 */
public class HmpServProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务项目编码 */
    private String projectcode;

    /** 节点编码 */
    private String nodecode;

    /** 服务项目名称 */
    @Excel(name = "服务项目名称")
    private String projectname;

    /** 服务项目分类 */
    @Excel(name = "服务项目分类")
    private String projecttype;

    /** 服务项目状态 */
    @Excel(name = "服务项目状态")
    private String status;

    /** 节点名称 */
    @Excel(name = "节点名称")
    private String nodename;

    /** 是否有效 */
    @Excel(name = "是否有效")
    private String validflag;

    /** 是否可结束 */
    @Excel(name = "是否可结束")
    private String endflag;

    /** 是否可取消 */
    @Excel(name = "是否可取消")
    private String cancelflag;

    /** 是否可回退 */
    @Excel(name = "是否可回退")
    private String rollbackflag;

    /** 流程条件 */
    @Excel(name = "流程条件")
    private String processflag;

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

    public void setProjectcode(String projectcode) 
    {
        this.projectcode = projectcode;
    }

    public String getProjectcode() 
    {
        return projectcode;
    }
    public void setNodecode(String nodecode) 
    {
        this.nodecode = nodecode;
    }

    public String getNodecode() 
    {
        return nodecode;
    }
    public void setProjectname(String projectname) 
    {
        this.projectname = projectname;
    }

    public String getProjectname() 
    {
        return projectname;
    }
    public void setProjecttype(String projecttype) 
    {
        this.projecttype = projecttype;
    }

    public String getProjecttype() 
    {
        return projecttype;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setNodename(String nodename) 
    {
        this.nodename = nodename;
    }

    public String getNodename() 
    {
        return nodename;
    }
    public void setValidflag(String validflag) 
    {
        this.validflag = validflag;
    }

    public String getValidflag() 
    {
        return validflag;
    }
    public void setEndflag(String endflag) 
    {
        this.endflag = endflag;
    }

    public String getEndflag() 
    {
        return endflag;
    }
    public void setCancelflag(String cancelflag) 
    {
        this.cancelflag = cancelflag;
    }

    public String getCancelflag() 
    {
        return cancelflag;
    }
    public void setRollbackflag(String rollbackflag) 
    {
        this.rollbackflag = rollbackflag;
    }

    public String getRollbackflag() 
    {
        return rollbackflag;
    }
    public void setProcessflag(String processflag) 
    {
        this.processflag = processflag;
    }

    public String getProcessflag() 
    {
        return processflag;
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
            .append("projectcode", getProjectcode())
            .append("nodecode", getNodecode())
            .append("projectname", getProjectname())
            .append("projecttype", getProjecttype())
            .append("status", getStatus())
            .append("nodename", getNodename())
            .append("validflag", getValidflag())
            .append("endflag", getEndflag())
            .append("cancelflag", getCancelflag())
            .append("rollbackflag", getRollbackflag())
            .append("processflag", getProcessflag())
            .append("operator", getOperator())
            .append("makedate", getMakedate())
            .append("maketime", getMaketime())
            .append("modifydate", getModifydate())
            .append("modifytime", getModifytime())
            .toString();
    }
}
