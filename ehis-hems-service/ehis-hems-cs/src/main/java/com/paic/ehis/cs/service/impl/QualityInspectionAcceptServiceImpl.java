package com.paic.ehis.cs.service.impl;


import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.QualityDTO;
import com.paic.ehis.cs.domain.dto.QualityFlagDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.AcceptVo;
import com.paic.ehis.cs.domain.vo.QualityAcceptVo;
import com.paic.ehis.cs.domain.vo.QualityFlagVO;
import com.paic.ehis.cs.domain.vo.QualityVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IQualityInspectionAcceptService;
import com.paic.ehis.cs.utils.CodeEnum;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 质检受理服务实现 QualityInspectionAcceptServiceImpl
 * by Louis
 */
@Service
public class QualityInspectionAcceptServiceImpl implements IQualityInspectionAcceptService
{

    @Autowired
    private QualityInspectionAcceptMapper qualityInspectionAcceptMapper;

    @Autowired
    private FlowLogMapper flowLogMapper;

    @Autowired
    private FieldMapMapper fieldMapMapper;

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public List<AcceptVo> selectSendPoolData(WorkOrderQueryDTO workOrderQueryDTO) {
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectSendByVo(workOrderQueryDTO);
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                //被保人
                String getInsuredPersonId= list.get(i).getInsuredPersonId();
                if(StringUtils.isNotEmpty(getInsuredPersonId)){
                    PersonInfo setInsuredPerson=personInfoMapper.selectPersonInfoById(getInsuredPersonId);
                    if(setInsuredPerson!=null){
                        list.get(i).setInsuredPerson(setInsuredPerson);
                    }
                }
                //投保人
                String getHolderPersonId= list.get(i).getHolderPersonId();
                if(StringUtils.isNotEmpty(getHolderPersonId)){
                    PersonInfo setHolderPerson=personInfoMapper.selectPersonInfoById(getHolderPersonId);
                    if(setHolderPerson!=null){
                        list.get(i).setHolderPerson(setHolderPerson);
                    }
                }
                //受理人
                String getAcceptUserId= list.get(i).getAcceptUserId();
                if(StringUtils.isNotEmpty(getAcceptUserId)){
                    UserInfo setAcceptUser=userInfoMapper.selectUserInfoById(getAcceptUserId);
                    if(setAcceptUser!=null){
                        list.get(i).setAcceptUser(setAcceptUser);
                    }
                }
                //原处理人
                String getModifyUserId= list.get(i).getModifyUserId();
                if(StringUtils.isNotEmpty(getModifyUserId)){
                    UserInfo setModifyUser=userInfoMapper.selectUserInfoById(getModifyUserId);
                    if(setModifyUser!=null){
                        list.get(i).setModifyUser(setModifyUser);
                    }
                }

            }
        }
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

    @Override
    public List<AcceptVo> selectAcceptPoolData(WorkOrderQueryDTO workOrderQueryDTO) {
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectAcceptByVo(workOrderQueryDTO);
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                //被保人
                String getInsuredPersonId= list.get(i).getInsuredPersonId();
                if(StringUtils.isNotEmpty(getInsuredPersonId)){
                    PersonInfo setInsuredPerson=personInfoMapper.selectPersonInfoById(getInsuredPersonId);
                    if(setInsuredPerson!=null){
                        list.get(i).setInsuredPerson(setInsuredPerson);
                    }
                }
                //投保人
                String getHolderPersonId= list.get(i).getHolderPersonId();
                if(StringUtils.isNotEmpty(getHolderPersonId)){
                    PersonInfo setHolderPerson=personInfoMapper.selectPersonInfoById(getHolderPersonId);
                    if(setHolderPerson!=null){
                        list.get(i).setHolderPerson(setHolderPerson);
                    }
                }
                //受理人
                String getAcceptUserId= list.get(i).getAcceptUserId();
                if(StringUtils.isNotEmpty(getAcceptUserId)){
                    UserInfo setAcceptUser=userInfoMapper.selectUserInfoById(getAcceptUserId);
                    if(setAcceptUser!=null){
                        list.get(i).setAcceptUser(setAcceptUser);
                    }
                }
                //处理人
                String getModifyUserId= list.get(i).getModifyUserId();
                if(StringUtils.isNotEmpty(getModifyUserId)){
                    UserInfo setModifyUser=userInfoMapper.selectUserInfoById(getModifyUserId);
                    if(setModifyUser!=null){
                        list.get(i).setModifyUser(setModifyUser);
                    }
                }

            }
        }
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

    @Transactional
    @Override
    public int insertAcceptVoBatch(String[] ids, Map<String, String> param){
        List<QualityInspectionAccept> list=new ArrayList<>();
        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        QualityInspectionAccept qualityInspectionAccept=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            //流转记录添加
            String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20);
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);

            qualityInspectionAccept=new QualityInspectionAccept();
            qualityInspectionAccept.setWorkOrderNo(ids[i]);
            qualityInspectionAccept.setStatus(param.get("status"));
            qualityInspectionAccept.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
            qualityInspectionAccept.setCreatedTime(DateUtils.getNowDate());
            qualityInspectionAccept.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
            qualityInspectionAccept.setUpdatedTime(DateUtils.getNowDate());
            list.add(qualityInspectionAccept);
        }
        //记录操作轨迹
        flowLogMapper.insertBatch(flowLogList);
        return qualityInspectionAcceptMapper.insertAcceptBatch(list);
    }

    @Transactional
    @Override
    public int inspectionHandleStatusByIds(String[] ids, Map<String, String> param) {
        WorkOrderQueryDTO workOrderQueryDTO=new WorkOrderQueryDTO();
        List<String> idList= Arrays.asList(ids);
        workOrderQueryDTO.setAcceptStatus(param.get("status"));
        workOrderQueryDTO.setWorkOrderNoList(idList);
        workOrderQueryDTO.setUpdateBy(String.valueOf(SecurityUtils.getUsername()));
        workOrderQueryDTO.setUpdateTime(DateUtils.getNowDate());

        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            //流转记录添加
            String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20);
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
//            flowLog.setSubId(ids[i]);

            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);
        }
        //记录操作轨迹
        flowLogMapper.insertBatch(flowLogList);
        //表状态更新
        return qualityInspectionAcceptMapper.updateAcceptByVoDTO(workOrderQueryDTO);
    }

    @Override
    public List<AcceptVo> getAcceptVoByIds(String[] ids) {
        List<String> idList= Arrays.asList(ids);
        WorkOrderQueryDTO workOrderQueryDTO=new WorkOrderQueryDTO();
        workOrderQueryDTO.setWorkOrderNoList(idList);
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectAcceptByVo(workOrderQueryDTO);
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

    @Override
    public AcceptVo getOneAcceptInfo(WorkOrderQueryDTO workOrderQueryDTO) {
        //共同属性值提取
        AcceptVo acceptVo=qualityInspectionAcceptMapper.getAcceptInfo(workOrderQueryDTO);
        //差异详情提取
        AcceptDetailInfo acceptDetailInfo= qualityInspectionAcceptMapper.getAcceptDetailInfo(workOrderQueryDTO);
        String sourceName="DemandAcceptVo";
        String businessType=workOrderQueryDTO.getBusinessType();
        if(CodeEnum.BUSINESS_TYPE_02.getCode().equals(businessType)){
            sourceName="ReservationAcceptVo";
        }else if(CodeEnum.BUSINESS_TYPE_03.getCode().equals(businessType)){
            sourceName="ComplaintAcceptVo";
            //投诉人
            PersonInfo complaintPerson=personInfoMapper.selectPersonInfoById(acceptVo.getComplaintPersonId());
            if(complaintPerson!=null) {
                acceptVo.setComplaintPerson(complaintPerson);
            }
        }
        //来电人
        PersonInfo callPerson=personInfoMapper.selectPersonInfoById(acceptVo.getCallPersonId());
        if(callPerson!=null) {
            acceptVo.setCallPerson(callPerson);
        }
        //联系人
        PersonInfo contactsPerson=personInfoMapper.selectPersonInfoById(acceptVo.getContactsPersonId());
        if(contactsPerson!=null) {
            acceptVo.setContactsPerson(contactsPerson);
        }

        //差异属性转换
        List<FieldMap> fieldMapList=fieldMapMapper.selectFieldMapById(sourceName);
        Map<String,String> fieldMap=new HashMap<>();
        for (int i = 0; i < fieldMapList.size(); i++) {
            fieldMap.put(fieldMapList.get(i).getSourceFiledName(),fieldMapList.get(i).getTargetColumnName());
        }
        VoUtils voUtils=new VoUtils<AcceptVo>();
        //转换
        acceptVo= (AcceptVo) voUtils.fromVoToVo(acceptVo,fieldMap,acceptDetailInfo);
        return acceptVo;
    }

    @Override
    public List<QualityAcceptVo> selectQualityVo(QualityDTO qualityDTO) {
        return qualityInspectionAcceptMapper.selectQualityVo(qualityDTO);
    }

    @Override
    public List<QualityFlagVO> selectQualityFlagVO(QualityFlagDTO qualityFlagDTO) {
        return qualityInspectionAcceptMapper.selectQualityFlagVO(qualityFlagDTO);
    }
}

