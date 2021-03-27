package com.paic.ehis.claimcal.service.impl;

import com.paic.ehis.claimcal.domain.CheckInfoDTO;
import com.paic.ehis.claimcal.domain.ClaimRuleInfo;
import com.paic.ehis.claimcal.domain.QueryUsedDTO;
import com.paic.ehis.claimcal.mapper.SelectRuleInfoMapper;
import com.paic.ehis.claimcal.service.CalculateCommonService;
import com.paic.ehis.claimcal.service.UpLimitService;
import com.paic.ehis.claimcal.utility.ElementUnit;
import com.paic.ehis.claimcal.utility.RuleElement;
import com.paic.ehis.system.api.domain.ClaimCaseBillDetailInfo;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xicc on 2021/3/26
 */
@Service
@Slf4j
public class UpLimitServiceImpl implements UpLimitService {


    @Autowired
    private SelectRuleInfoMapper selectRuleInfoMapper;

    @Autowired
    private CalculateCommonService calculateCommonService;



    @Override
    public ClaimCaseCalculateInfo calculateUpLimit(ClaimCaseCalculateInfo claimCaseCalculateInfo) {

        String sex = claimCaseCalculateInfo.getSex();
        List<ClaimCaseBillInfo> billInfoList = claimCaseCalculateInfo.getClaimCaseBillInfoList();
        for (ClaimCaseBillInfo billInfo : billInfoList) {

            String hospitalCode = billInfo.getHospitalCode();
            String department = billInfo.getDepartment();
            Date treatmentStartDate = billInfo.getTreatmentStartDate();
            Long treatmentDays = billInfo.getTreatmentDays();

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




                if(detailInfo.getCalAmount().compareTo(BigDecimal.ZERO)<=0){ continue; }
                //判断剩余次数
                List<ClaimRuleInfo> yearLimitTimeRuleList = selectRuleInfoMapper.selectFeeitemCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.TIME.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode);
                if (yearLimitTimeRuleList.size()==0) {
                    yearLimitTimeRuleList = selectRuleInfoMapper.selectDetailCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.TIME.getCode(), riskCode, planCode, dutyCode, dutyDetailCode);
                    if (yearLimitTimeRuleList.size()==0) {
                        yearLimitTimeRuleList = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.TIME.getCode(), riskCode, planCode, dutyCode);
                    }
                }
                for (ClaimRuleInfo ruleInfo : yearLimitTimeRuleList) {
                    CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                    //规则适用判断
                    if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                        BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                        QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.YEARLIMIT.getCode(), ElementUnit.TIME.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode, validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                        BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                        BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                        restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                        if(restvalue.compareTo(BigDecimal.ZERO) == 0) {
                            detailInfo.setCalAmount(BigDecimal.ZERO);
                            break;
                        }
                    }
                }

                if(detailInfo.getCalAmount().compareTo(BigDecimal.ZERO)<=0){ continue; }
                //判断剩余天数
                BigDecimal payDays = new BigDecimal(treatmentDays);
                List<ClaimRuleInfo> yearLimitDayRuleList = selectRuleInfoMapper.selectFeeitemCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.DAY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode);
                if (yearLimitDayRuleList.size()==0) {
                    yearLimitDayRuleList = selectRuleInfoMapper.selectDetailCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.DAY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode);
                    if (yearLimitDayRuleList.size()==0) {
                        yearLimitDayRuleList = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.DAY.getCode(), riskCode, planCode, dutyCode);
                    }
                }
                for (ClaimRuleInfo ruleInfo : yearLimitDayRuleList) {

                    CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                    //规则适用判断
                    if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                        BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                        QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.YEARLIMIT.getCode(), ElementUnit.TIME.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode, validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                        BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                        BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                        restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                        if(restvalue.compareTo(BigDecimal.ZERO) == 0) {
                            detailInfo.setCalAmount(BigDecimal.ZERO);
                            break;
                        }else {
                            payDays = payDays.compareTo(restvalue) > 0 ? restvalue : payDays;
                        }
                    }
                }
                BigDecimal calAmount = detailInfo.getCalAmount();

                BigDecimal dayLimitMoney = calAmount.divide(new BigDecimal(treatmentDays));
                List<ClaimRuleInfo> dayLimitMoneyRuleList = new ArrayList<>();
                List<ClaimRuleInfo> dayLimitMoneyFeeitemRuleList = selectRuleInfoMapper.selectFeeitemCodeRuleList(RuleElement.DAYLIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode);
                List<ClaimRuleInfo> dayLimitMoneyDetailRuleList = selectRuleInfoMapper.selectDetailCodeRuleList(RuleElement.DAYLIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode);
                List<ClaimRuleInfo> dayLimitMoneyDutyRuleList = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.DAYLIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode);
                dayLimitMoneyRuleList.addAll(dayLimitMoneyFeeitemRuleList);
                dayLimitMoneyRuleList.addAll(dayLimitMoneyDetailRuleList);
                dayLimitMoneyRuleList.addAll(dayLimitMoneyDutyRuleList);
                for (ClaimRuleInfo ruleInfo : dayLimitMoneyRuleList) {
                    CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                    //规则适用判断
                    if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                        BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
//                        QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.TIMELIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode, validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
//                        BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
//                        BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                        BigDecimal restvalue = elementValue;
                        restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                        if(restvalue.compareTo(BigDecimal.ZERO) == 0) {
                            detailInfo.setCalAmount(BigDecimal.ZERO);
                            break;
                        }else {
                            dayLimitMoney = dayLimitMoney.compareTo(restvalue) > 0 ? restvalue : dayLimitMoney;
                        }
                    }
                }
                BigDecimal allDayLimitMoney = dayLimitMoney.multiply(new BigDecimal(treatmentDays));
                calAmount = calAmount.compareTo(allDayLimitMoney) > 0 ? allDayLimitMoney : calAmount;


                //判断剩余金额
                if(calAmount.compareTo(BigDecimal.ZERO)<=0){ continue; }
                List<ClaimRuleInfo> timeLimitMoneyRuleList = new ArrayList<>();
                List<ClaimRuleInfo> timeLimitMoneyFeeitemRuleList = selectRuleInfoMapper.selectFeeitemCodeRuleList(RuleElement.TIMELIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode);
                List<ClaimRuleInfo> timeLimitMoneyDetailRuleList = selectRuleInfoMapper.selectDetailCodeRuleList(RuleElement.TIMELIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode);
                List<ClaimRuleInfo> timeLimitMoneyDutyRuleList = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.TIMELIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode);
                timeLimitMoneyRuleList.addAll(timeLimitMoneyFeeitemRuleList);
                timeLimitMoneyRuleList.addAll(timeLimitMoneyDetailRuleList);
                timeLimitMoneyRuleList.addAll(timeLimitMoneyDutyRuleList);
                for (ClaimRuleInfo ruleInfo : timeLimitMoneyRuleList) {
                    CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                    //规则适用判断
                    if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                        BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                        QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.TIMELIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode, validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                        BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                        BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                        restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                        if(restvalue.compareTo(BigDecimal.ZERO) == 0) {
                            detailInfo.setCalAmount(BigDecimal.ZERO);
                            break;
                        }else {
                            calAmount = calAmount.compareTo(restvalue) > 0 ? restvalue : calAmount;
                        }
                    }
                }

                if(calAmount.compareTo(BigDecimal.ZERO)<=0){ continue; }
                List<ClaimRuleInfo> yearLimitMoneyRuleList = new ArrayList<>();
                List<ClaimRuleInfo> yearLimitMoneyFeeitemRuleList = selectRuleInfoMapper.selectFeeitemCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode);
                List<ClaimRuleInfo> yearLimitMoneyDetailRuleList = selectRuleInfoMapper.selectDetailCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode);
                List<ClaimRuleInfo> yearLimitMoneyDutyRuleList = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.YEARLIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode);
                yearLimitMoneyRuleList.addAll(yearLimitMoneyFeeitemRuleList);
                yearLimitMoneyRuleList.addAll(yearLimitMoneyDetailRuleList);
                yearLimitMoneyRuleList.addAll(yearLimitMoneyDutyRuleList);
                for (ClaimRuleInfo ruleInfo : yearLimitMoneyRuleList) {
                    CheckInfoDTO checkInfoDTO = new CheckInfoDTO(treatmentStartDate,validStartDate,sex,hospitalCode,riskCode,planCode,dutyCode);
                    //规则适用判断
                    if(calculateCommonService.ruleCheck(ruleInfo,checkInfoDTO)){
                        BigDecimal elementValue = ruleInfo.getElementValue(); //规则值
                        QueryUsedDTO queryUsedDTO = new QueryUsedDTO(policyNo, policyItemNo, RuleElement.YEARLIMIT.getCode(), ElementUnit.MONEY.getCode(), riskCode, planCode, dutyCode, dutyDetailCode, feeItemCode, validStartDate, validEndDate, hospitalCode, treatmentStartDate, department);
                        BigDecimal historyValue = calculateCommonService.getUesdValue(billInfoList,queryUsedDTO); //使用值
                        BigDecimal restvalue = elementValue.subtract(historyValue); //剩余值
                        restvalue = restvalue.compareTo(BigDecimal.ZERO)>0?restvalue:BigDecimal.ZERO;

                        if(restvalue.compareTo(BigDecimal.ZERO) == 0) {
                            detailInfo.setCalAmount(BigDecimal.ZERO);
                            break;
                        }else {
                            calAmount = calAmount.compareTo(restvalue) > 0 ? restvalue : calAmount;
                        }
                    }
                }

                detailInfo.setCalAmount(calAmount);

            }
        }

        log.info("案件{}限额计算成功",claimCaseCalculateInfo.getRptNo());
        return claimCaseCalculateInfo;
    }
}
