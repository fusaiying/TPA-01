package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCaseDiscussion;
import com.paic.ehis.system.mapper.ClaimCaseDiscussionMapper;
import com.paic.ehis.system.service.IClaimCaseDiscussionService;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 案件协谈信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ClaimCaseDiscussionServiceImpl implements IClaimCaseDiscussionService
{
    @Autowired
    private ClaimCaseDiscussionMapper claimCaseDiscussionMapper;

    /**
     * 查询案件协谈信息
     * 
     * @param discId 案件协谈信息ID
     * @return 案件协谈信息
     */
    @Override
    public ClaimCaseDiscussion selectClaimCaseDiscussionById(Long discId)
    {
        return claimCaseDiscussionMapper.selectClaimCaseDiscussionById(discId);
    }

    /**
     * 查询案件协谈信息列表
     * 
     * @param claimCaseDiscussion 案件协谈信息
     * @return 案件协谈信息
     */
    @Override
    public List<ClaimCaseDiscussion> selectClaimCaseDiscussionList(ClaimCaseDiscussion claimCaseDiscussion)
    {
        return claimCaseDiscussionMapper.selectClaimCaseDiscussionList(claimCaseDiscussion);
    }

    /**
     * 新增案件协谈信息
     * 
     * @param claimCaseDiscussion 案件协谈信息
     * @return 结果
     */
    @Override
    public int insertClaimCaseDiscussion(ClaimCaseDiscussion claimCaseDiscussion)
    {
        claimCaseDiscussion.setCreateTime(DateUtils.getNowDate());
        return claimCaseDiscussionMapper.insertClaimCaseDiscussion(claimCaseDiscussion);
    }

    /**
     * 修改案件协谈信息
     * 
     * @param claimCaseDiscussion 案件协谈信息
     * @return 结果
     */
    @Override
    public int updateClaimCaseDiscussion(ClaimCaseDiscussion claimCaseDiscussion)
    {
        claimCaseDiscussion.setUpdateTime(DateUtils.getNowDate());
        return claimCaseDiscussionMapper.updateClaimCaseDiscussion(claimCaseDiscussion);
    }

    /**
     * 批量删除案件协谈信息
     * 
     * @param discIds 需要删除的案件协谈信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDiscussionByIds(Long[] discIds)
    {
        return claimCaseDiscussionMapper.deleteClaimCaseDiscussionByIds(discIds);
    }

    /**
     * 删除案件协谈信息信息
     * 
     * @param discId 案件协谈信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseDiscussionById(Long discId)
    {
        return claimCaseDiscussionMapper.deleteClaimCaseDiscussionById(discId);
    }
}
