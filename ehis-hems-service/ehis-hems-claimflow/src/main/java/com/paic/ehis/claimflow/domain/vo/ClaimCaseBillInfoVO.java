package com.paic.ehis.claimflow.domain.vo;

import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCaseBillDetail;
import lombok.Data;

import java.util.List;

/**
 * 案件账单信息表
 */
@Data
public class ClaimCaseBillInfoVO {

    /** 案件账单明细表+诊断信息 */
    private ClaimCaseBill bill;

    /** 费用项明细 */
    private List<ClaimCaseBillDetail> billDetail;
}
