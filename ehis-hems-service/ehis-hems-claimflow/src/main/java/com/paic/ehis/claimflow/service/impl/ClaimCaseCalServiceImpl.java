package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCase;
import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCaseCal;
import com.paic.ehis.claimflow.domain.SyncExchangeRate;
import com.paic.ehis.claimflow.domain.vo.CalBillSummaryVo;
import com.paic.ehis.claimflow.domain.vo.CalConclusionVo;
import com.paic.ehis.claimflow.mapper.ClaimCaseBillMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseMapper;
import com.paic.ehis.claimflow.service.IClaimCaseCalService;
import com.paic.ehis.claimflow.service.IExchangeRateService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.domain.BaseProviderSettle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 案件赔付信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseCalServiceImpl implements IClaimCaseCalService
{
    @Autowired
    private ClaimCaseCalMapper claimCaseCalMapper;

    @Autowired
    private GetProviderInfoService getProviderInfoService;

    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;

    @Autowired
    private IExchangeRateService exchangeRateService;

    @Autowired
    private ClaimCaseMapper cClaimCaseMapper;

    /**
     * 查询案件赔付信息
     * 
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    @Override
    public ClaimCaseCal selectClaimCaseCalByRptNo(String rptNo)
    {
        return claimCaseCalMapper.selectClaimCaseCalByRptNo(rptNo);
    }

    /**
     * 理算审核 默认查询案件汇总信息 列表
     *
     * @param claimCaseBill 案件赔付账单明细
     * @return 案件赔付账单明细集合
     */
    @Override
    public CalBillSummaryVo selectCaseCalBillSummary(ClaimCaseBill claimCaseBill) {
        return claimCaseCalMapper.selectClaimCaseBillSummaryByRptNo(claimCaseBill.getRptNo());
    }

    /**
     * 理算审核  查询案件赔付结论信息及理算总值
     *
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    @Override
    public CalConclusionVo selectClaimCaseCalInformation(String rptNo) {

        CalConclusionVo calConclusionVo = claimCaseCalMapper.selectClaimCaseCalInformation(rptNo);
        if(null != calConclusionVo) {
            //获取‘是否仅结算理赔责任’ 是01-非全赔 否02-全赔
            if(StringUtils.isNotBlank(calConclusionVo.getHospitalCode())) {
                BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
                baseProviderSettle.setOrgFlag("01");
                baseProviderSettle.setProviderCode(calConclusionVo.getHospitalCode());
                List<BaseProviderSettle> baseProviderList = getProviderInfoService.selectsettleInfoNew(baseProviderSettle);
                if (!baseProviderList.isEmpty()) {
                    String claimFlag = baseProviderList.get(0).getClaimFlag();
                    calConclusionVo.setClaimFlag(claimFlag);
                    /**
                     * 外币给付金额
                     * 1、非全赔医院：根据账单币种及汇率对赔付金额进行汇率转换
                     * 2、全赔医院：根据账单币种及汇率对折后金额进行汇率转换
                     * Eg：账单币种HKD，汇率：0.9，赔付金额900CNY
                     * 外币给付金额：900CNY/0.9=1000HKD
                     */
                    SyncExchangeRate exchangeRate = new SyncExchangeRate();
                    exchangeRate.setBeforeMoney("CNY");
                    exchangeRate.setAfterMoney(calConclusionVo.getBillCurrency());
                    exchangeRate.setDateConvert(claimCaseBillMapper.selectEarliestTreatmentBillByRptNo(rptNo).getTreatmentStartDate());
                    exchangeRate = exchangeRateService.getExchangeRate(exchangeRate);
                    if(StringUtils.isNull(exchangeRate)){
                        exchangeRate = new SyncExchangeRate();
                        exchangeRate.setParities(new BigDecimal(1));
                    }
                    calConclusionVo.setExchangeRate(exchangeRate.getParities());
//                    BigDecimal payAmount1 = calConclusionVo.getPayAmount();
//
//                    BigDecimal billAmount1 = calConclusionVo.getSumBillAmount();
//                    BigDecimal discountAmount1 = calConclusionVo.getSumHosDiscountAmount();// 折扣金额

                    //01-非全赔
//                    if("01".equals(claimFlag)) {
//                        if(payAmount1 != null) {
//                            BigDecimal payAmountForeign = payAmount1.divide(exchangeRate.getParities(),20,BigDecimal.ROUND_HALF_UP);
//                            calConclusionVo.setPayAmountForeign(payAmountForeign);
//
//                        }
//                    }
                    //02-全赔
//                    if("02".equals(claimFlag)) {
//                        if(billAmount1 != null && discountAmount1 != null) {
//                            BigDecimal subtractVal = billAmount1.subtract(discountAmount1);
//                            BigDecimal payAmountForeign = subtractVal.divide(exchangeRate.getParities(),20,BigDecimal.ROUND_HALF_UP);
//                            calConclusionVo.setPayAmountForeign(payAmountForeign);
//                        }
//                    }
                    // 需要判断，是否申诉案件；若是 计算 本次支付差额
                    if(StringUtils.isNotBlank(calConclusionVo.getIsAppeal()) && calConclusionVo.getIsAppeal().equals("02")) {

                        CalConclusionVo precalConclusionVo = claimCaseCalMapper.selectPreCalConclusionByRptNo(rptNo);
                        if(precalConclusionVo != null) {
                            precalConclusionVo = claimCaseCalMapper.selectClaimCaseCalInformation(precalConclusionVo.getRptNo());
                        }
                        /***
                         * 折后金额等于全部账单的（账单金额-折扣金额）之和
                         *
                         * 本次支付差额
                         * 1、当前案件为申诉案件时显示该字段；
                         * 2、本次支付差额（人民币）=本次赔付金额（非全赔）/折后金额（全赔）-申诉原案件赔付金额/折后金额；
                         * 3、本次支付差额（外币）=本次外币给付金额-申诉原案件外币给付金额；
                         * 4、若本次支付差额为负值则显示负号，代表需要收费
                         * 5、显示格式为金额+币种
                         */
                        BigDecimal defaultValue = new BigDecimal(0);

                       // BigDecimal preBillAmount = precalConclusionVo.getSumBillAmount() == null ?  defaultValue : precalConclusionVo.getSumBillAmount(); // 原案件

                        BigDecimal prePayAmount = precalConclusionVo.getCalAmount() == null ?  defaultValue : precalConclusionVo.getCalAmount(); // 原案件 赔付金额
                        BigDecimal preSumBillAmount = precalConclusionVo.getSumBillAmount() == null ? defaultValue : precalConclusionVo.getSumBillAmount(); //原案件 账单金额
                        BigDecimal preDiscountAmount = precalConclusionVo.getSumHosDiscountAmount() == null ? defaultValue : precalConclusionVo.getSumHosDiscountAmount();//原案件 折扣金额

                        String billCurrency = calConclusionVo.getBillCurrency();  // 账单币种
                        BigDecimal payAmount = calConclusionVo.getCalAmount() == null ? defaultValue : calConclusionVo.getCalAmount(); // 赔付金额
                        BigDecimal sumBillAmount = calConclusionVo.getSumBillAmount() == null ? defaultValue : calConclusionVo.getSumBillAmount(); // 账单金额
                        BigDecimal discountAmount = calConclusionVo.getSumHosDiscountAmount() == null ? defaultValue : calConclusionVo.getSumHosDiscountAmount();// 折扣金额

                        if (billCurrency.equalsIgnoreCase("CNY")) {
                           //  paymentDifference
                            // 全赔付
                            if (claimFlag.equals("01")) {
                                calConclusionVo.setPaymentDifference(payAmount.subtract(prePayAmount));
                            }
                            if (claimFlag.equals("02")) {
                                BigDecimal subtract = sumBillAmount.subtract(discountAmount); // 本次 折后金额
                                BigDecimal subtract1 = preSumBillAmount.subtract(preDiscountAmount); // 原案件 折后金额
                                calConclusionVo.setPaymentDifference(subtract.subtract(subtract1));
                            }
                        } else {
                            //本次支付差额（外币）=本次外币给付金额-申诉原案件外币给付金额；
                            calConclusionVo.setPaymentDifference(calConclusionVo.getPayAmountForeign().subtract(precalConclusionVo.getPayAmountForeign()));
//                            //01-非全赔
//                            if("01".equals(claimFlag)) {
//                                if(prePayAmount != null) {
//                                    //本次支付差额（外币）=本次外币给付金额-申诉原案件外币给付金额；
//                                    calConclusionVo.setPaymentDifference(calConclusionVo.getPayAmountForeign().subtract(precalConclusionVo.getPayAmountForeign()));
//                                    BigDecimal payAmountForeign = prePayAmount.divide(exchangeRate.getParities(),20,BigDecimal.ROUND_HALF_UP);
//                                    precalConclusionVo.setPayAmountForeign(payAmountForeign);
//
//
//                                }
//                            }
//                            //02-全赔
//                            if("02".equals(claimFlag)) {
//                                if(preSumBillAmount != null && preDiscountAmount != null) {
//                                    //本次支付差额（外币）=本次外币给付金额-申诉原案件外币给付金额；
//                                    calConclusionVo.setPaymentDifference(calConclusionVo.getPayAmountForeign().subtract(precalConclusionVo.getPayAmountForeign()));
//
//                                    BigDecimal subtractVa = preSumBillAmount.subtract(preDiscountAmount);
//                                    BigDecimal payAmountForeign = subtractVa.divide(exchangeRate.getParities(),20,BigDecimal.ROUND_HALF_UP);
//                                    precalConclusionVo.setPayAmountForeign(payAmountForeign);
//
//                                }
//                            }
                        }
                    }
                }
            }
            if ("05".equals(calConclusionVo.getPayConclusion()) || "10".equals(calConclusionVo.getPayConclusion())){
                calConclusionVo.setCalAmount(new BigDecimal(String.valueOf(0)));
            }
            if (StringUtils.isNotEmpty(calConclusionVo.getPayConclusion())) {
                ClaimCaseCal claimCaseCal = new ClaimCaseCal();
                claimCaseCal.setRptNo(rptNo);
                claimCaseCal.setPayConclusion(calConclusionVo.getPayConclusion());
                claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
                claimCaseCal.setUpdateTime(DateUtils.getNowDate());
                claimCaseCalMapper.updateClaimCaseCalByRptNo(claimCaseCal);
            }
        }

        return calConclusionVo;
    }

    /**
     * 查询案件赔付信息列表
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 案件赔付信息
     */
    @Override
    public List<ClaimCaseCal> selectClaimCaseCalList(ClaimCaseCal claimCaseCal)
    {
        return claimCaseCalMapper.selectClaimCaseCalList(claimCaseCal);
    }

    /**
     * 新增案件赔付信息
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseCal(ClaimCaseCal claimCaseCal)
    {
        claimCaseCal.setStatus("Y");
        claimCaseCal.setCreateBy(SecurityUtils.getUsername());
        claimCaseCal.setCreateTime(DateUtils.getNowDate());
        claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCal.setUpdateTime(DateUtils.getNowDate());
        return claimCaseCalMapper.insertClaimCaseCal(claimCaseCal);
    }

    /**
     * 修改案件赔付信息
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public CalConclusionVo updateClaimCaseCal(ClaimCaseCal claimCaseCal) {

        /**
         * modify by  :  hjw   更新数据可能多于一条， 还有无效数据也更新了
         */
        CalConclusionVo reult = null;
