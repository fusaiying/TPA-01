package com.paic.ehis.cs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IReservationAcceptVoService;
import com.paic.ehis.cs.utils.CodeEnum;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 个人预约修改页面
     * @param workOrderNo
     * @return
     */
    @Override
    public ReservationAcceptVo selectReservationAcceptVoList3(String workOrderNo) {
        ReservationAcceptVo reservationAcceptVo= reservationAcceptVoMapper.selectReservationAcceptVoList3(workOrderNo);
        String sourceName="ReservationAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);

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

        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getSourceFiledName(),fieldMap.getTargetColumnName());
            VoUtils voUtils=new VoUtils<DemandAcceptVo>();
            reservationAcceptVo= (ReservationAcceptVo) voUtils.fromVoToVo(reservationAcceptVo,map,acceptDetailInfo);
        }

        return reservationAcceptVo;
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
        workOrderAccept.setStatus(CodeEnum.ORDER_STATE_01.getCode());
        workOrderAccept.setAcceptBy(reservationAcceptVo.getAcceptBy());
        workOrderAccept.setAcceptTime(reservationAcceptVo.getAcceptTime());
        workOrderAccept.setModifyTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setCreateBy(reservationAcceptVo.getCreateBy());
        workOrderAccept.setUpdateBy(reservationAcceptVo.getUpdateBy());
        workOrderAccept.setOrganCode(reservationAcceptVo.getOrganCode());
        workOrderAccept.setUpdateTime(reservationAcceptVo.getUpdateTime());
        workOrderAccept.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        workOrderAccept.setPolicyNo(reservationAcceptVo.getPolicyNo());
        workOrderAccept.setPolicyItemNo(reservationAcceptVo.getPolicyItemNo());
        workOrderAccept.setCreateTime(reservationAcceptVo.getCreateTime());
        workOrderAccept.setBusinessType(reservationAcceptVo.getBusinessType());
        workOrderAccept.setOtherNo(reservationAcceptVo.getOtherNo());
        workOrderAccept.setInsuredNo(reservationAcceptVo.getInsuredNo());
        workOrderAccept.setInsuredName(reservationAcceptVo.getInsuredName());
        workOrderAcceptMapper.insertWorkOrderAccept(workOrderAccept);

        //详细表插入
        acceptDetailInfo.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        acceptDetailInfo.setChannelCode(reservationAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(reservationAcceptVo.getItemCode());
        String callPersonId=PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10);
        acceptDetailInfo.setCallPersonId(callPersonId);
        acceptDetailInfo.setCallRelationBy(reservationAcceptVo.getCallRelationBy());
        acceptDetailInfo.setPriorityLevel(reservationAcceptVo.getPriorityLevel());
        String contactsPersonId=PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10);
        acceptDetailInfo.setContactsPersonId(contactsPersonId);
        acceptDetailInfo.setContactsRelationBy(reservationAcceptVo.getContactsRelationBy());
        acceptDetailInfo.setEmail(reservationAcceptVo.getEmail());
        acceptDetailInfo.setOrganCode(reservationAcceptVo.getOrganCode());
        acceptDetailInfo.setContent(reservationAcceptVo.getContent());
        acceptDetailInfo.setStatus(CodeEnum.ORDER_STATE_01.getCode());
        acceptDetailInfo.setCreateBy(reservationAcceptVo.getCreateBy());
        acceptDetailInfo.setCreateTime(reservationAcceptVo.getCreateTime());
        acceptDetailInfo.setUpdateBy(reservationAcceptVo.getUpdateBy());
        acceptDetailInfo.setUpdateTime(reservationAcceptVo.getUpdateTime());
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
        acceptDetailInfoMapper.insertAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
        personInfo1.setPersonId(callPersonId);
        personInfo1.setName(reservationAcceptVo.getCallPerson().getName());
        personInfo1.setMobilePhone(reservationAcceptVo.getCallPerson().getMobilePhone());
        personInfo1.setCreatedBy(reservationAcceptVo.getCreateBy());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(reservationAcceptVo.getUpdateBy());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        if(reservationAcceptVo.getContactsPerson()!=null){
            personInfo2.setPersonId(contactsPersonId);
            personInfo2.setSex(reservationAcceptVo.getContactsPerson().getSex());
            personInfo2.setName(reservationAcceptVo.getContactsPerson().getName());
            personInfo2.setLanguage(reservationAcceptVo.getContactsPerson().getLanguage());
            personInfo2.setMobilePhone(reservationAcceptVo.getContactsPerson().getMobilePhone());
            String homePhone=reservationAcceptVo.getContactsPerson().getHomePhone();
            String workPhone=reservationAcceptVo.getContactsPerson().getWorkPhone();
            personInfo2.setHomePhone(StringUtils.isNotEmpty(homePhone)? homePhone:"---");
            personInfo2.setWorkPhone(StringUtils.isNotEmpty(workPhone)? workPhone:"---");
            personInfo2.setCreatedBy(reservationAcceptVo.getCreateBy());
            personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            personInfo2.setUpdatedBy(reservationAcceptVo.getUpdateBy());
            personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            personInfoMapper.insertPersonInfo(personInfo2);
        }


        //轨迹表插入
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20));
        flowLog.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("06");
        flowLog.setLinkCode(workOrderAccept.getStatus());
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeBy(SecurityUtils.getUsername());
        flowLog.setStatus(workOrderAccept.getStatus());
        flowLog.setCreatedBy(reservationAcceptVo.getCreateBy());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(reservationAcceptVo.getUpdateBy());
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
        workOrderAccept.setActivationNum(workOrderAccept.getActivationNum()+1);
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
        acceptDetailInfo.setEarliestTime(reservationAcceptVo.getEarliestTime());
        acceptDetailInfo.setFirstFlag(reservationAcceptVo.getFirstFlag());
