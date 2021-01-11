package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.HmpBenefitCustomer;

/**
 * 客户信息Mapper接口
 * 
 * @author sino
 * @date 2020-11-17
 */
public interface HmpBenefitCustomerMapper 
{
    /**
     * 查询客户信息
     * 
     * @param customerid 客户信息ID
     * @return 客户信息
     */
    public HmpBenefitCustomer selectHmpBenefitCustomerById(String customerid);

    /**
     * 查询客户信息列表
     * 
     * @param hmpBenefitCustomer 客户信息
     * @return 客户信息集合
     */
    public List<HmpBenefitCustomer> selectHmpBenefitCustomerList(HmpBenefitCustomer hmpBenefitCustomer);

    /**
     * 新增客户信息
     * 
     * @param hmpBenefitCustomer 客户信息
     * @return 结果
     */
    public int insertHmpBenefitCustomer(HmpBenefitCustomer hmpBenefitCustomer);

    /**
     * 修改客户信息
     * 
     * @param hmpBenefitCustomer 客户信息
     * @return 结果
     */
    public int updateHmpBenefitCustomer(HmpBenefitCustomer hmpBenefitCustomer);

    /**
     * 删除客户信息
     * 
     * @param customerid 客户信息ID
     * @return 结果
     */
    public int deleteHmpBenefitCustomerById(String customerid);

    /**
     * 批量删除客户信息
     * 
     * @param customerids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpBenefitCustomerByIds(String[] customerids);

    /**
     * 查询客户信息列表
     *
     * @param hmpBenefitCustomer 客户信息
     * @return 客户信息集合
     */
    public List<HmpBenefitCustomer> selectHmpBenefitCustomerListUseChinese(HmpBenefitCustomer hmpBenefitCustomer);
}
