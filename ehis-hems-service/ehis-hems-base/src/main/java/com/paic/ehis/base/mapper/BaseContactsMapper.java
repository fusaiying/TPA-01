package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseContacts;

import java.util.List;


/**
 * base_contacts（联系人信息）Mapper接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface BaseContactsMapper 
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
     * @param suppliercode base_contacts（联系人信息）ID
     * @return base_contacts（联系人信息）
     */
    public List<BaseContacts> selectBaseContactsByCode(String suppliercode);

    /**
     * 查询base_contacts（联系人信息）
     *
     * @param suppliercode base_contacts（联系人信息）ID
     * @return base_contacts（联系人信息）
     */
    public List<BaseContacts> selectBaseContactsByCodeNew(String suppliercode);

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
     * 新增base_contacts（联系人信息）
     *
     * @param baseContacts base_contacts（联系人信息）
     * @return 结果
     */
    public int insertBaseContactsNew(BaseContacts baseContacts);
    /**
     * 修改base_contacts（联系人信息）
     * 
     * @param baseContacts base_contacts（联系人信息）
     * @return 结果
     */
    public int updateBaseContacts(BaseContacts baseContacts);

    /*public int updateBaseContact(BaseContacts baseContacts);*/
    public int updateBaseContactsNew(BaseContacts baseContacts);


    public  int updateBaseContactsStatus(String supplierCode);

    /**
     * 删除base_contacts（联系人信息）
     * 
     * @param serialno base_contacts（联系人信息）ID
     * @return 结果
     */
    public int deleteBaseContactsById(String serialno);

    public int deleteBaseContactsByIdNew(String serialno);

    /**
     * 批量删除base_contacts（联系人信息）
     * 
     * @param serialnos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseContactsByIds(String[] serialnos);

    /**
     * 删除base_contacts（联系人信息）
     *
     * @param supplierCode base_contacts（联系人信息）供应商编码
     * @return 结果
     */
    public int deleteBaseContactsByCode(String supplierCode);

    /**
     * 修改base_contacts（联系人信息）
     *
     * @param supplierCode base_contacts（联系人信息）
     * @return 结果
     */
    public int updateBaseContactsByCode(String supplierCode);

    /**
     * 修改base_contacts（联系人信息）
     *
     * @param supplierCode base_contacts（联系人信息）
     * @return 结果
     */
    public int updateBaseContactsByCodeNew(String supplierCode);

}
