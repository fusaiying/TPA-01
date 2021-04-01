package com.paic.ehis.claimcal.service.impl;

import com.paic.ehis.claimcal.service.PayCheckService;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by xicc on 2021/4/1
 */
@Service
@Slf4j
public class PayCheckServiceImpl implements PayCheckService {


    @Override
    public ClaimCaseCalculateInfo calculateCheck(ClaimCaseCalculateInfo claimCaseCalculateInfo) {
        return claimCaseCalculateInfo;
    }
}
