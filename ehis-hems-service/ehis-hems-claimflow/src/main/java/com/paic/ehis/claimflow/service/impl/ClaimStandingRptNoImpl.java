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
    public int updateStandingRptNo(ClaimStandingRptNoDTO claimStandingRptNoDTO) {

        return claimCaseStandingMapper.updateClaimCaseStandingRptNo(claimStandingRptNoDTO);

    }
}
