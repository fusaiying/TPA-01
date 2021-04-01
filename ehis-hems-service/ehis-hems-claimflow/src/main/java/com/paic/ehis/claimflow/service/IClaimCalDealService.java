package com.paic.ehis.claimflow.service;

import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalItemDTO;
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
     * 根据案件号查询案件信息
     * @param rptNo
     * @return
     */
    ClaimCaseCalculateInfo selectClaimCaseInfo(String rptNo);

    /**
     * 根据报案号查询案件保单信息
     * @param rptNo
     * @return
     */
    List<ClaimCasePolicyDTO> selectClaimCasePolicy(String rptNo);

    /**
     * 保存理算信息
     * @param claimCaseCalculateInfo
     * @return
     */
    int save(ClaimCaseCalculateInfo claimCaseCalculateInfo);

    /**
     * 查询使用值信息
     * @param claimCaseCalItemDTO
     * @return
     */
    List<ClaimCaseCalItemDTO> getCaInfo(ClaimCaseCalItemDTO claimCaseCalItemDTO);
}
