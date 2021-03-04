package com.paic.ehis.claimflow.domain.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ClaimMoreDTO {
    private ClaimMoreSubDTO polCertMap;
    private String tpye;
}
