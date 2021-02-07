package com.paic.ehis.claimflow.mapper;


import com.paic.ehis.claimflow.domain.ClaimProductTaskLog;

import java.util.List;

/**
 * 产品状态轨迹 Mapper接口
 * 
 * @author sino
 * @date 2021-01-07
 */
public interface ClaimProductTaskLogMapper 
{
    /**
     * 查询产品状态轨迹 
     * 
     * @param riskLogNo 产品状态轨迹 ID
     * @return 产品状态轨迹 
     */
    public ClaimProductTaskLog selectClaimProductTaskLogById(String riskLogNo);

    /**
     * 查询产品状态轨迹 列表
     * 
     * @param claimProductTaskLog 产品状态轨迹 
     * @return 产品状态轨迹 集合
     */
    public List<ClaimProductTaskLog> selectClaimProductTaskLogList(ClaimProductTaskLog claimProductTaskLog);

    /**
     * 条件查询产品状态轨迹
     *
     * @param claimProductTaskLog 产品状态轨迹
     * @return 结果
     */
    public ClaimProductTaskLog selectClaimProductTaskLogByCondition(ClaimProductTaskLog claimProductTaskLog);

    /**
     * 新增产品状态轨迹 
     * 
     * @param claimProductTaskLog 产品状态轨迹 
     * @return 结果
     */
    public int insertClaimProductTaskLog(ClaimProductTaskLog claimProductTaskLog);

    /**
     * 修改产品状态轨迹 
     * 
     * @param claimProductTaskLog 产品状态轨迹 
     * @return 结果
     */
    public int updateClaimProductTaskLog(ClaimProductTaskLog claimProductTaskLog);


    /**
     * 删除产品状态轨迹 
     * 
     * @param riskLogNo 产品状态轨迹 ID
     * @return 结果
     */
    public int deleteClaimProductTaskLogById(String riskLogNo);

    /**
     * 批量删除产品状态轨迹 
     * 
     * @param riskLogNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimProductTaskLogByIds(String[] riskLogNos);

    public List<ClaimProductTaskLog> selectClaimProductTaskLogByMany(ClaimProductTaskLog claimProductTaskLog);

    /**
     * 任务改派:查询产品状态轨迹列表
     */
    public ClaimProductTaskLog selectClaimProductTaskLog(String riskLogNo);
}
