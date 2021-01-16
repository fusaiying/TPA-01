package com.paic.ehis.system.service;

import java.util.List;

import com.paic.ehis.system.domain.ClaimCase;
import com.paic.ehis.system.domain.dto.CaseDispatchDTO;
import com.paic.ehis.system.domain.dto.ClaimCaseDTO;
import com.paic.ehis.system.domain.dto.DispatchDTO;
import com.paic.ehis.system.domain.vo.ClaimAndBatchVo;
import com.paic.ehis.system.domain.vo.ProcessingCaseVo;
import com.paic.ehis.system.domain.vo.DispatchVo;

/**
 * 案件信息 Service接口
 * 
 * @author sino
 * @date 2021-01-09
 */
public interface IClaimCaseService 
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
    public List<ClaimCase> selectProcessedClaimCaseList(ClaimCaseDTO claimCaseDTO);
    /**
     * 查询悬挂中受理案件信息 列表
     *
     * @param claimCaseDTO 案件信息
     * @return 案件信息 集合
     */
    public List<ProcessingCaseVo> selectSuspensionClaimCaseList(ClaimCaseDTO claimCaseDTO);

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
     * 批量删除案件信息 
     * 
     * @param rptNos 需要删除的案件信息 ID
     * @return 结果
     */
    public int deleteClaimCaseByIds(String[] rptNos);

    /**
     * 删除案件信息 信息
     * 
     * @param rptNo 案件信息 ID
     * @return 结果
     */
    public int deleteClaimCaseById(String rptNo);

    /**
     * 获取案件信息 详细信息（多表关联查询batch和Claim表）
     * @param rptNo
     * @return 结果
     */
    public ClaimAndBatchVo selectClaimClaimAndBatchById(String rptNo);

    /**
     * 问题件按钮（子任务添加功能为完成）
     *  修改案件信息
     * @param claimCase
     * @return
     */
    public int updateCaseInfoSuspend(ClaimCase claimCase);

    /**
     * 受理确认按钮功能
     * 修改案件信息
     * @param claimCase
     * @return
     */
    public int updateCaseAndRecordInfoSuspend(ClaimCase claimCase);

    /**
     * 撤件弹框确认按钮
     * 修改案件信息
     * @param claimCase
     * @return
     */
    public int updateCaseAndRecordInfoCancel(ClaimCase claimCase);

    /**
     * 查询案件调度工作池
     * @param
     * @return
     */
    //List<ClaimCase> selectCaseDispatchList(ClaimCase claimCase);
    List<DispatchVo> selectCaseDispatchList(DispatchDTO dispatchDTO);

    public void updateClaimCase(CaseDispatchDTO caseDispatchDTO);

    //条件理算审核
    public List<ClaimCase> SelectConditionsForTheAdjustmentUnder(ClaimCase claimCase);//待处理
    public List<ClaimCase> SelectConditionsForTheAdjustmentOver(ClaimCase claimCase);//已处理
    public List<ClaimCase> SelectConditionsForTheAdjustmentHang(ClaimCase claimCase);//悬挂


}
