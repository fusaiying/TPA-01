package com.paic.ehis.finance.mapper;


import com.paic.ehis.finance.domain.BaseIssuingcompanyRiskrela;
import com.paic.ehis.finance.domain.BaseIssuingcompanyRule;
import com.paic.ehis.finance.domain.dto.TpaSettleDTO;

import java.util.List;

/**
 * 出单公司产品关联 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface BaseIssuingcompanyRiskrelaMapper 
{
    /**
     * 查询出单公司产品关联 
     * 
     * @param companyCode 出单公司产品关联 ID
     * @return 出单公司产品关联 
     */
    public BaseIssuingcompanyRiskrela selectBaseIssuingcompanyRiskrelaById(String companyCode);

    /**
     * 查询出单公司产品关联 列表
     * 
     * @param baseIssuingcompanyRiskrela 出单公司产品关联 
     * @return 出单公司产品关联 集合
     */
    public List<BaseIssuingcompanyRiskrela> selectBaseIssuingcompanyRiskrelaList(BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela);

    /**
     * 新增出单公司产品关联 
     * 
     * @param baseIssuingcompanyRiskrela 出单公司产品关联 
     * @return 结果
     */
    public int insertBaseIssuingcompanyRiskrela(BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela);

    /**
     * 修改出单公司产品关联 
     * 
     * @param baseIssuingcompanyRiskrela 出单公司产品关联 
     * @return 结果
     */
    public int updateBaseIssuingcompanyRiskrela(BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela);

    /**
     * 删除出单公司产品关联 
     * 
     * @param companyCode 出单公司产品关联 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyRiskrelaById(String companyCode);

    /**
     * 批量删除出单公司产品关联 
     * 
     * @param companyCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyRiskrelaByIds(String[] companyCodes);

    /**
     *
     * @param companyCode
     * @return
     */
    public BaseIssuingcompanyRiskrela selectBaseIssuingcompanyRiskrelaByIdOne(String companyCode);

    /**
     *
     * @param companyCode
     * @return
     */
    public List<BaseIssuingcompanyRiskrela> selectBaseIssuingcompanyRiskrelaByIdTwo(String companyCode);

    /**
     * 查询出单公司产品关联 列表
     *
     * @param tpaSettleDTO 出单公司产品关联
     * @return 出单公司产品关联 集合
     */
    public List<BaseIssuingcompanyRule> selectCompanyRiskrelaRiskByTpa(TpaSettleDTO tpaSettleDTO);

}
