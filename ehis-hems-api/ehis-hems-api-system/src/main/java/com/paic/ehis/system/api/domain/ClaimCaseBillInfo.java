package com.paic.ehis.system.api.domain;

import com.paic.ehis.system.api.domain.dto.ClaimCaseBillDTO;
import com.paic.ehis.system.api.domain.dto.ClaimCaseBillDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class ClaimCaseBillInfo {

    private ClaimCaseBillDTO claimCaseBill;

    private List<ClaimCaseBillDetailDTO> claimCaseBillDetailList;
}
