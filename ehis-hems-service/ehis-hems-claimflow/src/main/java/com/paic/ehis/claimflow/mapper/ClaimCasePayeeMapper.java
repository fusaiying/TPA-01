package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.ClaimCasePayee;

import java.util.List;

/**
 * 案件领款人信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCasePayeeMapper 
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
     * 删除案件领款人信息
     * 
     * @param payeeId 案件领款人信息ID
     * @return 结果
     */
    public int deleteClaimCasePayeeById(Long payeeId);

    /**
     * 批量删除案件领款人信息
     * 
     * @param payeeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCasePayeeByIds(Long[] payeeIds);

    /*
    * //案件领款人
默认查询当前被保人上一笔案件的账户信息
//报案号传过来
* */
    public ClaimCasePayee selectClaimCasePayeeselectOne(String rptNo);

    /**
     * 获取案件领款人信息
     * @param rptNo
     * @return
     */
    public List<ClaimCasePayee> selectClaimCasePayeeByRptNo(String rptNo);

    public int CheckClaimCasePayeeByRptNo(String rptNo);
}
