package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.domain.dto.BillDetailDTO;
import com.paic.ehis.claimflow.domain.vo.CalConclusionVo;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillItemVo;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillVo;
import com.paic.ehis.claimflow.mapper.*;
import com.paic.ehis.claimflow.service.IClaimCaseCalBillService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 案件赔付账单明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseCalBillServiceImpl implements IClaimCaseCalBillService
{
    @Autowired
    private ClaimCaseCalBillMapper claimCaseCalBillMapper;

    @Autowired
    private ClaimCaseCalItemMapper claimCaseCalItemMapper;

    @Autowired
    private ClaimCaseCalMapper claimCaseCalMapper;

    @Autowired
    private ClaimBatchMapper claimBatchMapper;

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    @Autowired
    private GetProviderInfoService getProviderInfoService;

    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;

    @Autowired
    private IExchangeRateService exchangeRateService;

    /**
     * 查询案件赔付账单明细
     * 
     * @param calBillId 案件赔付账单明细ID
     * @return 案件赔付账单明细
     */
    @Override
    public ClaimCaseCalBill selectClaimCaseCalBillById(Long calBillId)
    {
        return claimCaseCalBillMapper.selectClaimCaseCalBillById(calBillId);
    }

    /**
     * 查询案件赔付账单明细列表
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 案件赔付账单明细
     */
    @Override
    public List<ClaimCaseCalBill> selectClaimCaseCalBillList(ClaimCaseCalBill claimCaseCalBill)
    {
        return claimCaseCalBillMapper.selectClaimCaseCalBillList(claimCaseCalBill);
    }

    /**
     * 理算审核默认查询案件赔付信息 列表
     *
     * @param claimCaseCalBill 案件赔付信息
     * @return 案件赔付信息
     */
    @Override
    public List<CaseCalBillVo> selectCaseCalInformationList(ClaimCaseCalBill claimCaseCalBill)
    {
        claimCaseCalBill.setStatus("Y");
        List<CaseCalBillVo> caseCalBillVos = claimCaseCalBillMapper.selectCaseCalInformationList(claimCaseCalBill);
        SyncExchangeRate exchangeRate = new SyncExchangeRate();
        ClaimCaseBill claimCaseBill = claimCaseBillMapper.selectEarliestTreatmentBillByRptNo(claimCaseCalBill.getRptNo());
        for (CaseCalBillVo caseCalBillVo : caseCalBillVos) {
            if (StringUtils.isNotEmpty(caseCalBillVo.getBillCurrency()) && !"CNY".equals(caseCalBillVo.getBillCurrency())){
                //获取汇率
                exchangeRate.setBeforeMoney(caseCalBillVo.getBillCurrency());
                exchangeRate.setAfterMoney("CNY");
                exchangeRate.setDateConvert(claimCaseBill.getTreatmentStartDate());
                exchangeRate = exchangeRateService.getExchangeRate(exchangeRate);
                if(StringUtils.isNull(exchangeRate)){
                    exchangeRate = new SyncExchangeRate();
                    exchangeRate.setParities(new BigDecimal(1));
                }
                caseCalBillVo.setCopay(caseCalBillVo.getCopay().multiply(exchangeRate.getParities()).setScale(2,BigDecimal.ROUND_HALF_DOWN));
                caseCalBillVo.setBillAmount(caseCalBillVo.getBillAmount().multiply(exchangeRate.getParities()).setScale(2,BigDecimal.ROUND_HALF_DOWN));
                caseCalBillVo.setHosDiscountAmount(caseCalBillVo.getHosDiscountAmount().multiply(exchangeRate.getParities()).setScale(2,BigDecimal.ROUND_HALF_DOWN));
                caseCalBillVo.setUnableAmount(caseCalBillVo.getUnableAmount().multiply(exchangeRate.getParities()).setScale(2,BigDecimal.ROUND_HALF_DOWN));
            }
        }
        return caseCalBillVos;
    }

    /**
     * 新增案件赔付账单明细
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseCalBill(ClaimCaseCalBill claimCaseCalBill)
    {
        claimCaseCalBill.setCreateTime(DateUtils.getNowDate());
        return claimCaseCalBillMapper.insertClaimCaseCalBill(claimCaseCalBill);
    }

    /**
     * 修改案件赔付账单明细
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseCalBill(ClaimCaseCalBill claimCaseCalBill)
    {
        claimCaseCalBill.setUpdateTime(DateUtils.getNowDate());
        return claimCaseCalBillMapper.updateClaimCaseCalBill(claimCaseCalBill);
    }

    /**
     * 理算审核  账单明细保存  按钮
     *
     * @param billDetailDTO 案件赔付账单明细
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int billDetailsSave(BillDetailDTO billDetailDTO) {

        String rptNo = billDetailDTO.getBillDetailList().get(0).getRptNo();
        int index = rptNo.indexOf("-");

        int size=0;
        String claimFlag = "";
        BigDecimal billTotalAmount=new BigDecimal(String.valueOf(0.00));
        BigDecimal totalDiscountAmount=new BigDecimal(String.valueOf(0.00));
        BigDecimal totalSelfAmount=new BigDecimal(String.valueOf(0.00));
        ArrayList<ClaimCaseCalBill> claimCaseCalBills = new ArrayList<>();
        ArrayList<ClaimCaseCalItem> claimCaseCalItems = new ArrayList<>();
        ClaimCaseCal claimCaseCal =claimCaseCalMapper.selectClaimCaseCalByRptNo(rptNo);
        claimCaseCal.setRptNo(rptNo);
        claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCal.setUpdateTime(DateUtils.getNowDate());
        BigDecimal pay = new BigDecimal(String.valueOf(0));
        if (StringUtils.isNotEmpty(billDetailDTO.getBillDetailList())) {
            for (CaseCalBillVo caseCalBillVo : billDetailDTO.getBillDetailList()) {
                ClaimCaseCalBill claimCaseCalBill = new ClaimCaseCalBill();
                claimCaseCalBill.setPayAmount(caseCalBillVo.getPayAmount());
                claimCaseCalBill.setRemark(caseCalBillVo.getRemark());
                claimCaseCalBill.setPayConclusion(caseCalBillVo.getPayConclusion());
                claimCaseCalBill.setRefusedAmount(caseCalBillVo.getRefusedAmount());
                claimCaseCalBill.setCalBillId(caseCalBillVo.getCalBillId());
                claimCaseCalBill.setUpdateBy(SecurityUtils.getUsername());
                if ("05".equals(caseCalBillVo.getPayConclusion()) || "10".equals(caseCalBillVo.getPayConclusion())){//存在拒赔结论时
                    size += 1;
                }
                pay=pay.add(claimCaseCalBill.getPayAmount());

                claimCaseCalBills.add(claimCaseCalBill);
                billTotalAmount=billTotalAmount.add(StringUtils.isNull(caseCalBillVo.getBillAmount())?new BigDecimal(String.valueOf(0.00)):caseCalBillVo.getBillAmount());//因账单总金额暂未实现，只能求和
                totalDiscountAmount=totalDiscountAmount.add(StringUtils.isNull(caseCalBillVo.getHosDiscountAmount())?new BigDecimal(String.valueOf(0.00)):caseCalBillVo.getHosDiscountAmount());//折扣总金额
                totalSelfAmount=totalSelfAmount.add(StringUtils.isNull(caseCalBillVo.getCopay())?new BigDecimal(String.valueOf(0.00)):caseCalBillVo.getCopay());
                if (StringUtils.isNotEmpty(caseCalBillVo.getMinData())) {
                    for (CaseCalBillItemVo minDatum : caseCalBillVo.getMinData()) {
                        ClaimCaseCalItem claimCaseCalItem = new ClaimCaseCalItem();
                        claimCaseCalItem.setPolicyNo(minDatum.getPolicyNo());
                        claimCaseCalItem.setRiskCode(minDatum.getRiskCode());
                        claimCaseCalItem.setDutyDetailCode(minDatum.getDutyDetailCode());
                        claimCaseCalItem.setCalItemId(minDatum.getCalItemId());
                        claimCaseCalItem.setUpdateBy(SecurityUtils.getUsername());
                        claimCaseCalItems.add(claimCaseCalItem);
                    }
                }
            }
        }
        if (StringUtils.isNotEmpty(claimCaseCalItems)) {
            claimCaseCalItemMapper.bulkUpdateClaimCaseCalItem(claimCaseCalItems);
        }
        //判断是否全赔医院
        ClaimCase claimCase = claimCaseMapper.selectClaimCaseById(rptNo);
        ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(claimCase.getBatchNo());
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        baseProviderSettle.setProviderCode(claimBatch.getHospitalcode());
        baseProviderSettle.setOrgFlag("02");
        if (getProviderInfoService.selectsettleInfoNew(baseProviderSettle).size()>0) {
            BaseProviderSettle settle = getProviderInfoService.selectsettleInfoNew(baseProviderSettle).get(0);
            claimFlag=settle.getClaimFlag();
        }
        SyncExchangeRate exchangeRate = new SyncExchangeRate();
        exchangeRate.setBeforeMoney("CNY");
        exchangeRate.setAfterMoney(claimCaseCal.getBillCurrency());
        exchangeRate.setDateConvert(claimCaseBillMapper.selectEarliestTreatmentBillByRptNo(claimCaseCal.getRptNo()).getTreatmentStartDate());
        exchangeRate = exchangeRateService.getExchangeRate(exchangeRate);
        if(StringUtils.isNull(exchangeRate)){
            exchangeRate = new SyncExchangeRate();
            exchangeRate.setParities(new BigDecimal(1));
        }
        if ("01".equals(claimFlag)){//非全赔,如果是全赔，默认是账单总金额不变，且cal表账单总金额字段未加
            if(index < 0 ) {
                claimCaseCal.setCalAmount(pay);
            }
            claimCaseCal.setPayAmount(pay);
            claimCaseCal.setDebtAmount(new BigDecimal(String.valueOf(0.00)));
            //外币给付金额   1、非全赔医院：根据账单币种及汇率对赔付金额进行汇率转换
           // claimCaseCal.setPayAmountForeign(claimCaseCal.getCalAmount().divide(exchangeRate.getParities(),2,BigDecimal.ROUND_HALF_DOWN));
        }
        if ("02".equals(claimFlag)){//全赔医院
            claimCaseCal.setCalAmount(pay);
            //追讨金额=账单金额-折扣金额-赔付金额-流水号自付额；
            claimCaseCal.setDebtAmount(billTotalAmount.subtract(totalDiscountAmount).subtract(totalSelfAmount).subtract(claimCaseCal.getCalAmount()));
            if(index < 0 ) {
                claimCaseCal.setPayAmount(billTotalAmount.subtract(totalDiscountAmount));
            }
            //外币给付金额   2、全赔医院：根据账单币种及汇率对折后金额进行汇率转换
           // claimCaseCal.setPayAmountForeign((billTotalAmount.subtract(totalDiscountAmount)).divide(exchangeRate.getParities(),2,BigDecimal.ROUND_HALF_DOWN));
        }

        /***
         * modify by: houjiawei
         * time: 2021-04-02
         *计算 外币给付金额
         * start
        */
        CalConclusionVo calConclusionVo = claimCaseCalMapper.selectClaimCaseCalInformation(rptNo);
        if(null != calConclusionVo) {
            BigDecimal payAmount1 = calConclusionVo.getPayAmount();
            BigDecimal billAmount1 = calConclusionVo.getSumBillAmount();
            BigDecimal discountAmount1 = calConclusionVo.getSumHosDiscountAmount();// 折扣金额

            BigDecimal voRate = calConclusionVo.getExchangeRate();
            voRate = voRate == null ? exchangeRate.getParities() : voRate;
            if(index < 0) {
                //01-非全赔
                if("01".equals(claimFlag)) {
                    if(payAmount1 != null) {
                        //BigDecimal payAmountForeign = payAmount1.divide(voRate,20,BigDecimal.ROUND_HALF_UP);
                        claimCaseCal.setPayAmountForeign(payAmount1);
                    }
                }
                // 02-全赔
                if("02".equals(claimFlag)) {
                    if(billAmount1 != null && discountAmount1 != null) {
                        BigDecimal subtractVal = billAmount1.subtract(discountAmount1);
                        // BigDecimal payAmountForeign = subtractVal.divide(voRate,20,BigDecimal.ROUND_HALF_UP);
                        claimCaseCal.setPayAmountForeign(subtractVal);
                    }
                }
            }

            /**
             * 需要判断，是否申诉案件；若是 计算 本次支付差额
             * modify by  :  hjw   如果是申诉案件 ， 需要更新 payMount字段值为 本次支付差额
             * time:2021-06-29
             */
            /** start */
            ClaimCaseCal nowClaimCaseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(rptNo);
            if(index > 0 && (nowClaimCaseCal.getCreateTime().getTime() == nowClaimCaseCal.getUpdateTime().getTime())) {
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
                BigDecimal prePayAmount = precalConclusionVo.getCalAmount() == null ?  defaultValue : precalConclusionVo.getCalAmount(); // 原案件 赔付金额
                BigDecimal preSumBillAmount = precalConclusionVo.getSumBillAmount() == null ? defaultValue : precalConclusionVo.getSumBillAmount(); //原案件 账单金额
                BigDecimal preDiscountAmount = precalConclusionVo.getSumHosDiscountAmount() == null ? defaultValue : precalConclusionVo.getSumHosDiscountAmount();//原案件 折扣金额

                String billCurrency = calConclusionVo.getBillCurrency();  // 账单币种
                BigDecimal payAmount = calConclusionVo.getCalAmount() == null ? defaultValue : calConclusionVo.getCalAmount(); // 赔付金额
                BigDecimal sumBillAmount = calConclusionVo.getSumBillAmount() == null ? defaultValue : calConclusionVo.getSumBillAmount(); // 账单金额
                BigDecimal discountAmount = calConclusionVo.getSumHosDiscountAmount() == null ? defaultValue : calConclusionVo.getSumHosDiscountAmount();// 折扣金额

                if (billCurrency.equalsIgnoreCase("CNY")) {
                    // 全赔付
                    if (claimFlag.equals("01")) {
                        //calConclusionVo.setPaymentDifference(payAmount.subtract(prePayAmount));
                        claimCaseCal.setPayAmount(payAmount.subtract(prePayAmount));
                    }
                    if (claimFlag.equals("02")) {
                        BigDecimal subtract = sumBillAmount.subtract(discountAmount); // 本次 折后金额
                        BigDecimal subtract1 = preSumBillAmount.subtract(preDiscountAmount); // 原案件 折后金额
                       //calConclusionVo.setPaymentDifference(subtract.subtract(subtract1));
                        claimCaseCal.setPayAmount(subtract.subtract(subtract1));
                    }
                } else {
                    //本次支付差额（外币）=本次外币给付金额-申诉原案件外币给付金额；
                    BigDecimal diffValue = billAmount1.subtract(discountAmount1).subtract(precalConclusionVo.getPayAmountForeign());
                    claimCaseCal.setPayAmountForeign(diffValue);
                    claimCaseCal.setPayAmount(diffValue.multiply(voRate));

                }
            }
            /** end */
        }
        /***
         * end
         */
        if (size==billDetailDTO.getBillDetailList().size()){//存在拒赔结论时，赔付金额为0，
            claimCaseCal.setCalAmount(new BigDecimal(String.valueOf(0.00)));
        }
        claimCaseCal.setRefusedAmount(billTotalAmount.subtract(totalDiscountAmount).subtract(claimCaseCal.getCalAmount()));
        //claimCaseCal.setExchangeRate(exchangeRate.getParities());
        claimCaseCalMapper.updateClaimCaseCal(claimCaseCal);
        return claimCaseCalBillMapper.bulkUpdateClaimCaseCalBill(claimCaseCalBills);
    }

    /**
     * 批量删除案件赔付账单明细
     * 
     * @param calBillIds 需要删除的案件赔付账单明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalBillByIds(Long[] calBillIds)
    {
        return claimCaseCalBillMapper.deleteClaimCaseCalBillByIds(calBillIds);
    }

    /**
     * 删除案件赔付账单明细信息
     * 
     * @param calBillId 案件赔付账单明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalBillById(Long calBillId)
    {
        return claimCaseCalBillMapper.deleteClaimCaseCalBillById(calBillId);
    }
}
