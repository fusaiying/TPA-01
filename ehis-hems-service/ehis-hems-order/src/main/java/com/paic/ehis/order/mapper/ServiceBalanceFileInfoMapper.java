package com.paic.ehis.order.mapper;


import com.paic.ehis.order.domain.ServiceBalanceFileInfo;

import java.util.List;

/**
 * service_balance_file_info（服务结算文件信息）Mapper接口
 * 
 * @author sino
 * @date 2021-02-07
 */
public interface ServiceBalanceFileInfoMapper 
{
    /**
     * 查询service_balance_file_info（服务结算文件信息）
     * 
     * @param serialNo service_balance_file_info（服务结算文件信息）ID
     * @return service_balance_file_info（服务结算文件信息）
     */
    public ServiceBalanceFileInfo selectServiceBalanceFileInfoById(Long serialNo);

    /**
     * 查询service_balance_file_info（服务结算文件信息）列表
     * 
     * @param serviceBalanceFileInfo service_balance_file_info（服务结算文件信息）
     * @return service_balance_file_info（服务结算文件信息）集合
     */
    public List<ServiceBalanceFileInfo> selectServiceBalanceFileInfoList(ServiceBalanceFileInfo serviceBalanceFileInfo);

    /**
     * 新增service_balance_file_info（服务结算文件信息）
     * 
     * @param serviceBalanceFileInfo service_balance_file_info（服务结算文件信息）
     * @return 结果
     */
    public int insertServiceBalanceFileInfo(ServiceBalanceFileInfo serviceBalanceFileInfo);

    /**
     * 修改service_balance_file_info（服务结算文件信息）
     * 
     * @param serviceBalanceFileInfo service_balance_file_info（服务结算文件信息）
     * @return 结果
     */
    public int updateServiceBalanceFileInfo(ServiceBalanceFileInfo serviceBalanceFileInfo);

    /**
     * 删除service_balance_file_info（服务结算文件信息）
     * 
     * @param serialNo service_balance_file_info（服务结算文件信息）ID
     * @return 结果
     */
    public int deleteServiceBalanceFileInfoById(Long serialNo);

    /**
     * 批量删除service_balance_file_info（服务结算文件信息）
     * 
     * @param serialNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteServiceBalanceFileInfoByIds(Long[] serialNos);
}
