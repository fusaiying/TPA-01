package com.paic.ehis.system.service.impl;


import java.util.Calendar;
import java.util.List;

import com.paic.ehis.system.domain.ClaimCase;
import com.paic.ehis.system.domain.ClaimCaseAccept;
import com.paic.ehis.system.domain.ClaimCaseRecord;
import com.paic.ehis.system.domain.dto.CaseDispatchDTO;
import com.paic.ehis.system.domain.dto.ClaimCaseDTO;
import com.paic.ehis.system.domain.dto.DispatchDTO;
import com.paic.ehis.system.domain.vo.ClaimAndBatchVo;
import com.paic.ehis.system.domain.vo.DispatchVo;
import com.paic.ehis.system.domain.vo.ProcessingCaseVo;
import com.paic.ehis.system.mapper.ClaimCaseAcceptMapper;
import com.paic.ehis.system.mapper.ClaimCaseMapper;
import com.paic.ehis.system.mapper.ClaimCaseRecordMapper;
import com.paic.ehis.system.service.IClaimCaseService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.system.domain.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-09
 */
@Service
public class ClaimCaseServiceImpl implements IClaimCaseService
{
    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;

    @Autowired
    private ClaimCaseAcceptMapper claimCaseAcceptMapper;

    /**
     * 查询案件信息 
     * 
     * @param rptNo 案件信息 ID
     * @return 案件信息 
     */
    @Override
    public ClaimCase selectClaimCaseById(String rptNo)
    {
        return claimCaseMapper.selectClaimCaseById(rptNo);
    }

    /**
     * 查询案件信息 列表
     * 
     * @param claimCase 案件信息 
     * @return 案件信息 
     */
    @Override
    public List<ClaimCase> selectClaimCaseList(ClaimCase claimCase)
    {
        return claimCaseMapper.selectClaimCaseList(claimCase);
    }

