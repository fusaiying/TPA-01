package com.paic.ehis.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.paic.ehis.common.core.constant.HttpStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseIssuingcompanyRule;
import com.paic.ehis.base.domain.ClaimProduct;
import com.paic.ehis.base.domain.dto.IssuingcompanyRuleDTO;
import com.paic.ehis.base.domain.vo.IssuingcompanyRuleVO;
import com.paic.ehis.base.mapper.BaseIssuingcompanyRuleMapper;
import com.paic.ehis.base.service.IBaseIssuingcompanyRuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 出单公司规则 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIssuingcompanyRuleServiceImpl implements IBaseIssuingcompanyRuleService 
{
    @Autowired
    private BaseIssuingcompanyRuleMapper baseIssuingcompanyRuleMapper;

    /**
     * 查询出单公司规则 
     * 
     * @param ruleNo 出单公司规则 ID
     * @return 出单公司规则 
     */
    @Override
    public BaseIssuingcompanyRule selectBaseIssuingcompanyRuleById(String ruleNo)
    {
        return baseIssuingcompanyRuleMapper.selectBaseIssuingcompanyRuleById(ruleNo);
    }

    /**
     * 查询出单公司规则 列表
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 出单公司规则 
     */
    @Override
    public TableDataInfo selectBaseIssuingcompanyRuleList(BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        baseIssuingcompanyRule.setStatus("Y");
        List<BaseIssuingcompanyRule> baseIssuingcompanyRules = baseIssuingcompanyRuleMapper.selectBaseIssuingcompanyRuleByProduct(baseIssuingcompanyRule);
        List<IssuingcompanyRuleVO> issuingcompanyRuleVOS = new ArrayList<>();
        for (BaseIssuingcompanyRule issuingcompanyRule : baseIssuingcompanyRules) {
            IssuingcompanyRuleVO issuingcompanyRuleVO = new IssuingcompanyRuleVO();
            BeanUtils.copyProperties(issuingcompanyRule,issuingcompanyRuleVO);
            if (issuingcompanyRule.getSettlementtype().equals("01")){
                issuingcompanyRuleVO.setAccounttype("02");
                issuingcompanyRuleVO.setServicefee(issuingcompanyRule.getSettlementvalue());
            }else if (issuingcompanyRule.getSettlementtype().equals("02")){
                issuingcompanyRuleVO.setAccounttype("01");
                issuingcompanyRuleVO.setPremrate(issuingcompanyRule.getSettlementvalue().intValue());
            }
            issuingcompanyRuleVOS.add(issuingcompanyRuleVO);
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(issuingcompanyRuleVOS);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo(baseIssuingcompanyRules).getTotal());
        return rspData;
    }

    /**
     * 新增或修改出单公司规则
     *
     * @param issuingcompanyRuleDTO 出单公司规则
     * @return 出单公司规则
     */
    @Transactional
    @Override
    public int addAndModifyBaseIssuingcompanyRule(IssuingcompanyRuleDTO issuingcompanyRuleDTO) {
        String[] riskcodes = issuingcompanyRuleDTO.getRiskcode().split(",");
        BaseIssuingcompanyRule issuingcompanyRule = new BaseIssuingcompanyRule();
        BeanUtils.copyProperties(issuingcompanyRuleDTO,issuingcompanyRule);
        if (StringUtils.isNotNull(issuingcompanyRuleDTO.getPremrate()))
        {
            issuingcompanyRule.setSettlementtype("02");
            issuingcompanyRule.setSettlementvalue(new BigDecimal(issuingcompanyRuleDTO.getPremrate()));
        }else if (StringUtils.isNotNull(issuingcompanyRuleDTO.getServicefee())){
            issuingcompanyRule.setSettlementtype("01");
            issuingcompanyRule.setSettlementvalue(issuingcompanyRuleDTO.getServicefee());
        }
        int rows = 0;
        for (String riskcode : riskcodes) {
            issuingcompanyRule.setRiskcode(riskcode);
            BaseIssuingcompanyRule companyRule = baseIssuingcompanyRuleMapper.selectBaseIssuingcompanyRule(issuingcompanyRule);
            if (StringUtils.isNotNull(companyRule)){
                issuingcompanyRule.setRuleno(companyRule.getRuleno());
                issuingcompanyRule.setUpdateBy(SecurityUtils.getUsername());
                issuingcompanyRule.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
                rows += baseIssuingcompanyRuleMapper.updateBaseIssuingcompanyRule(issuingcompanyRule);
            }else{
                issuingcompanyRule.setStatus("Y");
                issuingcompanyRule.setRuleno("S"+ PubFun.createMySqlMaxNoUseCache("service_fee_rule",10,4));
                issuingcompanyRule.setCreateBy(SecurityUtils.getUsername());
                issuingcompanyRule.setCreateTime(DateUtils.getNowDate());
                issuingcompanyRule.setUpdateBy(SecurityUtils.getUsername());
                issuingcompanyRule.setUpdateTime(DateUtils.getNowDate());
                rows += baseIssuingcompanyRuleMapper.insertBaseIssuingcompanyRule(issuingcompanyRule);
            }

        }
        return rows;
    }


    /**
     * 新增出单公司规则 
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 结果
     */
    @Override
    public int insertBaseIssuingcompanyRule(BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        baseIssuingcompanyRule.setStatus("Y");
        baseIssuingcompanyRule.setCreateBy(SecurityUtils.getUsername());
        baseIssuingcompanyRule.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompanyRule.setUpdateBy(SecurityUtils.getUsername());
        baseIssuingcompanyRule.setUpdateTime(DateUtils.getNowDate());
        return baseIssuingcompanyRuleMapper.insertBaseIssuingcompanyRule(baseIssuingcompanyRule);
    }

    /**
     * 修改出单公司规则 
     * 
     * @param baseIssuingcompanyRule 出单公司规则 
     * @return 结果
     */
    @Override
    public int updateBaseIssuingcompanyRule(BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        baseIssuingcompanyRule.setUpdateTime(DateUtils.getNowDate());
        baseIssuingcompanyRule.setUpdateBy(SecurityUtils.getUsername());
        return baseIssuingcompanyRuleMapper.updateBaseIssuingcompanyRule(baseIssuingcompanyRule);
    }

    /**
     * 批量删除出单公司规则 
     * 
     * @param ruleNos 需要删除的出单公司规则 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyRuleByIds(String[] ruleNos)
    {
        return baseIssuingcompanyRuleMapper.deleteBaseIssuingcompanyRuleByIds(ruleNos);
    }

    /**
     * 删除出单公司规则 信息
     * 
     * @param ruleNo 出单公司规则 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyRuleById(String ruleNo)
    {
        return baseIssuingcompanyRuleMapper.deleteBaseIssuingcompanyRuleById(ruleNo);
    }

    /**
     * 查询出单公司规则 列表
     *
     * @param baseIssuingcompanyRule 出单公司规则
     * @return 出单公司规则
     */
    @Override
    public List<ClaimProduct> selectBaseIssuingCompanyRiskList(BaseIssuingcompanyRule baseIssuingcompanyRule)
    {
        baseIssuingcompanyRule.setStatus("Y");
        return baseIssuingcompanyRuleMapper.selectBaseIssuingCompanyRiskList(baseIssuingcompanyRule);
    }
}
