package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.HmpAuditTrack;

/**
 * 产品审核轨迹Mapper接口
 * 
 * @author sino
 * @date 2020-11-04
 */
public interface HmpAuditTrackMapper 
{
    /**
     * 查询产品审核轨迹
     * 
     * @param auditno 产品审核轨迹ID
     * @return 产品审核轨迹
     */
    public HmpAuditTrack selectHmpAuditTrackById(String auditno);

    /**
     * 查询产品审核轨迹列表
     * 
     * @param hmpAuditTrack 产品审核轨迹
     * @return 产品审核轨迹集合
     */
    public List<HmpAuditTrack> selectHmpAuditTrackList(HmpAuditTrack hmpAuditTrack);

    /**
     * 新增产品审核轨迹
     * 
     * @param hmpAuditTrack 产品审核轨迹
     * @return 结果
     */
    public int insertHmpAuditTrack(HmpAuditTrack hmpAuditTrack);

    /**
     * 修改产品审核轨迹
     * 
     * @param hmpAuditTrack 产品审核轨迹
     * @return 结果
     */
    public int updateHmpAuditTrack(HmpAuditTrack hmpAuditTrack);

    /**
     * 删除产品审核轨迹
     * 
     * @param auditno 产品审核轨迹ID
     * @return 结果
     */
    public int deleteHmpAuditTrackById(String auditno);

    /**
     * 批量删除产品审核轨迹
     * 
     * @param auditnos 需要删除的数据ID
     * @return 结果
     */
    public int deleteHmpAuditTrackByIds(String[] auditnos);
}
