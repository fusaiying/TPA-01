package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimCaseInsured;

/**
 * 案件被保人信息Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseInsuredMapper 
{
    /**
     * 查询案件被保人信息
     * 
     * @param rptNo 案件被保人信息ID
     * @return 案件被保人信息
     */
    public ClaimCaseInsured selectClaimCaseInsuredById(String rptNo);

    /**
     * 查询案件被保人信息列表
     * 
     * @param claimCaseInsured 案件被保人信息
     * @return 案件被保人信息集合
     */
    public List<ClaimCaseInsured> selectClaimCaseInsuredList(ClaimCaseInsured claimCaseInsured);

    /**
     * 新增案件被保人信息
     * 
     * @param claimCaseInsured 案件被保人信息
     * @return 结果
     */
    public int insertClaimCaseInsured(ClaimCaseInsured claimCaseInsured);

    /**
     * 修改案件被保人信息
     * 
     * @param claimCaseInsured 案件被保人信息
     * @return 结果
     */
    public int updateClaimCaseInsured(ClaimCaseInsured claimCaseInsured);

    /**
     * 删除案件被保人信息
     * 
     * @param rptNo 案件被保人信息ID
     * @return 结果
     */
    public int deleteClaimCaseInsuredById(String rptNo);

    /**
     * 批量删除案件被保人信息
     * 
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseInsuredByIds(String[] rptNos);
}
