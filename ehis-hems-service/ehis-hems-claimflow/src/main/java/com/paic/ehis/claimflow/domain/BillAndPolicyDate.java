package com.paic.ehis.claimflow.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: lyc
 * @CreateTime: 2021/3/3
 * @Company:
 */
@Data
public class BillAndPolicyDate {
    /**账单治疗起期*/
    private Date billStartDate;
    /**账单治疗止期*/
    private Date billEndDate;
    /**保单治疗起期*/
    private Date policyStartDate;
    /**保单治疗止期*/
    private Date policyEndDate;
}
