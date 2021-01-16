package com.paic.ehis.system.mapper;

import java.util.List;

import com.paic.ehis.system.domain.vo.DutyVo;
import com.paic.ehis.system.domain.vo.PolicyVo;
import com.paic.ehis.system.domain.PolicyInfo;
import com.paic.ehis.system.domain.dto.PolicyDTO;

/**
 * 保单信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface PolicyInfoMapper 
{
    /**
     * 查询保单信息
     * 
     * @param policyNo 保单信息ID
     * @return 保单信息
     */
    public PolicyInfo selectPolicyInfoById(String policyNo);

    /**
     * 查询保单信息列表
     * 
     * @param policyInfo 保单信息
     * @return 保单信息集合
     */
    public List<PolicyInfo> selectPolicyInfoList(PolicyInfo policyInfo);

    /**
     * 新增保单信息
     * 
     * @param policyInfo 保单信息
     * @return 结果
     */
    public int insertPolicyInfo(PolicyInfo policyInfo);

    /**
     * 修改保单信息
     * 
     * @param policyInfo 保单信息
     * @return 结果
     */
    public int updatePolicyInfo(PolicyInfo policyInfo);

    /**
     * 删除保单信息
     * 
     * @param policyNo 保单信息ID
     * @return 结果
     */
    public int deletePolicyInfoById(String policyNo);

    /**
     * 批量删除保单信息
     * 
     * @param policyNos 需要删除的数据ID
     * @return 结果
     */
    public int deletePolicyInfoByIds(String[] policyNos);

    /**
     * 被保人保单信息页面
     * @param policyDTO
     * @return
     */
    List<PolicyVo> selectInsuredList(PolicyDTO policyDTO);

    List<DutyVo> selectDutyList(String riskName);
}
