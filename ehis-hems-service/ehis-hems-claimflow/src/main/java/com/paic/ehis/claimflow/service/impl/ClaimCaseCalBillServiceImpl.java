package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.domain.dto.BillDetailDTO;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillItemVo;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillVo;
import com.paic.ehis.claimflow.mapper.*;
import com.paic.ehis.claimflow.service.IClaimCaseCalBillService;
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
        return claimCaseCalBillMapper.selectCaseCalInformationList(claimCaseCalBill);
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
        String claimFlag = "";
        boolean flag = false;
        BigDecimal billTotalAmount=new BigDecimal(String.valueOf(0.00));
        BigDecimal totalDiscountAmount=new BigDecimal(String.valueOf(0.00));
        BigDecimal totalSelfAmount=new BigDecimal(String.valueOf(0.00));
        ArrayList<ClaimCaseCalBill> claimCaseCalBills = new ArrayList<>();
        ArrayList<ClaimCaseCalItem> claimCaseCalItems = new ArrayList<>();
        ClaimCaseCal claimCaseCal =claimCaseCalMapper.selectClaimCaseCalByRptNo(billDetailDTO.getBillDetailList().get(0).getRptNo());
        claimCaseCal.setRptNo(billDetailDTO.getBillDetailList().get(0).getRptNo());
        claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCal.setUpdateTime(DateUtils.getNowDate());
        BigDecimal pay = new BigDecimal(String.valueOf(0));
        if (StringUtils.isNotEmpty(billDetailDTO.getBillDetailList())) {
            for (CaseCalBillVo caseCalBillVo : billDetailDTO.getBillDetailList()) {
                ClaimCaseCalBill claimCaseCalBill = new ClaimCaseCalBill();
                claimCaseCalBill.setPayAmount(caseCalBillVo.getPayAmount());
                claimCaseCalBill.setRemark(caseCalBillVo.getRemark());
                claimCaseCalBill.setPayConclusion(caseCalBillVo.getPayConclusion());
                claimCaseCalBill.setCalBillId(caseCalBillVo.getCalBillId());
                claimCaseCalBill.setUpdateBy(SecurityUtils.getUsername());
                if ("05".equals(caseCalBillVo.getPayConclusion())){//存在拒赔结论时
                    flag=true;
                }else{
                    pay=pay.add(claimCaseCalBill.getPayAmount());
                }
                claimCaseCalBills.add(claimCaseCalBill);
                billTotalAmount=billTotalAmount.add(caseCalBillVo.getBillAmount());//因账单总金额暂未实现，只能求和
                totalDiscountAmount=totalDiscountAmount.add(caseCalBillVo.getHosDiscountAmount());//折扣总金额
                totalSelfAmount=(totalSelfAmount.add(caseCalBillVo.getCopay()));
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
        ClaimCase claimCase = claimCaseMapper.selectClaimCaseById(billDetailDTO.getBillDetailList().get(0).getRptNo());
        ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(claimCase.getBatchNo());
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        baseProviderSettle.setProviderCode(claimBatch.getHospitalcode());
        if (getProviderInfoService.selectsettleInfoNew(baseProviderSettle).size()>0) {
            BaseProviderSettle settle = getProviderInfoService.selectsettleInfoNew(baseProviderSettle).get(0);
            claimFlag=settle.getClaimFlag();
        }
        if ("01".equals(claimFlag)){//非全赔,如果是全赔，默认是账单总金额不变，且cal表账单总金额字段未加
            if (flag){//存在拒赔结论时，赔付金额为0，
                claimCaseCal.setCalAmount(new BigDecimal(String.valueOf(0.00)));
                claimCaseCal.setRefusedAmount(billTotalAmount.subtract(totalDiscountAmount).subtract(claimCaseCal.getCalAmount()));
            }
            claimCaseCal.setPayAmount(pay);
            claimCaseCal.setCalAmount(pay);
        }
        if ("02".equals(claimFlag)){//全赔医院
            claimCaseCal.setCalAmount(pay);
            claimCaseCal.setDebtAmount(billTotalAmount.subtract(totalDiscountAmount).subtract(totalSelfAmount).subtract(claimCaseCal.getCalAmount()));
        //此处并未真正实现，偷换概念，追讨金额=账单金额-折扣金额-赔付金额-流水号自付额；
            claimCaseCal.setPayAmount(billTotalAmount.subtract(totalDiscountAmount));
        }

        claimCaseCalMapper.updateClaimCaseCalByRptNo(claimCaseCal);
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
