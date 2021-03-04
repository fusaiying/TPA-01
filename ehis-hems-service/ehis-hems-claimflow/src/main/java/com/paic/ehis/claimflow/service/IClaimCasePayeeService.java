package com.paic.ehis.claimflow.service;


import com.paic.ehis.claimflow.domain.ClaimCasePayee;

import java.util.List;

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
     * 校验案件领款人领款比例
     * @param rptNo
     * @return
     */
    public int CheckThePaymentRatio(String rptNo);

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
     * @param payeeId 案件领款人信息ID
     * @return 结果
     */
    public int deleteClaimCasePayeeByRptNo(Long payeeId);

    /**
     * 获取案件领款人信息-最近的一条
     * @param claimCasePayee
     * @return
     */
    public ClaimCasePayee selectClaimCasePayeeselectOne(ClaimCasePayee claimCasePayee);

    /**
     * 获取案件领款人信息
     * @param rptNo
     * @return
     */
    public List<ClaimCasePayee> selectClaimCasePayeeByRptNo(String rptNo);
}
