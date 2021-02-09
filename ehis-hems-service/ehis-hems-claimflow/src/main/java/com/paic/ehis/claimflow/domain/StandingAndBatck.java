package com.paic.ehis.claimflow.domain;


import com.paic.ehis.claimflow.domain.vo.ClaimCaseStandingVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StandingAndBatck implements Serializable {

    private ClaimBatch claimBatch;

    private List<ClaimCaseStandingVo> standingData;
}
