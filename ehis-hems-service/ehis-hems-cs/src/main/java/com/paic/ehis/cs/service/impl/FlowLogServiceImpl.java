package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import com.paic.ehis.cs.service.IFlowLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 业务流转信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-29
 */
@Service
public class FlowLogServiceImpl implements IFlowLogService
{
    @Autowired
    private FlowLogMapper flowLogMapper;

    /**
     * 查询业务流转信息 
     * 
     * @param flowId 业务流转信息 ID
     * @return 业务流转信息 
     */
    @Override
    public FlowLog selectFlowLogById(String flowId)
    {
        return flowLogMapper.selectFlowLogById(flowId);
    }

    /**
     * 查询业务流转信息 列表
     * 
     * @param flowLog 业务流转信息 
     * @return 业务流转信息 
     */
    @Override
    public List<FlowLog> selectFlowLogList(FlowLog flowLog)
    {
        return flowLogMapper.selectFlowLogList(flowLog);
    }

    /**
     * 新增业务流转信息 
     * 
     * @param flowLog 业务流转信息 
     * @return 结果
     */
    @Override
    public int insertFlowLog(FlowLog flowLog)
    {
        return flowLogMapper.insertFlowLog(flowLog);
    }

    /**
     * 修改业务流转信息 
     * 
     * @param flowLog 业务流转信息 
     * @return 结果
     */
    @Override
    public int updateFlowLog(FlowLog flowLog)
    {
        return flowLogMapper.updateFlowLog(flowLog);
    }

    /**
     * 批量删除业务流转信息 
     * 
     * @param flowIds 需要删除的业务流转信息 ID
     * @return 结果
     */
    @Override
    public int deleteFlowLogByIds(String[] flowIds)
    {
        return flowLogMapper.deleteFlowLogByIds(flowIds);
    }

    /**
     * 删除业务流转信息 信息
     * 
     * @param flowId 业务流转信息 ID
     * @return 结果
     */
    @Override
    public int deleteFlowLogById(String flowId)
    {
        return flowLogMapper.deleteFlowLogById(flowId);
    }
}
