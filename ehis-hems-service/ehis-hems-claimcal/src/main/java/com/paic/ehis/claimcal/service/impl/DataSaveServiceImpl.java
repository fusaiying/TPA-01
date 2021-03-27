package com.paic.ehis.claimcal.service.impl;

import com.paic.ehis.claimcal.service.DataSaveService;
import com.paic.ehis.system.api.domain.ClaimCaseBillDetailInfo;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xicc on 2021/3/27
 */
@Service
@Slf4j
public class DataSaveServiceImpl implements DataSaveService {



    @Override
    public boolean calculateDataSave(ClaimCaseCalculateInfo claimCaseCalculateInfo) {

        List<ClaimCaseBillInfo> billInfoList = claimCaseCalculateInfo.getClaimCaseBillInfoList();
        for (ClaimCaseBillInfo billInfo : billInfoList) {

            String hospitalCode = billInfo.getHospitalCode();
            String department = billInfo.getDepartment();
            Date treatmentStartDate = billInfo.getTreatmentStartDate();

            List<ClaimCaseBillDetailInfo> detailInfoList = billInfo.getClaimCaseBillDetailInfoList();
            for (ClaimCaseBillDetailInfo detailInfo : detailInfoList) {
                String policyNo = detailInfo.getPolicyNo();
                String policyItemNo = detailInfo.getPolicyItemNo();
                String planCode = detailInfo.getPlanCode();
                String riskCode = detailInfo.getRiskCode();
                String dutyCode = detailInfo.getDutyCode();
                String dutyDetailCode = detailInfo.getDutyDetailCode();
                String feeItemCode = detailInfo.getFeeItemCode();
                Date validStartDate = detailInfo.getValidStartDate(); //保单有效起期

                BigDecimal calAmount = detailInfo.getCalAmount();


                //准备claim_case_cal_item
            }

            //准备claim_case_cal_bill

        }

        //准备claim_case_cal
        //准备claim_case_cal_used
        //准备claim_case_cal_rule

        //调用远程接口，insert数据
        return false;
    }
}
