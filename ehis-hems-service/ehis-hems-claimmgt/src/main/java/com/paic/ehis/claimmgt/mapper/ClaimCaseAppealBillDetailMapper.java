package com.paic.ehis.claimmgt.mapper;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealBillDetail;
import com.paic.ehis.claimmgt.domain.ClaimCaseAppealTask;

import java.util.List;

/**
 * 案件账单费用项明细Mapper接口
 *
 * @author sino
 * @date 2021-03-16
 */
public interface ClaimCaseAppealBillDetailMapper
{

    /**
     * 查询案件账单费用项明细列表
     * @ahthor:hjw
     * @time:2021-03-16
     */
    public List<ClaimCaseAppealBillDetail> claimCaseAppealBillDetailList(ClaimCaseAppealBillDetail bean);


    /**
     * @ahthor:hjw
     * @time:2021-03-16
     * 案件修正同步原账单后，同步 claim_case_bill_detail 的billId
     */
    public int updateClaimCaseAppealBillDetail(ClaimCaseAppealBillDetail detail);


    /**
     * @ahthor:hjw
     * @time:2021-03-16
     * 案件修正同步原账单后，同步 claim_case_bill_diagnosis 的billId
     */
    public int updateClaimCaseAppealBillDiagnosis(ClaimCaseAppealBillDetail detail);

    /**
     * @ahthor:hjw
     * @time:2021-03-16
     * 案件修正同步原账单后，同步 claim_case_cal_bill 的billId
     */
    public int updateClaimCaseAppealCalBill(ClaimCaseAppealBillDetail detail);

    /**
     * @ahthor:hjw
     * @time:2021-03-16
     * 案件修正同步原账单后，同步 claim_case_bill update_by 清空
     */
    public int updateClaimCaseAppealBill(ClaimCaseAppealBillDetail detail);


}
