package com.paic.ehis.cs.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.vo.ComplaintAcceptVo;
import com.paic.ehis.cs.domain.vo.ReservationAcceptVo;
import com.paic.ehis.cs.mapper.AcceptDetailInfoMapper;
import com.paic.ehis.cs.mapper.FlowLogMapper;
import com.paic.ehis.cs.mapper.PersonInfoMapper;
import com.paic.ehis.cs.mapper.WorkOrderAcceptMapper;
import com.paic.ehis.cs.service.IReservationAcceptVoService;
import com.paic.ehis.cs.service.IWorkOrderAcceptService;
import com.paic.ehis.cs.utils.CodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 工单申请信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-21
 */
@Service
public class WorkOrderAcceptServiceImpl implements IWorkOrderAcceptService
{
    @Autowired
    private WorkOrderAcceptMapper workOrderAcceptMapper;
    @Autowired
    private AcceptDetailInfoMapper acceptDetailInfoMapper;
    @Autowired
    private PersonInfoMapper personInfoMapper;
    @Autowired
    private FlowLogMapper flowLogMapper;
    @Autowired
    private IReservationAcceptVoService iReservationAcceptVoService;

//    ######################################################################## 外部受理接口 ########################################################################

    @Transactional
    @Override
    public int insertBasicServiceApplication(BasicServiceAppilcation businessData) {
        //工单受理
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        //受理详情
        AcceptDetailInfo acceptDetailInfo=new AcceptDetailInfo();
        //接口默认处理人
//        String username = "SYSTEM";
        String sourceName="";
        //信息需求
        if ("informationApplication".equals(businessData.getType())) {
            workOrderAccept.setBusinessType(CodeEnum.BUSINESS_TYPE_01.getCode());
            sourceName="DemandAcceptVo";
        } else if("complainApplication".equals(businessData.getType())){
            //投诉人
            String complainPersonId= PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10);
            insertComplainPersonInfo(businessData,complainPersonId);

            workOrderAccept.setBusinessType(CodeEnum.BUSINESS_TYPE_03.getCode());
            sourceName="ComplaintAcceptVo";
            acceptDetailInfo.setItemCode(CodeEnum.SERVICE_ITEM_B00006.getCode());
            acceptDetailInfo.setComplaintPersonId(complainPersonId);
            acceptDetailInfo.setContent(businessData.getComplainContent());
        }

        //联系人
        String contaPersonId= PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10);
        insertContaPersonInfo(businessData,contaPersonId);
        //来电人
        String callPersonId= PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10);
        insertCallPersonInfo(businessData,callPersonId);

        String workOrderNo="9900000000"+PubFun.createMySqlMaxNoUseCache("cs_work_order_no",10,6);
        //workOrderAccept信息处理
        workOrderAccept.setWorkOrderNo(workOrderNo);
        workOrderAccept.setOtherNo(businessData.getApplicationCaseNo());
        workOrderAccept.setStatus(CodeEnum.ORDER_STATE_01.getCode());
        workOrderAccept.setAcceptBy(businessData.getAccepter());
        workOrderAccept.setAcceptTime(businessData.getAcceptDate());
        //确认是否被保人客户号
        workOrderAccept.setInsuredNo(businessData.getCustomerNo());
        workOrderAccept.setPolicyNo(businessData.getPolicyList());
        workOrderAccept.setPolicyItemNo(businessData.getCertno());
        workOrderAccept.setRiskCode(businessData.getProductName());

        workOrderAccept.setCreateBy(businessData.getAccepter());
        workOrderAccept.setCreateTime(DateUtils.getNowDate());
