package com.paic.ehis.claimmgt.mapper;



import com.paic.ehis.claimmgt.domain.ClaimCaseStanding;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseStandingDTO;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseStandingVo1;

import java.util.List;

/**
 * 报案台账信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface ClaimCaseStandingMapper 
{
    /**
     * 查询报案台账信息 
     * 
     * @param rptNo 报案台账信息 ID
     * @return 报案台账信息 
     */
    public ClaimCaseStanding selectClaimCaseStandingById(String rptNo);

    /**
    * @author 硠君
    * @Description  通过批次号查询报案台账信息
    * @Date 15:36 2021/2/8
    * @Parm [claimCaseStanding]
    * @return java.util.List<com.paic.ehis.claimmgt.domain.ClaimCaseStanding>
    **/
    public List<ClaimCaseStanding> selectCaseStandingListByBatchNo(String batchNo);

    /**
     * 查询报案台账信息 列表      
     * 
     * @param claimCaseStanding 报案台账信息 
     * @return 报案台账信息 集合
     */
    public List<ClaimCaseStanding> selectClaimCaseStandingList(ClaimCaseStanding claimCaseStanding);

    public List<ClaimCaseStandingVo1> selectClaimCaseStandingListNew(ClaimCaseStandingDTO claimCaseStandingDTO);

    public String selectCompanyName(String rptNo);

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
     * 删除报案台账信息 
     * 
     * @param rptNo 报案台账信息 ID
     * @return 结果
     */
    public int deleteClaimCaseStandingById(String rptNo);

    /**
     * 批量删除报案台账信息 
     * 
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseStandingByIds(String[] rptNos);


    /**
     *
     * @param rptno
     * @return
     */
    public ClaimCaseStanding selectClaimCaseStandingByIdOne(String rptno);
}
