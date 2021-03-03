package com.paic.ehis.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paic.ehis.cs.domain.FieldMap;
import com.paic.ehis.cs.domain.FlowLog;
import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.domain.dto.ConsultationDTO;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ServiceProcessingVo;
import com.paic.ehis.cs.domain.vo.UmCode;
import com.paic.ehis.cs.mapper.FieldMapMapper;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import com.paic.ehis.cs.mapper.WorkHandleInfoMapper;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.cs.mapper.CollaborativeFromMapper;
import com.paic.ehis.cs.domain.CollaborativeFrom;
import com.paic.ehis.cs.service.ICollaborativeFromService;

/**
 * 协办信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-02-05
 */
@Service
public class CollaborativeFromServiceImpl implements ICollaborativeFromService 
{
    @Autowired
    private CollaborativeFromMapper collaborativeFromMapper;
    @Autowired
    private FlowLogMapper flowLogMapper;
    @Autowired
    private WorkHandleInfoMapper workHandleInfoMapper;
    @Autowired
    private FieldMapMapper fieldMapMapper;

    /**
     * 查询协办信息 
     * 
     * @param collaborativeId 协办信息 ID
     * @return 协办信息 
     */
    @Override
    public CollaborativeFrom selectCollaborativeFromById(Long collaborativeId)
    {
        return collaborativeFromMapper.selectCollaborativeFromById(collaborativeId);
    }

    /**
     * 查询协办信息 列表
     * 
     * @param collaborativeFrom 协办信息 
     * @return 协办信息 
     */
    @Override
    public List<CollaborativeFrom> selectCollaborativeFromList(CollaborativeFrom collaborativeFrom)
    {
        return collaborativeFromMapper.selectCollaborativeFromList(collaborativeFrom);
    }

    @Override
    public List<CollaborativeFrom> selectCollaborativeFromListNew(ConsultationDTO consultationDTO) {
        return collaborativeFromMapper.selectCollaborativeFromListNew(consultationDTO);
    }

    /**
     * 新增协办信息 
     * 
     * @param collaborativeFrom 协办信息 
     * @return 结果
     */
    @Override
    public int insertCollaborativeFrom(CollaborativeFrom collaborativeFrom)
    {
        return collaborativeFromMapper.insertCollaborativeFrom(collaborativeFrom);
    }

    /**
     * 修改协办信息 
     * 
     * @param collaborativeFrom 协办信息 
     * @return 结果
     */
    @Override
    public int updateCollaborativeFrom(CollaborativeFrom collaborativeFrom)
    {
        return collaborativeFromMapper.updateCollaborativeFrom(collaborativeFrom);
    }

    /**
     * 批量删除协办信息 
     * 
     * @param collaborativeIds 需要删除的协办信息 ID
     * @return 结果
     */
    @Override
    public int deleteCollaborativeFromByIds(Long[] collaborativeIds)
    {
        return collaborativeFromMapper.deleteCollaborativeFromByIds(collaborativeIds);
    }

    /**
     * 删除协办信息 信息
     * 
     * @param collaborativeId 协办信息 ID
     * @return 结果
     */
    @Override
    public int deleteCollaborativeFromById(Long collaborativeId)
    {
        return collaborativeFromMapper.deleteCollaborativeFromById(collaborativeId);
    }

