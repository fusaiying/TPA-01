package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimCaseCheckRule;
import com.paic.ehis.claimflow.domain.ClaimCaseCheckRuleAttr;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseCheckDTO;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseCheckRuleDTO;
import com.paic.ehis.claimflow.mapper.ClaimCaseCheckRuleAttrMapper;
import com.paic.ehis.claimflow.mapper.ClaimCaseCheckRuleMapper;
import com.paic.ehis.claimflow.service.IClaimCaseCheckRuleService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 案件抽检规则Service业务层处理
 * 
 * @author sino
 * @date 2021-01-22
 */
@Service
public class ClaimCaseCheckRuleServiceImpl implements IClaimCaseCheckRuleService
{
    @Autowired
    private ClaimCaseCheckRuleMapper claimCaseCheckRuleMapper;

    @Autowired
    private ClaimCaseCheckRuleAttrMapper claimCaseCheckRuleAttrMapper;

    /**
     * 查询案件抽检规则
     * 
     * @param checkRuleNo 案件抽检规则ID
     * @return 案件抽检规则
     */
    @Override
    public ClaimCaseCheckRule selectClaimCaseCheckRuleById(String checkRuleNo)
    {
        return claimCaseCheckRuleMapper.selectClaimCaseCheckRuleById(checkRuleNo);
    }

    /**
     * 查询案件抽检规则列表
     * 
     * @param claimCaseCheckRule 案件抽检规则
     * @return 案件抽检规则
     */
    @Override
    public List<ClaimCaseCheckRule> selectClaimCaseCheckRuleList(ClaimCaseCheckRule claimCaseCheckRule)
    {
        return claimCaseCheckRuleMapper.selectClaimCaseCheckRuleList(claimCaseCheckRule);
    }

    /**
     * 新增案件抽检规则
     * 
     * @param claimCaseCheckRule 案件抽检规则
     * @return 结果
     */
    @Override
    public int insertClaimCaseCheckRule(ClaimCaseCheckRule claimCaseCheckRule)
    {
        claimCaseCheckRule.setCreateTime(DateUtils.getNowDate());
        return claimCaseCheckRuleMapper.insertClaimCaseCheckRule(claimCaseCheckRule);
    }

    /**
     * 修改案件抽检规则
     * 
     * @param claimCaseCheckRule 案件抽检规则
     * @return 结果
     */
    @Override
    public int updateClaimCaseCheckRule(ClaimCaseCheckRule claimCaseCheckRule)
    {
        claimCaseCheckRule.setUpdateTime(DateUtils.getNowDate());
        return claimCaseCheckRuleMapper.updateClaimCaseCheckRule(claimCaseCheckRule);
    }

    /**
     * 批量删除案件抽检规则
     * 
     * @param checkRuleNos 需要删除的案件抽检规则ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCheckRuleByIds(String[] checkRuleNos)
    {
        return claimCaseCheckRuleMapper.deleteClaimCaseCheckRuleByIds(checkRuleNos);
    }

    /**
     * 删除案件抽检规则信息
     * 
     * @param checkRuleNo 案件抽检规则ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseCheckRuleById(String checkRuleNo)
    {
        return claimCaseCheckRuleMapper.deleteClaimCaseCheckRuleById(checkRuleNo);
    }

    /** 新增 */
    @Override
    public int insertClaimCaseCheckRuleNew(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO) {

        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();
        claimCaseCheckRuleDTO.setCreateTime(nowDate);
        claimCaseCheckRuleDTO.setCreateBy(username);
        claimCaseCheckRuleDTO.setUpdateBy(username);
        claimCaseCheckRuleDTO.setUpdateTime(nowDate);

        String checkRuleNo = "SPOT" + PubFun.createMySqlMaxNoUseCache("checkRuleNo", 0, 3);
        claimCaseCheckRuleDTO.setCheckRuleNo(checkRuleNo);
        claimCaseCheckRuleMapper.insertClaimCaseCheckRuleNew(claimCaseCheckRuleDTO);
        //根据抽检规则编码新增案件抽检规则属性表数据
        List<String> payConclusion = Arrays.asList(claimCaseCheckRuleDTO.getPayConclusion().split(","));
        for (String pc:payConclusion) {
            ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr = new ClaimCaseCheckRuleAttr();
            claimCaseCheckRuleAttr.setCheckRuleNo(checkRuleNo);
            claimCaseCheckRuleAttr.setPayConclusion(pc);
            claimCaseCheckRuleAttr.setCreateTime(nowDate);
            claimCaseCheckRuleAttr.setCreateBy(username);
            claimCaseCheckRuleAttr.setStatus("Y");
            claimCaseCheckRuleAttrMapper.insertClaimCaseCheckRuleAttr(claimCaseCheckRuleAttr);
        }
        return 0;
    }