//        workOrderAccept.setUpdateBy(username);
//        workOrderAccept.setUpdateTime(DateUtils.getNowDate());

        //acceptDetailInfo信息处理
        workOrderAccept.setWorkOrderNo(workOrderNo);
        acceptDetailInfo.setCallCenterId(businessData.getBusinessSeqNo());
        acceptDetailInfo.setContactsPersonId(contaPersonId);
        acceptDetailInfo.setCallPersonId(callPersonId);
        acceptDetailInfo.setEmail(businessData.getEmail());
        acceptDetailInfo.setContent(businessData.getApplicationContent());
        //可能要转码
        acceptDetailInfo.setChannelCode(businessData.getAcceptChannel());
        acceptDetailInfo.setItemCode(businessData.getServiceSecondItemCode());
        acceptDetailInfo.setCallRelationBy(businessData.getCallerInsRelation());
        acceptDetailInfo.setContactsRelationBy(businessData.getContactsInsRelation());
        acceptDetailInfo.setPriorityLevel(businessData.getPriorityCode());

        acceptDetailInfo.setCreateBy(businessData.getAccepter());
        acceptDetailInfo.setCreateTime(DateUtils.getNowDate());
        acceptDetailInfo.setUpdateBy(businessData.getAccepter());
        acceptDetailInfo.setUpdateTime(DateUtils.getNowDate());

        acceptDetailInfoMapper.insertAcceptDetailInfo(acceptDetailInfo);
        //轨迹信息
        FlowLog flowLog=new FlowLog();
        String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20);
        flowLog.setFlowId(flow_id);
        flowLog.setWorkOrderNo(businessData.getApplicationCaseNo());
        flowLog.setLinkCode(CodeEnum.ORDER_STATE_01.getCode());
        flowLog.setOperateCode(CodeEnum.OPERATE_CODE_01.getCode());
        flowLog.setStatus(CodeEnum.ORDER_STATE_01.getCode());
        flowLog.setMakeTime(DateUtils.getNowDate());
        flowLog.setCreatedBy(businessData.getAccepter());
        flowLog.setUpdatedBy(businessData.getAccepter());
        flowLog.setCreatedTime(DateUtils.getNowDate());
        flowLog.setUpdatedTime(DateUtils.getNowDate());
        flowLogMapper.insertFlowLog(flowLog);
        return workOrderAcceptMapper.insertWorkOrderAccept(workOrderAccept);
    }

    /**
     * 基础受理联系人信息处理
     * @return
     */
    @Transactional
    public int insertContaPersonInfo(BasicServiceAppilcation businessData,String personId){
        PersonInfo personInfo=new PersonInfo();
        personInfo.setPersonId(personId);
        personInfo.setSex(businessData.getContaGenderCode());
        personInfo.setName(businessData.getContaName());
        personInfo.setLanguage(businessData.getPerferLan());
        personInfo.setMobilePhone(businessData.getContaMobileNo());
        LinePhone contaHomePhone = businessData.getContaHomePhone();
        if(contaHomePhone!=null){
            String homephone = (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneCountryCode())?contaHomePhone.getPhoneCountryCode():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneAreaCode())?contaHomePhone.getPhoneAreaCode():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneNo())?contaHomePhone.getPhoneNo():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getExtensionNo())?contaHomePhone.getExtensionNo():"");
            personInfo.setHomePhone(homephone);
        }
        personInfo.setEmail(businessData.getEmail());
        personInfo.setCreatedBy(businessData.getAccepter());
        personInfo.setCreatedTime(DateUtils.getNowDate());
        personInfo.setUpdatedBy(businessData.getAccepter());
        personInfo.setUpdatedTime(DateUtils.getNowDate());
        return personInfoMapper.insertPersonInfo(personInfo);
    }

    /**
     * 基础受理来电人信息处理
     * @return
     */
    @Transactional
    public int insertCallPersonInfo(BasicServiceAppilcation businessData,String personId){
        PersonInfo personInfo=new PersonInfo();
        personInfo.setPersonId(personId);
        personInfo.setName(businessData.getCaller());
        personInfo.setMobilePhone(businessData.getPhoneNo());
        personInfo.setCreatedBy(businessData.getAccepter());
        personInfo.setCreatedTime(DateUtils.getNowDate());
        personInfo.setUpdatedBy(businessData.getAccepter());
        personInfo.setUpdatedTime(DateUtils.getNowDate());
        return personInfoMapper.insertPersonInfo(personInfo);
    }

    /**
     * 基础受理投诉人信息处理
     * @return
     */
    @Transactional
    public int insertComplainPersonInfo(BasicServiceAppilcation businessData,String personId){
        PersonInfo personInfo=new PersonInfo();
        personInfo.setPersonId(personId);
        personInfo.setSex(businessData.getComplainGenderCode());
        personInfo.setName(businessData.getComplainName());
        personInfo.setIdentity(businessData.getComplainRole());
        personInfo.setCreatedBy(businessData.getAccepter());
        personInfo.setCreatedTime(DateUtils.getNowDate());
        personInfo.setUpdatedBy(businessData.getAccepter());
        personInfo.setUpdatedTime(DateUtils.getNowDate());
        return personInfoMapper.insertPersonInfo(personInfo);
    }

    /**
     * 增值申请人信息处理
     * @param businessData
     * @param personId
     * @return
     */
    @Transactional
    public int insertApplyPersonInfo(IncrementApplication businessData,String personId){
        PersonInfo personInfo=new PersonInfo();
        personInfo.setPersonId(personId);
        personInfo.setName(businessData.getApplyName());
        personInfo.setIdNumber(businessData.getApplyCertificateNo());
        personInfo.setIdType(businessData.getApplyCertificateType());
        personInfo.setCreatedBy(businessData.getAccepter());
        personInfo.setCreatedTime(DateUtils.getNowDate());
        personInfo.setUpdatedBy(businessData.getAccepter());
        personInfo.setUpdatedTime(DateUtils.getNowDate());
        return personInfoMapper.insertPersonInfo(personInfo);
    }

    @Transactional
    @Override
    public int insertIncrementServiceApplication(IncrementApplication businessData) {
        ReservationAcceptVo reservationAcceptVo=new ReservationAcceptVo();
        String applyPersonId= PubFun.createMySqlMaxNoUseCache("cs_person_id", 10, 10);
        //申请信息处理
        insertApplyPersonInfo(businessData,applyPersonId);
        reservationAcceptVo.setComplaintPersonId(applyPersonId);
        // VIP门诊预约
        if ("vipClinic".equals(businessData.getType())) {
            reservationAcceptVo.setItemCode(CodeEnum.SERVICE_ITEM_04.getCode());
            //门诊直接结算
        }else if("clinicDirectPay".equals(businessData.getType())){
            reservationAcceptVo.setItemCode(CodeEnum.SERVICE_ITEM_06.getCode());
        }
        //工单号
        String workOrderNo="9900000000"+PubFun.createMySqlMaxNoUseCache("cs_work_order_no",10,6);
        reservationAcceptVo.setWorkOrderNo(workOrderNo);
        //外部受理号
        reservationAcceptVo.setOtherNo(businessData.getApplicationCaseNo());
        //受理人
        reservationAcceptVo.setAcceptBy(businessData.getApplyName());
        //受理时间
        reservationAcceptVo.setAcceptTime(DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS,businessData.getAcceptDate()));
        //是否有直结资格
        reservationAcceptVo.setOutpatientSettlement(businessData.getIsHasDb());
        /** 是否申请直结*/
        reservationAcceptVo.setSettlementFlag(businessData.getDirectPayFlag());
        //是否持有直结卡
        reservationAcceptVo.setSettlementCard(businessData.getHasInsuranceCard());
        //预约日期
        reservationAcceptVo.setAppointmentDate(businessData.getPreengagementDate());
        //就诊时间段
        reservationAcceptVo.setClinicTime(businessData.getClinicTime());
        //是否持有有效证件
        reservationAcceptVo.setValidCertificate(businessData.getHasValidCertificate());
        //医疗机构编号
        reservationAcceptVo.setMedicalInstitution(businessData.getHospitalId());
        //医疗机构名称
        reservationAcceptVo.setHospitalName(businessData.getHospitalName());
        //科室
        reservationAcceptVo.setDepartment(businessData.getHospitalDepartmentName());
        /** 医院来电号码*/
        reservationAcceptVo.setHospitalWorkCall(businessData.getHospitalCallPhoneNo());
        /** 就诊类型 牙科、生育、*/
        reservationAcceptVo.setVisitType(businessData.getClinicType());
        //疾病名称
        reservationAcceptVo.setDisease(businessData.getDiseaseName());
        //是否意外
        reservationAcceptVo.setAccidentFlag(businessData.getIsAccident());
        //优先级
        reservationAcceptVo.setPriorityLevel(businessData.getPriorityCode());
        /** 最早发病时间*/
        reservationAcceptVo.setEarliestTime(businessData.getEarliestOnsetDate()+"-"+businessData.getEarliestOnsetDateUnit());
        /** 发病时间*/
        reservationAcceptVo.setSymptomTimes(businessData.getOnsetDate()+"-"+businessData.getOnsetDateUnit());
        /** 意外原因*/
        reservationAcceptVo.setAccidentReason(businessData.getAccidentDescr());
        /** 症状或体征 */
        reservationAcceptVo.setSymptomsSigns(businessData.getDiseaseFeature());
        //赔付比例
        reservationAcceptVo.setCompensationRatio(businessData.getPayOutRate());
        //业务内容
        reservationAcceptVo.setContent(businessData.getContent());
        //联系人email
        reservationAcceptVo.setEmail(businessData.getEmail());
        //保单号
        reservationAcceptVo.setPolicyNo(businessData.getPolicyList());
        //分单号
        reservationAcceptVo.setPolicyItemNo(businessData.getCertno());
        /** 来电人与被保险人关系*/
        reservationAcceptVo.setCallRelationBy(businessData.getCallerRelationCode());
        /** 电话中心流水号*/
        reservationAcceptVo.setCallCenterId(businessData.getBusinessSeqNo());
        /** 出险人客户号 */
        reservationAcceptVo.setInsuredNo(businessData.getCustomerNo());
        /** 就诊人姓名*/
        reservationAcceptVo.setInsuredName(businessData.getTreatmentName());
        PersonInfo insuredPerson=new PersonInfo();
        /** 就诊人姓名*/
        insuredPerson.setName(businessData.getTreatmentName());
        insuredPerson.setOtherCustomerNo(businessData.getCustomerNo());
        /** 就诊人证件号码*/
        insuredPerson.setIdNumber(businessData.getTreatmentCertificateNo());
        reservationAcceptVo.setInsuredPerson(insuredPerson);
        //联系人信息
        PersonInfo contaPerson=new PersonInfo();
        //联系人姓名
        contaPerson.setName(businessData.getContaName());
        //联系人性别
        contaPerson.setSex(businessData.getContaGenderCode());
        //联系人邮箱
        contaPerson.setEmail(businessData.getEmail());
        //联系人语言
        contaPerson.setLanguage(businessData.getPerferLan());
        //联系人传真
        contaPerson.setFax(businessData.getFaxNo());
        //联系人手机号
        contaPerson.setMobilePhone(businessData.getContaMobileNo());
        LinePhone contaHomePhone = businessData.getContaHomePhone();
        if(contaHomePhone!=null){
            String homephone = (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneCountryCode())?contaHomePhone.getPhoneCountryCode():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneAreaCode())?contaHomePhone.getPhoneAreaCode():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getPhoneNo())?contaHomePhone.getPhoneNo():"")
                    + "-" + (contaHomePhone != null && !StringUtils.isEmpty(contaHomePhone.getExtensionNo())?contaHomePhone.getExtensionNo():"");
            contaPerson.setHomePhone(homephone);
            String[] homephone1=homephone.split("-");
            contaPerson.setHomePhone1(homephone1);
        }
        reservationAcceptVo.setContactsPerson(contaPerson);
        //申请人信息
        PersonInfo applyPerson=new PersonInfo();
        applyPerson.setName(businessData.getApplyName());
        //直结里面的属性
        applyPerson.setIdNumber(businessData.getApplyCertificateNo());
        applyPerson.setIdType(businessData.getApplyCertificateType());

        reservationAcceptVo.setComplaintPerson(applyPerson);
        //来电人信息
        PersonInfo callerPerson =new PersonInfo();
        /** 来电人姓名*/
        callerPerson.setName(businessData.getCallerName());
        /** 来电人电话*/
        callerPerson.setMobilePhone(businessData.getPhoneNo());
        reservationAcceptVo.setCallPerson(callerPerson);

