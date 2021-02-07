package com.paic.ehis.claimflow.domain;

import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 案件追讨白名单对象 claim_debt_whitelist
 *
 * @author sino
 * @date 2021-01-20
 */
public class ClaimDebtWhitelist extends BaseEntity
{

    private static final long serialVersionUID = 1L;

    /** 追讨白名单ID */
    private Long debtWhitelistId;

    /** 被保人客户号 */
    @Excel(name = "被保人客户号")
    private String insuredNo;

    /** 等级 */
    @Excel(name = "等级")
    private String level;

    /** 追缴通知标记 */
    @Excel(name = "追缴通知标记")
    private String recMessageFlag;

    /** 追讨金额上限 */
    @Excel(name = "追讨金额上限")
    private BigDecimal debtAmountUp;

    /** 数据状态（Y-有效，N-无效） */
    @Excel(name = "数据状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    /** 被保人姓名 */
    @Excel(name = "被保人姓名")
    private String name;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String  idType;

    /** 被保人证件号 */
    @Excel(name = "被保人证件号")
    private String idNo;

    /*性别*/
    @Excel(name = "性别")
    private String sex;
    /*生日*/
    @Excel(name = "生日")
    private Date birthday;

    /** 剩余金额 */
    @Excel(name = "剩余金额")
    private BigDecimal residualAmount;

    /** 报案号 */
    @Excel(name = "报案号")
    private String rptNo;

    public String getRptNo() {
        return rptNo;
    }

    public void setRptNo(String rptNo) {
        this.rptNo = rptNo;
    }

    public BigDecimal getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(BigDecimal residualAmount) {
        this.residualAmount = residualAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idNoType) {
        this.idType = idNoType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setDebtWhitelistId(Long debtWhitelistId)
    {
        this.debtWhitelistId = debtWhitelistId;
    }

    public Long getDebtWhitelistId()
    {
        return debtWhitelistId;
    }
    public void setInsuredNo(String insuredNo)
    {
        this.insuredNo = insuredNo;
    }

    public String getInsuredNo()
    {
        return insuredNo;
    }
    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLevel()
    {
        return level;
    }
    public void setRecMessageFlag(String recMessageFlag)
    {
        this.recMessageFlag = recMessageFlag;
    }

    public String getRecMessageFlag()
    {
        return recMessageFlag;
    }
    public void setDebtAmountUp(BigDecimal debtAmountUp)
    {
        this.debtAmountUp = debtAmountUp;
    }

    public BigDecimal getDebtAmountUp()
    {
        return debtAmountUp;
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
                .append("debtWhitelistId", getDebtWhitelistId())
                .append("insuredNo", getInsuredNo())
                .append("level", getLevel())
                .append("recMessageFlag", getRecMessageFlag())
                .append("debtAmountUp", getDebtAmountUp())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
