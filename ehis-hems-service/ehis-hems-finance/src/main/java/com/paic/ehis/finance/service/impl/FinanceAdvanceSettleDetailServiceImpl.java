package com.paic.ehis.finance.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.finance.mapper.*;
import com.paic.ehis.finance.service.IFinanceAdvanceSettleDetailService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.finance.domain.*;
import com.paic.ehis.finance.domain.dto.FinanceAdvanceSettleDTO;
import com.paic.ehis.finance.domain.vo.FinanceAdvanceSettleVO;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.domain.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 代垫费结算明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-30
 */
@Service
public class FinanceAdvanceSettleDetailServiceImpl implements IFinanceAdvanceSettleDetailService
{
    @Autowired
    private FinanceAdvanceSettleDetailMapper financeAdvanceSettleDetailMapper;

    @Autowired
    FinanceAdvanceSettleTaskMapper financeAdvanceSettleTaskMapper;

    @Autowired
    FinanceSettleRecordMapper financeSettleRecordMapper;

    @Autowired
    private RemoteUserService userService;

    /**
     * 查询代垫费结算明细
     * 
     * @param detailId 代垫费结算明细ID
     * @return 代垫费结算明细
     */
    @Override
    public FinanceAdvanceSettleDetail selectFinanceAdvanceSettleDetailById(Long detailId)
    {
        return financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleDetailById(detailId);
    }

    /**
     * 查询代垫费结算明细列表
     * 
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 代垫费结算明细
     */
    @Override
    public List<FinanceAdvanceSettleDetail> selectFinanceAdvanceSettleDetailList(FinanceAdvanceSettleDetail financeAdvanceSettleDetail)
    {
        return financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleDetailList(financeAdvanceSettleDetail);
    }