//        /** 怀孕时间单位 */
//        private String gravidityDateUnit;
//        /** 疫苗名称 */
//        private String immunizationName;
//        /** 疾病代码*/
//        private String disease;
//        /** 婚姻状况*/
//        private String maritalStatus;
//        /** 就诊原因*/
//        private String clinicReason;
//        /** 怀孕日期*/
//        private String gravidityDate;
//        /** 地区编码*/
//        private String regionCode;

        return iReservationAcceptVoService.insertServiceInfo(reservationAcceptVo);
    }















    //    ######################################################################## 内部功能 ########################################################################



    /**
     * 查询工单申请信息 
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 工单申请信息 
     */
    @Override
    public WorkOrderAccept selectWorkOrderAcceptById(String workOrderNo)
    {
        return workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
    }

    //实时查询案件操作人和状态
    @Override
    public String selectWorkOrderAcceptById1(String workOrderNo) {
        WorkOrderAccept workOrderAccept=workOrderAcceptMapper.selectWorkOrderAcceptById1(workOrderNo);
        String status = workOrderAccept.getStatus();
        return status;
    }

    /**
     * 查询工单申请信息 列表
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 工单申请信息 
     */
    @Override
    public List<WorkOrderAccept> selectWorkOrderAcceptList(WorkOrderAccept workOrderAccept)
    {
        return workOrderAcceptMapper.selectWorkOrderAcceptList(workOrderAccept);
    }

    /**
     * 新增工单申请信息 
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 结果
     */
    @Override
    public int insertWorkOrderAccept(WorkOrderAccept workOrderAccept)
    {
        workOrderAccept.setCreateTime(DateUtils.getNowDate());
        return workOrderAcceptMapper.insertWorkOrderAccept(workOrderAccept);
    }

    /**
     * 修改工单申请信息 
     * 
     * @param workOrderAccept 工单申请信息 
     * @return 结果
     */
    @Override
    public int updateWorkOrderAccept(WorkOrderAccept workOrderAccept)
    {
        workOrderAccept.setUpdateTime(DateUtils.getNowDate());
        return workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
    }

    /**
     * 批量删除工单申请信息 
     * 
     * @param workOrderNos 需要删除的工单申请信息 ID
     * @return 结果
     */
    @Override
    public int deleteWorkOrderAcceptByIds(String[] workOrderNos)
    {
        return workOrderAcceptMapper.deleteWorkOrderAcceptByIds(workOrderNos);
    }

    /**
     * 删除工单申请信息 信息
     * 
     * @param workOrderNo 工单申请信息 ID
     * @return 结果
     */
    @Override
    public int deleteWorkOrderAcceptById(String workOrderNo)
    {
        return workOrderAcceptMapper.deleteWorkOrderAcceptById(workOrderNo);
    }

    /**
     * 工单挂起
     * @param complaintAcceptVo
     * @return
     */
    @Override
    public int updateHangReason(ComplaintAcceptVo complaintAcceptVo) {
        WorkOrderAccept workOrderAccept=new WorkOrderAccept();
        workOrderAccept.setWorkOrderNo(complaintAcceptVo.getWorkOrderNo());
        workOrderAccept.setHangReason(complaintAcceptVo.getHangReason());
        workOrderAccept.setHangFlag(complaintAcceptVo.getHangFlag());
        return workOrderAcceptMapper.updateHangReason(complaintAcceptVo);
    }
    /**
     * 查询工单是否挂起
     */
    @Override
    public WorkOrderAccept selectHangFlag(WorkOrderAccept workOrderAccept) {
        return workOrderAcceptMapper.selectHangFlag(workOrderAccept);
    }
}
