package com.paic.ehis.claimcal.service.impl;

import com.alibaba.fastjson.JSON;
import com.paic.ehis.claimcal.domain.CheckInfoDTO;
import com.paic.ehis.claimcal.domain.ClaimRuleConstraint;
import com.paic.ehis.claimcal.domain.ClaimRuleInfo;
import com.paic.ehis.claimcal.domain.QueryUsedDTO;
import com.paic.ehis.claimcal.mapper.SelectRuleInfoMapper;
import com.paic.ehis.claimcal.service.CalculateCommonService;
import com.paic.ehis.claimcal.utility.ElementUnit;
import com.paic.ehis.claimcal.utility.RuleElement;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.RemoteClaimCalService;
import com.paic.ehis.system.api.domain.BaseProviderInfo;
import com.paic.ehis.system.api.domain.ClaimCaseBillDetailInfo;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import com.paic.ehis.system.api.domain.dto.ClaimCaseCalItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by xicc on 2021/3/24
 */
@Service
@Slf4j
public class CalculateCommonServiceImpl implements CalculateCommonService {

    @Autowired
    private SelectRuleInfoMapper selectRuleInfoMapper;

    @Autowired
    private RemoteClaimCalService remoteClaimCalService;


    @Override
    public boolean ruleCheck(ClaimRuleInfo ruleInfo, CheckInfoDTO checkInfoDTO) {
        boolean check = true;

        List<ClaimRuleConstraint> constraintList = ruleInfo.getConstraintList();

        List<ClaimRuleConstraint> constraintList00 = new ArrayList<>(); //地点

        List<ClaimRuleConstraint> constraintList31 = new ArrayList<>(); //医院属性
        List<ClaimRuleConstraint> constraintList32 = new ArrayList<>(); //医院级别
        List<ClaimRuleConstraint> constraintList33 = new ArrayList<>(); //部门类别
        List<ClaimRuleConstraint> constraintList34 = new ArrayList<>(); //医院类别
        List<ClaimRuleConstraint> constraintList35 = new ArrayList<>(); //网络内
        List<ClaimRuleConstraint> constraintList36 = new ArrayList<>(); //是否直结
        for (ClaimRuleConstraint constraint : constraintList) {
            if(!check){
                break;
            }
            switch (constraint.getConstraintType()){
                case "00":
                    constraintList00.add(constraint);
                    break;
                case "11":
                    if(!constraint.getValue1().equals(checkInfoDTO.getGender())){
                        check = false;
                    }
                    break;
                case "12":
                    List<ClaimRuleInfo> list = selectRuleInfoMapper.selectDutyCodeRuleList(RuleElement.YEARDEDUCTIBLE.getCode(), ElementUnit.MONEY.getCode(), checkInfoDTO.getRiskCode(), checkInfoDTO.getPlanCode(), checkInfoDTO.getDutyCode());
                    if (list.size()>0) {
                        int dayPass = differentDays(checkInfoDTO.getValidStartDate(), checkInfoDTO.getTreatmentStartDate());
                        int dayCheck = list.get(0).getElementValue().intValue();
                        if(dayPass<=dayCheck&&"N".equals(constraint.getValue1())){
                            check = false;
                        }
                        if(dayPass>dayCheck&&"Y".equals(constraint.getValue1())){
                            check = false;
                        }
                    }
                    break;
                case "30":
                    if(!constraint.getValue1().equals(checkInfoDTO.getHospitalcode())){
                        check = false;
                    }
                    break;
                case "31":
                    constraintList31.add(constraint);
                    break;
                case "32":
                    constraintList32.add(constraint);
                    break;
                case "33":
                    constraintList33.add(constraint);
                    break;
                case "34":
                    constraintList34.add(constraint);
                    break;
                case "35":
                    constraintList35.add(constraint);
                    break;
                case "36":
                    constraintList36.add(constraint);
                    break;
                default:
                    break;

            }
        }
        if(!constraintList31.isEmpty() || !constraintList32.isEmpty()
                || !constraintList33.isEmpty() || !constraintList34.isEmpty()
                || !constraintList35.isEmpty() || !constraintList36.isEmpty()){
            BaseProviderInfo providerInfo = new BaseProviderInfo(); //todo 查询医院信息
            if(check){
                for (ClaimRuleConstraint constraint : constraintList31) {
                    if(!constraint.getValue1().equals(providerInfo.getFirstAttribute())){
                        check = false;
                    }else {
                        check = true;
                        break;
                    }
                }
            }





        }



        return check;
    }

