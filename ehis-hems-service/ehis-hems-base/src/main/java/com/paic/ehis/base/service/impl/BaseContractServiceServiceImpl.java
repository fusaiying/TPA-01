package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.base.domain.BaseContractService;
import com.paic.ehis.base.mapper.BaseContractServiceMapper;
import com.paic.ehis.base.service.IBaseContractServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public BaseContractService insertBaseContractService(BaseContractService baseContractService) {

     BaseContractService baseContractService1=new BaseContractService();
     if (StringUtils.isNotBlank(baseContractService.getSerialNo())){
         //当流水号不为空时更新服务项目信息
         baseContractServiceMapper.updateBaseContractService(baseContractService);
         baseContractService.setCreateTime(DateUtils.getNowDate());
         baseContractService.setStatus("Y");
         baseContractServiceMapper.insertBaseContractService(baseContractService);
     }else {  //当流水号为空时新增服务项目信息
         baseContractService.setCreateTime(DateUtils.getNowDate());
         baseContractService.setSerialNo(PubFun.createMySqlMaxNoUseCache("serialno", 10, 9));
         baseContractService.setStatus("Y");
         baseContractService.getSupplierCode();
         baseContractServiceMapper.insertBaseContractService(baseContractService);
         baseContractService1.setSerialNo(baseContractService.getSerialNo());
     }
     return baseContractService1;
    }

    /**
     * 批量新增
     */
    @Override
    public int insertForeach(List<BaseContractService> baseContractServiceList){
        int result = 0;
        String username = SecurityUtils.getUsername();
        Date nowDate = new Date();
        for(BaseContractService bean : baseContractServiceList ) {
            bean.setUpdateBy(username);
            bean.setUpdateTime(nowDate);
            if(StringUtils.isBlank(bean.getStatus())) {
                bean.setStatus("Y");
            }
            if(StringUtils.isNotBlank(bean.getSerialNo())) {
                result =  baseContractServiceMapper.updateBaseContractService(bean);
            } else {
                bean.setSerialNo(PubFun.createMySqlMaxNoUseCache("serialno", 10, 9));
                bean.setCreateTime(nowDate);
                bean.setCreateBy(username);
                result = baseContractServiceMapper.insertBaseContractService(bean);
            }
        }
        return result;
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
     * @return 结果
     */
    @Override
    public int deleteBaseContractServiceById(String serialNo)
    {
        return baseContractServiceMapper.deleteBaseContractServiceById(serialNo);
    }


}
