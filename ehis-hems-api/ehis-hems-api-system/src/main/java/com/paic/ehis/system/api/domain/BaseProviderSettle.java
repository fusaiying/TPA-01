package com.paic.ehis.system.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * base_provider_settle(服务商结算信息)对象 base_provider_settle
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseProviderSettle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务商编码 */
    private String providerCode;

    /** 流水号 */
    private String serialNo;

    /** 结算币种 */
    @Excel(name = "结算币种")
    private String currency;

    /** 是否仅结算理赔责任 */
    @Excel(name = "是否仅结算理赔责任")
    private String claimFlag;

    /** 支付通知时间 */
    @Excel(name = "支付通知时间")
    private String noticeDay;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 状态 */
    @Excel(name = "变更标志")
    private String updateFlag;

    private String currencyName;

    private String claimFlagName;

    private String orgFlag;

    public void setProviderCode(String providerCode) 
    {
        this.providerCode = providerCode;
    }

    public String getProviderCode() 
    {
        return providerCode;
    }
    public void setCurrency(String currency) 
    {
        this.currency = currency;
    }

    public String getCurrency() 
    {
        return currency;
    }
    public void setClaimFlag(String claimFlag) 
    {
        this.claimFlag = claimFlag;
    }

    public String getClaimFlag() 
    {
        return claimFlag;
    }
    public void setNoticeDay(String noticeDay) 
    {
        this.noticeDay = noticeDay;
    }

    public String getNoticeDay() 
    {
        return noticeDay;
    }
    public void setEffectTime(Date effectTime) 
    {
        this.effectTime = effectTime;
    }

    public Date getEffectTime() 
    {
        return effectTime;
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
            .append("providerCode", getProviderCode())
            .append("currency", getCurrency())
            .append("claimFlag", getClaimFlag())
            .append("noticeDay", getNoticeDay())
            .append("effectTime", getEffectTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
