package com.paic.ehis.claimcal.service;



import com.paic.ehis.claimcal.domain.ClaimProductTaskLog;
import com.paic.ehis.claimcal.domain.ClaimProductTaskLogInfo;

import java.util.List;

/**
 * 产品状态轨迹 Service接口
 * 
 * @author sino
 * @date 2021-01-07
 */
public interface IClaimProductTaskLogService 
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
     * 新增产品状态轨迹 
     * 
     * @param claimProductTaskLogInfo 产品状态轨迹
     * @return 结果
     */
    public int insertClaimProductTaskLog(ClaimProductTaskLogInfo claimProductTaskLogInfo);

    /**
     * 检查产品下的计划 保障区域
     *
     * @param riskCode 产品 ID
     * @return 产品状态轨迹
     */
    public int checkClaimProductById(String riskCode);

    /**
     * 修改产品状态轨迹 
     * 
     * @param claimProductTaskLog 产品状态轨迹 
     * @return 结果
     */
    public int updateClaimProductTaskLog(ClaimProductTaskLog claimProductTaskLog);

    /**
     * 批量删除产品状态轨迹 
     * 
     * @param riskLogNos 需要删除的产品状态轨迹 ID
     * @return 结果
     */
    public int deleteClaimProductTaskLogByIds(String[] riskLogNos);

    /**
     * 删除产品状态轨迹 信息
     * 
     * @param riskLogNo 产品状态轨迹 ID
     * @return 结果
     */
    public int deleteClaimProductTaskLogById(String riskLogNo);
}
