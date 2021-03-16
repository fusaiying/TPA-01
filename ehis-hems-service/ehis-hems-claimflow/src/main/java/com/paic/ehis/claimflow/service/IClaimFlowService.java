package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.dto.ClaimFlowDTO;
import com.paic.ehis.claimflow.domain.vo.PolicyListVo;

import java.util.List;

public interface IClaimFlowService {
    public List<PolicyListVo> selectPolicyList(ClaimFlowDTO claimFlowDTO);

    public PolicyListVo getPolicyInfoBy(ClaimFlowDTO claimFlowDTO);
}
