package com.paic.ehis.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件信息对象 hpfileinputdownload
 * 
 * @author sino
 * @date 2020-10-16
 */
public class HmpFileInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件ID */
    private String filecode;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String filename;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filepath;

    /** 文件来源 */
    @Excel(name = "文件来源")
    private String filesource;

    /** 备注1-描述 */
    @Excel(name = "备注1-描述")
    private String alternatefield1;

    /** 备注2 */
    @Excel(name = "备注2")
    private String alternatefield2;

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

    /** 修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifydate;

    /** 修改时间 */
    @Excel(name = "修改时间")
    private String modifytime;

    public void setFilecode(String filecode)
    {
        this.filecode = filecode;
    }

    public String getFilecode()
    {
        return filecode;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setFilepath(String filepath) 
    {
        this.filepath = filepath;
    }

    public String getFilepath() 
    {
        return filepath;
    }
    public void setFilesource(String filesource) 
    {
        this.filesource = filesource;
    }

    public String getFilesource() 
    {
        return filesource;
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
            .append("filecode", getFilecode())
            .append("filename", getFilename())
            .append("filepath", getFilepath())
            .append("filesource", getFilesource())
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
