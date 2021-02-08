package com.paic.ehis.cs.mapper;

import java.util.List;
import com.paic.ehis.cs.domain.CollaborativeFrom;
import com.paic.ehis.cs.domain.dto.ConsultationDTO;

/**
 * 协办信息 Mapper接口
 * 
 * @author sino
 * @date 2021-02-05
 */
public interface CollaborativeFromMapper 
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
     * 删除协办信息 
     * 
     * @param collaborativeId 协办信息 ID
     * @return 结果
     */
    public int deleteCollaborativeFromById(Long collaborativeId);

    /**
     * 批量删除协办信息 
     * 
     * @param collaborativeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCollaborativeFromByIds(Long[] collaborativeIds);
}
