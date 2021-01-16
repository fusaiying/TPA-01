package com.paic.ehis.system.mapper;

import java.util.List;

import com.paic.ehis.system.domain.vo.ClaimAndBatchVo;
import com.paic.ehis.system.domain.vo.ProcessingCaseVo;
import com.paic.ehis.system.domain.ClaimCase;
import com.paic.ehis.system.domain.dto.ClaimCaseDTO;
import com.paic.ehis.system.domain.dto.DispatchDTO;
import com.paic.ehis.system.domain.vo.DispatchPolicyVo;
import com.paic.ehis.system.domain.vo.DispatchVo;

/**
 * 案件信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface ClaimCaseMapper 
{
    /**
     * 查询案件信息 
     * 
     * @param rptNo 案件信息 ID
     * @return 案件信息 
     */
    public ClaimCase selectClaimCaseById(String rptNo);

    /**
     * 查询案件信息 列表
     *
     * @param claimCase 案件信息
     * @return 案件信息 集合
     */
    public List<ClaimCase> selectClaimCaseList(ClaimCase claimCase);

    /**
     * 查询处理中案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    public List<ProcessingCaseVo> selectProcessingClaimCaseList(ClaimCaseDTO claimCaseDTO);

    /**
     * 查询已处理受理案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    public List<ClaimCase> selectClaimCaseProcessedList(ClaimCaseDTO claimCaseDTO);

    /**
     * 新增案件信息 
     * 
     * @param claimCase 案件信息 
     * @return 结果
     */
    public int insertClaimCase(ClaimCase claimCase);

    /**
     * 修改案件信息 
     * 
     * @param claimCase 案件信息 
     * @return 结果
     */
    public int updateClaimCase(ClaimCase claimCase);

    /**
     * 删除案件信息 
     * 
     * @param rptNo 案件信息 ID
     * @return 结果
     */
    public int deleteClaimCaseById(String rptNo);

    /**
     * 批量删除案件信息 
     * 
     * @param rptNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseByIds(String[] rptNos);


    public ClaimAndBatchVo selectClaimClaimAndBatchById(String rptNo);

    //List<ClaimCase> selectCaseDispatchList(ClaimCase claimCase);

    List<DispatchVo> selectCaseDispatchList(DispatchDTO dispatchDTO);

    List<DispatchPolicyVo>  selectPolicyDispatchList(DispatchDTO dispatchDTO);

    //条件里算审核

    //条件理算审核
    public List<ClaimCase> SelectConditionsForTheAdjustmentUnder(ClaimCase claimCase);//待处理
    public List<ClaimCase> SelectConditionsForTheAdjustmentOver(ClaimCase claimCase);//已处理
    public List<ClaimCase> SelectConditionsForTheAdjustmentHang(ClaimCase claimCase);//悬挂
}
