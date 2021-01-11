package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * base_supplier_contract（供应商合约）对象 base_supplier_contract
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseSupplierContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合约编码 */
    private String contractNo;


    /** 前三个月日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dBefore;


    /** 现在日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dNow;

    /** 合约名称 */
    @Excel(name = "合约名称")
    private String contractName;

    /** 合约类型 */
    @Excel(name = "合约类型")
    private String contractType;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String servcomNo;

    /** 合约期限类型 */
    @Excel(name = "合约期限类型")
    private String contracttermType;

    /** 合约签订日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合约签订日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signDate;

    /** 合约生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合约生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cvaliDate;

    /** 合约截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合约截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiryDate;

    /** 提前协商天数 */
    @Excel(name = "提前协商天数")
    private String contractadvance;

    /** 合同分类 */
    @Excel(name = "合同分类")
    private String contractsort;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setContractNo(String contractNo) 
    {
        this.contractNo = contractNo;
    }

    public String getContractNo() 
    {
        return contractNo;
    }
    public void setContractName(String contractName) 
    {
        this.contractName = contractName;
    }

    public String getContractName() 
    {
        return contractName;
    }
    public void setContractType(String contractType) 
    {
        this.contractType = contractType;
    }

    public String getContractType() 
    {
        return contractType;
    }
    public void setServcomNo(String servcomNo) 
    {
        this.servcomNo = servcomNo;
    }

    public String getServcomNo() 
    {
        return servcomNo;
    }
    public void setContracttermType(String contracttermType) 
    {
        this.contracttermType = contracttermType;
    }

    public String getContracttermType() 
    {
        return contracttermType;
    }
    public void setSignDate(Date signDate) 
    {
        this.signDate = signDate;
    }

    public Date getSignDate() 
    {
        return signDate;
    }
    public void setCvaliDate(Date cvaliDate) 
    {
        this.cvaliDate = cvaliDate;
    }

    public Date getCvaliDate() 
    {
        return cvaliDate;
    }
    public void setExpiryDate(Date expiryDate) 
    {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() 
    {
        return expiryDate;
    }
    public void setContractadvance(String contractadvance) 
    {
        this.contractadvance = contractadvance;
    }

    public String getContractadvance() 
    {
        return contractadvance;
    }
    public void setContractsort(String contractsort) 
    {
        this.contractsort = contractsort;
    }

    public String getContractsort() 
    {
        return contractsort;
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
            .append("contractName", getContractName())
            .append("contractType", getContractType())
            .append("servcomNo", getServcomNo())
            .append("contracttermType", getContracttermType())
            .append("signDate", getSignDate())
            .append("cvaliDate", getCvaliDate())
            .append("expiryDate", getExpiryDate())
            .append("contractadvance", getContractadvance())
            .append("contractsort", getContractsort())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
