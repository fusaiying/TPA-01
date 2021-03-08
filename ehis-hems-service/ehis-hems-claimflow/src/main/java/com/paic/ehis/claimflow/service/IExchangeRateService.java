package com.paic.ehis.claimflow.service;


import com.paic.ehis.claimflow.domain.SyncExchangeRate;

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
