package com.paic.ehis.claimflow.mapper;


import com.paic.ehis.claimflow.domain.ClaimCaseFiling;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseFilingDTO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingInformationVO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingListVO;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseFilingVO;

import java.util.List;

/**
 * 案件归档Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimCaseFilingMapper 
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
     * 删除案件归档
     * 
     * @param batchNo 案件归档ID
     * @return 结果
     */
    public int deleteClaimCaseFilingById(String batchNo);

    /**
     * 批量删除案件归档
     * 
     * @param batchNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseFilingByIds(String[] batchNos);

    /** 查询案件归档主页面 */
    List<ClaimCaseFilingVO> selectCaseClaimCaseFilingList(ClaimCaseFilingDTO claimCaseFilingDTO);


    /** 编辑按钮 */
    public int updateClaimCaseFilingEdit(ClaimCaseFilingListVO claimCaseFilingListVO);

    /** 查询案件归档详细信息 */
    public ClaimCaseFilingInformationVO selectCaseClaimCaseFilingInfo(ClaimCaseFilingListVO claimCaseFilingListVO);
}
