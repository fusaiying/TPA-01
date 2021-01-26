package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * base_contract_annex（合约附件）对象 base_contract_annex
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseContractAnnex extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 合约编码 */
    @Excel(name = "合约编码")
    private String contractNo;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierCode;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String annexName;

    /** 附件类型 */
    @Excel(name = "附件类型")
    private String annexType;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadMan;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remak;

    /** 状态 */
    @Excel(name = "状态",readConverterExp = "Y=有效，N=无效")
    private String status;

    public void setContractNo(String contractNo) 
    {
        this.contractNo = contractNo;
    }

    public String getContractNo() 
    {
        return contractNo;
    }
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setAnnexName(String annexName) 
    {
        this.annexName = annexName;
    }

    public String getAnnexName() 
    {
        return annexName;
    }
    public void setAnnexType(String annexType) 
    {
        this.annexType = annexType;
    }

    public String getAnnexType() 
    {
        return annexType;
    }
    public void setUploadMan(String uploadMan) 
    {
        this.uploadMan = uploadMan;
    }

    public String getUploadMan() 
    {
        return uploadMan;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setRemak(String remak) 
    {
        this.remak = remak;
    }

    public String getRemak() 
    {
        return remak;
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
            .append("contractNo", getContractNo())
            .append("supplierCode", getSupplierCode())
            .append("annexName", getAnnexName())
            .append("annexType", getAnnexType())
            .append("uploadMan", getUploadMan())
            .append("uploadTime", getUploadTime())
            .append("remak", getRemak())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
