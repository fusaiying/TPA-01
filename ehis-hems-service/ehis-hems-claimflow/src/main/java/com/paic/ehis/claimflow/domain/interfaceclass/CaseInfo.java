package com.paic.ehis.claimflow.domain.interfaceclass;

import com.paic.ehis.common.core.annotation.Excel;
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

    /** 归档号 */
    private String filingNo;

    /** 申请人信息 */
    private ClaimCaseRegisterInfo registerInfo;

    /** 被保人信息 */
    private ClaimCaseInsuredInfo caseInsuredInfo;

    /** 受理信息 */
    private ClaimCaseAcceptInfo caseAcceptInfo;

    /** 领款人信息 */
    private List<ClaimCasePayeeInfo> payeeInfoList;

    /** 账单信息  List*/
    private List<ClaimCaseBillInfo> caseBillList;

    /** 案件备注信息 */
    private List<ClaimCaseRemarkInfo> remarkInfoList;

}
