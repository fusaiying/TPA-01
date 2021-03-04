package com.paic.ehis.claimcal.domain;


import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 出单公司信息 对象 base_issuingcompany
 *
 * @author sino
 * @date 2021-01-05
 */
public class BaseIssuingcompany extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 出单公司编码
     */
    private String companycode;

    /**
     * 出单公司名称
     */
    @Excel(name = "出单公司名称")
    private String companyname;

    /**
     * 出单公司简写名称
     */
    @Excel(name = "出单公司简写名称")
    private String simplename;

    /**
     * 状态（Y-有效，N-无效）
     */
    @Excel(name = "状态", readConverterExp = "Y=有效,N=无效")
    private String status;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getSimplename() {
        return simplename;
    }

    public void setSimplename(String simplename) {
        this.simplename = simplename;
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
                .append("companycode", getCompanycode())
                .append("companyname", getCompanyname())
                .append("simplename", getSimplename())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
