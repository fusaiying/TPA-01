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
public class ClaimCaseStanding extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 报案号
     */
    private String rptno;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码")
    private String idno;

    /**
     * 被保险人姓名
     */
    @Excel(name = "被保险人姓名")
    private String name;

    /**
     * 理赔材料
     */
    @Excel(name = "理赔材料")
    private String claimmaterials;

    /**
     * 其他（案件去向）
     */
    @Excel(name = "其他", readConverterExp = "案=件去向")
    private String otherinfo;

    /**
     * 状态（Y-有效，N-无效）
     */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public String getRptno() {
        return rptno;
    }

    public void setRptno(String rptno) {
        this.rptno = rptno;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClaimmaterials() {
        return claimmaterials;
    }

    public void setClaimmaterials(String claimmaterials) {
        this.claimmaterials = claimmaterials;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("rptno", getRptno())
                .append("idno", getIdno())
                .append("name", getName())
                .append("claimmaterials", getClaimmaterials())
                .append("remark", getRemark())
                .append("otherinfo", getOtherinfo())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
