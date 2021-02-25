package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimCaseStanding;
import com.paic.ehis.claimflow.domain.dto.ClaimStandingRptNoDTO;
import com.paic.ehis.claimflow.mapper.ClaimCaseStandingMapper;
import com.paic.ehis.claimflow.service.ClaimStandingRptNoService;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: lyc
 * @CreateTime: 2021/2/25
 * @Company:
 */
@Service
public class ClaimStandingRptNoImpl implements ClaimStandingRptNoService {
    @Autowired
    private ClaimCaseStandingMapper claimCaseStandingMapper;

    @Override
    public int selectClaimHistory(ClaimStandingRptNoDTO claimStandingRptNoDTO) {
        List<ClaimCaseStanding> claimCaseStandingList = new ArrayList<>();
        //根据批次号去查报案号
        if(StringUtils.isNotEmpty(claimStandingRptNoDTO.getBatchNo())) {
            //根据报案号去查台账信息
            claimCaseStandingList= claimCaseStandingMapper.selectClaimCaseStandingByIdOne(claimStandingRptNoDTO.getBatchNo());
        }
        int i=0;
        for (ClaimCaseStanding claimCaseStanding : claimCaseStandingList) {
            if (claimCaseStanding.getIdno().equals(claimStandingRptNoDTO.getIdNo()) && claimCaseStanding.getIdType().equals(claimStandingRptNoDTO.getIdType())
                    && claimCaseStanding.getName().equals(claimStandingRptNoDTO.getName())){
                claimCaseStanding.setRptno(claimStandingRptNoDTO.getRptNo());
                i=i+claimCaseStandingMapper.updateClaimCaseStandingRptNo(claimCaseStanding);
            }
        }

        return i;

    }
}
