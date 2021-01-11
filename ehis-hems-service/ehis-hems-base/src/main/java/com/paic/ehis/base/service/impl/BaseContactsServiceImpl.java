package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.domain.BaseContacts;
import com.paic.ehis.base.mapper.BaseContactsMapper;
import com.paic.ehis.base.service.IBaseContactsService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * base_contacts（联系人信息）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
public class BaseContactsServiceImpl implements IBaseContactsService
{
    @Autowired
    private BaseContactsMapper baseContactsMapper;

    /**
     * 查询base_contacts（联系人信息）
     * 
     * @param serialno base_contacts（联系人信息）ID
     * @return base_contacts（联系人信息）
     */
    @Override
    public BaseContacts selectBaseContactsById(String serialno)
    {
        return baseContactsMapper.selectBaseContactsById(serialno);
    }

    /**
     * 查询base_contacts（联系人信息）列表
     * 
     * @param baseContacts base_contacts（联系人信息）
     * @return base_contacts（联系人信息）
     */
    @Override
    public List<BaseContacts> selectBaseContactsList(BaseContacts baseContacts)
    {
        return baseContactsMapper.selectBaseContactsList(baseContacts);
    }

    @Override
    public int insertBaseContacts(BaseContacts baseContacts) {
        return 0;
    }

    /**
     * 新增base_contacts（联系人信息）
     * 
     * @param baseContactsVo base_contacts（联系人信息）
     * @return 结果
     */
    @Override
    public int insertBaseContacts(List<BaseContacts> baseContactsVo)
    {
        int count = 0;
        //先删除后增加
        baseContactsMapper.deleteBaseContactsByCode(baseContactsVo.get(0).getSupplierCode());
        for(BaseContacts baseContact :baseContactsVo){
            baseContact.setCreateTime(DateUtils.getNowDate());
            baseContact.setStatus("Y");
            baseContact.setSerialNo(PubFun.createMySqlMaxNoUseCache("derialno",10,9));
            int i =baseContactsMapper.insertBaseContacts(baseContact);
            count += i;
        }
        return count;
    }



    /**
     * 批量删除base_contacts（联系人信息）
     * 
     * @param serialnos 需要删除的base_contacts（联系人信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseContactsByIds(String[] serialnos)
    {
        return baseContactsMapper.deleteBaseContactsByIds(serialnos);
    }

    /**
     * 删除base_contacts（联系人信息）信息
     * 
     * @param serialno base_contacts（联系人信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseContactsById(String serialno)
    {
        return baseContactsMapper.deleteBaseContactsById(serialno);
    }

    /**
     * 查询base_contacts（联系人信息）
     *
     * @param suppliercode base_contacts（联系人信息）
     * @return base_contacts（联系人信息）
     */
    @Override
    public List<BaseContacts> selectBaseContactsByCode(String suppliercode)
    {
        return baseContactsMapper.selectBaseContactsByCode(suppliercode);
    }
}
