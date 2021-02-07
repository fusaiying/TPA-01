package com.paic.ehis.claimflow.mapper;



import com.paic.ehis.claimflow.domain.ClaimCaseCalBill;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillVo;

import java.util.List;

/**
 * 案件赔付账单明细Mapper接口
 * 
 * @author sino
 * @date 2021-01-20
 */
public interface ClaimCaseCalBillMapper 
{
    /**
     * 查询案件赔付账单明细
     * 
     * @param calBillId 案件赔付账单明细ID
     * @return 案件赔付账单明细
     */
    public ClaimCaseCalBill selectClaimCaseCalBillById(Long calBillId);

    /**
     * 查询案件赔付账单明细列表
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 案件赔付账单明细集合
     */
    public List<ClaimCaseCalBill> selectClaimCaseCalBillList(ClaimCaseCalBill claimCaseCalBill);

    /**
     * 理算审核默认查询案件账单明细列表
     *
     * @param claimCaseCalBill 案件赔付信息
     * @return 案件赔付信息集合
     */
    public List<CaseCalBillVo> selectCaseCalInformationList(ClaimCaseCalBill claimCaseCalBill);

    /**
     * 新增案件赔付账单明细
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 结果
     */
    public int insertClaimCaseCalBill(ClaimCaseCalBill claimCaseCalBill);

    /**
     * 修改案件赔付账单明细
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 结果
     */
    public int updateClaimCaseCalBill(ClaimCaseCalBill claimCaseCalBill);

    /**
     * 修改案件赔付账单明细
     *
     * @param calBillList 案件赔付账单明细
     * @return 结果
     */
    public int bulkUpdateClaimCaseCalBill(List<ClaimCaseCalBill> calBillList);

    /**
     * 删除案件赔付账单明细
     * 
     * @param calBillId 案件赔付账单明细ID
     * @return 结果
     */
    public int deleteClaimCaseCalBillById(Long calBillId);

    /**
     * 批量删除案件赔付账单明细
     * 
     * @param calBillIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteClaimCaseCalBillByIds(Long[] calBillIds);
}
