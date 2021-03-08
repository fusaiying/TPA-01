package com.paic.ehis.claimflow.mapper;


import com.paic.ehis.claimflow.domain.SyncExchangeRate;

/**
 * 角色表 数据层
 * 
 * @author admin
 */
public interface SyncExchangeRateMapper
{
    /**
     * 获取汇率
     * 
     * @param syncExchangeRate
     * @return 角色列表
     */
    public SyncExchangeRate getSyncExchangeRate(SyncExchangeRate syncExchangeRate);

}
