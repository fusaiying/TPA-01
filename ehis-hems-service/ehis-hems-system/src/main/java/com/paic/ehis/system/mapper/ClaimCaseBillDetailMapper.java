package com.paic.ehis.system.mapper;

import java.util.List;
import com.paic.ehis.system.domain.ClaimCaseBillDetail;

/**
 * 案件账单费用项明细Mapper接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface ClaimCaseBillDetailMapper 
{
    /**
     * 查询案件账单费用项明细
     * 
     * @param detailId 案件账单费用项明细ID
     * @return 案件账单费用项明细
     */
    public ClaimCaseBillDetail selectClaimCaseBillDetailById(Long detailId);

    /**
     * 查询案件账单费用项明细列表
     * 
     * @param claimCaseBillDetail 案件账单费用项明细
     * @return 案件账单费用项明细集合
     */
    public List<ClaimCaseBillDetail> selectClaimCaseBillDetailList(ClaimCaseBillDetail claimCaseBillDetail);

    /**
     * 新增案件账单费用项明细
     * 
     * @param claimCaseBillDetail 案件账单费用项明细
     * @return 结果
     */
    public int insertClaimCaseBillDetail(ClaimCaseBillDetail claimCaseBillDetail);

    /**
     * 修改案件账单费用项明细
     * 
     * @param claimCaseBillDetail 案件账单费用项明细
     * @return 结果
     */
    public int updateClaimCaseBillDetail(ClaimCaseBillDetail claimCaseBillDetail);

    /**
     * 删除案件账单费用项明细
     * 
     * @param detailId 案件账单费用项明细ID
     * @return 结果
     */
    public int deleteClaimCaseBillDetailById(Long detailId);

    /**
     * 批量删除案件账单费用项明细
     * 
     * @param detailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseBillDetailByIds(Long[] detailIds);
}
