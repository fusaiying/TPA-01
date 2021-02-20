package com.paic.ehis.cs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationDealVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IComplaintAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private WorkHandleInfoServiceImpl workHandleInfoMapper;
    @Autowired
    private FlowLogServiceImpl flowLogMapper;


    @Override
    public List<DemandAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO) {
        DemandAcceptVo demandAcceptVo=new DemandAcceptVo();
        demandAcceptVo.setStatus("01");
        return complaintAcceptVoMapper.selectComplaintAcceptVoList(acceptDTO);
    }

    @Override
    public List<DemandAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO) {
        DemandAcceptVo demandAcceptVo=new DemandAcceptVo();
        demandAcceptVo.setStatus("02");
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
        complaintAcceptVo.setInsurer(personInfoMapper.selectPersonInfoById(complaintAcceptVo.getInsuredNo()));
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
        acceptDetailInfo.setCallRelationBy(complaintAcceptVo.getCallRelationBy());
        acceptDetailInfo.setPriorityLevel(complaintAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(complaintAcceptVo.getContactsPersonId());
        acceptDetailInfo.setContactsRelationBy(complaintAcceptVo.getContactsRelationBy());
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
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(complaintAcceptVo.getContactsPersonId());
        personInfo2.setSex(complaintAcceptVo.getContactsSex());
        personInfo2.setName(complaintAcceptVo.getContactsName());
        personInfo2.setLanguage(complaintAcceptVo.getContactsLanguage());
        personInfo2.setMobilePhone(complaintAcceptVo.getContactsMobilePhone());
        personInfo2.setLinePhone(complaintAcceptVo.getContactsCountry()+"-"+complaintAcceptVo.getContactsQuhao()+"-"+complaintAcceptVo.getContactsNumber()+"-"+complaintAcceptVo.getContactsSecondNumber());
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo2);
        //插入投诉人
        personInfo3.setPersonId(complaintAcceptVo.getCallPersonId());
        personInfo2.setSex(complaintAcceptVo.getContactsSex());
        personInfo2.setName(complaintAcceptVo.getContactsName());
        personInfo2.setLanguage(complaintAcceptVo.getContactsLanguage());
        personInfo2.setMobilePhone(complaintAcceptVo.getContactsMobilePhone());
        personInfo2.setLinePhone(complaintAcceptVo.getContactsCountry()+"-"+complaintAcceptVo.getContactsQuhao()+"-"+complaintAcceptVo.getContactsNumber()+"-"+complaintAcceptVo.getContactsSecondNumber());
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
        PersonInfo personInfo1=new PersonInfo();
        PersonInfo personInfo2=new PersonInfo();
        PersonInfo personInfo3=new PersonInfo();
        FlowLog flowLog=new FlowLog();

        //工单表修改
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
        workOrderAccept.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);

        AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
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
        personInfo1.setPersonId(complaintAcceptVo.getCallPersonId());
  //      personInfo1.setName(complaintAcceptVo.getCallPerson().getName());
 //       personInfo1.setMobilePhone(complaintAcceptVo.getCallPerson().getMobilePhone());
        personInfo1.setCreatedBy(SecurityUtils.getUsername());
        personInfo1.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo1.setUpdatedBy(SecurityUtils.getUsername());
        personInfo1.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.updatePersonInfo(personInfo1);
        //插入联系人
        personInfo2.setPersonId(complaintAcceptVo.getContactsPersonId());
      /*  personInfo2.setSex(complaintAcceptVo.getContactsPerson().getSex());
        personInfo2.setName(demandAcceptVo.getContactsName());
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
        flowLog.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("01");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
//        demandAcceptVoMapper.insertFlowLog(flowLog);

//        AcceptDetailInfo acceptDetailInfo2= acceptDetailInfoMapper.selectAcceptDetailInfoById(workOrderNo);
//        WorkOrderAccept workOrderAccept2=workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);


        Map map1 = JSONObject.parseObject(JSONObject.toJSONString(complaintAcceptVo1), Map.class);
        Map map2 = JSONObject.parseObject(JSONObject.toJSONString(complaintAcceptVo), Map.class);

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
                editDetail.setKeyDictType("complaintAcceptVo");
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
                editInfo.setEditRemark(complaintAcceptVo.getEditInfo().getEditRemark());
                editInfo.setEditReason(complaintAcceptVo.getEditInfo().getEditReason());
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
                editInfo.setEditRemark(complaintAcceptVo.getEditInfo().getEditRemark());
                editInfo.setEditReason(complaintAcceptVo.getEditInfo().getEditReason());
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
                editInfo.setEditRemark(complaintAcceptVo.getEditInfo().getEditRemark());
                editInfo.setEditReason(complaintAcceptVo.getEditInfo().getEditReason());
                editInfoMapper.insertEditInfo(editInfo);

            }

        }

        Map map7 = JSONObject.parseObject(JSONObject.toJSONString(complainantPerson), Map.class);
        Map map8 = JSONObject.parseObject(JSONObject.toJSONString(personInfo3), Map.class);

        Iterator<String> iter4 = map7.keySet().iterator();
        while(iter4.hasNext()){
            EditDetail editDetail=new EditDetail();
            EditInfo editInfo=new EditInfo();
            String map7key=iter4.next();
            String map7value = String.valueOf(map7.get(map7key));
            String map8value = String.valueOf(map8.get(map7key));
            if (!map7value.equals(map8value)) {
                keyList.add(map7key);
                editDetail.setKeyDictType("complaintAcceptVo");
                editDetail.setItemKey(map7key);
                editDetail.setOldValue(map7value);
                editDetail.setNowValue(map8value);
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
                editInfo.setEditRemark(complaintAcceptVo.getEditInfo().getEditRemark());
                editInfo.setEditReason(complaintAcceptVo.getEditInfo().getEditReason());
                editInfoMapper.insertEditInfo(editInfo);

            }

        }


        return  complaintAcceptVoMapper.insertFlowLog(flowLog);
    }

    /**
     * 投诉处理
     * @param complaintDealVo
     * @return
     */
    @Override
    public int complaintHandling(ComplaintDealVo complaintDealVo) {
        if(complaintDealVo.getSign()=="01"){
        WorkHandleInfo workHandleInfo = new WorkHandleInfo();
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
        return workHandleInfoMapper.assistInComplaint(complaintDealVo);}
        else {
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


            WorkHandleInfo workHandleInfo=new WorkHandleInfo();
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

            return workHandleInfoMapper.assistInComplaint(complaintDealVo);

        }
    }
    /**
     * 投诉页面获取处理
     * @param workOrderNo
     * @return
     */
    @Override
    public int updateComplaintStatus(String workOrderNo) {
        return complaintAcceptVoMapper.updateComplaintStatus(workOrderNo);
    }

}
