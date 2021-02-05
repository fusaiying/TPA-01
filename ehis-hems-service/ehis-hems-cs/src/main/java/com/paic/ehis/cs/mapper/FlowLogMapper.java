package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.FlowLog;

import java.util.List;


/**
 * 业务流转信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-29
 */
public interface FlowLogMapper 
{
    /**
     * 查询业务流转信息 
     * 
     * @param flowId 业务流转信息 ID
     * @return 业务流转信息 
     */
    public FlowLog selectFlowLogById(String flowId);

    /**
     * 查询业务流转信息 列表
     * 
     * @param flowLog 业务流转信息 
     * @return 业务流转信息 集合
     */
    public List<FlowLog> selectFlowLogList(FlowLog flowLog);

    /**
     * 新增业务流转信息 
     * 
     * @param flowLog 业务流转信息 
     * @return 结果
     */
    public int insertFlowLog(FlowLog flowLog);

    /**
     * 修改业务流转信息 
     * 
     * @param flowLog 业务流转信息 
     * @return 结果
     */
    public int updateFlowLog(FlowLog flowLog);

    /**
     * 删除业务流转信息 
     * 
     * @param flowId 业务流转信息 ID
     * @return 结果
     */
    public int deleteFlowLogById(String flowId);

    /**
     * 批量删除业务流转信息 
     * 
     * @param flowIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFlowLogByIds(String[] flowIds);
}
