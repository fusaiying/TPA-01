package com.paic.ehis.system.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.ClaimCaseMapper;
import com.paic.ehis.system.domain.ClaimCase;
import com.paic.ehis.system.service.IClaimCaseService;

/**
 * 案件信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class ClaimCaseServiceImpl implements IClaimCaseService 
{
    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    /**
     * 查询案件信息 
     * 
     * @param rptNo 案件信息 ID
     * @return 案件信息 
     */
    @Override
    public ClaimCase selectClaimCaseById(String rptNo)
    {
        return claimCaseMapper.selectClaimCaseById(rptNo);
    }

    /**
     * 查询案件信息 列表
     * 
     * @param claimCase 案件信息 
     * @return 案件信息 
     */
    @Override
    public List<ClaimCase> selectClaimCaseList(ClaimCase claimCase)
    {
        return claimCaseMapper.selectClaimCaseList(claimCase);
    }

    /**
     * 新增案件信息 
     * 
     * @param claimCase 案件信息 
     * @return 结果
     */
    @Override
    public int insertClaimCase(ClaimCase claimCase)
    {
        claimCase.setCreateTime(DateUtils.getNowDate());
        return claimCaseMapper.insertClaimCase(claimCase);
    }

    /**
     * 修改案件信息 
     * 
     * @param claimCase 案件信息 
     * @return 结果
     */
    @Override
    public int updateClaimCase(ClaimCase claimCase)
    {
        claimCase.setUpdateTime(DateUtils.getNowDate());
        return claimCaseMapper.updateClaimCase(claimCase);
    }

    /**
     * 批量删除案件信息 
     * 
     * @param rptNos 需要删除的案件信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseByIds(String[] rptNos)
    {
        return claimCaseMapper.deleteClaimCaseByIds(rptNos);
    }

    /**
     * 删除案件信息 信息
     * 
     * @param rptNo 案件信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseById(String rptNo)
    {
        return claimCaseMapper.deleteClaimCaseById(rptNo);
    }
}
