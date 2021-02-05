package com.paic.ehis.system.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.system.domain.*;
import com.paic.ehis.system.mapper.ClaimProductMapper;
import com.paic.ehis.system.mapper.ClaimProductPlanMapper;
import com.paic.ehis.system.mapper.ClaimProductTaskLogMapper;
import com.paic.ehis.system.mapper.ClaimRuleMapper;
import com.paic.ehis.system.service.IClaimProductTaskLogService;
import com.paic.ehis.system.utility.ElementUnit;
import com.paic.ehis.system.utility.RuleElement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品状态轨迹 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-07
 */
@Service
public class ClaimProductTaskLogServiceImpl implements IClaimProductTaskLogService 
{
    @Autowired
    private ClaimProductTaskLogMapper claimProductTaskLogMapper;

    @Autowired
    private ClaimProductMapper claimProductMapper;

    @Autowired
    private ClaimProductPlanMapper claimProductPlanMapper;

    @Autowired
    private ClaimRuleMapper claimRuleMapper;
    /**
     * 查询产品状态轨迹 
     * 
     * @param riskLogNo 产品状态轨迹 ID
     * @return 产品状态轨迹 
     */
    @Override
    public ClaimProductTaskLog selectClaimProductTaskLogById(String riskLogNo)
    {
        return claimProductTaskLogMapper.selectClaimProductTaskLogById(riskLogNo);
    }

    /**
     * 查询产品状态轨迹 列表
     * 
     * @param claimProductTaskLog 产品状态轨迹 
     * @return 产品状态轨迹 
     */
    @Override
    public List<ClaimProductTaskLog> selectClaimProductTaskLogList(ClaimProductTaskLog claimProductTaskLog)
    {
        return claimProductTaskLogMapper.selectClaimProductTaskLogList(claimProductTaskLog);
    }

    /**
     * 新增产品状态轨迹 
     * 
     * @param claimProductTaskLogInfo 产品状态轨迹
     * @return 结果
     */
    @Override
    public int insertClaimProductTaskLog(ClaimProductTaskLogInfo claimProductTaskLogInfo)
    {
        ClaimProductTaskLog claimProductTaskLog = new ClaimProductTaskLog();
        BeanUtils.copyProperties(claimProductTaskLogInfo,claimProductTaskLog);
        ClaimProductTaskLog historyLogQurey = new ClaimProductTaskLog();
        historyLogQurey.setRiskCode(claimProductTaskLog.getRiskCode());
        historyLogQurey.setIsHistory("N");
        List<ClaimProductTaskLog> logList = claimProductTaskLogMapper.selectClaimProductTaskLogList(historyLogQurey);
        for (ClaimProductTaskLog taskLog : logList) {
            taskLog.setIsHistory("Y");
            taskLog.setUpdateBy(SecurityUtils.getUsername());//：flint  xcc：taskLog.setCreateBy(claimProductTaskLog.getCreateBy());
            taskLog.setUpdateTime(DateUtils.getNowDate());//：flint  xcc：taskLog.setCreateTime(DateUtils.getNowDate());
            claimProductTaskLogMapper.updateClaimProductTaskLog(taskLog);
        }

        claimProductTaskLog.setRiskLogNo(PubFun.createMySqlMaxNoUseCache("riskLogNo",10,20));
        claimProductTaskLog.setIsHistory("N");
        claimProductTaskLog.setCreateTime(DateUtils.getNowDate());
        claimProductTaskLog.setStatus("Y");
        ClaimProduct product = claimProductMapper.selectClaimProductById(claimProductTaskLog.getRiskCode());
        product.setRiskStatus(claimProductTaskLog.getRiskStatus());
        product.setUpdateBy(SecurityUtils.getUsername());// :flint
        historyLogQurey.setIsHistory("Y");                                                                                       //从审核退回定义判断代码块中取出
        historyLogQurey.setRiskStatus("02");                                                                                    // 为获取定义完成过来的轨迹信息
        List<ClaimProductTaskLog> logs02 = claimProductTaskLogMapper.selectClaimProductTaskLogList(historyLogQurey);            //  flint：
                      //: flint 如果是提交审核，则轨迹表UpdateBy为空
            claimProductTaskLog.setUpdateBy(logs02.get(0).getUpdateBy());
            claimProductTaskLog.setUpdateTime(DateUtils.getNowDate());

        if ("03".equals(claimProductTaskLog.getRiskStatus())) {
            historyLogQurey.setIsHistory("Y");                                                                                       //从审核退回定义判断代码块中取出
            historyLogQurey.setRiskStatus("03");                                                                                    // 为获取定义完成过来的轨迹信息
            List<ClaimProductTaskLog> logs03 = claimProductTaskLogMapper.selectClaimProductTaskLogList(historyLogQurey);
            if (logs03.size()>0) {//如果存在数据则不为第一次通过
                claimProductTaskLog.setUpdateBy(logs03.get(0).getUpdateBy());
            }else {
            claimProductTaskLog.setUpdateBy(null);
            }
        }
        //如果是从审核退回定义
        if ("02".equals(claimProductTaskLog.getRiskStatus())) {
            if (logs02.size()>0) {
                product.setUpdateBy(logs02.get(0).getUpdateBy());//：flint  xcc：product.setUpdateBy(logs02.get(0).getCreateBy()
            }
        }
        product.setUpdateTime(DateUtils.getNowDate());
        if (StringUtils.isNotEmpty(claimProductTaskLogInfo.getExamineConclusion())) {
            product.setExamineConclusion(claimProductTaskLogInfo.getExamineConclusion());
            product.setConclusionExplanation(claimProductTaskLogInfo.getConclusionExplanation());

            //审核通过时，结论非必录，如果不录入结论说明，需要用空白覆盖]
            if("04".equals(claimProductTaskLog.getRiskStatus())&&StringUtils.isEmpty(claimProductTaskLogInfo.getConclusionExplanation())){
                product.setConclusionExplanation(" ");
            }
        }
        claimProductMapper.updateClaimProduct(product);

        return claimProductTaskLogMapper.insertClaimProductTaskLog(claimProductTaskLog);
    }

