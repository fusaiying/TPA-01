package com.paic.ehis.base.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.paic.ehis.base.service.IBaseSupplierInfoService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.base.base.utility.Dateutils;
import com.paic.ehis.base.domain.BaseSupplierInfo;
import com.paic.ehis.base.mapper.BaseSupplierInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * base_supplier_info（供应商基础信息）Service业务层处理
 * 
 * @author sino
 * @date 2020-12-31
 */
@Service
public class BaseSupplierInfoServiceImpl implements IBaseSupplierInfoService
{
    @Autowired
    private BaseSupplierInfoMapper baseSupplierInfoMapper;

    /**
     * 查询base_supplier_info（供应商基础信息）
     * 
     * @param servcomNo base_supplier_info（供应商基础信息）ID
     * @return base_supplier_info（供应商基础信息）
     */
    @Override
    public BaseSupplierInfo selectBaseSupplierInfoById(String servcomNo)
    {
        return baseSupplierInfoMapper.selectBaseSupplierInfoById(servcomNo);
    }

    /**
     * 查询base_supplier_info（供应商基础信息）列表
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return base_supplier_info（供应商基础信息）
     */
    @Override
    public List<BaseSupplierInfo> selectBaseSupplierInfoList(BaseSupplierInfo baseSupplierInfo) throws Exception
    {
        Map map = Dateutils.getCurrontTime1();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        baseSupplierInfo.setdBefore1(sdf.parse(String.valueOf(map.get("defaultStartDate"))));
        baseSupplierInfo.setdNow1(sdf.parse(String.valueOf(map.get("defaultEndDate"))));
        return baseSupplierInfoMapper.selectBaseSupplierInfoList(baseSupplierInfo);
    }

    /**
     * 新增base_supplier_info（供应商基础信息）
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return 结果
     */
    @Override
    public int insertBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo)
    {
        baseSupplierInfo.setCreateTime(DateUtils.getNowDate());
        return baseSupplierInfoMapper.insertBaseSupplierInfo(baseSupplierInfo);
    }

    /**
     * 修改base_supplier_info（供应商基础信息）
     * 
     * @param baseSupplierInfo base_supplier_info（供应商基础信息）
     * @return 结果
     */
    @Override
    public int updateBaseSupplierInfo(BaseSupplierInfo baseSupplierInfo)
    {
        baseSupplierInfo.setUpdateTime(DateUtils.getNowDate());
        return baseSupplierInfoMapper.updateBaseSupplierInfo(baseSupplierInfo);
    }

    /**
     * 批量删除base_supplier_info（供应商基础信息）
     * 
     * @param servcomNos 需要删除的base_supplier_info（供应商基础信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierInfoByIds(String[] servcomNos)
    {
        return baseSupplierInfoMapper.deleteBaseSupplierInfoByIds(servcomNos);
    }

    /**
     * 删除base_supplier_info（供应商基础信息）信息
     * 
     * @param servcomNo base_supplier_info（供应商基础信息）ID
     * @return 结果
     */
    @Override
    public int deleteBaseSupplierInfoById(String servcomNo)
    {
        return baseSupplierInfoMapper.deleteBaseSupplierInfoById(servcomNo);
    }
}
