package com.paic.ehis.finance.mapper;

import com.paic.ehis.system.api.domain.FinanceBorrowInfo;

import java.util.List;

/**
 * 案件借款明细Mapper接口
 * 
 * @author sino
 * @date 2021-02-03
 */
public interface FinanceBorrowInfoMapper 
{
    /**
     * 查询案件借款明细
     * 
     * @param borrowId 案件借款明细ID
     * @return 案件借款明细
     */
    public FinanceBorrowInfo selectFinanceBorrowInfoById(Long borrowId);

    /**
     * 查询案件借款明细列表
     * 
     * @param financeBorrowInfo 案件借款明细
     * @return 案件借款明细集合
     */
    public List<FinanceBorrowInfo> selectFinanceBorrowInfoList(FinanceBorrowInfo financeBorrowInfo);

    /**
     * 新增案件借款明细
     * 
     * @param financeBorrowInfo 案件借款明细
     * @return 结果
     */
    public int insertFinanceBorrowInfo(FinanceBorrowInfo financeBorrowInfo);

    /**
     * 修改案件借款明细
     * 
     * @param financeBorrowInfo 案件借款明细
     * @return 结果
     */
    public int updateFinanceBorrowInfo(FinanceBorrowInfo financeBorrowInfo);

    /**
     * 删除案件借款明细
     * 
     * @param borrowId 案件借款明细ID
     * @return 结果
     */
    public int deleteFinanceBorrowInfoById(Long borrowId);

    /**
     * 批量删除案件借款明细
     * 
     * @param borrowIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFinanceBorrowInfoByIds(Long[] borrowIds);

    /**
     * 根据报案号查询借款信息
     *
     * @param rptNo
     * @return
     */
    public FinanceBorrowInfo selectFinanceBorrowInfoByRptNo(String rptNo);

    /**
     * 修改案件借款明细
     * 置借款数据为无效
     *
     * @param rptNo
     * @return
     */
    public int updateFinanceBorrowInfoByRptNo(String rptNo);

    /**
     * 根据报案号更新借款表信息
     *
     * @param borrowInfo
     * @return
     */
    public int updateBorrowInfoByRptNo(FinanceBorrowInfo borrowInfo);

}
