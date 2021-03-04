package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCaseCal;
import com.paic.ehis.claimflow.domain.vo.CalBillSummaryVo;
import com.paic.ehis.claimflow.domain.vo.CalConclusionVo;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalMapper;
import com.paic.ehis.claimflow.service.IClaimCaseCalService;
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
            // TODO: 获取‘是否仅结算理赔责任’ 是01-非全赔 否02-全赔
            if(StringUtils.isNotBlank(calConclusionVo.getHospitalCode())) {
                BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
                baseProviderSettle.setProviderCode(calConclusionVo.getHospitalCode());
                if (getProviderInfoService.selectsettleInfoNew(baseProviderSettle).size()>0) {
                    List<BaseProviderSettle> settleList = getProviderInfoService.selectsettleInfoNew(baseProviderSettle);
                    if(!settleList.isEmpty()) {
                        String claimFlag = settleList.get(0).getClaimFlag();
                        calConclusionVo.setClaimFlag(claimFlag);

                        // TODO: 需要判断，是否申诉案件；若是 计算 本次支付差额
                        if(calConclusionVo.getIsAppeal().equals("01")) {
                            /***
                             * 本次支付差额
                             * 1、当前案件为申诉案件时显示该字段；
                             * 2、本次支付差额（人民币）=本次赔付金额（非全赔）/折后金额（全赔）-申诉原案件赔付金额/折后金额；
                             * 3、本次支付差额（外币）=本次外币给付金额-申诉原案件外币给付金额；
                             * 4、若本次支付差额为负值则显示负号，代表需要收费
                             * 5、显示格式为金额+币种
                             */
//                        BigDecimal payAmount = calConclusionVo.getPayAmount(); // 赔付金额
//                        BigDecimal discountAmount = calConclusionVo.getSumHosDiscountAmount();// 折扣金额
                        }

                        /**
                         * 外币给付金额
                         * 1、非全赔医院：根据账单币种及汇率对赔付金额进行汇率转换
                         * 2、全赔医院：根据账单币种及汇率对折后金额进行汇率转换
                         * Eg：账单币种HKD，汇率：0.9，赔付金额900CNY
                         * 外币给付金额：900CNY/0.9=1000HKD
                         */
                        BigDecimal exchangeRate = calConclusionVo.getExchangeRate();
                        BigDecimal payAmount = calConclusionVo.getPayAmount();

                        BigDecimal billAmount = calConclusionVo.getSumBillAmount();
                        BigDecimal discountAmount = calConclusionVo.getSumHosDiscountAmount();// 折扣金额

                        if(exchangeRate != null) {
                            //01-非全赔
                            if("01".equals(claimFlag)) {
                                if(payAmount != null) {
                                    BigDecimal payAmountForeign = payAmount.divide(exchangeRate,20,BigDecimal.ROUND_HALF_UP);
                                    calConclusionVo.setPayAmountForeign(payAmountForeign);

                                }
                            }
                            //02-全赔
                            if("02".equals(claimFlag)) {
                                if(billAmount != null && discountAmount != null) {
                                    BigDecimal subtractVal = billAmount.subtract(discountAmount);
                                    BigDecimal payAmountForeign = subtractVal.divide(exchangeRate,20,BigDecimal.ROUND_HALF_UP);
                                    calConclusionVo.setPayAmountForeign(payAmountForeign);
                                }
                            }
                        }

                    }
                }
            }
            if ("05".equals(calConclusionVo.getPayConclusion()) || "10".equals(calConclusionVo.getPayConclusion())){
                calConclusionVo.setCalAmount(new BigDecimal(String.valueOf(0)));
            }
            ClaimCaseCal claimCaseCal = new ClaimCaseCal();
            claimCaseCal.setRptNo(rptNo);
            claimCaseCal.setPayConclusion(calConclusionVo.getPayConclusion());
            claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
            claimCaseCal.setUpdateTime(DateUtils.getNowDate());
            claimCaseCalMapper.updateClaimCaseCalByRptNo(claimCaseCal);
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
        try {
            claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
            claimCaseCal.setUpdateTime(DateUtils.getNowDate());
            int i = claimCaseCalMapper.updateClaimCaseCalByRptNo(claimCaseCal);
            if (i > 0){
                reult = claimCaseCalMapper.selectClaimCaseCalInformation(claimCaseCal.getRptNo());
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

}
