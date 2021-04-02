package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.FinanceAdvanceSettleDetail;
import com.paic.ehis.finance.domain.FinanceTpaSettleDetail;
import com.paic.ehis.finance.domain.dto.FinanceAdvanceSettleDTO;
import com.paic.ehis.finance.domain.vo.FinanceAdvanceSettleVO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 代垫费结算明细Mapper接口
 *
 * @author sino
 * @date 2021-01-30
 */
@Component
public interface FinanceAdvanceSettleDetailMapper {
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
     * 删除代垫费结算明细
     *
     * @param detailId 代垫费结算明细ID
     * @return 结果
     */
    public int deleteFinanceAdvanceSettleDetailById(Long detailId);

    /**
     * 根据settleTaskNo批量删除
     */
    public int deleteFinanceSettleDetailsettleTaskNos(String[] settleTaskNos);

    /**
     * 批量删除代垫费结算明细
     *
     * @param detailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceAdvanceSettleDetailByIds(Long[] detailIds);

    /**
     * 查询交接任务列表信息
     */
    public List<FinanceAdvanceSettleVO> selectFinanceAdvanceSettleVOList(FinanceAdvanceSettleDTO financeAdvanceSettleDTO);

    /**
     * 根据结算任务号查询代垫费案件结算明细
     */
    public List<FinanceAdvanceSettleVO> selectFinanceAdvanceSettleVOInfo(String settleTaskNo);

    /**
     * 计算折后金额结算金额默认为折后金额
     */
    public String selectDiscountAmount(String rptNo);

    /**
     * 计算任务总金额
     */
    public String selectTotalAmount(String batchNo);

    /**
    *  根据出单公司编码查询最新得结算止期
    */
    public Date selectLastendDate(String companyName);

    /**
     * 计算账单总金额
     */
    public String selectBillAmount(String rptNo);

    /**
     * 删除按钮修改状态为无效
     */
    public int deletefinanceinfo(String settleTaskNo);

    /**
     * 任务确认环节确认按钮，将结算状态由待确认改为待核销(待结算)
     */
    public int updateSettleStatus1(String settleTaskNo);

    /**
     * 核销按钮将结算状态由待核销改为已结算
     */
    public int updateSettleStatus2(String settleTaskNo);


}
