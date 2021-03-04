package com.paic.ehis.claimapt.domain;

import com.paic.ehis.claimapt.domain.Vo.ClaimCaseStandingVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StandingAndBatck implements Serializable {

    private ClaimBatch claimBatch;

    private List<ClaimCaseStandingVo> standingData;
}
