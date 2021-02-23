package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseContacts;
import com.paic.ehis.base.domain.vo.BaseContactsVo;

import java.util.List;


/**
 * base_contacts（联系人信息）Service接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface IBaseContactsService 
{
    /**
     * 查询base_contacts（联系人信息）
     * 
     * @param serialno base_contacts（联系人信息）ID
     * @return base_contacts（联系人信息）
     */
    public BaseContacts selectBaseContactsById(String serialno);

    /**
     * 查询base_contacts（联系人信息）
     *
     * @param baseContacts base_contacts（联系人信息）ID
     * @return base_contacts（联系人信息）
     */
    public List<BaseContacts> selectBaseContactsByCode(BaseContacts baseContacts);

    /**
     * 查询base_contacts（联系人信息）列表
     * 
     * @param baseContacts base_contacts（联系人信息）
     * @return base_contacts（联系人信息）集合
     */
    public List<BaseContacts> selectBaseContactsList(BaseContacts baseContacts);

    /**
     * 新增base_contacts（联系人信息）
     * 
     * @param baseContacts base_contacts（联系人信息）
     * @return 结果
     */
    public int insertBaseContacts(BaseContacts baseContacts);

    /**
     * 修改base_contacts（联系人信息）
     *
     * @return 结果
     */
    public int insertBaseContacts(BaseContactsVo baseContactsVo);

    public int insertBaseContactsNew(String providerCode);

    public int deleteBaseContacts(BaseContacts baseContacts);

    public int deleteBaseContact(BaseContacts baseContacts);

    public int addBaseContacts(List<BaseContacts> baseContactsVo);


    public  int updateBaseContactsStatus(String supplierCode);


    /**
     * 批量删除base_contacts（联系人信息）
     * 
     * @param serialnos 需要删除的base_contacts（联系人信息）ID
     * @return 结果
     */
    public int deleteBaseContactsByIds(String[] serialnos);

    /**
     * 删除base_contacts（联系人信息）信息
     * 
     * @param serialno base_contacts（联系人信息）ID
     * @return 结果
     */
    public int deleteBaseContactsById(String serialno);

}
