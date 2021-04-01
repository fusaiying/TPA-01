package com.paic.ehis.claimflow.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GCCClaimAcceptVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 通知书信息
     */
    private List<GCCClaimSend> claimSendList;
    /**
     * 账单信息
     */
    private List<GCCClaimBill> claimBillList;
    /**
     * 理赔材料信息
     */
    private List<GCCClaimDoc> claimDocList;
}
