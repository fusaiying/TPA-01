package com.paic.ehis.claimflow.domain.interfaceclass;

import com.paic.ehis.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 主体类
 */
@Data
public class CaseInfo extends BaseEntity {
    /** 批次号 */
    private String batchNo;

    /** 报案号 */
    private String rptNo;

    /** 理赔申请原因      TODO:理赔申请原因实体类更换String*/
    private List<String> applicationReason;

    /** 案件来源 */
    private String caseSource;

    /** 案件信息 */
    private ClaimCase caseInfo;

    /** 申请人信息 */
    private ClaimCaseRegister registerInfo;

    /** 被保人信息 */
    private ClaimCaseInsured caseInsuredInfo;

    /** 受理信息 */
    private ClaimCaseAccept caseAcceptInfo;

    /** 领款人信息 */
    private List<ClaimCasePayee> payeeInfoList;

    /** 账单信息  List*/
    private List<ClaimCaseBill> caseBillList;

    /** 影像信息 */

}
