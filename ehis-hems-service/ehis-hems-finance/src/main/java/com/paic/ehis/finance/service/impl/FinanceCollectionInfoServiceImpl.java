package com.paic.ehis.finance.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.finance.domain.FinanceCollectionInfo;
import com.paic.ehis.finance.mapper.FinanceCollectionInfoMapper;
import com.paic.ehis.finance.service.IFinanceCollectionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实收信息明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-30
 */
@Service
public class FinanceCollectionInfoServiceImpl implements IFinanceCollectionInfoService 
{
    @Autowired
    private FinanceCollectionInfoMapper financeCollectionInfoMapper;

    /**
     * 查询实收信息明细
     * 
     * @param collectionId 实收信息明细ID
     * @return 实收信息明细
     */
    @Override
    public FinanceCollectionInfo selectFinanceCollectionInfoById(Long collectionId)
    {
        return financeCollectionInfoMapper.selectFinanceCollectionInfoById(collectionId);
    }

    /**
     * 查询实收信息明细列表
     * 
     * @param financeCollectionInfo 实收信息明细
     * @return 实收信息明细
     */
    @Override
    public List<FinanceCollectionInfo> selectFinanceCollectionInfoList(FinanceCollectionInfo financeCollectionInfo)
    {
        return financeCollectionInfoMapper.selectFinanceCollectionInfoList(financeCollectionInfo);
    }

    /**
     * 新增实收信息明细
     * 
     * @param financeCollectionInfo 实收信息明细
     * @return 结果
     */
    @Override
    public int insertFinanceCollectionInfo(FinanceCollectionInfo financeCollectionInfo)
    {
        financeCollectionInfo.setCreateTime(DateUtils.getNowDate());
        return financeCollectionInfoMapper.insertFinanceCollectionInfo(financeCollectionInfo);
    }

    /**
     * 修改实收信息明细
     * 
     * @param financeCollectionInfo 实收信息明细
     * @return 结果
     */
    @Override
    public int updateFinanceCollectionInfo(FinanceCollectionInfo financeCollectionInfo)
    {
        financeCollectionInfo.setUpdateTime(DateUtils.getNowDate());
        return financeCollectionInfoMapper.updateFinanceCollectionInfo(financeCollectionInfo);
    }

    /**
     * 批量删除实收信息明细
     * 
     * @param collectionIds 需要删除的实收信息明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceCollectionInfoByIds(Long[] collectionIds)
    {
        return financeCollectionInfoMapper.deleteFinanceCollectionInfoByIds(collectionIds);
    }

    /**
     * 删除实收信息明细信息
     * 
     * @param collectionId 实收信息明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceCollectionInfoById(Long collectionId)
    {
        return financeCollectionInfoMapper.deleteFinanceCollectionInfoById(collectionId);
    }
}
