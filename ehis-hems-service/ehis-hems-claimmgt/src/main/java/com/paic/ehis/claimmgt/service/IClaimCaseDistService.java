package com.paic.ehis.claimmgt.service;

import com.paic.ehis.claimmgt.domain.ClaimCaseDist;
import com.paic.ehis.claimmgt.domain.ClaimUserRole;
//import com.paic.ehis.claimmgt.domain.dto.ClaimBatchDTO;

import java.util.List;

/**
 * 案件分配规则Service接口
 *
 * @author sino
 * @date 2021-01-22
 */
public interface IClaimCaseDistService
{
    /**
     * 获取分配规则信息
     * @param claimCaseDist
     * @return
     */
    List<ClaimCaseDist> getClaimCaseDistInfo(ClaimCaseDist claimCaseDist);

    /**
     * 查询理赔角色信息
     * @param claimUserRole
     * @return
     */
    List<ClaimUserRole> selectClaimUserRole(ClaimUserRole claimUserRole);

    /**
     * 编辑分配规则
     * @param claimCaseDist
     * @return
     */
    int editClaimCaseDist(ClaimCaseDist claimCaseDist);

    /**
     * 编辑理赔角色
     * @param claimUserRole
     * @return
     */
    int editClaimUserRole(ClaimUserRole claimUserRole);


}
