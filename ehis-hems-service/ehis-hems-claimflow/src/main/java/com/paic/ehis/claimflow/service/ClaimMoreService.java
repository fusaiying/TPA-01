package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.dto.ClaimMoreDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimMore;

public interface ClaimMoreService {
    public ClaimMore selectClaimMore(ClaimMoreDTO claimMoreDTO);
}
