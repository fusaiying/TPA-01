package com.paic.ehis.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.paic.ehis.system.domain.*;
import com.paic.ehis.system.mapper.ClaimRuleConstraintMapper;
import com.paic.ehis.system.mapper.ClaimRuleMapper;
import com.paic.ehis.system.mapper.ClaimRuleRelationMapper;
import com.paic.ehis.system.service.IClaimRuleService;
import com.paic.ehis.common.core.constant.HttpStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.domain.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 福利规则 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-06
 */
@Service
public class ClaimRuleServiceImpl implements IClaimRuleService
{
    @Autowired
    private ClaimRuleMapper claimRuleMapper;

    @Autowired
    private ClaimRuleConstraintMapper claimRuleConstraintMapper;

    @Autowired
    private ClaimRuleRelationMapper claimRuleRelationMapper;


    /**
     * 查询福利规则 
     * 
     * @param ruleNo 福利规则 ID
     * @return 福利规则 
     */
    @Override
    public ClaimRule selectClaimRuleById(String ruleNo)
    {
        return claimRuleMapper.selectClaimRuleById(ruleNo);
    }

    @Override
    public ClaimRuleInfo selectClaimRuleInfoById(String ruleNo) {

        ClaimRuleInfo claimRuleInfo = new ClaimRuleInfo();

        //查询规则表
        ClaimRule claimRule = claimRuleMapper.selectClaimRuleById(ruleNo);
        BeanUtils.copyProperties(claimRule,claimRuleInfo);

        //查询规则条件表
        ClaimRuleConstraint constraintQuery = new ClaimRuleConstraint();
        constraintQuery.setRuleNo(ruleNo);
        List<ClaimRuleConstraint> constraintList = claimRuleConstraintMapper.selectClaimRuleConstraintList(constraintQuery);
        claimRuleInfo.setConstraintList(constraintList);

        //查询规则关联对象表
        ClaimRuleRelation relationQurey = new ClaimRuleRelation();
        relationQurey.setRuleNo(ruleNo);
        List<ClaimRuleRelationInfo> relationInfoList = claimRuleRelationMapper.selectClaimRuleRelationInfoList(relationQurey);
        claimRuleInfo.setRelationInfoList(relationInfoList);

        return claimRuleInfo;
    }

    /**
     * 查询福利规则 列表
     * 
     * @param claimRule 福利规则 
     * @return 福利规则 
     */
    @Override
    public List<ClaimRule> selectClaimRuleList(ClaimRule claimRule)
    {
        return claimRuleMapper.selectClaimRuleList(claimRule);
    }

    @Override
    public TableDataInfo selectClaimRuleInfoList(ClaimRule claimRule) {
        List<ClaimRuleInfo> claimRuleInfoList = new ArrayList<>();
        List<ClaimRule> claimRuleList = claimRuleMapper.selectClaimRuleList(claimRule);
        for (ClaimRule rule : claimRuleList) {
            claimRuleInfoList.add(selectClaimRuleInfoById(rule.getRuleNo()));
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(claimRuleInfoList);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo(claimRuleList).getTotal());

        return rspData;
    }

    /**
     * 新增福利规则 
     * 
     * @param claimRule 福利规则 
     * @return 结果
     */
    @Override
    public int insertClaimRule(ClaimRule claimRule)
    {
        claimRule.setCreateTime(DateUtils.getNowDate());
        return claimRuleMapper.insertClaimRule(claimRule);
    }

