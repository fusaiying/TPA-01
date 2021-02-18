package com.paic.ehis.claimflow.mapper;

import com.paic.ehis.claimflow.domain.ClaimCaseBillDetail;
import com.paic.ehis.system.api.domain.dto.ClaimCaseBillDetailDTO;

import java.util.List;

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
     * 根据报案号查询账单明细数据
     * @param rptNo
     * @return
     */
    public List<ClaimCaseBillDetailDTO> selectClaimCaseBillDetailDTOByRptNo(String rptNo);

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

    /**
     * 根据账单ID查询费用项明细
     *
     * @param billId
     * @return
     */
    public List<ClaimCaseBillDetail> selectClaimCaseBillDetailByBillId(Long billId);

    /**
     * 根据账单ID更新数据，失效
     *
     * @param billId
     * @return
     */
    public int updateClaimCaseBillDetailByBillId(Long billId);
}
