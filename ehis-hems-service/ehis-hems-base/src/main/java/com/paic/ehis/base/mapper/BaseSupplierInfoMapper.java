package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseSupplierInfo;

import java.util.List;


/**
 * base_supplier_info（供应商基础信息）Mapper接口
 * 
 * @author sino
 * @date 2020-12-31
 */
public interface BaseSupplierInfoMapper 
{
    /**
     * 查询base_supplier_info（供应商基础信息）
     * 
     * @param servcomNo base_supplier_info（供应商基础信息）ID
     * @return base_supplier_info（供应商基础信息）
     */
    public BaseSupplierInfo selectBaseSupplierInfoById(String servcomNo);

    /**
     * 查询base_supplier_info（供应商基础信息）列表
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return base_supplier_info（供应商基础信息）集合
     */
    public List<BaseSupplierInfo> selectBaseSupplierInfoList(BaseSupplierInfo baseSupplierInfo);

    /**
     * 新增base_supplier_info（供应商基础信息）
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return 结果
     */
    public int insertBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo);

    /**
     * 修改base_supplier_info（供应商基础信息）
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return 结果
     */
    public int updateBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo);

    /**
     * 删除base_supplier_info（供应商基础信息）
     * 
     * @param servcomNo base_supplier_info（供应商基础信息）ID
     * @return 结果
     */
    public int deleteBaseSupplierInfoById(String servcomNo);

    /**
     * 批量删除base_supplier_info（供应商基础信息）
     * 
     * @param servcomNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseSupplierInfoByIds(String[] servcomNos);
}
