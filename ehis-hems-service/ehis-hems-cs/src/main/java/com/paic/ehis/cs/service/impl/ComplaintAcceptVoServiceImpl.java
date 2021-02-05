package com.paic.ehis.cs.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.AcceptDTO;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IComplaintAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
    public List<DemandAcceptVo> selectComplaintAcceptVoList(AcceptDTO acceptDTO) {
        return complaintAcceptVoMapper.selectComplaintAcceptVoList(acceptDTO);
    }

    @Override
    public List<DemandAcceptVo> selectComplaintAcceptVoListOne(AcceptDTO acceptDTO) {
        return complaintAcceptVoMapper.selectComplaintAcceptVoListOne(acceptDTO);
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
        //personInfo2.setLinePhone(complaintAcceptVo.getContactsPerson().getContactsCountry()+"-"+complaintAcceptVo.getContactsQuhao()+"-"+complaintAcceptVo.getContactsNumber()+"-"+complaintAcceptVo.getContactsSecondNumber());
        personInfo2.setCreatedBy(SecurityUtils.getUsername());
        personInfo2.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo2.setUpdatedBy(SecurityUtils.getUsername());
        personInfo2.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo2);
        //插入投诉人
        personInfo3.setPersonId(complaintAcceptVo.getCallPersonId());
        personInfo3.setCreatedBy(SecurityUtils.getUsername());
        personInfo3.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        personInfo3.setUpdatedBy(SecurityUtils.getUsername());
        personInfo3.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
        complaintAcceptVoMapper.insertPersonInfo(personInfo3);
        //轨迹表插入
        flowLog.setFlowId("00000000000000000"+ PubFun.createMySqlMaxNoUseCache("cs_flow_id",10,3));
        flowLog.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        flowLog.setOperateCode("01");
        flowLog.setCreatedBy(SecurityUtils.getUsername());
        flowLog.setCreatedTime(DateUtils.parseDate(DateUtils.getTime()));
        flowLog.setUpdatedBy(SecurityUtils.getUsername());
        flowLog.setUpdatedTime(DateUtils.parseDate(DateUtils.getTime()));
//        demandAcceptVoMapper.insertFlowLog(flowLog);
        return  complaintAcceptVoMapper.insertFlowLog(flowLog);
    }
}
