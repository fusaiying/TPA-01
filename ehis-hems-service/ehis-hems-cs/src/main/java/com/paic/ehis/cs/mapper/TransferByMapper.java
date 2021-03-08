package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.TransferBy;

import java.util.List;

/**
 * 转办信息 Mapper接口
 * 
 * @author sino
 * @date 2021-02-07
 */
public interface TransferByMapper 
{
    /**
     * 查询转办信息 
     * 
     * @param transferId 转办信息 ID
     * @return 转办信息 
     */
    public TransferBy selectTransferByById(Long transferId);

    /**
     * 查询转办信息 列表
     * 
     * @param transferBy 转办信息 
     * @return 转办信息 集合
     */
    public List<TransferBy> selectTransferByList(TransferBy transferBy);

    /**
     * 新增转办信息 
     * 
     * @param transferBy 转办信息 
     * @return 结果
     */
    public int insertTransferBy(TransferBy transferBy);

    /**
     * 修改转办信息 
     * 
     * @param transferBy 转办信息 
     * @return 结果
     */
    public int updateTransferBy(TransferBy transferBy);

    /**
     * 删除转办信息 
     * 
     * @param transferId 转办信息 ID
     * @return 结果
     */
    public int deleteTransferByById(Long transferId);

    /**
     * 批量删除转办信息 
     * 
     * @param transferIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransferByByIds(Long[] transferIds);
}
