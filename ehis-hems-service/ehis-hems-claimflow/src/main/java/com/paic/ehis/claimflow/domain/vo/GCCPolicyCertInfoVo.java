package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GCCPolicyCertInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private GCCPolCert polCertMap;
    private List<GCCLiabItem> liabItemList;
    private GCCPropose proposeMap;
}
