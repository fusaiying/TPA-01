package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimCaseAccept;

/**
 * 案件受理信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseAcceptMapper 
{
    /**
     * 查询案件受理信息 
     * 
     * @param rptNo 案件受理信息 ID
     * @return 案件受理信息 
     */
    public ClaimCaseAccept selectClaimCaseAcceptById(String rptNo);

    /**
     * 查询案件受理信息 列表
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 案件受理信息 集合
     */
    public List<ClaimCaseAccept> selectClaimCaseAcceptList(ClaimCaseAccept claimCaseAccept);

    /**
     * 新增案件受理信息 
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 结果
     */
    public int insertClaimCaseAccept(ClaimCaseAccept claimCaseAccept);

    /**
     * 修改案件受理信息 
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 结果
     */
    public int updateClaimCaseAccept(ClaimCaseAccept claimCaseAccept);

    /**
     * 删除案件受理信息 
     * 
     * @param rptNo 案件受理信息 ID
     * @return 结果
     */
    public int deleteClaimCaseAcceptById(String rptNo);

    /**
     * 批量删除案件受理信息 
     * 
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseAcceptByIds(String[] rptNos);
}
