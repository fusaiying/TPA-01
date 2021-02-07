package com.paic.ehis.claimflow.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paic.ehis.claimflow.domain.*;
import com.paic.ehis.claimflow.domain.vo.CaseCalBillItemVo;
import com.paic.ehis.claimflow.mapper.ClaimCaseCalItemMapper;
import com.paic.ehis.claimflow.mapper.ClaimCasePolicyMapper;
import com.paic.ehis.claimflow.mapper.ClaimProductDutyDetailMapper;
import com.paic.ehis.claimflow.mapper.ClaimProductFeeitemMapper;
import com.paic.ehis.claimflow.service.IClaimCaseCalItemService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 案件赔付费用项明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class ClaimCaseCalItemServiceImpl implements IClaimCaseCalItemService
{
    @Autowired
    private ClaimCaseCalItemMapper claimCaseCalItemMapper;

    @Autowired
    PolicyAndRiskService policyAndRiskService;

    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;

    @Autowired
    private ClaimProductFeeitemMapper claimProductFeeitemMapper;

    @Autowired
    private ClaimProductDutyDetailMapper claimProductDutyDetailMapper;

    /**
     * 查询案件赔付费用项明细
     * 
     * @param calItemId 案件赔付费用项明细ID
     * @return 案件赔付费用项明细
     */
    @Override
    public ClaimCaseCalItem selectClaimCaseCalItemById(Long calItemId)
    {
        return claimCaseCalItemMapper.selectClaimCaseCalItemById(calItemId);
    }

    /**
     * 查询案件赔付费用项明细列表
     * 
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 案件赔付费用项明细
     */
    @Override
    public List<ClaimCaseCalItem> selectClaimCaseCalItemList(ClaimCaseCalItem claimCaseCalItem)
    {
        return claimCaseCalItemMapper.selectClaimCaseCalItemList(claimCaseCalItem);
    }

    /**
     * 理算审核 下拉查询案件赔付费用项明细列表
     *
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 案件赔付费用项明细集合
     */
    @Override
    public List<CaseCalBillItemVo> selectClaimCaseCalFeeItemList(ClaimCaseCalItem claimCaseCalItem) {
        claimCaseCalItem.setStatus("Y");
        return claimCaseCalItemMapper.selectClaimCaseCalFeeItemList(claimCaseCalItem);
    }

    /**
     * 理算审核 查询保单险种关联列表
     *
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 案件赔付费用项明细集合
     */
    @Override
    public List<PolicyRiskRelation> insurancePolicyList(ClaimCaseCalItem claimCaseCalItem){
        PolicyAndRiskRelation policyAndRiskRelation = new PolicyAndRiskRelation();
        PolicyRiskRelation policyRiskRelation;
        ArrayList<PolicyRiskRelation> policyRiskRelations = new ArrayList<>();
        List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(claimCaseCalItem.getRptNo());
        ObjectMapper objectMapper = new ObjectMapper();

        for (ClaimCasePolicy claimCasePolicy : claimCasePolicies) {
            policyAndRiskRelation.setPolicyNo(claimCasePolicy.getPolicyNo());
            TableDataInfo relationList = policyAndRiskService.getRelationList(policyAndRiskRelation);
            if (StringUtils.isNotNull(relationList.getRows())) {
                for (Object row : relationList.getRows()) {
                    policyRiskRelation = objectMapper.convertValue(row, PolicyRiskRelation.class);
                    policyRiskRelations.add(policyRiskRelation);
                }
            }
        }
        return policyRiskRelations;
    }

    /**
     * 理算审核 查询费用项关联责任明细列表
     *
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 案件赔付费用项明细集合
     */
    @Override
    public List<ClaimProductDutyDetail> responsibilityDetailsList(ClaimCaseCalItem claimCaseCalItem) {
        ClaimProductFeeitem claimProductFeeitem = new ClaimProductFeeitem();
        claimProductFeeitem.setRiskCode(claimCaseCalItem.getRiskCode());
        claimProductFeeitem.setFeeitemCode(claimCaseCalItem.getFeeItemCode());
        List<ClaimProductFeeitem> claimProductFeeitems = claimProductFeeitemMapper.selectClaimProductFeeitemList(claimProductFeeitem);
        ArrayList<ClaimProductDutyDetail> claimProductDutyDetails = new ArrayList<>();
        for (ClaimProductFeeitem productFeeitem : claimProductFeeitems) {
            ClaimProductDutyDetail claimProductDutyDetail = claimProductDutyDetailMapper.selectClaimProductDutyDetailById(productFeeitem.getDutyDetailCode());
            if (StringUtils.isNotNull(claimProductDutyDetail)) {
                claimProductDutyDetails.add(claimProductDutyDetail);
            }
        }
        return claimProductDutyDetails;
    }


    /**
     * 新增案件赔付费用项明细
     * 
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseCalItem(ClaimCaseCalItem claimCaseCalItem)
    {
        claimCaseCalItem.setCreateTime(DateUtils.getNowDate());
        return claimCaseCalItemMapper.insertClaimCaseCalItem(claimCaseCalItem);
    }

    /**
     * 修改案件赔付费用项明细
     * 
     * @param claimCaseCalItem 案件赔付费用项明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseCalItem(ClaimCaseCalItem claimCaseCalItem)
    {
        claimCaseCalItem.setUpdateTime(DateUtils.getNowDate());
        return claimCaseCalItemMapper.updateClaimCaseCalItem(claimCaseCalItem);
    }

    /**
     * 批量删除案件赔付费用项明细
     * 
     * @param calItemIds 需要删除的案件赔付费用项明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalItemByIds(Long[] calItemIds)
    {
        return claimCaseCalItemMapper.deleteClaimCaseCalItemByIds(calItemIds);
    }

    /**
     * 删除案件赔付费用项明细信息
     * 
     * @param calItemId 案件赔付费用项明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCalItemById(Long calItemId)
    {
        return claimCaseCalItemMapper.deleteClaimCaseCalItemById(calItemId);
    }
}
