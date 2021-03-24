package com.paic.ehis.cs.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.QualityDTO;
import com.paic.ehis.cs.domain.dto.QualityFlagDTO;
import com.paic.ehis.cs.domain.dto.QualityInspectionDTO;
import com.paic.ehis.cs.domain.dto.WorkOrderQueryDTO;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.mapper.*;
import com.paic.ehis.cs.service.IQualityInspectionAcceptService;
import com.paic.ehis.cs.utils.CodeEnum;
import com.paic.ehis.cs.utils.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 质检受理服务实现 QualityInspectionAcceptServiceImpl
 * by Louis
 */
@Service
public class QualityInspectionAcceptServiceImpl implements IQualityInspectionAcceptService
{

    @Autowired
    private QualityInspectionAcceptMapper qualityInspectionAcceptMapper;

    @Autowired
    private FlowLogMapper flowLogMapper;

    @Autowired
    private FieldMapMapper fieldMapMapper;

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private WorkOrderAcceptMapper workOrderAcceptMapper;


    @Override

    //发送质检工作池/工单查询
    public List<AcceptVo> selectSendPoolData(WorkOrderQueryDTO workOrderQueryDTO) {
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectSendByVo(workOrderQueryDTO);
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                //被保人
                String getInsuredPersonId= list.get(i).getInsuredPersonId();
                if(StringUtils.isNotEmpty(getInsuredPersonId)){
                    PersonInfo setInsuredPerson=personInfoMapper.selectPersonInfoById(getInsuredPersonId);
                    if(setInsuredPerson!=null){
                        list.get(i).setInsuredPerson(setInsuredPerson);
                    }
                }
                //投保人
                String getHolderPersonId= list.get(i).getHolderPersonId();
                if(StringUtils.isNotEmpty(getHolderPersonId)){
                    PersonInfo setHolderPerson=personInfoMapper.selectPersonInfoById(getHolderPersonId);
                    if(setHolderPerson!=null){
                        list.get(i).setHolderPerson(setHolderPerson);
                    }
                }

         /*       //受理人
                String getAcceptUserId= list.get(i).getAcceptUserId();
                if(StringUtils.isNotEmpty(getAcceptUserId)){
                    UserInfo setAcceptUser=userInfoMapper.selectUserInfoById(getAcceptUserId);
                    if(setAcceptUser!=null){
                        list.get(i).setAcceptUser(setAcceptUser);
                    }
                }
*/
                //原处理人
                String getModifyUserId= list.get(i).getModifyUserId();
                if(StringUtils.isNotEmpty(getModifyUserId)){
                    UserInfo setModifyUser=userInfoMapper.selectUserInfoById(getModifyUserId);
                    if(setModifyUser!=null){
                        list.get(i).setModifyUser(setModifyUser);
                    }
                }

            }
        }
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

    /**
     * 工单获取
     */
    @Override
    public AcceptVo updateSendByVoById(String workOrderNo) {
        AcceptVo acceptVo = qualityInspectionAcceptMapper.selectSendByVoById1(workOrderNo);//先查询状态为处理中的工单
        AcceptVo acceptVo1 = qualityInspectionAcceptMapper.selectSendByVoById2(workOrderNo);//不存在则获取最新的非处理的工单数据
        String updateby = String.valueOf(SecurityUtils.getUsername());
        WorkOrderAccept workOrderAccept = workOrderAcceptMapper.selectWorkOrderAcceptById(workOrderNo);
        if (null != acceptVo && !acceptVo.getUpdateBy().equals(updateby)) {//如果获取的数据的操作人不是当前操作人，说明数据目前在别人的工作池
            if (null != acceptVo.getBusinessType()) {//不是预约已处理
                workOrderAccept.setStatus("02");
                workOrderAccept.setAcceptBy(SecurityUtils.getUsername());
                workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
                workOrderAccept.setUpdateTime(DateUtils.getNowDate());
                workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
            }
            acceptVo.setFlag("1");//弹框显示前工单正在处理，是否申请到个人池
            return acceptVo;//存在则获取处理中的工单数据
        }
        else if (null != acceptVo1 && acceptVo1.getUpdateBy().equals(updateby)) {//说明此数据在自己的个人池
            if (null != acceptVo1.getBusinessType()) {//不是预约已处理
                workOrderAccept.setStatus("02");
                workOrderAccept.setAcceptBy(SecurityUtils.getUsername());
                workOrderAccept.setUpdateBy(SecurityUtils.getUsername());
                workOrderAccept.setUpdateTime(DateUtils.getNowDate());
                workOrderAcceptMapper.updateWorkOrderAccept(workOrderAccept);
            }
            acceptVo1.setFlag("2");//不出弹框
        } return acceptVo1;
    }
    //质检中，待质检查询
    @Override
    public List<AcceptVo> selectAcceptPoolData(WorkOrderQueryDTO workOrderQueryDTO) {
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectAcceptByVo(workOrderQueryDTO);
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                //被保人
                String getInsuredPersonId= list.get(i).getInsuredPersonId();
                if(StringUtils.isNotEmpty(getInsuredPersonId)){
                    PersonInfo setInsuredPerson=personInfoMapper.selectPersonInfoById(getInsuredPersonId);
                    if(setInsuredPerson!=null){
                        list.get(i).setInsuredPerson(setInsuredPerson);
                    }
                }
                //投保人
                String getHolderPersonId= list.get(i).getHolderPersonId();
                if(StringUtils.isNotEmpty(getHolderPersonId)){
                    PersonInfo setHolderPerson=personInfoMapper.selectPersonInfoById(getHolderPersonId);
                    if(setHolderPerson!=null){
                        list.get(i).setHolderPerson(setHolderPerson);
                    }
                }
               list.get(i).setCreateBy(list.get(i).getAcceptBy());
           /*     //受理人
                String getAcceptUserId= list.get(i).getAcceptUserId();
                if(StringUtils.isNotEmpty(getAcceptUserId)){
                    UserInfo setAcceptUser=userInfoMapper.selectUserInfoById(getAcceptUserId);
                    if(setAcceptUser!=null){
                        list.get(i).setAcceptUser(setAcceptUser);
                    }
                }*/
                //处理人
                String getModifyUserId= list.get(i).getModifyUserId();
                if(StringUtils.isNotEmpty(getModifyUserId)){
                    UserInfo setModifyUser=userInfoMapper.selectUserInfoById(getModifyUserId);
                    if(setModifyUser!=null){
                        list.get(i).setModifyUser(setModifyUser);
                    }
                }

            }
        }
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

    @Transactional
    @Override
    public int insertAcceptVoBatch(String[] ids, Map<String, String> param){
        List<QualityInspectionAccept> list=new ArrayList<>();
        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        QualityInspectionAccept qualityInspectionAccept=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            //流转记录添加
            String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20);
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);

            qualityInspectionAccept=new QualityInspectionAccept();
            qualityInspectionAccept.setWorkOrderNo(ids[i]);
            qualityInspectionAccept.setStatus(param.get("status"));
            qualityInspectionAccept.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
            qualityInspectionAccept.setCreatedTime(DateUtils.getNowDate());
            qualityInspectionAccept.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
            qualityInspectionAccept.setUpdatedTime(DateUtils.getNowDate());
            list.add(qualityInspectionAccept);
        }
        //记录操作轨迹
        flowLogMapper.insertBatch(flowLogList);
        return qualityInspectionAcceptMapper.insertAcceptBatch(list);
    }

    @Transactional
    @Override
    public int inspectionHandleStatusByIds(String[] ids, Map<String, String> param) {
        WorkOrderQueryDTO workOrderQueryDTO=new WorkOrderQueryDTO();
        List<String> idList= Arrays.asList(ids);
        workOrderQueryDTO.setAcceptStatus(param.get("status"));
        workOrderQueryDTO.setStatus(CodeEnum.INSPECTION_STATE_01.getCode());
        workOrderQueryDTO.setWorkOrderNoList(idList);
        workOrderQueryDTO.setUpdateBy(SecurityUtils.getUsername());
        workOrderQueryDTO.setUpdateTime(DateUtils.getNowDate());

        List<FlowLog> flowLogList=new ArrayList<>();
        FlowLog flowLog=null;
        for (int i = 0; i < ids.length; i++) {
            flowLog=new FlowLog();
            //流转记录添加
            String flow_id= PubFun.createMySqlMaxNoUseCache("cs_flow_id",20,20);
            flowLog.setFlowId(flow_id);
            flowLog.setCreatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setCreatedTime(DateUtils.getNowDate());
            flowLog.setUpdatedBy(String.valueOf(SecurityUtils.getUsername()));
            flowLog.setUpdatedTime(DateUtils.getNowDate());
            flowLog.setWorkOrderNo(ids[i]);
//            flowLog.setSubId(ids[i]);

            flowLog.setLinkCode(param.get("linkCode"));
            flowLog.setOperateCode(param.get("operateCode"));
            flowLogList.add(flowLog);
        }
        //记录操作轨迹
        flowLogMapper.insertBatch(flowLogList);
        //表状态更新
        return qualityInspectionAcceptMapper.updateAcceptByVoDTO(workOrderQueryDTO);
    }

    @Override
    public List<AcceptVo> getAcceptVoByIds(String[] ids) {
        List<String> idList= Arrays.asList(ids);
        WorkOrderQueryDTO workOrderQueryDTO=new WorkOrderQueryDTO();
        workOrderQueryDTO.setWorkOrderNoList(idList);
        List<AcceptVo> list=qualityInspectionAcceptMapper.selectAcceptByVo(workOrderQueryDTO);
        return list==null ? new ArrayList<AcceptVo>() : list;
    }

    @Override
    public AcceptVo getOneAcceptInfo(WorkOrderQueryDTO workOrderQueryDTO) {
        //共同属性值提取
        AcceptVo acceptVo=qualityInspectionAcceptMapper.getAcceptInfo(workOrderQueryDTO);
        //差异详情提取
        AcceptDetailInfo acceptDetailInfo= qualityInspectionAcceptMapper.getAcceptDetailInfo(workOrderQueryDTO);
        String sourceName="DemandAcceptVo";
        String businessType=workOrderQueryDTO.getBusinessType();
        if(CodeEnum.BUSINESS_TYPE_02.getCode().equals(businessType)){
            sourceName="ReservationAcceptVo";
        }else if(CodeEnum.BUSINESS_TYPE_03.getCode().equals(businessType)){
            sourceName="ComplaintAcceptVo";
            //投诉人
            PersonInfo complaintPerson=personInfoMapper.selectPersonInfoById(acceptVo.getComplaintPersonId());
            if(complaintPerson!=null) {
                if(StringUtils.isNotEmpty(complaintPerson.getHomePhone())){
                    String[] homePhone1=complaintPerson.getHomePhone().split("-");
                    complaintPerson.setHomePhone1(homePhone1);
                }
                if(StringUtils.isNotEmpty(complaintPerson.getWorkPhone())){
                    String[] workPhone1=complaintPerson.getWorkPhone().split("-");
                    complaintPerson.setWorkPhone1(workPhone1);
                }
                if(StringUtils.isNotEmpty(complaintPerson.getLinePhone())){
                    String[] linePhone1=complaintPerson.getLinePhone().split("-");
                    complaintPerson.setLinePhone1(linePhone1);
                }
                acceptVo.setComplaintPerson(complaintPerson);
            }
        }
        //来电人
        PersonInfo callPerson=personInfoMapper.selectPersonInfoById(acceptVo.getCallPersonId());
        if(callPerson!=null) {
            if(StringUtils.isNotEmpty(callPerson.getHomePhone())){
                String[] homePhone1=callPerson.getHomePhone().split("-");
                callPerson.setHomePhone1(homePhone1);
            }
            if(StringUtils.isNotEmpty(callPerson.getWorkPhone())){
                String[] workPhone1=callPerson.getWorkPhone().split("-");
                callPerson.setWorkPhone1(workPhone1);
            }
            if(StringUtils.isNotEmpty(callPerson.getLinePhone())){
                String[] linePhone1=callPerson.getLinePhone().split("-");
                callPerson.setLinePhone1(linePhone1);
            }
            acceptVo.setCallPerson(callPerson);
        }
        //联系人
        PersonInfo contactsPerson=personInfoMapper.selectPersonInfoById(acceptVo.getContactsPersonId());
        if(contactsPerson!=null) {
            if(StringUtils.isNotEmpty(contactsPerson.getHomePhone())){
                String[] homePhone1=contactsPerson.getHomePhone().split("-");
                contactsPerson.setHomePhone1(homePhone1);
            }
            if(StringUtils.isNotEmpty(contactsPerson.getWorkPhone())){
                String[] workPhone1=contactsPerson.getWorkPhone().split("-");
                contactsPerson.setWorkPhone1(workPhone1);
            }
            if(StringUtils.isNotEmpty(contactsPerson.getLinePhone())){
                String[] linePhone1=contactsPerson.getLinePhone().split("-");
                contactsPerson.setLinePhone1(linePhone1);
            }
            acceptVo.setContactsPerson(contactsPerson);
        }

        //差异属性转换
        List<FieldMap> fieldMapList=fieldMapMapper.selectFieldMapById(sourceName);
        Map<String,String> fieldMap=new HashMap<>();
        for (int i = 0; i < fieldMapList.size(); i++) {
            fieldMap.put(fieldMapList.get(i).getSourceFiledName(),fieldMapList.get(i).getTargetColumnName());
        }
        VoUtils voUtils=new VoUtils<AcceptVo>();
        //转换
        acceptVo= (AcceptVo) voUtils.fromVoToVo(acceptVo,fieldMap,acceptDetailInfo);
        return acceptVo;
    }

    @Override
    public List<QualityAcceptVo> selectQualityVo(QualityDTO qualityDTO) {
        return qualityInspectionAcceptMapper.selectQualityVo(qualityDTO);
    }
    @Override
    public List<QualityInspectionHandleVo> selectQualityFlagVO(QualityInspectionDTO qualityInspectionDTO) {
        return qualityInspectionAcceptMapper.selectQualityFlagVO(qualityInspectionDTO);
    }

    //信息需求失效批处理
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public List<AcceptVo> batchAcceptVo(String invalidDateStar) {
        if (StringUtils.isEmpty(invalidDateStar)) {
            throw new RuntimeException("日期为空！");
        }
        SimpleDateFormat foramt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = foramt.parse(invalidDateStar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 2); // 目前时间加30分钟

        cal1.add(Calendar.MINUTE, -2); // 目前时间减30分钟
     /*   if (1 == cal.get(Calendar.DAY_OF_WEEK)) {
            cal.add(Calendar.DATE, -1);
        }
        cal.add(Calendar.DAY_OF_MONTH, -7);//时间减去7天
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//Calendar.MONDAY 这个是周一

        if (1 == cal1.get(Calendar.DAY_OF_WEEK)) {
            cal1.add(Calendar.DATE, -1);
        }
        cal1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//这个是周日*/

        WorkOrderQueryDTO workOrderQueryDTO = new WorkOrderQueryDTO();//根据工单状态
        //workOrderQueryDTO.setEndCaseStartTime(foramt.format(cal.getTime()) + " 00:00:00");//获取上周一
        //workOrderQueryDTO.setEndCaseEndTime(foramt.format(cal1.getTime()) + " 23:59:59");//获取上周天
        workOrderQueryDTO.setEndCaseEndTime(foramt.format(cal.getTime()));//获取一小时后
        workOrderQueryDTO.setEndCaseStartTime(foramt.format(cal1.getTime()));//获取一小时前
        //2.工单状态处理； 04-已完成的工单且没有被质检过
        workOrderQueryDTO.setAcceptStatus(CodeEnum.ORDER_STATE_04.getCode());
        //3.业务类型为： 01-信息需求和03-投诉的才可以质检
        List<String> businessTypeList = new ArrayList<>();
        businessTypeList.add(CodeEnum.BUSINESS_TYPE_01.getCode());
        businessTypeList.add(CodeEnum.BUSINESS_TYPE_03.getCode());
        workOrderQueryDTO.setBusinessTypeList(businessTypeList);
        List<AcceptVo> acceptVos = new ArrayList<>();
        List<AcceptVo> list = qualityInspectionAcceptMapper.getWorkOrderCountByUserId(workOrderQueryDTO);//根据操作人分组获取工单
        int i = list.size();
        for (AcceptVo acceptVo : list) {
            String workOrderNos = acceptVo.getWorkOrderNos();
            String[] strings = workOrderNos.split(",");
            Set<String> set = new HashSet<String>();
            Random random = new Random();
            double h = i * 0.1;
            if (h <= 1) {
                int j = 1;//抽取总量的10%，如计算结果≤1，则取1
                int a = 0;
                while (true) {
                    a = random.nextInt(strings.length);
                    set.add(strings[a]);
                    if (set.size() >= j) {
                        break;
                    }
                }
                for (String ran : set) {
                    System.out.println(ran);
                    workOrderQueryDTO.setWorkOrderNo(ran);
                    acceptVos=qualityInspectionAcceptMapper.getWorkOrderCountByUserId(workOrderQueryDTO);
                }
            } else if (h > 1) {
                int j = (int) h;//抽取总量的10%，计算结果＞1，则向下取整
                int a = 0;
                while (true) {
                    a = random.nextInt(strings.length);
                    set.add(strings[a]);
                    if (set.size() >= j) {
                        break;
                    }
                }
                for (String ran : set) {
                    System.out.println(ran);
                    workOrderQueryDTO.setWorkOrderNo(ran);
                    acceptVos=qualityInspectionAcceptMapper.getWorkOrderCountByUserId(workOrderQueryDTO);
                }
            } else if (h == 0) {
                int j = 0;//抽取总量的10%，计算结果为0，则取0
                System.out.println(j);
                workOrderQueryDTO.setWorkOrderNo(null);
            }
        }
        return acceptVos;
    }

}

