package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseSupplierOutlets;

import java.util.List;


/**
 * base_supplier_outlets（服务网点配置）Mapper接口
 * 
 * @author sino
 * @date 2020-12-27
 */
public interface BaseSupplierOutletsMapper 
{
    /**
     * 查询base_supplier_outlets（服务网点配置）
     * 
     * @param servcomno base_supplier_outlets（服务网点配置）ID
     * @return base_supplier_outlets（服务网点配置）
     */
    public BaseSupplierOutlets selectBaseSupplierOutletsById(String servcomno);

    /**
     * 查询base_supplier_outlets（服务网点配置）列表
     * 
     * @param baseSupplierOutlets base_supplier_outlets（服务网点配置）
     * @return base_supplier_outlets（服务网点配置）集合
     */
    public List<BaseSupplierOutlets> selectBaseSupplierOutletsList(BaseSupplierOutlets baseSupplierOutlets);

    /**
     * 新增base_supplier_outlets（服务网点配置）
     * 
     * @param baseSupplierOutlets base_supplier_outlets（服务网点配置）
     * @return 结果
     */
    public int insertBaseSupplierOutlets(BaseSupplierOutlets baseSupplierOutlets);

    /**
     * 修改base_supplier_outlets（服务网点配置）
     * 
     * @param baseSupplierOutlets base_supplier_outlets（服务网点配置）
     * @return 结果
     */
    public int updateBaseSupplierOutlets(BaseSupplierOutlets baseSupplierOutlets);

    /**
     * 删除base_supplier_outlets（服务网点配置）
     * 
     * @param servcomno base_supplier_outlets（服务网点配置）ID
     * @return 结果
     */
    public int deleteBaseSupplierOutletsById(String servcomno);

    /**
     * 批量删除base_supplier_outlets（服务网点配置）
     * 
     * @param servcomnos 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseSupplierOutletsByIds(String[] servcomnos);

   public int  updateBaseSupplierOutletsStatus(String websiteCode);
}
