package com.paic.ehis.cs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IReservationAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;
import java.util.*;


@Service
public class ReservationAcceptVoServiceImpl implements IReservationAcceptVoService {

    @Autowired
    private ReservationAcceptVoMapper reservationAcceptVoMapper;
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
    @Autowired
    private FlowLogMapper flowLogMapper;
    @Autowired
    private EditDetailMapper editDetailMapper;
    @Autowired
    private EditInfoMapper editInfoMapper;

    @Override
    public List<ReservationAcceptVo> selectReservationAcceptVoList(AcceptDTO acceptDTO) {
        List<ReservationAcceptVo> reservationAcceptVos= reservationAcceptVoMapper.selectReservationAcceptVoList(acceptDTO);
        String sourceName="ReservationAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        for (ReservationAcceptVo reservationAcceptVo:reservationAcceptVos){
            reservationAcceptVo.setCallPerson(personInfoMapper.selectPersonInfoById(reservationAcceptVo.getCallPersonId()));
            reservationAcceptVo.setContactsPerson(personInfoMapper.selectPersonInfoById(reservationAcceptVo.getContactsPersonId()));
            reservationAcceptVo.setOperatorLast(userInfoMapper.selectUserInfoById(reservationAcceptVo.getCreateBy()));
            reservationAcceptVo.setReviser(userInfoMapper.selectUserInfoById(reservationAcceptVo.getUpdateBy()));
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(reservationAcceptVo.getWorkOrderNo());
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getSourceFiledName(),fieldMap.getTargetColumnName());
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                reservationAcceptVo= (ReservationAcceptVo) voUtils.fromVoToVo(reservationAcceptVo,map,acceptDetailInfo);
            }
        }
        return reservationAcceptVos;
    }

    @Override
    public List<ReservationAcceptVo> selectReservationAcceptVoList2(AcceptDTO acceptDTO) {
        List<ReservationAcceptVo> reservationAcceptVos= reservationAcceptVoMapper.selectReservationAcceptVoList2(acceptDTO);
        String sourceName="ReservationAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        for (ReservationAcceptVo reservationAcceptVo:reservationAcceptVos){
            reservationAcceptVo.setCallPerson(personInfoMapper.selectPersonInfoById(reservationAcceptVo.getCallPersonId()));
            reservationAcceptVo.setContactsPerson(personInfoMapper.selectPersonInfoById(reservationAcceptVo.getContactsPersonId()));
            reservationAcceptVo.setOperatorLast(userInfoMapper.selectUserInfoById(reservationAcceptVo.getCreateBy()));
            reservationAcceptVo.setReviser(userInfoMapper.selectUserInfoById(reservationAcceptVo.getUpdateBy()));
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(reservationAcceptVo.getWorkOrderNo());
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getSourceFiledName(),fieldMap.getTargetColumnName());
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                reservationAcceptVo= (ReservationAcceptVo) voUtils.fromVoToVo(reservationAcceptVo,map,acceptDetailInfo);
            }
        }
        return reservationAcceptVos;
    }

    @Override
    public int updateStatusM(String[] workOrderNos) {
        reservationAcceptVoMapper.updateStatusM(workOrderNos);
        int count;
        for ( count=0;count< workOrderNos.length;count++) {
           // WorkOrderAccept workOrderAccept = workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNos[count]);
            FlowLog flowLog=new FlowLog();
            flowLog.setWorkOrderNo(workOrderNos[count]);
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id",10,10));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setCreatedBy(SecurityUtils.getUsername());
            flowLog.setMakeBy(SecurityUtils.getUsername());
            flowLog.setMakeTime(DateUtils.getNowDate());
            flowLogMapper.insertFlowLog(flowLog);
        }
        return count;
        }


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int insertServiceInfo(ReservationAcceptVo reservationAcceptVo) {
        reservationAcceptVo.setContactsPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id",10,6));
        reservationAcceptVo.setCallPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id",10,6));
        reservationAcceptVo.setBusinessType("预约");
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
        workOrderAccept.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        workOrderAccept.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setBusinessType(reservationAcceptVo.getBusinessType());
        workOrderAcceptMapper.insertWorkOrderAccept(workOrderAccept);

        //详细表插入
        acceptDetailInfo.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        acceptDetailInfo.setChannelCode(reservationAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(reservationAcceptVo.getItemCode());
        acceptDetailInfo.setCallPersonId(reservationAcceptVo.getCallPersonId());
        acceptDetailInfo.setCallRelationBy(reservationAcceptVo.getCallRelationBy());
        acceptDetailInfo.setPriorityLevel(reservationAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(reservationAcceptVo.getContactsPersonId());
        acceptDetailInfo.setContactsRelationBy(reservationAcceptVo.getContactsRelationBy());
        acceptDetailInfo.setEmail(reservationAcceptVo.getEmail());
        acceptDetailInfo.setContent(reservationAcceptVo.getContent());
        acceptDetailInfo.setStatus(reservationAcceptVo.getStatus());
        acceptDetailInfo.setCreateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        acceptDetailInfo.setCallCenterId(reservationAcceptVo.getCallCenterId());
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap("accept_detail_info","DemandAcceptVo");
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<DemandAcceptVo>();
            acceptDetailInfo= (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo,map,reservationAcceptVo);
        }
        //详细表插入
  //    demandAcceptVoMapper.insertAcceptDetailInfo(acceptDetailInfo);
        acceptDetailInfoMapper.insertAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
        personInfo1.setPersonId(reservationAcceptVo.getCallPersonId());
        personInfo1.setName(reservationAcceptVo.getCallPerson().getName());
        personInfo1.setMobilePhone(reservationAcceptVo.getCallPerson().getMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(reservationAcceptVo.getContactsPersonId());
        personInfo2.setSex(reservationAcceptVo.getContactsPerson().getSex());
        personInfo2.setName(reservationAcceptVo.getContactsPerson().getName());
        personInfo2.setLanguage(reservationAcceptVo.getContactsPerson().getLanguage());
        personInfo2.setMobilePhone(reservationAcceptVo.getContactsPerson().getMobilePhone());
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.insertPersonInfo(personInfo2);

        //轨迹表插入
        flowLog.setFlowId("00000000000000000"+PubFun.createMySqlMaxNoUseCache("cs_flow_id",10,3));
        flowLog.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("06");
        flowLog.setLinkCode("01");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        return  flowLogMapper.insertFlowLog(flowLog);
    }



    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int updateReservationAcceptVo(ReservationAcceptVo reservationAcceptVo) {
        String workOrderNo=reservationAcceptVo.getWorkOrderNo();
        AcceptDTO acceptDTO=new AcceptDTO();
        acceptDTO.setWorkOrderNo(workOrderNo);
        List<ReservationAcceptVo> reservationAcceptVos=reservationAcceptVoMapper.selectReservationAcceptVoList(acceptDTO);
        ReservationAcceptVo reservationAcceptVo1=reservationAcceptVos.get(0);
        PersonInfo callPerson= personInfoMapper.selectPersonInfoById(reservationAcceptVo.getCallPersonId());
        PersonInfo contactsPerson=personInfoMapper.selectPersonInfoById(reservationAcceptVo.getContactsPersonId());
        PersonInfo personInfo1=new PersonInfo();
        PersonInfo personInfo2=new PersonInfo();
        FlowLog flowLog=new FlowLog();

        //工单表修改
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);

        AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap("accept_detail_info","ReservationAcceptVo");
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<ReservationAcceptVo>();
            acceptDetailInfo= (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo,map,reservationAcceptVo);
        }
        acceptDetailInfoMapper.updateAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
        personInfo1.setPersonId(reservationAcceptVo.getCallPersonId());
        //      personInfo1.setName(complaintAcceptVo.getCallPerson().getName());
        //       personInfo1.setMobilePhone(complaintAcceptVo.getCallPerson().getMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(reservationAcceptVo.getContactsPersonId());
        /*personInfo2.setSex(reservationAcceptVo.getContactsPerson().getSex());
        personInfo2.setName(reservationAcceptVo.getContactsName());
        personInfo2.setLanguage(demandAcceptVo.getContactsLanguage());
        personInfo2.setMobilePhone(demandAcceptVo.getContactsMobilePhone());
        personInfo2.setLinePhone(demandAcceptVo.getContactsCountry()+"-"+demandAcceptVo.getContactsQuhao()+"-"+demandAcceptVo.getContactsNumber()+"-"+demandAcceptVo.getContactsSecondNumber());*/
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(personInfo2);

        //轨迹表插入
        flowLog.setFlowId("00000000000000000"+PubFun.createMySqlMaxNoUseCache("cs_flow_id",10,3));
        flowLog.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("01");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
//        demandAcceptVoMapper.insertFlowLog(flowLog);

//        AcceptDetailInfo acceptDetailInfo2= acceptDetailInfoMapper.selectAcceptDetailInfoById(workOrderNo);
//        WorkOrderAccept workOrderAccept2=workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);


        Map map1 = JSONObject.parseObject(JSONObject.toJSONString(reservationAcceptVo1), Map.class);
        Map map2 = JSONObject.parseObject(JSONObject.toJSONString(reservationAcceptVo), Map.class);

        //     Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(acceptDetailInfo1));

        List<String> keyList=new ArrayList<>();
        Iterator<String> iter1 = map1.keySet().iterator();
        while(iter1.hasNext()){
            EditDetail editDetail=new EditDetail();
            EditInfo editInfo=new EditInfo();
            String map1key=iter1.next();
            String map1value = String.valueOf(map1.get(map1key));
            String map2value = String.valueOf(map2.get(map1key));
            if (!map1value.equals(map2value)) {
                keyList.add(map1key);
                editDetail.setKeyDictType("reservationAcceptVo");
                editDetail.setItemKey(map1key);
                editDetail.setOldValue(map1value);
                editDetail.setNowValue(map2value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setEditId(PubFun.createMySqlMaxNoUseCache("cs_edit_id",10,8));
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetailMapper.insertEditDetail(editDetail);
                editInfo.setEditId(Long.valueOf(editDetail.getEditId()));
                editInfo.setWorkOrderId(workOrderNo);
                editInfo.setCreatedBy(SecurityUtils.getUsername());
                editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editInfo.setUpdatedBy(SecurityUtils.getUsername());
                editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
               /* editInfo.setEditRemark(reservationAcceptVo.getEditInfo().getEditRemark());
                editInfo.setEditReason(reservationAcceptVo.getEditInfo().getEditReason());*/
                editInfoMapper.insertEditInfo(editInfo);

            }

        }

        Map map3 = JSONObject.parseObject(JSONObject.toJSONString(callPerson), Map.class);
        Map map4 = JSONObject.parseObject(JSONObject.toJSONString(personInfo1), Map.class);

        //     Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(acceptDetailInfo1));


        Iterator<String> iter2 = map3.keySet().iterator();
        while(iter2.hasNext()){
            EditDetail editDetail=new EditDetail();
            EditInfo editInfo=new EditInfo();
            String map3key=iter2.next();
            String map3value = String.valueOf(map3.get(map3key));
            String map4value = String.valueOf(map4.get(map3key));
            if (!map3value.equals(map4value)) {
                keyList.add(map3key);
                editDetail.setKeyDictType("complaintAcceptVo");
                editDetail.setItemKey(map3key);
                editDetail.setOldValue(map3value);
                editDetail.setNowValue(map4value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setEditId(PubFun.createMySqlMaxNoUseCache("cs_edit_id",10,8));
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetailMapper.insertEditDetail(editDetail);
                editInfo.setEditId(Long.valueOf(editDetail.getEditId()));
                editInfo.setWorkOrderId(workOrderNo);
                editInfo.setCreatedBy(SecurityUtils.getUsername());
                editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editInfo.setUpdatedBy(SecurityUtils.getUsername());
                editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
               /* editInfo.setEditRemark(complaintAcceptVo.getEditInfo().getEditRemark());
                editInfo.setEditReason(complaintAcceptVo.getEditInfo().getEditReason());*/
                editInfoMapper.insertEditInfo(editInfo);

            }

        }

        Map map5 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson), Map.class);
        Map map6 = JSONObject.parseObject(JSONObject.toJSONString(personInfo2), Map.class);

        Iterator<String> iter3 = map5.keySet().iterator();
        while(iter3.hasNext()){
            EditDetail editDetail=new EditDetail();
            EditInfo editInfo=new EditInfo();
            String map5key=iter3.next();
            String map5value = String.valueOf(map5.get(map5key));
            String map6value = String.valueOf(map6.get(map5key));
            if (!map5value.equals(map6value)) {
                keyList.add(map5key);
                editDetail.setKeyDictType("complaintAcceptVo");
                editDetail.setItemKey(map5key);
                editDetail.setOldValue(map5value);
                editDetail.setNowValue(map6value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setEditId(PubFun.createMySqlMaxNoUseCache("cs_edit_id",10,8));
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetailMapper.insertEditDetail(editDetail);
                editInfo.setEditId(Long.valueOf(editDetail.getEditId()));
                editInfo.setWorkOrderId(workOrderNo);
                editInfo.setCreatedBy(SecurityUtils.getUsername());
                editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editInfo.setUpdatedBy(SecurityUtils.getUsername());
                editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
               /* editInfo.setEditRemark(reservationAcceptVo.getEditInfo().getEditRemark());
                editInfo.setEditReason(reservationAcceptVo.getEditInfo().getEditReason());*/
                editInfoMapper.insertEditInfo(editInfo);

            }

        }

        return  flowLogMapper.insertFlowLog(flowLog);
    }







}