    /**
     * 查询处理中案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    @Override
    public List<ProcessingCaseVo> selectProcessingClaimCaseList(ClaimCaseDTO claimCaseDTO) {
        if (StringUtils.isEmpty(claimCaseDTO.getBatchNo())&&StringUtils.isEmpty(claimCaseDTO.getName())&&StringUtils.isEmpty(claimCaseDTO.getRptNo())){
           Calendar calendar = Calendar.getInstance();
           calendar.set(Calendar.MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 30);
           claimCaseDTO.setUpdateTime(DateUtils.parseDate(calendar.getTime()));
       }
        claimCaseDTO.setCaseStatus("05");
        claimCaseDTO.setStatus("Y");
        claimCaseDTO.setUpdateBy(SecurityUtils.getUsername());
        /*          实现多少天多少小时多少分的计算实现
        ArrayList<ProcessingCaseVo> processingCaseVos = new ArrayList<>();
        for (ClaimCase claimCase : claimCases) {
            ProcessingCaseVo processingCaseVo = new ProcessingCaseVo();
            BeanUtils.copyProperties(claimCase,processingCaseVo);
            long times = now.getTime()-claimCase.getUpdateTime().getTime();
            long day = times / (24 * 60 * 60 * 1000);
            long hour = (times / (60 * 60 * 1000) - day * 24);
            long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
            StringBuilder sbTime = new StringBuilder();
            if (day>0){
                sbTime.append(day).append("天");
            }
            if (hour > 0) {
                sbTime.append(hour).append("小时");
            }
            if (min > 0) {
                sbTime.append(min).append("分钟");
            }
            processingCaseVo.setStayTime(sbTime.toString());
            processingCaseVos.add(processingCaseVo);
        }*/
        return claimCaseMapper.selectProcessingClaimCaseList(claimCaseDTO);
    }

    /**
     * 查询已处理受理案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    @Override
    public List<ClaimCase> selectProcessedClaimCaseList(ClaimCaseDTO claimCaseDTO) {
        claimCaseDTO.setOperation("05");
        claimCaseDTO.setIsHistory("Y");
        claimCaseDTO.setCaseStatus("'05','30'");
        claimCaseDTO.setStatus("Y");
        claimCaseDTO.setUpdateBy(SecurityUtils.getUsername());
        return claimCaseMapper.selectClaimCaseProcessedList(claimCaseDTO);
    }

    /**
     * 查询悬挂中受理案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    @Override
    public List<ProcessingCaseVo> selectSuspensionClaimCaseList(ClaimCaseDTO claimCaseDTO) {
        claimCaseDTO.setCaseStatus("30");
        claimCaseDTO.setStatus("Y");
        claimCaseDTO.setUpdateBy(SecurityUtils.getUsername());
        return claimCaseMapper.selectProcessingClaimCaseList(claimCaseDTO);
    }

    /**
     * 新增案件信息 
     * 
     * @param claimCase 案件信息 
     * @return 结果
     */
    @Override
    public int insertClaimCase(ClaimCase claimCase)
    {
        claimCase.setCreateTime(DateUtils.getNowDate());
        return claimCaseMapper.insertClaimCase(claimCase);
    }

    /**
     * 修改案件信息 
     * 
     * @param claimCase 案件信息 
     * @return 结果
     */
    @Override
    public int updateClaimCase(ClaimCase claimCase)
    {
        claimCase.setUpdateTime(DateUtils.getNowDate());
        return claimCaseMapper.updateClaimCase(claimCase);
    }

    /**
     * 批量删除案件信息 
     * 
     * @param rptNos 需要删除的案件信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseByIds(String[] rptNos)
    {
        return claimCaseMapper.deleteClaimCaseByIds(rptNos);
    }

    /**
     * 删除案件信息 信息
     * 
     * @param rptNo 案件信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseById(String rptNo)
    {
        return claimCaseMapper.deleteClaimCaseById(rptNo);
    }

    /**
     *  获取案件信息 详细信息（多表关联查询batch和Claim表）
     * @param rptNo 报案号
     * @return 结果
     */
    public ClaimAndBatchVo selectClaimClaimAndBatchById(String rptNo){
        return claimCaseMapper.selectClaimClaimAndBatchById(rptNo);
    }

    /**
     * 问题件按钮（子任务添加功能未完成）
     * 修改案件信息
     * @param claimCase
     * @return
     */
    public int updateCaseInfoSuspend(ClaimCase claimCase)
    {
        claimCase.setCaseStatus("30");//问题件
        claimCase.setStatus("Y");
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.getNowDate());

        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCase.getRptNo());//报案号
        claimCaseRecord.setHistoryFlag("N");//是否历史节点
        claimCaseRecord.setOperation("30");//流程节点
        claimCaseRecord.setOperator(SecurityUtils.getUsername());//流程节点操作人
        claimCaseRecord.setStatus("Y");//数据状态
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecordSecond(claimCaseRecord);

        return claimCaseMapper.updateClaimCase(claimCase);
    }

    /**
     * 受理确认按钮功能
     *修改案件信息
     * @param claimCase
     * @return
     */
    public int updateCaseAndRecordInfoSuspend(ClaimCase claimCase){

        //判断是否为审核岗退回受理
        // 通过查询报案号为本报案号,数据状态为"Y",是否为历史节点："N",流程节点为："07"审核的上一流程节点ID；
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(claimCase.getRptNo());
        claimCaseRecord.setStatus("Y");
        claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setOperation("07");
        List<ClaimCaseRecord> claimCaseRecords = claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
        ClaimCaseRecord claimCaseRecord1 = new ClaimCaseRecord();
        if (null == claimCaseRecords || claimCaseRecords.size() ==0 ){
            //为空的情况
            //第一次处理-案件状态05->06
            claimCase.setCaseStatus("06");//案件信息-录入
            claimCaseRecord1.setOperation("06");//案件操作记录-录入
        }else{
            //不为空的情况
            //第二次处理-案件状态05->07
            claimCase.setCaseStatus("07");//案件信息-审核
            claimCaseRecord1.setOperation("07");//案件操作记录-审核
        }
        claimCase.setStatus("Y");
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.getNowDate());
        claimCaseMapper.updateClaimCase(claimCase);//完成案件信息改变

        //修改受理信息的材料齐全日期：
        ClaimCaseAccept claimCaseAccept = new ClaimCaseAccept();
        claimCaseAccept.setRptNo(claimCase.getRptNo());
        claimCaseAccept.setMaterialCompleteDate(DateUtils.getNowDate());
        claimCaseAcceptMapper.updateClaimCaseAccept(claimCaseAccept);

        //完成案件操作记录表的记录
        claimCaseRecord1.setRptNo(claimCase.getRptNo());
        claimCaseRecord1.setOperator(SecurityUtils.getUsername());
        claimCaseRecord1.setHistoryFlag("N");
        claimCaseRecord1.setStatus("Y");
        claimCaseRecord1.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord1.setCreateTime(DateUtils.getNowDate());

        return claimCaseRecordMapper.insertClaimCaseRecordSecond(claimCaseRecord1);
    }

    /**
     * 撤件弹框确认按钮
     *  修改案件信息
     * @param claimCase
     * @return
     */
    public int updateCaseAndRecordInfoCancel(ClaimCase claimCase){
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();

        String pulloutType = claimCase.getPulloutType();
        if (pulloutType=="01"){//撤件97
            claimCase.setCaseStatus("97");
            claimCaseRecord.setOperation("97");
        }else if (pulloutType=="02"){//撤件可申诉98
            claimCase.setCaseStatus("98");
            claimCaseRecord.setOperation("98");
        }
        claimCase.setStatus("Y");
        claimCase.setUpdateBy(SecurityUtils.getUsername());
        claimCase.setUpdateTime(DateUtils.getNowDate());

        //生成案件操作记录表
        claimCaseRecord.setRptNo(claimCase.getRptNo());
        claimCaseRecord.setOperator(SecurityUtils.getUsername());
        claimCaseRecord.setHistoryFlag("N");
        claimCaseRecord.setStatus("Y");
        claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
        claimCaseRecord.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecordSecond(claimCaseRecord);

        return claimCaseMapper.updateClaimCase(claimCase);
    }


    /**
     * 查询案件调度工作池
     * @param
     * @return
     */
   /* @Override
    public List<ClaimCase> selectCaseDispatchList(ClaimCase claimCase) {
        DispatchDTO dispatchDTO=new DispatchDTO();
        if ( StringUtils.isNull(dispatchDTO.getSubmitenddate())) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            dispatchDTO.setSubmitstartdate(calendar.getTime());
            dispatchDTO.setSubmitenddate(DateUtils.parseDate(DateUtils.getTime()));
            return claimCaseMapper.selectCaseDispatchList(claimCase);
        }
        return claimCaseMapper.selectCaseDispatchList(claimCase);
    }*/
    @Override
    public List<DispatchVo> selectCaseDispatchList(DispatchDTO dispatchDTO) {
        /*List<DispatchVo> l = new ArrayList<>();
        List<DispatchVo> dispatchVoList=claimCaseMapper.selectCaseDispatchList(dispatchDTO);
        DispatchVo dispatchVo=new DispatchVo();
        for (DispatchVo d:dispatchVoList
             ) {
            dispatchVo.setRptNo(d.getRptNo());
            dispatchVo.setClaimType(d.getClaimType());
            dispatchVo.setSubmitdate(d.getSubmitdate());
            dispatchVo.setCaseStatus(d.getCaseStatus());
            dispatchVo.setSource(d.getSource());
            List<DispatchPolicyVo> dispatchPolicyVos=claimCaseMapper.selectPolicyDispatchList(dispatchDTO);
            dispatchVo.setDispatchVos(dispatchPolicyVos);
            l.add(dispatchVo);
        }*/
        /*claimCaseMapper.selectCaseDispatchList(dispatchDTO);
        claimCaseMapper.selectPolicyDispatchList(dispatchDTO);*/
        if ( StringUtils.isNull(dispatchDTO.getSubmitenddate())) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            dispatchDTO.setSubmitstartdate(calendar.getTime());
            dispatchDTO.setSubmitenddate(DateUtils.parseDate(DateUtils.getTime()));
            return claimCaseMapper.selectCaseDispatchList(dispatchDTO);
        }
        return claimCaseMapper.selectCaseDispatchList(dispatchDTO);
    }

    @Override
    public void updateClaimCase(CaseDispatchDTO caseDispatchDTO) {
        List<String>  l=caseDispatchDTO.getRptNo();
        ClaimCase claimCase=new ClaimCase();
        for (String rptno:l) {
            claimCase.setRptNo(rptno);
            claimCase.setUpdateBy(caseDispatchDTO.getUpdateBy());
            claimCaseMapper.updateClaimCase(claimCase);
        }
    }

    //条件理算审核
    public List<ClaimCase> SelectConditionsForTheAdjustmentUnder(ClaimCase claimCase){
        return null;
    }//待处理
    public List<ClaimCase> SelectConditionsForTheAdjustmentOver(ClaimCase claimCase){
        return null;
    }//已处理
    public List<ClaimCase> SelectConditionsForTheAdjustmentHang(ClaimCase claimCase){
        return null;
    }//悬挂

}