package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.ClaimDebtWhitelist;

import java.util.List;

/**
 * 案件追讨白名单Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimDebtWhitelistMapper 
{
    /**
     * 查询案件追讨白名单
     * 
     * @param debtWhitelistId 案件追讨白名单ID
     * @return 案件追讨白名单
     */
    public ClaimDebtWhitelist selectClaimDebtWhitelistById(Long debtWhitelistId);

    /**
     * 查询案件追讨白名单列表
     * 
     * @param claimDebtWhitelist 案件追讨白名单
     * @return 案件追讨白名单集合
     */
    public List<ClaimDebtWhitelist> selectClaimDebtWhitelistList(ClaimDebtWhitelist claimDebtWhitelist);

    /**
     * 白名单维护界面初始化或未录入任何查询条件，点击查询按钮时，默认查询状态不为失效，追缴通知为是的白名单信息
     */
    public List<ClaimDebtWhitelist> selectClaimDebtWhitelistList1(ClaimDebtWhitelist claimDebtWhitelist);

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
     * 删除案件追讨白名单
     * 
     * @param debtWhitelistId 案件追讨白名单ID
     * @return 结果
     */
    public int deleteClaimDebtWhitelistById(Long debtWhitelistId);

    /**
     * 批量删除案件追讨白名单
     * 
     * @param debtWhitelistIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimDebtWhitelistByIds(Long[] debtWhitelistIds);

    /**
     *根据报案号查询欠款
     */
    public ClaimDebtWhitelist selectResidualList(String rptNo);
}
