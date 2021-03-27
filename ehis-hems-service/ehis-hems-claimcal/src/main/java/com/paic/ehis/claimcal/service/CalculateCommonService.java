package com.paic.ehis.claimcal.service;

import com.paic.ehis.claimcal.domain.CheckInfoDTO;
import com.paic.ehis.claimcal.domain.ClaimRuleInfo;
import com.paic.ehis.claimcal.domain.QueryUsedDTO;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalItemDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xicc on 2021/3/24
 */
public interface CalculateCommonService {


    boolean ruleCheck(ClaimRuleInfo ruleInfo, CheckInfoDTO checkInfoDTO);


    BigDecimal getUesdValue(List<ClaimCaseBillInfo> claimCaseBillInfoList, QueryUsedDTO queryUsedDTO);

}
