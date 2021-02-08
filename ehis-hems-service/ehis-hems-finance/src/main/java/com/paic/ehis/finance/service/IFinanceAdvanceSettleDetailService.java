package com.paic.ehis.finance.service;

import com.paic.ehis.finance.domain.FinanceAdvanceSettleDetail;
import com.paic.ehis.finance.domain.dto.FinanceAdvanceSettleDTO;
import com.paic.ehis.finance.domain.vo.FinanceAdvanceSettleVO;

import java.util.List;

/**
 * 代垫费结算明细Service接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface IFinanceAdvanceSettleDetailService 
{
    /**
     * 查询代垫费结算明细
     * 
     * @param detailId 代垫费结算明细ID
     * @return 代垫费结算明细
     */
    public FinanceAdvanceSettleDetail selectFinanceAdvanceSettleDetailById(Long detailId);

    /**
     * 查询代垫费结算明细列表
     * 
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 代垫费结算明细集合
     */
    public List<FinanceAdvanceSettleDetail> selectFinanceAdvanceSettleDetailList(FinanceAdvanceSettleDetail financeAdvanceSettleDetail);

    /**
     * 新增代垫费结算明细
     * 
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 结果
     */
    public int insertFinanceAdvanceSettleDetail(FinanceAdvanceSettleDetail financeAdvanceSettleDetail);

    /**
     * 修改代垫费结算明细
     * 
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 结果
     */
    public int updateFinanceAdvanceSettleDetail(FinanceAdvanceSettleDetail financeAdvanceSettleDetail);

    /**
     * 批量删除代垫费结算明细
     * 
     * @param detailIds 需要删除的代垫费结算明细ID
     * @return 结果
     */
    public int deleteFinanceAdvanceSettleDetailByIds(Long[] detailIds);

    /**
     * 删除代垫费结算明细信息
     * 
     * @param detailId 代垫费结算明细ID
     * @return 结果
     */
    public int deleteFinanceAdvanceSettleDetailById(Long detailId);


    /**查询交接任务列表信息*/
    public List<FinanceAdvanceSettleVO> selectFinanceAdvanceSettleVOList(FinanceAdvanceSettleDTO financeAdvanceSettleDTO);

    /** 根据结算任务号查询代垫费案件结算明细*/
    public  List<FinanceAdvanceSettleVO> selectFinanceAdvanceSettleVOInfo(String settleTaskNo);

    /**删除按钮修改状态为无效*/
    public int deletefinanceinfo(String settleTaskNo);

    /**任务确认环节确认按钮，将结算状态由待确认改为待核销(待结算)*/
    public int updateSettleStatus1(String settleTaskNo);

    /**核销按钮将结算状态由待核销改为已结算*/
    public int updateSettleStatus2(String[] settleTaskNos);

   /**发起垫付款任务*/
    public List<FinanceAdvanceSettleVO> InitiateAdvancePaymentTask(FinanceAdvanceSettleDTO financeAdvanceSettleDTO);

}