    /**
     * 新增代垫费结算明细
     * 
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 结果
     */
    @Override
    public int insertFinanceAdvanceSettleDetail(FinanceAdvanceSettleDetail financeAdvanceSettleDetail)
    {
        financeAdvanceSettleDetail.setCreateTime(DateUtils.getNowDate());
        return financeAdvanceSettleDetailMapper.insertFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);
    }

    /**
     * 修改代垫费结算明细
     * 
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 结果
     */
    @Override
    public int updateFinanceAdvanceSettleDetail(FinanceAdvanceSettleDetail financeAdvanceSettleDetail)
    {
        financeAdvanceSettleDetail.setUpdateTime(DateUtils.getNowDate());
        return financeAdvanceSettleDetailMapper.updateFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);
    }

    /**
     * 批量删除代垫费结算明细
     * 
     * @param detailIds 需要删除的代垫费结算明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceAdvanceSettleDetailByIds(Long[] detailIds)
    {
        return financeAdvanceSettleDetailMapper.deleteFinanceAdvanceSettleDetailByIds(detailIds);
    }

    /**
     * 删除代垫费结算明细信息
     * 
     * @param detailId 代垫费结算明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceAdvanceSettleDetailById(Long detailId)
    {
        return financeAdvanceSettleDetailMapper.deleteFinanceAdvanceSettleDetailById(detailId);
    }


    /** 查询代垫费结算明细列表*/
    @Override
    public List<FinanceAdvanceSettleVO> selectFinanceAdvanceSettleVOList(FinanceAdvanceSettleDTO financeAdvanceSettleDTO)
    {
        return financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleVOList(financeAdvanceSettleDTO);
    }

    /** 根据结算任务号查询代垫费案件结算明细*/
    @Override
    public  List<FinanceAdvanceSettleVO> selectFinanceAdvanceSettleVOInfo(String settleTaskNo) {
        List<FinanceAdvanceSettleVO> financeAdvanceSettleVO=financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleVOInfo(settleTaskNo);
        List<FinanceAdvanceSettleVO> financeAdvanceSettleVOS=new ArrayList<>();
        for(FinanceAdvanceSettleVO financeAdvanceSettleVO1:financeAdvanceSettleVO){
        /*获取折后金额，根据报案号获取折后金额*/
            String discountamount=financeAdvanceSettleDetailMapper.selectDiscountAmount(financeAdvanceSettleVO1.getRptNo());
            /*获取账单总金额*/
            String billAmount=financeAdvanceSettleDetailMapper.selectBillAmount(financeAdvanceSettleVO1.getRptNo());
            FinanceAdvanceSettleVO financeAdvanceSettleVO2 = new FinanceAdvanceSettleVO();
            BeanUtils.copyProperties(financeAdvanceSettleVO1,financeAdvanceSettleVO2);
            financeAdvanceSettleVO2.setBillAmount(new BigDecimal(billAmount));   //赋值给账单总金额
            financeAdvanceSettleVO2.setDiscountedAmount(new BigDecimal(discountamount)); //给折后金额赋值
            financeAdvanceSettleVO2.setAdvanceAmount(new BigDecimal(discountamount));  //结算金额默认为折后金额
            financeAdvanceSettleVOS.add(financeAdvanceSettleVO2);
        }
        return financeAdvanceSettleVOS;
    }

    /**删除按钮修改状态为无效*/
    @Override
    public int deletefinanceinfo(String settleTaskNo)
    {
        return financeAdvanceSettleDetailMapper.deletefinanceinfo(settleTaskNo);
    }

    /**任务确认环节确认按钮，将结算状态由待确认改为待核销(待结算)*/
    @Override
    public int updateSettleStatus1(String settleTaskNo)
    {
        return financeAdvanceSettleDetailMapper.updateSettleStatus1(settleTaskNo);
    }

    /** 核销按钮将结算状态由待核销改为已结算*/
    @Override
    public int updateSettleStatus2(String settleTaskNo)
    {
        return financeAdvanceSettleDetailMapper.updateSettleStatus2(settleTaskNo);
    }


    /**发起垫付款任务*/
    @Override
    public List<FinanceAdvanceSettleVO> InitiateAdvancePaymentTask(FinanceAdvanceSettleDTO financeAdvanceSettleDTO) {
        ArrayList<FinanceAdvanceSettleVO> financeAdvanceSettleVOS=new ArrayList<>();
        FinanceAdvanceSettleVO financeAdvanceSettleVO = new FinanceAdvanceSettleVO();
        //垫付款服务费及明细的新增
        FinanceAdvanceSettleTask financeAdvanceSettleTask = new FinanceAdvanceSettleTask();
        FinanceAdvanceSettleDetail financeAdvanceSettleDetail = new FinanceAdvanceSettleDetail();
        FinanceSettleRecord financeSettleRecord = new FinanceSettleRecord();
        ObjectMapper objectMapper = new ObjectMapper();
        SysUser info = objectMapper.convertValue(userService.userInfo().get("data"),SysUser.class);
        // 获取当前用户所属机构
        String organCode = "";
        if (null != info) {
            organCode = info.getOrganCode();
        }
        financeAdvanceSettleTask.setSettleStatus("01");
        financeAdvanceSettleTask.setSettleEndDate(financeAdvanceSettleDTO.getSettleEndDate());
        financeAdvanceSettleTask.setCompanyCode(financeAdvanceSettleDTO.getCompanyCode());
        financeAdvanceSettleTask.setStatus("Y");
        financeAdvanceSettleTask.setDeptCode(organCode);
        financeAdvanceSettleTask.setCreateBy(info.getUserName());
        financeAdvanceSettleTask.setCreateTime(DateUtils.getNowDate());
        financeAdvanceSettleTask.setUpdateBy(info.getUserName());
        financeAdvanceSettleTask.setUpdateTime(DateUtils.getNowDate());

        financeAdvanceSettleDetail.setStatus("Y");
        financeAdvanceSettleDetail.setDeptCode(organCode);
        financeAdvanceSettleDetail.setCreateBy(info.getUserName());
        financeAdvanceSettleDetail.setCreateTime(DateUtils.getNowDate());
        financeAdvanceSettleDetail.setUpdateBy(info.getUserName());
        financeAdvanceSettleDetail.setUpdateTime(DateUtils.getNowDate());

        financeSettleRecord.setTaskType("02");
        financeSettleRecord.setOperator(info.getUserName());
        financeSettleRecord.setHistoryFlag("N");
        financeSettleRecord.setOperation("01");
        financeSettleRecord.setStatus("Y");
        financeSettleRecord.setDeptCode(organCode);
        financeSettleRecord.setCreateBy(info.getUserName());
        financeSettleRecord.setCreateTime(DateUtils.getNowDate());
        financeSettleRecord.setUpdateBy(info.getUserName());
        financeSettleRecord.setUpdateTime(DateUtils.getNowDate());

        String taskNo = "AS" + PubFun.createMySqlMaxNoUseCache("finance_advance_settle_detail", 10, 10);
        if (StringUtils.isNotNull(financeAdvanceSettleDTO.getSettleTaskNo())) {
            List<FinanceAdvanceSettleDetail> financeAdvanceSettleDetails = financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleDetailList(financeAdvanceSettleDetail);
            //设值 结算表的结算起期
            if (StringUtils.isNotEmpty(financeAdvanceSettleDetails)){
                FinanceAdvanceSettleTask advanceSettleTask = new FinanceAdvanceSettleTask();
                advanceSettleTask.setSettleTaskNo(financeAdvanceSettleDetails.get(0).getSettleTaskNo());
                List<FinanceAdvanceSettleTask> financeAdvanceSettleTasks = financeAdvanceSettleTaskMapper.selectFinanceAdvanceSettleTaskList(financeAdvanceSettleTask);
                financeAdvanceSettleTask.setSettleStartDate(financeAdvanceSettleTasks.get(0).getSettleEndDate());
            }
            financeAdvanceSettleTask.setSettleTaskNo(taskNo);

            financeAdvanceSettleTaskMapper.insertFinanceAdvanceSettleTask(financeAdvanceSettleTask);
            financeSettleRecord.setSettleTaskNo(taskNo);
            financeSettleRecordMapper.insertFinanceSettleRecord(financeSettleRecord);
        }else{
            List<FinanceAdvanceSettleDetail> financeAdvanceSettleDetails = financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleDetailList(financeAdvanceSettleDetail);
            //设值 结算表的结算起期
            if (StringUtils.isNotEmpty(financeAdvanceSettleDetails)){
                FinanceAdvanceSettleTask advanceSettleTask = new FinanceAdvanceSettleTask();
                advanceSettleTask.setSettleTaskNo(financeAdvanceSettleDetails.get(0).getSettleTaskNo());
                List<FinanceAdvanceSettleTask> financeAdvanceSettleTasks = financeAdvanceSettleTaskMapper.selectFinanceAdvanceSettleTaskList(financeAdvanceSettleTask);
                financeAdvanceSettleTask.setSettleStartDate(financeAdvanceSettleTasks.get(0).getSettleEndDate());
            }
            financeAdvanceSettleTask.setSettleTaskNo(taskNo);
            financeAdvanceSettleTaskMapper.insertFinanceAdvanceSettleTask(financeAdvanceSettleTask);
            financeSettleRecord.setSettleTaskNo(taskNo);
            financeSettleRecordMapper.insertFinanceSettleRecord(financeSettleRecord);
            financeAdvanceSettleVOS.add(financeAdvanceSettleVO);
        } return financeAdvanceSettleVOS;
    }

}
