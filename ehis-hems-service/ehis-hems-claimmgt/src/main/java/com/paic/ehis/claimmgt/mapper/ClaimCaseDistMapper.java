package com.paic.ehis.claimmgt.mapper;

import com.paic.ehis.claimmgt.domain.ClaimCaseDist;
import com.paic.ehis.claimmgt.domain.ClaimUserRole;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseOperationDTO;

import java.util.List;

/**
 * 案件分配规则Mapper接口
 * 
 * @author sino
 * @date 2021-01-22
 */
public interface ClaimCaseDistMapper 
{
    /**
     * 查询案件分配规则列表
     * 
     * @param claimCaseDist 案件分配规则
     * @return 案件分配规则集合
     */
    public List<ClaimCaseDist> selectClaimCaseDistList(ClaimCaseDist claimCaseDist);

    /**
     * 新增案件分配规则
     * 
     * @param claimCaseDist 案件分配规则
     * @return 结果
     */
    public int insertClaimCaseDist(ClaimCaseDist claimCaseDist);

    /**
     * 修改案件分配规则
     * 
     * @param claimCaseDist 案件分配规则
     * @return 结果
     */
    public int updateClaimCaseDist(ClaimCaseDist claimCaseDist);

    /**
     * 查询理赔角色信息
     * @param claimUserRole
     * @return
     */
    public List<ClaimUserRole> selectClaimUserRole(ClaimUserRole claimUserRole);

    /**
     * 更新理赔角色
     * @param claimUserRole
     * @return
     */
    public int updateClaimUserRole(ClaimUserRole claimUserRole);

    /**
     * 根据用户信息查询案件数量
     * @param claimCaseOperationDTO
     * @return
     */
    public int selectCaseCountByClaimCaseOperation(ClaimCaseOperationDTO claimCaseOperationDTO);
}
