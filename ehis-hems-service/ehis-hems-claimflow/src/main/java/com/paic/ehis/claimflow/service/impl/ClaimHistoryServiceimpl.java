package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.domain.dto.ClaimHistoryDTO;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillItemVo;
import com.paic.ehis.claimflow.domain.vo.ClaimHistory;
import com.paic.ehis.claimflow.mapper.*;
import com.paic.ehis.claimflow.service.ClaimHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClaimHistoryServiceimpl implements ClaimHistoryService {

    @Autowired
    private PolicyInfoMapper policyInfoMapper;
    @Autowired
    private PolicyRiskRelationMapper policyRiskRelationMapper;
    @Autowired
    private ClaimCaseCalItemMapper claimCaseCalItemMapper;
    @Autowired
    private ClaimCaseAcceptMapper claimCaseAcceptMapper;
    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;

    @Override
    public ClaimHistory selectClaimHistory(ClaimHistoryDTO claimHistoryDTO) {
        ClaimHistory claimHistory=new ClaimHistory();
        String customerNo=claimHistoryDTO.getPolCertMap().getCustomerNo();
//        String certno=claimHistoryDTO.getPolCertMap().getCertno();
        CustomerInfoMap customerInfoMap=policyInfoMapper.selectCustomerInfoMap(customerNo);
        claimHistory.setCustomerInfoMap(customerInfoMap);
        ClaimHistory claimHistory1=policyRiskRelationMapper.selectRisk(customerInfoMap.getPolno());
        claimHistory.setMainPlanCode(claimHistory1.getMainPlanCode());
        claimHistory.setMainPlanName(claimHistory1.getMainPlanName());
//        ClaimCaseCalItem claimCaseCaltItem=new ClaimCaseCalItem();
//        claimCaseCaltItem.setPolicyNo(customerInfoMap.getPolno());
//        claimCaseCaltItem.setPolicyItemNo(certno);
//        claimCaseCaltItem.setStatus("Y");
//        List<CaseCalBillItemVo> claimCaseCaltItemList=claimCaseCalItemMapper.selectClaimCaseCalFeeItemList(claimCaseCaltItem);
//        List<Claim> claimList=new ArrayList<>();
//        Claim claim=new Claim();
//        for (CaseCalBillItemVo caseCalBillItemVo:claimCaseCaltItemList){
//            String rptNo=caseCalBillItemVo.getRptNo();
//            AccidentInfoMap accidentInfoMap=claimCaseAcceptMapper.selectacciPlace(rptNo);
//         //   String acciPlace=accidentInfoMap.getAcciPlace();
//            claim.setAccidentInfoMap(accidentInfoMap);
//            claim.setPaidAmt(claimCaseBillMapper.selectPaidAmt(rptNo).getPaidAmt());
//            claimList.add(claim);
//        }
//        claimHistory.setClaimList(claimList);
        return claimHistory;
    }
}
