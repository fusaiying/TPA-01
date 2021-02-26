package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.claimflow.domain.Claim;
import com.paic.ehis.claimflow.domain.CustomerInfoMap;
import lombok.Data;


import java.util.List;

@Data
public class ClaimHistory {
    private String mainPlanName;
    private CustomerInfoMap customerInfoMap;
    private String mainPlanCode;
    private List<Claim> claimList;

}
