package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.dto.ClaimFlowDTO;
import com.paic.ehis.claimflow.domain.vo.PolicyListVo;
import com.paic.ehis.claimflow.mapper.ClaimCaseBillMapper;
import com.paic.ehis.claimflow.mapper.PolicyInfoMapper;
import com.paic.ehis.claimflow.service.IClaimFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimFlowServiceimpl implements IClaimFlowService {

    @Autowired
    private PolicyInfoMapper policyInfoMapper;

    @Override
    public List<PolicyListVo> selectPolicyList(ClaimFlowDTO claimFlowDTO) {
        List<PolicyListVo> policyListVos = policyInfoMapper.selectPolicyList(claimFlowDTO);
        return policyListVos;
    }

    @Override
    public PolicyListVo getPolicyInfoBy(ClaimFlowDTO claimFlowDTO) {
        List<PolicyListVo> policyListVos = policyInfoMapper.selectPolicyList(claimFlowDTO);
        return policyListVos!=null?policyListVos.get(0):null;
    }
}
