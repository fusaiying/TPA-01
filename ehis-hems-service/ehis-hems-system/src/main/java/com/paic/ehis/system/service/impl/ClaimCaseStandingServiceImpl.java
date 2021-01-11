package com.paic.ehis.system.service.impl;

import java.util.List;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.mapper.ClaimCaseStandingMapper;
import com.paic.ehis.system.domain.ClaimCaseStanding;
import com.paic.ehis.system.service.IClaimCaseStandingService;

/**
 * 报案台账信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class ClaimCaseStandingServiceImpl implements IClaimCaseStandingService 
{
    @Autowired
    private ClaimCaseStandingMapper claimCaseStandingMapper;

    /**
     * 查询报案台账信息 
     * 
     * @param rptNo 报案台账信息 ID
     * @return 报案台账信息 
     */
    @Override
    public ClaimCaseStanding selectClaimCaseStandingById(String rptNo)
    {
        return claimCaseStandingMapper.selectClaimCaseStandingById(rptNo);
    }

    /**
     * 查询报案台账信息 列表
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 报案台账信息 
     */
    @Override
    public List<ClaimCaseStanding> selectClaimCaseStandingList(ClaimCaseStanding claimCaseStanding)
    {
        return claimCaseStandingMapper.selectClaimCaseStandingList(claimCaseStanding);
    }

    /**
     * 新增报案台账信息 
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 结果
     */
    @Override
    public int insertClaimCaseStanding(ClaimCaseStanding claimCaseStanding)
    {
        claimCaseStanding.setCreateTime(DateUtils.getNowDate());
        return claimCaseStandingMapper.insertClaimCaseStanding(claimCaseStanding);
    }

    /**
     * 修改报案台账信息 
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 结果
     */
    @Override
    public int updateClaimCaseStanding(ClaimCaseStanding claimCaseStanding)
    {
        claimCaseStanding.setUpdateTime(DateUtils.getNowDate());
        return claimCaseStandingMapper.updateClaimCaseStanding(claimCaseStanding);
    }

    /**
     * 批量删除报案台账信息 
     * 
     * @param rptNos 需要删除的报案台账信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseStandingByIds(String[] rptNos)
    {
        return claimCaseStandingMapper.deleteClaimCaseStandingByIds(rptNos);
    }

    /**
     * 删除报案台账信息 信息
     * 
     * @param rptNo 报案台账信息 ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseStandingById(String rptNo)
    {
        return claimCaseStandingMapper.deleteClaimCaseStandingById(rptNo);
    }
}
