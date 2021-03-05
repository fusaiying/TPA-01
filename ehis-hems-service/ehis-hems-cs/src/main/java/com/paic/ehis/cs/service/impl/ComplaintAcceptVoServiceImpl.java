package com.paic.ehis.cs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IComplaintAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.method.P;
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
    public List<DemandAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO) {
        DemandAcceptVo demandAcceptVo=new DemandAcceptVo();
        demandAcceptVo.setStatus("01");
        return complaintAcceptVoMapper.selectComplaintAcceptVoList(acceptDTO);
    }

    @Override
    public List<DemandAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO) {
        return complaintAcceptVoMapper.selectComplaintAcceptVoListOne(acceptDTO);
    }

    @Override
    public ComplaintAcceptVo selectComplaintAcceptVo(String workOrderNo) {
        ComplaintAcceptVo complaintAcceptVo=complaintAcceptVoMapper.selectComplaintAcceptVo(workOrderNo);
        String sourceName="ComplaintAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);

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
            complaintAcceptVo.setComplainantPerson(personInfo3);
        } else {
            complaintAcceptVo.setComplainantPerson(new PersonInfo());
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
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        workOrderAccept.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAccept.setBusinessType(complaintAcceptVo.getBusinessType());
        complaintAcceptVoMapper.insertWorkOrderAccept(workOrderAccept);


        acceptDetailInfo.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        acceptDetailInfo.setChannelCode(complaintAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(complaintAcceptVo.getItemCode());
        acceptDetailInfo.setCallPersonId(complaintAcceptVo.getCallPersonId());
        acceptDetailInfo.setPriorityLevel(complaintAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(complaintAcceptVo.getContactsPersonId());
        acceptDetailInfo.setComplaintPersonId(complaintAcceptVo.getComplaintPersonId());
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
        personInfo1.setPersonId(complaintAcceptVo.getCallPersonId());
        personInfo1.setName(complaintAcceptVo.getCallPerson().getName());
        personInfo1.setMobilePhone(complaintAcceptVo.getCallPerson().getMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(complaintAcceptVo.getContactsPersonId());
        personInfo2.setSex(complaintAcceptVo.getContactsPerson().getSex());
        personInfo2.setName(complaintAcceptVo.getContactsPerson().getName());
        personInfo2.setAddress(complaintAcceptVo.getContactsPerson().getAddress());
        personInfo2.setLanguage(complaintAcceptVo.getContactsPerson().getLanguage());
        personInfo2.setMobilePhone(complaintAcceptVo.getContactsPerson().getMobilePhone());
        //personInfo2.setLinePhone(complaintAcceptVo.getContactsCountry()+"-"+complaintAcceptVo.getContactsQuhao()+"-"+complaintAcceptVo.getContactsNumber()+"-"+complaintAcceptVo.getContactsSecondNumber());
        if(complaintAcceptVo.getContactsPerson().getHomePhone1().length>3) {
            personInfo2.setHomePhone(complaintAcceptVo.getContactsPerson().getHomePhone1()[0] + "-" + complaintAcceptVo.getContactsPerson().getHomePhone1()[1] + "-" + complaintAcceptVo.getContactsPerson().getHomePhone1()[2] + "-" + complaintAcceptVo.getContactsPerson().getHomePhone1()[3]);
        }
        if(complaintAcceptVo.getContactsPerson().getWorkPhone1().length>3) {
            personInfo2.setWorkPhone(complaintAcceptVo.getContactsPerson().getWorkPhone1()[0] + "-" + complaintAcceptVo.getContactsPerson().getWorkPhone1()[1] + "-" + complaintAcceptVo.getContactsPerson().getWorkPhone1()[2] + "-" + complaintAcceptVo.getContactsPerson().getWorkPhone1()[3]);
        }
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo2);
        //插入投诉人
        personInfo3.setPersonId(complaintAcceptVo.getComplaintPersonId());
        personInfo3.setIdentity(complaintAcceptVo.getComplainantPerson().getIdentity());
        personInfo3.setSex(complaintAcceptVo.getComplainantPerson().getSex());
        personInfo3.setName(complaintAcceptVo.getComplainantPerson().getName());
        personInfo3.setLanguage(complaintAcceptVo.getContactsLanguage());
        personInfo3.setMobilePhone(complaintAcceptVo.getComplainantPerson().getMobilePhone());
        personInfo3.setCreatedBy(SecurityUtils.getUsername());
        personInfo3.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo3.setUpdatedBy(SecurityUtils.getUsername());
        personInfo3.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo3);
        //轨迹表插入
        flowLog.setFlowId("00000000000000000"+ PubFun.createMySqlMaxNoUseCache("flow_id",10,3));
        flowLog.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("01");
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

        PersonInfo callPerson= personInfoMapper.selectPersonInfoById(complaintAcceptVo.getCallPersonId());
        PersonInfo contactsPerson=personInfoMapper.selectPersonInfoById(complaintAcceptVo.getContactsPersonId());
        PersonInfo complainantPerson=personInfoMapper.selectPersonInfoById(complaintAcceptVo.getComplaintPersonId());
        PersonInfo callPerson1= personInfoMapper.selectPersonInfoById(complaintAcceptVo.getCallPersonId());
        PersonInfo contactsPerson1=personInfoMapper.selectPersonInfoById(complaintAcceptVo.getContactsPersonId());
        PersonInfo complaintPerson1=personInfoMapper.selectPersonInfoById(complaintAcceptVo.getComplaintPersonId());
        FlowLog flowLog=new FlowLog();

        //工单表修改
        WorkOrderAccept workOrderAccept=workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
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

        //插入来电人
        callPerson.setPersonId(complaintAcceptVo.getCallPersonId());
        callPerson.setName(complaintAcceptVo.getCallPerson().getName());
        callPerson.setMobilePhone(complaintAcceptVo.getCallPerson().getMobilePhone());
        callPerson.setCreatedBy(SecurityUtils.getUsername());
        callPerson.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        callPerson.setUpdatedBy(SecurityUtils.getUsername());
        callPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(callPerson);
        //插入联系人
        contactsPerson.setPersonId(complaintAcceptVo.getContactsPersonId());
        contactsPerson.setSex(complaintAcceptVo.getContactsPerson().getSex());
        contactsPerson.setName(complaintAcceptVo.getContactsPerson().getName());
        contactsPerson.setLanguage(complaintAcceptVo.getContactsPerson().getLanguage());
        contactsPerson.setMobilePhone(complaintAcceptVo.getContactsPerson().getMobilePhone());
        contactsPerson.setAddress(complaintAcceptVo.getContactsPerson().getAddress());
    //    contactsPerson.setLinePhone(complaintAcceptVo.getContactsPerson().getLinePhone1()[0]+"-"+complaintAcceptVo.getContactsPerson().getLinePhone1()[1]+"-"+complaintAcceptVo.getContactsPerson().getLinePhone1()[2]+"-"+complaintAcceptVo.getContactsPerson().getLinePhone1()[3]);
        contactsPerson.setHomePhone(complaintAcceptVo.getContactsPerson().getHomePhone1()[0]+"-"+complaintAcceptVo.getContactsPerson().getHomePhone1()[1]+"-"+complaintAcceptVo.getContactsPerson().getHomePhone1()[2]+"-"+complaintAcceptVo.getContactsPerson().getHomePhone1()[3]);
        contactsPerson.setWorkPhone(complaintAcceptVo.getContactsPerson().getWorkPhone1()[0]+"-"+complaintAcceptVo.getContactsPerson().getWorkPhone1()[1]+"-"+complaintAcceptVo.getContactsPerson().getWorkPhone1()[2]+"-"+complaintAcceptVo.getContactsPerson().getWorkPhone1()[3]);
        contactsPerson.setCreatedBy(SecurityUtils.getUsername());
        contactsPerson.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        contactsPerson.setUpdatedBy(SecurityUtils.getUsername());
        contactsPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(contactsPerson);
        //插入投诉人
        complainantPerson.setPersonId(complaintAcceptVo.getComplaintPersonId());
        complainantPerson.setSex(complaintAcceptVo.getComplainantPerson().getSex());
        complainantPerson.setIdentity(complaintAcceptVo.getComplainantPerson().getIdentity());
        complainantPerson.setName(complaintAcceptVo.getComplainantPerson().getName());
        complainantPerson.setMobilePhone(complaintAcceptVo.getComplainantPerson().getMobilePhone());
        complainantPerson.setCreatedBy(SecurityUtils.getUsername());
        complainantPerson.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complainantPerson.setUpdatedBy(SecurityUtils.getUsername());
        complainantPerson.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(complainantPerson);

        //轨迹表插入
        flowLog.setFlowId("00000000000000000"+PubFun.createMySqlMaxNoUseCache("cs_flow_id",10,3));
        flowLog.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("01");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));


        String editId=PubFun.createMySqlMaxNoUseCache("cs_edit_id",10,8);
        Map map1 = JSONObject.parseObject(JSONObject.toJSONString(complaintAcceptVo1), Map.class);
        Map map2 = JSONObject.parseObject(JSONObject.toJSONString(complaintAcceptVo), Map.class);

        //     Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(acceptDetailInfo1));

        List<String> keyList=new ArrayList<>();
        Iterator<String> iter1 = map1.keySet().iterator();
        while(iter1.hasNext()){
            EditDetail editDetail=new EditDetail();
 //           EditInfo editInfo=new EditInfo();
            String map1key=iter1.next();
            String map1value = String.valueOf(map1.get(map1key));
            String map2value = String.valueOf(map2.get(map1key));
            if (!map1value.equals(map2value)) {
                keyList.add(map1key);
                editDetail.setKeyDictType("complaintAcceptVo");
                editDetail.setItemKey(map1key);
                editDetail.setOldValue(map1value);
                editDetail.setNowValue(map2value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setEditId(editId);
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetailMapper.insertEditDetail(editDetail);
            }

        }

        Map map3 = JSONObject.parseObject(JSONObject.toJSONString(callPerson1), Map.class);
        Map map4 = JSONObject.parseObject(JSONObject.toJSONString(callPerson), Map.class);

        //     Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(acceptDetailInfo1));


        Iterator<String> iter2 = map3.keySet().iterator();
        while(iter2.hasNext()){
            EditDetail editDetail=new EditDetail();
 //           EditInfo editInfo=new EditInfo();
            String map3key=iter2.next();
            String map3value = String.valueOf(map3.get(map3key));
            String map4value = String.valueOf(map4.get(map3key));
            if (!map3value.equals(map4value)) {
                keyList.add("callPerson."+map3key);
                editDetail.setKeyDictType("complaintAcceptVo");
                editDetail.setItemKey(map3key);
                editDetail.setOldValue(map3value);
                editDetail.setNowValue(map4value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
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

        Iterator<String> iter3 = map5.keySet().iterator();
        while(iter3.hasNext()){
            EditDetail editDetail=new EditDetail();
 //           EditInfo editInfo=new EditInfo();
            String map5key=iter3.next();
            String map5value = String.valueOf(map5.get(map5key));
            String map6value = String.valueOf(map6.get(map5key));
            if (!map5value.equals(map6value)) {
                keyList.add(map5key);
                editDetail.setKeyDictType("complaintAcceptVo");
                editDetail.setItemKey("contactsPerson."+map5key);
                editDetail.setOldValue(map5value);
                editDetail.setNowValue(map6value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setEditId(editId);
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetailMapper.insertEditDetail(editDetail);

            }

        }

        Map map7 = JSONObject.parseObject(JSONObject.toJSONString(complaintPerson1), Map.class);
        Map map8 = JSONObject.parseObject(JSONObject.toJSONString(complainantPerson), Map.class);

        Iterator<String> iter4 = map7.keySet().iterator();
        while(iter4.hasNext()){
            EditDetail editDetail=new EditDetail();
//            EditInfo editInfo=new EditInfo();
            String map7key=iter4.next();
            String map7value = String.valueOf(map7.get(map7key));
            String map8value = String.valueOf(map8.get(map7key));
            if (!map7value.equals(map8value)) {
                keyList.add(map7key);
                editDetail.setKeyDictType("complaintAcceptVo");
                editDetail.setItemKey("complainPerson."+map7key);
                editDetail.setOldValue(map7value);
                editDetail.setNowValue(map8value);
                editDetail.setDetailId(PubFun.createMySqlMaxNoUseCache("cs_detail_id",10,8));
                editDetail.setEditId(editId);
                editDetail.setCreatedBy(SecurityUtils.getUsername());
                editDetail.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
                editDetail.setUpdatedBy(SecurityUtils.getUsername());
                editDetail.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
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
        editInfo.setEditRemark(complaintAcceptVo.getEditRemark());
        editInfo.setEditReason(complaintAcceptVo.getEditReason());
        editInfoMapper.insertEditInfo(editInfo);

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
        workHandleInfo.setCreatedBy(complaintDealVo.getCreatedBy());
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
        List<FieldMap> KVMap = fieldMapMapper.selectKVMap("work_handle_info", "ComplaintDealVo");
        for (FieldMap fieldMap : KVMap) {
            fieldMap.getTargetColumnName();
            fieldMap.getSourceFiledName();
            Map map = new HashMap<String, String>();
            map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
            VoUtils voUtils = new VoUtils<ComplaintDealVo>();
            workHandleInfo = (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo, map, complaintDealVo);
        }
        return workHandleInfoMapper.assistInComplaint(workHandleInfo);}
        else {

            //将所有状态置为N
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfoMapper.updateStatus(workHandleInfo);

           // workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id",10,6)));
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("Y");
            workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
            workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfo.setRemark(complaintDealVo.getRemark());
            List<FieldMap> KVMap=fieldMapMapper.selectKVMap("work_handle_info","complaintDealVo");
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
     /*   WorkHandleInfo workHandleInfo=new WorkHandleInfo();
        workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
        workHandleInfo.setCreatedBy(complaintDealVo.getCreatedBy());
        WorkHandleInfo workHandleInfo1=workHandleInfoMapper.selectCreatedBy(workHandleInfo);

        if (workHandleInfo1==null) {*/
        //将所有状态置为N
        WorkHandleInfo workHandleInfo = new WorkHandleInfo();
        if (complaintDealVo.getRootImprovement().isEmpty()) {
            //将所有数据置为n
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfoMapper.updateStatus(workHandleInfo);
            //修改主表状态为已处理
            WorkOrderAccept workOrderAccept=new WorkOrderAccept();
            workOrderAccept.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workOrderAccept.setStatus("03");
            workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
            //插入轨迹表
            FlowLog flowLog = new FlowLog();
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id", 10, 6));
            flowLog.setLinkCode("03");
            flowLog.setMakeBy(SecurityUtils.getUsername());
            //没有um帐号
            flowLog.setUmNum(SecurityUtils.getLoginUser().getUserId().toString());
            flowLog.setCreatedBy(SecurityUtils.getUsername());
            flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setUpdatedBy(SecurityUtils.getUsername());
            flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            flowLogMapper.updateFlowLog(flowLog);
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
        } else {

            //将主表状态置为已处理
            String workOrderNo=complaintDealVo.getWorkOrderNo();
            WorkOrderAccept workOrderAccept1=workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
            WorkOrderAccept workOrderAccept2=workOrderAccept1;
            workOrderAccept1.setStatus("03");
            workOrderAcceptMapper.updateWorkOrderStatus(workOrderAccept1);
            String workOrderNo2="9900000000"+PubFun.createMySqlMaxNoUseCache("cs_work_order_no",10,6);
            workOrderAccept2.setWorkOrderNo(workOrderNo2);
            workOrderAccept2.setPolicyNo(workOrderAccept1.getPolicyNo());
            workOrderAccept2.setPolicyItemNo(workOrderAccept1.getPolicyItemNo());
            workOrderAccept2.setRiskCode(workOrderAccept1.getRiskCode());
            workOrderAccept2.setInsuredNo(workOrderAccept1.getInsuredNo());
            workOrderAccept2.setInsuredName(workOrderAccept1.getInsuredName());
            workOrderAccept2.setHolderNo(workOrderAccept1.getHolderNo());
            workOrderAccept2.setHolderName(workOrderAccept1.getHolderName());
            workOrderAccept2.setAcceptBy(workOrderAccept1.getAcceptBy());
            workOrderAccept2.setAcceptTime(workOrderAccept1.getAcceptTime());
            workOrderAccept2.setModifyBy(workOrderAccept1.getModifyBy());
            workOrderAccept2.setModifyTime(workOrderAccept1.getModifyTime());
            workOrderAccept2.setVipFlag(workOrderAccept1.getVipFlag());
            workOrderAccept2.setOrganCode(workOrderAccept1.getOrganCode());
            workOrderAccept2.setEndDate(workOrderAccept1.getEndDate());
            workOrderAccept2.setClickTime(workOrderAccept1.getClickTime());
            workOrderAccept2.setStatus("02");
            workOrderAccept2.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
            workOrderAccept2.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            workOrderAccept2.setUpdateBy(SecurityUtils.getUsername());
            workOrderAccept2.setCreateBy(SecurityUtils.getUsername());
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

            //插入轨迹表
            FlowLog flowLog = new FlowLog();
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id", 10, 6));
            flowLog.setLinkCode("03");
            flowLog.setMakeBy(SecurityUtils.getUsername());
            //没有um帐号
            flowLog.setUmNum(SecurityUtils.getLoginUser().getUserId().toString());
            flowLog.setCreatedBy(SecurityUtils.getUsername());
            flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setUpdatedBy(SecurityUtils.getUsername());
            flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            flowLogMapper.updateFlowLog(flowLog);

            FlowLog flowLog2 = new FlowLog();
            flowLog2.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id", 10, 6));
            flowLog2.setStatus("02");
            flowLog2.setMakeBy(SecurityUtils.getUsername());
            //没有um帐号
            flowLog2.setUmNum(SecurityUtils.getLoginUser().getUserId().toString());
            flowLog2.setCreatedBy(SecurityUtils.getUsername());
            flowLog2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog2.setUpdatedBy(SecurityUtils.getUsername());
            flowLog2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog2.setWorkOrderNo(workOrderAccept2.getWorkOrderNo());
            flowLogMapper.insertFlowLog(flowLog2);
            //将所有状态置为N
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfoMapper.updateStatus(workHandleInfo);
            // 第一条元数据转义
            workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id", 10, 6)));
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("Y");
            workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
            workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
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
            String status="Y";
            //生成WorkHandleInfo第一条数据
            WorkHandleInfo workHandleInfo1=workHandleInfoMapper.selectWorkHandleInfo(workOrderNo,status);
            WorkHandleInfo workHandleInfo2=workHandleInfo1;
            BeanUtils.copyProperties(workHandleInfo2,workHandleInfo1);
            workHandleInfo2.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id",10,6)));
            workHandleInfo2.setWorkOrderNo(workOrderNo2);
            workHandleInfo2.setStatus("Y");

            return workHandleInfoMapper.assistInComplaint(workHandleInfo2);
        }
    }


        /*else {
            //将所有状态置为N
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfoMapper.updateStatus(workHandleInfo);
            //插入轨迹表
            FlowLog flowLog=new FlowLog();
            flowLog.setFlowId(PubFun.createMySqlMaxNoUseCache("flow_id",10,6));
            //flowLog.setWorkOrderNo();从前端获得
            flowLog.setStatus("03");
            flowLog.setCreatedBy(SecurityUtils.getUsername());
            flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setUpdatedBy(SecurityUtils.getUsername());
            flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            flowLog.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            flowLogMapper.updateFlowLog(flowLog);

            //WorkHandleInfo workHandleInfo=new WorkHandleInfo();
            workHandleInfo.setHandleId(Long.parseLong(PubFun.createMySqlMaxNoUseCache("handle_id",10,6)));
            workHandleInfo.setHandleType("处理");
            workHandleInfo.setStatus("03");
            workHandleInfo.setCreatedBy(SecurityUtils.getUsername());
            workHandleInfo.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setUpdatedBy(SecurityUtils.getUsername());
            workHandleInfo.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
            workHandleInfo.setWorkOrderNo(complaintDealVo.getWorkOrderNo());
            workHandleInfo.setRemark(complaintDealVo.getRemark());
            List<FieldMap> KVMap=fieldMapMapper.selectKVMap("work_handle_info","complaintDealVo");
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
                VoUtils voUtils=new VoUtils<ComplaintDealVo>();
                workHandleInfo= (WorkHandleInfo) voUtils.fromVoToVo(workHandleInfo,map,complaintDealVo);}

            return workHandleInfoMapper.updateServiceProcessing(workHandleInfo);
*/


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
    }
