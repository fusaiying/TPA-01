package com.paic.ehis.order.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * service_balance_file_info（服务结算文件信息）对象 service_balance_file_info
 * 
 * @author sino
 * @date 2021-02-07
 */
public class ServiceBalanceFileInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private Long serialNo;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件来源 */
    @Excel(name = "文件来源")
    private String fileSource;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setSerialNo(Long serialNo) 
    {
        this.serialNo = serialNo;
    }

    public Long getSerialNo() 
    {
        return serialNo;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileSource(String fileSource) 
    {
        this.fileSource = fileSource;
    }

    public String getFileSource() 
    {
        return fileSource;
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
            .append("serialNo", getSerialNo())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileSource", getFileSource())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
