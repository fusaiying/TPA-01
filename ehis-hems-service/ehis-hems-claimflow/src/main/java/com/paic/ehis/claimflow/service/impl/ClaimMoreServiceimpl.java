package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimBill;
import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.dto.ClaimMoreDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimMore;
import com.paic.ehis.claimflow.mapper.ClaimCaseBillMapper;
import com.paic.ehis.claimflow.service.ClaimMoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimMoreServiceimpl implements ClaimMoreService {

    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;

    @Override
    public ClaimMore selectClaimMore(ClaimMoreDTO claimMoreDTO) {
        ClaimMore claimMore=new ClaimMore();
        String rptNo=claimMoreDTO.getPolCertMap().getDocuno();
        List<ClaimBill> claimBillList=claimCaseBillMapper.selectClaimBillList(rptNo);
        claimMore.setClaimBillList(claimBillList);
        return claimMore;
    }
}
