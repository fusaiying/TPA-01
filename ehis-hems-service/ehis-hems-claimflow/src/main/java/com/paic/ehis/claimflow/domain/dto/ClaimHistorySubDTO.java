package com.paic.ehis.claimflow.domain.dto;


import lombok.Data;

@Data
public class ClaimHistorySubDTO {
    private String regionCodeName;
    private String uid;
    private String callerNo;
    private String customerNo;
    private String callId;
    private String certno;
    private String appType;
    private String applicantName;
    private String queryKey;
}
