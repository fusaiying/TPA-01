package com.paic.ehis.cs.service.impl;


import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.domain.FieldMap;
import com.paic.ehis.cs.domain.vo.DemandAcceptVo;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IDemandAcceptVoService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DemandAcceptVoServiceimpl implements IDemandAcceptVoService {
    @Autowired
    private DemandAcceptVoMapper demandAcceptVoMapper;
    @Autowired
    private PersonInfoMapper personInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private FieldMapMapper fieldMapMapper;
    @Autowired
    private AcceptDetailInfoMapper acceptDetailInfoMapper;

    @Override
    public List<DemandAcceptVo> selectDemandAcceptList(DemandAcceptVo demandAcceptVo) {
        List<DemandAcceptVo> demandAcceptVos= demandAcceptVoMapper.selectDemandAcceptVoList(demandAcceptVo);
        String sourceName="DemandAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        for (DemandAcceptVo demandAcceptVo1:demandAcceptVos){
            demandAcceptVo1.setCallPerson(personInfoMapper.selectPersonInfoById(demandAcceptVo1.getCallPersonId()));
            demandAcceptVo1.setContactsPerson(personInfoMapper.selectPersonInfoById(demandAcceptVo1.getContactsPersonId()));
            demandAcceptVo1.setOperatorLast(userInfoMapper.selectUserInfoById(demandAcceptVo1.getCreateBy()));
            demandAcceptVo1.setReviser(userInfoMapper.selectUserInfoById(demandAcceptVo1.getUpdateBy()));
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(demandAcceptVo1.getWorkOrderNo());
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                demandAcceptVo1= (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo1,map,acceptDetailInfo);
           /* Iterator<String> iter = KVMap.keySet().iterator();
            while(iter.hasNext()){
                String key=iter.next();
                String value = KVMap.get(key);
                Map fieldMap =new HashMap<String,String>();
                fieldMap.put(key,value);
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                demandAcceptVo1= (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo1,KVMap,acceptDetailInfo);*/
            }
        }
        return demandAcceptVos;
    }

    @Override
    public List<DemandAcceptVo> selectDemandAcceptList2(DemandAcceptVo demandAcceptVo) {
        List<DemandAcceptVo> demandAcceptVos= demandAcceptVoMapper.selectDemandAcceptVoList2(demandAcceptVo);
        String sourceName="DemandAcceptVo";
        String targetTableName="accept_detail_info";
        List<FieldMap> KVMap=fieldMapMapper.selectKVMap(targetTableName,sourceName);
        for (DemandAcceptVo demandAcceptVo1:demandAcceptVos){
            demandAcceptVo1.setCallPerson(personInfoMapper.selectPersonInfoById(demandAcceptVo1.getCallPersonId()));
            demandAcceptVo1.setContactsPerson(personInfoMapper.selectPersonInfoById(demandAcceptVo1.getContactsPersonId()));
            demandAcceptVo1.setOperatorLast(userInfoMapper.selectUserInfoById(demandAcceptVo1.getCreateBy()));
            demandAcceptVo1.setReviser(userInfoMapper.selectUserInfoById(demandAcceptVo1.getUpdateBy()));
            AcceptDetailInfo acceptDetailInfo=acceptDetailInfoMapper.selectAcceptDetailInfoById(demandAcceptVo1.getWorkOrderNo());
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
                VoUtils voUtils=new VoUtils<DemandAcceptVo>();
                demandAcceptVo1= (DemandAcceptVo) voUtils.fromVoToVo(demandAcceptVo1,map,acceptDetailInfo);
            }
        }
        return demandAcceptVos;
    }

    @Override
    public int updateStatus(String workOrderNo) {
        String modifyBy=String.valueOf(SecurityUtils.getLoginUser().getUserId());

        return demandAcceptVoMapper.updateStatus(workOrderNo);
    }
}
