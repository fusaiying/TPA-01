package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.order.domain.ServiceBalanceFileInfo;
import com.paic.ehis.order.mapper.ServiceBalanceFileInfoMapper;
import com.paic.ehis.order.service.IServiceBalanceFileInfoService;
import com.paic.ehis.order.service.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service_balance_file_info（服务结算文件信息）Service业务层处理
 * 
 * @author sino
 * @date 2021-02-07
 */
@Service
public class ServiceBalanceFileInfoServiceImpl implements IServiceBalanceFileInfoService
{
    @Autowired
    private ServiceBalanceFileInfoMapper serviceBalanceFileInfoMapper;

    /**
     * 查询service_balance_file_info（服务结算文件信息）
     * 
     * @param serialNo service_balance_file_info（服务结算文件信息）ID
     * @return service_balance_file_info（服务结算文件信息）
     */
    @Override
    public ServiceBalanceFileInfo selectServiceBalanceFileInfoById(Long serialNo)
    {
        return serviceBalanceFileInfoMapper.selectServiceBalanceFileInfoById(serialNo);
    }

    /**
     * 查询service_balance_file_info（服务结算文件信息）列表
     * 
     * @param serviceBalanceFileInfo service_balance_file_info（服务结算文件信息）
     * @return service_balance_file_info（服务结算文件信息）
     */
    @Override
    public List<ServiceBalanceFileInfo> selectServiceBalanceFileInfoList(ServiceBalanceFileInfo serviceBalanceFileInfo)
    {
        return serviceBalanceFileInfoMapper.selectServiceBalanceFileInfoList(serviceBalanceFileInfo);
    }

    /**
     * 新增service_balance_file_info（服务结算文件信息）
     * 
     * @param serviceBalanceFileInfo service_balance_file_info（服务结算文件信息）
     * @return 结果
     */
    @Override
    public int insertServiceBalanceFileInfo(ServiceBalanceFileInfo serviceBalanceFileInfo)
    {
        serviceBalanceFileInfo.setCreateTime(DateUtils.getNowDate());
        serviceBalanceFileInfo.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceFileInfo.setCreateBy(SecurityUtils.getUsername());
        serviceBalanceFileInfo.setUpdateBy(SecurityUtils.getUsername());
        serviceBalanceFileInfo.setStatus(StatusEnum.VALID.getCode());
        return serviceBalanceFileInfoMapper.insertServiceBalanceFileInfo(serviceBalanceFileInfo);
    }

    /**
     * 修改service_balance_file_info（服务结算文件信息）
     * 
     * @param serviceBalanceFileInfo service_balance_file_info（服务结算文件信息）
     * @return 结果
     */
    @Override
    public int updateServiceBalanceFileInfo(ServiceBalanceFileInfo serviceBalanceFileInfo)
    {
        serviceBalanceFileInfo.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceFileInfo.setUpdateBy(SecurityUtils.getUsername());
        return serviceBalanceFileInfoMapper.updateServiceBalanceFileInfo(serviceBalanceFileInfo);
    }

    /**
     * 批量删除service_balance_file_info（服务结算文件信息）
     * 
     * @param serialNos 需要删除的service_balance_file_info（服务结算文件信息）ID
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceFileInfoByIds(Long[] serialNos)
    {
        return serviceBalanceFileInfoMapper.deleteServiceBalanceFileInfoByIds(serialNos);
    }

    /**
     * 删除service_balance_file_info（服务结算文件信息）信息
     * 
     * @param serialNo service_balance_file_info（服务结算文件信息）ID
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceFileInfoById(Long serialNo)
    {
        return serviceBalanceFileInfoMapper.deleteServiceBalanceFileInfoById(serialNo);
    }
}
