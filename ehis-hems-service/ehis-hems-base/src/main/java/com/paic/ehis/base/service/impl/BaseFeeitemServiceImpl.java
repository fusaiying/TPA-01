package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseFeeitem;
import com.paic.ehis.base.mapper.BaseFeeitemMapper;
import com.paic.ehis.base.service.IBaseFeeitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 费用项信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseFeeitemServiceImpl implements IBaseFeeitemService 
{
    @Autowired
    private BaseFeeitemMapper baseFeeitemMapper;

    /**
     * 查询费用项信息 
     * 
     * @param feeitemCode 费用项信息 ID
     * @return 费用项信息 
     */
    @Override
    public BaseFeeitem selectBaseFeeitemByCode(String feeitemCode)
    {
        return baseFeeitemMapper.selectBaseFeeitemByCode(feeitemCode);
    }

    /**
     * 查询费用项信息
     *
     * @param feeitemName 费用项信息 ID
     * @return 费用项信息
     */
    @Override
    public BaseFeeitem selectBaseFeeitemByName(String feeitemName)
    {
        return baseFeeitemMapper.selectBaseFeeitemByName(feeitemName);
    }

    /**
     * 查询费用项信息 列表
     * 
     * @param baseFeeitem 费用项信息 
     * @return 费用项信息 
     */
    @Override
    public List<BaseFeeitem> selectBaseFeeitemList(BaseFeeitem baseFeeitem)
    {
        baseFeeitem.setStatus("Y");
        return baseFeeitemMapper.selectBaseFeeitemList(baseFeeitem);
    }

    /**
     * 新增费用项信息 
     * 
     * @param baseFeeitem 费用项信息 
     * @return 结果
     */
    @Override
    public int insertBaseFeeitem(BaseFeeitem baseFeeitem)
    {
        baseFeeitem.setStatus("Y");
        baseFeeitem.setCreateBy(SecurityUtils.getUsername());
        baseFeeitem.setCreateTime(DateUtils.getNowDate());
        baseFeeitem.setUpdateBy(SecurityUtils.getUsername());
        baseFeeitem.setUpdateTime(DateUtils.getNowDate());
        return baseFeeitemMapper.insertBaseFeeitem(baseFeeitem);
    }

    /**
     * 修改费用项信息 
     * 
     * @param baseFeeitem 费用项信息 
     * @return 结果
     */
    @Override
    public int updateBaseFeeitem(BaseFeeitem baseFeeitem)
    {
        baseFeeitem.setUpdateBy(SecurityUtils.getUsername());
        baseFeeitem.setUpdateTime(DateUtils.getNowDate());
        return baseFeeitemMapper.updateBaseFeeitem(baseFeeitem);
    }

    /**
     * 批量删除费用项信息 
     * 
     * @param feeitemCodes 需要删除的费用项信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseFeeitemByIds(String[] feeitemCodes)
    {
        return baseFeeitemMapper.deleteBaseFeeitemByIds(feeitemCodes);
    }

    /**
     * 删除费用项信息 信息
     * 
     * @param feeitemCode 费用项信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseFeeitemById(String feeitemCode)
    {
        return baseFeeitemMapper.deleteBaseFeeitemById(feeitemCode);
    }
}
