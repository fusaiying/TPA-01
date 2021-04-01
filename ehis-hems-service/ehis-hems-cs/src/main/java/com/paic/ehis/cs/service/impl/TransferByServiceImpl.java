package com.paic.ehis.cs.service.impl;

import java.util.List;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.TransferBy;
import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.UmCode;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import com.paic.ehis.cs.mapper.TransferByMapper;
import com.paic.ehis.cs.mapper.WorkHandleInfoMapper;
import com.paic.ehis.cs.mapper.WorkOrderAcceptMapper;
import com.paic.ehis.cs.service.ITransferByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 转办信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-07
 */
@Service
public class TransferByServiceImpl implements ITransferByService
{
    @Autowired
    private TransferByMapper transferByMapper;

    @Autowired
    private WorkOrderAcceptMapper workOrderAcceptMapper;

    @Autowired
    private WorkHandleInfoMapper workHandleInfoMapper;

    @Autowired
    private FlowLogMapper flowLogMapper;

    /**
     * 查询转办信息 
     * 
     * @param transferId 转办信息 ID
     * @return 转办信息 
     */
    @Override
    public TransferBy selectTransferByById(Long transferId)
    {
        return transferByMapper.selectTransferByById(transferId);
    }

    /**
     * 查询转办信息 列表
     * 
     * @param transferBy 转办信息 
     * @return 转办信息 
     */
    @Override
    public List<TransferBy> selectTransferByList(TransferBy transferBy)
    {
        return transferByMapper.selectTransferByList(transferBy);
    }

    /**
     * 新增转办信息 
     * 
     * @param demandAcceptVo 转办信息
     * @return 结果
     */
    @Override
    public void insertTransferBy(DemandAcceptVo demandAcceptVo)
    {
        List<UmCode> list=demandAcceptVo.getUmCode();
        TransferBy transferBy=new TransferBy();
        //获得工单号
        transferBy.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        //随机生成流水号
        transferBy.setTransferId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("transfer_id",10,6)));
        transferBy.setUmCode(list.get(0).getValue());
        transferBy.setReason(demandAcceptVo.getReason());
        transferBy.setStatus("Y");
        transferBy.setCreatedBy(SecurityUtils.getUsername());
        transferBy.setCreatedTime(DateUtils.getNowDate());
        transferBy.setUpdatedBy(SecurityUtils.getUsername());
        transferBy.setUpdatedTime(DateUtils.getNowDate());
        transferByMapper.insertTransferBy(transferBy);
        WorkOrderAccept workOrderAccept=workOrderAcceptMapper.selectWorkOrderAcceptById(demandAcceptVo.getWorkOrderNo());
        workOrderAccept.setUpdateBy(list.get(0).getValue());
        workOrderAccept.setUpdateTime(DateUtils.getNowDate());
        //转办后更新处理人信息（因为没有正式保存服务信息  所以受理时间不变）
        workOrderAccept.setModifyBy(list.get(0).getValue());
        workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);

        //清除原服务处理信息
        WorkHandleInfo workHandleInfo=workHandleInfoMapper.selectWorkHandleInfoByNo(demandAcceptVo.getWorkOrderNo());//获取handleId
        if(workHandleInfo != null && workHandleInfo.getHandleId() != null && workHandleInfo.getHandleId()!=0){
            workHandleInfoMapper.deleteWorkHandleInfoById(workHandleInfo.getHandleId());
        }

        for (int i=1;i< list.size();i++){
            //获得工单号
            transferBy.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
            //随机生成流水号
            transferBy.setTransferId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("transfer_id",10,6)));
            transferBy.setUmCode(list.get(i).getValue());
            transferBy.setStatus("T");
            transferBy.setReason(demandAcceptVo.getReason());
            transferBy.setCreatedBy(SecurityUtils.getUsername());
            transferBy.setCreatedTime(DateUtils.getNowDate());
            transferBy.setUpdatedBy(SecurityUtils.getUsername());
            transferBy.setUpdatedTime(DateUtils.getNowDate());
            transferByMapper.insertTransferBy(transferBy);
        }

        FlowLog flowLog=new FlowLog();
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20,20));
        flowLog.setToDepartment("研发部门");
        flowLog.setToReason(demandAcceptVo.getReason());
        flowLog.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeBy(SecurityUtils.getUsername());
        flowLog.setLinkCode(workOrderAccept.getStatus());
        flowLog.setToReason(demandAcceptVo.getReason());
        flowLog.setOperateCode("10");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLogMapper.insertFlowLog(flowLog);
    }

    /**
     * 修改转办信息 
     * 
     * @param transferBy 转办信息 
     * @return 结果
     */
    @Override
    public int updateTransferBy(TransferBy transferBy)
    {
        return transferByMapper.updateTransferBy(transferBy);
    }

    /**
     * 批量删除转办信息 
     * 
     * @param transferIds 需要删除的转办信息 ID
     * @return 结果
     */
    @Override
    public int deleteTransferByByIds(Long[] transferIds)
    {
        return transferByMapper.deleteTransferByByIds(transferIds);
    }

    /**
     * 删除转办信息 信息
     * 
     * @param transferId 转办信息 ID
     * @return 结果
     */
    @Override
    public int deleteTransferByById(Long transferId)
    {
        return transferByMapper.deleteTransferByById(transferId);
    }
}
