package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseContractService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
/**
 * base_contract_service（合约服务项目）Mapper接口
 * 
 * @author sino
 * @date 2020-12-31
 */
public interface BaseContractServiceMapper 
{
    /**
     * 查询base_contract_service（合约服务项目）
     * 
     * @param contractNo base_contract_service（合约服务项目）ID
     * @return base_contract_service（合约服务项目）
     */
    public BaseContractService selectBaseContractServiceById(String contractNo);

    /**
     * 查询base_contract_service（合约服务项目）列表
     * 
     * @param baseContractService base_contract_service（合约服务项目）
     * @return base_contract_service（合约服务项目）集合
     */
    public List<BaseContractService> selectBaseContractServiceList(BaseContractService baseContractService);

    /**
     * 新增base_contract_service（合约服务项目）
     * 
     * @param baseContractService base_contract_service（合约服务项目）
     * @return 结果
     */
    public int insertBaseContractService(BaseContractService baseContractService);

    /**
     * 修改base_contract_service（合约服务项目）
     * 
     * @param baseContractService base_contract_service（合约服务项目）
     * @return 结果
     */
    public int updateBaseContractService(BaseContractService baseContractService);

    /**
     * 删除base_contract_service（合约服务项目）
     * 
     * @param contractNo base_contract_service（合约服务项目）ID
     * @return 结果
     */
    public int deleteBaseContractServiceById(String contractNo);

    /**
     * 批量删除base_contract_service（合约服务项目）
     * 
     * @param contractNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseContractServiceByIds(String[] contractNos);
}
