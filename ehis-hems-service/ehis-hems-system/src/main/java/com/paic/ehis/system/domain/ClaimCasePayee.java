package com.paic.ehis.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 案件领款人信息对象 claim_case_payee
 * 
 * @author sino
 * @date 2021-01-09
 */
public class ClaimCasePayee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 领款人ID */
    private Long payeeId;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    /** 领款方式 */
    @Excel(name = "领款方式")
    private String payMode;

    /** 与被保人关系 */
    @Excel(name = "与被保人关系")
    private String relationIns;

    /** 领款人姓名 */
    @Excel(name = "领款人姓名")
    private String payeeName;

    /** 领款人性别 */
    @Excel(name = "领款人性别")
    private String payeeSex;

    /** 领款人证件类型 */
    @Excel(name = "领款人证件类型")
    private String payeeIdType;

    /** 领款人证件号 */
    @Excel(name = "领款人证件号")
    private String payeeIdNo;

    /** 职业编码 */
    @Excel(name = "职业编码")
    private String payeeOccupation;

    /** 国籍 */
    @Excel(name = "国籍")
    private String payeeNationality;

    /** 证件有效期止期（长期=9999-12-31） */
    @Excel(name = "证件有效期止期", readConverterExp = "长=期=9999-12-31")
    private Date idEndDate;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String payeeMobile;

    /** 开户行编码 */
    @Excel(name = "开户行编码")
    private String payeeBank;

    /** 账户属性 */
    @Excel(name = "账户属性")
    private String accAttribute;

    /** 账号 */
    @Excel(name = "账号")
    private String accNo;

    /** 分配比例 */
    @Excel(name = "分配比例")
    private BigDecimal payeeRatio;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public void setPayeeId(Long payeeId) 
    {
        this.payeeId = payeeId;
    }

    public Long getPayeeId() 
    {
        return payeeId;
    }
    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setPayMode(String payMode) 
    {
        this.payMode = payMode;
    }

    public String getPayMode() 
    {
        return payMode;
    }
    public void setRelationIns(String relationIns) 
    {
        this.relationIns = relationIns;
    }

    public String getRelationIns() 
    {
        return relationIns;
    }
    public void setPayeeName(String payeeName) 
    {
        this.payeeName = payeeName;
    }

    public String getPayeeName() 
    {
        return payeeName;
    }
    public void setPayeeSex(String payeeSex) 
    {
        this.payeeSex = payeeSex;
    }

    public String getPayeeSex() 
    {
        return payeeSex;
    }
    public void setPayeeIdType(String payeeIdType) 
    {
        this.payeeIdType = payeeIdType;
    }

    public String getPayeeIdType() 
    {
        return payeeIdType;
    }
    public void setPayeeIdNo(String payeeIdNo) 
    {
        this.payeeIdNo = payeeIdNo;
    }

    public String getPayeeIdNo() 
    {
        return payeeIdNo;
    }
    public void setPayeeOccupation(String payeeOccupation) 
    {
        this.payeeOccupation = payeeOccupation;
    }

    public String getPayeeOccupation() 
    {
        return payeeOccupation;
    }
    public void setPayeeNationality(String payeeNationality) 
    {
        this.payeeNationality = payeeNationality;
    }

    public String getPayeeNationality() 
    {
        return payeeNationality;
    }
    public void setIdEndDate(Date idEndDate) 
    {
        this.idEndDate = idEndDate;
    }

    public Date getIdEndDate() 
    {
        return idEndDate;
    }
    public void setPayeeMobile(String payeeMobile) 
    {
        this.payeeMobile = payeeMobile;
    }

    public String getPayeeMobile() 
    {
        return payeeMobile;
    }
    public void setPayeeBank(String payeeBank) 
    {
        this.payeeBank = payeeBank;
    }

    public String getPayeeBank() 
    {
        return payeeBank;
    }
    public void setAccAttribute(String accAttribute) 
    {
        this.accAttribute = accAttribute;
    }

    public String getAccAttribute() 
    {
        return accAttribute;
    }
    public void setAccNo(String accNo) 
    {
        this.accNo = accNo;
    }

    public String getAccNo() 
    {
        return accNo;
    }
    public void setPayeeRatio(BigDecimal payeeRatio) 
    {
        this.payeeRatio = payeeRatio;
    }

    public BigDecimal getPayeeRatio() 
    {
        return payeeRatio;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
            .append("payeeId", getPayeeId())
            .append("rptNo", getRptNo())
            .append("payMode", getPayMode())
            .append("relationIns", getRelationIns())
            .append("payeeName", getPayeeName())
            .append("payeeSex", getPayeeSex())
            .append("payeeIdType", getPayeeIdType())
            .append("payeeIdNo", getPayeeIdNo())
            .append("payeeOccupation", getPayeeOccupation())
            .append("payeeNationality", getPayeeNationality())
            .append("idEndDate", getIdEndDate())
            .append("payeeMobile", getPayeeMobile())
            .append("payeeBank", getPayeeBank())
            .append("accAttribute", getAccAttribute())
            .append("accNo", getAccNo())
            .append("payeeRatio", getPayeeRatio())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("address", getAddress())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
