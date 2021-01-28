package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.HcsModification;

import java.util.List;


/**
 * HCS预约修改 Mapper接口
 * 
 * @author sino
 * @date 2021-01-21
 */
public interface HcsModificationMapper 
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
     * 删除HCS预约修改 
     * 
     * @param workOrderNo HCS预约修改 ID
     * @return 结果
     */
    public int deleteHcsModificationById(String workOrderNo);

    /**
     * 批量删除HCS预约修改 
     * 
     * @param workOrderNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteHcsModificationByIds(String[] workOrderNos);
}
