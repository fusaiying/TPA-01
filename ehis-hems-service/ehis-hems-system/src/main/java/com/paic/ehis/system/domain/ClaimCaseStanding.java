package com.paic.ehis.system.domain;


import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.paic.ehis.common.core.annotation.Excel;

/**
 * 报案台账信息 对象 claim_case_standing
 * 
 * @author sino
 * @date 2021-01-05
 */
public class ClaimCaseStanding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报案号 */
    private String rptNo;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idNo;

    /** 被保险人姓名 */
    @Excel(name = "被保险人姓名")
    private String name;

    /** 理赔材料 */
    @Excel(name = "理赔材料")
    private String claimMaterials;

    /** 其他（案件去向） */
    @Excel(name = "其他", readConverterExp = "案=件去向")
    private String otherInfo;

    /** 状态（Y-有效，N-无效） */
    @Excel(name = "状态", readConverterExp = "Y=-有效，N-无效")
    private String status;

    public void setRptNo(String rptNo) 
    {
        this.rptNo = rptNo;
    }

    public String getRptNo() 
    {
        return rptNo;
    }
    public void setIdNo(String idNo) 
    {
        this.idNo = idNo;
    }

    public String getIdNo() 
    {
        return idNo;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setClaimMaterials(String claimMaterials) 
    {
        this.claimMaterials = claimMaterials;
    }

    public String getClaimMaterials() 
    {
        return claimMaterials;
    }
    public void setOtherInfo(String otherInfo) 
    {
        this.otherInfo = otherInfo;
    }

    public String getOtherInfo() 
    {
        return otherInfo;
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
            .append("rptNo", getRptNo())
            .append("idNo", getIdNo())
            .append("name", getName())
            .append("claimMaterials", getClaimMaterials())
            .append("remark", getRemark())
            .append("otherInfo", getOtherInfo())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
