package com.paic.ehis.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class IssuingCompanyVo implements Serializable {

    private String companycode;

    private String companyname;

    private String simplename;

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

    @Override
    public String toString() {
        return "IssuingCompanyVo{" +
                "companycode='" + companycode + '\'' +
                ", companyname='" + companyname + '\'' +
                ", simplename='" + simplename + '\'' +
                '}';
    }
}
