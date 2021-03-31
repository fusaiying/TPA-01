package com.paic.ehis.claimcal.service.impl;

import com.alibaba.fastjson.JSON;
import com.paic.ehis.claimcal.domain.ClaimProductDutyDetail;
import com.paic.ehis.claimcal.domain.PolicyRiskRelation;
import com.paic.ehis.claimcal.domain.SyncExchangeRate;
import com.paic.ehis.claimcal.mapper.ClaimProductDutyDetailMapper;
import com.paic.ehis.claimcal.mapper.PolicyRiskRelationMapper;
import com.paic.ehis.claimcal.service.*;
import com.paic.ehis.common.core.constant.HttpStatus;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.RemoteClaimCalService;
import com.paic.ehis.system.api.domain.BaseProviderSettle;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalInfo;
import com.paic.ehis.system.api.domain.ClaimCaseCalculateInfo;
import com.paic.ehis.system.api.domain.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ClaimCalServiceImpl implements IClaimCalService {

    @Autowired
    private RemoteClaimCalService remoteClaimCalService;

    @Autowired
    private PolicyRiskRelationMapper policyRiskRelationMapper;

    @Autowired
    private ClaimProductDutyDetailMapper claimProductDutyDetailMapper;

    @Autowired
    private IExchangeRateService exchangeRateService;

    @Autowired
    private GetProviderInfoService getProviderInfoService;

    @Autowired
    private PayableService payableService;

    @Autowired
    private DeductibleService deductibleService;

    @Autowired
    private CopayService copayService;

    @Autowired
    private UpLimitService upLimitService;

    @Autowired
    private DataSaveService dataSaveService;
    /**
     * 本案件理算历史数据清理
     * @param rgtNo 报案号
     * @return
     */
    @Override
    public boolean Clear(String rgtNo)
    {
        remoteClaimCalService.Clear(rgtNo);

        return false;
    }

    /**
     * 案件理算计算处理
     * @param rgtNo 报案号
     * @return
     */
    @Override
    public boolean Calculate(String rgtNo)
    {

        //获取案件信息
        Object data = remoteClaimCalService.getCaseInfo(rgtNo).get(AjaxResult.DATA_TAG);
        ClaimCaseCalculateInfo claimCaseCalculateInfo = new ClaimCaseCalculateInfo();
        if(data != null){
            String jsonCaseInfoStr = JSON.toJSONString(data);
            claimCaseCalculateInfo = JSON.parseObject(jsonCaseInfoStr, ClaimCaseCalculateInfo.class);
        }

        //责任匹配

        //拒赔判断

        //可理算金额和汇率转换
        claimCaseCalculateInfo = payableService.calculatePayable(claimCaseCalculateInfo);

        //免赔额计算
        claimCaseCalculateInfo = deductibleService.calculateDeductible(claimCaseCalculateInfo);

        //赔付比例计算
        claimCaseCalculateInfo = copayService.calculateCopay(claimCaseCalculateInfo);

        //限额计算
        claimCaseCalculateInfo = upLimitService.calculateUpLimit(claimCaseCalculateInfo);

        //数据落库
        boolean save = dataSaveService.calculateDataSave(claimCaseCalculateInfo);


//        ClaimCaseCalInfo cccI = new ClaimCaseCalInfo();
//
//        Object casePolicyData = remoteClaimCalService.getCasePolicyInfo(rgtNo).get(AjaxResult.DATA_TAG);
//        List<ClaimCasePolicyDTO> claimCasePolicyDTOList  = new ArrayList<ClaimCasePolicyDTO>();
//        if(casePolicyData != null){
//            String jsonCasePolicyStr = JSON.toJSONString(casePolicyData);
//            claimCasePolicyDTOList = JSON.parseArray(jsonCasePolicyStr,ClaimCasePolicyDTO.class);
//        }
//
//        String policyNo = claimCasePolicyDTOList.get(0).getPolicyNo();
//        String policyItemNo = claimCasePolicyDTOList.get(0).getPolicyItemNo();
//        String planCode = claimCasePolicyDTOList.get(0).getPlanCode();
//
//        PolicyRiskRelation prr = new PolicyRiskRelation();
//        prr.setPolicyNo(policyNo);
//        List<PolicyRiskRelation> policyRiskRelationList = policyRiskRelationMapper.selectPolicyRiskRelationList(prr);
//        String riskCode = policyRiskRelationList.get(0).getRiskCode();
//
//        ClaimProductDutyDetail cpd = new ClaimProductDutyDetail();
//        cpd.setRiskCode(riskCode);
//        cpd.setPlanCode(planCode);
//        List<ClaimProductDutyDetail> claimProductDutyDetailList = claimProductDutyDetailMapper.selectClaimProductDutyDetailList(cpd);
//        String dutyCode = claimProductDutyDetailList.get(0).getDutyCode();
//        String dutyDetailCode = claimProductDutyDetailList.get(0).getDutyDetailCode();
//
//        ClaimCaseCalDTO cccD = new ClaimCaseCalDTO();
//        List<ClaimCaseCalBillDTO>  cccBDList = new ArrayList<ClaimCaseCalBillDTO>();
//        List<ClaimCaseCalItemDTO> cccIDList = new ArrayList<ClaimCaseCalItemDTO>();
//        String billCurrency = "";
//        BigDecimal sumCalAmount = new BigDecimal(0);
//        BigDecimal sumFeeAmount = new BigDecimal(0);
//
//        //自付额
//        BigDecimal sumHosDiscountAmount = new BigDecimal(0);
//
//        SyncExchangeRate exchangeRate = new SyncExchangeRate();
//        BigDecimal sumCalAmountCNY = new BigDecimal(0);
//        for (ClaimCaseBillInfo ccbI : claimCaseBillInfoList) {
//            ClaimCaseBillDTO claimCaseBillDTO = ccbI.getClaimCaseBill();
//            List<ClaimCaseBillDetailDTO> ccbDList = ccbI.getClaimCaseBillDetailList();
//            billCurrency = claimCaseBillDTO.getBillCurrency();
//
//            //获取汇率
//            exchangeRate.setBeforeMoney(billCurrency);
//            exchangeRate.setAfterMoney("CNY");
//            exchangeRate.setDateConvert(claimCaseBillDTO.getTreatmentStartDate());
//            exchangeRate = exchangeRateService.getExchangeRate(exchangeRate);
////            if(StringUtils.isNull(exchangeRate)){
////                throw new RuntimeException("获取汇率失败！");
////            }
//            if(!StringUtils.isNull(exchangeRate)){//为获取到，按1汇率
//                exchangeRate = new SyncExchangeRate();
//                exchangeRate.setParities(new BigDecimal(1));
//            }
//
//            BigDecimal sumBillCalAmount = new BigDecimal(0);
//            for (ClaimCaseBillDetailDTO ccbD : ccbDList) {
//                ClaimCaseCalItemDTO cccID = new ClaimCaseCalItemDTO();
//                cccID.setRptNo(rgtNo);
//                cccID.setBillDetailId(ccbD.getDetailId());
//                cccID.setBillId(ccbD.getBillId());
//                cccID.setPolicyNo(policyNo);
//                cccID.setPolicyItemNo(policyItemNo);
//                cccID.setRiskCode(riskCode);
//                cccID.setPlanCode(planCode);
//                cccID.setDutyCode(dutyCode);
//                cccID.setDutyDetailCode(dutyDetailCode);
//                cccID.setFeeItemCode(ccbD.getFeeItemCode());
//                BigDecimal calAmount = ccbD.getBillDetailAmount().subtract(StringUtils.isNull(ccbD.getBillDetailCopay()) ? BigDecimal.ZERO : ccbD.getBillDetailCopay()).
//                        subtract(StringUtils.isNull(ccbD.getAdvancePayment()) ? BigDecimal.ZERO : ccbD.getAdvancePayment()).
//                        subtract(StringUtils.isNull(ccbD.getSelfAmount()) ? BigDecimal.ZERO : ccbD.getSelfAmount()).
//                        subtract(StringUtils.isNull(ccbD.getHosDiscountAmount()) ? BigDecimal.ZERO : ccbD.getHosDiscountAmount()).
//                        subtract(StringUtils.isNull(ccbD.getUnableAmount()) ? BigDecimal.ZERO : ccbD.getUnableAmount());
//                BigDecimal calAmountCNY = calAmount.multiply(exchangeRate.getParities());
//                cccID.setCalAmount(calAmountCNY.setScale(2,BigDecimal.ROUND_HALF_UP));
//                cccID.setRefusedAmount(new BigDecimal(0));
//                cccID.setDeduUsed(new BigDecimal(0));
//                cccID.setPayRate(new BigDecimal(100));
//                sumBillCalAmount = sumBillCalAmount.add(cccID.getCalAmount());
//                sumCalAmountCNY = sumCalAmountCNY.add(calAmountCNY);
//                sumFeeAmount = sumFeeAmount.add(ccbD.getBillDetailAmount());
//                sumHosDiscountAmount = sumHosDiscountAmount.add(ccbD.getHosDiscountAmount());
//
//                cccIDList.add(cccID);
//            }
//
//            ClaimCaseCalBillDTO cccBD = new ClaimCaseCalBillDTO();
//            cccBD.setRptNo(claimCaseBillDTO.getRptNo());
//            cccBD.setBillId(claimCaseBillDTO.getBillId());
//            cccBD.setCalAmount(sumBillCalAmount);
//            cccBD.setPayAmount(sumBillCalAmount);
//            cccBD.setRefusedAmount(new BigDecimal(0));
//            cccBD.setDeduUsed(new BigDecimal(0));
//            sumCalAmount = sumCalAmount.add(sumBillCalAmount);
//
//
//            cccBDList.add(cccBD);
//        }
//
//        cccD.setRptNo(rgtNo);
//        cccD.setBillCurrency(billCurrency);
//        cccD.setCalAmount(sumCalAmount);
//        cccD.setPayAmount(sumCalAmount);
//        cccD.setRefusedAmount(new BigDecimal(0));
//
//        //追讨金额 =  账单金额 - 折扣金额 -  赔付金额 -  自付额
//        cccD.setDebtAmount(sumFeeAmount.subtract(sumCalAmount).subtract(sumHosDiscountAmount));
//        cccD.setExchangeRate(exchangeRate.getParities());
//        cccD.setPayAmountForeign(sumCalAmountCNY.divide(exchangeRate.getParities()).setScale(2,BigDecimal.ROUND_HALF_UP));
//
//        cccI.setClaimCaseCalInfo(cccD);
//        cccI.setClaimCaseCalBillList(cccBDList);
//        cccI.setClaimCaseCalItemList(cccIDList);
//
//        AjaxResult ar = remoteClaimCalService.saveCaseCalInfo(cccI);
//        String jsonCode = JSON.toJSONString(ar.get(AjaxResult.CODE_TAG));
//        String dealCode = JSON.parseObject(jsonCode,String.class);
//        if(String.valueOf(HttpStatus.SUCCESS).equals(dealCode)){
//            return true;
//        }

        return save;
    }

}
