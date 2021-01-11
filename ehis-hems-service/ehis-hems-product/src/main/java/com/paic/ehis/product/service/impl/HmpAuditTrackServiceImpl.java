package com.paic.ehis.product.service.impl;


import com.paic.ehis.product.domain.HmpAuditTrack;
import com.paic.ehis.product.mapper.HmpAuditTrackMapper;
import com.paic.ehis.product.service.IHmpAuditTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品审核轨迹Service业务层处理
 * 
 * @author sino
 * @date 2020-11-04
 */
@Service
public class HmpAuditTrackServiceImpl implements IHmpAuditTrackService
{
    @Autowired
    private HmpAuditTrackMapper hmpAuditTrackMapper;

    /**
     * 查询产品审核轨迹
     * 
     * @param auditno 产品审核轨迹ID
     * @return 产品审核轨迹
     */
    @Override
    public HmpAuditTrack selectHmpAuditTrackById(String auditno)
    {
        return hmpAuditTrackMapper.selectHmpAuditTrackById(auditno);
    }

    /**
     * 查询产品审核轨迹列表
     * 
     * @param hmpAuditTrack 产品审核轨迹
     * @return 产品审核轨迹
     */
    @Override
    public List<HmpAuditTrack> selectHmpAuditTrackList(HmpAuditTrack hmpAuditTrack)
    {
        return hmpAuditTrackMapper.selectHmpAuditTrackList(hmpAuditTrack);
    }

    /**
     * 新增产品审核轨迹
     * 
     * @param hmpAuditTrack 产品审核轨迹
     * @return 结果
     */
    @Override
    public int insertHmpAuditTrack(HmpAuditTrack hmpAuditTrack)
    {
        return hmpAuditTrackMapper.insertHmpAuditTrack(hmpAuditTrack);
    }

    /**
     * 修改产品审核轨迹
     * 
     * @param hmpAuditTrack 产品审核轨迹
     * @return 结果
     */
    @Override
    public int updateHmpAuditTrack(HmpAuditTrack hmpAuditTrack)
    {
        return hmpAuditTrackMapper.updateHmpAuditTrack(hmpAuditTrack);
    }

    /**
     * 批量删除产品审核轨迹
     * 
     * @param auditnos 需要删除的产品审核轨迹ID
     * @return 结果
     */
    @Override
    public int deleteHmpAuditTrackByIds(String[] auditnos)
    {
        return hmpAuditTrackMapper.deleteHmpAuditTrackByIds(auditnos);
    }

    /**
     * 删除产品审核轨迹信息
     * 
     * @param auditno 产品审核轨迹ID
     * @return 结果
     */
    @Override
    public int deleteHmpAuditTrackById(String auditno)
    {
        return hmpAuditTrackMapper.deleteHmpAuditTrackById(auditno);
    }

    /**
     * 查询复核完成产品最近审核记录
     *
     * @param productNo 产品编号
     * @return 结果
     */
    @Override
    public HmpAuditTrack getProductExamineResult(String productNo) {
        return hmpAuditTrackMapper.getProductExamineResultLate(productNo);
    }

}
