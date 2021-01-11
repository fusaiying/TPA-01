package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.mapper.BaseFeeitemMapper;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.domain.BaseFeeitem;
import com.paic.ehis.system.service.IBaseFeeitemService;

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
    public BaseFeeitem selectBaseFeeitemById(String feeitemCode)
    {
        return baseFeeitemMapper.selectBaseFeeitemById(feeitemCode);
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
        baseFeeitem.setCreateTime(DateUtils.getNowDate());
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
