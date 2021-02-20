package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseCalBill;
import com.paic.ehis.claimflow.domain.ClaimCaseCalItem;
import com.paic.ehis.claimflow.domain.dto.BillDetailDTO;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillItemVo;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillVo;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalBillMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalItemMapper;
import com.paic.ehis.claimflow.service.IClaimCaseCalBillService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 案件赔付账单明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseCalBillServiceImpl implements IClaimCaseCalBillService
{
    @Autowired
    private ClaimCaseCalBillMapper claimCaseCalBillMapper;

    @Autowired
    private ClaimCaseCalItemMapper claimCaseCalItemMapper;

    /**
     * 查询案件赔付账单明细
     * 
     * @param calBillId 案件赔付账单明细ID
     * @return 案件赔付账单明细
     */
    @Override
    public ClaimCaseCalBill selectClaimCaseCalBillById(Long calBillId)
    {
        return claimCaseCalBillMapper.selectClaimCaseCalBillById(calBillId);
    }

    /**
     * 查询案件赔付账单明细列表
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 案件赔付账单明细
     */
    @Override
    public List<ClaimCaseCalBill> selectClaimCaseCalBillList(ClaimCaseCalBill claimCaseCalBill)
    {
        return claimCaseCalBillMapper.selectClaimCaseCalBillList(claimCaseCalBill);
    }

    /**
     * 理算审核默认查询案件赔付信息 列表
     *
     * @param claimCaseCalBill 案件赔付信息
     * @return 案件赔付信息
     */
    @Override
    public List<CaseCalBillVo> selectCaseCalInformationList(ClaimCaseCalBill claimCaseCalBill)
    {
        claimCaseCalBill.setStatus("Y");
        return claimCaseCalBillMapper.selectCaseCalInformationList(claimCaseCalBill);
    }

    /**
     * 新增案件赔付账单明细
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseCalBill(ClaimCaseCalBill claimCaseCalBill)
    {
        claimCaseCalBill.setCreateTime(DateUtils.getNowDate());
        return claimCaseCalBillMapper.insertClaimCaseCalBill(claimCaseCalBill);
    }

    /**
     * 修改案件赔付账单明细
     * 
     * @param claimCaseCalBill 案件赔付账单明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseCalBill(ClaimCaseCalBill claimCaseCalBill)
    {
        claimCaseCalBill.setUpdateTime(DateUtils.getNowDate());
        return claimCaseCalBillMapper.updateClaimCaseCalBill(claimCaseCalBill);
    }

    /**
     * 理算审核  账单明细保存  按钮
     *
     * @param billDetailDTO 案件赔付账单明细
     * @return 结果
     */
    @Transactional
    @Override
    public int billDetailsSave(BillDetailDTO billDetailDTO) {
        ArrayList<ClaimCaseCalBill> claimCaseCalBills = new ArrayList<>();
        ArrayList<ClaimCaseCalItem> claimCaseCalItems = new ArrayList<>();
        if (StringUtils.isNotEmpty(billDetailDTO.getBillDetailList())) {
            for (CaseCalBillVo caseCalBillVo : billDetailDTO.getBillDetailList()) {
                ClaimCaseCalBill claimCaseCalBill = new ClaimCaseCalBill();
                claimCaseCalBill.setCalAmount(caseCalBillVo.getCalAmount());
                claimCaseCalBill.setRemark(caseCalBillVo.getRemark());
                claimCaseCalBill.setPayConclusion(caseCalBillVo.getPayConclusion());
                claimCaseCalBill.setCalBillId(caseCalBillVo.getCalBillId());
                claimCaseCalBill.setUpdateBy(SecurityUtils.getUsername());
                claimCaseCalBills.add(claimCaseCalBill);
                if (StringUtils.isNotEmpty(caseCalBillVo.getMinData())) {
                    for (CaseCalBillItemVo minDatum : caseCalBillVo.getMinData()) {
                        ClaimCaseCalItem claimCaseCalItem = new ClaimCaseCalItem();
                        claimCaseCalItem.setPolicyNo(minDatum.getPolicyNo());
                        claimCaseCalItem.setRiskCode(minDatum.getRiskCode());
                        claimCaseCalItem.setDutyDetailCode(minDatum.getDutyDetailCode());
                        claimCaseCalItem.setCalItemId(minDatum.getCalItemId());
                        claimCaseCalItem.setUpdateBy(SecurityUtils.getUsername());
                        claimCaseCalItems.add(claimCaseCalItem);
                    }
                }
            }
        }
        if (StringUtils.isNotEmpty(claimCaseCalItems)) {
            claimCaseCalItemMapper.bulkUpdateClaimCaseCalItem(claimCaseCalItems);
        }
        return claimCaseCalBillMapper.bulkUpdateClaimCaseCalBill(claimCaseCalBills);
    }

    /**
     * 批量删除案件赔付账单明细
     * 
     * @param calBillIds 需要删除的案件赔付账单明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalBillByIds(Long[] calBillIds)
    {
        return claimCaseCalBillMapper.deleteClaimCaseCalBillByIds(calBillIds);
    }

    /**
     * 删除案件赔付账单明细信息
     * 
     * @param calBillId 案件赔付账单明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalBillById(Long calBillId)
    {
        return claimCaseCalBillMapper.deleteClaimCaseCalBillById(calBillId);
    }
}
