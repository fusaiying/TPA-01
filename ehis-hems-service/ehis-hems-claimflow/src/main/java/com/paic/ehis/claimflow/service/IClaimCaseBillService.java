package com.paic.ehis.claimflow.service;

import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseDTO;
import com.paic.ehis.claimflow.domain.vo.BillAccomplishVo;
import com.paic.ehis.claimflow.domain.vo.BillProcessingVo;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseBillInfoVO;
import com.paic.ehis.system.api.domain.ClaimProductFeeitem;

import java.util.List;

/**
 * 案件账单明细Service接口
 * 
 * @author sino
 * @date 2021-01-15
 */
public interface IClaimCaseBillService 
{
    /**
     * 查询案件账单明细
     * 
     * @param billId 案件账单明细ID
     * @return 案件账单明细
     */
    public ClaimCaseBillInfoVO selectClaimCaseBillById(Long billId);

    /**
     * 查询案件账单明细列表
     * 
     * @param claimCaseBill 案件账单明细
     * @return 案件账单明细集合
     */
    public List<ClaimCaseBill> selectClaimCaseBillList(ClaimCaseBill claimCaseBill);

    /**
     * 新增案件账单明细
     * 
     * @param claimCaseBill 案件账单明细
     * @return 结果
     */
    public int insertClaimCaseBill(ClaimCaseBillInfoVO claimCaseBill);

    /**
     * 修改案件账单明细
     * 
     * @param claimCaseBill 案件账单明细
     * @return 结果
     */
    public int updateClaimCaseBill(ClaimCaseBillInfoVO claimCaseBill);

    /**
     * 批量删除案件账单明细
     * 
     * @param billIds 需要删除的案件账单明细ID
     * @return 结果
     */
    public int deleteClaimCaseBillByIds(Long[] billIds);

    /**
     * 删除案件账单明细信息
     * 
     * @param billId 案件账单明细ID
     * @return 结果
     */
    public int deleteClaimCaseBillById(Long billId);

    /**
     * 查询处理中工作池
     *
     * @param claimCaseDTO
     * @return
     */
    public List<BillProcessingVo> selectProcessingList(ClaimCaseDTO claimCaseDTO);

    /**
     * 查询已处理工作池
     *
     * @param claimCaseDTO
     * @return
     */
    public List<BillAccomplishVo> selectAccomplishList(ClaimCaseDTO claimCaseDTO);

    /**
     * 根据报案号查询账单明细表
     *
     * @param rptNo
     * @return
     */
    public ClaimCaseBill selectClaimCaseBillListByRptNo(String rptNo);

    /**
     * 根据报案号查询费用项信息
     *
     * @param rptNo
     * @return
     */
    public List<ClaimProductFeeitem> selectFeeitemList(String rptNo);

    /**
     * 理算计算
     * @param rptNo
     * @return
     */
    public boolean ClaimCal(String rptNo);
}
