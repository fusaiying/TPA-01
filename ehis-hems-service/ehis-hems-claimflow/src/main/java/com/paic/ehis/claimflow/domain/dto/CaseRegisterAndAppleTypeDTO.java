package com.paic.ehis.claimflow.domain.dto;



import com.paic.ehis.claimflow.domain.ClaimCaseAccept;

import java.io.Serializable;
import java.util.List;

public class CaseRegisterAndAppleTypeDTO implements Serializable {
    private ClaimCaseAccept claimCaseAccept;//案件受理信息表对象

    private List<String> applyTypes;//申请原因

    public ClaimCaseAccept getClaimCaseAccept() {
        return claimCaseAccept;
    }

    public void setClaimCaseAccept(ClaimCaseAccept claimCaseAccept) {
        this.claimCaseAccept = claimCaseAccept;
    }

    public List<String> getApplyTypes() {
        return applyTypes;
    }

    public void setApplyTypes(List<String> applyTypes) {
        this.applyTypes = applyTypes;
    }

    @Override
    public String toString() {
        return "CaseRegisterAndAppleTypeDTO{" +
                "claimCaseAccept=" + claimCaseAccept +
                ", applyTypes=" + applyTypes +
                '}';
    }
}
