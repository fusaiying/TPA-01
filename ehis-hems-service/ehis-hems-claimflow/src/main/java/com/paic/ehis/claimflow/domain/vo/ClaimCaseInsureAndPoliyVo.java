package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.claimflow.domain.ClaimCaseInsured;

import java.io.Serializable;
import java.util.List;

public class ClaimCaseInsureAndPoliyVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private ClaimCaseInsured claimCaseInsured;
    private List<PolicyVo> policyInfominData;

    public ClaimCaseInsured getClaimCaseInsured() {
        return claimCaseInsured;
    }

    public void setClaimCaseInsured(ClaimCaseInsured claimCaseInsured) {
        this.claimCaseInsured = claimCaseInsured;
    }

    public List<PolicyVo> getPolicyInfominData() {
        return policyInfominData;
    }

    public void setPolicyInfominData(List<PolicyVo> policyInfominData) {
        this.policyInfominData = policyInfominData;
    }

    @Override
    public String toString() {
        return "ClaimCaseInsureAndPoliyVo{" +
                "claimCaseInsured=" + claimCaseInsured +
                ", policyInfominData=" + policyInfominData +
                '}';
    }
}
