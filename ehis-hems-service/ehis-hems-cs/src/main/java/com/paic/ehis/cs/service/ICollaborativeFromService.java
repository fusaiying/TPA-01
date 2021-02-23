package com.paic.ehis.cs.service;

import java.util.List;
import com.paic.ehis.cs.domain.CollaborativeFrom;
import com.paic.ehis.cs.domain.dto.ConsultationDTO;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;

/**
 * 协办信息 Service接口
 * 
 * @author sino
 * @date 2021-02-05
 */
public interface ICollaborativeFromService 
{
    /**
     * 查询协办信息 
     * 
     * @param collaborativeId 协办信息 ID
     * @return 协办信息 
     */
    public CollaborativeFrom selectCollaborativeFromById(Long collaborativeId);

    /**
     * 查询协办信息 列表
     * 
     * @param collaborativeFrom 协办信息 
     * @return 协办信息 集合
     */
    public List<CollaborativeFrom> selectCollaborativeFromList(CollaborativeFrom collaborativeFrom);

    public List<CollaborativeFrom> selectCollaborativeFromListNew(ConsultationDTO consultationDTO);

    /**
     * 新增协办信息 
     * 
     * @param collaborativeFrom 协办信息 
     * @return 结果
     */
    public int insertCollaborativeFrom(CollaborativeFrom collaborativeFrom);

    /**
     * 修改协办信息 
     * 
     * @param collaborativeFrom 协办信息 
     * @return 结果
     */
    public int updateCollaborativeFrom(CollaborativeFrom collaborativeFrom);

    /**
     * 批量删除协办信息 
     * 
     * @param collaborativeIds 需要删除的协办信息 ID
     * @return 结果
     */
    public int deleteCollaborativeFromByIds(Long[] collaborativeIds);

    /**
     * 删除协办信息 信息
     * 
     * @param collaborativeId 协办信息 ID
     * @return 结果
     */
    public int deleteCollaborativeFromById(Long collaborativeId);

    /**
     * 增加协办信息
     * @param demandAcceptVo
     * @return
     */
    int insertTeamwork(DemandAcceptVo demandAcceptVo);

    /**
     * 征求意见投诉需求处理意见
     * @param demandAcceptVo
     * @return
     */

    int insertConsultationDemand(DemandAcceptVo demandAcceptVo);
    int insertConsultationDemandOne(ComplaintDealVo complaintDealVo);
}
