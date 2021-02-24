package com.paic.ehis.cs.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.mapper.BasicServiceApplicationMapper;
import com.paic.ehis.cs.mapper.FieldMapMapper;
import com.paic.ehis.cs.service.IBasicServiceApplicationService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicServiceApplicationServiceImpl implements IBasicServiceApplicationService {
    @Autowired
    private BasicServiceApplicationMapper basicServiceApplicationMapper;

    @Autowired
    private FieldMapMapper fieldMapMapper;

    @Override
    public int insertBasicServiceApplication(BasicServiceAppilcation basicServiceAppilcation) {

        //建对象
        AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
        PersonInfo personInfo1=new PersonInfo();
        PersonInfo personInfo2=new PersonInfo();
        PersonInfo personInfo3=new PersonInfo();
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        String username = SecurityUtils.getUsername();
        Date time = DateUtils.parseDate(DateUtils.dateTime());

        //判断得到的businessType是informationApplication还是complainApplication
        if ("informationApplication".equals(basicServiceAppilcation.getBusinessData().getType())) {
            //personInfo表新增数据
            personInfo1.setPersonId(PubFun.createMySqlMaxNoUseCache("person_id", 6, 6));
            personInfo1.setSex(Long.valueOf(basicServiceAppilcation.getBusinessData().getContaGenderCode()));
            personInfo1.setName(basicServiceAppilcation.getBusinessData().getContaName());
            personInfo1.setLanguage(basicServiceAppilcation.getBusinessData().getPerferLan());
            personInfo1.setMobilePhone(basicServiceAppilcation.getBusinessData().getContaMobileNo());
            personInfo1.setHomePhone(basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneCountryCode()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneAreaCode()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneNo()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getExtensionNo());
            personInfo1.setIdentity("contacts");
            personInfo1.setCreatedBy(username);
            personInfo1.setCreatedTime(time);
            personInfo1.setUpdatedBy(username);
            personInfo1.setUpdatedTime(time);
            basicServiceApplicationMapper.insertPersonInfoTable(personInfo1);

            personInfo2.setPersonId(PubFun.createMySqlMaxNoUseCache("person_id", 6, 6));
            personInfo2.setName(basicServiceAppilcation.getBusinessData().getCaller());
            personInfo2.setLanguage(basicServiceAppilcation.getBusinessData().getPerferLan());
            personInfo2.setMobilePhone(basicServiceAppilcation.getBusinessData().getPhoneNo());
            personInfo2.setHomePhone(basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneCountryCode()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneAreaCode()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneNo()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getExtensionNo());
            personInfo2.setIdentity("caller");
            personInfo2.setCreatedBy(username);
            personInfo2.setCreatedTime(time);
            personInfo2.setUpdatedBy(username);
            personInfo2.setUpdatedTime(time);
            basicServiceApplicationMapper.insertPersonInfoTable(personInfo2);

            personInfo3.setPersonId(PubFun.createMySqlMaxNoUseCache("person_id", 6, 6));
            personInfo3.setLanguage(basicServiceAppilcation.getBusinessData().getPerferLan());
            personInfo3.setMobilePhone(basicServiceAppilcation.getBusinessData().getEnrolPhone());
            personInfo3.setIdentity("enrol");
            personInfo3.setHomePhone(basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneCountryCode()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneAreaCode()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getPhoneNo()+
                    "-"+basicServiceAppilcation.getBusinessData().getContaHomePhone().getExtensionNo());
            personInfo3.setCreatedBy(username);
            personInfo3.setCreatedTime(time);
            personInfo3.setUpdatedBy(username);
            personInfo3.setUpdatedTime(time);
            basicServiceApplicationMapper.insertPersonInfoTable(personInfo3);

            //workOrderAccept表新增数据
            workOrderAccept.setAcceptBy(basicServiceAppilcation.getBusinessData().getAccepter());
            workOrderAccept.setBusinessType(basicServiceAppilcation.getBusinessData().getType());
            workOrderAccept.setAcceptTime(basicServiceAppilcation.getBusinessData().getAcceptDate());
            workOrderAccept.setInsuredNo(basicServiceAppilcation.getBusinessData().getCustomerNo());
            workOrderAccept.setPolicyItemNo(basicServiceAppilcation.getBusinessData().getCertno());
            workOrderAccept.setCreateBy(username);
            workOrderAccept.setCreateTime(time);
            workOrderAccept.setUpdateBy(username);
            workOrderAccept.setUpdateTime(time);
            basicServiceApplicationMapper.insertWorkOrderAcceptTable(workOrderAccept);

            //acceptDetailInfo表新增数据
            acceptDetailInfo.setCallCenterId(basicServiceAppilcation.getBusinessData().getBusinessSeqNo());
            acceptDetailInfo.setChannelCode(basicServiceAppilcation.getBusinessData().getAcceptChannel());
            acceptDetailInfo.setItemCode(basicServiceAppilcation.getBusinessData().getQuestionType());
            acceptDetailInfo.setCallRelationBy(basicServiceAppilcation.getBusinessData().getCallerInsRelation());
            acceptDetailInfo.setContactsRelationBy(basicServiceAppilcation.getBusinessData().getContactsInsRelation());
            acceptDetailInfo.setPriorityLevel(basicServiceAppilcation.getBusinessData().getPriorityCode());
            acceptDetailInfo.setEmail(basicServiceAppilcation.getBusinessData().getEmail());
            acceptDetailInfo.setContent(basicServiceAppilcation.getBusinessData().getApplicationContent());
            acceptDetailInfo.setCreateBy(username);
            acceptDetailInfo.setCreateTime(time);
            acceptDetailInfo.setUpdateBy(username);
            acceptDetailInfo.setUpdateTime(time);

            //转义表转换字段名
            BusinessData businessData = basicServiceAppilcation.getBusinessData();
            List<FieldMap> KVMap=fieldMapMapper.selectKVMap("accept_detail_info","BusinessData");
            for (FieldMap fieldMap:KVMap){
                fieldMap.getTargetColumnName();
                fieldMap.getSourceFiledName();
                Map map=new HashMap<String,String>();
                map.put(fieldMap.getTargetColumnName(),fieldMap.getSourceFiledName());
                VoUtils voUtils=new VoUtils<BusinessData>();
                acceptDetailInfo= (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo,map,businessData);
            }
            basicServiceApplicationMapper.insertAcceptDetialInfoTable(acceptDetailInfo);
        }
        return 1;
    }
}
