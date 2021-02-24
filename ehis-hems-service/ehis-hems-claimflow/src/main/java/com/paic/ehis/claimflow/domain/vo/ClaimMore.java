package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.claimflow.domain.ClaimBill;
import com.paic.ehis.claimflow.domain.ClaimDoc;
import com.paic.ehis.claimflow.domain.ClaimSend;
import lombok.Data;

import java.util.List;

@Data
public class ClaimMore {

    private List<ClaimSend> claimSendList;

    private List<ClaimBill> claimBillList;

    private List<ClaimDoc>  claimDocList;
}
