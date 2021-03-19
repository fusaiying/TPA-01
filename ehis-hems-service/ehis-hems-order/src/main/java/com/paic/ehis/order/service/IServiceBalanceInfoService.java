package com.paic.ehis.order.service;


import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.order.domain.ServiceBalanceInfo;

import java.util.List;

/**
 * service_balance_info(服务结算基本信息)Service接口
 * 
 * @author chenhaiming
 * @date 2021-01-20
 */
public interface IServiceBalanceInfoService 
{
    /**
     * 查询service_balance_info(服务结算基本信息)
     * 
     * @param serialNo service_balance_info(服务结算基本信息)ID
     * @return service_balance_info(服务结算基本信息)
     */
    public ServiceBalanceInfo selectServiceBalanceInfoById(Long serialNo);

    /**
     * 查询service_balance_info(服务结算基本信息)列表
     * 
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_info(服务结算基本信息)集合
     */
    public List<ServiceBalanceInfo> selectServiceBalanceInfoList(ServiceBalanceInfo serviceBalanceInfo);

    /**
     * 新增service_balance_info(服务结算基本信息)
     * 
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return 结果
     */
    public int insertServiceBalanceInfo(ServiceBalanceInfo serviceBalanceInfo);

    /**
     * 修改service_balance_info(服务结算基本信息)
     * 
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return 结果
     */
    public int updateServiceBalanceInfo(ServiceBalanceInfo serviceBalanceInfo);

    /**
     * 批量删除service_balance_info(服务结算基本信息)
     * 
     * @param serialNos 需要删除的service_balance_info(服务结算基本信息)serialNo
     * @return 结果
     */
    public int deleteServiceBalanceInfoByIds(Long[] serialNos);

    /**
     * 删除service_balance_info(服务结算基本信息)信息
     * 
     * @param serialNo service_balance_info(服务结算基本信息)serialNo
     * @return 结果
     */
    public int deleteServiceBalanceInfoById(Long serialNo);

    /**
     * 查询service_balance_info(服务结算基本信息)
     *
     * @param taskNo service_balance_info(服务结算基本信息)taskNo
     * @return service_balance_info(服务结算基本信息)
     */
    public ServiceBalanceInfo selectServiceBalanceInfoByTaskNo(String taskNo);

    /**
     * 查询service_balance_info(服务结算基本信息)列表
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_info(服务结算基本信息)集合
     */
    public List<ServiceBalanceInfo> selectServiceBalanceInfoList2(ServiceBalanceInfo serviceBalanceInfo);

    /**
     * 查询service_balance_info(服务结算基本信息)列表 默认
     * @returnservice_balance_info(服务结算基本信息)集合
     */
    public List<ServiceBalanceInfo> selectServiceBalanceInfoList2Default();

    /**
     * 更新service_balance_info(服务结算基本信息)信息
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return 结果
     */
    public AjaxResult updateServiceBalanceInfoByTaskNo(ServiceBalanceInfo serviceBalanceInfo);
}
