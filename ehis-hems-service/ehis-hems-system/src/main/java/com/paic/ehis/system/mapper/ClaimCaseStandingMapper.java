package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimCaseStanding;

/**
 * 报案台账信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface ClaimCaseStandingMapper 
{
    /**
     * 查询报案台账信息 
     * 
     * @param rptNo 报案台账信息 ID
     * @return 报案台账信息 
     */
    public ClaimCaseStanding selectClaimCaseStandingById(String rptNo);

    /**
     * 查询报案台账信息 列表
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 报案台账信息 集合
     */
    public List<ClaimCaseStanding> selectClaimCaseStandingList(ClaimCaseStanding claimCaseStanding);

    /**
     * 新增报案台账信息 
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 结果
     */
    public int insertClaimCaseStanding(ClaimCaseStanding claimCaseStanding);

    /**
     * 修改报案台账信息 
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 结果
     */
    public int updateClaimCaseStanding(ClaimCaseStanding claimCaseStanding);

    /**
     * 删除报案台账信息 
     * 
     * @param rptNo 报案台账信息 ID
     * @return 结果
     */
    public int deleteClaimCaseStandingById(String rptNo);

    /**
     * 批量删除报案台账信息 
     * 
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseStandingByIds(String[] rptNos);
}