    @Override
    public BigDecimal getUesdValue(List<ClaimCaseBillInfo> claimCaseBillInfoList, QueryUsedDTO queryUsedDTO) {

        BigDecimal used = BigDecimal.ZERO;

        String ruleElement = queryUsedDTO.getRuleElement();
        String elementUnit = queryUsedDTO.getElementUnit();
        String policyNo = queryUsedDTO.getPolicyNo();
        String policyItemNo = queryUsedDTO.getPolicyItemNo();
        String riskCode = queryUsedDTO.getRiskCode();
        String planCode = queryUsedDTO.getPlanCode();
        String dutyCode = queryUsedDTO.getDutyCode();
        String dutyDetailCode = queryUsedDTO.getDutyDetailCode();
        String feeitemCode = queryUsedDTO.getFeeitemCode();
        Date startDate = queryUsedDTO.getStartDate();
        Date endDate = queryUsedDTO.getEndDate();
        String hospitalCode = queryUsedDTO.getHospitalCode();
        String department = queryUsedDTO.getDepartment();
        Date treatmentStartDateNow = queryUsedDTO.getTreatmentStartDate();


        ClaimCaseCalItemDTO claimCaseCalItemDTO = new ClaimCaseCalItemDTO();
        claimCaseCalItemDTO.setPolicyNo(policyNo);
        claimCaseCalItemDTO.setPolicyItemNo(policyItemNo);
        claimCaseCalItemDTO.setRiskCode(riskCode);
        claimCaseCalItemDTO.setPlanCode(planCode);
        claimCaseCalItemDTO.setDutyCode(dutyCode);
        claimCaseCalItemDTO.setDutyDetailCode(dutyDetailCode);
        claimCaseCalItemDTO.setFeeItemCode(feeitemCode);

        for (ClaimCaseBillInfo billInfo : claimCaseBillInfoList) {
            Date treatmentStartDatePass = billInfo.getTreatmentStartDate();
            List<ClaimCaseBillDetailInfo> detailInfoList = billInfo.getClaimCaseBillDetailInfoList();
            for (ClaimCaseBillDetailInfo detailInfo : detailInfoList) {

                if(policyNo.equals(detailInfo.getPolicyNo())&&policyItemNo.equals(detailInfo.getPolicyItemNo())
                    &&riskCode.equals(detailInfo.getRiskCode())&&planCode.equals(detailInfo.getPlanCode())){

                    if(!"".equals(dutyCode)&&!detailInfo.getDutyCode().equals(dutyCode)){
                        continue;
                    }
                    if(!"".equals(dutyDetailCode)&&!detailInfo.getDutyDetailCode().equals(dutyDetailCode)){
                        continue;
                    }
                    if(!"".equals(feeitemCode)&&!detailInfo.getFeeItemCode().equals(feeitemCode)){
                        continue;
                    }

                    if(RuleElement.YEARDEDUCTIBLE.getCode().equals(ruleElement)){
                        used = used.add(detailInfo.getDeduUsed());
                    }
                    if(RuleElement.TIMEDEDUCTIBLE.getCode().equals(ruleElement)){
                        if(treatmentStartDateNow.compareTo(treatmentStartDatePass)==0
                                &&hospitalCode.equals(billInfo.getHospitalCode())
                                &&department.equals(billInfo.getDepartment())){
                            used = used.add(detailInfo.getDeduUsed());
                        }
                    }
                    if(RuleElement.YEARLIMIT.getCode().equals(ruleElement)){
                        if(ElementUnit.DAY.getCode().equals(elementUnit)){
                            if(treatmentStartDateNow.compareTo(treatmentStartDatePass)!=0){
                                used = used.add(new BigDecimal(billInfo.getTreatmentDays()));
                            }
                        }else if(ElementUnit.TIME.getCode().equals(elementUnit)){
                            if(treatmentStartDateNow.compareTo(treatmentStartDatePass)==0
                                    &&hospitalCode.equals(billInfo.getHospitalCode())
                                    &&department.equals(billInfo.getDepartment())){
                                used = used.add(BigDecimal.ONE);
                            }
                        }else {
                            used = used.add(detailInfo.getCalAmount());
                        }
                    }
                    if(RuleElement.DAYLIMIT.getCode().equals(ruleElement)){
                        if(treatmentStartDateNow.compareTo(treatmentStartDatePass)==0){
                            used = used.add(detailInfo.getCalAmount().subtract(new BigDecimal(billInfo.getTreatmentDays())));
                        }
                    }
                    if(RuleElement.TIMELIMIT.getCode().equals(ruleElement)){
                        if(treatmentStartDateNow.compareTo(treatmentStartDatePass)==0
                                &&hospitalCode.equals(billInfo.getHospitalCode())
                                &&department.equals(billInfo.getDepartment())){
                            used = used.add(detailInfo.getCalAmount());
                        }
                    }
                }


            }
        }





        Object data = remoteClaimCalService.getCaInfo(claimCaseCalItemDTO).get(AjaxResult.DATA_TAG);
        List<ClaimCaseCalItemDTO> claimCaseCalItemDTOList = new ArrayList<>();
        if(data != null){
            String jsonCaseBillInfoStr = JSON.toJSONString(data);
            claimCaseCalItemDTOList = JSON.parseArray(jsonCaseBillInfoStr,ClaimCaseCalItemDTO.class);
        }
        Set<String> times = new HashSet<>();
        for (ClaimCaseCalItemDTO itemDTO : claimCaseCalItemDTOList) {
            Date treatmentStartDatePass = itemDTO.getTreatmentStartDate();

            if(RuleElement.YEARDEDUCTIBLE.getCode().equals(ruleElement)||RuleElement.TIMEDEDUCTIBLE.getCode().equals(ruleElement)){
                if(RuleElement.YEARDEDUCTIBLE.getCode().equals(ruleElement)){
                    if(treatmentStartDatePass.compareTo(startDate)>=0&&treatmentStartDatePass.compareTo(endDate)<=0){
                        used = used.add(itemDTO.getDeduUsed());
                    }
                }else {
                    if(treatmentStartDatePass.compareTo(startDate)==0
                            &&hospitalCode.equals(itemDTO.getHospitalCode())
                            &&department.equals(itemDTO.getDepartment())){
                        used = used.add(itemDTO.getDeduUsed());
                    }
                }
            }else {
                if(RuleElement.YEARLIMIT.getCode().equals(ruleElement)&ElementUnit.DAY.getCode().equals(elementUnit)){
                    int days = differentDays(itemDTO.getTreatmentStartDate(), itemDTO.getTreatmentEndDate());
                    used = used.add(new BigDecimal(days));
                }else if(RuleElement.YEARLIMIT.getCode().equals(ruleElement)&ElementUnit.TIME.getCode().equals(elementUnit)){
                    times.add(treatmentStartDatePass+"-"+hospitalCode+"-"+department);
                }else {
                    if(RuleElement.YEARLIMIT.getCode().equals(ruleElement)){
                        if(treatmentStartDatePass.compareTo(startDate)>=0&&treatmentStartDatePass.compareTo(endDate)<=0){
                            used = used.add(itemDTO.getCalAmount());
                        }
                    }else if(RuleElement.DAYLIMIT.getCode().equals(ruleElement)){
                        if(treatmentStartDateNow.compareTo(treatmentStartDatePass)==0){
                            used = used.add(itemDTO.getCalAmount());
                        }
                    }else if(RuleElement.TIMELIMIT.getCode().equals(ruleElement)){
                        if(treatmentStartDateNow.compareTo(treatmentStartDatePass)==0
                                &&hospitalCode.equals(itemDTO.getHospitalCode())
                                &&department.equals(itemDTO.getDepartment())){
                            used = used.add(itemDTO.getCalAmount());
                        }
                    }
                }
            }
        }
        if(RuleElement.YEARLIMIT.getCode().equals(ruleElement)&ElementUnit.TIME.getCode().equals(elementUnit)){
            used = new BigDecimal(times.size());
        }
        return used;
    }



    //日期推移
    public Date getDatePast(Date beginDate,int Value,String Unit){
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginDate);
        if("Y".equals(Unit)){
            begin.add(Calendar.YEAR, Value);
        }else if("M".equals(Unit)){
            begin.add(Calendar.MONTH, Value);
        }else {
            begin.add(Calendar.DAY_OF_MONTH, Value);
        }
        return begin.getTime();
    }

    /**
     * 年龄计算
     * */
    private int getAge(Date birthday, Date visitdate){
        int age = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(visitdate);
        if (cal.before(birthday)) {
            return -1;
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {age--;}
            }else{
                age--;
            }
        }
        return age;
    }

    public int differentDays(Date startDate, Date endDate){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startDate);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endDate);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //不同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else    //同一年
        {
            return day2 - day1;
        }
    }
}
