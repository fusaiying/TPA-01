package com.paic.ehis.system.api.domain;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalBillDTO;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalDTO;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class ClaimCaseCalInfo extends BaseEntity {

    private ClaimCaseCalDTO claimCaseCalInfo;

    private List<ClaimCaseCalBillDTO> claimCaseCalBillList;

    private List<ClaimCaseCalItemDTO> claimCaseCalItemList;
}
