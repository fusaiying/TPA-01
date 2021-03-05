package com.paic.ehis.claimcal.service;

import com.paic.ehis.claimcal.domain.SyncExchangeRate;

/**
 * 理算计算处理
 * @author duwei
 */
public interface IExchangeRateService {

    /**
     * 获取汇率
     * @param syncExchangeRate
     * @return
     */
    SyncExchangeRate getExchangeRate(SyncExchangeRate syncExchangeRate);
}
