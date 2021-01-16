package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimCaseDiscussion;

/**
 * 案件协谈信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface ClaimCaseDiscussionMapper 
{
    /**
     * 查询案件协谈信息
     * 
     * @param discId 案件协谈信息ID
     * @return 案件协谈信息
     */
    public ClaimCaseDiscussion selectClaimCaseDiscussionById(Long discId);

    /**
     * 查询案件协谈信息列表
     * 
     * @param claimCaseDiscussion 案件协谈信息
     * @return 案件协谈信息集合
     */
    public List<ClaimCaseDiscussion> selectClaimCaseDiscussionList(ClaimCaseDiscussion claimCaseDiscussion);

    /**
     * 新增案件协谈信息
     * 
     * @param claimCaseDiscussion 案件协谈信息
     * @return 结果
     */
    public int insertClaimCaseDiscussion(ClaimCaseDiscussion claimCaseDiscussion);

    /**
     * 修改案件协谈信息
     * 
     * @param claimCaseDiscussion 案件协谈信息
     * @return 结果
     */
    public int updateClaimCaseDiscussion(ClaimCaseDiscussion claimCaseDiscussion);

    /**
     * 删除案件协谈信息
     * 
     * @param discId 案件协谈信息ID
     * @return 结果
     */
    public int deleteClaimCaseDiscussionById(Long discId);

    /**
     * 批量删除案件协谈信息
     * 
     * @param discIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseDiscussionByIds(Long[] discIds);
}
