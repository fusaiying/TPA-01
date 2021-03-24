package com.paic.ehis.cs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IReservationAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private HcsModificationMapper hcsModificationMapper;

    @Override
    public List<ReservationAcceptVo> selectReservationAcceptVoList(AcceptDTO acceptDTO) {
        List<ReservationAcceptVo> reservationAcceptVos= reservationAcceptVoMapper.selectReservationAcceptVoList(acceptDTO);
        String sourceName="ReservationAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        for (ReservationAcceptVo reservationAcceptVo:reservationAcceptVos){
            PersonInfo callPerson=personInfoMapper.selectPersonInfoById(reservationAcceptVo.getCallPersonId());
            if (callPerson!= null) {
                reservationAcceptVo.setCallPerson(callPerson);
            } else {
                reservationAcceptVo.setCallPerson(new PersonInfo());
            }
            PersonInfo contactsPerson =personInfoMapper.selectPersonInfoById(reservationAcceptVo.getContactsPersonId());
            if (contactsPerson!= null) {
                String linePhone = contactsPerson.getLinePhone();
                if (linePhone != null) {
                    String[] linePhone1 = linePhone.split("\\-");
                    contactsPerson.setLinePhone1(linePhone1);
                } else {
                    contactsPerson.setLinePhone1(new String[4]);
                }
                String homePhone = contactsPerson.getHomePhone();
                if (homePhone != null) {
                    String[] homePhone1 = homePhone.split("\\-");
                    contactsPerson.setHomePhone1(homePhone1);
                } else {
                    contactsPerson.setHomePhone1(new String[4]);
                }
                String workPhone = contactsPerson.getWorkPhone();
                if (workPhone != null) {
                    String[] workPhone1 = workPhone.split("\\-");
                    contactsPerson.setWorkPhone1(workPhone1);
                } else {
                    contactsPerson.setWorkPhone1(new String[4]);
                }
                reservationAcceptVo.setContactsPerson(contactsPerson);
            } else {
                reservationAcceptVo.setContactsPerson(new PersonInfo());
            }
            PersonInfo complaintPerson=personInfoMapper.selectPersonInfoById(reservationAcceptVo.getComplaintPersonId());
            if (complaintPerson!= null) {
                reservationAcceptVo.setComplaintPerson(contactsPerson);
            } else {
                reservationAcceptVo.setComplaintPerson(new PersonInfo());
            }
            reservationAcceptVo.setOperatorLast(userInfoMapper.selectUserInfoById(reservationAcceptVo.getCreateBy()));
            reservationAcceptVo.setReviser(userInfoMapper.selectUserInfoById(reservationAcceptVo.getUpdateBy()));
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(reservationAcceptVo.getWorkOrderNo());
            if(acceptDetailInfo==null){
                continue;
            }
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
            PersonInfo callPerson=personInfoMapper.selectPersonInfoById(reservationAcceptVo.getCallPersonId());
            if (callPerson!= null) {
                reservationAcceptVo.setCallPerson(callPerson);
            } else {
                reservationAcceptVo.setCallPerson(new PersonInfo());
            }
            PersonInfo contactsPerson =personInfoMapper.selectPersonInfoById(reservationAcceptVo.getContactsPersonId());
            if (contactsPerson!= null) {
                String linePhone=contactsPerson.getLinePhone();
                if (linePhone!=null){
                    String[] linePhone1=linePhone.split("\\-");
                    contactsPerson.setLinePhone1(linePhone1);
                }else{
                    contactsPerson.setLinePhone1(new String[4]);
                }
                String homePhone=contactsPerson.getHomePhone();
                if (homePhone!=null){
                    String[] homePhone1=homePhone.split("\\-");
                    contactsPerson.setHomePhone1(homePhone1);
                }else {
                    contactsPerson.setHomePhone1(new String[4]);
                }
                String workPhone=contactsPerson.getWorkPhone();
                if (workPhone!=null){
                    String[] workPhone1=workPhone.split("\\-");
                    contactsPerson.setWorkPhone1(workPhone1);
                }else{
                    contactsPerson.setWorkPhone1(new String[4]);
                }
                reservationAcceptVo.setContactsPerson(contactsPerson);
            } else {
                reservationAcceptVo.setContactsPerson(new PersonInfo());
            }
            PersonInfo complaintPerson=personInfoMapper.selectPersonInfoById(reservationAcceptVo.getComplaintPersonId());
            if (complaintPerson!= null) {
                reservationAcceptVo.setComplaintPerson(contactsPerson);
            } else {
                reservationAcceptVo.setComplaintPerson(new PersonInfo());
            }
            reservationAcceptVo.setOperatorLast(userInfoMapper.selectUserInfoById(reservationAcceptVo.getCreateBy()));
            reservationAcceptVo.setReviser(userInfoMapper.selectUserInfoById(reservationAcceptVo.getUpdateBy()));
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(reservationAcceptVo.getWorkOrderNo());
            if(acceptDetailInfo==null){
                continue;
            }
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
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20));
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
        AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
        PersonInfo personInfo1=new PersonInfo();
        PersonInfo personInfo2=new PersonInfo();
        FlowLog flowLog=new FlowLog();
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        //工单表插入
        workOrderAccept.setStatus("01");
        //workOrderAccept.setBusinessType("02");
        workOrderAccept.setAcceptTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setModifyTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setCreateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setOrganCode(reservationAcceptVo.getOrganCode());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        workOrderAccept.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setBusinessType(reservationAcceptVo.getBusinessType());
        workOrderAcceptMapper.insertWorkOrderAccept(workOrderAccept);

        //详细表插入
        acceptDetailInfo.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        acceptDetailInfo.setChannelCode(reservationAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(reservationAcceptVo.getItemCode());
        acceptDetailInfo.setCallPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        acceptDetailInfo.setCallRelationBy(reservationAcceptVo.getCallRelationBy());
        acceptDetailInfo.setPriorityLevel(reservationAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        acceptDetailInfo.setContactsRelationBy(reservationAcceptVo.getContactsRelationBy());
        acceptDetailInfo.setEmail(reservationAcceptVo.getEmail());
        acceptDetailInfo.setContent(reservationAcceptVo.getContent());
        acceptDetailInfo.setStatus("01");
        acceptDetailInfo.setCreateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        acceptDetailInfo.setCallCenterId(reservationAcceptVo.getCallCenterId());
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap("accept_detail_info","ReservationAcceptVo");
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<ReservationAcceptVo>();
            acceptDetailInfo= (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo,map,reservationAcceptVo);
        }
        //详细表插入
  //    demandAcceptVoMapper.insertAcceptDetailInfo(acceptDetailInfo);
        acceptDetailInfoMapper.insertAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
        personInfo1.setPersonId(acceptDetailInfo.getCallPersonId());
        personInfo1.setName(reservationAcceptVo.getCallPerson().getName());
        personInfo1.setMobilePhone(reservationAcceptVo.getCallPerson().getMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(acceptDetailInfo.getContactsPersonId());
        personInfo2.setSex(reservationAcceptVo.getContactsPerson().getSex());
        personInfo2.setName(reservationAcceptVo.getContactsPerson().getName());
        personInfo2.setLanguage(reservationAcceptVo.getContactsPerson().getLanguage());
        personInfo2.setMobilePhone(reservationAcceptVo.getContactsPerson().getMobilePhone());
 //        personInfo2.setHomePhone(reservationAcceptVo.getContactsPerson().getHomePhone1()[0]+"-"+reservationAcceptVo.getContactsPerson().getHomePhone1()[1]+"-"+reservationAcceptVo.getContactsPerson().getHomePhone1()[2]+"-"+reservationAcceptVo.getContactsPerson().getHomePhone1()[3]);
 //       personInfo2.setWorkPhone(reservationAcceptVo.getContactsPerson().getWorkPhone1()[0]+"-"+reservationAcceptVo.getContactsPerson().getWorkPhone1()[1]+"-"+reservationAcceptVo.getContactsPerson().getWorkPhone1()[2]+"-"+reservationAcceptVo.getContactsPerson().getWorkPhone1()[3]);
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.insertPersonInfo(personInfo2);

        //轨迹表插入
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20));
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
        ReservationAcceptVo reservationAcceptVo1=reservationAcceptVoMapper.selectReservationAcceptVo(workOrderNo);
        String sourceName="ReservationAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap1=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        AcceptDetailInfo acceptDetailInfo1=acceptDetailInfoMapper.selectAcceptDetailInfoById(reservationAcceptVo1.getWorkOrderNo());
        for (FieldMap fieldMap:KVMap1){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getSourceFiledName(),fieldMap.getTargetColumnName());
            VoUtils voUtils=new VoUtils<DemandAcceptVo>();
            reservationAcceptVo1= (ReservationAcceptVo) voUtils.fromVoToVo(reservationAcceptVo1,map,acceptDetailInfo1);
        }

        PersonInfo callPerson1= personInfoMapper.selectPersonInfoById(reservationAcceptVo.getCallPersonId());
        PersonInfo contactsPerson1=personInfoMapper.selectPersonInfoById(reservationAcceptVo.getContactsPersonId());
        PersonInfo complaintPerson1=new PersonInfo();
        PersonInfo callPerson=new PersonInfo();
        PersonInfo contactsPerson=new PersonInfo();
        PersonInfo complaintPerson=new PersonInfo();
        BeanUtils.copyProperties(callPerson1, callPerson);
        BeanUtils.copyProperties(contactsPerson1, contactsPerson);
        FlowLog flowLog=new FlowLog();

        //工单表修改
        WorkOrderAccept workOrderAccept=workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
        workOrderAccept.setOrganCode(reservationAcceptVo.getOrganCode());
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);

        AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(workOrderNo);
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        acceptDetailInfo.setChannelCode(reservationAcceptVo.getChannelCode());
        acceptDetailInfo.setCallCenterId(reservationAcceptVo.getCallCenterId());
        acceptDetailInfo.setPriorityLevel(reservationAcceptVo.getPriorityLevel());
        acceptDetailInfo.setEmail(reservationAcceptVo.getEmail());
        acceptDetailInfo.setOutpatientSettlement(reservationAcceptVo.getOutpatientSettlement());
        acceptDetailInfo.setVisitType(reservationAcceptVo.getVisitType());
        acceptDetailInfo.setDisease(reservationAcceptVo.getDisease());
        acceptDetailInfo.setSymptomsSigns(reservationAcceptVo.getSymptomsSigns());
        acceptDetailInfo.setSymptomTimes(reservationAcceptVo.getSymptomTimes());
        acceptDetailInfo.setTimeUnit(reservationAcceptVo.getTimeUnit());
        acceptDetailInfo.setAccidentFlag(reservationAcceptVo.getAccidentFlag());
        acceptDetailInfo.setAccidentReason(reservationAcceptVo.getAccidentReason());
        acceptDetailInfo.setValidCertificate(reservationAcceptVo.getValidCertificate());
        acceptDetailInfo.setSettlementCard(reservationAcceptVo.getSettlementCard());
        acceptDetailInfo.setComplaintTime(reservationAcceptVo.getComplaintTime());
        acceptDetailInfo.setHospitalDays(reservationAcceptVo.getHospitalDays());
        acceptDetailInfo.setMedicalInstitution(reservationAcceptVo.getMedicalInstitution());
        acceptDetailInfo.setDepartment(reservationAcceptVo.getDepartment());
        acceptDetailInfo.setBunk(reservationAcceptVo.getBunk());
        acceptDetailInfo.setCompensationRatio(reservationAcceptVo.getCompensationRatio());
        acceptDetailInfo.setAppointmentDate(reservationAcceptVo.getAppointmentDate());
        acceptDetailInfo.setHospitalWorkCall(reservationAcceptVo.getHospitalWorkCall());
        //acceptDetailInfo.setEarliestTime(reservationAcceptVo.getEarliestTime());
        acceptDetailInfo.setFirstFlag(reservationAcceptVo.getFirstFlag());
        acceptDetailInfo.setClinicDate(reservationAcceptVo.getClinicDate());
        acceptDetailInfo.setClinicTime(reservationAcceptVo.getClinicTime());
        acceptDetailInfo.setProvince(reservationAcceptVo.getProvince());
        acceptDetailInfo.setCity(reservationAcceptVo.getCity());
        acceptDetailInfo.setSettlementFlag(reservationAcceptVo.getSettlementFlag());
        acceptDetailInfo.setContent(reservationAcceptVo.getContent());
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
        //callPerson.setFax(reservationAcceptVo.getCallPerson().getFax());
        callPerson.setName(reservationAcceptVo.getCallPerson().getName());
        callPerson.setMobilePhone(reservationAcceptVo.getCallPerson().getMobilePhone());
        callPerson.setUpdatedBy(SecurityUtils.getUsername());
        callPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(callPerson);

        //插入联系人
        contactsPerson.setSex(reservationAcceptVo.getContactsPerson().getSex());
        contactsPerson.setName(reservationAcceptVo.getContactsPerson().getName());
        contactsPerson.setLanguage(reservationAcceptVo.getContactsPerson().getLanguage());
        contactsPerson.setMobilePhone(reservationAcceptVo.getContactsPerson().getMobilePhone());
        /*if(reservationAcceptVo.getContactsPerson().getHomePhone1().length <=3){
            contactsPerson.setHomePhone("---");
        }else{
            contactsPerson.setHomePhone(reservationAcceptVo.getContactsPerson().getHomePhone1()[0]+"-"+reservationAcceptVo.getContactsPerson().getHomePhone1()[1]+"-"+reservationAcceptVo.getContactsPerson().getHomePhone1()[2]+"-"+reservationAcceptVo.getContactsPerson().getHomePhone1()[3]);
        }
        if(reservationAcceptVo.getContactsPerson().getWorkPhone1().length <= 3){
            contactsPerson.setWorkPhone("---");
        }else{
            contactsPerson.setWorkPhone(reservationAcceptVo.getContactsPerson().getWorkPhone1()[0]+"-"+reservationAcceptVo.getContactsPerson().getWorkPhone1()[1]+"-"+reservationAcceptVo.getContactsPerson().getWorkPhone1()[2]+"-"+reservationAcceptVo.getContactsPerson().getWorkPhone1()[3]);
        }*/
        contactsPerson.setUpdatedBy(SecurityUtils.getUsername());
        contactsPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(contactsPerson);

       //插入申请人
        complaintPerson.setName(reservationAcceptVo.getComplaintPerson().getName());
        complaintPerson.setUpdatedBy(SecurityUtils.getUsername());
        complaintPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(complaintPerson);

        Map<String ,String> tDictType = new HashMap<String ,String>();
        tDictType.put("sex","cs_sex");
        tDictType.put("language","cs_communication_language");
        tDictType.put("channelCode","cs_channel");
        tDictType.put("priorityLevel","cs_priority");
        tDictType.put("organCode","cs_organization");
        tDictType.put("callRelationBy","cs_relation");
        tDictType.put("visitType","cs_consultation_type");
        tDictType.put("validCertificate","cs_whether_flag");
        tDictType.put("settlementCard","cs_whether_flag");
        tDictType.put("accidentFlag","cs_whether_flag");
        tDictType.put("identity","cs_identity");
        tDictType.put("symptomTimes","cs_time_unit");

        //基本信息 修改明细
        EditDetail editDetail=new EditDetail();
        String editId=PubFun.createMySqlMaxNoUseCache("cs_edit_id",10,8);
        Map map1 = JSONObject.parseObject(JSONObject.toJSONString(reservationAcceptVo1), Map.class);
        Map map2 = JSONObject.parseObject(JSONObject.toJSONString(reservationAcceptVo), Map.class);
        List<String> keyList=new ArrayList<>();
        Iterator<String> iter1 = map1.keySet().iterator();
        while(iter1.hasNext()){
            String map1key=iter1.next();
            if("updateBy".equals(map1key) || "updateTime".equals(map1key) || "changeTime".equals(map1key) || "createBy".equals(map1key) || "createTime".equals(map1key)  || "updatedBy".equals(map1key) || "updatedTime".equals(map1key) || "createdBy".equals(map1key) || "createdTime".equals(map1key)) {
                continue;
            }
            String map1value = String.valueOf(map1.get(map1key));
            String map2value = String.valueOf(map2.get(map1key));

            editDetail=new EditDetail();
            editDetail.setKeyDictType("reservationAcceptVo");
            editDetail.setItemKey(map1key);
            editDetail.setEditId(editId);
            editDetail.setCreatedBy(SecurityUtils.getUsername());
            editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setUpdatedBy(SecurityUtils.getUsername());
            editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setValueDictType(tDictType.get(map1key));

            if((map1value == null || map1value.equals("")) && (map2value != null && !map2value.equals(""))){
                keyList.add(map1key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setNowValue(map2value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if((map2value == null || map2value.equals("")) && (map1value != null && !map1value.equals(""))){
                keyList.add(map1key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setOldValue(map1value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if ((map1value != null && !map1value.equals("")) && (map2value != null && !map2value.equals("")) && !map1value.equals(map2value)) {
                keyList.add(map1key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setOldValue(map1value);
                editDetail.setNowValue(map2value);
                editDetailMapper.insertEditDetail(editDetail);
            }
        }

        //来电人信息修改明细
        Map map3 = JSONObject.parseObject(JSONObject.toJSONString(callPerson1), Map.class);
        Map map4 = JSONObject.parseObject(JSONObject.toJSONString(callPerson), Map.class);
        Iterator<String> iter2 = map3.keySet().iterator();
        while(iter2.hasNext()){
            String map3key=iter2.next();
            if("updateBy".equals(map3key) || "updateTime".equals(map3key) || "changeTime".equals(map3key) || "createBy".equals(map3key) || "createTime".equals(map3key)  || "updatedBy".equals(map3key) || "updatedTime".equals(map3key) || "createdBy".equals(map3key) || "createdTime".equals(map3key)) {
                continue;
            }
            String map3value = String.valueOf(map3.get(map3key));
            String map4value = String.valueOf(map4.get(map3key));

            editDetail=new EditDetail();
            editDetail.setKeyDictType("reservationAcceptVo");
            editDetail.setItemKey("callPerson."+map3key);
            editDetail.setEditId(editId);
            editDetail.setCreatedBy(SecurityUtils.getUsername());
            editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setUpdatedBy(SecurityUtils.getUsername());
            editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setValueDictType(tDictType.get(map3key));

            if((map3value == null || "".equals(map3value)) && (map4value !=null && !"".equals(map4value))){
                keyList.add(map3key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setNowValue(map4value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if((map4value == null || "".equals(map4value)) && (map3value !=null && !"".equals(map3value))){
                keyList.add(map3key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(map3value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if ((map3value !=null && !"".equals(map3value)) && (map4value !=null && !"".equals(map4value)) &&(!map3value.equals(map4value))) {
                keyList.add(map3key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(map3value);
                editDetail.setNowValue(map4value);
                editDetailMapper.insertEditDetail(editDetail);
            }
        }

        //联系人
        Map map5 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson1), Map.class);
        Map map6 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson), Map.class);
        Iterator<String> iter3 = map6.keySet().iterator();
        while(iter3.hasNext()){
            String map5key=iter3.next();
            if("updateBy".equals(map5key) || "updateTime".equals(map5key) || "changeTime".equals(map5key) || "createBy".equals(map5key) || "createTime".equals(map5key)  || "updatedBy".equals(map5key) || "updatedTime".equals(map5key) || "createdBy".equals(map5key) || "createdTime".equals(map5key)) {
                continue;
            }
            String map5value = String.valueOf(map5.get(map5key));
            String map6value = String.valueOf(map6.get(map5key));

            editDetail=new EditDetail();
            editDetail.setKeyDictType("reservationAcceptVo");
            editDetail.setItemKey("contactsPerson."+map5key);
            editDetail.setEditId(editId);
            editDetail.setCreatedBy(SecurityUtils.getUsername());
            editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setUpdatedBy(SecurityUtils.getUsername());
            editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setValueDictType(tDictType.get(map5key));

            if ((map5value == null || map5value.equals("")) && (map6value != null && !map6value.equals(""))) {
                keyList.add(map5key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setNowValue(String.valueOf(map6value));
                editDetailMapper.insertEditDetail(editDetail);
            } else if ((map6value == null || map6value.equals("")) && (map5value != null && !map5value.equals(""))) {
                keyList.add(map5key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(String.valueOf(map5value));
                editDetailMapper.insertEditDetail(editDetail);
            } else if ((map5value != null && !map5value.equals("")) && (map6value != null && !map6value.equals("")) && !map5value.equals(map6value)) {
                keyList.add(map5key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(String.valueOf(map5value));
                editDetail.setNowValue(String.valueOf(map6value));
                editDetailMapper.insertEditDetail(editDetail);
            }
        }

        Map map7 = JSONObject.parseObject(JSONObject.toJSONString(complaintPerson1), Map.class);
        Map map8 = JSONObject.parseObject(JSONObject.toJSONString(complaintPerson), Map.class);
        Iterator<String> iter4 = map7.keySet().iterator();
        while(iter4.hasNext()){
            String map7key=iter4.next();
            if("updateBy".equals(map7key) || "updateTime".equals(map7key) || "changeTime".equals(map7key) || "createBy".equals(map7key) || "createTime".equals(map7key)  || "updatedBy".equals(map7key) || "updatedTime".equals(map7key) || "createdBy".equals(map7key) || "createdTime".equals(map7key)) {
                continue;
            }
            String map7value = String.valueOf(map7.get(map7key));
            String map8value = String.valueOf(map8.get(map7key));

            editDetail=new EditDetail();
            editDetail.setKeyDictType("reservationAcceptVo");
            editDetail.setItemKey("complainPerson."+map7key);
            editDetail.setEditId(editId);
            editDetail.setCreatedBy(SecurityUtils.getUsername());
            editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setUpdatedBy(SecurityUtils.getUsername());
            editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setValueDictType(tDictType.get(map7key));

            if((map7value == null || "".equals(map7value)) && (map8value != null && !"".equals(map8value))){
                keyList.add(map7key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setNowValue(map8value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if((map8value == null || "".equals(map8value)) && (map7value != null && !"".equals(map7value))){
                keyList.add(map7key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(map7value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if ((map8value != null && !"".equals(map8value)) && (map7value != null && !"".equals(map7value)) && (!map7value.equals(map8value))) {
                keyList.add(map7key);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(map7value);
                editDetail.setNowValue(map8value);
                editDetailMapper.insertEditDetail(editDetail);
            }
        }

        EditInfo editInfo=new EditInfo();
        editInfo.setEditId(editId);
        editInfo.setWorkOrderId(workOrderNo);
        editInfo.setCreatedBy(SecurityUtils.getUsername());
        editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setUpdatedBy(SecurityUtils.getUsername());
        editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setEditRemark(reservationAcceptVo.getEditRemark());
        editInfo.setEditReason(reservationAcceptVo.getEditReason());
        editInfoMapper.insertEditInfo(editInfo);

        //轨迹表插入
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeBy(SecurityUtils.getUsername());
        flowLog.setOperateCode("03");
        flowLog.setStatus(workOrderAccept.getStatus());
        flowLog.setSubId(editId);
        flowLog.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));

        //Hcs
        if (reservationAcceptVo.getAlterId() != null){
            hcsModificationMapper.updateHcsStauts(reservationAcceptVo.getAlterId());
        }

        return  flowLogMapper.insertFlowLog(flowLog);
    }



    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int updateClickTime(AcceptDTO acceptDTO){
        //更新处理时间
        WorkOrderAccept workOrderAccept=workOrderAcceptMapper.selectWorkOrderAcceptById(acceptDTO.getWorkOrderNo());
        workOrderAccept.setClickTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        return workOrderAcceptMapper.updateClickTime(workOrderAccept);
    }



}