    @Override
    public int insertClaimRuleInfo(ClaimRuleInfo claimRuleInfo) {

        if (claimRuleInfo.getIsShare().equals("Y")) { //共享
            String ruleNo = PubFun.createMySqlMaxNoUseCache("ruleNo", 10, 20);

            List<ClaimRuleConstraint> constraintList = claimRuleInfo.getConstraintList();
            for (ClaimRuleConstraint constraint : constraintList) {
                constraint.setConstraintNo(PubFun.createMySqlMaxNoUseCache("constraintNo",10,20));
                constraint.setRuleNo(ruleNo);
                constraint.setStatus("Y");
                constraint.setCreateBy(claimRuleInfo.getCreateBy());
                constraint.setCreateTime(DateUtils.getNowDate());
            }
            if (constraintList.size()>0) {
                claimRuleConstraintMapper.insertList(constraintList);
            }

            List<ClaimRuleRelationInfo> relationInfoList = claimRuleInfo.getRelationInfoList();
            List<ClaimRuleRelation> relationList = new ArrayList<>();
            for (ClaimRuleRelationInfo relationInfo : relationInfoList) {
                ClaimRuleRelation relation = new ClaimRuleRelation();
                BeanUtils.copyProperties(relationInfo,relation);
                relation.setRelationNo(PubFun.createMySqlMaxNoUseCache("relationNo",10,20));
                relation.setRuleNo(ruleNo);
                relation.setStatus("Y");
                relation.setCreateBy(claimRuleInfo.getCreateBy());
                relation.setCreateTime(DateUtils.getNowDate());
                relationList.add(relation);
            }
            if (relationList.size()>0) {
                claimRuleRelationMapper.insertList(relationList);
            }

            ClaimRule claimRule = new ClaimRule();
            BeanUtils.copyProperties(claimRuleInfo,claimRule);

            claimRule.setPlanCode(relationList.get(0).getPlanCode());
            claimRule.setRuleNo(ruleNo);
            claimRule.setStatus("Y");
            claimRule.setCreateTime(DateUtils.getNowDate());
            return claimRuleMapper.insertClaimRule(claimRule);
        }else { //不共享
            List<ClaimRuleRelationInfo> relationInfoList = claimRuleInfo.getRelationInfoList();
            for (ClaimRuleRelationInfo relationInfo : relationInfoList) {
                String ruleNo = PubFun.createMySqlMaxNoUseCache("ruleNo", 10, 20);

                ClaimRuleRelation relation = new ClaimRuleRelation();
                BeanUtils.copyProperties(relationInfo,relation);
                relation.setRelationNo(PubFun.createMySqlMaxNoUseCache("relationNo",10,20));
                relation.setRuleNo(ruleNo);
                relation.setStatus("Y");
                relation.setCreateBy(claimRuleInfo.getCreateBy());
                relation.setCreateTime(DateUtils.getNowDate());

                claimRuleRelationMapper.insertClaimRuleRelation(relation);
                List<ClaimRuleConstraint> constraintList = claimRuleInfo.getConstraintList();
                for (ClaimRuleConstraint constraint : constraintList) {
                    constraint.setConstraintNo(PubFun.createMySqlMaxNoUseCache("constraintNo",10,20));
                    constraint.setRuleNo(ruleNo);
                    constraint.setStatus("Y");
                    constraint.setCreateBy(claimRuleInfo.getCreateBy());
                    constraint.setCreateTime(DateUtils.getNowDate());
                }
                if (constraintList.size()>0) {
                    claimRuleConstraintMapper.insertList(constraintList);
                }

                ClaimRule claimRule = new ClaimRule();
                BeanUtils.copyProperties(claimRuleInfo,claimRule);

                claimRule.setPlanCode(relation.getPlanCode());
                claimRule.setRuleNo(ruleNo);
                claimRule.setStatus("Y");
                claimRule.setCreateTime(DateUtils.getNowDate());
                claimRuleMapper.insertClaimRule(claimRule);
            }
            return 1;
        }
    }

    /**
     * 修改福利规则 
     * 
     * @param claimRule 福利规则 
     * @return 结果
     */
    @Override
    public int updateClaimRule(ClaimRule claimRule)
    {
        claimRule.setUpdateTime(DateUtils.getNowDate());
        return claimRuleMapper.updateClaimRule(claimRule);
    }

    @Override
    public int updateClaimRuleInfo(ClaimRuleInfo claimRuleInfo) {

        String[] ruleNos = new String[1];
        ruleNos[0] = claimRuleInfo.getRuleNo();
        deleteClaimRuleInfoByIds(ruleNos);


        insertClaimRuleInfo(claimRuleInfo);

        return 1;
    }

    /**
     * 批量删除福利规则 
     * 
     * @param ruleNos 需要删除的福利规则 ID
     * @return 结果
     */
    @Override
    public int deleteClaimRuleByIds(String[] ruleNos)
    {
        return claimRuleMapper.deleteClaimRuleByIds(ruleNos);
    }


    @Override
    public int deleteClaimRuleInfoByIds(String[] ruleNos) {
        //更新规则表
        updateNRuleByIds(ruleNos);
        for (String ruleNo : ruleNos) {
            //更新规则条件表
            ClaimRuleConstraint constraintQuery = new ClaimRuleConstraint();
            constraintQuery.setRuleNo(ruleNo);
            List<ClaimRuleConstraint> constraintList = claimRuleConstraintMapper.selectClaimRuleConstraintList(constraintQuery);
            String[] constraintNos = new String[constraintList.size()];
            int countConstraint = 0;
            for( int i = 0; i < constraintList.size(); i++ ) {
                constraintNos[countConstraint] = constraintList.get(i).getConstraintNo();
                countConstraint++;
            }
            if (constraintNos.length>0) {
                updateNConstraintByIds(constraintNos);
            }

            //更新规则关联对象表
            ClaimRuleRelation relationQurey = new ClaimRuleRelation();
            relationQurey.setRuleNo(ruleNo);
            List<ClaimRuleRelation> relationList = claimRuleRelationMapper.selectClaimRuleRelationList(relationQurey);
            String[] relationNos = new String[relationList.size()];
            int countRelation = 0;
            for( int i = 0; i < relationList.size(); i++ ) {
                relationNos[countRelation] = relationList.get(i).getRelationNo();
                countRelation++;
            }
            if (relationNos.length>0) {
                updateNRelationByIds(relationNos);
            }
        }
        return 1;
    }

    public int updateNRuleByIds(String[] ruleNos) {
        return claimRuleMapper.deleteByUpdateN(ruleNos);
    }
    public int updateNConstraintByIds(String[] constraintNos) {
        return claimRuleConstraintMapper.deleteByUpdateN(constraintNos);
    }
    public int updateNRelationByIds(String[] relationNos) {
        return claimRuleRelationMapper.deleteByUpdateN(relationNos);
    }
    /**
     * 删除福利规则 信息
     * 
     * @param ruleNo 福利规则 ID
     * @return 结果
     */
    @Override
    public int deleteClaimRuleById(String ruleNo)
    {
        return claimRuleMapper.deleteClaimRuleById(ruleNo);
    }
}
