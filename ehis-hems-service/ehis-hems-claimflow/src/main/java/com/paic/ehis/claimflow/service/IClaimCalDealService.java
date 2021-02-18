package com.paic.ehis.claimflow.service;

import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalInfo;
import com.paic.ehis.system.api.domain.dto.ClaimCasePolicyDTO;

import java.util.List;

public interface IClaimCalDealService {
    /**
     * 根据报案号清理理算计算数据
     * @param rptNo 报案号
     * @return
     */
    int clear(String rptNo);

    /**
     * 根据案件号查询账单信息
     * @param rptNo
     * @return
     */
    List<ClaimCaseBillInfo> selectClaimCaseBillInfo(String rptNo);

    /**
     * 根据报案号查询案件保单信息
     * @param rptNo
     * @return
     */
    List<ClaimCasePolicyDTO> selectClaimCasePolicy(String rptNo);

    /**
     * 保存理算信息
     * @param claimCaseCalInfo
     * @return
     */
    int save(ClaimCaseCalInfo claimCaseCalInfo);
}
