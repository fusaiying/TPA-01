package com.paic.ehis.claimflow.domain.dto;

import lombok.Data;

@Data
public class ClaimHistoryDTO {
    private ClaimHistorySubDTO polCertMap;
    private String type;
}
