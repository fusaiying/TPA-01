package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * base_bank（银行信息）对象 base_bank
 * 
 * @author sino
 * @date 2020-12-31
 */
public class BaseBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String providerCode;

    /** 开户银行编码 */
    @Excel(name = "开户银行编码")
    private String bankCode;

    /** 开户银行名称 */
    @Excel(name = "开户银行名称")
    private String bankName;

    /** 银行账号 */
    @Excel(name = "银行账号")
    private String accountNo;

    /** 银行账户名称 */
    @Excel(name = "银行账户名称")
    private String accountName;

    /** 账户类型 */
    @Excel(name = "账户类型")
    private String accountType;

    /** 银行信息描述 */
    @Excel(name = "银行信息描述")
    private String bankDetail;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setProviderCode(String providerCode)
    {
        this.providerCode = providerCode;
    }

    public String getProviderCode()
    {
        return providerCode;
    }
    public void setBankCode(String bankCode)
    {
        this.bankCode = bankCode;
    }

    public String getBankCode()
    {
        return bankCode;
    }
    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBankName()
    {
        return bankName;
    }
    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }

    public String getAccountNo()
    {
        return accountNo;
    }
    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountName()
    {
        return accountName;
    }
    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getAccountType()
    {
        return accountType;
    }
    public void setBankDetail(String bankDetail)
    {
        this.bankDetail = bankDetail;
    }

    public String getBankDetail()
    {
        return bankDetail;
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
            .append("providerCode", getProviderCode())
            .append("bankCode", getBankCode())
            .append("bankName", getBankName())
            .append("accountNo", getAccountNo())
            .append("accountName", getAccountName())
            .append("accountType", getAccountType())
            .append("bankDetail", getBankDetail())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
