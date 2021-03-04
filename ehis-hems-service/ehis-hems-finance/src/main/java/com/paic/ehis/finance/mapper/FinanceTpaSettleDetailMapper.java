package com.paic.ehis.finance.mapper;



import com.paic.ehis.finance.domain.FinanceTpaSettleDetail;

import java.util.List;

/**
 * TPA服务费结算明细Mapper接口
 * 
 * @author sino
 * @date 2021-01-30
 */
public interface FinanceTpaSettleDetailMapper 
{
    /**
     * 查询TPA服务费结算明细
     * 
     * @param detailId TPA服务费结算明细ID
     * @return TPA服务费结算明细
     */
    public FinanceTpaSettleDetail selectFinanceTpaSettleDetailById(Long detailId);

    /**
     * 查询TPA服务费结算明细
     *
     * @param settleTaskNo TPA服务费结算明细ID
     * @return TPA服务费结算明细
     */
    public List<FinanceTpaSettleDetail> selectFinanceTpaSettleDetailBySettleTaskNo(String settleTaskNo);

    /**
     * 查询TPA服务费结算明细列表
     * 
     * @param financeTpaSettleDetail TPA服务费结算明细
     * @return TPA服务费结算明细集合
     */
    public List<FinanceTpaSettleDetail> selectFinanceTpaSettleDetailList(FinanceTpaSettleDetail financeTpaSettleDetail);

    /**
     * 新增TPA服务费结算明细
     * 
     * @param financeTpaSettleDetail TPA服务费结算明细
     * @return 结果
     */
    public int insertFinanceTpaSettleDetail(FinanceTpaSettleDetail financeTpaSettleDetail);

    /**
     * 修改TPA服务费结算明细
     * 
     * @param financeTpaSettleDetail TPA服务费结算明细
     * @return 结果
     */
    public int updateFinanceTpaSettleDetail(FinanceTpaSettleDetail financeTpaSettleDetail);

    /**
     * 无效化 TPA服务费结算明细
     *
     * @param settleTaskNo TPA服务费结算明细
     * @return 结果
     */
    public int neutralizeFinanceTpaSettleDetail(String settleTaskNo);

    /**
     * 删除TPA服务费结算明细
     *
     * @param financeTpaSettleDetailS TPA服务费结算明细
     * @return 结果
     */
    public int removeFinanceTpaSettleDetail(List<FinanceTpaSettleDetail> financeTpaSettleDetailS);


    /**
     *  根据结算任务号  修改TPA服务费结算明细
     *
     * @param financeTpaSettleDetail TPA服务费结算明细
     * @return 结果
     */
    public int editFinanceTpaSettleDetail(FinanceTpaSettleDetail financeTpaSettleDetail);

    /**
     * 删除TPA服务费结算明细
     * 
     * @param detailId TPA服务费结算明细ID
     * @return 结果
     */
    public int deleteFinanceTpaSettleDetailById(Long detailId);

    /**
     * 批量删除TPA服务费结算明细
     * 
     * @param detailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceTpaSettleDetailByIds(Long[] detailIds);
}
