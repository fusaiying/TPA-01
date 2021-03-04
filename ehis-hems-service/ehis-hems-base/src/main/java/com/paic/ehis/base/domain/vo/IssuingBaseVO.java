package com.paic.ehis.base.domain.vo;

import com.paic.ehis.common.core.annotation.Excel;

import java.io.Serializable;

public class IssuingBaseVO implements Serializable {
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

    private String riskName;

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

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    @Override
    public String toString() {
        return "IssuingBaseVO{" +
                "companycode='" + companycode + '\'' +
                ", companyname='" + companyname + '\'' +
                ", simplename='" + simplename + '\'' +
                ", status='" + status + '\'' +
                ", riskName='" + riskName + '\'' +
                '}';
    }
}