    /**
     * 检查每个计划是否都设置了保障区域规则
     *
     * @param riskCode 产品 ID
     * @return 结果
     */
    @Override
    public int checkClaimProductById(String riskCode) {
        int planCheck = 1;
        ClaimProductPlan planQuery = new ClaimProductPlan();
        planQuery.setRiskCode(riskCode);
        planQuery.setStatus("Y");
        List<ClaimProductPlan> plans = claimProductPlanMapper.selectClaimProductPlanList(planQuery);
        for (ClaimProductPlan plan : plans) {
            ClaimRule ruleQuery = new ClaimRule();
            ruleQuery.setRiskCode(riskCode);
            ruleQuery.setPlanCode(plan.getPlanCode());
            ruleQuery.setRuleElement(RuleElement.ENSUREREGION.getCode());
            ruleQuery.setElementUnit(ElementUnit.PERCENT.getCode());
            ruleQuery.setElementValue(BigDecimal.valueOf(100));
            ruleQuery.setStatus("Y");
            List<ClaimRule> rules = claimRuleMapper.selectClaimRuleList(ruleQuery);
            if (rules.size()<1) {
                planCheck = 0;
                break;
            }
        }
        return planCheck;
    }

    /**
     * 修改产品状态轨迹 
     * 
     * @param claimProductTaskLog 产品状态轨迹 
     * @return 结果
     */
    @Override
    public int updateClaimProductTaskLog(ClaimProductTaskLog claimProductTaskLog)
    {
        claimProductTaskLog.setUpdateTime(DateUtils.getNowDate());
        return claimProductTaskLogMapper.updateClaimProductTaskLog(claimProductTaskLog);
    }

    /**
     * 批量删除产品状态轨迹 
     * 
     * @param riskLogNos 需要删除的产品状态轨迹 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductTaskLogByIds(String[] riskLogNos)
    {
        return claimProductTaskLogMapper.deleteClaimProductTaskLogByIds(riskLogNos);
    }

    /**
     * 删除产品状态轨迹 信息
     * 
     * @param riskLogNo 产品状态轨迹 ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductTaskLogById(String riskLogNo)
    {
        return claimProductTaskLogMapper.deleteClaimProductTaskLogById(riskLogNo);
    }
}
