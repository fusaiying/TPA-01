package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * base_supplier_contract_bak（供应商合约）对象 base_supplier_contract_bak
 * 
 * @author sino
 * @date 2021-01-08
 */
@Data
public class BaseSupplierContractBak extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机构类别 */
    private String providerType;

    /** 联系人 */
    private String liaison;

    /** 自动续约 */
    private String renewFlag;

    /** 流水号 */
    private String serialNo;

    /** 合约编码 */
    @Excel(name = "合约编码")
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

    /** 服务机构ID */
    @Excel(name = "服务机构ID")
    private String providerCode;

    /** 服务机构名称 */
    @Excel(name = "服务机构名称")
    private String providerName;

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

    /** 合约终止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合约终止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 合约截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合约截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expiryDate;

    /** 提前协商天数 */
    @Excel(name = "提前协商天数")
    private String contractadvance;

    /** 电子邮件 */
    @Excel(name = "电子邮件")
    private String email;

    /** 合约甲方 */
    @Excel(name = "合约甲方")
    private String contractPartyA;

    /** 合约乙方 */
    @Excel(name = "合约乙方")
    private String contractPartyB;

    /** 关联合约 */
    @Excel(name = "关联合约")
    private String connectedContract;

    /** 合约丙方 */
    @Excel(name = "合约丙方")
    private String contractPartyC;

    /** 押金金额 */
    @Excel(name = "押金金额")
    private BigDecimal deposit;

    /** 合作单位 */
    @Excel(name = "合作单位")
    private String cooperativeUnit;

    /** 直结类型 */
    @Excel(name = "直结类型")
    private String straight;

    /** 标志 */
    @Excel(name = "标志")
    private String flag;

    /** 合约控费标志 */
    @Excel(name = "合约控费标志")
    private String contractControlFlag;

    /** 诊疗费折扣 */
    @Excel(name = "诊疗费折扣")
    private String treatmentDiscount;

    /** 检查费折扣 */
    @Excel(name = "检查费折扣")
    private String examineDiscount;

    /** 床位费折扣 */
    @Excel(name = "床位费折扣")
    private String bedDiscount;

    /** 护理费折扣 */
    @Excel(name = "护理费折扣")
    private String allowance;

    /** 特殊费折扣信息 */
    @Excel(name = "特殊费折扣信息")
    private String specialDiscount;

    /** 医药费折扣 */
    @Excel(name = "医药费折扣")
    private String costs;

    /** 折扣除外项目 */
    @Excel(name = "折扣除外项目")
    private String project;

    /** 折扣信息 */
    @Excel(name = "折扣信息")
    private String discountinfo;

    /** 次均控费 */
    @Excel(name = "次均控费")
    private String averageCost;

    /** 次均控费类型 */
    @Excel(name = "次均控费类型")
    private String type;

    /** 就诊次数 */
    @Excel(name = "就诊次数")
    private String advicenum;

    /** 次均控费除外项目 */
    @Excel(name = "次均控费除外项目")
    private String averageCostExcept;

    /** 合同分类 */
    @Excel(name = "合同分类")
    private String contractsort;

    /** 合约终止原因 */
    @Excel(name = "合约终止原因")
    private String reason;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 办公电话 */
    @Excel(name = "办公电话")
    private String tel;

    /** 盒脊编号 */
    @Excel(name = "盒脊编号")
    private String boxRidgeCode;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setSerialNo(String serialNo) 
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo() 
    {
        return serialNo;
    }
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
    public void setProviderCode(String providerCode) 
    {
        this.providerCode = providerCode;
    }

    public String getProviderCode() 
    {
        return providerCode;
    }
    public void setProviderName(String providerName) 
    {
        this.providerName = providerName;
    }

    public String getProviderName() 
    {
        return providerName;
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
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
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
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setContractPartyA(String contractPartyA) 
    {
        this.contractPartyA = contractPartyA;
    }

    public String getContractPartyA() 
    {
        return contractPartyA;
    }
    public void setContractPartyB(String contractPartyB) 
    {
        this.contractPartyB = contractPartyB;
    }

    public String getContractPartyB() 
    {
        return contractPartyB;
    }
    public void setConnectedContract(String connectedContract) 
    {
        this.connectedContract = connectedContract;
    }

    public String getConnectedContract() 
    {
        return connectedContract;
    }
    public void setContractPartyC(String contractPartyC) 
    {
        this.contractPartyC = contractPartyC;
    }

    public String getContractPartyC() 
    {
        return contractPartyC;
    }
    public void setDeposit(BigDecimal deposit) 
    {
        this.deposit = deposit;
    }

    public BigDecimal getDeposit() 
    {
        return deposit;
    }
    public void setCooperativeUnit(String cooperativeUnit) 
    {
        this.cooperativeUnit = cooperativeUnit;
    }

    public String getCooperativeUnit() 
    {
        return cooperativeUnit;
    }
    public void setStraight(String straight) 
    {
        this.straight = straight;
    }

    public String getStraight() 
    {
        return straight;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }

    public String getFlag() 
    {
        return flag;
    }
    public void setContractControlFlag(String contractControlFlag) 
    {
        this.contractControlFlag = contractControlFlag;
    }

    public String getContractControlFlag() 
    {
        return contractControlFlag;
    }
    public void setTreatmentDiscount(String treatmentDiscount) 
    {
        this.treatmentDiscount = treatmentDiscount;
    }

    public String getTreatmentDiscount() 
    {
        return treatmentDiscount;
    }
    public void setExamineDiscount(String examineDiscount) 
    {
        this.examineDiscount = examineDiscount;
    }

    public String getExamineDiscount() 
    {
        return examineDiscount;
    }
    public void setBedDiscount(String bedDiscount) 
    {
        this.bedDiscount = bedDiscount;
    }

    public String getBedDiscount() 
    {
        return bedDiscount;
    }
    public void setAllowance(String allowance) 
    {
        this.allowance = allowance;
    }

    public String getAllowance() 
    {
        return allowance;
    }
    public void setSpecialDiscount(String specialDiscount) 
    {
        this.specialDiscount = specialDiscount;
    }

    public String getSpecialDiscount() 
    {
        return specialDiscount;
    }
    public void setCosts(String costs) 
    {
        this.costs = costs;
    }

    public String getCosts() 
    {
        return costs;
    }
    public void setProject(String project) 
    {
        this.project = project;
    }

    public String getProject() 
    {
        return project;
    }
    public void setDiscountinfo(String discountinfo) 
    {
        this.discountinfo = discountinfo;
    }

    public String getDiscountinfo() 
    {
        return discountinfo;
    }
    public void setAverageCost(String averageCost) 
    {
        this.averageCost = averageCost;
    }

    public String getAverageCost() 
    {
        return averageCost;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setAdvicenum(String advicenum) 
    {
        this.advicenum = advicenum;
    }

    public String getAdvicenum() 
    {
        return advicenum;
    }
    public void setAverageCostExcept(String averageCostExcept) 
    {
        this.averageCostExcept = averageCostExcept;
    }

    public String getAverageCostExcept() 
    {
        return averageCostExcept;
    }
    public void setContractsort(String contractsort) 
    {
        this.contractsort = contractsort;
    }

    public String getContractsort() 
    {
        return contractsort;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setBoxRidgeCode(String boxRidgeCode) 
    {
        this.boxRidgeCode = boxRidgeCode;
    }

    public String getBoxRidgeCode() 
    {
        return boxRidgeCode;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getLiaison() {
        return liaison;
    }

    public void setLiaison(String liaison) {
        this.liaison = liaison;
    }

    public String getRenewFlag() {
        return renewFlag;
    }

    public void setRenewFlag(String renewFlag) {
        this.renewFlag = renewFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("serialNo", getSerialNo())
            .append("contractNo", getContractNo())
            .append("contractName", getContractName())
            .append("contractType", getContractType())
            .append("servcomNo", getServcomNo())
            .append("providerCode", getProviderCode())
            .append("providerName", getProviderName())
            .append("contracttermType", getContracttermType())
            .append("signDate", getSignDate())
            .append("cvaliDate", getCvaliDate())
            .append("endDate", getEndDate())
            .append("expiryDate", getExpiryDate())
            .append("contractadvance", getContractadvance())
            .append("email", getEmail())
            .append("contractPartyA", getContractPartyA())
            .append("contractPartyB", getContractPartyB())
            .append("connectedContract", getConnectedContract())
            .append("contractPartyC", getContractPartyC())
            .append("deposit", getDeposit())
            .append("cooperativeUnit", getCooperativeUnit())
            .append("straight", getStraight())
            .append("flag", getFlag())
            .append("contractControlFlag", getContractControlFlag())
            .append("treatmentDiscount", getTreatmentDiscount())
            .append("examineDiscount", getExamineDiscount())
            .append("bedDiscount", getBedDiscount())
            .append("allowance", getAllowance())
            .append("specialDiscount", getSpecialDiscount())
            .append("costs", getCosts())
            .append("project", getProject())
            .append("discountinfo", getDiscountinfo())
            .append("averageCost", getAverageCost())
            .append("type", getType())
            .append("advicenum", getAdvicenum())
            .append("averageCostExcept", getAverageCostExcept())
            .append("contractsort", getContractsort())
            .append("remark", getRemark())
            .append("reason", getReason())
            .append("phone", getPhone())
            .append("tel", getTel())
            .append("boxRidgeCode", getBoxRidgeCode())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
