package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.SyncExchangeRate;
import com.paic.ehis.claimflow.mapper.SyncExchangeRateMapper;
import com.paic.ehis.claimflow.service.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceImpl implements IExchangeRateService {

    @Autowired
    private SyncExchangeRateMapper syncExchangeRateMapper;

    @Override
    public SyncExchangeRate getExchangeRate(SyncExchangeRate syncExchangeRate) {
        return syncExchangeRateMapper.getSyncExchangeRate(syncExchangeRate);
    }
}
