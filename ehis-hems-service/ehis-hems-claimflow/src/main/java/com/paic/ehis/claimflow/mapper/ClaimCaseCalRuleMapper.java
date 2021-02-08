package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.ClaimCaseCalRule;
import com.paic.ehis.claimflow.domain.vo.TheAdjustmentSubsidiaryOneVO;

import java.util.List;

/**
 * 案件赔付规则记录Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimCaseCalRuleMapper 
{
    /**
     * 查询案件赔付规则记录
     * 
     * @param calRuleId 案件赔付规则记录ID
     * @return 案件赔付规则记录
     */
    public ClaimCaseCalRule selectClaimCaseCalRuleById(Long calRuleId);

    /**
     * 查询案件赔付规则记录列表
     * 
     * @param claimCaseCalRule 案件赔付规则记录
     * @return 案件赔付规则记录集合
     */
    public List<ClaimCaseCalRule> selectClaimCaseCalRuleList(ClaimCaseCalRule claimCaseCalRule);

    /**
     * 新增案件赔付规则记录
     * 
     * @param claimCaseCalRule 案件赔付规则记录
     * @return 结果
     */
    public int insertClaimCaseCalRule(ClaimCaseCalRule claimCaseCalRule);

    /**
     * 修改案件赔付规则记录
     * 
     * @param claimCaseCalRule 案件赔付规则记录
     * @return 结果
     */
    public int updateClaimCaseCalRule(ClaimCaseCalRule claimCaseCalRule);

    /**
     * 删除案件赔付规则记录
     * 
     * @param calRuleId 案件赔付规则记录ID
     * @return 结果
     */
    public int deleteClaimCaseCalRuleById(Long calRuleId);

    /**
     * 批量删除案件赔付规则记录
     * 
     * @param calRuleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseCalRuleByIds(Long[] calRuleIds);


    public List<TheAdjustmentSubsidiaryOneVO> selectClaimCaseCalRuleListByRptNo(String rptNo);

}
