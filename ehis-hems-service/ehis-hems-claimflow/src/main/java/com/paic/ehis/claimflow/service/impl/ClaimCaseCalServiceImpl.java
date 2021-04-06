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

            // 需要判断，是否申诉案件 显示 本次支付差额
            if(StringUtils.isNotBlank(calConclusionVo.getIsAppeal()) && calConclusionVo.getIsAppeal().equals("02")) {
                String billCurrency = calConclusionVo.getBillCurrency();  // 账单币种
                if (billCurrency.equalsIgnoreCase("CNY")) {
                    calConclusionVo.setPaymentDifference(calConclusionVo.getPayAmount());
                } else {
                    calConclusionVo.setPaymentDifference(calConclusionVo.getPayAmountForeign());
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
        CalConclusionVo reult = null;
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
