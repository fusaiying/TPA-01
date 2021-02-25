package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.AccidentInfoMap;
import com.paic.ehis.claimflow.domain.ClaimCaseAccept;

import java.util.List;

/**
 * 案件受理信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseAcceptMapper 
{
    /**
     * 查询案件受理信息 
     * 
     * @param rptNo 案件受理信息 ID
     * @return 案件受理信息 
     */
    public ClaimCaseAccept selectClaimCaseAcceptById(String rptNo);

    /**
     *
     * @param rptNo
     * @return
     */
    public List<ClaimCaseAccept> selectClaimCaseAcceptByIdOne(String rptNo);


    /**
     * 查询案件受理信息 列表
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 案件受理信息 集合
     */
    public List<ClaimCaseAccept> selectClaimCaseAcceptList(ClaimCaseAccept claimCaseAccept);

    public AccidentInfoMap selectacciPlace(String rptNo);

    /**
     * 新增案件受理信息 
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 结果
     */
    public int insertClaimCaseAccept(ClaimCaseAccept claimCaseAccept);

    /**
     * 修改案件受理信息 
     * 
     * @param claimCaseAccept 案件受理信息 
     * @return 结果
     */
    public int updateClaimCaseAccept(ClaimCaseAccept claimCaseAccept);

    /**
     * 删除案件受理信息 
     * 
     * @param rptNo 案件受理信息 ID
     * @return 结果
     */
    public int deleteClaimCaseAcceptById(String rptNo);

    /**
     * 批量删除案件受理信息 
     * 
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseAcceptByIds(String[] rptNos);
}
