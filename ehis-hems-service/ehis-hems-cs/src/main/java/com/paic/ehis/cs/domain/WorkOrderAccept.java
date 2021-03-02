package com.paic.ehis.cs.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 工单申请信息 对象 work_order_accept
 * 
 * @author sino
 * @date 2021-03-01
 */
public class WorkOrderAccept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单号 */
    private String workOrderNo;

    /** 业务类型 01-信息需求，02-预约，03-投诉； */
    @Excel(name = "业务类型 01-信息需求，02-预约，03-投诉；")
    private String businessType;

    /** 保单号 */
    @Excel(name = "保单号")
    private String policyNo;

    /** 分单号 */
    @Excel(name = "分单号")
    private String policyItemNo;

    /** 险种号 */
    @Excel(name = "险种号")
    private String riskCode;

    /** 被保人客户号，person_info编号 */
    @Excel(name = "被保人客户号，person_info编号")
    private String insuredNo;

    /** 被保人 */
    @Excel(name = "被保人")
    private String insuredName;

    /** 投保人客户号，person_info编号 */
    @Excel(name = "投保人客户号，person_info编号")
    private String holderNo;

    /** 投保人 */
    @Excel(name = "投保人")
    private String holderName;

    /** 受理人 */
    @Excel(name = "受理人")
    private String acceptBy;

    /** 受理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "受理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acceptTime;

    /** 处理人 仅争对获取操作的人 */
    @Excel(name = "处理人 仅争对获取操作的人")
    private String modifyBy;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /** VIP标识 */
    @Excel(name = "VIP标识")
    private String vipFlag;

    /** 出单机构 */
    @Excel(name = "出单机构")
    private String organCode;

    /** 状态 每个业务流程中的状态；如：待处理、处理中（&协办表=协办中）、处理完成、质检中、质检完成；
信息需求公共查询中状态：01-待处理；02-处理中；03-已处理；04-已完成；05-已取消； */
    @Excel(name = "状态 每个业务流程中的状态；如：待处理、处理中", readConverterExp = "&=协办表=协办中")
    private String status;

    /** 结案日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 处理时间 信息需求，预约点击处理超链接记录此时间；投诉为完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间 信息需求，预约点击处理超链接记录此时间；投诉为完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date clickTime;

    /** 有无挂起 */
    @Excel(name = "有无挂起")
    private String hangFlag;

    /** 挂起原因 */
    @Excel(name = "挂起原因")
    private String hangReason;

    public void setWorkOrderNo(String workOrderNo) 
    {
        this.workOrderNo = workOrderNo;
    }

    public String getWorkOrderNo() 
    {
        return workOrderNo;
    }
    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }
    public void setPolicyNo(String policyNo) 
    {
        this.policyNo = policyNo;
    }

    public String getPolicyNo() 
    {
        return policyNo;
    }
    public void setPolicyItemNo(String policyItemNo) 
    {
        this.policyItemNo = policyItemNo;
    }

    public String getPolicyItemNo() 
    {
        return policyItemNo;
    }
    public void setRiskCode(String riskCode) 
    {
        this.riskCode = riskCode;
    }

    public String getRiskCode() 
    {
        return riskCode;
    }
    public void setInsuredNo(String insuredNo) 
    {
        this.insuredNo = insuredNo;
    }

    public String getInsuredNo() 
    {
        return insuredNo;
    }
    public void setInsuredName(String insuredName) 
    {
        this.insuredName = insuredName;
    }

    public String getInsuredName() 
    {
        return insuredName;
    }
    public void setHolderNo(String holderNo) 
    {
        this.holderNo = holderNo;
    }

    public String getHolderNo() 
    {
        return holderNo;
    }
    public void setHolderName(String holderName) 
    {
        this.holderName = holderName;
    }

    public String getHolderName() 
    {
        return holderName;
    }
    public void setAcceptBy(String acceptBy) 
    {
        this.acceptBy = acceptBy;
    }

    public String getAcceptBy() 
    {
        return acceptBy;
    }
    public void setAcceptTime(Date acceptTime) 
    {
        this.acceptTime = acceptTime;
    }

    public Date getAcceptTime() 
    {
        return acceptTime;
    }
    public void setModifyBy(String modifyBy) 
    {
        this.modifyBy = modifyBy;
    }

    public String getModifyBy() 
    {
        return modifyBy;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }
    public void setVipFlag(String vipFlag) 
    {
        this.vipFlag = vipFlag;
    }

    public String getVipFlag() 
    {
        return vipFlag;
    }
    public void setOrganCode(String organCode) 
    {
        this.organCode = organCode;
    }

    public String getOrganCode() 
    {
        return organCode;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setClickTime(Date clickTime) 
    {
        this.clickTime = clickTime;
    }

    public Date getClickTime() 
    {
        return clickTime;
    }
    public void setHangFlag(String hangFlag) 
    {
        this.hangFlag = hangFlag;
    }

    public String getHangFlag() 
    {
        return hangFlag;
    }
    public void setHangReason(String hangReason) 
    {
        this.hangReason = hangReason;
    }

    public String getHangReason() 
    {
        return hangReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("workOrderNo", getWorkOrderNo())
            .append("businessType", getBusinessType())
            .append("policyNo", getPolicyNo())
            .append("policyItemNo", getPolicyItemNo())
            .append("riskCode", getRiskCode())
            .append("insuredNo", getInsuredNo())
            .append("insuredName", getInsuredName())
            .append("holderNo", getHolderNo())
            .append("holderName", getHolderName())
            .append("acceptBy", getAcceptBy())
            .append("acceptTime", getAcceptTime())
            .append("modifyBy", getModifyBy())
            .append("modifyTime", getModifyTime())
            .append("vipFlag", getVipFlag())
            .append("organCode", getOrganCode())
            .append("status", getStatus())
            .append("endDate", getEndDate())
            .append("clickTime", getClickTime())
            .append("hangFlag", getHangFlag())
            .append("hangReason", getHangReason())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
