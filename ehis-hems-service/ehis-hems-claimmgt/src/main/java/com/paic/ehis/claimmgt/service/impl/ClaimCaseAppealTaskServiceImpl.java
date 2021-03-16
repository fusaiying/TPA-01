package com.paic.ehis.claimmgt.service.impl;

import java.util.Date;
import java.util.List;

import com.paic.ehis.claimmgt.domain.ClaimCaseAppealBillDetail;
import com.paic.ehis.claimmgt.domain.ClaimCaseAppealRecord;
import com.paic.ehis.claimmgt.domain.ClaimCaseAppealTask;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseAppealTaskVo;
import com.paic.ehis.claimmgt.mapper.ClaimCaseAppealBillDetailMapper;
import com.paic.ehis.claimmgt.mapper.ClaimCaseAppealRecordMapper;
import com.paic.ehis.claimmgt.mapper.ClaimCaseAppealTaskMapper;
import com.paic.ehis.claimmgt.service.IClaimCaseAppealTaskService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件申诉任务Service业务层处理
 * 
 * @author sino
 * @date 2021-03-15
 */
@Service
public class ClaimCaseAppealTaskServiceImpl implements IClaimCaseAppealTaskService
{
    @Autowired
    private ClaimCaseAppealTaskMapper claimCaseAppealTaskMapper;

    @Autowired
    private ClaimCaseAppealRecordMapper claimCaseAppealRecordMapper;

    @Autowired
    private ClaimCaseAppealBillDetailMapper claimCaseAppealBillDetailMapper;

    /**
     * 查询案件申诉任务
     * 
     * @param taskId 案件申诉任务ID
     * @return 案件申诉任务
     */
    @Override
    public ClaimCaseAppealTask selectClaimCaseAppealTaskById(Long taskId)
    {
        return claimCaseAppealTaskMapper.selectClaimCaseAppealTaskById(taskId);
    }

    /**
     * 查询案件申诉任务列表
     * 
     * @param claimCaseAppealTask 案件申诉任务
     * @return 案件申诉任务
     */
    @Override
    public List<ClaimCaseAppealTask> selectClaimCaseAppealTaskList(ClaimCaseAppealTask claimCaseAppealTask)
    {
        return claimCaseAppealTaskMapper.selectClaimCaseAppealTaskList(claimCaseAppealTask);
    }

    /**
     * 新增案件申诉任务
     * 
     * @param bean 案件申诉任务
     * @return 结果
     */
    @Override
    public int insertClaimCaseAppealTask(ClaimCaseAppealTask bean)
    {
        Long taskId = claimCaseAppealTaskMapper.getTaskId();

        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        bean.setTaskId(taskId);
        bean.setCreateTime(nowDate);
        bean.setCreateBy(username);
        bean.setUpdateTime(nowDate);
        bean.setUpdateBy(username);
        bean.setStatus("Y");
        bean.setAppealStatus("01");
        bean.setApplyOperator(username);
        this.insertRecordLog(bean,null);
        return claimCaseAppealTaskMapper.insertClaimCaseAppealTask(bean);
    }

    /**
     * 修改案件申诉任务
     * 
     * @param bean 案件申诉任务
     * @return 结果
     */
    @Override
    public ClaimCaseAppealTask updateClaimCaseAppealTask(ClaimCaseAppealTask bean)
    {
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        bean.setUpdateTime(nowDate);
        bean.setUpdateBy(username);
        bean.setAuditor(username);

        // 进入申诉初审状态
        if(bean.getDealType().equalsIgnoreCase("initiate")) {
            bean.setAppealStatus("02");
        }
        //初审确认  同意 / 不同意  （申诉完成 / 申诉退回）
        if(bean.getDealType().equalsIgnoreCase("audit")) {
           if(bean.getIsAgree().equals("01")) {
               bean.setAppealStatus("03");
               /**apply_type
                * TODO:
                初审决定选择同意，点击初审确认后，
                此时申诉案件报案号为原报案号-1（流水），
                并且流转至理赔审核岗原审核人工作池，
                同时将原案件的批次信息、就诊人信息、申请人信息、收款人信息、受理信息、账单信息、赔付明细（重新匹配理算）、
                赔付备注（类型为申诉案件）带入申诉案件中，需要重新进行赔付计算，重新下发赔付结论。
                */
               String newRptNo = this.getNewRptNo(bean.getAppealRptNo());
               bean.setNewRptNo(newRptNo);
               this.insertTableData(bean);
           } else {
               bean.setAppealStatus("04");
           }
        }
        Long orgRecordId = this.updateHistoryFlag(bean);
        this.insertRecordLog(bean,orgRecordId);
        claimCaseAppealTaskMapper.updateClaimCaseAppealTask(bean);
        return bean;
    }

