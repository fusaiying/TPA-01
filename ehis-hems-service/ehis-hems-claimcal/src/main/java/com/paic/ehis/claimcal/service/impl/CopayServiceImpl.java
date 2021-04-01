package com.paic.ehis.claimcal.service.impl;

import com.paic.ehis.claimcal.domain.CheckInfoDTO;
import com.paic.ehis.claimcal.domain.ClaimRuleInfo;
import com.paic.ehis.claimcal.mapper.SelectRuleInfoMapper;
import com.paic.ehis.claimcal.service.CalculateCommonService;
import com.paic.ehis.claimcal.service.CopayService;
import com.paic.ehis.claimcal.utility.ElementUnit;
import com.paic.ehis.claimcal.utility.RuleElement;
import com.paic.ehis.system.api.domain.ClaimCaseBillDetailInfo;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by xicc on 2021/3/26
 */
@Service
@Slf4j
public class CopayServiceImpl implements CopayService {

    @Autowired
    private SelectRuleInfoMapper selectRuleInfoMapper;

    @Autowired
    private CalculateCommonService calculateCommonService;


    @Override
    public ClaimCaseCalculateInfo calculateCopay(ClaimCaseCalculateInfo claimCaseCalculateInfo) {

        Set<String> exceptSet = claimCaseCalculateInfo.getExceptSet();

        String sex = claimCaseCalculateInfo.getSex();
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

                if(detailInfo.getCalAmount().compareTo(BigDecimal.ZERO)<=0){ continue; }

                String copayDetailRuleNo = "";
                BigDecimal detailRatio  = BigDecimal.valueOf(100);
                List<ClaimRuleInfo> detailCodeCopayRuleList = selectRuleInfoMapper.selectDetailCodeRuleList(RuleElement.COPAY.getCode(), ElementUnit.PERCENT.getCode(), riskCode, planCode, dutyCode, dutyDetailCode);
                for (ClaimRuleInfo ruleInfo : detailCodeCopayRuleList) {
                    CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                    if(!exceptSet.contains(ruleInfo.getRuleNo()) && calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                        BigDecimal elementValue = ruleInfo.getElementValue();
                        if (detailRatio.compareTo(elementValue) > 0) {
                            copayDetailRuleNo = ruleInfo.getRuleNo();
                        }
                        detailRatio = detailRatio.compareTo(elementValue) > 0 ? elementValue : detailRatio;
                    }
                }
                if(!"".equals(copayDetailRuleNo)){detailInfo = calculateCommonService.addRuleUsed(detailInfo,copayDetailRuleNo,detailRatio,detailRatio); }

                String copayDutyRuleNo = "";
                BigDecimal dutyRatio  = BigDecimal.valueOf(100);
                List<ClaimRuleInfo> dutyCodeCopayRuleList = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.COPAY.getCode(), ElementUnit.PERCENT.getCode(), riskCode, planCode, dutyCode);
                for (ClaimRuleInfo ruleInfo : dutyCodeCopayRuleList) {
                    CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                    if(!exceptSet.contains(ruleInfo.getRuleNo()) && calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                        BigDecimal elementValue = ruleInfo.getElementValue();
                        if (dutyRatio.compareTo(elementValue) > 0) {
                            copayDutyRuleNo = ruleInfo.getRuleNo();
                        }
                        dutyRatio = dutyRatio.compareTo(elementValue) > 0 ? elementValue : dutyRatio;
                    }
                }
                if(!"".equals(copayDutyRuleNo)){detailInfo = calculateCommonService.addRuleUsed(detailInfo,copayDutyRuleNo,dutyRatio,dutyRatio); }

                BigDecimal ratio = detailRatio.multiply(dutyRatio).divide(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(100));
                calAmount = calAmount.multiply(ratio);


                detailInfo.setCalAmount(calAmount);
                detailInfo.setPayRate(ratio);
            }
        }

        log.info("案件{}赔付比例计算成功",claimCaseCalculateInfo.getRptNo());

        return claimCaseCalculateInfo;
    }
}
