package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.ClaimCaseFiling;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseFilingDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingInformationVO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingListVO;

import java.util.List;

/**
 * 案件归档Service接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface IClaimCaseFilingService 
{
    /**
     * 查询案件归档
     * 
     * @param batchNo 案件归档ID
     * @return 案件归档
     */
    public ClaimCaseFiling selectClaimCaseFilingById(String batchNo);

    /**
     * 查询案件归档列表
     * 
     * @param claimCaseFiling 案件归档
     * @return 案件归档集合
     */
    public List<ClaimCaseFiling> selectClaimCaseFilingList(ClaimCaseFiling claimCaseFiling);

    /**
     * 新增案件归档
     * 
     * @param claimCaseFiling 案件归档
     * @return 结果
     */
    public int insertClaimCaseFiling(ClaimCaseFiling claimCaseFiling);

    /**
     * 修改案件归档
     * 
     * @param claimCaseFiling 案件归档
     * @return 结果
     */
    public int updateClaimCaseFiling(ClaimCaseFiling claimCaseFiling);

    /**
     * 批量删除案件归档
     * 
     * @param batchNos 需要删除的案件归档ID
     * @return 结果
     */
    public int deleteClaimCaseFilingByIds(String[] batchNos);

    /**
     * 删除案件归档信息
     * 
     * @param batchNo 案件归档ID
     * @return 结果
     */
    public int deleteClaimCaseFilingById(String batchNo);

    /** 查询案件归档主页面 */
    List<ClaimCaseFilingListVO> selectCaseClaimCaseFilingList(ClaimCaseFilingDTO claimCaseFilingDTO);

    /** 改变是否销毁状态 */
    public int updateClaimCaseFilingDestroy(ClaimCaseFilingListVO claimCaseFilingListVO);

    /** 编辑按钮 */
    public void updateClaimCaseFilingEdit(ClaimCaseFilingListVO claimCaseFilingListVO);

    /** 查询案件归档详细信息 */
    public List<ClaimCaseFilingInformationVO> selectCaseClaimCaseFilingInfo(ClaimCaseFilingListVO claimCaseFilingListVO);

    /** 保存案件归档详细信息 */
    public int updateClaimCaseFilingInfo(List<ClaimCaseFilingInformationVO> claimCaseFilingInformationVO);
}
