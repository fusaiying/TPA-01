package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.ClaimCaseDiscussion;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseDiscussionDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseDiscussionVO;

import java.util.List;

/**
 * 案件协谈信息Service接口
 *
 * @author sino
 * @date 2021-01-15
 */
public interface IClaimCaseDiscussionService
{

    /**
     * 查询案件协谈信息
     *
     * @param discId 案件协谈信息ID
     * @return 案件协谈信息
     */
    public ClaimCaseDiscussion selectClaimCaseDiscussionById(Long discId);

    /**
     * 查询案件协谈信息
     *
     * @param rptNo 案件协谈信息ID
     * @return 案件协谈信息
     */
    public List<ClaimCaseDiscussion> selectDiscussionListByRptNo(String rptNo);

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
     * 批量删除案件协谈信息
     *
     * @param discIds 需要删除的案件协谈信息ID
     * @return 结果
     */
    public int deleteClaimCaseDiscussionByIds(Long[] discIds);

    /**
     * 删除案件协谈信息信息
     *
     * @param discId 案件协谈信息ID
     * @return 结果
     */
    public int deleteClaimCaseDiscussionById(Long discId);

    public int updatecasediscussionStatus(String repNo);

    List<ClaimCaseDiscussionVO> selectCaseDisListUnder(ClaimCaseDiscussionDTO claimCaseDiscussionDTO);
    List<ClaimCaseDiscussionVO> selectCaseDisListOver(ClaimCaseDiscussionDTO claimCaseDiscussionDTO);
/*查询基本信息表*/
    public ClaimCaseDiscussionVO  selectCaseBaseInfo(String rptNo);
}
