package com.paic.ehis.claimcal.service.impl;

import com.paic.ehis.claimcal.domain.CheckInfoDTO;
import com.paic.ehis.claimcal.domain.ClaimRuleInfo;
import com.paic.ehis.claimcal.domain.QueryUsedDTO;
import com.paic.ehis.claimcal.mapper.SelectRuleInfoMapper;
import com.paic.ehis.claimcal.service.CalculateCommonService;
import com.paic.ehis.claimcal.service.DeductibleService;
import com.paic.ehis.claimcal.utility.ElementUnit;
import com.paic.ehis.claimcal.utility.RuleElement;
import com.paic.ehis.system.api.domain.ClaimCaseBillDetailInfo;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import com.paic.ehis.system.api.domain.dto.ClaimCaseBillDTO;
import com.paic.ehis.system.api.domain.dto.ClaimCaseBillDetailDTO;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by xicc on 2021/3/24
 */
@Service
@Slf4j
public class DeductibleServiceImpl implements DeductibleService {

    @Autowired
    private SelectRuleInfoMapper selectRuleInfoMapper;

    @Autowired
    private CalculateCommonService calculateCommonService;


    @Override
    public ClaimCaseCalculateInfo calculateDeductible(ClaimCaseCalculateInfo claimCaseCalculateInfo) {

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
                Date validEndDate = detailInfo.getValidEndDate();

                BigDecimal calAmount = detailInfo.getCalAmount();

                if(detailInfo.getCalAmount().compareTo(BigDecimal.ZERO)<=0){ continue; }


                boolean deductCheck = false; //免赔额一个费用项只使用一次
                if(!deductCheck){
                    List<ClaimRuleInfo> feeitemYearDeductibleRuleList = selectRuleInfoMapper.selectFeeitemCodeRuleList(RuleElement.YEARDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode);
                    for (ClaimRuleInfo ruleInfo : feeitemYearDeductibleRuleList) {

                        CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                        //规则适用判断
                        if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                            BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                            QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.YEARDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode, validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                            BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                            BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                            restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;


                            if(restvalue.compareTo(BigDecimal.ZERO) > 0) {
                                BigDecimal deduct = restvalue.compareTo(calAmount) > 0 ? calAmount : restvalue;
                                calAmount = calAmount.subtract(deduct); //修改理算金额


                                detailInfo.setCalAmount(calAmount);
                                detailInfo.setDeduUsed(deduct); //免赔值记录

                            }
                        }
                    }
                }

                if(!deductCheck){
                    List<ClaimRuleInfo> feeitemTimeDeductibleRuleList = selectRuleInfoMapper.selectFeeitemCodeRuleList(RuleElement.TIMEDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode);
                    for (ClaimRuleInfo ruleInfo : feeitemTimeDeductibleRuleList) {

                        CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                        //规则适用判断
                        if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                            BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                            QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.TIMEDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode, validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                            BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                            BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                            restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                            if(restvalue.compareTo(BigDecimal.ZERO) > 0) {
                                BigDecimal deduct = restvalue.compareTo(calAmount) > 0 ? calAmount : restvalue;
                                calAmount = calAmount.subtract(deduct); //修改理算金额


                                detailInfo.setCalAmount(calAmount);
                                detailInfo.setDeduUsed(deduct); //免赔值记录

                            }
                        }
                    }
                }

                if(!deductCheck){
                    List<ClaimRuleInfo> detailYearDeductibleRuleList = selectRuleInfoMapper.selectDetailCodeRuleList(RuleElement.YEARDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode);
                    for (ClaimRuleInfo ruleInfo : detailYearDeductibleRuleList) {

                        CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                        //规则适用判断
                        if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                            BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                            QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.YEARDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, "", validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                            BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                            BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                            restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                            if(restvalue.compareTo(BigDecimal.ZERO) > 0) {
                                BigDecimal deduct = restvalue.compareTo(calAmount) > 0 ? calAmount : restvalue;
                                calAmount = calAmount.subtract(deduct); //修改理算金额


                                detailInfo.setCalAmount(calAmount);
                                detailInfo.setDeduUsed(deduct); //免赔值记录

                            }
                        }
                    }
                }

                if(!deductCheck){
                    List<ClaimRuleInfo> detailTimeDeductibleRuleList = selectRuleInfoMapper.selectDetailCodeRuleList(RuleElement.TIMEDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode);
                    for (ClaimRuleInfo ruleInfo : detailTimeDeductibleRuleList) {

                        CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                        //规则适用判断
                        if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                            BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                            QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.TIMEDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, "", validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                            BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                            BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                            restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                            if(restvalue.compareTo(BigDecimal.ZERO) > 0) {
                                BigDecimal deduct = restvalue.compareTo(calAmount) > 0 ? calAmount : restvalue;
                                calAmount = calAmount.subtract(deduct); //修改理算金额


                                detailInfo.setCalAmount(calAmount);
                                detailInfo.setDeduUsed(deduct); //免赔值记录

                            }
                        }
                    }
                }

                if(!deductCheck){
                    List<ClaimRuleInfo> dutyYearDeductibleRuleList = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.YEARDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode);
                    for (ClaimRuleInfo ruleInfo : dutyYearDeductibleRuleList) {

                        CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                        //规则适用判断
                        if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                            BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                            QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.YEARDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, "", "", validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                            BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                            BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                            restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                            if(restvalue.compareTo(BigDecimal.ZERO) > 0) {
                                BigDecimal deduct = restvalue.compareTo(calAmount) > 0 ? calAmount : restvalue;
                                calAmount = calAmount.subtract(deduct); //修改理算金额


                                detailInfo.setCalAmount(calAmount);
                                detailInfo.setDeduUsed(deduct); //免赔值记录

                            }
                        }
                    }
                }

                if(!deductCheck){
                    List<ClaimRuleInfo> dutyTimeDeductibleRuleList = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.TIMEDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode);
                    for (ClaimRuleInfo ruleInfo : dutyTimeDeductibleRuleList) {

                        CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                        //规则适用判断
                        if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                            BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                            QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.TIMEDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, "", "", validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                            BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                            BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                            restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;


                            if(restvalue.compareTo(BigDecimal.ZERO) > 0) {
                                BigDecimal deduct = restvalue.compareTo(calAmount) > 0 ? calAmount : restvalue;
                                calAmount = calAmount.subtract(deduct); //修改理算金额


                                detailInfo.setCalAmount(calAmount);
                                detailInfo.setDeduUsed(deduct); //免赔值记录

                            }
                        }
                    }
                }
            }
        }


        log.info("案件{}免赔额计算成功",claimCaseCalculateInfo.getRptNo());



        return claimCaseCalculateInfo;
    }



}
