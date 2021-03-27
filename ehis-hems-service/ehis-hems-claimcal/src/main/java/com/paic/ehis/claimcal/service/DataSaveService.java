package com.paic.ehis.claimcal.service;

import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;

/**
 * Created by xicc on 2021/3/27
 */
public interface DataSaveService {
    boolean calculateDataSave(ClaimCaseCalculateInfo claimCaseCalculateInfo);
}
