package com.paic.ehis.system.service;

import java.util.List;
import com.paic.ehis.system.domain.HmpBenefitRuleProd;

/**
 * 分配内容Service接口
 * 
 * @author sino
 * @date 2020-11-23
 */
public interface IHmpBenefitRuleProdService 
{
    /**
     * 查询分配内容
     * 
     * @param rulecode 分配内容ID
     * @return 分配内容
     */
    public HmpBenefitRuleProd selectHmpBenefitRuleProdById(String rulecode);

    /**
     * 查询分配内容列表
     * 
     * @param hmpBenefitRuleProd 分配内容
     * @return 分配内容集合
     */
    public List<HmpBenefitRuleProd> selectHmpBenefitRuleProdList(HmpBenefitRuleProd hmpBenefitRuleProd);

    /**
     * 新增分配内容
     * 
     * @param hmpBenefitRuleProd 分配内容
     * @return 结果
     */
    public int insertHmpBenefitRuleProd(HmpBenefitRuleProd hmpBenefitRuleProd);

    /**
     * 修改分配内容
     * 
     * @param hmpBenefitRuleProd 分配内容
     * @return 结果
     */
    public int updateHmpBenefitRuleProd(HmpBenefitRuleProd hmpBenefitRuleProd);

    /**
     * 批量删除分配内容
     * 
     * @param rulecodes 需要删除的分配内容ID
     * @return 结果
     */
    public int deleteHmpBenefitRuleProdByIds(String[] rulecodes);

    /**
     * 删除分配内容信息
     * 
     * @param rulecode 分配内容ID
     * @return 结果
     */
    public int deleteHmpBenefitRuleProdById(String rulecode);
}
