package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.base.domain.BaseSupplierOutlets;
import com.paic.ehis.base.mapper.BaseSupplierOutletsMapper;
import com.paic.ehis.base.mapper.OutletsMapper;
import com.paic.ehis.base.service.IBaseSupplierOutletsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * base_supplier_outlets（服务网点配置）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-27
 */
@Service
public class BaseSupplierOutletsServiceImpl implements IBaseSupplierOutletsService
{
    @Autowired
    private BaseSupplierOutletsMapper baseSupplierOutletsMapper;
    @Autowired
    private OutletsMapper outletsMapper;

    @Override
    public List<BaseSupplierOutlets> selectOutletsList(BaseSupplierOutlets baseSupplierOutlets){

        return outletsMapper.selectOutletsList(baseSupplierOutlets);
    }


    /**
     * 查询base_supplier_outlets（服务网点配置）
     * 
     * @param servcomno base_supplier_outlets（服务网点配置）ID
     * @return base_supplier_outlets（服务网点配置）
     */
    @Override
    public BaseSupplierOutlets selectBaseSupplierOutletsById(String servcomno)
    {
        return baseSupplierOutletsMapper.selectBaseSupplierOutletsById(servcomno);
    }

    /**
     * 查询base_supplier_outlets（服务网点配置）列表
     * 
     * @param baseSupplierOutlets base_supplier_outlets（服务网点配置）
     * @return base_supplier_outlets（服务网点配置）
     */
    @Override
    public List<BaseSupplierOutlets> selectBaseSupplierOutletsList(BaseSupplierOutlets baseSupplierOutlets)
    {
        return baseSupplierOutletsMapper.selectBaseSupplierOutletsList(baseSupplierOutlets);
    }

    /**
     * 新增base_supplier_outlets（服务网点配置）
     * 
     * @param baseSupplierOutlets base_supplier_outlets（服务网点配置）
     * @return 结果
     */
    @Override
    public int insertBaseSupplierOutlets(BaseSupplierOutlets baseSupplierOutlets)
    {
        baseSupplierOutlets.setCreateTime(DateUtils.getNowDate());
        baseSupplierOutlets.setUpdateTime(DateUtils.getNowDate());
        baseSupplierOutlets.setSerialNo(PubFun.createMySqlMaxNoUseCache("derialno", 10, 9));
        return baseSupplierOutletsMapper.insertBaseSupplierOutlets(baseSupplierOutlets);
    }

    /**
     * 修改base_supplier_outlets（服务网点配置）
     * 
     * @param baseSupplierOutlets base_supplier_outlets（服务网点配置）
     * @return 结果
     */
    @Override
    public int updateBaseSupplierOutlets(BaseSupplierOutlets baseSupplierOutlets)
    {
        baseSupplierOutlets.setUpdateTime(DateUtils.getNowDate());
        return baseSupplierOutletsMapper.updateBaseSupplierOutlets(baseSupplierOutlets);
    }

    @Override
    public int updateBaseSupplierOutletsStatus( BaseSupplierOutlets baseSupplierOutlets)
    {
      //  String websiteCode=baseSupplierOutlets.getWebsiteCode();
        return baseSupplierOutletsMapper.updateBaseSupplierOutletsStatus(baseSupplierOutlets);
    }

    /**
     * 批量删除base_supplier_outlets（服务网点配置）
     * 
     * @param servcomnos 需要删除的base_supplier_outlets（服务网点配置）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierOutletsByIds(String[] servcomnos)
    {
        return baseSupplierOutletsMapper.deleteBaseSupplierOutletsByIds(servcomnos);
    }

    /**
     * 删除base_supplier_outlets（服务网点配置）信息
     * 
     * @param servcomno base_supplier_outlets（服务网点配置）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierOutletsById(String servcomno)
    {
        return baseSupplierOutletsMapper.deleteBaseSupplierOutletsById(servcomno);
    }
}
