package com.paic.ehis.claimcal.service;

/**
 * 理算计算处理
 * @author duwei
 */
public interface IClaimCalService {

    /**
     * 本案件理算历史数据清理
     * @param rgtNo 报案号
     * @return
     */
    boolean Clear(String rgtNo);

    /**
     * 案件理算计算处理
     * @param rgtNo 报案号
     * @return
     */
    boolean Calculate(String rgtNo);
}
