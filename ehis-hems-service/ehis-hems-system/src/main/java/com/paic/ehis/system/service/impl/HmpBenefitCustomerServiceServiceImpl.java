package com.paic.ehis.system.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpBenefitCustomerServiceMapper;
import com.paic.ehis.system.domain.HmpBenefitCustomerService;
import com.paic.ehis.system.service.IHmpBenefitCustomerServiceService;

/**
 * 客户服务产品Service业务层处理
 * 
 * @author sino
 * @date 2020-11-17
 */
@Service
public class HmpBenefitCustomerServiceServiceImpl implements IHmpBenefitCustomerServiceService 
{
    @Autowired
    private HmpBenefitCustomerServiceMapper hmpBenefitCustomerServiceMapper;

    /**
     * 查询客户服务产品
     * 
     * @param customerid 客户服务产品ID
     * @return 客户服务产品
     */
    @Override
    public HmpBenefitCustomerService selectHmpBenefitCustomerServiceById(String customerid)
    {
        return hmpBenefitCustomerServiceMapper.selectHmpBenefitCustomerServiceById(customerid);
    }

    /**
     * 查询客户服务产品列表
     * 
     * @param hmpBenefitCustomerService 客户服务产品
     * @return 客户服务产品
     */
    @Override
    public List<HmpBenefitCustomerService> selectHmpBenefitCustomerServiceList(HmpBenefitCustomerService hmpBenefitCustomerService)
    {
        return hmpBenefitCustomerServiceMapper.selectHmpBenefitCustomerServiceList(hmpBenefitCustomerService);
    }

    /**
     * 新增客户服务产品
     * 
     * @param hmpBenefitCustomerService 客户服务产品
     * @return 结果
     */
    @Override
    public int insertHmpBenefitCustomerService(HmpBenefitCustomerService hmpBenefitCustomerService)
    {
        hmpBenefitCustomerService.setCreateTime(DateUtils.getNowDate());
        return hmpBenefitCustomerServiceMapper.insertHmpBenefitCustomerService(hmpBenefitCustomerService);
    }

    /**
     * 修改客户服务产品
     * 
     * @param hmpBenefitCustomerService 客户服务产品
     * @return 结果
     */
    @Override
    public int updateHmpBenefitCustomerService(HmpBenefitCustomerService hmpBenefitCustomerService)
    {
        hmpBenefitCustomerService.setUpdateTime(DateUtils.getNowDate());
        return hmpBenefitCustomerServiceMapper.updateHmpBenefitCustomerService(hmpBenefitCustomerService);
    }

    /**
     * 批量删除客户服务产品
     * 
     * @param customerids 需要删除的客户服务产品ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitCustomerServiceByIds(String[] customerids)
    {
        return hmpBenefitCustomerServiceMapper.deleteHmpBenefitCustomerServiceByIds(customerids);
    }

    /**
     * 删除客户服务产品信息
     * 
     * @param customerid 客户服务产品ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitCustomerServiceById(String customerid)
    {
        return hmpBenefitCustomerServiceMapper.deleteHmpBenefitCustomerServiceById(customerid);
    }

    /**
     * 查询客户服务产品列表
     *
     * @param hmpBenefitCustomerService 客户服务产品
     * @return 客户服务产品
     */
    @Override
    public List<HmpBenefitCustomerService> selectHmpBenefitCustomerServiceListUseChinese(HmpBenefitCustomerService hmpBenefitCustomerService)
    {
        return hmpBenefitCustomerServiceMapper.selectHmpBenefitCustomerServiceListUseChinese(hmpBenefitCustomerService);
    }
}
