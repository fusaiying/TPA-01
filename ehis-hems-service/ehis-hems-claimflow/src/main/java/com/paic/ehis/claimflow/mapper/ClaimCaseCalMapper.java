package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.ClaimCaseCal;
import com.paic.ehis.claimflow.domain.vo.CalBillSummaryVo;
import com.paic.ehis.claimflow.domain.vo.CalConclusionVo;

import java.util.List;

/**
 * 案件赔付信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimCaseCalMapper 
{
    /**
     * 查询案件赔付信息
     * 
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    public ClaimCaseCal selectClaimCaseCalByRptNo(String rptNo);

    /**
     * 理算审核  查询案件赔付信息及理算总值
     *
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    public CalConclusionVo selectClaimCaseCalInformation(String rptNo);

    /**
     * 根据报案号查询账单明细表
     *
     * @param rptNo
     * @return
     */
    public CalBillSummaryVo selectClaimCaseBillSummaryByRptNo(String rptNo);

    /**
     * 查询案件赔付信息列表
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 案件赔付信息集合
     */
    public List<ClaimCaseCal> selectClaimCaseCalList(ClaimCaseCal claimCaseCal);

    /**
     * 新增案件赔付信息
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 结果
     */
    public int insertClaimCaseCal(ClaimCaseCal claimCaseCal);

    /**
     * 修改案件赔付信息
     * 
     * @param claimCaseCal 案件赔付信息
     * @return 结果
     */
    public int updateClaimCaseCal(ClaimCaseCal claimCaseCal);

    /**
     * 删除案件赔付信息
     * 
     * @param calId 案件赔付信息ID
     * @return 结果
     */
    public int deleteClaimCaseCalById(Long calId);

    /**
     * 批量删除案件赔付信息
     * 
     * @param calIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseCalByIds(Long[] calIds);
    public  List<ClaimCaseCal> selectClaimCaseCalByIdOne(String rptNo);

    /**
     * 通过批次号查询理算信息
     *
     * @param batchNo
     * @return
     */
    public List<ClaimCaseCal> selectClaimCaseCalByBatchNo(String batchNo);
}
