package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.ClaimCaseApplyType;

import java.util.List;

/**
 * 案件申请类别Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseApplyTypeMapper 
{
    /**
     * 查询案件申请类别
     * 
     * @param applyId 案件申请类别ID
     * @return 案件申请类别
     */
    public ClaimCaseApplyType selectClaimCaseApplyTypeById(Long applyId);

    /**
     * 查询案件申请类别列表
     * 
     * @param claimCaseApplyType 案件申请类别
     * @return 案件申请类别集合
     */
    public List<ClaimCaseApplyType> selectClaimCaseApplyTypeList(ClaimCaseApplyType claimCaseApplyType);

    /**
     * 新增案件申请类别
     * 
     * @param claimCaseApplyType 案件申请类别
     * @return 结果
     */
    public int insertClaimCaseApplyType(ClaimCaseApplyType claimCaseApplyType);

    /**
     * 修改案件申请类别
     * 
     * @param claimCaseApplyType 案件申请类别
     * @return 结果
     */
    public int updateClaimCaseApplyType(ClaimCaseApplyType claimCaseApplyType);

    /**
     * 删除案件申请类别
     * 
     * @param applyId 案件申请类别ID
     * @return 结果
     */
    public int deleteClaimCaseApplyTypeById(Long applyId);

    /**
     * 批量删除案件申请类别
     * 
     * @param applyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseApplyTypeByIds(Long[] applyIds);

    public List<ClaimCaseApplyType> selectClaimCaseApplyTypeByRptNo(String rptNo);
}
