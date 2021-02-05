package com.paic.ehis.claimflow.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DebtInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 报案号 */
    private String rptNo;

    /** 保单号 */
    private String policyNo;

    /** 就诊医院 */
    private String hospitalCode;

    /** 被保人姓名 */
    private String insuredName;

    /** 证件号码 */
    private String idNo;

    /** 开始时间 **/
    private Date startDate;

    /** 结束时间 */
    private Date endDate;

    /** 分单号 */
    private String policyItemNo;

    /** 白名单标记 */
    private String whiteStatus;

}
