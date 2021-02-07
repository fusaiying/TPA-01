package com.paic.ehis.claimflow.service;



import com.paic.ehis.claimflow.domain.ClaimCaseAccept;
import com.paic.ehis.claimflow.domain.dto.CaseRegisterAndAppleTypeDTO;

import java.util.List;

/**
 * 案件受理信息 Service接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface IClaimCaseAcceptService 
{
    /**
     * 查询案件受理信息 
     * 
     * @param rptNo 案件受理信息 ID
     * @return 案件受理信息 
     */
    public CaseRegisterAndAppleTypeDTO selectClaimCaseAcceptById(String rptNo);

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
     * @param caseRegisterAndAppleTypeDTO 案件受理信息
     * @return 结果
     */
    public int insertClaimCaseAccept(CaseRegisterAndAppleTypeDTO caseRegisterAndAppleTypeDTO);

    /**
     * 修改案件受理信息 
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 结果
     */
    public int updateClaimCaseAccept(ClaimCaseAccept claimCaseAccept);

    /**
     * 批量删除案件受理信息 
     * 
     * @param rptNos 需要删除的案件受理信息 ID
     * @return 结果
     */
    public int deleteClaimCaseAcceptByIds(String[] rptNos);

    /**
     * 删除案件受理信息 信息
     * 
     * @param rptNo 案件受理信息 ID
     * @return 结果
     */
    public int deleteClaimCaseAcceptById(String rptNo);
}
