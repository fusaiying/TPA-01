package com.paic.ehis.finance.service;

import com.paic.ehis.finance.domain.FinanceCollectionInfo;

import java.util.List;

/**
 * 实收信息明细Service接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface IFinanceCollectionInfoService 
{
    /**
     * 查询实收信息明细
     * 
     * @param collectionId 实收信息明细ID
     * @return 实收信息明细
     */
    public FinanceCollectionInfo selectFinanceCollectionInfoById(Long collectionId);

    /**
     * 查询实收信息明细列表
     * 
     * @param financeCollectionInfo 实收信息明细
     * @return 实收信息明细集合
     */
    public List<FinanceCollectionInfo> selectFinanceCollectionInfoList(FinanceCollectionInfo financeCollectionInfo);

    /**
     * 新增实收信息明细
     * 
     * @param financeCollectionInfo 实收信息明细
     * @return 结果
     */
    public int insertFinanceCollectionInfo(FinanceCollectionInfo financeCollectionInfo);

    /**
     * 修改实收信息明细
     * 
     * @param financeCollectionInfo 实收信息明细
     * @return 结果
     */
    public int updateFinanceCollectionInfo(FinanceCollectionInfo financeCollectionInfo);

    /**
     * 批量删除实收信息明细
     * 
     * @param collectionIds 需要删除的实收信息明细ID
     * @return 结果
     */
    public int deleteFinanceCollectionInfoByIds(Long[] collectionIds);

    /**
     * 删除实收信息明细信息
     * 
     * @param collectionId 实收信息明细ID
     * @return 结果
     */
    public int deleteFinanceCollectionInfoById(Long collectionId);
}
