package com.paic.ehis.claimflow.service;



import com.paic.ehis.claimflow.domain.ClaimCaseInsured;
import com.paic.ehis.claimflow.domain.ClaimCasePayee;
import com.paic.ehis.claimflow.domain.dto.InsuredAndPolicy;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseInsureAndPoliyVo;

import java.util.List;

/**
 * 案件被保人信息Service接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface IClaimCaseInsuredService 
{
    /**
     * 查询案件被保人信息
     * 
     * @param rptNo 案件被保人信息ID
     * @return 案件被保人信息
     */
    public ClaimCaseInsureAndPoliyVo selectClaimCaseInsuredById(String rptNo);

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
     * 批量删除案件被保人信息
     * 
     * @param rptNos 需要删除的案件被保人信息ID
     * @return 结果
     */
    public int deleteClaimCaseInsuredByIds(String[] rptNos);

    /**
     * 删除案件被保人信息信息
     * 
     * @param rptNo 案件被保人信息ID
     * @return 结果
     */
    public int deleteClaimCaseInsuredById(String rptNo);

    /**
     * 受理页面 新增被保人信息+保单信息关联
     * @param insuredAndPolicy
     * @return
     */
    public int insertClaimCaseInsuredAndPolicy(InsuredAndPolicy insuredAndPolicy);

    /**
     *  查询上次被保人的领款人信息
     * @param claimCaseInsured
     * @return
     */
    public int selectPayeeListByInsured(ClaimCaseInsured claimCaseInsured);


}
