package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.dto.ClaimHistoryDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimHistory;

public interface ClaimHistoryService {
    public ClaimHistory selectClaimHistory(ClaimHistoryDTO claimHistoryDTO);
}
