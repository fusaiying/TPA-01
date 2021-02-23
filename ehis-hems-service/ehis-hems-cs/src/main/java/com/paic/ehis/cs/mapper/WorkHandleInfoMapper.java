package com.paic.ehis.cs.mapper;

import java.util.List;
import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.ServiceProcessingVo;

/**
 * 工单处理信息 Mapper接口
 * 
 * @author sino
 * @date 2021-02-02
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
     * 协办投诉处理
     * @param workHandleInfo
     * @return
     */
    public int assistInComplaint(WorkHandleInfo workHandleInfo);

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


    /**
     * 修改服务处理页面
     * @param workHandleInfo
     * @return
     */
    public int insertServiceProcessing(WorkHandleInfo workHandleInfo);

    /**
     * 服务处理已有数据时修改表中数据并将其余数据状态改为N
     * @param workHandleInfo
     * @return
     */
    public int updateServiceProcessing(WorkHandleInfo workHandleInfo);

    /**
     * 将所有数据置为N
     * @param workHandleInfo
     * @return
     */
    public int updateStatus(WorkHandleInfo workHandleInfo);



    /**
     * 根据工单号查询当前创建人
     * @param workHandleInfo
     * @return
     */
    public WorkHandleInfo selectCreatedBy(WorkHandleInfo workHandleInfo);
}
