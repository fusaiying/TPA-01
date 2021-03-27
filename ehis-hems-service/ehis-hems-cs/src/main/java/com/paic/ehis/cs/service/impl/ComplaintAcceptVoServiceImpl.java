package com.paic.ehis.cs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IComplaintAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ComplaintAcceptVoServiceImpl implements IComplaintAcceptVoService {
    @Autowired
    private ComplaintAcceptVoMapper complaintAcceptVoMapper;
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
    private EditInfoMapper editInfoMapper;
    @Autowired
    private EditDetailMapper editDetailMapper;
    @Autowired
    private WorkHandleInfoMapper workHandleInfoMapper;
    @Autowired
    private FlowLogMapper flowLogMapper;


    @Override
    public List<ComplaintAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO) {
        DemandAcceptVo demandAcceptVo=new DemandAcceptVo();
        demandAcceptVo.setStatus("01");
        return complaintAcceptVoMapper.selectComplaintAcceptVoList(acceptDTO);
    }

    @Override
    public List<ComplaintAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO) {
        return complaintAcceptVoMapper.selectComplaintAcceptVoListOne(acceptDTO);
    }

    @Override
    public ComplaintAcceptVo selectComplaintAcceptVo(String workOrderNo) {
        ComplaintAcceptVo complaintAcceptVo=complaintAcceptVoMapper.selectComplaintAcceptVo(workOrderNo);
        String sourceName="ComplaintAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        if(complaintAcceptVo!=null){
            PersonInfo personInfo = personInfoMapper.selectPersonInfoById(complaintAcceptVo.getCallPersonId());
            if (personInfo != null) {
                complaintAcceptVo.setCallPerson(personInfo);
            } else {
                complaintAcceptVo.setCallPerson(new PersonInfo());
            }

            PersonInfo personInfo2 = personInfoMapper.selectPersonInfoById(complaintAcceptVo.getContactsPersonId());
            if (personInfo2 != null) {
                String linePhone=personInfo2.getLinePhone();
                if (linePhone!=null){
                    String[] linePhone1=linePhone.split("\\-");
                    personInfo2.setLinePhone1(linePhone1);
                }else{
                    personInfo2.setLinePhone1(new String[4]);
                }
                String homePhone=personInfo2.getHomePhone();
                if (homePhone!=null){
                    String[] homePhone1=homePhone.split("\\-");
                    personInfo2.setHomePhone1(homePhone1);
                }else {
                    personInfo2.setHomePhone1(new String[4]);
                }
                String workPhone=personInfo2.getWorkPhone();
                if (workPhone!=null){
                    String[] workPhone1=workPhone.split("\\-");
                    personInfo2.setWorkPhone1(workPhone1);
                }else{
                    personInfo2.setWorkPhone1(new String[4]);
                }
                complaintAcceptVo.setContactsPerson(personInfo2);
            } else {
                complaintAcceptVo.setContactsPerson(new PersonInfo());
            }

            PersonInfo personInfo3 = personInfoMapper.selectPersonInfoById(complaintAcceptVo.getComplaintPersonId());
            if (personInfo3 != null) {
                complaintAcceptVo.setComplaintPerson(personInfo3);
            } else {
                complaintAcceptVo.setComplaintPerson(new PersonInfo());
            }
            PersonInfo personInfo4 =  personInfoMapper.selectPersonInfoById(complaintAcceptVo.getInsuredNo());
            if (personInfo4 != null) {
                complaintAcceptVo.setInsurer(personInfo4);
            } else {
                complaintAcceptVo.setInsurer(new PersonInfo());
            }
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(complaintAcceptVo.getWorkOrderNo());
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getSourceFiledName(),fieldMap.getTargetColumnName());
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                complaintAcceptVo= (ComplaintAcceptVo) voUtils.fromVoToVo(complaintAcceptVo,map,acceptDetailInfo);
            }
        }
        return complaintAcceptVo;
    }

    /**
     * 投诉新增页面
     * @param complaintAcceptVo
     * @return
     */
    @Override
    public int insertComplaintInfo(ComplaintAcceptVo complaintAcceptVo) {
        AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
        PersonInfo personInfo1=new PersonInfo();
        PersonInfo personInfo2=new PersonInfo();
        PersonInfo personInfo3=new PersonInfo();
        FlowLog flowLog=new FlowLog();
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        //工单表插入
        workOrderAccept.setStatus("01");
        workOrderAccept.setOrganCode(complaintAcceptVo.getOrganCode());
        workOrderAccept.setCreateBy(SecurityUtils.getUsername());
        //workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        //workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        workOrderAccept.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setBusinessType(complaintAcceptVo.getBusinessType());
        workOrderAccept.setAcceptTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setModifyTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertWorkOrderAccept(workOrderAccept);


        acceptDetailInfo.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        acceptDetailInfo.setOrganCode(complaintAcceptVo.getOrganCode());
        acceptDetailInfo.setChannelCode(complaintAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(complaintAcceptVo.getItemCode());
        acceptDetailInfo.setCallPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        acceptDetailInfo.setPriorityLevel(complaintAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        acceptDetailInfo.setComplaintPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        acceptDetailInfo.setEmail(complaintAcceptVo.getEmail());
        acceptDetailInfo.setContent(complaintAcceptVo.getContent());
        acceptDetailInfo.setStatus(complaintAcceptVo.getStatus());
        acceptDetailInfo.setCreateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap("accept_detail_info","ComplaintAcceptVo");
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<ComplaintAcceptVo>();
            acceptDetailInfo= (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo,map,complaintAcceptVo);
        }

        //详细表插入
        complaintAcceptVoMapper.insertAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
        personInfo1.setPersonId(acceptDetailInfo.getCallPersonId());
        personInfo1.setName(complaintAcceptVo.getCallPerson().getName());
        personInfo1.setMobilePhone(complaintAcceptVo.getCallPerson().getMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(acceptDetailInfo.getContactsPersonId());
        personInfo2.setSex(complaintAcceptVo.getContactsPerson().getSex());
        personInfo2.setName(complaintAcceptVo.getContactsPerson().getName());
        personInfo2.setAddress(complaintAcceptVo.getContactsPerson().getAddress());
        personInfo2.setLanguage(complaintAcceptVo.getContactsPerson().getLanguage());
        personInfo2.setMobilePhone(complaintAcceptVo.getContactsPerson().getMobilePhone());

        String[] homePhone1= complaintAcceptVo.getContactsPerson().getHomePhone1();
        if(StringUtils.isNotEmpty(homePhone1)){
            String tHomePhone = (StringUtils.isNotEmpty(homePhone1[0]) && !"null".equals(homePhone1[0])) ? homePhone1[0]:"";
            for (int i = 1; i < homePhone1.length; i++) {
                tHomePhone += "-";
                tHomePhone += (StringUtils.isNotEmpty(homePhone1[i]) && !"null".equals(homePhone1[i])) ? homePhone1[i]:"";
            }
            for (int i = 0; i < (4-homePhone1.length); i++) {
                tHomePhone += "-";
            }
            personInfo2.setHomePhone(tHomePhone);
        }else{
            personInfo2.setHomePhone("---");
        }

        String[] workPhone1= complaintAcceptVo.getContactsPerson().getWorkPhone1();
        if(StringUtils.isNotEmpty(workPhone1)){
            String tWorkPhone = (StringUtils.isNotEmpty(workPhone1[0]) && !"null".equals(workPhone1[0])) ? workPhone1[0] : "";
            for (int i = 1; i < workPhone1.length; i++) {
                tWorkPhone += "-";
                tWorkPhone += (StringUtils.isNotEmpty(workPhone1[i]) && !"null".equals(workPhone1[i])) ? workPhone1[i] : "";
            }
            for (int i = 0; i < (4-workPhone1.length); i++) {
                tWorkPhone += "-";
            }
            personInfo2.setWorkPhone(tWorkPhone);
        }else{
            personInfo2.setWorkPhone("---");
        }

        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo2);
        //插入投诉人
        personInfo3.setPersonId(acceptDetailInfo.getComplaintPersonId());
        personInfo3.setIdentity(complaintAcceptVo.getComplaintPerson().getIdentity());
        personInfo3.setSex(complaintAcceptVo.getComplaintPerson().getSex());
        personInfo3.setName(complaintAcceptVo.getComplaintPerson().getName());
        personInfo3.setLanguage(complaintAcceptVo.getContactsLanguage());
        personInfo3.setMobilePhone(complaintAcceptVo.getComplaintPerson().getMobilePhone());
        personInfo3.setCreatedBy(SecurityUtils.getUsername());
        personInfo3.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo3.setUpdatedBy(SecurityUtils.getUsername());
        personInfo3.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo3);
        //轨迹表插入
        flowLog.setFlowId( PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeBy(SecurityUtils.getUsername());
        flowLog.setOperateCode("01");
        flowLog.setLinkCode(workOrderAccept.getStatus());
        flowLog.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
//        demandAcceptVoMapper.insertFlowLog(flowLog);
        return  complaintAcceptVoMapper.insertFlowLog(flowLog);
    }


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public int updateComplaintAcceptVo(ComplaintAcceptVo complaintAcceptVo) {
        String workOrderNo=complaintAcceptVo.getWorkOrderNo();
        ComplaintAcceptVo complaintAcceptVo1=complaintAcceptVoMapper.selectComplaintAcceptVo(workOrderNo);
        String sourceName="ComplaintAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap1=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        AcceptDetailInfo acceptDetailInfo1=acceptDetailInfoMapper.selectAcceptDetailInfoById(complaintAcceptVo1.getWorkOrderNo());
        for (FieldMap fieldMap:KVMap1){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getSourceFiledName(),fieldMap.getTargetColumnName());
            VoUtils voUtils=new VoUtils<DemandAcceptVo>();
            complaintAcceptVo1= (ComplaintAcceptVo) voUtils.fromVoToVo(complaintAcceptVo1,map,acceptDetailInfo1);
        }
        String callPersonId=complaintAcceptVo.getCallPersonId();
        if(StringUtils.isEmpty(callPersonId) && complaintAcceptVo.getCallPerson()!=null){
            callPersonId=complaintAcceptVo.getCallPerson().getPersonId();
        }
        PersonInfo callPerson1= personInfoMapper.selectPersonInfoById(callPersonId);
        PersonInfo callPerson=new PersonInfo();
        if(callPerson1!=null){
            BeanUtils.copyProperties(callPerson1, callPerson);
            //插入来电人
            callPerson.setPersonId(callPersonId);
            callPerson.setName(complaintAcceptVo.getCallPerson().getName());
            callPerson.setMobilePhone(complaintAcceptVo.getCallPerson().getMobilePhone());
            callPerson.setUpdatedBy(SecurityUtils.getUsername());
            callPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            personInfoMapper.updatePersonInfo(callPerson);
        }

        String contactsPersonId=complaintAcceptVo.getContactsPersonId();
        if(StringUtils.isEmpty(contactsPersonId) && complaintAcceptVo.getContactsPerson()!=null){
            contactsPersonId=complaintAcceptVo.getContactsPerson().getPersonId();
        }
        PersonInfo contactsPerson1=personInfoMapper.selectPersonInfoById(contactsPersonId);
        PersonInfo contactsPerson=new PersonInfo();
        if(contactsPerson1!=null){
            BeanUtils.copyProperties(contactsPerson1, contactsPerson);
            //插入联系人
            contactsPerson.setPersonId(contactsPersonId);
            contactsPerson.setSex(complaintAcceptVo.getContactsPerson().getSex());
            contactsPerson.setName(complaintAcceptVo.getContactsPerson().getName());
            contactsPerson.setLanguage(complaintAcceptVo.getContactsPerson().getLanguage());
            contactsPerson.setMobilePhone(complaintAcceptVo.getContactsPerson().getMobilePhone());
            contactsPerson.setAddress(complaintAcceptVo.getContactsPerson().getAddress());

            String[] homePhone1= complaintAcceptVo.getContactsPerson().getHomePhone1();
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

            String[] workPhone1= complaintAcceptVo.getContactsPerson().getWorkPhone1();
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
        }

        String complaintPersonId=complaintAcceptVo.getComplaintPersonId();
        if(StringUtils.isEmpty(complaintPersonId) && complaintAcceptVo.getComplaintPerson()!=null){
            complaintPersonId=complaintAcceptVo.getComplaintPerson().getPersonId();
        }
        PersonInfo complaintPerson1=personInfoMapper.selectPersonInfoById(complaintPersonId);
        //数据库对象
        PersonInfo complaintPerson=new PersonInfo();
        if(complaintPerson1!=null){
            BeanUtils.copyProperties(complaintPerson1, complaintPerson);
            //插入投诉人
            complaintPerson.setPersonId(complaintPersonId);
            complaintPerson.setSex(complaintAcceptVo.getComplaintPerson().getSex());
            complaintPerson.setIdentity(complaintAcceptVo.getComplaintPerson().getIdentity());
            complaintPerson.setName(complaintAcceptVo.getComplaintPerson().getName());
            complaintPerson.setMobilePhone(complaintAcceptVo.getComplaintPerson().getMobilePhone());
            complaintPerson.setUpdatedBy(SecurityUtils.getUsername());
            complaintPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            personInfoMapper.updatePersonInfo(complaintPerson);
        }

        FlowLog flowLog=new FlowLog();
        //工单表修改
        WorkOrderAccept workOrderAccept=workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
        int activation='1';
        workOrderAccept.setActivationNum(activation);
        workOrderAccept.setOrganCode(complaintAcceptVo.getOrganCode());
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);

        AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(workOrderNo);
        acceptDetailInfo.setChannelCode(complaintAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(complaintAcceptVo.getItemCode());
        acceptDetailInfo.setCallCenterId(complaintAcceptVo.getCallCenterId());
        acceptDetailInfo.setPriorityLevel(complaintAcceptVo.getPriorityLevel());
        acceptDetailInfo.setEmail(complaintAcceptVo.getEmail());
        acceptDetailInfo.setContent(complaintAcceptVo.getContent());
        acceptDetailInfo.setPersuasionFlag(complaintAcceptVo.getPersuasionFlag());
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap("accept_detail_info","ComplaintAcceptVo");
        for (FieldMap fieldMap:KVMap){
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map=new HashMap<String,String>();
            map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
            VoUtils voUtils=new VoUtils<ComplaintAcceptVo>();
            acceptDetailInfo= (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo,map,complaintAcceptVo);
        }
        acceptDetailInfoMapper.updateAcceptDetailInfo(acceptDetailInfo);
        String editId=PubFun.createMySqlMaxNoUseCache("cs_edit_id",10,8);
        EditDetail editDetail = new EditDetail();
        Map map1 = JSONObject.parseObject(JSONObject.toJSONString(complaintAcceptVo1), Map.class);
        Map map2 = JSONObject.parseObject(JSONObject.toJSONString(complaintAcceptVo), Map.class);

        List outList = new ArrayList();
        outList.add("hangFlag");
        outList.add("hangReason");
        outList.add("businessType");
        outList.add("acceptTime");
        outList.add("modifyTime");
        outList.add("status");
        outList.add("contactsSex");
        outList.add("contactsName");
        outList.add("contactsLanguage");
        outList.add("contactsMobilePhone");
        outList.add("contactsCountry");
        outList.add("contactsQuhao");
        outList.add("contactsNumber");
        outList.add("contactsSecondNumber");
        outList.add("editReason");
        outList.add("editRemark");
        outList.add("editInfo");
        outList.add("callPerson");
        outList.add("contactsPerson");
        outList.add("contactsPerson");
        outList.add("complaintPerson");
        outList.add("insurer");
        outList.add("OperatorLast");
        outList.add("Reviser");
        outList.add("editDetail");

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

        List<String> keyList = new ArrayList<>();
        Iterator<String> iter = map1.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if(tSpecialMap.get(key)==null || "".equals(tSpecialMap.get(key))){
                keyList.add(key);
            }
        }
        Iterator<String> iter2 = map2.keySet().iterator();
        while (iter2.hasNext()) {
            String key = iter2.next();
            if(tSpecialMap.get(key) == null || "".equals(tSpecialMap.get(key))){
                if (!keyList.contains(key)) {
                    keyList.add(key);
                }
            }
        }
        for (int i = 0; i < keyList.size(); i++) {
            String mapkey = keyList.get(i);
            if (outList.contains(mapkey)) {
                continue;
            } else {
                Object map1value = map1.get(mapkey);
                Object map2value = map2.get(mapkey);

                editDetail = new EditDetail();
                editDetail.setItemKey(mapkey);
                editDetail.setKeyDictType("complaintAcceptVo");
                editDetail.setEditId(editId);
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setValueDictType(tDictType.get(mapkey));

                if ((map1value == null || map1value.equals("")) && (map2value != null && !map2value.equals(""))) {
                    editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                    editDetail.setNowValue(String.valueOf(map2value));
                    editDetailMapper.insertEditDetail(editDetail);
                } else if ((map2value == null || map2value.equals("")) && (map1value != null && !map1value.equals(""))) {
                    editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                    editDetail.setOldValue(String.valueOf(map1value));
                    editDetailMapper.insertEditDetail(editDetail);
                } else if ((map1value != null && !map1value.equals("")) && (map2value != null && !map2value.equals("")) && !map1value.equals(map2value)) {
                    editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                    editDetail.setOldValue(String.valueOf(map1value));
                    editDetail.setNowValue(String.valueOf(map2value));
                    editDetailMapper.insertEditDetail(editDetail);
                }
            }
        }


        Map map3 = JSONObject.parseObject(JSONObject.toJSONString(callPerson1), Map.class);
        Map map4 = JSONObject.parseObject(JSONObject.toJSONString(callPerson), Map.class);
        Iterator<String> iter5 = map3.keySet().iterator();
        while(iter5.hasNext()){
            String map3key=iter5.next();
            if(!"".equals(tSpecialMap.get(map3key)) && tSpecialMap.get(map3key)!=null) {
                continue;
            }
            String map3value = String.valueOf(map3.get(map3key));
            String map4value = String.valueOf(map4.get(map3key));

            editDetail = new EditDetail();
            editDetail.setKeyDictType("complaintAcceptVo");
            editDetail.setItemKey("callPerson."+map3key);
            editDetail.setEditId(editId);
            editDetail.setCreatedBy(SecurityUtils.getUsername());
            editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setUpdatedBy(SecurityUtils.getUsername());
            editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setValueDictType(tDictType.get(map3key));

            if((map3value == null || "".equals(map3value)) && (map4value !=null && !"".equals(map4value))){
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setNowValue(map4value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if((map4value == null || "".equals(map4value)) && (map3value !=null && !"".equals(map3value))){
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(map3value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if ((map3value !=null && !"".equals(map3value)) && (map4value !=null && !"".equals(map4value)) &&(!map3value.equals(map4value))) {
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(map3value);
                editDetail.setNowValue(map4value);
                editDetailMapper.insertEditDetail(editDetail);
            }

        }

        Map map5 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson1), Map.class);
        Map map6 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson), Map.class);
        Iterator<String> iter3 = map6.keySet().iterator();
        while(iter3.hasNext()){
            String map5key=iter3.next();
            if(!"".equals(tSpecialMap.get(map5key)) && tSpecialMap.get(map5key)!=null) {
                continue;
            }
            Object map5value = map5.get(map5key);
            Object map6value = map6.get(map5key);

            editDetail = new EditDetail();
            editDetail.setItemKey("contactsPerson." + map5key);
            editDetail.setKeyDictType("complaintAcceptVo");
            editDetail.setEditId(editId);
            editDetail.setCreatedBy(SecurityUtils.getUsername());
            editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setUpdatedBy(SecurityUtils.getUsername());
            editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setValueDictType(tDictType.get(map5key));

            if ((map5value == null || map5value.equals("")) && (map6value != null && !map6value.equals(""))) {
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setNowValue(String.valueOf(map6value));
                editDetailMapper.insertEditDetail(editDetail);
            } else if ((map6value == null || map6value.equals("")) && (map5value != null && !map5value.equals(""))) {
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(String.valueOf(map5value));
                editDetailMapper.insertEditDetail(editDetail);
            } else if ((map5value != null && !map5value.equals("")) && (map6value != null && !map6value.equals("")) && !map5value.equals(map6value)) {
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

            editDetail = new EditDetail();
            editDetail.setKeyDictType("complaintAcceptVo");
            editDetail.setItemKey("complainPerson." + map7key);
            editDetail.setEditId(editId);
            editDetail.setCreatedBy(SecurityUtils.getUsername());
            editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setUpdatedBy(SecurityUtils.getUsername());
            editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setValueDictType(tDictType.get(map7key));

            if((map7value == null || "".equals(map7value)) && (map8value != null && !"".equals(map8value))){
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setNowValue(map8value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if((map8value == null || "".equals(map8value)) && (map7value != null && !"".equals(map7value))){
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(map7value);
                editDetailMapper.insertEditDetail(editDetail);
            }else if ((map8value != null && !"".equals(map8value)) && (map7value != null && !"".equals(map7value)) && (!map7value.equals(map8value))) {
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(map7value);
                editDetail.setNowValue(map8value);
                editDetailMapper.insertEditDetail(editDetail);
            }
        }
        if(complaintAcceptVo.getEditInfo()!=null){
            EditInfo editInfo=new EditInfo();
            editInfo.setEditId(editId);
            editInfo.setWorkOrderId(workOrderNo);
            editInfo.setCreatedBy(SecurityUtils.getUsername());
            editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editInfo.setUpdatedBy(SecurityUtils.getUsername());
            editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editInfo.setEditRemark(complaintAcceptVo.getEditInfo().getEditRemark());
            editInfo.setEditReason(complaintAcceptVo.getEditInfo().getEditReason());
            editInfoMapper.insertEditInfo(editInfo);
        }


        //轨迹表插入
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeBy(SecurityUtils.getUsername());
        flowLog.setOperateCode("03");
        flowLog.setLinkCode(workOrderAccept.getStatus());
        flowLog.setSubId(editId);
        flowLog.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        //flowLogMapper.insertFlowLog(flowLog);

        return  complaintAcceptVoMapper.insertFlowLog(flowLog);
    }

    /**
     * 投诉处理   暂存
     * @param complaintDealVo
     * @return
     */
    @Override
    public int complaintHandling(ComplaintDealVo complaintDealVo) {
        WorkHandleInfo workHandleInfo=new WorkHandleInfo();
        workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
        workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
        WorkHandleInfo workHandleInfo1=workHandleInfoMapper.selectCreatedBy(workHandleInfo);
        if (workHandleInfo1==null) {

            //将所有状态置为N
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfoMapper.updateStatus(workHandleInfo);

            workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id", 10, 6)));
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("Y");
            workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
            workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfo.setRemark(complaintDealVo.getRemark());
            List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ComplaintDealVo");
            for (FieldMap fieldMap : KVMap) {
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map = new HashMap<String, String>();
                map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                VoUtils voUtils = new VoUtils<ComplaintDealVo>();
                workHandleInfo = (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo, map, complaintDealVo);
            }
            return workHandleInfoMapper.assistInComplaint(workHandleInfo);
        }else {

            //将所有状态置为N
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfoMapper.updateStatus(workHandleInfo);
            workHandleInfo.setHandleId(workHandleInfo1.getHandleId());
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("Y");
            workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ComplaintDealVo");
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
                VoUtils voUtils=new VoUtils<ComplaintDealVo>();
                workHandleInfo= (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo,map,complaintDealVo);}
            return workHandleInfoMapper.updateServiceProcessing(workHandleInfo);
        }
    }

    /**
     * 投诉处理   保存
     * @param complaintDealVo
     * @return
     */
    @Override
    public int complaintSaveHandling(ComplaintDealVo complaintDealVo) {
        WorkHandleInfo workHandleInfo=new WorkHandleInfo();
        workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
        workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
        workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        WorkHandleInfo workHandleInfos=workHandleInfoMapper.selectCreatedBy(workHandleInfo);


        //将所有状态置为N
        //WorkHandleInfo workHandleInfo = new WorkHandleInfo();
        if (complaintDealVo.getRootImprovement()==null) {
            //将所有数据置为n
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfoMapper.updateStatus(workHandleInfo);
            //修改主表状态为已完成
            WorkOrderAccept workOrderAccept=new WorkOrderAccept();
            workOrderAccept.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workOrderAccept.setStatus("04");
            workOrderAccept.setEndDate(DateUtils.parseDate(DateUtils.getTime()));
            workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);

            if (workHandleInfos == null) {
            workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id", 10, 6)));
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("Y");
            workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ComplaintDealVo");
            for (FieldMap fieldMap : KVMap) {
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map = new HashMap<String, String>();
                map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                VoUtils voUtils = new VoUtils<ComplaintDealVo>();
                workHandleInfo = (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo, map, complaintDealVo);
            }
             workHandleInfoMapper.assistInComplaint(workHandleInfo);
            }else{
                workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
                workHandleInfo.setHandleType("处理");
                workHandleInfo.setStatus("Y");
                workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
                workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
                workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ComplaintDealVo");
                for (FieldMap fieldMap : KVMap) {
                    fieldMap.getTargetColumnName();
                    fieldMap.getSourceFiledName();
                    Map map = new HashMap<String, String>();
                    map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                    VoUtils voUtils = new VoUtils<ComplaintDealVo>();
                    workHandleInfo = (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo, map, complaintDealVo);
                }
                workHandleInfoMapper.updateServiceProcessing(workHandleInfo);
            }

            //插入轨迹表
            FlowLog flowLog = new FlowLog();
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
            flowLog.setLinkCode(workOrderAccept.getStatus());
            flowLog.setOperateCode("08");
            flowLog.setMakeBy(SecurityUtils.getUsername());
            flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
            //没有um帐号
            flowLog.setUmNum(SecurityUtils.getUsername());
            flowLog.setCreatedBy(SecurityUtils.getUsername());
            flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setUpdatedBy(SecurityUtils.getUsername());
            flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            return  flowLogMapper.insertFlowLog(flowLog);
        } else {

            //将主表状态置为已完成
            String workOrderNo=complaintDealVo.getWorkOrderNo();
            WorkOrderAccept workOrderAccept1=workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
            WorkOrderAccept workOrderAccept2=workOrderAccept1;
            workOrderAccept1.setStatus("04");
            workOrderAccept1.setEndDate(DateUtils.parseDate(DateUtils.getTime()));
            workOrderAcceptMapper.updateWorkOrderStatus(workOrderAccept1);
            BeanUtils.copyProperties(workOrderAccept2,workOrderAccept1);
            String workOrderNo2="9900000000"+PubFun.createMySqlMaxNoUseCache("cs_work_order_no",10,6);
            workOrderAccept2.setWorkOrderNo(workOrderNo2);
            workOrderAccept2.setStatus("02");
            workOrderAccept2.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
            workOrderAccept2.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            workOrderAccept2.setUpdateBy(SecurityUtils.getUsername());
            workOrderAccept2.setCreateBy(SecurityUtils.getUsername());
            workOrderAccept2.setEndDate(DateUtils.parseDate(DateUtils.getTime()));
            workOrderAcceptMapper.insertWorkOrderAccept(workOrderAccept2);

            AcceptDetailInfo acceptDetailInfo1=acceptDetailInfoMapper.selectAcceptDetailInfoById(workOrderNo);
            //生成一条数据  状态置为根因改善
            AcceptDetailInfo acceptDetailInfo2=acceptDetailInfo1;
            BeanUtils.copyProperties(acceptDetailInfo2,acceptDetailInfo1);
            acceptDetailInfo2.setWorkOrderNo(workOrderNo2);
            acceptDetailInfo2.setStatus("02");
            acceptDetailInfo2.setItemCode("B00034");//置为根因改善
            acceptDetailInfo2.setCreateBy(SecurityUtils.getUsername());
            acceptDetailInfo2.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
            acceptDetailInfo2.setUpdateBy(SecurityUtils.getUsername());
            acceptDetailInfo2.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            acceptDetailInfoMapper.insertAcceptDetailInfo(acceptDetailInfo2);

            //将所有状态置为N
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfoMapper.updateStatus(workHandleInfo);
            // 第一条元数据转义
            if (workHandleInfos == null) {
                workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id", 10, 6)));
                workHandleInfo.setHandleType("处理");
                workHandleInfo.setStatus("Y");
                workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
                workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
                workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
                List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ComplaintDealVo");
                for (FieldMap fieldMap : KVMap) {
                    fieldMap.getTargetColumnName();
                    fieldMap.getSourceFiledName();
                    Map map = new HashMap<String, String>();
                    map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                    VoUtils voUtils = new VoUtils<ComplaintDealVo>();
                    workHandleInfo = (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo, map, complaintDealVo);
                }
                workHandleInfoMapper.assistInComplaint(workHandleInfo);
            }else{
                workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
                workHandleInfo.setHandleType("处理");
                workHandleInfo.setStatus("Y");
                workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
                workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
                workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ComplaintDealVo");
                for (FieldMap fieldMap : KVMap) {
                    fieldMap.getTargetColumnName();
                    fieldMap.getSourceFiledName();
                    Map map = new HashMap<String, String>();
                    map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                    VoUtils voUtils = new VoUtils<ComplaintDealVo>();
                    workHandleInfo = (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo, map, complaintDealVo);
                }
                workHandleInfoMapper.updateServiceProcessing(workHandleInfo);
            }

            FlowLog flowLog2 = new FlowLog();
            flowLog2.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
            flowLog2.setLinkCode(workOrderAccept2.getStatus());
            flowLog2.setOperateCode("01");
            flowLog2.setMakeBy(SecurityUtils.getUsername());
            flowLog2.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
            //没有um帐号
            flowLog2.setUmNum(SecurityUtils.getUsername());
            flowLog2.setCreatedBy(SecurityUtils.getUsername());
            flowLog2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog2.setUpdatedBy(SecurityUtils.getUsername());
            flowLog2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog2.setWorkOrderNo(workOrderAccept2.getWorkOrderNo());
            flowLogMapper.insertFlowLog(flowLog2);

            //插入轨迹表
            FlowLog flowLog = new FlowLog();
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
            flowLog.setLinkCode(workOrderAccept1.getStatus());
            flowLog.setOperateCode("08");
            flowLog.setMakeBy(SecurityUtils.getUsername());
            flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
            //没有um帐号
            flowLog.setUmNum(SecurityUtils.getUsername());
            flowLog.setCreatedBy(SecurityUtils.getUsername());
            flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setUpdatedBy(SecurityUtils.getUsername());
            flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setWorkOrderNo(complaintDealVo.getWorkOrderNo());

            return flowLogMapper.insertFlowLog(flowLog);
        }
    }
    @Override
    public List<ComplaintsCascade> selectComplaintsCascadeList() {
        List level1 =new ArrayList<ComplaintsCascade>();
        List level2 =new ArrayList<Level2>();
        //获取一级投诉分类
        List<Level3> level1s=complaintAcceptVoMapper.selectLevel1();
        for(Level3 l :level1s) {
            ComplaintsCascade adress = new ComplaintsCascade();
            adress.setLevel1code(l.getCode());
            adress.setLevel1name(l.getCodeNmae());
            List<Level3> level2s = complaintAcceptVoMapper.selectLevel2(l.getCode());
            for (Level3 city : level2s) {
                Level2 level2Info = new Level2();
                level2Info.setLevel2code(city.getCode());
                level2Info.setLevel2name(city.getCodeNmae());
                List<Level3> level3 = complaintAcceptVoMapper.selectLevel2(city.getCode());
                level2Info.setLevel3(level3);
                level2.add(level2Info);
            }
            adress.setLevel2(level2);
            level1.add(adress);
        }
        return level1;
    }


    @Override
    public List<Level3> selectLevel1()
    {
        return complaintAcceptVoMapper.selectLevel1();
    }


    @Override
    public List<Level3> selectLevel2(String parentCode)
    {
        return complaintAcceptVoMapper.selectLevel2(parentCode);
    }

    @Override
    public ComplaintDealVo selectServiceProcess(String workOrderNo) {
        return workOrderAcceptMapper.selectServiceProcess(workOrderNo);
    }


}
