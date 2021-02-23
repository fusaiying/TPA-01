package com.paic.ehis.claimapt.service;


import com.paic.ehis.claimapt.domain.ClaimBatch;
import com.paic.ehis.claimapt.domain.ClaimCaseStanding;
import com.paic.ehis.claimapt.domain.DTO.ClaimCaseStandingDTO;
import com.paic.ehis.claimapt.domain.Vo.ClaimCaseStandingVo;
import com.paic.ehis.claimapt.domain.Vo.ClaimCaseStandingVo1;

import java.util.List;

/**
 * 报案台账信息 Service接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface IClaimCaseStandingService 
{
    /**
     * 查询报案台账信息 
     * 
     * @param rptNo 报案台账信息 ID
     * @return 报案台账信息 
     */
    public ClaimCaseStanding selectClaimCaseStandingById(String rptNo);

    /**
     * 查询报案台账信息 列表
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 报案台账信息 集合
     */
    public List<ClaimCaseStanding> selectClaimCaseStandingList(ClaimCaseStanding claimCaseStanding);

    public List<ClaimCaseStandingVo1> selectClaimCaseStandingListNew(ClaimCaseStandingDTO claimCaseStandingDTO);

    List<ClaimCaseStandingVo1> selectClaimCaseStandingListFirst(ClaimCaseStandingDTO claimCaseStandingDTO);

    /**
     * 新增报案台账信息 
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 结果
     */
    public int insertClaimCaseStanding(ClaimCaseStanding claimCaseStanding);

    /**
     * 修改报案台账信息 
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 结果
     */
    public int updateClaimCaseStanding(ClaimCaseStanding claimCaseStanding);

    public int updateClaimCaseStandingVo1(ClaimCaseStandingVo1 claimCaseStandingVo1);

    /**
     * 批量删除报案台账信息 
     * 
     * @param rptNos 需要删除的报案台账信息 ID
     * @return 结果
     */
    public int deleteClaimCaseStandingByIds(String[] rptNos);

    /**
     * 删除报案台账信息 信息
     * 
     * @param rptNo 报案台账信息 ID
     * @return 结果
     */
    public int deleteClaimCaseStandingById(String rptNo);

    /*********************************************************/
    /**
     * 机构交单-事后-保存-新增报案台账信息
     *机构交单-事后-提交-新增报案台账信息
     * @param claimCaseStandingVo 报案台账信息
     * @return 结果
     */
    public int insertSysClaimCaseStanding(ClaimCaseStandingVo claimCaseStandingVo);//

    /**
     * 机构交单-事后-保存-修改报案台账信息
     *机构交单-事后-提交-修改报案台账信息
     * @param claimCaseStandingVo 报案台账信息
     * @return 结果
     */
    public int updateSysClaimCaseStanding(ClaimCaseStandingVo claimCaseStandingVo);//

    public List<ClaimCaseStandingVo> selectClaimCaseStandingByBatchno(ClaimBatch claimBatch);

    public int updateClaimCaseStandingByBatchno(String batchno);

    public List<ClaimCaseStanding> selectClaimCaseStandingByIdidType(ClaimCaseStanding claimCaseStanding);
}