//        SyncExchangeRate exchangeRate = new SyncExchangeRate();
//        ClaimCaseCal caseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(claimCaseCal.getRptNo());
//        String currency="CNY";
//        try {
//            //获取汇率
//            if (currency.equals(claimCaseCal.getBillCurrency())) {
//                exchangeRate.setBeforeMoney(caseCal.getBillCurrency());
//                exchangeRate.setAfterMoney(claimCaseCal.getBillCurrency());
//                ClaimCaseBill claimCaseBill = claimCaseBillMapper.selectEarliestTreatmentBillByRptNo(claimCaseCal.getRptNo());
//                exchangeRate.setDateConvert(claimCaseBill.getTreatmentStartDate());
//                exchangeRate = exchangeRateService.getExchangeRate(exchangeRate);
//                if(StringUtils.isNull(exchangeRate)){
//                    return null;
//                }
//            }
//
//            claimCaseCal.setPayAmountForeign(exchangeRate.getParities().multiply(claimCaseCal.));
//            claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
//            claimCaseCal.setUpdateTime(DateUtils.getNowDate());
//            int i = claimCaseCalMapper.updateClaimCaseCalByRptNo(claimCaseCal);
//            if (i > 0){
//                reult = claimCaseCalMapper.selectClaimCaseCalInformation(claimCaseCal.getRptNo());
//            }
            try {
                /**
                 * modify by  :  hjw   如果是申诉案件 ， 需要更新 payMount字段值为 本次支付差额
                 * time:2021-06-29
                 */
                /** start */
                String rptNo = claimCaseCal.getRptNo();
                String username = SecurityUtils.getUsername();
                Date nowDate = DateUtils.getNowDate();
//                if(rptNo.indexOf("-") > 0 && claimCaseCal.getPaymentDifference() != null) {
//                    claimCaseCal.setPayAmount(claimCaseCal.getPaymentDifference());
//                    ClaimCaseCal nowClaimCaseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(rptNo);
//                    CalConclusionVo precalConclusionVo = claimCaseCalMapper.selectPreCalConclusionByRptNo(rptNo);
//                    if(null != precalConclusionVo) {
//                        BigDecimal defaultValue = new BigDecimal(0);
//                        ClaimCaseCal preClaimCaseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(precalConclusionVo.getRptNo());
//                        BigDecimal payAmount = nowClaimCaseCal.getPayAmount() == null ? defaultValue : nowClaimCaseCal.getPayAmount();
//                        BigDecimal payAmountForeign = nowClaimCaseCal.getPayAmountForeign() == null ? defaultValue : nowClaimCaseCal.getPayAmountForeign();
//                        BigDecimal prePayAmount = preClaimCaseCal.getPayAmount() == null ? defaultValue : preClaimCaseCal.getPayAmount();
//
//                        // 如果RMB，直接更新payAmount 为支付差额 否则  payAmouont 和  payAmountForeign 互换
//                        if(nowClaimCaseCal.getBillCurrency().equals("CNY")) {
//                            claimCaseCal.setPayAmount(payAmount.subtract(prePayAmount));
//                        }  else {
//                            BigDecimal exchangeRate = nowClaimCaseCal.getExchangeRate() == null ? new BigDecimal(1) : nowClaimCaseCal.getExchangeRate();
//                            claimCaseCal.setPayAmount(payAmountForeign.divide(exchangeRate,2));
//                            claimCaseCal.setPayAmountForeign(payAmount);
//                        }
//                    }
//                }
//                /** end */
                claimCaseCal.setUpdateBy(username);
                claimCaseCal.setUpdateTime(nowDate);
                int i = claimCaseCalMapper.updateClaimCaseCalByRptNo(claimCaseCal);

                ClaimCase claimCase = new ClaimCase();
                claimCase.setRptNo(rptNo);
                claimCase.setUpdateBy(username);
                claimCase.setUpdateTime(nowDate);
                claimCase.setCaseFlag("01");
                cClaimCaseMapper.updateClaimCase(claimCase);
                if (i > 0){
                    reult = claimCaseCalMapper.selectClaimCaseCalInformation(rptNo);
                }
        } catch (Exception e){

        }
        return reult;
    }

    /**
     * 批量删除案件赔付信息
     * 
     * @param calIds 需要删除的案件赔付信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalByIds(Long[] calIds)
    {
        return claimCaseCalMapper.deleteClaimCaseCalByIds(calIds);
    }

    /**
     * 删除案件赔付信息信息
     * 
     * @param calId 案件赔付信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalById(Long calId)
    {
        return claimCaseCalMapper.deleteClaimCaseCalById(calId);
    }

    /***
     * 案件理算  保存后 更新 claim_case_cal 表 赔付结论 为 空
     * @param rptNo
     * @return
     * auth: hjw
     */
    @Override
    public int updateClaimConclusionNull(String rptNo) {
        return claimCaseCalMapper.updateClaimConclusionNull(rptNo);
    }

    /***
     * 案件理算  币种选择后 更新 页面展示汇率及外币给付金额
     * @param claimCaseCal
     * @return
     * auth: flint
     */
    @Override
    public ClaimCaseCal selectExchangeRateForeign(ClaimCaseCal claimCaseCal) {
        SyncExchangeRate exchangeRate = new SyncExchangeRate();
        ClaimCaseCal caseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(claimCaseCal.getRptNo());
        try {
            //获取汇率
            if (!caseCal.getBillCurrency().equals(claimCaseCal.getBillCurrency())) {
                exchangeRate.setBeforeMoney(caseCal.getBillCurrency());
                exchangeRate.setAfterMoney(claimCaseCal.getBillCurrency());
                ClaimCaseBill claimCaseBill = claimCaseBillMapper.selectEarliestTreatmentBillByRptNo(claimCaseCal.getRptNo());
                exchangeRate.setDateConvert(claimCaseBill.getTreatmentStartDate());
                exchangeRate = exchangeRateService.getExchangeRate(exchangeRate);
                if (StringUtils.isNull(exchangeRate)) {
                    return null;
                }
            }
            claimCaseCal.setExchangeRate(exchangeRate.getParities().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            claimCaseCal.setPayAmountForeign(exchangeRate.getParities().multiply(claimCaseCal.getCalAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));

        } catch (Exception e) {

        }
        return claimCaseCal;
    }

    /***
     * 申诉案件根据 申诉的报案号，查询上一个报案信息
     * @param rptNo
     * @return CalConclusionVo
     * auth: hjw
     */
    public CalConclusionVo selectPreCalConclusionByRptNo(String rptNo) {
        CalConclusionVo precalConclusionVo = claimCaseCalMapper.selectPreCalConclusionByRptNo(rptNo);
        if(null != precalConclusionVo) {
            precalConclusionVo = this.selectClaimCaseCalInformation(precalConclusionVo.getRptNo());
        }
        return precalConclusionVo;
    }

}
