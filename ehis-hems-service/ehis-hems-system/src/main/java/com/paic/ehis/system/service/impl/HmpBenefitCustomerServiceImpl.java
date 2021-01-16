package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.HmpBenefitCustomer;
import com.paic.ehis.system.mapper.HmpBenefitCustomerMapper;
import com.paic.ehis.system.service.IHmpBenefitCustomerService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户信息Service业务层处理
 * 
 * @author sino
 * @date 2020-11-17
 */
@Service
public class HmpBenefitCustomerServiceImpl implements IHmpBenefitCustomerService
{
    @Autowired
    private HmpBenefitCustomerMapper hmpBenefitCustomerMapper;

    /**
     * 查询客户信息
     * 
     * @param customerid 客户信息ID
     * @return 客户信息
     */
    @Override
    public HmpBenefitCustomer selectHmpBenefitCustomerById(String customerid)
    {
        return hmpBenefitCustomerMapper.selectHmpBenefitCustomerById(customerid);
    }

    /**
     * 查询客户信息列表
     * 
     * @param hmpBenefitCustomer 客户信息
     * @return 客户信息
     */
    @Override
    public List<HmpBenefitCustomer> selectHmpBenefitCustomerList(HmpBenefitCustomer hmpBenefitCustomer)
    {
        return hmpBenefitCustomerMapper.selectHmpBenefitCustomerList(hmpBenefitCustomer);
    }

    /**
     * 新增客户信息
     * 
     * @param hmpBenefitCustomer 客户信息
     * @return 结果
     */
    @Override
    public int insertHmpBenefitCustomer(HmpBenefitCustomer hmpBenefitCustomer)
    {
        hmpBenefitCustomer.setCreateTime(DateUtils.getNowDate());
        return hmpBenefitCustomerMapper.insertHmpBenefitCustomer(hmpBenefitCustomer);
    }

    /**
     * 修改客户信息
     * 
     * @param hmpBenefitCustomer 客户信息
     * @return 结果
     */
    @Override
    public int updateHmpBenefitCustomer(HmpBenefitCustomer hmpBenefitCustomer)
    {
        hmpBenefitCustomer.setUpdateTime(DateUtils.getNowDate());
        return hmpBenefitCustomerMapper.updateHmpBenefitCustomer(hmpBenefitCustomer);
    }

    /**
     * 批量删除客户信息
     * 
     * @param customerids 需要删除的客户信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitCustomerByIds(String[] customerids)
    {
        return hmpBenefitCustomerMapper.deleteHmpBenefitCustomerByIds(customerids);
    }

    /**
     * 删除客户信息信息
     * 
     * @param customerid 客户信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitCustomerById(String customerid)
    {
        return hmpBenefitCustomerMapper.deleteHmpBenefitCustomerById(customerid);
    }

    /**
     * 查询客户信息列表
     *
     * @param hmpBenefitCustomer 客户信息
     * @return 客户信息
     */
    @Override
    public List<HmpBenefitCustomer> selectHmpBenefitCustomerListUseChinese(HmpBenefitCustomer hmpBenefitCustomer)
    {
        return hmpBenefitCustomerMapper.selectHmpBenefitCustomerListUseChinese(hmpBenefitCustomer);
    }
}
