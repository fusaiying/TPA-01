package com.paic.ehis.cs.service.impl;



import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IDemandAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class DemandAcceptVoServiceimpl implements IDemandAcceptVoService {
    @Autowired
    private DemandAcceptVoMapper demandAcceptVoMapper;
    @Autowired
    private PersonInfoMapper personInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private FieldMapMapper fieldMapMapper;
    @Autowired
    private AcceptDetailInfoMapper acceptDetailInfoMapper;
    @Autowired
    private WorkOrderAcceptMapper workOrderAcceptMapper;


    @Override
    public List<DemandAcceptVo> selectDemandAcceptList(AcceptDTO acceptDTO) {
        List<DemandAcceptVo> demandAcceptVos= demandAcceptVoMapper.selectDemandAcceptVoList(acceptDTO);
        String sourceName="DemandAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        for (DemandAcceptVo demandAcceptVo1:demandAcceptVos){
            demandAcceptVo1.setCallPerson(personInfoMapper.selectPersonInfoById(demandAcceptVo1.getCallPersonId()));
            demandAcceptVo1.setContactsPerson(personInfoMapper.selectPersonInfoById(demandAcceptVo1.getContactsPersonId()));
            demandAcceptVo1.setOperatorLast(userInfoMapper.selectUserInfoById(demandAcceptVo1.getCreateBy()));
            demandAcceptVo1.setReviser(userInfoMapper.selectUserInfoById(demandAcceptVo1.getUpdateBy()));
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(demandAcceptVo1.getWorkOrderNo());
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getSourceFiledName(),fieldMap.getTargetColumnName());
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                demandAcceptVo1= (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo1,map,acceptDetailInfo);
           /* Iterator<String> iter = KVMap.keySet().iterator();
            while(iter.hasNext()){
                String key=iter.next();
                String value = KVMap.get(key);
                Map fieldMap =new HashMap<String,String>();
                fieldMap.put(key,value);
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                demandAcceptVo1= (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo1,KVMap,acceptDetailInfo);*/
            }
        }
        return demandAcceptVos;
    }

    @Override
    public List<DemandAcceptVo> selectDemandAcceptList2(AcceptDTO acceptDTO) {
        List<DemandAcceptVo> demandAcceptVos= demandAcceptVoMapper.selectDemandAcceptVoList2(acceptDTO);
        String sourceName="DemandAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        for (DemandAcceptVo demandAcceptVo1:demandAcceptVos){
            demandAcceptVo1.setCallPerson(personInfoMapper.selectPersonInfoById(demandAcceptVo1.getCallPersonId()));
            demandAcceptVo1.setContactsPerson(personInfoMapper.selectPersonInfoById(demandAcceptVo1.getContactsPersonId()));
            demandAcceptVo1.setOperatorLast(userInfoMapper.selectUserInfoById(demandAcceptVo1.getCreateBy()));
            demandAcceptVo1.setReviser(userInfoMapper.selectUserInfoById(demandAcceptVo1.getUpdateBy()));
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(demandAcceptVo1.getWorkOrderNo());
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getSourceFiledName(),fieldMap.getTargetColumnName());
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                demandAcceptVo1= (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo1,map,acceptDetailInfo);
            }
        }
        return demandAcceptVos;
    }

    @Override
    public int updateStatus(String workOrderNo) {
        String modifyBy=String.valueOf(SecurityUtils.getLoginUser().getUserId());
        return demandAcceptVoMapper.updateStatus(workOrderNo);
    }

    @Override
    public int updateStatusM(String[] workOrderNos) {
        return demandAcceptVoMapper.updateStatusM(workOrderNos);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int insertServiceInfo(DemandAcceptVo demandAcceptVo) {
       AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
       PersonInfo personInfo1=new PersonInfo();
       PersonInfo personInfo2=new PersonInfo();
       FlowLog flowLog=new FlowLog();
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        //工单表插入
        workOrderAccept.setStatus("01");
        workOrderAccept.setCreateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        workOrderAccept.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setBusinessType(demandAcceptVo.getBusinessType());
        demandAcceptVoMapper.insertWorkOrderAccept(workOrderAccept);

//        try {ReflectUtils
//            ComUtil.copyProperties(demandAcceptVo,acceptDetailInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        acceptDetailInfo.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        acceptDetailInfo.setChannelCode(demandAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(demandAcceptVo.getItemCode());
        acceptDetailInfo.setCallPersonId(demandAcceptVo.getCallPersonId());
        acceptDetailInfo.setCallRelationBy(demandAcceptVo.getCallRelationBy());
        acceptDetailInfo.setPriorityLevel(demandAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(demandAcceptVo.getContactsPersonId());
        acceptDetailInfo.setContactsRelationBy(demandAcceptVo.getContactsRelationBy());
        acceptDetailInfo.setEmail(demandAcceptVo.getEmail());
        acceptDetailInfo.setContent(demandAcceptVo.getContent());
        acceptDetailInfo.setStatus(demandAcceptVo.getStatus());
        acceptDetailInfo.setCreateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap("accept_detail_info","DemandAcceptVo");
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<DemandAcceptVo>();
            acceptDetailInfo= (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo,map,demandAcceptVo);
        }
        //详细表插入
        demandAcceptVoMapper.insertAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
         personInfo1.setPersonId(demandAcceptVo.getCallPersonId());
         personInfo1.setName(demandAcceptVo.getCallName());
         personInfo1.setMobilePhone(demandAcceptVo.getCallMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        demandAcceptVoMapper.insertPersonInfo(personInfo1);
         //插入联系人
        personInfo2.setPersonId(demandAcceptVo.getContactsPersonId());
        personInfo2.setSex(demandAcceptVo.getContactsSex());
        personInfo2.setName(demandAcceptVo.getContactsName());
        personInfo2.setLanguage(demandAcceptVo.getContactsLanguage());
        personInfo2.setMobilePhone(demandAcceptVo.getContactsMobilePhone());
        personInfo2.setLinePhone(demandAcceptVo.getContactsCountry()+"-"+demandAcceptVo.getContactsQuhao()+"-"+demandAcceptVo.getContactsNumber()+"-"+demandAcceptVo.getContactsSecondNumber());
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        demandAcceptVoMapper.insertPersonInfo(personInfo2);

        //轨迹表插入
        flowLog.setFlowId("00000000000000000"+PubFun.createMySqlMaxNoUseCache("cs_flow_id",10,3));
        flowLog.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("01");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
//        demandAcceptVoMapper.insertFlowLog(flowLog);
        return  demandAcceptVoMapper.insertFlowLog(flowLog);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int updateServiceInfo(DemandAcceptVo demandAcceptVo) {
        AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
        PersonInfo personInfo1=new PersonInfo();
        PersonInfo personInfo2=new PersonInfo();
        FlowLog flowLog=new FlowLog();
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        //工单表修改
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//      demandAcceptVoMapper.insertWorkOrderAccept(workOrderAccept);
        workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
//        try {ReflectUtils
//            ComUtil.copyProperties(demandAcceptVo,acceptDetailInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//      acceptDetailInfo.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
//      acceptDetailInfo.setChannelCode(demandAcceptVo.getChannelCode());
//      acceptDetailInfo.setItemCode(demandAcceptVo.getItemCode());
/*        acceptDetailInfo.setCallPersonId(demandAcceptVo.getCallPersonId());
        acceptDetailInfo.setCallRelationBy(demandAcceptVo.getCallRelationBy());
        acceptDetailInfo.setPriorityLevel(demandAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(demandAcceptVo.getContactsPersonId());
        acceptDetailInfo.setContactsRelationBy(demandAcceptVo.getContactsRelationBy());
        acceptDetailInfo.setEmail(demandAcceptVo.getEmail());
        acceptDetailInfo.setContent(demandAcceptVo.getContent());
        acceptDetailInfo.setStatus(demandAcceptVo.getStatus());
        acceptDetailInfo.setCreateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));*/
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap("accept_detail_info","DemandAcceptVo");
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<DemandAcceptVo>();
            acceptDetailInfo= (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo,map,demandAcceptVo);
        }
        //详细表插入
//        demandAcceptVoMapper.insertAcceptDetailInfo(acceptDetailInfo);
        acceptDetailInfoMapper.updateAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
        personInfo1.setPersonId(demandAcceptVo.getCallPersonId());
        personInfo1.setName(demandAcceptVo.getCallName());
        personInfo1.setMobilePhone(demandAcceptVo.getCallMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        demandAcceptVoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(demandAcceptVo.getContactsPersonId());
        personInfo2.setSex(demandAcceptVo.getContactsSex());
        personInfo2.setName(demandAcceptVo.getContactsName());
        personInfo2.setLanguage(demandAcceptVo.getContactsLanguage());
        personInfo2.setMobilePhone(demandAcceptVo.getContactsMobilePhone());
        personInfo2.setLinePhone(demandAcceptVo.getContactsCountry()+"-"+demandAcceptVo.getContactsQuhao()+"-"+demandAcceptVo.getContactsNumber()+"-"+demandAcceptVo.getContactsSecondNumber());
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        demandAcceptVoMapper.insertPersonInfo(personInfo2);

        //轨迹表插入
        flowLog.setFlowId("00000000000000000"+PubFun.createMySqlMaxNoUseCache("cs_flow_id",10,3));
        flowLog.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("01");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
//        demandAcceptVoMapper.insertFlowLog(flowLog);
        return  demandAcceptVoMapper.insertFlowLog(flowLog);
    }


}
