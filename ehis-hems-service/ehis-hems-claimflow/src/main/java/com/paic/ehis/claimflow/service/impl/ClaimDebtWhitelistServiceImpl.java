package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimDebtWhitelist;
import com.paic.ehis.claimflow.domain.PolicyInsured;
import com.paic.ehis.claimflow.mapper.ClaimDebtWhitelistMapper;
import com.paic.ehis.claimflow.mapper.PolicyInsuredMapper;
import com.paic.ehis.claimflow.service.IClaimDebtWhitelistService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * 案件追讨白名单Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimDebtWhitelistServiceImpl implements IClaimDebtWhitelistService
{
    @Autowired
    private ClaimDebtWhitelistMapper claimDebtWhitelistMapper;

    @Autowired
    PolicyInsuredMapper policyInsuredMapper;

    /**
     * 查询案件追讨白名单列表
     * 
     * @param claimDebtWhitelist 案件追讨白名单
     * @return 案件追讨白名单
     */
    @Override
    public List<ClaimDebtWhitelist> selectClaimDebtWhitelistList(ClaimDebtWhitelist claimDebtWhitelist)
    {

        List<ClaimDebtWhitelist> claimDebtWhitelists=claimDebtWhitelistMapper.selectClaimDebtWhitelistList(claimDebtWhitelist);
        for(ClaimDebtWhitelist claimDebtWhitelist1:claimDebtWhitelists) {
            String insuredNo = claimDebtWhitelist1.getInsuredNo();
            PolicyInsured policyInsured = new PolicyInsured();
            policyInsured.setInsuredNo(insuredNo);
            List<PolicyInsured> policyInsured1 = policyInsuredMapper.selectRecognizee(policyInsured);
                for (PolicyInsured policyInfo : policyInsured1) {
                        claimDebtWhitelist1.setName(policyInfo.getName());
                        claimDebtWhitelist1.setIdType(policyInfo.getIdType());
                        claimDebtWhitelist1.setIdNo(policyInfo.getIdNo());
                        claimDebtWhitelist1.setSex(policyInfo.getSex());
                        claimDebtWhitelist1.setBirthday(policyInfo.getBirthday());
            }
        }
        return claimDebtWhitelists;
    }

    /**
     * 新增/修改案件追讨白名单
     * 
     * @param claimDebtWhitelist 案件追讨白名单
     * @return 结果
     */
    @Override
    public int insertClaimDebtWhitelist(ClaimDebtWhitelist claimDebtWhitelist)
    {
        Date nowDate = new Date();
        String username = SecurityUtils.getUsername();
        claimDebtWhitelist.setUpdateBy(username);
        claimDebtWhitelist.setUpdateTime(nowDate);
        int i;
        if(null == claimDebtWhitelist.getDebtWhitelistId()){
            claimDebtWhitelist.setCreateBy(username);
            claimDebtWhitelist.setCreateTime(nowDate);
            claimDebtWhitelist.setStatus("Y");
            i=claimDebtWhitelistMapper.insertClaimDebtWhitelist(claimDebtWhitelist);
        }else {
            i=claimDebtWhitelistMapper.updateClaimDebtWhitelist(claimDebtWhitelist);
        }
        return i;
    }

    /**
     * 修改案件追讨白名单
     * 
     * @param claimDebtWhitelist 案件追讨白名单
     * @return 结果
     */
    @Override
    public int updateClaimDebtWhitelist(ClaimDebtWhitelist claimDebtWhitelist)
    {
        claimDebtWhitelist.setUpdateTime(DateUtils.getNowDate());
        return claimDebtWhitelistMapper.updateClaimDebtWhitelist(claimDebtWhitelist);
    }

    /**
     * 批量删除案件追讨白名单
     * 
     * @param debtWhitelistIds 需要删除的案件追讨白名单ID
     * @return 结果
     */
    @Override
    public int deleteClaimDebtWhitelistByIds(Long[] debtWhitelistIds)
    {
        return claimDebtWhitelistMapper.deleteClaimDebtWhitelistByIds(debtWhitelistIds);
    }

    /**
     * 删除案件追讨白名单信息
     * 
     * @param debtWhitelistId 案件追讨白名单ID
     * @return 结果
     */
    @Override
    public int deleteClaimDebtWhitelistById(Long debtWhitelistId)
    {
        return claimDebtWhitelistMapper.deleteClaimDebtWhitelistById(debtWhitelistId);
    }

    /**
     *根据报案号查询欠款
     */
    @Override
    public ClaimDebtWhitelist selectResidualList(String rptNo) {
        return claimDebtWhitelistMapper.selectResidualList(rptNo);
    }
}
