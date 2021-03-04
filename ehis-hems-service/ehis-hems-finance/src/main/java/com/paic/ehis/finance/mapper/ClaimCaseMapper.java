package com.paic.ehis.finance.mapper;

import com.paic.ehis.finance.domain.ClaimCase;

import java.util.List;

/**
 * 案件信息 Mapper接口
 *
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseMapper
{
    /**
     * 查询案件信息
     *
     * @param rptNo 案件信息 ID
     * @return 案件信息
     */
    public ClaimCase selectClaimCaseById(String rptNo);

    /**
     * 查询案件信息 列表
     *
     * @param claimCase 案件信息
     * @return 案件信息 集合
     */
    public List<ClaimCase> selectClaimCaseList(ClaimCase claimCase);


    /**
     * 修改案件信息
     *
     * @param claimCase 案件信息 
     * @return 结果
     */
    public int updateClaimCase(ClaimCase claimCase);

}
