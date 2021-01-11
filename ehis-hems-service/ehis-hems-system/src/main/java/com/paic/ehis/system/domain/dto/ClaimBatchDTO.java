package com.paic.ehis.system.domain.dto;

import com.paic.ehis.system.domain.ClaimBatch;
import com.paic.ehis.system.domain.ClaimBatchRecord;
import lombok.Data;

import java.io.Serializable;
@Data
public class ClaimBatchDTO implements Serializable {
    private ClaimBatch claimBatch;
    private ClaimBatchRecord claimBatchRecord;
}
