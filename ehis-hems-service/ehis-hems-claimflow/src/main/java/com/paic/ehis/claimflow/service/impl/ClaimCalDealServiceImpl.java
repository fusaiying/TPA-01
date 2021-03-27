package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.mapper.*;
import com.paic.ehis.claimflow.service.IClaimCalDealService;
import com.paic.ehis.common.core.annotation.Excel;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.system.api.domain.*;
import com.paic.ehis.system.api.domain.ClaimCasePolicy;
import com.paic.ehis.system.api.domain.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClaimCalDealServiceImpl implements IClaimCalDealService {
    @Autowired
    private ClaimCaseCalMapper claimCaseCalMapper;

    @Autowired
    private ClaimCaseCalBillMapper claimCaseCalBillMapper;

    @Autowired
    private ClaimCaseCalItemMapper claimCaseCalItemMapper;

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    @Autowired
    private ClaimCaseInsuredMapper claimCaseInsuredMapper;

    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;

    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;

    @Autowired
    private ClaimCaseBillDetailMapper claimCaseBillDetailMapper;


    /**
     * 根据报案号清理理算计算数据
     * @param rptNo 报案号
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int clear(String rptNo) {
        ClaimCaseCal claimCaseCal = new ClaimCaseCal();
        claimCaseCal.setRptNo(rptNo);
        claimCaseCal.setStatus("N");
        claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCal.setUpdateTime(DateUtils.getNowDate());
        int i = claimCaseCalMapper.updateClaimCaseCalByRptNo(claimCaseCal);

        ClaimCaseCalBill claimCaseCalBill = new ClaimCaseCalBill();
        claimCaseCalBill.setRptNo(rptNo);
        claimCaseCalBill.setStatus("N");
        claimCaseCalBill.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCalBill.setUpdateTime(DateUtils.getNowDate());
        i = i + claimCaseCalBillMapper.updateClaimCaseCalBillByRptNo(claimCaseCalBill);

        ClaimCaseCalItem claimCaseCalItem = new ClaimCaseCalItem();
        claimCaseCalItem.setRptNo(rptNo);
        claimCaseCalItem.setStatus("N");
        claimCaseCalItem.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCalItem.setUpdateTime(DateUtils.getNowDate());
        i = i + claimCaseCalItemMapper.updateClaimCaseCalItemByRptNo(claimCaseCalItem);

        return i;
    }

    /**
     * 根据案件号查询账单信息
     * @param rptNo
     * @return
     */
    @Override
    public ClaimCaseCalculateInfo selectClaimCaseInfo(String rptNo) {
        ClaimCaseCalculateInfo claimCaseCalculateInfo = new ClaimCaseCalculateInfo();

        ClaimCase claimCase = claimCaseMapper.selectClaimCaseById(rptNo);
        BeanUtils.copyProperties(claimCase,claimCaseCalculateInfo);

        List<ClaimCaseInsured> insuredList = claimCaseInsuredMapper.selectCaseList(rptNo);
        if (insuredList.size()>0) {
            ClaimCaseInsured claimCaseInsured = insuredList.get(0);
            BeanUtils.copyProperties(claimCaseInsured,claimCaseCalculateInfo);
        }

        List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(rptNo);
        if (claimCasePolicies.size()>0) {
            ClaimCasePolicy claimCasePolicy = claimCasePolicies.get(0);
            BeanUtils.copyProperties(claimCasePolicy,claimCaseCalculateInfo);
        }

        List<ClaimCaseBillInfo> claimCaseBillInfoList = new ArrayList<>();
        ClaimCaseBill claimCaseBillQuery = new ClaimCaseBill();
        claimCaseBillQuery.setRptNo(rptNo);
        List<ClaimCaseBill> claimCaseBills = claimCaseBillMapper.selectClaimCaseBillList(claimCaseBillQuery);
        for (ClaimCaseBill claimCaseBill : claimCaseBills) {

            ClaimCaseBillInfo claimCaseBillInfo = new ClaimCaseBillInfo();
            BeanUtils.copyProperties(claimCaseBill,claimCaseBillInfo);


            List<ClaimCaseBillDetailInfo> claimCaseBillDetailInfoList = new ArrayList<>();

            List<ClaimCaseBillDetail> claimCaseBillDetails = claimCaseBillDetailMapper.selectClaimCaseBillDetailByBillId(claimCaseBill.getBillId());
            for (ClaimCaseBillDetail claimCaseBillDetail : claimCaseBillDetails) {
                ClaimCaseBillDetailInfo claimCaseBillDetailInfo = new ClaimCaseBillDetailInfo();
                BeanUtils.copyProperties(claimCaseBillDetail,claimCaseBillDetailInfo);

                claimCaseBillDetailInfoList.add(claimCaseBillDetailInfo);
            }
            claimCaseBillInfo.setClaimCaseBillDetailInfoList(claimCaseBillDetailInfoList);

            claimCaseBillInfoList.add(claimCaseBillInfo);
        }
        claimCaseCalculateInfo.setClaimCaseBillInfoList(claimCaseBillInfoList);

        return claimCaseCalculateInfo;
    }

    /**
     * 根据报案号查询案件保单信息
     * @param rptNo
     * @return
     */
    @Override
    public List<ClaimCasePolicyDTO> selectClaimCasePolicy(String rptNo) {
        List<ClaimCasePolicyDTO> claimCasePolicyDTOList = new ArrayList<ClaimCasePolicyDTO>();
        List<ClaimCasePolicy> claimCasePolicyList = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(rptNo);
        claimCasePolicyList.forEach(ccpL -> {
            ClaimCasePolicyDTO claimCasePolicyDTO = new ClaimCasePolicyDTO();

            claimCasePolicyDTO.setInsuredNo(ccpL.getInsuredNo());
            claimCasePolicyDTO.setRptNo(ccpL.getRptNo());
            claimCasePolicyDTO.setPolicyNo(ccpL.getPolicyNo());
            claimCasePolicyDTO.setPolicyItemNo(ccpL.getPolicyItemNo());
            claimCasePolicyDTO.setValidStartDate(ccpL.getValidStartDate());
            claimCasePolicyDTO.setValidEndDate(ccpL.getValidEndDate());
            claimCasePolicyDTO.setPolicyRiskType(ccpL.getPolicyRiskType());
            claimCasePolicyDTO.setPolicyStatus(ccpL.getPolicyStatus());
            claimCasePolicyDTO.setPolicyType(ccpL.getPolicyType());
            claimCasePolicyDTO.setPlanCode(ccpL.getPlanCode());
            claimCasePolicyDTO.setSsFlag(ccpL.getSsFlag());

            claimCasePolicyDTOList.add(claimCasePolicyDTO);
        });

        return claimCasePolicyDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int save(ClaimCaseCalInfo claimCaseCalInfo) {
        ClaimCaseCalDTO claimCaseCalDTO = claimCaseCalInfo.getClaimCaseCalInfo();
        List<ClaimCaseCalBillDTO> claimCaseCalBillDTOList = claimCaseCalInfo.getClaimCaseCalBillList();
        List<ClaimCaseCalItemDTO> claimCaseCalItemDTOList = claimCaseCalInfo.getClaimCaseCalItemList();

        ClaimCaseCal claimCaseCal = new ClaimCaseCal();
        claimCaseCal.setRptNo(claimCaseCalDTO.getRptNo());
        claimCaseCal.setCalAmount(claimCaseCalDTO.getCalAmount());
        claimCaseCal.setBillCurrency(claimCaseCalDTO.getBillCurrency());
        claimCaseCal.setPayAmount(claimCaseCalDTO.getPayAmount());
        claimCaseCal.setRefusedAmount(claimCaseCalDTO.getRefusedAmount());
        claimCaseCal.setDebtAmount(claimCaseCalDTO.getDebtAmount());
        claimCaseCal.setExchangeRate(claimCaseCalDTO.getExchangeRate());
        claimCaseCal.setPayAmountForeign(claimCaseCalDTO.getPayAmountForeign());
        claimCaseCal.setStatus("Y");
        claimCaseCal.setCreateBy(SecurityUtils.getUsername());
        claimCaseCal.setCreateTime(DateUtils.getNowDate());
        claimCaseCal.setUpdateBy(SecurityUtils.getUsername());
        claimCaseCal.setUpdateTime(DateUtils.getNowDate());
        int i = claimCaseCalMapper.insertClaimCaseCal(claimCaseCal);

        for (ClaimCaseCalBillDTO claimCaseCalBillDTO : claimCaseCalBillDTOList) {
            ClaimCaseCalBill claimCaseCalBill = new ClaimCaseCalBill();
            claimCaseCalBill.setRptNo(claimCaseCalBillDTO.getRptNo());
            claimCaseCalBill.setBillId(claimCaseCalBillDTO.getBillId());
            claimCaseCalBill.setCalAmount(claimCaseCalBillDTO.getCalAmount());
            claimCaseCalBill.setPayAmount(claimCaseCalBillDTO.getPayAmount());
            claimCaseCalBill.setRefusedAmount(claimCaseCalBillDTO.getRefusedAmount());
            claimCaseCalBill.setDeduUsed(claimCaseCalBillDTO.getDeduUsed());
            claimCaseCalBill.setStatus("Y");
            claimCaseCalBill.setCreateBy(SecurityUtils.getUsername());
            claimCaseCalBill.setCreateTime(DateUtils.getNowDate());
            claimCaseCalBill.setUpdateBy(SecurityUtils.getUsername());
            claimCaseCalBill.setUpdateTime(DateUtils.getNowDate());

            i = i + claimCaseCalBillMapper.insertClaimCaseCalBill(claimCaseCalBill);
        }

        for (ClaimCaseCalItemDTO claimCaseCalItemDTO : claimCaseCalItemDTOList) {
            ClaimCaseCalItem claimCaseCalItem = new ClaimCaseCalItem();
            claimCaseCalItem.setRptNo(claimCaseCalItemDTO.getRptNo());
            claimCaseCalItem.setBillDetailId(claimCaseCalItemDTO.getBillDetailId());
            claimCaseCalItem.setBillId(claimCaseCalItemDTO.getBillId());
            claimCaseCalItem.setPolicyNo(claimCaseCalItemDTO.getPolicyNo());
            claimCaseCalItem.setPolicyItemNo(claimCaseCalItemDTO.getPolicyItemNo());
            claimCaseCalItem.setRiskCode(claimCaseCalItemDTO.getRiskCode());
            claimCaseCalItem.setPlanCode(claimCaseCalItemDTO.getPlanCode());
            claimCaseCalItem.setDutyDetailCode(claimCaseCalItemDTO.getDutyDetailCode());
            claimCaseCalItem.setDutyCode(claimCaseCalItemDTO.getDutyCode());
            claimCaseCalItem.setFeeItemCode(claimCaseCalItemDTO.getFeeItemCode());
            claimCaseCalItem.setCalAmount(claimCaseCalItemDTO.getCalAmount());
            claimCaseCalItem.setRefusedAmount(claimCaseCalItemDTO.getRefusedAmount());
            claimCaseCalItem.setDeduUsed(claimCaseCalItemDTO.getDeduUsed());
            claimCaseCalItem.setPayRate(claimCaseCalItemDTO.getPayRate());
            claimCaseCalItem.setStatus("Y");
            claimCaseCalItem.setCreateBy(SecurityUtils.getUsername());
            claimCaseCalItem.setCreateTime(DateUtils.getNowDate());
            claimCaseCalItem.setUpdateBy(SecurityUtils.getUsername());
            claimCaseCalItem.setUpdateTime(DateUtils.getNowDate());

            i = i + claimCaseCalItemMapper.insertClaimCaseCalItem(claimCaseCalItem);
        }

        return i;
    }

    @Override
    public List<ClaimCaseCalItemDTO> getCaInfo(ClaimCaseCalItemDTO claimCaseCalItemDTO) {

        List<ClaimCaseCalItemDTO> claimCaseCalItemDTOList = new ArrayList<>();
        ClaimCaseCalItem claimCaseCalItem = new ClaimCaseCalItem();
        BeanUtils.copyProperties(claimCaseCalItemDTO,claimCaseCalItem);
        List<ClaimCaseCalItem> claimCaseCalItems = claimCaseCalItemMapper.selectClaimCaseCalItemList(claimCaseCalItem);
        for (ClaimCaseCalItem caseCalItem : claimCaseCalItems) {
            ClaimCaseCalItemDTO calItemDTO = new ClaimCaseCalItemDTO();
            BeanUtils.copyProperties(caseCalItem,calItemDTO);
            claimCaseCalItemDTOList.add(calItemDTO);
        }
        return claimCaseCalItemDTOList;
    }


}
