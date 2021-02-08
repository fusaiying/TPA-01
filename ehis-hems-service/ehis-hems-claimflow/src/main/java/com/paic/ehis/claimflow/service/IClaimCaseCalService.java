package com.paic.ehis.claimflow.service;


import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCaseCal;
import com.paic.ehis.claimflow.domain.vo.CalBillSummaryVo;
import com.paic.ehis.claimflow.domain.vo.CalConclusionVo;

import java.util.List;

/**
 * 案件赔付信息Service接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface IClaimCaseCalService 
{
    /**
     * 查询案件赔付信息
     * 
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    public ClaimCaseCal selectClaimCaseCalByRptNo(String rptNo);

    /**
     * 理算审核  查询案件赔付结论信息及理算总值
     *
     * @param rptNo 案件赔付信息ID
     * @return 案件赔付信息
     */
    public CalConclusionVo selectClaimCaseCalInformation(String rptNo);

    /**
     * 理算审核默认查询案件汇总信息 列表
     *
     * @param claimCaseBill 案件赔付账单明细
     * @return 案件赔付账单明细集合
     */
    public CalBillSummaryVo selectCaseCalBillSummary(ClaimCaseBill claimCaseBill);

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
    public CalConclusionVo updateClaimCaseCal(ClaimCaseCal claimCaseCal);

    /**
     * 批量删除案件赔付信息
     * 
     * @param calIds 需要删除的案件赔付信息ID
     * @return 结果
     */
    public int deleteClaimCaseCalByIds(Long[] calIds);

    /**
     * 删除案件赔付信息信息
     * 
     * @param calId 案件赔付信息ID
     * @return 结果
     */
    public int deleteClaimCaseCalById(Long calId);
}
