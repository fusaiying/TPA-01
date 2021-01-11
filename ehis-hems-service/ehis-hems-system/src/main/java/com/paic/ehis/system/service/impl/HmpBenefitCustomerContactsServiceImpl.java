package com.paic.ehis.system.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.HmpBenefitCustomerContactsMapper;
import com.paic.ehis.system.domain.HmpBenefitCustomerContacts;
import com.paic.ehis.system.service.IHmpBenefitCustomerContactsService;

/**
 * 客户联系信息Service业务层处理
 * 
 * @author sino
 * @date 2020-11-23
 */
@Service
public class HmpBenefitCustomerContactsServiceImpl implements IHmpBenefitCustomerContactsService 
{
    @Autowired
    private HmpBenefitCustomerContactsMapper hmpBenefitCustomerContactsMapper;

    /**
     * 查询客户联系信息
     * 
     * @param contactscode 客户联系信息ID
     * @return 客户联系信息
     */
    @Override
    public HmpBenefitCustomerContacts selectHmpBenefitCustomerContactsById(String contactscode)
    {
        return hmpBenefitCustomerContactsMapper.selectHmpBenefitCustomerContactsById(contactscode);
    }

    /**
     * 查询客户联系信息列表
     * 
     * @param hmpBenefitCustomerContacts 客户联系信息
     * @return 客户联系信息
     */
    @Override
    public List<HmpBenefitCustomerContacts> selectHmpBenefitCustomerContactsList(HmpBenefitCustomerContacts hmpBenefitCustomerContacts)
    {
        return hmpBenefitCustomerContactsMapper.selectHmpBenefitCustomerContactsList(hmpBenefitCustomerContacts);
    }

    /**
     * 新增客户联系信息
     * 
     * @param hmpBenefitCustomerContacts 客户联系信息
     * @return 结果
     */
    @Override
    public int insertHmpBenefitCustomerContacts(HmpBenefitCustomerContacts hmpBenefitCustomerContacts)
    {
        hmpBenefitCustomerContacts.setCreateTime(DateUtils.getNowDate());
        return hmpBenefitCustomerContactsMapper.insertHmpBenefitCustomerContacts(hmpBenefitCustomerContacts);
    }

    /**
     * 修改客户联系信息
     * 
     * @param hmpBenefitCustomerContacts 客户联系信息
     * @return 结果
     */
    @Override
    public int updateHmpBenefitCustomerContacts(HmpBenefitCustomerContacts hmpBenefitCustomerContacts)
    {
        hmpBenefitCustomerContacts.setUpdateTime(DateUtils.getNowDate());
        return hmpBenefitCustomerContactsMapper.updateHmpBenefitCustomerContacts(hmpBenefitCustomerContacts);
    }

    /**
     * 批量删除客户联系信息
     * 
     * @param contactscodes 需要删除的客户联系信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitCustomerContactsByIds(String[] contactscodes)
    {
        return hmpBenefitCustomerContactsMapper.deleteHmpBenefitCustomerContactsByIds(contactscodes);
    }

    /**
     * 删除客户联系信息信息
     * 
     * @param contactscode 客户联系信息ID
     * @return 结果
     */
    @Override
    public int deleteHmpBenefitCustomerContactsById(String contactscode)
    {
        return hmpBenefitCustomerContactsMapper.deleteHmpBenefitCustomerContactsById(contactscode);
    }
}