    /**
     * 增加协办信息  协办按钮接口可调
     * @param demandAcceptVo
     * @return
     */
    @Override
    public void insertTeamwork(DemandAcceptVo demandAcceptVo) {
        //修改个人池状态为已协办
       // demandAcceptVoMapper.updateTeamwork(demandAcceptVo.getWorkOrderNo());
        List<UmCode> umCode1=demandAcceptVo.getUmCode();
        //往协办池加数据
        CollaborativeFrom collaborativeFrom=new CollaborativeFrom();
        for(UmCode um:umCode1){
            collaborativeFrom.setUmCode(um.getValue());
            //随机生成流水号
            collaborativeFrom.setCollaborativeId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("collaborative_id",10,6)));
            collaborativeFrom.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());//接受工单号
            //collaborativeFrom.setFromUserId(demandAcceptVo.getCallPersonId());//接收用户id
            //collaborativeFrom.setUmCode(demandAcceptVo.getUmCode());
            collaborativeFrom.setStatus("01");
            collaborativeFrom.setSolicitOpinion(demandAcceptVo.getSolicitOpinion());
            collaborativeFrom.setCreatedBy(SecurityUtils.getUsername());
            collaborativeFrom.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            collaborativeFrom.setUpdatedBy(SecurityUtils.getUsername());
            collaborativeFrom.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
             collaborativeFromMapper.insertCollaborativeFrom(collaborativeFrom);
        }

    }
    /**
     * 征求意见信息信息需求服务处理   加处理意见
     * @param demandAcceptVo
     * @return
     */
    @Override
    public int insertConsultationDemand(DemandAcceptVo demandAcceptVo) {
        FlowLog flowLog=new FlowLog();
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id",10,6));
        flowLog.setLinkCode("06");//状态置为已协办
        flowLog.setMakeBy(SecurityUtils.getUsername());
        //没有um帐号
        flowLog.setUmNum(SecurityUtils.getLoginUser().getUserId().toString());
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        flowLogMapper.insertFlowLog(flowLog);



        //征求意见处理完毕后  将协办表中状态改为02   增加处理意见
        CollaborativeFrom collaborativeFrom=new CollaborativeFrom();
        collaborativeFrom.setStatus("02");
        collaborativeFrom.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        collaborativeFrom.setCollaborativeId(demandAcceptVo.getCollaborativeId());
        collaborativeFrom.setOpinion(demandAcceptVo.getOpinion());
        collaborativeFrom.setSolicitOpinion(demandAcceptVo.getSolicitOpinion());
        collaborativeFrom.setCreatedBy(SecurityUtils.getUsername());
        collaborativeFrom.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        collaborativeFrom.setUpdatedBy(SecurityUtils.getUsername());
        collaborativeFrom.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        return collaborativeFromMapper.updateConsultationDemand(collaborativeFrom);
    }
    /**
     * 征求意见 投诉  服务处理 意见
     * @param complaintDealVo
     * @return
     */

    @Override
    public int insertConsultationDemandOne(ComplaintDealVo complaintDealVo) {
        //轨迹表插入数据
        FlowLog flowLog=new FlowLog();
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id",10,6));
        flowLog.setLinkCode("06");
        flowLog.setMakeBy(SecurityUtils.getUsername());
        //没有um帐号
        flowLog.setUmNum(SecurityUtils.getLoginUser().getUserId().toString());
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
        flowLogMapper.insertFlowLog(flowLog);


        //征求意见处理完毕后  将协办表中状态改为02   增加投诉不成立理由 投诉是否成立  处理方案  处理依据
        CollaborativeFrom collaborativeFrom=new CollaborativeFrom();
        collaborativeFrom.setStatus("02");
        collaborativeFrom.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
        collaborativeFrom.setCollaborativeId(complaintDealVo.getCollaborativeId());
        collaborativeFrom.setValidFlag(complaintDealVo.getValidFlag());
        collaborativeFrom.setNonReason(complaintDealVo.getNonReason());
        collaborativeFrom.setTreatmentBasis(complaintDealVo.getTreatmentBasis());
        collaborativeFrom.setTreatmentPlan(complaintDealVo.getTreatmentPlan());
        collaborativeFrom.setCreatedBy(SecurityUtils.getUsername());
        collaborativeFrom.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        collaborativeFrom.setUpdatedBy(SecurityUtils.getUsername());
        collaborativeFrom.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        return collaborativeFromMapper.updateConsultationDemandOne(collaborativeFrom);
    }

    /**
     * 撤销协办修改状态
     * @param collaborativeFrom
     * @return
     */
    @Override
    public int updateCollaborativeStatus(CollaborativeFrom collaborativeFrom) {
       // collaborativeFrom.getWorkOrderNo();
        return collaborativeFromMapper.updateCollaborativeStatus(collaborativeFrom);
    }
}
