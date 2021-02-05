package com.paic.ehis.cs.service;

import com.paic.ehis.cs.domain.HcsModification;

import java.util.List;


/**
 * HCS预约修改 Service接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface IHcsModificationService 
{
    /**
     * 查询HCS预约修改 
     * 
     * @param workOrderNo HCS预约修改 ID
     * @return HCS预约修改 
     */
    public HcsModification selectHcsModificationById(String workOrderNo);

    /**
     * 查询HCS预约修改 列表
     * 
     * @param hcsModification HCS预约修改 
     * @return HCS预约修改 集合
     */
    public List<HcsModification> selectHcsModificationList(HcsModification hcsModification);

    /**
     * 新增HCS预约修改 
     * 
     * @param hcsModification HCS预约修改 
     * @return 结果
     */
    public int insertHcsModification(HcsModification hcsModification);

    /**
     * 修改HCS预约修改 
     * 
     * @param hcsModification HCS预约修改 
     * @return 结果
     */
    public int updateHcsModification(HcsModification hcsModification);

    /**
     * 批量删除HCS预约修改 
     * 
     * @param workOrderNos 需要删除的HCS预约修改 ID
     * @return 结果
     */
    public int deleteHcsModificationByIds(String[] workOrderNos);

    /**
     * 删除HCS预约修改 信息
     * 
     * @param workOrderNo HCS预约修改 ID
     * @return 结果
     */
    public int deleteHcsModificationById(String workOrderNo);
}
