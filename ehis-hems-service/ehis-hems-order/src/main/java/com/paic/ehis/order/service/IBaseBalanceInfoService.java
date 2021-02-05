package com.paic.ehis.order.service;

import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.order.domain.BaseBalanceInfo;

import java.util.List;

/**
 * base_balance_info(服务结算基本信息)Service接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface IBaseBalanceInfoService 
{
    /**
     * 查询base_balance_info(服务结算基本信息)
     * 
     * @param serialNo base_balance_info(服务结算基本信息)ID
     * @return base_balance_info(服务结算基本信息)
     */
    public BaseBalanceInfo selectBaseBalanceInfoById(Long serialNo);

    /**
     * 查询base_balance_info(服务结算基本信息)列表
     * 
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return base_balance_info(服务结算基本信息)集合
     */
    public List<BaseBalanceInfo> selectBaseBalanceInfoList(BaseBalanceInfo baseBalanceInfo);

    /**
     * 新增base_balance_info(服务结算基本信息)
     * 
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return 结果
     */
    public int insertBaseBalanceInfo(BaseBalanceInfo baseBalanceInfo);

    /**
     * 修改base_balance_info(服务结算基本信息)
     * 
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return 结果
     */
    public int updateBaseBalanceInfo(BaseBalanceInfo baseBalanceInfo);

    /**
     * 批量删除base_balance_info(服务结算基本信息)
     * 
     * @param taskNos 需要删除的base_balance_info(服务结算基本信息)taskNo
     * @return 结果
     */
    public int deleteBaseBalanceInfoByIds(Long[] taskNos);

    /**
     * 删除base_balance_info(服务结算基本信息)信息
     * 
     * @param taskNo base_balance_info(服务结算基本信息)taskNo
     * @return 结果
     */
    public int deleteBaseBalanceInfoById(Long taskNo);

    /**
     * 查询base_balance_info(服务结算基本信息)列表
     *
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return base_balance_info(服务结算基本信息)集合
     */
    public List<BaseBalanceInfo> selectBaseBalanceInfoList2(BaseBalanceInfo baseBalanceInfo);

    /**
     * 更新base_balance_info(服务结算基本信息)信息
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return 结果
     */
    public AjaxResult updateBaseBalanceInfoByTaskNo(BaseBalanceInfo baseBalanceInfo);
}
