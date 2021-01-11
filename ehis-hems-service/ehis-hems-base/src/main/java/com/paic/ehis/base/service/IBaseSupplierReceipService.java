package com.paic.ehis.base.service;

import com.paic.ehis.base.domain.BaseSupplierReceip;

import java.util.List;


/**
 * base_supplier_receipt(供应商开票信息)Service接口
 * 
 * @author sino
 * @date 2020-12-25
 */
public interface IBaseSupplierReceipService 
{
    /**
     * 查询base_supplier_receipt(供应商开票信息)
     * 
     * @param suppliercode base_supplier_receipt(供应商开票信息)ID
     * @return base_supplier_receipt(供应商开票信息)
     */
    public BaseSupplierReceip selectBaseSupplierReceipById(String suppliercode);

    /**
     * 查询base_supplier_receipt(供应商开票信息)列表
     * 
     * @param baseSupplierReceip base_supplier_receipt(供应商开票信息)
     * @return base_supplier_receipt(供应商开票信息)集合
     */
    public List<BaseSupplierReceip> selectBaseSupplierReceipList(BaseSupplierReceip baseSupplierReceip);

    /**
     * 新增base_supplier_receipt(供应商开票信息)
     * 
     * @param baseSupplierReceip base_supplier_receipt(供应商开票信息)
     * @return 结果
     */
    public int insertBaseSupplierReceip(BaseSupplierReceip baseSupplierReceip);

    /**
     * 修改base_supplier_receipt(供应商开票信息)
     * 
     * @param baseSupplierReceip base_supplier_receipt(供应商开票信息)
     * @return 结果
     */
    public int updateBaseSupplierReceip(BaseSupplierReceip baseSupplierReceip);

    /**
     * 批量删除base_supplier_receipt(供应商开票信息)
     * 
     * @param suppliercodes 需要删除的base_supplier_receipt(供应商开票信息)ID
     * @return 结果
     */
    public int deleteBaseSupplierReceipByIds(String[] suppliercodes);

    /**
     * 删除base_supplier_receipt(供应商开票信息)信息
     * 
     * @param suppliercode base_supplier_receipt(供应商开票信息)ID
     * @return 结果
     */
    public int deleteBaseSupplierReceipById(String suppliercode);
}
