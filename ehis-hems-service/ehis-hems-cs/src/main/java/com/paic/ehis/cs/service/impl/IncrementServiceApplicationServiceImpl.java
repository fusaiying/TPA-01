package com.paic.ehis.cs.service.impl;


import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.mapper.FieldMapMapper;
import com.paic.ehis.cs.mapper.IncrementServiceApplicationMapper;
import com.paic.ehis.cs.service.IncrementServiceApplicationService;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncrementServiceApplicationServiceImpl implements IncrementServiceApplicationService {
    @Autowired
    IncrementServiceApplicationMapper incrementServiceApplicationMapper;
    @Autowired
    FieldMapMapper fieldMapMapper;


    @Override
    public int addIncrementServiceApplication(IncrementServiceApplication incrementServiceApplication) {
        AcceptDetailInfo acceptDetailInfo = new AcceptDetailInfo();
        PersonInfo personInfo1 = new PersonInfo();
        PersonInfo personInfo2 = new PersonInfo();
        PersonInfo personInfo3 = new PersonInfo();
        WorkOrderAccept workOrderAccept = new WorkOrderAccept();
        String username = SecurityUtils.getUsername();
        Date time = new Date();
        if (incrementServiceApplication.getBusinessData() != null) {
            ContaHomePhone contaHomePhone = incrementServiceApplication.getBusinessData().getContaHomePhone();
            String homephone = (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneCountryCode())?contaHomePhone.getPhoneCountryCode():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneAreaCode())?contaHomePhone.getPhoneAreaCode():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneNo())?contaHomePhone.getPhoneNo():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getExtensionNo())?contaHomePhone.getExtensionNo():"");
            if ("vipClinic".equals(incrementServiceApplication.getBusinessData().getType())) {
                String personid1 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid2 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid3 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                personInfo1.setPersonId(personid1);
                personInfo1.setSex(incrementServiceApplication.getBusinessData().getContaGenderCode());
                personInfo1.setName(incrementServiceApplication.getBusinessData().getContaName());
                personInfo1.setLanguage(incrementServiceApplication.getBusinessData().getPerferLan());
                personInfo1.setFax(incrementServiceApplication.getBusinessData().getFaxNo());
                personInfo1.setOtherCustomerNo(incrementServiceApplication.getBusinessData().getCustomerNo());
                personInfo1.setHomePhone(homephone);
                personInfo1.setCreatedBy(username);
                personInfo1.setCreatedTime(time);
                personInfo1.setUpdatedBy(username);
                personInfo1.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo1);

                personInfo2.setPersonId(personid2);
                personInfo2.setName(incrementServiceApplication.getBusinessData().getCallerName());
                personInfo2.setMobilePhone(incrementServiceApplication.getBusinessData().getPhoneNo());
                personInfo2.setHomePhone(homephone);
                personInfo2.setCreatedBy(username);
                personInfo2.setCreatedTime(time);
                personInfo2.setUpdatedBy(username);
                personInfo2.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo2);

                personInfo3.setPersonId(personid3);
                personInfo3.setName(incrementServiceApplication.getBusinessData().getApplyName());
                personInfo3.setHomePhone(homephone);
                personInfo3.setCreatedBy(username);
                personInfo3.setCreatedTime(time);
                personInfo3.setUpdatedBy(username);
                personInfo3.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo3);

                workOrderAccept.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                workOrderAccept.setStatus("02");
                workOrderAccept.setAcceptBy(incrementServiceApplication.getBusinessData().getAccepter());
                workOrderAccept.setAcceptTime(DateUtils.parseDate(incrementServiceApplication.getBusinessData().getAcceptDate()));
                workOrderAccept.setBusinessType("02");
                workOrderAccept.setPolicyNo(incrementServiceApplication.getBusinessData().getPolicyList());
                workOrderAccept.setPolicyItemNo(incrementServiceApplication.getBusinessData().getCertno());
                workOrderAccept.setCreateBy(username);
                workOrderAccept.setCreateTime(time);
                workOrderAccept.setUpdateBy(username);
                workOrderAccept.setUpdateTime(time);
                incrementServiceApplicationMapper.insertWorkOrderAcceptTable(workOrderAccept);

                acceptDetailInfo.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                acceptDetailInfo.setProp1(incrementServiceApplication.getBusinessData().getIsAccident());
                acceptDetailInfo.setProp2(incrementServiceApplication.getBusinessData().getClinicType());
                acceptDetailInfo.setProp4(incrementServiceApplication.getBusinessData().getOnsetDate());
                acceptDetailInfo.setProp5(incrementServiceApplication.getBusinessData().getHasValidCertificate());
                acceptDetailInfo.setProp6(incrementServiceApplication.getBusinessData().getHasInsuranceCard());
                acceptDetailInfo.setProp7(incrementServiceApplication.getBusinessData().getDiseaseName());
                acceptDetailInfo.setProp10(incrementServiceApplication.getBusinessData().getHospitalDepartmentName());
                acceptDetailInfo.setProp12(incrementServiceApplication.getBusinessData().getPayOutRate());
                acceptDetailInfo.setProp14(incrementServiceApplication.getBusinessData().getPreengagementDate());
                acceptDetailInfo.setProp15(incrementServiceApplication.getBusinessData().getHospitalCallPhoneNo());
                acceptDetailInfo.setProp16(incrementServiceApplication.getBusinessData().getDiseaseFeature());
                acceptDetailInfo.setProp17(incrementServiceApplication.getBusinessData().getAccidentDescr());
                acceptDetailInfo.setProp18(incrementServiceApplication.getBusinessData().getPreengagementTime());
                acceptDetailInfo.setProp19(incrementServiceApplication.getBusinessData().getEarliestOnsetDate());
                acceptDetailInfo.setProp22(incrementServiceApplication.getBusinessData().getClinicTime());
                acceptDetailInfo.setProp25(incrementServiceApplication.getBusinessData().getDirectPayFlag());
                acceptDetailInfo.setEmail(incrementServiceApplication.getBusinessData().getEmail());
                acceptDetailInfo.setCallRelationBy(incrementServiceApplication.getBusinessData().getCallerRelationCode());
                acceptDetailInfo.setCallCenterId(incrementServiceApplication.getBusinessData().getBusinessSeqNo());
                acceptDetailInfo.setPriorityLevel(incrementServiceApplication.getBusinessData().getPriorityCode());
                acceptDetailInfo.setContactsPersonId(personid1);
                acceptDetailInfo.setCallPersonId(personid2);
                acceptDetailInfo.setComplaintPersonId(personid3);
                acceptDetailInfo.setCreateBy(username);
                acceptDetailInfo.setCreateTime(time);
                acceptDetailInfo.setUpdateBy(username);
                acceptDetailInfo.setUpdateTime(time);
                IncrementBusinessData businessData = incrementServiceApplication.getBusinessData();
                List<FieldMap> KVMap = fieldMapMapper.selectKVMap("accept_detail_info", "BusinessData");
                for (FieldMap fieldMap : KVMap) {
                    fieldMap.getTargetColumnName();
                    fieldMap.getSourceFiledName();
                    Map map = new HashMap<String, String>();
                    map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                    VoUtils voUtils = new VoUtils<BusinessData>();
                    acceptDetailInfo = (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo, map, businessData);
                }
                incrementServiceApplicationMapper.insertAcceptDetialInfoTable(acceptDetailInfo);
            }else if("clinicDirectPay".equals(incrementServiceApplication.getBusinessData().getType())){
                String personid1 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid2 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid3 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);

                acceptDetailInfo.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                acceptDetailInfo.setProp1(incrementServiceApplication.getBusinessData().getIsAccident());
                acceptDetailInfo.setProp2(incrementServiceApplication.getBusinessData().getClinicType());
                acceptDetailInfo.setProp4(incrementServiceApplication.getBusinessData().getOnsetDate());
                acceptDetailInfo.setProp5(incrementServiceApplication.getBusinessData().getHasValidCertificate());
                acceptDetailInfo.setProp6(incrementServiceApplication.getBusinessData().getHasInsuranceCard());
                acceptDetailInfo.setProp7(incrementServiceApplication.getBusinessData().getDiseaseName());
                acceptDetailInfo.setProp10(incrementServiceApplication.getBusinessData().getHospitalDepartmentName());
                acceptDetailInfo.setProp11(incrementServiceApplication.getBusinessData().getHospitalName());
                acceptDetailInfo.setProp12(incrementServiceApplication.getBusinessData().getPayOutRate());
                acceptDetailInfo.setProp14(incrementServiceApplication.getBusinessData().getPreengagementDate());
                acceptDetailInfo.setProp15(incrementServiceApplication.getBusinessData().getHospitalCallPhoneNo());
                acceptDetailInfo.setProp16(incrementServiceApplication.getBusinessData().getDiseaseFeature());
                acceptDetailInfo.setProp17(incrementServiceApplication.getBusinessData().getAccidentDescr());
                acceptDetailInfo.setProp18(incrementServiceApplication.getBusinessData().getPreengagementTime());
                acceptDetailInfo.setProp19(incrementServiceApplication.getBusinessData().getEarliestOnsetDate());
                acceptDetailInfo.setProp21(incrementServiceApplication.getBusinessData().getClinicDate());
                acceptDetailInfo.setProp22(incrementServiceApplication.getBusinessData().getClinicTime());
                acceptDetailInfo.setProp22(incrementServiceApplication.getBusinessData().getClinicTime1());
                acceptDetailInfo.setProp22(incrementServiceApplication.getBusinessData().getClinicTime2());
                acceptDetailInfo.setProp25(incrementServiceApplication.getBusinessData().getDirectPayFlag());
                acceptDetailInfo.setCallRelationBy(incrementServiceApplication.getBusinessData().getCallerRelationCode());
                acceptDetailInfo.setEmail(incrementServiceApplication.getBusinessData().getEmail());
                acceptDetailInfo.setPriorityLevel(incrementServiceApplication.getBusinessData().getPriorityCode());
                acceptDetailInfo.setCallCenterId(incrementServiceApplication.getBusinessData().getBusinessSeqNo());
                acceptDetailInfo.setContactsPersonId(personid1);
                acceptDetailInfo.setCallPersonId(personid2);
                acceptDetailInfo.setComplaintPersonId(personid3);
                acceptDetailInfo.setCreateBy(username);
                acceptDetailInfo.setCreateTime(time);
                acceptDetailInfo.setUpdateBy(username);
                acceptDetailInfo.setUpdateTime(time);
                IncrementBusinessData businessData = incrementServiceApplication.getBusinessData();
                List<FieldMap> KVMap = fieldMapMapper.selectKVMap("accept_detail_info", "BusinessData");
                for (FieldMap fieldMap : KVMap) {
                    fieldMap.getTargetColumnName();
                    fieldMap.getSourceFiledName();
                    Map map = new HashMap<String, String>();
                    map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                    VoUtils voUtils = new VoUtils<BusinessData>();
                    acceptDetailInfo = (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo, map, businessData);
                }
                incrementServiceApplicationMapper.insertAcceptDetialInfoTable(acceptDetailInfo);

                workOrderAccept.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                workOrderAccept.setStatus("02");
                workOrderAccept.setAcceptBy(incrementServiceApplication.getBusinessData().getAccepter());
                workOrderAccept.setAcceptTime(DateUtils.parseDate(incrementServiceApplication.getBusinessData().getAcceptDate()));
                workOrderAccept.setBusinessType("04");
                workOrderAccept.setPolicyNo(incrementServiceApplication.getBusinessData().getPolicyList());
                workOrderAccept.setPolicyItemNo(incrementServiceApplication.getBusinessData().getCertno());
                workOrderAccept.setCreateBy(username);
                workOrderAccept.setCreateTime(time);
                workOrderAccept.setUpdateBy(username);
                workOrderAccept.setUpdateTime(time);
                incrementServiceApplicationMapper.insertWorkOrderAcceptTable(workOrderAccept);

                personInfo1.setPersonId(personid1);
                personInfo1.setSex(incrementServiceApplication.getBusinessData().getContaGenderCode());
                personInfo1.setName(incrementServiceApplication.getBusinessData().getContaName());
                personInfo1.setLanguage(incrementServiceApplication.getBusinessData().getPerferLan());
                personInfo1.setMobilePhone(incrementServiceApplication.getBusinessData().getContaMobileNo());
                personInfo1.setFax(incrementServiceApplication.getBusinessData().getFaxNo());
                personInfo1.setOtherCustomerNo(incrementServiceApplication.getBusinessData().getCustomerNo());
                personInfo1.setHomePhone(homephone);
                personInfo1.setCreatedBy(username);
                personInfo1.setCreatedTime(time);
                personInfo1.setUpdatedBy(username);
                personInfo1.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo1);

                personInfo2.setPersonId(personid2);
                personInfo2.setName(incrementServiceApplication.getBusinessData().getCallerName());
                personInfo2.setMobilePhone(incrementServiceApplication.getBusinessData().getPhoneNo());
                personInfo2.setHomePhone(homephone);
                personInfo2.setCreatedBy(username);
                personInfo2.setCreatedTime(time);
                personInfo2.setUpdatedBy(username);
                personInfo2.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo2);

                personInfo3.setPersonId(personid3);
                personInfo3.setName(incrementServiceApplication.getBusinessData().getApplyName());
                personInfo3.setHomePhone(homephone);
                personInfo3.setCreatedBy(username);
                personInfo3.setCreatedTime(time);
                personInfo3.setUpdatedBy(username);
                personInfo3.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo3);

            }else if("demesticSecondCure".equals(incrementServiceApplication.getBusinessData().getType())){
                String personid1 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid2 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid3 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);

                acceptDetailInfo.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                acceptDetailInfo.setCallRelationBy(incrementServiceApplication.getBusinessData().getCallerRelationCode());
                acceptDetailInfo.setPriorityLevel(incrementServiceApplication.getBusinessData().getPriorityCode());
                acceptDetailInfo.setCallCenterId(incrementServiceApplication.getBusinessData().getBusinessSeqNo());
                acceptDetailInfo.setEmail(incrementServiceApplication.getBusinessData().getEmail());
                acceptDetailInfo.setContactsPersonId(personid1);
                acceptDetailInfo.setCallPersonId(personid2);
                acceptDetailInfo.setComplaintPersonId(personid3);
                acceptDetailInfo.setCreateBy(username);
                acceptDetailInfo.setCreateTime(time);
                acceptDetailInfo.setUpdateBy(username);
                acceptDetailInfo.setUpdateTime(time);
                IncrementBusinessData businessData = incrementServiceApplication.getBusinessData();
                List<FieldMap> KVMap = fieldMapMapper.selectKVMap("accept_detail_info", "BusinessData");
                for (FieldMap fieldMap : KVMap) {
                    fieldMap.getTargetColumnName();
                    fieldMap.getSourceFiledName();
                    Map map = new HashMap<String, String>();
                    map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                    VoUtils voUtils = new VoUtils<BusinessData>();
                    acceptDetailInfo = (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo, map, businessData);
                }
                incrementServiceApplicationMapper.insertAcceptDetialInfoTable(acceptDetailInfo);

                workOrderAccept.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                workOrderAccept.setStatus("02");
                workOrderAccept.setAcceptBy(incrementServiceApplication.getBusinessData().getAccepter());
                workOrderAccept.setAcceptTime(DateUtils.parseDate(incrementServiceApplication.getBusinessData().getAcceptDate()));
                workOrderAccept.setBusinessType("05");
                workOrderAccept.setPolicyNo(incrementServiceApplication.getBusinessData().getPolicyList());
                workOrderAccept.setPolicyItemNo(incrementServiceApplication.getBusinessData().getCertno());
                workOrderAccept.setCreateBy(username);
                workOrderAccept.setCreateTime(time);
                workOrderAccept.setUpdateBy(username);
                workOrderAccept.setUpdateTime(time);
                incrementServiceApplicationMapper.insertWorkOrderAcceptTable(workOrderAccept);

                personInfo1.setPersonId(personid1);
                personInfo1.setSex(incrementServiceApplication.getBusinessData().getContaGenderCode());
                personInfo1.setName(incrementServiceApplication.getBusinessData().getContaName());
                personInfo1.setLanguage(incrementServiceApplication.getBusinessData().getPerferLan());
                personInfo1.setMobilePhone(incrementServiceApplication.getBusinessData().getContaMobileNo());
                personInfo1.setOtherCustomerNo(incrementServiceApplication.getBusinessData().getCustomerNo());
                personInfo1.setHomePhone(homephone);
                personInfo1.setCreatedBy(username);
                personInfo1.setCreatedTime(time);
                personInfo1.setUpdatedBy(username);
                personInfo1.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo1);

                personInfo2.setPersonId(personid2);
                personInfo2.setName(incrementServiceApplication.getBusinessData().getCallerName());
                personInfo2.setMobilePhone(incrementServiceApplication.getBusinessData().getPhoneNo());
                personInfo2.setHomePhone(homephone);
                personInfo2.setCreatedBy(username);
                personInfo2.setCreatedTime(time);
                personInfo2.setUpdatedBy(username);
                personInfo2.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo2);

                personInfo3.setPersonId(personid3);
                personInfo3.setName(incrementServiceApplication.getBusinessData().getApplyName());
                personInfo3.setHomePhone(homephone);
                personInfo3.setCreatedBy(username);
                personInfo3.setCreatedTime(time);
                personInfo3.setUpdatedBy(username);
                personInfo3.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo3);
            }else if("demesticAccidentAssitance".equals(incrementServiceApplication.getBusinessData().getType())){
                String personid1 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid2 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid3 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);

                acceptDetailInfo.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                acceptDetailInfo.setCallRelationBy(incrementServiceApplication.getBusinessData().getCallerRelationCode());
                acceptDetailInfo.setProp17(incrementServiceApplication.getBusinessData().getAccidentContent());
                acceptDetailInfo.setCallCenterId(incrementServiceApplication.getBusinessData().getBusinessSeqNo());
                acceptDetailInfo.setEmail(incrementServiceApplication.getBusinessData().getEmail());
                acceptDetailInfo.setContactsPersonId(personid1);
                acceptDetailInfo.setCallPersonId(personid2);
                acceptDetailInfo.setComplaintPersonId(personid3);
                acceptDetailInfo.setCreateBy(username);
                acceptDetailInfo.setCreateTime(time);
                acceptDetailInfo.setUpdateBy(username);
                acceptDetailInfo.setUpdateTime(time);
                IncrementBusinessData businessData = incrementServiceApplication.getBusinessData();
                List<FieldMap> KVMap = fieldMapMapper.selectKVMap("accept_detail_info", "BusinessData");
                for (FieldMap fieldMap : KVMap) {
                    fieldMap.getTargetColumnName();
                    fieldMap.getSourceFiledName();
                    Map map = new HashMap<String, String>();
                    map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                    VoUtils voUtils = new VoUtils<BusinessData>();
                    acceptDetailInfo = (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo, map, businessData);
                }
                incrementServiceApplicationMapper.insertAcceptDetialInfoTable(acceptDetailInfo);

                workOrderAccept.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                workOrderAccept.setStatus("02");
                workOrderAccept.setAcceptBy(incrementServiceApplication.getBusinessData().getAccepter());
                workOrderAccept.setAcceptTime(DateUtils.parseDate(incrementServiceApplication.getBusinessData().getAcceptDate()));
                workOrderAccept.setBusinessType("06");
                workOrderAccept.setPolicyNo(incrementServiceApplication.getBusinessData().getPolicyList());
                workOrderAccept.setPolicyItemNo(incrementServiceApplication.getBusinessData().getCertno());
                workOrderAccept.setCreateBy(username);
                workOrderAccept.setCreateTime(time);
                workOrderAccept.setUpdateBy(username);
                workOrderAccept.setUpdateTime(time);
                incrementServiceApplicationMapper.insertWorkOrderAcceptTable(workOrderAccept);

                personInfo1.setPersonId(personid1);
                personInfo1.setSex(incrementServiceApplication.getBusinessData().getContaGenderCode());
                personInfo1.setName(incrementServiceApplication.getBusinessData().getContaName());
                personInfo1.setLanguage(incrementServiceApplication.getBusinessData().getPerferLan());
                personInfo1.setMobilePhone(incrementServiceApplication.getBusinessData().getContaMobileNo());
                personInfo1.setOtherCustomerNo(incrementServiceApplication.getBusinessData().getCustomerNo());
                personInfo1.setHomePhone(homephone);
                personInfo1.setCreatedBy(username);
                personInfo1.setCreatedTime(time);
                personInfo1.setUpdatedBy(username);
                personInfo1.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo1);

                personInfo2.setPersonId(personid2);
                personInfo2.setName(incrementServiceApplication.getBusinessData().getCallerName());
                personInfo2.setMobilePhone(incrementServiceApplication.getBusinessData().getPhoneNo());
                personInfo2.setHomePhone(homephone);
                personInfo2.setCreatedBy(username);
                personInfo2.setCreatedTime(time);
                personInfo2.setUpdatedBy(username);
                personInfo2.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo2);

                personInfo3.setPersonId(personid3);
                personInfo3.setName(incrementServiceApplication.getBusinessData().getApplyName());
                personInfo3.setHomePhone(homephone);
                personInfo3.setCreatedBy(username);
                personInfo3.setCreatedTime(time);
                personInfo3.setUpdatedBy(username);
                personInfo3.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo3);
            }else if("overseaAccidentAssitance".equals(incrementServiceApplication.getBusinessData().getType())){
                String personid1 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid2 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);
                String personid3 = PubFun.createMySqlMaxNoUseCache("person_id", 6, 6);

                acceptDetailInfo.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                acceptDetailInfo.setCallRelationBy(incrementServiceApplication.getBusinessData().getCallerRelationCode());
                acceptDetailInfo.setProp17(incrementServiceApplication.getBusinessData().getAccidentContent());
                acceptDetailInfo.setCallCenterId(incrementServiceApplication.getBusinessData().getBusinessSeqNo());
                acceptDetailInfo.setEmail(incrementServiceApplication.getBusinessData().getEmail());
                acceptDetailInfo.setContactsPersonId(personid1);
                acceptDetailInfo.setCallPersonId(personid2);
                acceptDetailInfo.setComplaintPersonId(personid3);
                acceptDetailInfo.setCreateBy(username);
                acceptDetailInfo.setCreateTime(time);
                acceptDetailInfo.setUpdateBy(username);
                acceptDetailInfo.setUpdateTime(time);
                IncrementBusinessData businessData = incrementServiceApplication.getBusinessData();
                List<FieldMap> KVMap = fieldMapMapper.selectKVMap("accept_detail_info", "BusinessData");
                for (FieldMap fieldMap : KVMap) {
                    fieldMap.getTargetColumnName();
                    fieldMap.getSourceFiledName();
                    Map map = new HashMap<String, String>();
                    map.put(fieldMap.getTargetColumnName(), fieldMap.getSourceFiledName());
                    VoUtils voUtils = new VoUtils<BusinessData>();
                    acceptDetailInfo = (AcceptDetailInfo) voUtils.fromVoToVo(acceptDetailInfo, map, businessData);
                }
                incrementServiceApplicationMapper.insertAcceptDetialInfoTable(acceptDetailInfo);

                workOrderAccept.setWorkOrderNo(incrementServiceApplication.getBusinessData().getApplicationCaseNo());
                workOrderAccept.setStatus("02");
                workOrderAccept.setAcceptBy(incrementServiceApplication.getBusinessData().getAccepter());
                workOrderAccept.setAcceptTime(DateUtils.parseDate(incrementServiceApplication.getBusinessData().getAcceptDate()));
                workOrderAccept.setBusinessType("07");
                workOrderAccept.setPolicyNo(incrementServiceApplication.getBusinessData().getPolicyList());
                workOrderAccept.setPolicyItemNo(incrementServiceApplication.getBusinessData().getCertno());
                workOrderAccept.setCreateBy(username);
                workOrderAccept.setCreateTime(time);
                workOrderAccept.setUpdateBy(username);
                workOrderAccept.setUpdateTime(time);
                incrementServiceApplicationMapper.insertWorkOrderAcceptTable(workOrderAccept);

                personInfo1.setPersonId(personid1);
                personInfo1.setSex(incrementServiceApplication.getBusinessData().getContaGenderCode());
                personInfo1.setName(incrementServiceApplication.getBusinessData().getContaName());
                personInfo1.setLanguage(incrementServiceApplication.getBusinessData().getPerferLan());
                personInfo1.setMobilePhone(incrementServiceApplication.getBusinessData().getContaMobileNo());
                personInfo1.setOtherCustomerNo(incrementServiceApplication.getBusinessData().getCustomerNo());
                personInfo1.setHomePhone(homephone);
                personInfo1.setCreatedBy(username);
                personInfo1.setCreatedTime(time);
                personInfo1.setUpdatedBy(username);
                personInfo1.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo1);

                personInfo2.setPersonId(personid2);
                personInfo2.setName(incrementServiceApplication.getBusinessData().getCallerName());
                personInfo2.setMobilePhone(incrementServiceApplication.getBusinessData().getPhoneNo());
                personInfo2.setHomePhone(homephone);
                personInfo2.setCreatedBy(username);
                personInfo2.setCreatedTime(time);
                personInfo2.setUpdatedBy(username);
                personInfo2.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo2);

                personInfo3.setPersonId(personid3);
                personInfo3.setName(incrementServiceApplication.getBusinessData().getApplyName());
                personInfo3.setHomePhone(homephone);
                personInfo3.setCreatedBy(username);
                personInfo3.setCreatedTime(time);
                personInfo3.setUpdatedBy(username);
                personInfo3.setUpdatedTime(time);
                incrementServiceApplicationMapper.insertPersonInfoTable(personInfo3);
            }
        }
        return 1;
    }
}
