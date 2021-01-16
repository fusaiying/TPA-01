package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimCaseRemark;

/**
 * 案件备注Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseRemarkMapper 
{
    /**
     * 查询案件备注
     * 
     * @param remarkId 案件备注ID
     * @return 案件备注
     */
    public ClaimCaseRemark selectClaimCaseRemarkById(Long remarkId);

    /**
     * 查询案件备注列表
     * 
     * @param claimCaseRemark 案件备注
     * @return 案件备注集合
     */
    public List<ClaimCaseRemark> selectClaimCaseRemarkList(ClaimCaseRemark claimCaseRemark);

    /**
     * 新增案件备注
     * 
     * @param claimCaseRemark 案件备注
     * @return 结果
     */
    public int insertClaimCaseRemark(ClaimCaseRemark claimCaseRemark);

    /**
     * 修改案件备注
     * 
     * @param claimCaseRemark 案件备注
     * @return 结果
     */
    public int updateClaimCaseRemark(ClaimCaseRemark claimCaseRemark);

    /**
     * 删除案件备注
     * 
     * @param remarkId 案件备注ID
     * @return 结果
     */
    public int deleteClaimCaseRemarkById(Long remarkId);

    /**
     * 批量删除案件备注
     * 
     * @param remarkIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseRemarkByIds(Long[] remarkIds);
}
