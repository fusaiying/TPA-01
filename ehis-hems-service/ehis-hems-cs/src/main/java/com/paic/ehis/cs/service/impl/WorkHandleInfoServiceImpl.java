package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.mapper.WorkHandleInfoMapper;
import com.paic.ehis.cs.service.IWorkHandleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 工单处理信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-30
 */
@Service
public class WorkHandleInfoServiceImpl implements IWorkHandleInfoService
{
    @Autowired
    private WorkHandleInfoMapper workHandleInfoMapper;

    /**
     * 查询工单处理信息 
     * 
     * @param handleId 工单处理信息 ID
     * @return 工单处理信息 
     */
    @Override
    public WorkHandleInfo selectWorkHandleInfoById(Long handleId)
    {
        return workHandleInfoMapper.selectWorkHandleInfoById(handleId);
    }

    /**
     * 查询工单处理信息 列表
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 工单处理信息 
     */
    @Override
    public List<WorkHandleInfo> selectWorkHandleInfoList(WorkHandleInfo workHandleInfo)
    {
        return workHandleInfoMapper.selectWorkHandleInfoList(workHandleInfo);
    }

    /**
     * 新增工单处理信息 
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 结果
     */
    @Override
    public int insertWorkHandleInfo(WorkHandleInfo workHandleInfo)
    {
        return workHandleInfoMapper.insertWorkHandleInfo(workHandleInfo);
    }

    /**
     * 修改工单处理信息 
     * 
     * @param workHandleInfo 工单处理信息 
     * @return 结果
     */
    @Override
    public int updateWorkHandleInfo(WorkHandleInfo workHandleInfo)
    {
        return workHandleInfoMapper.updateWorkHandleInfo(workHandleInfo);
    }

    /**
     * 批量删除工单处理信息 
     * 
     * @param handleIds 需要删除的工单处理信息 ID
     * @return 结果
     */
    @Override
    public int deleteWorkHandleInfoByIds(Long[] handleIds)
    {
        return workHandleInfoMapper.deleteWorkHandleInfoByIds(handleIds);
    }

    /**
     * 删除工单处理信息 信息
     * 
     * @param handleId 工单处理信息 ID
     * @return 结果
     */
    @Override
    public int deleteWorkHandleInfoById(Long handleId)
    {
        return workHandleInfoMapper.deleteWorkHandleInfoById(handleId);
    }
}
