package com.paic.ehis.cs.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;

import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.WorkOrderVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IDemandAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.BeanUtils;
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


    @Override
    public List<DemandAcceptVo> selectDemandAcceptList(AcceptDTO acceptDTO) {
        List<DemandAcceptVo> demandAcceptVos = demandAcceptVoMapper.selectDemandAcceptVoList(acceptDTO);
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
    public List<DemandAcceptVo> selectDemandAcceptList2(AcceptDTO acceptDTO) {
        acceptDTO.setUpdateBy(SecurityUtils.getUsername());
        List<DemandAcceptVo> demandAcceptVos = demandAcceptVoMapper.selectDemandAcceptVoList2(acceptDTO);
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
        return demandAcceptVoMapper.updateStatus(workOrderNo);
    }

    @Override
    public int updateStatusM(String[] workOrderNos) {
        return demandAcceptVoMapper.updateStatusM(workOrderNos);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int insertServiceInfo(DemandAcceptVo demandAcceptVo) {
        AcceptDetailInfo acceptDetailInfo = new AcceptDetailInfo();
        PersonInfo personInfo1 = new PersonInfo();
        PersonInfo personInfo2 = new PersonInfo();
        FlowLog flowLog = new FlowLog();
        WorkOrderAccept workOrderAccept = new WorkOrderAccept();
        //工单表插入
        workOrderAccept.setStatus("01");

        workOrderAccept.setHolderName(demandAcceptVo.getHolderName());
        workOrderAccept.setOrganCode(demandAcceptVo.getOrganCode());
        workOrderAccept.setAcceptTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setModifyTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setCreateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setWorkOrderNo(demandAcceptVo.getWorkOrderNo());
        workOrderAccept.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
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
        demandAcceptVoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(acceptDetailInfo.getContactsPersonId());
        personInfo2.setSex(demandAcceptVo.getContactsSex());
        personInfo2.setName(demandAcceptVo.getContactsName());
        personInfo2.setLanguage(demandAcceptVo.getContactsLanguage());
        personInfo2.setMobilePhone(demandAcceptVo.getContactsMobilePhone());
        if (demandAcceptVo.getContactsCountry() != null && demandAcceptVo.getContactsCountry() != "") {
            personInfo2.setLinePhone(demandAcceptVo.getContactsCountry() + "-" + demandAcceptVo.getContactsQuhao() + "-" + demandAcceptVo.getContactsNumber() + "-" + demandAcceptVo.getContactsSecondNumber());
        }
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        demandAcceptVoMapper.insertPersonInfo(personInfo2);

        //轨迹表插入
        flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("cs_flow_id", 20, 20));
        flowLog.setMakeTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setMakeBy(SecurityUtils.getUsername());
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
     //   PersonInfo callPerson = personInfoMapper.selectPersonInfoById(demandAcceptVo.getCallPersonId());
        PersonInfo callPerson1 = personInfoMapper.selectPersonInfoById(demandAcceptVo.getCallPersonId());
        PersonInfo callPerson=new PersonInfo();
        BeanUtils.copyProperties(callPerson1, callPerson);
     //   PersonInfo contactsPerson = personInfoMapper.selectPersonInfoById(demandAcceptVo.getContactsPersonId());
        PersonInfo contactsPerson1 = personInfoMapper.selectPersonInfoById(demandAcceptVo.getContactsPersonId());
        contactsPerson1.setLinePhone("");
        PersonInfo contactsPerson=new PersonInfo();
        BeanUtils.copyProperties(contactsPerson1, contactsPerson);
        FlowLog flowLog = new FlowLog();
        WorkOrderAccept workOrderAccept = workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);

        workOrderAccept.setOrganCode(demandAcceptVo.getOrganCode());
        //工单表修改
//        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
//        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);


//        acceptDetailInfo.setUpdateBy(SecurityUtils.getUsername());
//        acceptDetailInfo.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
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
//        callPerson.setUpdatedBy(SecurityUtils.getUsername());
//        callPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(callPerson);

        //插入联系人
        contactsPerson.setSex(demandAcceptVo.getContactsPerson().getSex());
        contactsPerson.setName(demandAcceptVo.getContactsPerson().getName());
        contactsPerson.setLanguage(demandAcceptVo.getContactsPerson().getLanguage());
        contactsPerson.setMobilePhone(demandAcceptVo.getContactsPerson().getMobilePhone());
        contactsPerson.setLinePhone(demandAcceptVo.getContactsPerson().getLinePhone1()[0] + "-" + demandAcceptVo.getContactsPerson().getLinePhone1()[1] + "-" + demandAcceptVo.getContactsPerson().getLinePhone1()[2] + "-" + demandAcceptVo.getContactsPerson().getLinePhone1()[3]);
      //  contactsPerson.setHomePhone(demandAcceptVo.getContactsPerson().getHomePhone1()[0] + "-" + demandAcceptVo.getContactsPerson().getHomePhone1()[1] + "-" + demandAcceptVo.getContactsPerson().getHomePhone1()[2] + "-" + demandAcceptVo.getContactsPerson().getHomePhone1()[3]);
      //  contactsPerson.setWorkPhone(demandAcceptVo.getContactsPerson().getWorkPhone1()[0] + "-" + demandAcceptVo.getContactsPerson().getWorkPhone1()[1] + "-" + demandAcceptVo.getContactsPerson().getWorkPhone1()[2] + "-" + demandAcceptVo.getContactsPerson().getWorkPhone1()[3]);
//        contactsPerson.setUpdatedBy(SecurityUtils.getUsername());
//        contactsPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(contactsPerson);



        String editId = PubFun.createMySqlMaxNoUseCache("cs_edit_id", 10, 8);
        Map map1 = JSONObject.parseObject(JSONObject.toJSONString(demandAcceptVo1), Map.class);
        Map map2 = JSONObject.parseObject(JSONObject.toJSONString(demandAcceptVo), Map.class);

        List<String> keyList = new ArrayList<>();
        Iterator<String> iter1 = map1.keySet().iterator();
        while (iter1.hasNext()) {
            EditDetail editDetail = new EditDetail();
            //    EditInfo editInfo=new EditInfo();
            String map1key = iter1.next();
            String map1value = String.valueOf(map1.get(map1key));
            String map2value = String.valueOf(map2.get(map1key));
            if (!map1value.equals(map2value)) {
                keyList.add(map1key);
                editDetail.setKeyDictType("demandAcceptVo");
                editDetail.setItemKey(map1key);
                editDetail.setOldValue(map1value);
                editDetail.setNowValue(map2value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setEditId(editId);
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetailMapper.insertEditDetail(editDetail);
            }
        }
        //
        Map map3 = JSONObject.parseObject(JSONObject.toJSONString(callPerson1), Map.class);
        Map map4 = JSONObject.parseObject(JSONObject.toJSONString(callPerson), Map.class);

        //     Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(acceptDetailInfo1));


        Iterator<String> iter2 = map3.keySet().iterator();
        while (iter2.hasNext()) {
            EditDetail editDetail = new EditDetail();
            //    EditInfo editInfo=new EditInfo();
            String map3key = iter2.next();
            String map3value = String.valueOf(map3.get(map3key));
            String map4value = String.valueOf(map4.get(map3key));

            if (!map3value.equals(map4value)) {
                keyList.add(map3key);
                editDetail.setKeyDictType("demandAcceptVo");
                editDetail.setItemKey("callPerson."+map3key);
                editDetail.setOldValue(map3value);
                editDetail.setNowValue(map4value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                editDetail.setEditId(editId);
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetailMapper.insertEditDetail(editDetail);
            }
        }

        Map map5 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson1), Map.class);
        Map map6 = JSONObject.parseObject(JSONObject.toJSONString(contactsPerson), Map.class);

        //     Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(acceptDetailInfo1));


        Iterator<String> iter3 = map6.keySet().iterator();
        while (iter3.hasNext()) {
            EditDetail editDetail = new EditDetail();
            String map5key = iter3.next();
            String map5value = String.valueOf(map5.get(map5key));
            String map6value = String.valueOf(map6.get(map5key));
            if (!map5value.equals(map6value)) {
                keyList.add(map5key);
                editDetail.setKeyDictType("demandAcceptVo");
                editDetail.setItemKey("contactsPerson."+map5key);
                editDetail.setOldValue(map5value);
                editDetail.setNowValue(map6value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id", 10, 8));
                //  editDetail.setEditId(edit);
                editDetail.setEditId(editId);
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetailMapper.insertEditDetail(editDetail);
            }
        }
        EditInfo editInfo = new EditInfo();
        editInfo.setEditId(editId);
        editInfo.setWorkOrderId(workOrderNo);
        editInfo.setCreatedBy(SecurityUtils.getUsername());
        editInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setUpdatedBy(SecurityUtils.getUsername());
        editInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        editInfo.setEditRemark(demandAcceptVo.getEditInfo().getEditRemark());
        editInfo.setEditReason(demandAcceptVo.getEditInfo().getEditReason());
        editInfoMapper.insertEditInfo(editInfo);

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
     * @param acceptDTO
     * @return
     */
    @Override
    public List<DemandAcceptVo> selectAssist(AcceptDTO acceptDTO) {
        return demandAcceptVoMapper.selectAssist(acceptDTO);
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
