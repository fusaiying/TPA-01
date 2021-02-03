package com.paic.ehis.cs.mapper;

import com.paic.ehis.cs.domain.WorkHandleInfo;

import java.util.List;


/**
 * 工单处理信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface WorkHandleInfoMapper 
{
    /**
     * 查询工单处理信息 
     * 
     * @param handleId 工单处理信息 ID
     * @return 工单处理信息 
     */
    public WorkHandleInfo selectWorkHandleInfoById(Long handleId);

    /**
     * 查询工单处理信息 列表
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 工单处理信息 集合
     */
    public List<WorkHandleInfo> selectWorkHandleInfoList(WorkHandleInfo workHandleInfo);

    /**
     * 新增工单处理信息 
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 结果
     */
    public int insertWorkHandleInfo(WorkHandleInfo workHandleInfo);

    /**
     * 修改工单处理信息 
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 结果
     */
    public int updateWorkHandleInfo(WorkHandleInfo workHandleInfo);

    /**
     * 删除工单处理信息 
     * 
     * @param handleId 工单处理信息 ID
     * @return 结果
     */
    public int deleteWorkHandleInfoById(Long handleId);

    /**
     * 批量删除工单处理信息 
     * 
     * @param handleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkHandleInfoByIds(Long[] handleIds);
}
