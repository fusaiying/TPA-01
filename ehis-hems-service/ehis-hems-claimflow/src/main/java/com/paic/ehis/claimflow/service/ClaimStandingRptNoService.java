package com.paic.ehis.claimflow.service;


import com.paic.ehis.claimflow.domain.ClaimCaseStanding;
import com.paic.ehis.claimflow.domain.dto.ClaimStandingRptNoDTO;

import java.util.List;

/**
 * @Description:
 * @Author: lyc
 * @CreateTime: 2021/2/25
 * @Company:
 */
public interface ClaimStandingRptNoService {
    public int selectClaimHistory(ClaimStandingRptNoDTO claimStandingRptNoDTO);
}
