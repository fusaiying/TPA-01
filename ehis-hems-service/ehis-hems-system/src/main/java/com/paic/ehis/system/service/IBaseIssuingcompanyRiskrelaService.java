package com.paic.ehis.system.service;

import java.util.List;
import com.paic.ehis.system.domain.BaseIssuingcompanyRiskrela;

/**
 * 出单公司产品关联 Service接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface IBaseIssuingcompanyRiskrelaService 
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
     * 批量删除出单公司产品关联 
     * 
     * @param companyCodes 需要删除的出单公司产品关联 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyRiskrelaByIds(String[] companyCodes);

    /**
     * 删除出单公司产品关联 信息
     * 
     * @param companyCode 出单公司产品关联 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyRiskrelaById(String companyCode);
}
