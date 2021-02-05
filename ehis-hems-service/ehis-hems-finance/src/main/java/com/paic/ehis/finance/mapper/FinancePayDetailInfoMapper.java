package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.FinancePayDetailInfo;

import java.util.List;

/**
 * 支付明细信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface FinancePayDetailInfoMapper 
{
    /**
     * 查询支付明细信息
     * 
     * @param payDetailId 支付明细信息ID
     * @return 支付明细信息
     */
    public FinancePayDetailInfo selectFinancePayDetailInfoById(Long payDetailId);

    /**
     * 查询支付明细信息列表
     * 
     * @param financePayDetailInfo 支付明细信息
     * @return 支付明细信息集合
     */
    public List<FinancePayDetailInfo> selectFinancePayDetailInfoList(FinancePayDetailInfo financePayDetailInfo);

    /**
     * 新增支付明细信息
     * 
     * @param financePayDetailInfo 支付明细信息
     * @return 结果
     */
    public int insertFinancePayDetailInfo(FinancePayDetailInfo financePayDetailInfo);

    /**
     * 修改支付明细信息
     * 
     * @param financePayDetailInfo 支付明细信息
     * @return 结果
     */
    public int updateFinancePayDetailInfo(FinancePayDetailInfo financePayDetailInfo);

    /**
     * 删除支付明细信息
     * 
     * @param payDetailId 支付明细信息ID
     * @return 结果
     */
    public int deleteFinancePayDetailInfoById(Long payDetailId);

    /**
     * 批量删除支付明细信息
     * 
     * @param payDetailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinancePayDetailInfoByIds(Long[] payDetailIds);
}
