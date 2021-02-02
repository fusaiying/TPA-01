package com.paic.ehis.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.FieldMap;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ServiceProcessingVo;
import com.paic.ehis.cs.mapper.FieldMapMapper;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.cs.mapper.WorkHandleInfoMapper;
import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.service.IWorkHandleInfoService;

/**
 * 工单处理信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-02
 */
@Service
public class WorkHandleInfoServiceImpl implements IWorkHandleInfoService 
{
    @Autowired
    private WorkHandleInfoMapper workHandleInfoMapper;
    @Autowired
    private FieldMapMapper fieldMapMapper;

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
    /**
     * 修改服务处理页面
     * @param serviceProcessingVo
     * @return
     */
    @Override
    public int insertServiceInfo(ServiceProcessingVo serviceProcessingVo) {
        WorkHandleInfo workHandleInfo=new WorkHandleInfo();
        workHandleInfo.setWorkOrderNo(serviceProcessingVo.getWorkOrderNo());
        workHandleInfo.setHandleId(serviceProcessingVo.getHandleId());
        workHandleInfo.setFlag(serviceProcessingVo.getFlag());
        workHandleInfo.setStatus(serviceProcessingVo.getStatus());
        workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
        workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
        workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        workHandleInfo.setHandleType(serviceProcessingVo.getHandleType());
        String sourceName="ServiceProcessingVo";
        String targetTableName="work_handle_info";
        //ServiceProcessingVo serviceProcessingVo=new ServiceProcessingVo();
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<DemandAcceptVo>();
            serviceProcessingVo= (ServiceProcessingVo) voUtils.fromVoToVo(serviceProcessingVo,map,workHandleInfo);
        }

        return workHandleInfoMapper.insertServiceProcessing(workHandleInfo);
    }
}
