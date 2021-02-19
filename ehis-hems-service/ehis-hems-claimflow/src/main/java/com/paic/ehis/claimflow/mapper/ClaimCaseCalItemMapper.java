package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.ClaimCaseCalItem;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillItemVo;

import java.util.List;

/**
 * 案件赔付费用项明细Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimCaseCalItemMapper 
{
    /**
     * 查询案件赔付费用项明细
     * 
     * @param calItemId 案件赔付费用项明细ID
     * @return 案件赔付费用项明细
     */
    public ClaimCaseCalItem selectClaimCaseCalItemById(Long calItemId);

    /**
     * 查询案件赔付费用项明细列表
     * 
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 案件赔付费用项明细集合
     */
    public List<ClaimCaseCalItem> selectClaimCaseCalItemList(ClaimCaseCalItem claimCaseCalItem);

    /**
     * 理算审核下拉查询案件赔付费用项明细列表
     *
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 案件赔付费用项明细集合
     */
    public List<CaseCalBillItemVo> selectClaimCaseCalFeeItemList(ClaimCaseCalItem claimCaseCalItem);

    /**
     * 新增案件赔付费用项明细
     * 
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 结果
     */
    public int insertClaimCaseCalItem(ClaimCaseCalItem claimCaseCalItem);

    /**
     * 修改案件赔付费用项明细
     * 
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 结果
     */
    public int updateClaimCaseCalItem(ClaimCaseCalItem claimCaseCalItem);

    public int updateClaimCaseCalItemByRptNo(ClaimCaseCalItem claimCaseCalItem);

    /**
     * 批量修改案件赔付费用项明细
     *
     * @param itemList 案件赔付费用项明细列表
     * @return 结果
     */
    public int bulkUpdateClaimCaseCalItem(List<ClaimCaseCalItem> itemList);

    /**
     * 删除案件赔付费用项明细
     * 
     * @param calItemId 案件赔付费用项明细ID
     * @return 结果
     */
    public int deleteClaimCaseCalItemById(Long calItemId);

    /**
     * 批量删除案件赔付费用项明细
     * 
     * @param calItemIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseCalItemByIds(Long[] calItemIds);
}
