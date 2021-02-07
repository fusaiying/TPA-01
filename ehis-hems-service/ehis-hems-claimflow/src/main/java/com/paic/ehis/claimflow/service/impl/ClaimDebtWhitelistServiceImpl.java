package com.paic.ehis.claimflow.service.impl;

import com.paic.ehis.claimflow.domain.ClaimDebtWhitelist;
import com.paic.ehis.claimflow.mapper.ClaimDebtWhitelistMapper;
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


    /**
     * 查询案件追讨白名单
     * 
     * @param debtWhitelistId 案件追讨白名单ID
     * @return 案件追讨白名单
     */
    @Override
    public ClaimDebtWhitelist selectClaimDebtWhitelistById(Long debtWhitelistId)
    {
        return claimDebtWhitelistMapper.selectClaimDebtWhitelistById(debtWhitelistId);
    }

    /**
     * 查询案件追讨白名单列表
     * 
     * @param claimDebtWhitelist 案件追讨白名单
     * @return 案件追讨白名单
     */
    @Override
    public List<ClaimDebtWhitelist> selectClaimDebtWhitelistList(ClaimDebtWhitelist claimDebtWhitelist)
    {
        return claimDebtWhitelistMapper.selectClaimDebtWhitelistList(claimDebtWhitelist);
    }

    /**
     * 白名单维护界面初始化或未录入任何查询条件，点击查询按钮时，默认查询状态不为失效，追缴通知为是的白名单信息
     */
    @Override
    public List<ClaimDebtWhitelist> selectClaimDebtWhitelistList1(ClaimDebtWhitelist claimDebtWhitelist)
    {
        return claimDebtWhitelistMapper.selectClaimDebtWhitelistList1(claimDebtWhitelist);
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
