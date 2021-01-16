package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCasePayee;

/**
 * 案件领款人信息Service接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface IClaimCasePayeeService 
{
    /**
     * 查询案件领款人信息
     * 
     * @param payeeId 案件领款人信息ID
     * @return 案件领款人信息
     */
    public ClaimCasePayee selectClaimCasePayeeById(Long payeeId);

    /**
     * 查询案件领款人信息列表
     * 
     * @param claimCasePayee 案件领款人信息
     * @return 案件领款人信息集合
     */
    public List<ClaimCasePayee> selectClaimCasePayeeList(ClaimCasePayee claimCasePayee);

    /**
     * 新增案件领款人信息
     * 
     * @param claimCasePayee 案件领款人信息
     * @return 结果
     */
    public int insertClaimCasePayee(ClaimCasePayee claimCasePayee);

    /**
     * 修改案件领款人信息
     * 
     * @param claimCasePayee 案件领款人信息
     * @return 结果
     */
    public int updateClaimCasePayee(ClaimCasePayee claimCasePayee);

    /**
     * 批量删除案件领款人信息
     * 
     * @param payeeIds 需要删除的案件领款人信息ID
     * @return 结果
     */
    public int deleteClaimCasePayeeByIds(Long[] payeeIds);

    /**
     * 删除案件领款人信息信息
     * 
     * @param rptNo 案件领款人信息ID
     * @return 结果
     */
    public int deleteClaimCasePayeeByRptNo(String rptNo);

    public ClaimCasePayee selectClaimCasePayeeselectOne(ClaimCasePayee claimCasePayee);
}
