package com.paic.ehis.base.service.impl;

import java.util.List;

import com.paic.ehis.base.service.IBaseSupplierReceipService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.domain.BaseSupplierReceip;
import com.paic.ehis.base.mapper.BaseSupplierReceipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * base_supplier_receipt(供应商开票信息)Service业务层处理
 * 
 * @author sino
 * @date 2020-12-25
 */
@Service
public class BaseSupplierReceipServiceImpl implements IBaseSupplierReceipService
{
    @Autowired
    private BaseSupplierReceipMapper baseSupplierReceipMapper;

    /**
     * 查询base_supplier_receipt(供应商开票信息)
     * 
     * @param suppliercode base_supplier_receipt(供应商开票信息)ID
     * @return base_supplier_receipt(供应商开票信息)
     */
    @Override
    public BaseSupplierReceip selectBaseSupplierReceipById(String suppliercode)
    {
        return baseSupplierReceipMapper.selectBaseSupplierReceipById(suppliercode);
    }

    /**
     * 查询base_supplier_receipt(供应商开票信息)列表
     * 
     * @param baseSupplierReceip base_supplier_receipt(供应商开票信息)
     * @return base_supplier_receipt(供应商开票信息)
     */
    @Override
    public List<BaseSupplierReceip> selectBaseSupplierReceipList(BaseSupplierReceip baseSupplierReceip)
    {
        return baseSupplierReceipMapper.selectBaseSupplierReceipList(baseSupplierReceip);
    }

    /**
     * 新增base_supplier_receipt(供应商开票信息)
     * 
     * @param baseSupplierReceip base_supplier_receipt(供应商开票信息)
     * @return 结果
     */
    @Override
    public int insertBaseSupplierReceip(BaseSupplierReceip baseSupplierReceip)
    {
        baseSupplierReceip.setCreateTime(DateUtils.getNowDate());
        return baseSupplierReceipMapper.insertBaseSupplierReceip(baseSupplierReceip);
    }

    /**
     * 修改base_supplier_receipt(供应商开票信息)
     * 
     * @param baseSupplierReceip base_supplier_receipt(供应商开票信息)
     * @return 结果
     */
    @Override
    public int updateBaseSupplierReceip(BaseSupplierReceip baseSupplierReceip)
    {
        baseSupplierReceip.setUpdateTime(DateUtils.getNowDate());
        return baseSupplierReceipMapper.updateBaseSupplierReceip(baseSupplierReceip);
    }

    /**
     * 批量删除base_supplier_receipt(供应商开票信息)
     * 
     * @param suppliercodes 需要删除的base_supplier_receipt(供应商开票信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierReceipByIds(String[] suppliercodes)
    {
        return baseSupplierReceipMapper.deleteBaseSupplierReceipByIds(suppliercodes);
    }

    /**
     * 删除base_supplier_receipt(供应商开票信息)信息
     * 
     * @param suppliercode base_supplier_receipt(供应商开票信息)ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierReceipById(String suppliercode)
    {
        return baseSupplierReceipMapper.deleteBaseSupplierReceipById(suppliercode);
    }
}
