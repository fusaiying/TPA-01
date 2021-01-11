package com.paic.ehis.system.domain;

import com.paic.ehis.system.domain.vo.ClaimCaseStandingVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StandingAndBatck implements Serializable {

    private ClaimBatch claimBatch;

    private List<ClaimCaseStandingVo> standingData;
}
