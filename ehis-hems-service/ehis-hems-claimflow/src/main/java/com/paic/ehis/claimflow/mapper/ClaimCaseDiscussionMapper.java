package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.ClaimCaseDiscussion;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseDiscussionDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseDiscussionVO;

import java.util.List;

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
     * 查询案件历史协谈信息列表
     *
     * @param claimCaseDiscussion 案件协谈信息
     * @return 案件协谈信息集合
     */
    public List<ClaimCaseDiscussion> selectDiscussionListByRptNo(ClaimCaseDiscussion claimCaseDiscussion);

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

    List<ClaimCaseDiscussionVO> selectCaseDisListUnder(ClaimCaseDiscussionDTO claimCaseDiscussionDTO);

    List<ClaimCaseDiscussionVO> selectCaseDisListOver(ClaimCaseDiscussionDTO claimCaseDiscussionDTO);

    public ClaimCaseDiscussionVO  selectCaseBaseInfo(String rptNo);

}
