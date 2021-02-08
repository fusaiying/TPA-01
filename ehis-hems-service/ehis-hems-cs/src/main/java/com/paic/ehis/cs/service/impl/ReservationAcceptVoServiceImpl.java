package com.paic.ehis.cs.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IReservationAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public int insertReservationAcceptVo(ReservationAcceptVo reservationAcceptVo) {
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
    //  demandAcceptVoMapper.insertWorkOrderAccept(workOrderAccept);
        workOrderAcceptMapper.insertWorkOrderAccept(workOrderAccept);


        acceptDetailInfo.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        acceptDetailInfo.setChannelCode(reservationAcceptVo.getChannelCode());
        acceptDetailInfo.setItemCode(reservationAcceptVo.getItemCode());
        acceptDetailInfo.setCallPersonId(reservationAcceptVo.getCallPersonId());
        acceptDetailInfo.setCallRelationBy(reservationAcceptVo.getCallRelationBy());
        acceptDetailInfo.setPriorityLevel(reservationAcceptVo.getPriorityLevel());
        acceptDetailInfo.setContactsPersonId(reservationAcceptVo.getContactsPersonId());
        acceptDetailInfo.setContactsRelationBy(reservationAcceptVo.getContactsRelationBy());
        acceptDetailInfo.setEmail(reservationAcceptVo.getEmail());
   //   acceptDetailInfo.setContent(reservationAcceptVo.getContent());
        acceptDetailInfo.setStatus(reservationAcceptVo.getStatus());
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
    //  personInfo2.setLinePhone(reservationAcceptVo.getContactsCountry()+"-"+demandAcceptVo.getContactsQuhao()+"-"+demandAcceptVo.getContactsNumber()+"-"+demandAcceptVo.getContactsSecondNumber());
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfoMapper.insertPersonInfo(personInfo2);

        //轨迹表插入
        flowLog.setFlowId("00000000000000000"+PubFun.createMySqlMaxNoUseCache("cs_flow_id",10,3));
        flowLog.setWorkOrderNo(reservationAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("01");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
//        demandAcceptVoMapper.insertFlowLog(flowLog);
        return  flowLogMapper.insertFlowLog(flowLog);
    }






}
