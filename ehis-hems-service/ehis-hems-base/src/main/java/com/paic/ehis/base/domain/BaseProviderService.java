package com.paic.ehis.base.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * base_provider_service(服务商服务信息)对象 base_provider_service
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseProviderService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 服务商编码 */
    private String serialNo;

    /** 服务商编码 */
    private String providerCode;

    /** 所属医疗网络类型 */
    @Excel(name = "所属医疗网络类型")
    private String networkType;

    /** 医院类别 */
    @Excel(name = "医院类别")
    private String category;

    /** 医院分类 */
    @Excel(name = "医院分类")
    private String classification;

    /** 医院评分 */
    @Excel(name = "医院评分")
    private String grade;

    /** 是否同步显示至外网 */
    @Excel(name = "是否同步显示至外网")
    private String displaynetwork;

    /** 是否在官网显示 */
    @Excel(name = "是否在官网显示")
    private String officialwebsite;

    /** 是否优选医院 */
    @Excel(name = "是否优选医院")
    private String prehospital;

    /** 是否对所有保单开放 */
    @Excel(name = "是否对所有保单开放")
    private String allcontopen;

    /** 医院合作状态 */
    @Excel(name = "医院合作状态")
    private String cooperationStatus;

    /** 服务定位Str */
    @Excel(name = "服务定位")
    private List<String> servicelocator;

    /** 服务定位Str */
    @Excel(name = "服务定位Str")
    private String servicelocatorStr;

    /** 医院合作状态 */
    @Excel(name = "网络医院直结类型")
    private List<String> networkHospitalType;

    /** 医院合作状态 */
    @Excel(name = "特殊语种服务")
    private List<String> speciallanguage;

    /** 医院合作状态 */
    @Excel(name = "网络医院直结类型Str")
    private String networkHospitalTypeStr;

    /** 医院合作状态 */
    @Excel(name = "特殊语种服务Str")
    private String speciallanguageStr;

    /** 服务特色 */
    @Excel(name = "服务特色")
    private String sellingpoint;

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

    /** 次均控费 */
    @Excel(name = "次均控费")
    private String code;

    /** 次均控费类型 */
    @Excel(name = "次均控费类型")
    private String type;

    /** 就诊次数 */
    @Excel(name = "就诊次数")
    private String adviceNum;

    /** 对外备注 */
    @Excel(name = "对外备注")
    private String foreignnote;

    /** 折扣信息 */
    @Excel(name = "折扣信息")
    private String discountinfo;

    /** 对内备注 */
    @Excel(name = "对内备注")
    private String internalnote;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 状态 */
    @Excel(name = "变更标志")
    private String updateFlag;


    /** 是否虚拟机构 */
    @Excel(name = "是否虚拟机构")
    private String virtualOrg;

    @Excel(name = "是否全国前十")
    private String topten;

    /** 是否专科医院 */
    @Excel(name = "是否专科医院")
    private String specializedHospital;

    /** 是否当地前三 */
    @Excel(name = "是否当地前三")
    private String topthird;

    /** 是否全国专科前五 */
    @Excel(name = "是否全国专科前五")
    private String topfive;

    /** 二证齐全是否发预授权书 */
    @Excel(name = "二证齐全是否发预授权书")
    private String continentFlag;

    /** 二证齐全说明 */
    @Excel(name = "二证齐全说明")
    private String continent;

    /** 预授权书发送及确认方式 */
    @Excel(name = "预授权书发送及确认方式")
    private String preauthsendway;

    /** 导检/陪检/驻点 */
    @Excel(name = "导检/陪检/驻点")
    private String leadFlag;


    private String orgFlag;

    private String weekendsWorking;




    public void setProviderCode(String providerCode) 
    {
        this.providerCode = providerCode;
    }

    public String getProviderCode() 
    {
        return providerCode;
    }
    public void setNetworkType(String networkType) 
    {
        this.networkType = networkType;
    }

    public String getNetworkType() 
    {
        return networkType;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setClassification(String classification) 
    {
        this.classification = classification;
    }

    public String getClassification() 
    {
        return classification;
    }
    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }
    public void setDisplaynetwork(String displaynetwork) 
    {
        this.displaynetwork = displaynetwork;
    }

    public String getDisplaynetwork() 
    {
        return displaynetwork;
    }
    public void setOfficialwebsite(String officialwebsite) 
    {
        this.officialwebsite = officialwebsite;
    }

    public String getOfficialwebsite() 
    {
        return officialwebsite;
    }
    public void setPrehospital(String prehospital) 
    {
        this.prehospital = prehospital;
    }

    public String getPrehospital() 
    {
        return prehospital;
    }
    public void setAllcontopen(String allcontopen) 
    {
        this.allcontopen = allcontopen;
    }

    public String getAllcontopen() 
    {
        return allcontopen;
    }
    public void setCooperationStatus(String cooperationStatus) 
    {
        this.cooperationStatus = cooperationStatus;
    }

    public String getCooperationStatus() 
    {
        return cooperationStatus;
    }
    public void setSellingpoint(String sellingpoint) 
    {
        this.sellingpoint = sellingpoint;
    }

    public String getSellingpoint() 
    {
        return sellingpoint;
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
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setAdviceNum(String adviceNum) 
    {
        this.adviceNum = adviceNum;
    }

    public String getAdviceNum() 
    {
        return adviceNum;
    }
    public void setForeignnote(String foreignnote) 
    {
        this.foreignnote = foreignnote;
    }

    public String getForeignnote() 
    {
        return foreignnote;
    }
    public void setDiscountinfo(String discountinfo) 
    {
        this.discountinfo = discountinfo;
    }

    public String getDiscountinfo() 
    {
        return discountinfo;
    }
    public void setInternalnote(String internalnote) 
    {
        this.internalnote = internalnote;
    }

    public String getInternalnote() 
    {
        return internalnote;
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
            .append("networkType", getNetworkType())
            .append("category", getCategory())
            .append("classification", getClassification())
            .append("grade", getGrade())
            .append("displaynetwork", getDisplaynetwork())
            .append("officialwebsite", getOfficialwebsite())
            .append("prehospital", getPrehospital())
            .append("allcontopen", getAllcontopen())
            .append("cooperationStatus", getCooperationStatus())
            .append("sellingpoint", getSellingpoint())
            .append("treatmentDiscount", getTreatmentDiscount())
            .append("examineDiscount", getExamineDiscount())
            .append("bedDiscount", getBedDiscount())
            .append("allowance", getAllowance())
            .append("specialDiscount", getSpecialDiscount())
            .append("costs", getCosts())
            .append("project", getProject())
            .append("code", getCode())
            .append("type", getType())
            .append("adviceNum", getAdviceNum())
            .append("foreignnote", getForeignnote())
            .append("discountinfo", getDiscountinfo())
            .append("internalnote", getInternalnote())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
