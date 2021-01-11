package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.service.IBaseContractServiceService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseContractService;
import com.paic.ehis.base.mapper.BaseContractServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base_contract_service（合约服务项目）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-31
 */
@Service
public class BaseContractServiceServiceImpl implements IBaseContractServiceService
{
    @Autowired
    private BaseContractServiceMapper baseContractServiceMapper;

    /**
     * 查询base_contract_service（合约服务项目）
     * 
     * @param contractNo base_contract_service（合约服务项目）ID
     * @return base_contract_service（合约服务项目）
     */
    @Override
    public BaseContractService selectBaseContractServiceById(String contractNo)
    {
        return baseContractServiceMapper.selectBaseContractServiceById(contractNo);
    }

    /**
     * 查询base_contract_service（合约服务项目）列表
     * 
     * @param baseContractService base_contract_service（合约服务项目）
     * @return base_contract_service（合约服务项目）
     */
    @Override
    public List<BaseContractService> selectBaseContractServiceList(BaseContractService baseContractService)
    {
        return baseContractServiceMapper.selectBaseContractServiceList(baseContractService);
    }

    /**
     * 新增base_contract_service（合约服务项目）
     * 
     * @param baseContractService base_contract_service（合约服务项目）
     * @return 结果
     */
    @Override
    public int insertBaseContractService(BaseContractService baseContractService)
    {
        baseContractService.setCreateTime(DateUtils.getNowDate());
        return baseContractServiceMapper.insertBaseContractService(baseContractService);
    }

    /**
     * 修改base_contract_service（合约服务项目）
     * 
     * @param baseContractService base_contract_service（合约服务项目）
     * @return 结果
     */
    @Override
    public int updateBaseContractService(BaseContractService baseContractService)
    {
        baseContractService.setUpdateTime(DateUtils.getNowDate());
        return baseContractServiceMapper.updateBaseContractService(baseContractService);
    }

    /**
     * 批量删除base_contract_service（合约服务项目）
     * 
     * @param contractNos 需要删除的base_contract_service（合约服务项目）ID
     * @return 结果
     */
    @Override
    public int deleteBaseContractServiceByIds(String[] contractNos)
    {
        return baseContractServiceMapper.deleteBaseContractServiceByIds(contractNos);
    }

    /**
     * 删除base_contract_service（合约服务项目）信息
     * 
     * @param contractNo base_contract_service（合约服务项目）ID
     * @return 结果
     */
    @Override
    public int deleteBaseContractServiceById(String contractNo)
    {
        return baseContractServiceMapper.deleteBaseContractServiceById(contractNo);
    }
}
