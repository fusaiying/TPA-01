package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 出单公司附件信息 对象 base_issuingcompany_attachfile
 * 
 * @author sino
 * @date 2021-01-05
 */
public class BaseIssuingcompanyAttachfile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 附件ID */
    private Long id;

    /** 出单公司编码 */
    @Excel(name = "出单公司编码")
    private String companyCode;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件后缀 */
    @Excel(name = "文件后缀")
    private String fileSuffix;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String filePath;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCompanyCode(String companyCode) 
    {
        this.companyCode = companyCode;
    }

    public String getCompanyCode() 
    {
        return companyCode;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileSuffix(String fileSuffix) 
    {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() 
    {
        return fileSuffix;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyCode", getCompanyCode())
            .append("fileName", getFileName())
            .append("fileSuffix", getFileSuffix())
            .append("filePath", getFilePath())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
