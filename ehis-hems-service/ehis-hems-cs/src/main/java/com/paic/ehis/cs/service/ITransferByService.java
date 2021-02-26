package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.TransferBy;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;

import java.util.List;


/**
 * 转办信息 Service接口
 * 
 * @author sino
 * @date 2021-02-07
 */
public interface ITransferByService 
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
     * @param demandAcceptVo 转办信息
     * @return 结果
     */
    public void insertTransferBy(DemandAcceptVo demandAcceptVo);

    /**
     * 修改转办信息 
     * 
     * @param transferBy 转办信息
     * @return 结果
     */
    public int updateTransferBy(TransferBy transferBy);

    /**
     * 批量删除转办信息 
     * 
     * @param transferIds 需要删除的转办信息 ID
     * @return 结果
     */
    public int deleteTransferByByIds(Long[] transferIds);

    /**
     * 删除转办信息 信息
     * 
     * @param transferId 转办信息 ID
     * @return 结果
     */
    public int deleteTransferByById(Long transferId);
}
