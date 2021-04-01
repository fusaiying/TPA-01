package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.dto.ClaimFlowDTO;
import com.paic.ehis.claimflow.domain.vo.*;

import java.util.List;

public interface IClaimFlowService {
    public List<PolicyListVo> selectPolicyList(ClaimFlowDTO claimFlowDTO);

    public PolicyListVo getPolicyInfoBy(ClaimFlowDTO claimFlowDTO);

    public List<GCCPolicyListVo> queryPolicyListToGCC(ClaimFlowDTO claimFlowDTO);

    public GCCPolicyInfoVo queryPolicyInfoToGCC(ClaimFlowDTO claimFlowDTO);

    public GCCPolicyCertInfoVo queryDutyInfoToGCC(ClaimFlowDTO claimFlowDTO);

    public List<GCCClaimListVo> queryClaimListToGCC(ClaimFlowDTO claimFlowDTO);

    public GCCClaimAcceptVo queryClaimAcceptListToGCC(ClaimFlowDTO claimFlowDTO);

    public List<GCCClaimBillDetail> queryClaimBillDetailToGCC(ClaimFlowDTO claimFlowDTO);
}
