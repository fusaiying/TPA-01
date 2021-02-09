package com.paic.ehis.cs.service;

import java.util.List;

import com.paic.ehis.cs.domain.CollaborativeFrom;
import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.ServiceProcessingVo;

/**
 * 工单处理信息 Service接口
 * 
 * @author sino
 * @date 2021-02-02
 */
public interface IWorkHandleInfoService 
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
     * 批量删除工单处理信息 
     * 
     * @param handleIds 需要删除的工单处理信息 ID
     * @return 结果
     */
    public int deleteWorkHandleInfoByIds(Long[] handleIds);

    /**
     * 删除工单处理信息 信息
     * 
     * @param handleId 工单处理信息 ID
     * @return 结果
     */
    public int deleteWorkHandleInfoById(Long handleId);

    /**
     * 修改服务处理页面
     * @param serviceProcessingVo
     * @return
     */
    public int insertServiceInfo(ServiceProcessingVo serviceProcessingVo);

    /**
     * 信息需求  协办处理页面  服务处理
     * @param serviceProcessingVo
     * @return
     */
    int teamworkProcessing(ServiceProcessingVo serviceProcessingVo);

    /**
     * 协办投诉处理
     * @param complaintDealVo
     * @return
     */
    int assistInComplaint(ComplaintDealVo complaintDealVo);
}
