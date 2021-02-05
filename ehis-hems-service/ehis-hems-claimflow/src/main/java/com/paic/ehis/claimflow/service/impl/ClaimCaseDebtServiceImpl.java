package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimCaseDebt;
import com.paic.ehis.claimflow.domain.ClaimCasePolicy;
import com.paic.ehis.claimflow.domain.dto.DebtInfoDTO;
import com.paic.ehis.claimflow.domain.vo.DebtInfoVO;
import com.paic.ehis.claimflow.mapper.ClaimCaseDebtMapper;
import com.paic.ehis.claimflow.mapper.ClaimCasePolicyMapper;
import com.paic.ehis.claimflow.service.IClaimCaseDebtService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 案件追讨信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseDebtServiceImpl implements IClaimCaseDebtService
{
    @Autowired
    private ClaimCaseDebtMapper claimCaseDebtMapper;
    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;

    /**
     * 查询案件追讨信息
     * 
     * @param debtId 案件追讨信息ID
     * @return 案件追讨信息
     */
    @Override
    public ClaimCaseDebt selectClaimCaseDebtById(Long debtId)
    {
        return claimCaseDebtMapper.selectClaimCaseDebtById(debtId);
    }

    /**
     * 查询案件追讨信息列表
     * 
     * @param claimCaseDebt 案件追讨信息
     * @return 案件追讨信息
     */
    @Override
    public List<ClaimCaseDebt> selectClaimCaseDebtList(ClaimCaseDebt claimCaseDebt)
    {
        return claimCaseDebtMapper.selectClaimCaseDebtList(claimCaseDebt);
    }

    /**
     * 新增案件追讨信息
     * 
     * @param claimCaseDebt 案件追讨信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseDebt(ClaimCaseDebt claimCaseDebt)
    {
        /*BigDecimal bigDecimal = new BigDecimal(0);
        if (reviewCompletedDTO.getDebtAmount().compareTo(bigDecimal) > -1){}*/
        claimCaseDebt.setStatus("Y");
        claimCaseDebt.setCreateBy(SecurityUtils.getUsername());
        claimCaseDebt.setCreateTime(DateUtils.getNowDate());
        claimCaseDebt.setCreateTime(DateUtils.getNowDate());
        return claimCaseDebtMapper.insertClaimCaseDebt(claimCaseDebt);
    }

    /**
     * 修改案件追讨信息
     * 
     * @param claimCaseDebt 案件追讨信息
     * @return 结果
     */
    @Override
    public int updateClaimCaseDebt(ClaimCaseDebt claimCaseDebt)
    {
        claimCaseDebt.setUpdateTime(DateUtils.getNowDate());
        return claimCaseDebtMapper.updateClaimCaseDebt(claimCaseDebt);
    }

    /**
     * 批量删除案件追讨信息
     * 
     * @param debtIds 需要删除的案件追讨信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDebtByIds(Long[] debtIds)
    {
        return claimCaseDebtMapper.deleteClaimCaseDebtByIds(debtIds);
    }

    /**
     * 删除案件追讨信息信息
     * 
     * @param debtId 案件追讨信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDebtById(Long debtId)
    {
        return claimCaseDebtMapper.deleteClaimCaseDebtById(debtId);
    }

    /**
     * 查询追讨工作池 - 初始化
     *
     * @return
     */
    @Override
    public List<DebtInfoVO> selectDebtInitList() {
        List<DebtInfoVO> debtInfoList = claimCaseDebtMapper.selectDebtInitList();
        for (DebtInfoVO debtInfo : debtInfoList){
            BigDecimal a = new BigDecimal("0");
            // 剩余欠款大于0
            if (null != debtInfo.getResidualAmount() && a.compareTo(debtInfo.getResidualAmount()) == -1){
                ClaimCasePolicy policy = new ClaimCasePolicy();
                policy.setRptNo(debtInfo.getRptNo());
                policy.setStatus("Y");
                List<ClaimCasePolicy> policyList = claimCasePolicyMapper.selectClaimCasePolicyList(policy);
                // 投保人姓名去重
                List<String> appntList = policyList.stream().map(ClaimCasePolicy::getAppName).collect(Collectors.toList());
                LinkedHashSet<String> hashSetAppnt = new LinkedHashSet<>(appntList);
                ArrayList<String> appntWithoutDup = new ArrayList<>(hashSetAppnt);
                // 投保人姓名拼接
                String appntName = StringUtils.join(appntWithoutDup, "|");
                debtInfo.setAppntName(appntName);
                // 保单号拼接
                List<String> policyNoList = policyList.stream().map(ClaimCasePolicy::getPolicyNo).collect(Collectors.toList());
                LinkedHashSet<String> hashSetPolicyNo = new LinkedHashSet<>(policyNoList);
                ArrayList<String> policyNoWithoutDup = new ArrayList<>(hashSetPolicyNo);
                String contNo = StringUtils.join(policyNoWithoutDup, "|");
                debtInfo.setContNo(contNo);
            } else {
                // 剩余欠款小于0 移除
                debtInfoList.remove(debtInfo);
            }
        }
        return debtInfoList;
    }

    /**
     * 查询追讨信息
     *
     * @param debtInfoDTO
     * @return
     */
    @Override
    public List<DebtInfoVO> selectDebtList(DebtInfoDTO debtInfoDTO) {
        List<DebtInfoVO> debtInfoVOList = claimCaseDebtMapper.selectDebtList(debtInfoDTO);
        for (DebtInfoVO debtInfo : debtInfoVOList){
            ClaimCasePolicy policy = new ClaimCasePolicy();
            policy.setRptNo(debtInfo.getRptNo());
            policy.setStatus("Y");
            List<ClaimCasePolicy> policyList = claimCasePolicyMapper.selectClaimCasePolicyList(policy);
            // 投保人姓名去重
            List<String> appntList = policyList.stream().map(ClaimCasePolicy::getAppName).collect(Collectors.toList());
            LinkedHashSet<String> hashSetAppnt = new LinkedHashSet<>(appntList);
            ArrayList<String> appntWithoutDup = new ArrayList<>(hashSetAppnt);
            // 投保人姓名拼接
            String appntName = StringUtils.join(appntWithoutDup, "|");
            debtInfo.setAppntName(appntName);
            // 保单号拼接
            List<String> policyNoList = policyList.stream().map(ClaimCasePolicy::getPolicyNo).collect(Collectors.toList());
            LinkedHashSet<String> hashSetPolicyNo = new LinkedHashSet<>(policyNoList);
            ArrayList<String> policyNoWithoutDup = new ArrayList<>(hashSetPolicyNo);
            String contNo = StringUtils.join(policyNoWithoutDup, "|");
            debtInfo.setContNo(contNo);
        }
        return debtInfoVOList;
    }
}
