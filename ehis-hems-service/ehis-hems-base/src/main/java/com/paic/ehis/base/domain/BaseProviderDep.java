package com.paic.ehis.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * base_provider_dept(服务商科室)对象 base_provider_dep
 * 
 * @author sino
 * @date 2020-12-31
 */
@Data
public class BaseProviderDep extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    private String serialNo;

    /** 服务商编码 */
    @Excel(name = "服务商编码")
    private String providerCode;

    /** 就诊类型 */
    @Excel(name = "就诊类型")
    private String visitingType;

    /** 一级科室 */
    @Excel(name = "一级科室")
    private String firstDept;

    /** 二级科室 */
    @Excel(name = "二级科室")
    private String secondDept;

    /** 就诊楼层 */
    @Excel(name = "就诊楼层")
    private String visitingFloor;

    /** 是否特色科室 */
    @Excel(name = "是否特色科室")
    private String charactdeptFlag;

    /** 是否直结 */
    @Excel(name = "是否直结")
    private String straightknotFlag;

    /** 是否可医保卡 */
    @Excel(name = "是否可医保卡")
    private String cartevitalFlag;

    /** 是否特需 */
    @Excel(name = "是否特需")
    private String spprocurementFlag;

    /** 出诊起始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出诊起始时间", width = 30, dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    private Date callstarttime;

    /** 出诊截至时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出诊截至时间", width = 30, dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    private Date callendtime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 状态 */
    @Excel(name = "变更标志")
    private String updateFlag;

    private String visitingTypeName;

    private String orgFlag;

    public void setProviderCode(String providerCode) 
    {
        this.providerCode = providerCode;
    }

    public String getProviderCode() 
    {
        return providerCode;
    }
    public void setVisitingType(String visitingType) 
    {
        this.visitingType = visitingType;
    }

    public String getVisitingType() 
    {
        return visitingType;
    }
    public void setFirstDept(String firstDept) 
    {
        this.firstDept = firstDept;
    }

    public String getFirstDept() 
    {
        return firstDept;
    }
    public void setSecondDept(String secondDept) 
    {
        this.secondDept = secondDept;
    }

    public String getSecondDept() 
    {
        return secondDept;
    }
    public void setVisitingFloor(String visitingFloor) 
    {
        this.visitingFloor = visitingFloor;
    }

    public String getVisitingFloor() 
    {
        return visitingFloor;
    }
    public void setCharactdeptFlag(String charactdeptFlag) 
    {
        this.charactdeptFlag = charactdeptFlag;
    }

    public String getCharactdeptFlag() 
    {
        return charactdeptFlag;
    }
    public void setStraightknotFlag(String straightknotFlag) 
    {
        this.straightknotFlag = straightknotFlag;
    }

    public String getStraightknotFlag() 
    {
        return straightknotFlag;
    }
    public void setCartevitalFlag(String cartevitalFlag) 
    {
        this.cartevitalFlag = cartevitalFlag;
    }

    public String getCartevitalFlag() 
    {
        return cartevitalFlag;
    }
    public void setSpprocurementFlag(String spprocurementFlag) 
    {
        this.spprocurementFlag = spprocurementFlag;
    }

    public String getSpprocurementFlag() 
    {
        return spprocurementFlag;
    }
    public void setCallstarttime(Date callstarttime) 
    {
        this.callstarttime = callstarttime;
    }

    public Date getCallstarttime() 
    {
        return callstarttime;
    }
    public void setCallendtime(Date callendtime) 
    {
        this.callendtime = callendtime;
    }

    public Date getCallendtime() 
    {
        return callendtime;
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
            .append("visitingType", getVisitingType())
            .append("firstDept", getFirstDept())
            .append("secondDept", getSecondDept())
            .append("visitingFloor", getVisitingFloor())
            .append("charactdeptFlag", getCharactdeptFlag())
            .append("straightknotFlag", getStraightknotFlag())
            .append("cartevitalFlag", getCartevitalFlag())
            .append("spprocurementFlag", getSpprocurementFlag())
            .append("callstarttime", getCallstarttime())
            .append("callendtime", getCallendtime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