//        acceptDetailInfo.setClinicDate(reservationAcceptVo.getClinicDate());
//        acceptDetailInfo.setClinicTime(reservationAcceptVo.getClinicTime());
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
        callPerson.setFax(reservationAcceptVo.getCallPerson().getFax());
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

        String[] homePhone1= reservationAcceptVo.getContactsPerson().getHomePhone1();
        if(StringUtils.isNotEmpty(homePhone1)){
            String tHomePhone = (StringUtils.isNotEmpty(homePhone1[0]) && !"null".equals(homePhone1[0])) ? homePhone1[0]:"";
            for (int i = 1; i < homePhone1.length; i++) {
                tHomePhone += "-";
                tHomePhone += (StringUtils.isNotEmpty(homePhone1[i]) && !"null".equals(homePhone1[i])) ? homePhone1[i]:"";
            }
            for (int i = 0; i < (4-homePhone1.length); i++) {
                tHomePhone += "-";
            }
            contactsPerson.setHomePhone(tHomePhone);
        }else{
            contactsPerson.setHomePhone("---");
        }

        String[] workPhone1= reservationAcceptVo.getContactsPerson().getWorkPhone1();
        if(StringUtils.isNotEmpty(workPhone1)){
            String tWorkPhone = (StringUtils.isNotEmpty(workPhone1[0]) && !"null".equals(workPhone1[0])) ? workPhone1[0] : "";
            for (int i = 1; i < workPhone1.length; i++) {
                tWorkPhone += "-";
                tWorkPhone += (StringUtils.isNotEmpty(workPhone1[i]) && !"null".equals(workPhone1[i])) ? workPhone1[i] : "";
            }
            for (int i = 0; i < (4-workPhone1.length); i++) {
                tWorkPhone += "-";
            }
            contactsPerson.setWorkPhone(tWorkPhone);
        }else{
            contactsPerson.setWorkPhone("---");
        }

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

        Map<String,String> tSpecialMap = new HashMap<String ,String>();
        tSpecialMap.put("updateBy","updateBy");
        tSpecialMap.put("updateTime","updateTime");
        tSpecialMap.put("createBy","createBy");
        tSpecialMap.put("createTime","createTime");
        tSpecialMap.put("updatedBy","updatedBy");
        tSpecialMap.put("updatedTime","updatedTime");
        tSpecialMap.put("createdBy","createdBy");
        tSpecialMap.put("createdTime","createdTime");
        tSpecialMap.put("changeTime","changeTime");
        tSpecialMap.put("remarkTime","remarkTime");
        tSpecialMap.put("oldmodifyBy","oldmodifyBy");

        //基本信息 修改明细
        EditDetail editDetail=new EditDetail();
        String editId=PubFun.createMySqlMaxNoUseCache("cs_edit_id",10,8);
        Map map1 = JSONObject.parseObject(JSONObject.toJSONString(reservationAcceptVo1), Map.class);
        Map map2 = JSONObject.parseObject(JSONObject.toJSONString(reservationAcceptVo), Map.class);
        List<String> keyList=new ArrayList<>();
        Iterator<String> iter1 = map1.keySet().iterator();
        while(iter1.hasNext()){
            String map1key=iter1.next();
            if(!"".equals(tSpecialMap.get(map1key)) && tSpecialMap.get(map1key)!=null) {
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
            if(!"".equals(tSpecialMap.get(map3key)) && tSpecialMap.get(map3key)!=null) {
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
            if(!"".equals(tSpecialMap.get(map5key)) && tSpecialMap.get(map5key)!=null) {
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
            if(!"".equals(tSpecialMap.get(map7key)) && tSpecialMap.get(map7key)!=null) {
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