    /** 修改 */
    @Override
    public int updateClaimCaseCheckRuleNew(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO) {

        int result = 0;
        Date nowDate = DateUtils.getNowDate();
        String username = SecurityUtils.getUsername();

        claimCaseCheckRuleDTO.setUpdateBy(username);
        claimCaseCheckRuleDTO.setUpdateTime(nowDate);
        claimCaseCheckRuleMapper.updateClaimCaseCheckRuleNew(claimCaseCheckRuleDTO);

        //逻辑删除所有的赔付结论
        ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr = new ClaimCaseCheckRuleAttr();
        claimCaseCheckRuleAttr.setCheckRuleNo(claimCaseCheckRuleDTO.getCheckRuleNo());
        claimCaseCheckRuleAttr.setStatus("N");
        claimCaseCheckRuleAttrMapper.updateClaimCaseCheckRuleAttrNew(claimCaseCheckRuleAttr);

        //得到前端传的赔付结论
        List<String> payConclusion = Arrays.asList(claimCaseCheckRuleDTO.getPayConclusion().split(","));
        //插入赔付结论
        for(String conclusion : payConclusion) {
            claimCaseCheckRuleAttr.setCheckRuleNo(claimCaseCheckRuleDTO.getCheckRuleNo());
            claimCaseCheckRuleAttr.setPayConclusion(conclusion);
            claimCaseCheckRuleAttr.setCreateBy(username);
            claimCaseCheckRuleAttr.setCreateTime(nowDate);
            claimCaseCheckRuleAttr.setUpdateBy(username);
            claimCaseCheckRuleAttr.setUpdateTime(nowDate);
            claimCaseCheckRuleAttr.setStatus("Y");
            result =  claimCaseCheckRuleAttrMapper.insertClaimCaseCheckRuleAttr(claimCaseCheckRuleAttr);
        }

        return result;
        //更改信息
//        if(claimCaseCheckRuleDTO.getStatus().equals("N")) {
//            claimCaseCheckRuleDTO.setUpdateBy(SecurityUtils.getUsername());
//            claimCaseCheckRuleDTO.setUpdateTime(DateUtils.getNowDate());
//        }
//        claimCaseCheckRuleMapper.updateClaimCaseCheckRuleNew(claimCaseCheckRuleDTO);
//
//        List<ClaimCaseCheckRuleDTO> claimCaseCheckRuleDTOS = claimCaseCheckRuleMapper.selectClaimCaseCheckRuleNew(claimCaseCheckRuleDTO);
//        //得到前端传的赔付结论
//        List<String> payConclusion = Arrays.asList(claimCaseCheckRuleDTO.getPayConclusion().split(","));
//
//        for (ClaimCaseCheckRuleDTO dto:claimCaseCheckRuleDTOS) {
//            //通过得到的抽检规则编码去查询ID
//            List<Long> strings = claimCaseCheckRuleAttrMapper.selectClaimCaseCheckRuleAttrByCheckRuleNoNew(dto.getCheckRuleNo());
//            //根据ID案件抽检规则属性表，修改数据状态为N
//            for (Long s: strings) {
//                ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr = new ClaimCaseCheckRuleAttr();
//                claimCaseCheckRuleAttr.setAttrId(s);
//                claimCaseCheckRuleAttr.setStatus("N");
//                claimCaseCheckRuleAttrMapper.updateClaimCaseCheckRuleAttrNew(claimCaseCheckRuleAttr);
//                //新增同样数据状态为Y的数据
//                for (String pc:payConclusion) {
//                    claimCaseCheckRuleAttr.setCheckRuleNo(claimCaseCheckRuleDTO.getCheckRuleNo());
//                    claimCaseCheckRuleAttr.setPayConclusion(pc);
//                    claimCaseCheckRuleAttr.setCreateTime(DateUtils.getNowDate());
//                    claimCaseCheckRuleAttr.setCreateBy(SecurityUtils.getUsername());
//                    claimCaseCheckRuleAttr.setStatus("Y");
//                    claimCaseCheckRuleAttrMapper.insertClaimCaseCheckRuleAttr(claimCaseCheckRuleAttr);
//                }
//            }
//        }
//        return 0;
    }

    /**
     * 判断案件是否符合流程抽检岗规则
     */
    @Override
    public ClaimCaseCheckDTO judgeClaimCaseCheckRule(ClaimCaseCheckDTO claimCaseCheckDTO) {

        claimCaseCheckDTO.setStatus("Y");
        //查询出符合要求的所有抽检规则
        List<ClaimCaseCheckDTO> claimCaseCheckRules = claimCaseCheckRuleMapper.selectClaimCaseCheckRuleMatch(claimCaseCheckDTO);

        boolean flag = false;
        for (ClaimCaseCheckDTO cc : claimCaseCheckRules) {
            double caseCheck = 0D;
            //判断是否在此抽检比例中
            double v = cc.getRate().multiply(new BigDecimal(10)).doubleValue();
            boolean random = PubFun.random(v);
            //将查询到的金额类型与接收的金额类型进行比较
            if ("赔付金额".equals(cc.getAmountType())) {
                caseCheck = claimCaseCheckDTO.getPayAmount().doubleValue();
            } else if ("拒付金额".equals(cc.getAmountType())) {
                caseCheck = claimCaseCheckDTO.getAmount().doubleValue();
            }
            //若此时的抽检比例和金额符合要求
            if (random && caseCheck <= cc.getCheckAmount().doubleValue()) {
                flag = true;
                break;
            }
        }
        //判断此案件应该进入什么案件状态
        if (flag) {
            claimCaseCheckDTO.setCaseStatus("08");
        } else {
            claimCaseCheckDTO.setCaseStatus("99");
        }
        return claimCaseCheckDTO;
    }

    /** 查询案件抽检规则 */
    @Override
    public List<ClaimCaseCheckRuleDTO> selectClaimCaseCheckRule(ClaimCaseCheckRuleDTO claimCaseCheckRuleDTO) {
        List<ClaimCaseCheckRuleDTO> claimCaseCheckRuleDTOS = claimCaseCheckRuleMapper.selectClaimCaseCheckRuleNew(claimCaseCheckRuleDTO);
        return claimCaseCheckRuleDTOS;
    }
}
