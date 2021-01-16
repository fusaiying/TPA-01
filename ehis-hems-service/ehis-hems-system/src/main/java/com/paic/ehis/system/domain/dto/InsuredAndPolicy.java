package com.paic.ehis.system.domain.dto;

import com.paic.ehis.system.domain.ClaimCaseInsured;

import java.io.Serializable;
import java.util.List;

public class InsuredAndPolicy implements Serializable {

    private List<String> policyNos;//保单号集合

    private ClaimCaseInsured claimCaseInsured;//案件被保人信息表

    public List<String> getPolicyNos() {
        return policyNos;
    }

    public void setPolicyNos(List<String> policyNos) {
        this.policyNos = policyNos;
    }

    public ClaimCaseInsured getClaimCaseInsured() {
        return claimCaseInsured;
    }

    public void setClaimCaseInsured(ClaimCaseInsured claimCaseInsured) {
        this.claimCaseInsured = claimCaseInsured;
    }

    @Override
    public String toString() {
        return "InsuredAndPolicy{" +
                "policyNos=" + policyNos +
                ", claimCaseInsured=" + claimCaseInsured +
                '}';
    }
}
