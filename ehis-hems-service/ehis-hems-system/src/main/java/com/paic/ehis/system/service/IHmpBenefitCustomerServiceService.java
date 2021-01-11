package com.paic.ehis.system.service;

import java.util.List;
import com.paic.ehis.system.domain.HmpBenefitCustomerService;

/**
 * 客户服务产品Service接口
 * 
 * @author sino
 * @date 2020-11-17
 */
public interface IHmpBenefitCustomerServiceService 
{
    /**
     * 查询客户服务产品
     * 
     * @param customerid 客户服务产品ID
     * @return 客户服务产品
     */
    public HmpBenefitCustomerService selectHmpBenefitCustomerServiceById(String customerid);

    /**
     * 查询客户服务产品列表
     * 
     * @param hmpBenefitCustomerService 客户服务产品
     * @return 客户服务产品集合
     */
    public List<HmpBenefitCustomerService> selectHmpBenefitCustomerServiceList(HmpBenefitCustomerService hmpBenefitCustomerService);

    /**
     * 新增客户服务产品
     * 
     * @param hmpBenefitCustomerService 客户服务产品
     * @return 结果
     */
    public int insertHmpBenefitCustomerService(HmpBenefitCustomerService hmpBenefitCustomerService);

    /**
     * 修改客户服务产品
     * 
     * @param hmpBenefitCustomerService 客户服务产品
     * @return 结果
     */
    public int updateHmpBenefitCustomerService(HmpBenefitCustomerService hmpBenefitCustomerService);

    /**
     * 批量删除客户服务产品
     * 
     * @param customerids 需要删除的客户服务产品ID
     * @return 结果
     */
    public int deleteHmpBenefitCustomerServiceByIds(String[] customerids);

    /**
     * 删除客户服务产品信息
     * 
     * @param customerid 客户服务产品ID
     * @return 结果
     */
    public int deleteHmpBenefitCustomerServiceById(String customerid);

    /**
     * 查询客户服务产品列表
     *
     * @param hmpBenefitCustomerService 客户服务产品
     * @return 客户服务产品集合
     */
    public List<HmpBenefitCustomerService> selectHmpBenefitCustomerServiceListUseChinese(HmpBenefitCustomerService hmpBenefitCustomerService);

}
