package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.FinancePayInfo;
import com.paic.ehis.finance.domain.dto.TransferfailedDTO;
import com.paic.ehis.finance.domain.vo.TransferfailedVo;

import java.util.List;

/**
 * 支付信息Mapper接口
 * 
 * @author sino
 * @date 2021-02-01
 */
public interface FinancePayInfoMapper 
{
    /**
     * 查询支付信息
     * 
     * @param payId 支付信息ID
     * @return 支付信息
     */
    public FinancePayInfo selectFinancePayInfoById(Long payId);

    /**
     * 查询支付信息
     *
     * @param batchNo 批次号
     * @return 支付信息
     */
    public FinancePayInfo selectFinancePayInfoByBatchNo(String batchNo);

    /**
     * 查询支付信息列表
     * 
     * @param financePayInfo 支付信息
     * @return 支付信息集合
     */
    public List<FinancePayInfo> selectFinancePayInfoList(FinancePayInfo financePayInfo);

    /**
     * 新增支付信息
     * 
     * @param financePayInfo 支付信息
     * @return 结果
     */
    public int insertFinancePayInfo(FinancePayInfo financePayInfo);

    /**
     * 修改支付信息
     * 
     * @param financePayInfo 支付信息
     * @return 结果
     */
    public int updateFinancePayInfo(FinancePayInfo financePayInfo);

    /**
     * 删除支付信息
     * 
     * @param payId 支付信息ID
     * @return 结果
     */
    public int deleteFinancePayInfoById(Long payId);

    /**
     * 批量删除支付信息
     * 
     * @param payIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinancePayInfoByIds(Long[] payIds);

    /**
     *查询转账失败处理工作池
     */
    public List<TransferfailedVo> selectTransferfailedList(TransferfailedDTO transferfailedDTO);

}
