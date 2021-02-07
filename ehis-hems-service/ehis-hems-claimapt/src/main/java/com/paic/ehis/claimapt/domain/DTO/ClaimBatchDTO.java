package com.paic.ehis.claimapt.domain.DTO;

import com.paic.ehis.claimapt.domain.ClaimBatch;
import com.paic.ehis.claimapt.domain.ClaimBatchRecord;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClaimBatchDTO implements Serializable {
    private ClaimBatch claimBatch;
    private ClaimBatchRecord claimBatchRecord;

    @Override
    public String toString() {
        return "ClaimBatchDTO{" +
                "claimBatch=" + claimBatch +
                ", claimBatchRecord=" + claimBatchRecord +
                '}';
    }
}
