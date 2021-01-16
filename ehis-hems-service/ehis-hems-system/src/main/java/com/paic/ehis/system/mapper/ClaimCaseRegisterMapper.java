package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimCaseRegister;

/**
 * 案件申请人信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseRegisterMapper 
{
    /**
     * 查询案件申请人信息
     * 
     * @param rptNo 案件申请人信息ID
     * @return 案件申请人信息
     */
    public ClaimCaseRegister selectClaimCaseRegisterById(String rptNo);

    /**
     * 查询案件申请人信息列表
     * 
     * @param claimCaseRegister 案件申请人信息
     * @return 案件申请人信息集合
     */
    public List<ClaimCaseRegister> selectClaimCaseRegisterList(ClaimCaseRegister claimCaseRegister);

    /**
     * 新增案件申请人信息
     * 
     * @param claimCaseRegister 案件申请人信息
     * @return 结果
     */
    public int insertClaimCaseRegister(ClaimCaseRegister claimCaseRegister);

    /**
     * 修改案件申请人信息
     * 
     * @param claimCaseRegister 案件申请人信息
     * @return 结果
     */
    public int updateClaimCaseRegister(ClaimCaseRegister claimCaseRegister);

    /**
     * 删除案件申请人信息
     * 
     * @param rptNo 案件申请人信息ID
     * @return 结果
     */
    public int deleteClaimCaseRegisterById(String rptNo);

    /**
     * 批量删除案件申请人信息
     * 
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseRegisterByIds(String[] rptNos);
}
