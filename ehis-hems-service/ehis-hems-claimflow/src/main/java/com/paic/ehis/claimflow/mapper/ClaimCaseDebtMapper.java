package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.ClaimCaseDebt;
import com.paic.ehis.claimflow.domain.dto.DebtInfo;
import com.paic.ehis.claimflow.domain.dto.DebtInfoDTO;
import com.paic.ehis.claimflow.domain.vo.DebtInfoVO;

import java.util.List;

/**
 * 案件追讨信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimCaseDebtMapper 
{
    /**
     * 查询案件追讨信息
     * 
     * @param debtId 案件追讨信息ID
     * @return 案件追讨信息
     */
    public ClaimCaseDebt selectClaimCaseDebtById(Long debtId);

    /**
     * 查询案件追讨信息
     *
     * @param rptNo 案件追讨信息ID
     * @return 案件追讨信息
     */
    public ClaimCaseDebt selectClaimCaseDebtByRptNo(String rptNo);

    /**
     * 查询案件追讨信息列表
     * 
     * @param claimCaseDebt 案件追讨信息
     * @return 案件追讨信息集合
     */
    public List<ClaimCaseDebt> selectClaimCaseDebtList(ClaimCaseDebt claimCaseDebt);

    /**
     * 新增案件追讨信息
     * 
     * @param claimCaseDebt 案件追讨信息
     * @return 结果
     */
    public int insertClaimCaseDebt(ClaimCaseDebt claimCaseDebt);

    /**
     * 修改案件追讨信息
     * 
     * @param claimCaseDebt 案件追讨信息
     * @return 结果
     */
    public int updateClaimCaseDebt(ClaimCaseDebt claimCaseDebt);

    /**
     * 删除案件追讨信息
     * 
     * @param debtId 案件追讨信息ID
     * @return 结果
     */
    public int deleteClaimCaseDebtById(Long debtId);

    /**
     * 批量删除案件追讨信息
     * 
     * @param debtIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseDebtByIds(Long[] debtIds);

    /**
     * 查询追讨工作池 - 初始化
     *
     * @return
     */
    public List<DebtInfoVO> selectDebtInitList();

    /**
     * 查询追讨信息
     *
     * @param debtInfoDTO
     * @return
     */
    public List<DebtInfoVO> selectDebtList(DebtInfoDTO debtInfoDTO);
}
