package com.paic.ehis.cs.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;

import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.PolicyListVo;
import com.paic.ehis.cs.domain.vo.WorkOrderVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IDemandAcceptVoService;
import com.paic.ehis.cs.utils.CodeEnum;
import com.paic.ehis.cs.utils.VoUtils;
import com.paic.ehis.system.api.ClaimFlowService;
import com.paic.ehis.system.api.domain.dto.ClaimFlowDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class DemandAcceptVoServiceimpl implements IDemandAcceptVoService {
    @Autowired
    private DemandAcceptVoMapper demandAcceptVoMapper;
    @Autowired
    private PersonInfoMapper personInfoMapper;
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
    private HcsModificationMapper hcsModificationMapper;

    @Autowired
    private ClaimFlowService claimFlowService;

    /**
     * 信息需求公共池实现
     * @param workOrderQueryDTO
     * @return
     */
    @Override
    public List<DemandAcceptVo> selectDemandAcceptList(WorkOrderQueryDTO workOrderQueryDTO) {
        List<DemandAcceptVo> demandAcceptVos = demandAcceptVoMapper.selectDemandAcceptVoList(workOrderQueryDTO);
        String sourceName = "DemandAcceptVo";
        String targetTableName = "accept_detail_info";
        List<FieldMap> KVMap = fieldMapMapper.selectKVMap(targetTableName, sourceName);
        for (DemandAcceptVo demandAcceptVo1 : demandAcceptVos) {
            PersonInfo callPerson = personInfoMapper.selectPersonInfoById(demandAcceptVo1.getCallPersonId());
            if (callPerson != null) {
                demandAcceptVo1.setCallPerson(callPerson);
            } else {
                demandAcceptVo1.setCallPerson(new PersonInfo());
            }
            PersonInfo contactsPerson = personInfoMapper.selectPersonInfoById(demandAcceptVo1.getContactsPersonId());
            if (contactsPerson != null) {
                //  demandAcceptVo1.setContactsPerson(contactsPerson);
                String linePhone = contactsPerson.getLinePhone();
                String[] linePhone1 = StringUtils.isEmpty(linePhone) ? new String[4] : linePhone.split("\\-");
                contactsPerson.setLinePhone1(linePhone1);
                String homePhone = contactsPerson.getHomePhone();
                String[] homePhone1 = StringUtils.isEmpty(homePhone) ? new String[4] : homePhone.split("\\-");
                contactsPerson.setHomePhone1(homePhone1);
                String workPhone = contactsPerson.getWorkPhone();
                String[] workPhone1 = StringUtils.isEmpty(workPhone) ? new String[4] : workPhone.split("\\-");
                contactsPerson.setWorkPhone1(workPhone1);
                demandAcceptVo1.setContactsPerson(contactsPerson);

            } else {
                demandAcceptVo1.setContactsPerson(new PersonInfo());
            }

            AcceptDetailInfo acceptDetailInfo = acceptDetailInfoMapper.selectAcceptDetailInfoById(demandAcceptVo1.getWorkOrderNo());
            if (acceptDetailInfo == null) {
                continue;
            }
            for (FieldMap fieldMap : KVMap) {
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map = new HashMap<String, String>();
                map.put(fieldMap.getSourceFiledName(), fieldMap.getTargetColumnName());
                VoUtils voUtils = new VoUtils<DemandAcceptVo>();
                demandAcceptVo1 = (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo1, map, acceptDetailInfo);
            }
        }
        return demandAcceptVos;
    }

    /**
     * 信息需求个人池实现
     * @param workOrderQueryDTO
     * @return
     */
    @Override
    public List<DemandAcceptVo> selectDemandAcceptList2(WorkOrderQueryDTO workOrderQueryDTO) {

        List<DemandAcceptVo> demandAcceptVos = demandAcceptVoMapper.selectDemandAcceptVoList2(workOrderQueryDTO);
        String sourceName = "DemandAcceptVo";
        String targetTableName = "accept_detail_info";
        List<FieldMap> KVMap = fieldMapMapper.selectKVMap(targetTableName, sourceName);
        for (DemandAcceptVo demandAcceptVo1 : demandAcceptVos) {
            PersonInfo callPerson = personInfoMapper.selectPersonInfoById(demandAcceptVo1.getCallPersonId());
            if (callPerson != null) {
                demandAcceptVo1.setCallPerson(callPerson);
            } else {
                demandAcceptVo1.setCallPerson(new PersonInfo());
            }
            PersonInfo contactsPerson = personInfoMapper.selectPersonInfoById(demandAcceptVo1.getContactsPersonId());

            if (contactsPerson != null) {
                //  demandAcceptVo1.setContactsPerson(contactsPerson);
                String linePhone = contactsPerson.getLinePhone();
                String[] linePhone1 = StringUtils.isEmpty(linePhone) ? new String[4] : linePhone.split("\\-");
                contactsPerson.setLinePhone1(linePhone1);
                String homePhone = contactsPerson.getHomePhone();
                String[] homePhone1 = StringUtils.isEmpty(homePhone) ? new String[4] : homePhone.split("\\-");
                contactsPerson.setHomePhone1(homePhone1);
                String workPhone = contactsPerson.getWorkPhone();
                String[] workPhone1 = StringUtils.isEmpty(workPhone) ? new String[4] : workPhone.split("\\-");
                contactsPerson.setWorkPhone1(workPhone1);
                demandAcceptVo1.setContactsPerson(contactsPerson);

            } else {
                demandAcceptVo1.setContactsPerson(new PersonInfo());
            }

            AcceptDetailInfo acceptDetailInfo = acceptDetailInfoMapper.selectAcceptDetailInfoById(demandAcceptVo1.getWorkOrderNo());
            if (acceptDetailInfo == null) {
                continue;
            }
            for (FieldMap fieldMap : KVMap) {
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map = new HashMap<String, String>();
                map.put(fieldMap.getSourceFiledName(), fieldMap.getTargetColumnName());
                VoUtils voUtils = new VoUtils<DemandAcceptVo>();
                demandAcceptVo1 = (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo1, map, acceptDetailInfo);
            }
        }
        return demandAcceptVos;
    }

    @Override
    public DemandAcceptVo selectDemandAcceptVo(String workOrderNo) {
        DemandAcceptVo demandAcceptVo = demandAcceptVoMapper.selectDemandAcceptVo(workOrderNo);
        String sourceName = "DemandAcceptVo";
        String targetTableName = "accept_detail_info";
        List<FieldMap> KVMap = fieldMapMapper.selectKVMap(targetTableName, sourceName);
        PersonInfo callPerson = personInfoMapper.selectPersonInfoById(demandAcceptVo.getCallPersonId());
        if (callPerson != null) {
            demandAcceptVo.setCallPerson(callPerson);
        } else {
            demandAcceptVo.setCallPerson(new PersonInfo());
        }
        PersonInfo contactsPerson = personInfoMapper.selectPersonInfoById(demandAcceptVo.getContactsPersonId());
        if (contactsPerson != null) {
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
            demandAcceptVo.setContactsPerson(contactsPerson);
        } else {
            demandAcceptVo.setContactsPerson(new PersonInfo());
        }
        AcceptDetailInfo acceptDetailInfo = acceptDetailInfoMapper.selectAcceptDetailInfoById(demandAcceptVo.getWorkOrderNo());
        for (FieldMap fieldMap : KVMap) {
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map = new HashMap<String, String>();
            map.put(fieldMap.getSourceFiledName(), fieldMap.getTargetColumnName());
            VoUtils voUtils = new VoUtils<DemandAcceptVo>();
            demandAcceptVo = (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo, map, acceptDetailInfo);
        }
        return demandAcceptVo;

    }

    @Override
    public int updateStatus(String workOrderNo) {
        WorkOrderAccept workOrderAccept=workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
        workOrderAccept.setStatus("02");
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.getNowDate());

        //投诉获取后更新受理人
        workOrderAccept.setAcceptBy(SecurityUtils.getUsername());

        return workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
    }

    @Override
    public int updateStatusM(String[] workOrderNos) {
        List list= CollectionUtils.arrayToList(workOrderNos);
        int count=0;
        for (int i=0;i< list.size();i++){
            WorkOrderAccept workOrderAccept=workOrderAcceptMapper.selectWorkOrderAcceptById(String.valueOf(list.get(i)));
            workOrderAccept.setStatus("02");
            workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
            workOrderAccept.setUpdateTime(DateUtils.getNowDate());

            //投诉获取后更新受理人
            workOrderAccept.setAcceptBy(SecurityUtils.getUsername());

            workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
            count ++;
        }
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int insertServiceInfo(DemandAcceptVo demandAcceptVo) {
        AcceptDetailInfo acceptDetailInfo = new AcceptDetailInfo();
        PersonInfo personInfo1 = new PersonInfo();
        PersonInfo personInfo2 = new PersonInfo();
        FlowLog flowLog = new FlowLog();
        WorkOrderAccept workOrderAccept = new WorkOrderAccept();
        if(StringUtils.isNotEmpty(demandAcceptVo.getPolicyItemNo()) && StringUtils.isNotEmpty(demandAcceptVo.getPolicyNo())){
            ClaimFlowDTO claimFlowDTO=new ClaimFlowDTO();
            claimFlowDTO.setPolicyNo(demandAcceptVo.getPolicyNo());
            claimFlowDTO.setPolicyItemNo(demandAcceptVo.getPolicyItemNo());
            AjaxResult policyInfoBy = claimFlowService.getPolicyInfoBy(claimFlowDTO);
            if(policyInfoBy.get("data")!=null){
                ObjectMapper mapper = new ObjectMapper();
                PolicyListVo policyListVo = mapper.convertValue(policyInfoBy.get("data"), PolicyListVo.class);
                workOrderAccept.setPolicyNo(demandAcceptVo.getPolicyNo());
                workOrderAccept.setPolicyItemNo(demandAcceptVo.getPolicyItemNo());
                workOrderAccept.setRiskCode(policyListVo.getRiskCodesStr());
                workOrderAccept.setInsuredName(policyListVo.getName());
                workOrderAccept.setHolderName(policyListVo.getAppName());
                //被保人信息保存
                String insuredId = PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10);
                PersonInfo insuredPerson=new PersonInfo();
                insuredPerson.setPersonId(insuredId);
                workOrderAccept.setInsuredNo(insuredId);
                insuredPerson.setName(policyListVo.getName());
                insuredPerson.setEmail(policyListVo.getEmail());
                insuredPerson.setBirthday(policyListVo.getBirthday());
                insuredPerson.setProvince(policyListVo.getProvince());
                insuredPerson.setCity(policyListVo.getCity());
                insuredPerson.setDistrict(policyListVo.getDistrict());
                insuredPerson.setAddress(policyListVo.getAddress());
                insuredPerson.setIdType(policyListVo.getIdType());
                insuredPerson.setIdNumber(policyListVo.getIdNo());
                insuredPerson.setSex(policyListVo.getSex());
                if(policyListVo.getPhone() == null || "".equals(policyListVo.getPhone())){
                    insuredPerson.setLinePhone("---");
                }else{
                    insuredPerson.setLinePhone(policyListVo.getPhone());
                }
                insuredPerson.setOtherCustomerNo(policyListVo.getInsuredNo());
                insuredPerson.setCreatedBy(SecurityUtils.getUsername());
                insuredPerson.setUpdatedBy(SecurityUtils.getUsername());
                insuredPerson.setCreatedTime(DateUtils.getNowDate());
                insuredPerson.setUpdatedTime(DateUtils.getNowDate());
                personInfoMapper.insertPersonInfo(insuredPerson);
            }
        }
        //工单表插入
        workOrderAccept.setStatus(CodeEnum.ORDER_STATE_01.getCode());
        workOrderAccept.setOrganCode(demandAcceptVo.getOrganCode());
        workOrderAccept.setAcceptBy(SecurityUtils.getUsername());
        workOrderAccept.setAcceptTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setCreateBy(SecurityUtils.getUsername());
        workOrderAccept.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        workOrderAccept.setBusinessType(demandAcceptVo.getBusinessType());
        demandAcceptVoMapper.insertWorkOrderAccept(workOrderAccept);

        acceptDetailInfo.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        acceptDetailInfo.setCallCenterId(demandAcceptVo.getCallCenterId());
        acceptDetailInfo.setChannelCode(demandAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(demandAcceptVo.getItemCode());
        acceptDetailInfo.setCallPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        acceptDetailInfo.setCallRelationBy(demandAcceptVo.getCallRelationBy());
        acceptDetailInfo.setPriorityLevel(demandAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10));
        acceptDetailInfo.setContactsRelationBy(demandAcceptVo.getContactsRelationBy());
        acceptDetailInfo.setEmail(demandAcceptVo.getEmail());
        acceptDetailInfo.setContent(demandAcceptVo.getContent());
        acceptDetailInfo.setStatus("01");
        acceptDetailInfo.setCreateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        List<FieldMap> KVMap = fieldMapMapper.selectKVMap("accept_detail_info", "DemandAcceptVo");
        for (FieldMap fieldMap : KVMap) {
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map = new HashMap<String, String>();
            map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
            VoUtils voUtils = new VoUtils<DemandAcceptVo>();
            acceptDetailInfo = (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo, map, demandAcceptVo);
        }
        //详细表插入
        demandAcceptVoMapper.insertAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
        personInfo1.setPersonId(acceptDetailInfo.getCallPersonId());
        personInfo1.setName(demandAcceptVo.getCallName());
        personInfo1.setMobilePhone(demandAcceptVo.getCallMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(acceptDetailInfo.getContactsPersonId());
        personInfo2.setSex(demandAcceptVo.getContactsSex());
        personInfo2.setName(demandAcceptVo.getContactsName());
        personInfo2.setLanguage(demandAcceptVo.getContactsLanguage());
        personInfo2.setMobilePhone(demandAcceptVo.getContactsMobilePhone());
        String tLinePhone = StringUtils.isNotEmpty(demandAcceptVo.getContactsCountry())?demandAcceptVo.getContactsCountry():"";
        tLinePhone += "-";
        tLinePhone += StringUtils.isNotEmpty(demandAcceptVo.getContactsQuhao())?demandAcceptVo.getContactsQuhao():"";
        tLinePhone += "-";
        tLinePhone += StringUtils.isNotEmpty(demandAcceptVo.getContactsNumber())?demandAcceptVo.getContactsNumber():"";
        tLinePhone += "-";
        tLinePhone += StringUtils.isNotEmpty(demandAcceptVo.getContactsSecondNumber())?demandAcceptVo.getContactsSecondNumber():"";
        personInfo2.setLinePhone(tLinePhone);
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.insertPersonInfo(personInfo2);

        //轨迹表插入
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeBy(SecurityUtils.getUsername());
        flowLog.setLinkCode("04");
        flowLog.setOperateCode("01");
        flowLog.setStatus("01");
        flowLog.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
//        demandAcceptVoMapper.insertFlowLog(flowLog);


        return demandAcceptVoMapper.insertFlowLog(flowLog);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int updateServiceInfo(DemandAcceptVo demandAcceptVo) {
        String workOrderNo = demandAcceptVo.getWorkOrderNo();
        DemandAcceptVo demandAcceptVo1 = demandAcceptVoMapper.selectDemandAcceptVoById(workOrderNo);
        AcceptDetailInfo acceptDetailInfo = acceptDetailInfoMapper.selectAcceptDetailInfoById(workOrderNo);
        PersonInfo callPerson1 = personInfoMapper.selectPersonInfoById(demandAcceptVo.getCallPersonId());
        PersonInfo callPerson = new PersonInfo();
        BeanUtils.copyProperties(callPerson1, callPerson);
        PersonInfo contactsPerson1 = personInfoMapper.selectPersonInfoById(demandAcceptVo.getContactsPersonId());
        PersonInfo contactsPerson = new PersonInfo();
        BeanUtils.copyProperties(contactsPerson1, contactsPerson);
        FlowLog flowLog = new FlowLog();
        WorkOrderAccept workOrderAccept = workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
        workOrderAccept.setOrganCode(demandAcceptVo.getOrganCode());
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
        acceptDetailInfo.setChannelCode(demandAcceptVo.getChannelCode());
        acceptDetailInfo.setCallCenterId(demandAcceptVo.getCallCenterId());
        acceptDetailInfo.setPriorityLevel(demandAcceptVo.getPriorityLevel());
        acceptDetailInfo.setEmail(demandAcceptVo.getEmail());
        acceptDetailInfo.setBankTransfer(demandAcceptVo.getBankTransfer());
        acceptDetailInfo.setBankHolder(demandAcceptVo.getBankHolder());
        acceptDetailInfo.setBankLocation(demandAcceptVo.getBankLocation());
        acceptDetailInfo.setAccountNumber(demandAcceptVo.getAccountNumber());
        acceptDetailInfo.setBankName(demandAcceptVo.getBankName());
        acceptDetailInfo.setContent(demandAcceptVo.getContent());
        List<FieldMap> KVMap = fieldMapMapper.selectKVMap("accept_detail_info", "DemandAcceptVo");
        for (FieldMap fieldMap : KVMap) {
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map = new HashMap<String, String>();
            map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
            VoUtils voUtils = new VoUtils<DemandAcceptVo>();
            acceptDetailInfo = (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo, map, demandAcceptVo);
        }
        //详细表插入
        acceptDetailInfoMapper.updateAcceptDetailInfo(acceptDetailInfo);

        //插入来电人
        callPerson.setName(demandAcceptVo.getCallPerson().getName());
        callPerson.setMobilePhone(demandAcceptVo.getCallPerson().getMobilePhone());
        callPerson.setUpdatedBy(SecurityUtils.getUsername());
        callPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(callPerson);

        //插入联系人
        contactsPerson.setSex(demandAcceptVo.getContactsPerson().getSex());
        contactsPerson.setName(demandAcceptVo.getContactsPerson().getName());
        contactsPerson.setLanguage(demandAcceptVo.getContactsPerson().getLanguage());
        contactsPerson.setMobilePhone(demandAcceptVo.getContactsPerson().getMobilePhone());
        if(demandAcceptVo.getContactsPerson().getLinePhone1().length<=3){
            contactsPerson.setLinePhone("---");
        }else{
            contactsPerson.setLinePhone(demandAcceptVo.getContactsPerson().getLinePhone1()[0] + "-" + demandAcceptVo.getContactsPerson().getLinePhone1()[1] + "-" + demandAcceptVo.getContactsPerson().getLinePhone1()[2] + "-" + demandAcceptVo.getContactsPerson().getLinePhone1()[3]);
        }
        contactsPerson.setUpdatedBy(SecurityUtils.getUsername());
        contactsPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(contactsPerson);


        String editId = PubFun.createMySqlMaxNoUseCache("cs_edit_id", 10, 8);
        EditDetail editDetail = new EditDetail();
        Map map1 = JSONObject.parseObject(JSONObject.toJSONString(demandAcceptVo1), Map.class);
        Map map2 = JSONObject.parseObject(JSONObject.toJSONString(demandAcceptVo), Map.class);

        List outList = new ArrayList();
        outList.add("businessService");
        outList.add("umCode");
        outList.add("alterId");
        outList.add("businessType");
        outList.add("businessService");
        outList.add("acceptTime");
        outList.add("modifyTime");
        outList.add("status");
        outList.add("callName");
        outList.add("callMobilePhone");
        outList.add("contactsSex");
        outList.add("contactsName");
        outList.add("contactsLanguage");
        outList.add("contactsMobilePhone");
        outList.add("contactsCountry");
        outList.add("contactsQuhao");
        outList.add("contactsNumber");
        outList.add("contactsSecondNumber");
        outList.add("officeCountry");
        outList.add("officeQuhao");
        outList.add("officeNumber");
        outList.add("officeSecondNumber");
        outList.add("editReason");
        outList.add("editRemark");
        outList.add("solicitOpinion");
        outList.add("reason");
        outList.add("CollaborativeId");
        outList.add("opinion");
        outList.add("validFlag");
        outList.add("nonReason");
        outList.add("treatmentPlan");
        outList.add("treatmentBasis");
        outList.add("isRedWord");
        outList.add("editInfo");
        outList.add("callPerson");
        outList.add("contactsPerson");

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
            if (!keyList.contains(key)) {
                if(tSpecialMap.get(key)==null || "".equals(tSpecialMap.get(key))){
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
                editDetail.setKeyDictType("demandAcceptVo");
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
        List<String> keyList1 = new ArrayList<>();
        Iterator<String> iter3 = map3.keySet().iterator();
        while (iter3.hasNext()) {
            String key = iter3.next();
            if(tSpecialMap.get(key)==null || "".equals(tSpecialMap.get(key))){
                keyList1.add(key);
            }
        }
        Iterator<String> iter4 = map4.keySet().iterator();
        while (iter4.hasNext()) {
            String key = iter4.next();
            if(tSpecialMap.get(key)==null || "".equals(tSpecialMap.get(key))){
                if (!keyList1.contains(key)) {
                    keyList1.add(key);
                }
            }
        }
        for (int j = 0; j < keyList1.size(); j++) {
            String map3key = keyList1.get(j);
            Object map3value = map3.get(map3key);
            Object map4value = map4.get(map3key);

            editDetail = new EditDetail();
            editDetail.setItemKey("callPerson."+map3key);
            editDetail.setKeyDictType("demandAcceptVo");
            editDetail.setEditId(editId);
            editDetail.setCreatedBy(SecurityUtils.getUsername());
            editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setUpdatedBy(SecurityUtils.getUsername());
            editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            editDetail.setValueDictType(tDictType.get(map3key));

            if ((map3value == null || map3value.equals("")) && (map4value != null && !map4value.equals(""))) {
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setNowValue(String.valueOf(map4value));
                editDetailMapper.insertEditDetail(editDetail);
            } else if ((map4value == null || map4value.equals("")) && (map3value != null && !map3value.equals(""))) {
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(String.valueOf(map3value));
                editDetailMapper.insertEditDetail(editDetail);
            } else if ((map3value != null && !map3value.equals("")) && (map4value != null && !map4value.equals("")) && !map3value.equals(map4value)) {
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setOldValue(String.valueOf(map3value));
                editDetail.setNowValue(String.valueOf(map4value));
                editDetailMapper.insertEditDetail(editDetail);
            }


        }

        Map map5 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson1), Map.class);
        Map map6 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson), Map.class);
        List<String> keyList2 = new ArrayList<>();
        Iterator<String> iter5 = map5.keySet().iterator();
        while (iter5.hasNext()) {
            String key = iter5.next();
            if(tSpecialMap.get(key)==null || "".equals(tSpecialMap.get(key))){
                keyList2.add(key);
            }
        }
        Iterator<String> iter6 = map6.keySet().iterator();
        while (iter6.hasNext()) {
            String key = iter6.next();
            if(tSpecialMap.get(key)==null || "".equals(tSpecialMap.get(key))){
                if (!keyList2.contains(key)) {
                    keyList2.add(key);
                }
            }
        }
        for (int k = 0; k < keyList2.size(); k++) {
            String map5key = keyList2.get(k);
            Object map5value = map5.get(map5key);
            Object map6value = map6.get(map5key);

            editDetail = new EditDetail();
            editDetail.setItemKey("contactsPerson."+map5key);
            editDetail.setKeyDictType("demandAcceptVo");
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

        EditInfo editInfo1 = new EditInfo();
        editInfo1.setEditId(editId);
        editInfo1.setWorkOrderId(workOrderNo);
        editInfo1.setCreatedBy(SecurityUtils.getUsername());
        editInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo1.setUpdatedBy(SecurityUtils.getUsername());
        editInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo1.setEditRemark(demandAcceptVo.getEditInfo().getEditRemark());
        editInfo1.setEditReason(demandAcceptVo.getEditInfo().getEditReason());
        editInfoMapper.insertEditInfo(editInfo1);

        //轨迹表插入
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeBy(SecurityUtils.getUsername());
        flowLog.setOperateCode("03");
        flowLog.setStatus(workOrderAccept.getStatus());
        flowLog.setSubId(editId);
        flowLog.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));

        //Hcs
        if (demandAcceptVo.getAlterId() != null) {
            hcsModificationMapper.updateHcsStauts(demandAcceptVo.getAlterId());
        }
        return demandAcceptVoMapper.insertFlowLog(flowLog);
    }

    /**
     * 协办工作池查询
     *
     * @param workOrderQueryDTO
     * @return
     */
    @Override
    public List<DemandAcceptVo> selectAssist(WorkOrderQueryDTO workOrderQueryDTO) {
        return demandAcceptVoMapper.selectAssist(workOrderQueryDTO);
    }


    /**
     * 工单查询
     * @param acceptDTO
     * @return
     */
    @Override
    public List<WorkOrderVo> selectWorkOrder(AcceptDTO acceptDTO) {
        //默认显示30个自然日的工单任务
        if(acceptDTO.getAcceptTimeStart()==null){
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) -30);

            acceptDTO.setAcceptTimeStart(calendar.getTime());
            acceptDTO.setAcceptTimeEnd(DateUtils.parseDate(DateUtils.getTime()));

        }
        return demandAcceptVoMapper.selectWorkOrder(acceptDTO);
    }


}
