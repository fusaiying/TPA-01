package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.ClaimDebtWhitelist;

import java.util.List;

/**
 * 案件追讨白名单Service接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface IClaimDebtWhitelistService 
{
    /**
     * 查询案件追讨白名单列表
     *
     * @param claimDebtWhitelist 案件追讨白名单
     * @return 案件追讨白名单集合
     */
    public List<ClaimDebtWhitelist> selectClaimDebtWhitelistList(ClaimDebtWhitelist claimDebtWhitelist);

    /**
     * 新增案件追讨白名单
     * 
     * @param claimDebtWhitelist 案件追讨白名单
     * @return 结果
     */
    public int insertClaimDebtWhitelist(ClaimDebtWhitelist claimDebtWhitelist);

    /**
     * 修改案件追讨白名单
     * 
     * @param claimDebtWhitelist 案件追讨白名单
     * @return 结果
     */
    public int updateClaimDebtWhitelist(ClaimDebtWhitelist claimDebtWhitelist);

    /**
     * 批量删除案件追讨白名单
     * 
     * @param debtWhitelistIds 需要删除的案件追讨白名单ID
     * @return 结果
     */
    public int deleteClaimDebtWhitelistByIds(Long[] debtWhitelistIds);

    /**
     * 删除案件追讨白名单信息
     * 
     * @param debtWhitelistId 案件追讨白名单ID
     * @return 结果
     */
    public int deleteClaimDebtWhitelistById(Long debtWhitelistId);

    /**
     *根据被保人客户号查询欠款
     */
    public ClaimDebtWhitelist  selectResidualList(String insuredNo);

}
