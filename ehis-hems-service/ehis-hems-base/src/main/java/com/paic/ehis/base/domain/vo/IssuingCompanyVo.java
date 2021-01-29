package com.paic.ehis.base.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IssuingCompanyVo implements Serializable {

    private String companycode;

    private String companyname;

    private String simplename;

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


    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    @Override
    public String toString() {
        return "IssuingCompanyVo{" +
                "companycode='" + companycode + '\'' +
                ", companyname='" + companyname + '\'' +
                ", simplename='" + simplename + '\'' +
                ", riskName='" + riskName + '\'' +
                '}';
    }
}
