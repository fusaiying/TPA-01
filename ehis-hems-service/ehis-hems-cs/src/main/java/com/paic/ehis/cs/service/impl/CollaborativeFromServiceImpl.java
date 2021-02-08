package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.dto.ConsultationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.cs.mapper.CollaborativeFromMapper;
import com.paic.ehis.cs.domain.CollaborativeFrom;
import com.paic.ehis.cs.service.ICollaborativeFromService;

/**
 * 协办信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-05
 */
@Service
public class CollaborativeFromServiceImpl implements ICollaborativeFromService 
{
    @Autowired
    private CollaborativeFromMapper collaborativeFromMapper;

    /**
     * 查询协办信息 
     * 
     * @param collaborativeId 协办信息 ID
     * @return 协办信息 
     */
    @Override
    public CollaborativeFrom selectCollaborativeFromById(Long collaborativeId)
    {
        return collaborativeFromMapper.selectCollaborativeFromById(collaborativeId);
    }

    /**
     * 查询协办信息 列表
     * 
     * @param collaborativeFrom 协办信息 
     * @return 协办信息 
     */
    @Override
    public List<CollaborativeFrom> selectCollaborativeFromList(CollaborativeFrom collaborativeFrom)
    {
        return collaborativeFromMapper.selectCollaborativeFromList(collaborativeFrom);
    }

    @Override
    public List<CollaborativeFrom> selectCollaborativeFromListNew(ConsultationDTO consultationDTO) {
        return collaborativeFromMapper.selectCollaborativeFromListNew(consultationDTO);
    }

    /**
     * 新增协办信息 
     * 
     * @param collaborativeFrom 协办信息 
     * @return 结果
     */
    @Override
    public int insertCollaborativeFrom(CollaborativeFrom collaborativeFrom)
    {
        return collaborativeFromMapper.insertCollaborativeFrom(collaborativeFrom);
    }

    /**
     * 修改协办信息 
     * 
     * @param collaborativeFrom 协办信息 
     * @return 结果
     */
    @Override
    public int updateCollaborativeFrom(CollaborativeFrom collaborativeFrom)
    {
        return collaborativeFromMapper.updateCollaborativeFrom(collaborativeFrom);
    }

    /**
     * 批量删除协办信息 
     * 
     * @param collaborativeIds 需要删除的协办信息 ID
     * @return 结果
     */
    @Override
    public int deleteCollaborativeFromByIds(Long[] collaborativeIds)
    {
        return collaborativeFromMapper.deleteCollaborativeFromByIds(collaborativeIds);
    }

    /**
     * 删除协办信息 信息
     * 
     * @param collaborativeId 协办信息 ID
     * @return 结果
     */
    @Override
    public int deleteCollaborativeFromById(Long collaborativeId)
    {
        return collaborativeFromMapper.deleteCollaborativeFromById(collaborativeId);
    }

    /**
     * 增加协办信息
     * @param collaborativeFrom
     * @return
     */
    @Override
    public int insertTeamwork(CollaborativeFrom collaborativeFrom) {
//修改个人池状态为已协办




        collaborativeFrom.setWorkOrderNo(collaborativeFrom.getWorkOrderNo());//接受工单号
        //UM帐号从页面获得

        return collaborativeFromMapper.insertCollaborativeFrom(collaborativeFrom);
    }
}
