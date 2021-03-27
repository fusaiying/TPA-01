package com.paic.ehis.claimcal.mapper;


import com.paic.ehis.claimcal.domain.ClaimRuleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 福利规则 Mapper接口
 * 
 * @author sino
 * @date 2021-01-06
 */
public interface SelectRuleInfoMapper
{
    /**
     * 查询福利规则 列表
     * 
     * @param ruleElement
     * @param elementUnit
     * @param riskCode
     * @param planCode
     * @param dutyCode
     * @param dutyDetailCode
     * @param feeitemCode
     *
     * @return 福利规则 集合
     */
    public List<ClaimRuleInfo> selectFeeitemCodeRuleList(@Param(value = "ruleElement")String ruleElement, @Param(value = "elementUnit")String elementUnit, @Param(value = "riskCode") String riskCode, @Param(value = "planCode") String planCode, @Param(value = "dutyCode") String dutyCode, @Param(value = "dutyDetailCode") String dutyDetailCode, @Param(value = "feeitemCode") String feeitemCode);

    public List<ClaimRuleInfo> selectDetailCodeRuleList(@Param(value = "ruleElement")String ruleElement, @Param(value = "elementUnit")String elementUnit, @Param(value = "riskCode") String riskCode, @Param(value = "planCode") String planCode, @Param(value = "dutyCode") String dutyCode, @Param(value = "dutyDetailCode") String dutyDetailCode);

    public List<ClaimRuleInfo> selectDutyCodeRuleList(@Param(value = "ruleElement")String ruleElement, @Param(value = "elementUnit")String elementUnit, @Param(value = "riskCode") String riskCode, @Param(value = "planCode") String planCode, @Param(value = "dutyCode") String dutyCode);

    public List<ClaimRuleInfo> selectPlanCodeRuleList(@Param(value = "ruleElement")String ruleElement, @Param(value = "elementUnit")String elementUnit, @Param(value = "riskCode") String riskCode, @Param(value = "planCode") String planCode);

}