    /**
     * 批量删除案件申诉任务
     * 
     * @param taskIds 需要删除的案件申诉任务ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAppealTaskByIds(Long[] taskIds)
    {
        return claimCaseAppealTaskMapper.deleteClaimCaseAppealTaskByIds(taskIds);
    }

    /**
     * 删除案件申诉任务信息
     * 
     * @param taskId 案件申诉任务ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseAppealTaskById(Long taskId)
    {
        return claimCaseAppealTaskMapper.deleteClaimCaseAppealTaskById(taskId);
    }

    /**
     * 查询案件申诉任务列表
     *
     * @param vo 案件申诉任务
     * @return 案件申诉任务集合
     */
    @Override
    public List<ClaimCaseAppealTaskVo> getAppealList(ClaimCaseAppealTaskVo vo) {
        return claimCaseAppealTaskMapper.getAppealList(vo);
    }

    @Override
    public int updateClaimCaseAppealBill(ClaimCaseAppealTask bean) {
        int result = 0;

        ClaimCaseAppealBillDetail paramBean = new ClaimCaseAppealBillDetail();
        paramBean.setRptNo(bean.getNewRptNo());
        List<ClaimCaseAppealBillDetail> list = claimCaseAppealBillDetailMapper.claimCaseAppealBillDetailList(paramBean);

        ClaimCaseAppealBillDetail detail = new ClaimCaseAppealBillDetail();

        for(ClaimCaseAppealBillDetail detailInfo : list) {
            detail.setOldBillId(Long.valueOf(detailInfo.getUpdateBy()));
            detail.setRptNo(detailInfo.getRptNo());
            detail.setBillId(detailInfo.getBillId());
            result += claimCaseAppealBillDetailMapper.updateClaimCaseAppealBillDetail(detail);
            result += claimCaseAppealBillDetailMapper.updateClaimCaseAppealCalBill(detail);
            result += claimCaseAppealBillDetailMapper.updateClaimCaseAppealBillDiagnosis(detail);
            result += claimCaseAppealBillDetailMapper.updateClaimCaseAppealBill(detail);
        }
        return result;
    }


    /**
     * 添加节点记录
     * @author: hjw
     * @time : 2021-3-15
     */
    private void  insertRecordLog(ClaimCaseAppealTask bean, Long orgRecordId){
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        ClaimCaseAppealRecord record = new ClaimCaseAppealRecord();
        record.setTaskId(bean.getTaskId());
        record.setOperation(bean.getAppealStatus());
        record.setOperator(username);
        record.setOrgRecordId(orgRecordId);
        record.setHistoryFlag("N");
        record.setCreateTime(nowDate);
        record.setCreateBy(username);
        record.setUpdateTime(nowDate);
        record.setUpdateBy(username);
        record.setStatus("Y");
        claimCaseAppealRecordMapper.insertClaimCaseAppealRecord(record);
    }


    /**
     * taskid记录更新未历史记录
     * @author: hjw
     * @time : 2021-3-15
     */
    private Long updateHistoryFlag(ClaimCaseAppealTask bean) {
        Long result = null;
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        ClaimCaseAppealRecord record = new ClaimCaseAppealRecord();
        record.setHistoryFlag("N");
        record.setTaskId(bean.getTaskId());
        List<ClaimCaseAppealRecord> recordList = claimCaseAppealRecordMapper.selectClaimCaseAppealRecordList(record);
        for(ClaimCaseAppealRecord updateBean : recordList) {
            result =  updateBean.getRecordId();
            ClaimCaseAppealRecord upDto = new ClaimCaseAppealRecord();
            upDto.setRecordId(result);
            upDto.setHistoryFlag("Y");
            upDto.setUpdateTime(nowDate);
            upDto.setUpdateBy(username);
            claimCaseAppealRecordMapper.updateClaimCaseAppealRecord(upDto);
        }
        return result;
    }

    /**
     * 获取申诉成功后的报案号
     * @author: hjw
     * @time : 2021-3-15
     */
    private String getNewRptNo(String appealRptNo){
        String appealNewRptNo = claimCaseAppealTaskMapper.getAppealNewRptNo(appealRptNo);
        if(StringUtils.isBlank(appealNewRptNo)){
            appealRptNo +="-1" ;
        } else {
            String[] rptArr = appealNewRptNo.split("-");
            int endNo = Integer.valueOf(rptArr[1]) + 1;
            appealRptNo += "-" + endNo;
        }
        return appealRptNo;
    }

    private void  insertTableData(ClaimCaseAppealTask bean){

        // select * from claim_case ;   -- cp  07
        //select * from claim_case_accept ; -- cp 案件受理信息表
        //select * from claim_case_apply_type ; -- cp  案件受理信息表 申请原因
        //select * from claim_case_bill  -- cp  案件账单明细表
        //select * from claim_case_bill_detail; -- cp  案件账单费用项明细表
        //SELECT * FROM claim_case_bill_diagnosis ; -- CP  案件账单诊断信息表
        //SELECT * FROM claim_case_insured ; -- cp  案件被保人信息表
        //SELECT * FROM claim_case_payee ; -- cp  案件领款人信息表
        //SELECT * FROM claim_case_register ; -- cp  案件申请人信息表
        //select * from claim_case_remark ; -- cp  案件备注表
        //SELECT * FROM claim_case_policy ; -- cp  案件保单关联表
        // SELECT * FROM claim_case_cal_bill -- cp 案件赔付账单明细表


        claimCaseAppealTaskMapper.insertClaimTableData(bean);
    }
}
