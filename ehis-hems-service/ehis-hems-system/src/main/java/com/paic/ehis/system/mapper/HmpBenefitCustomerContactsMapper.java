package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.HmpBenefitCustomerContacts;

/**
 * 客户联系信息Mapper接口
 * 
 * @author sino
 * @date 2020-11-23
 */
public interface HmpBenefitCustomerContactsMapper 
{
    /**
     * 查询客户联系信息
     * 
     * @param contactscode 客户联系信息ID
     * @return 客户联系信息
     */
    public HmpBenefitCustomerContacts selectHmpBenefitCustomerContactsById(String contactscode);

    /**
     * 查询客户联系信息列表
     * 
     * @param hmpBenefitCustomerContacts 客户联系信息
     * @return 客户联系信息集合
     */
    public List<HmpBenefitCustomerContacts> selectHmpBenefitCustomerContactsList(HmpBenefitCustomerContacts hmpBenefitCustomerContacts);

    /**
     * 新增客户联系信息
     * 
     * @param hmpBenefitCustomerContacts 客户联系信息
     * @return 结果
     */
    public int insertHmpBenefitCustomerContacts(HmpBenefitCustomerContacts hmpBenefitCustomerContacts);

    /**
     * 修改客户联系信息
     * 
     * @param hmpBenefitCustomerContacts 客户联系信息
     * @return 结果
     */
    public int updateHmpBenefitCustomerContacts(HmpBenefitCustomerContacts hmpBenefitCustomerContacts);

    /**
     * 删除客户联系信息
     * 
     * @param contactscode 客户联系信息ID
     * @return 结果
     */
    public int deleteHmpBenefitCustomerContactsById(String contactscode);

    /**
     * 批量删除客户联系信息
     * 
     * @param contactscodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpBenefitCustomerContactsByIds(String[] contactscodes);
}
