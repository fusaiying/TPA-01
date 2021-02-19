package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.claimflow.domain.PolicyInsured;
import lombok.Data;

import java.util.List;

@Data
public class PolicyFlagVo {
    private  List<PolicyInsured> policyInsuredList;
    private String Flag;
}
