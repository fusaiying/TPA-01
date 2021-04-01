package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.dto.ClaimFlowDTO;
import com.paic.ehis.claimflow.domain.vo.*;
import com.paic.ehis.claimflow.mapper.ClaimCaseBillDetailMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseBillMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseMapper;
import com.paic.ehis.claimflow.mapper.PolicyInfoMapper;
import com.paic.ehis.claimflow.service.IClaimFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimFlowServiceimpl implements IClaimFlowService {

    @Autowired
    private PolicyInfoMapper policyInfoMapper;
    @Autowired
    private ClaimCaseMapper ClaimCaseMapper;
    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;
    @Autowired
    private ClaimCaseBillDetailMapper claimCaseBillDetailMapper;

    @Override
    public List<PolicyListVo> selectPolicyList(ClaimFlowDTO claimFlowDTO) {
        List<PolicyListVo> policyListVos = policyInfoMapper.selectPolicyList(claimFlowDTO);
        return policyListVos;
    }

    @Override
    public PolicyListVo getPolicyInfoBy(ClaimFlowDTO claimFlowDTO) {
        List<PolicyListVo> policyListVos = policyInfoMapper.selectPolicyList(claimFlowDTO);
        return policyListVos!=null && !policyListVos.isEmpty() ? policyListVos.get(0) : null;
    }

    @Override
    public List<GCCPolicyListVo> queryPolicyListToGCC(ClaimFlowDTO claimFlowDTO) {
//        List<GCCPolicyListVo> gccPolicyListVos ;//= policyInfoMapper.queryPolicyListToGCC(claimFlowDTO);
        return null;
    }

    @Override
    public GCCPolicyInfoVo queryPolicyInfoToGCC(ClaimFlowDTO claimFlowDTO) {
//        return policyInfoMapper.queryPolicyInfoToGCC(claimFlowDTO);
        return null;
    }

    @Override
    public GCCPolicyCertInfoVo queryDutyInfoToGCC(ClaimFlowDTO claimFlowDTO) {
//        return policyInfoMapper.queryDutyInfoToGCC(claimFlowDTO);
        return null;
    }

    @Override
    public List<GCCClaimListVo> queryClaimListToGCC(ClaimFlowDTO claimFlowDTO) {
        return ClaimCaseMapper.queryClaimListToGCC(claimFlowDTO);
    }
    @Override
    public GCCClaimAcceptVo queryClaimAcceptListToGCC(ClaimFlowDTO claimFlowDTO) {
        GCCClaimAcceptVo claimAcceptVo=new GCCClaimAcceptVo();
        //返回体对象中只有账单信息可以提供
        String rptNo=claimFlowDTO.getDocuno();
        List<GCCClaimBill> claimBillList=claimCaseBillMapper.queryClaimAcceptListToGCC(rptNo);
        claimAcceptVo.setClaimBillList(claimBillList);
        return claimAcceptVo;
    }

    @Override
    public List<GCCClaimBillDetail> queryClaimBillDetailToGCC(ClaimFlowDTO claimFlowDTO) {
        return claimCaseBillDetailMapper.selectGCCClaimBillDetailById(claimFlowDTO.getSeqno());
    }
}
