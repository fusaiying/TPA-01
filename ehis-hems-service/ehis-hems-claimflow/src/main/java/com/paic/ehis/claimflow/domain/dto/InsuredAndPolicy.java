package com.paic.ehis.claimflow.domain.dto;


import com.paic.ehis.claimflow.domain.ClaimCaseInsured;
import com.paic.ehis.claimflow.domain.vo.PolicyVo;

import java.io.Serializable;
import java.util.List;

public class InsuredAndPolicy implements Serializable {

    private List<PolicyVo> policyNos;//保单号集合

    private ClaimCaseInsured claimCaseInsured;//案件被保人信息表

    public List<PolicyVo> getPolicyNos() {
        return policyNos;
    }

    public void setPolicyNos(List<PolicyVo> policyNos) {
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
