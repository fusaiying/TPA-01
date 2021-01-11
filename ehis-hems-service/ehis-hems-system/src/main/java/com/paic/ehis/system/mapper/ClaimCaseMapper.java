package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimCase;

/**
 * 案件信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
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
     * 新增案件信息 
     * 
     * @param claimCase 案件信息 
     * @return 结果
     */
    public int insertClaimCase(ClaimCase claimCase);

    /**
     * 修改案件信息 
     * 
     * @param claimCase 案件信息 
     * @return 结果
     */
    public int updateClaimCase(ClaimCase claimCase);

    /**
     * 删除案件信息 
     * 
     * @param rptNo 案件信息 ID
     * @return 结果
     */
    public int deleteClaimCaseById(String rptNo);

    /**
     * 批量删除案件信息 
     * 
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseByIds(String[] rptNos);
}
