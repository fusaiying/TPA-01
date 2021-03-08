package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseIssuingcompany;

import java.util.List;

/**
 * 出单公司信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface BaseIssuingcompanyMapper 
{
    /**
     * 查询出单公司信息 
     * 
     * @param companyCode 出单公司信息 ID
     * @return 出单公司信息 
     */
    public BaseIssuingcompany selectBaseIssuingcompanyById(String companyCode);

    /**
     * 查询出单公司信息 列表
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 出单公司信息 集合
     */
    public List<BaseIssuingcompany> selectBaseIssuingcompanyList(BaseIssuingcompany baseIssuingcompany);

    /**
     * 新增出单公司信息 
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 结果
     */
    public int insertBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany);

    /**
     * 修改出单公司信息 
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 结果
     */
    public int updateBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany);

    /**
     * 删除出单公司信息 
     * 
     * @param companyCode 出单公司信息 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyById(String companyCode);

    /**
     * 批量删除出单公司信息 
     * 
     * @param companyCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyByIds(String[] companyCodes);

    /**
     * 出单公司简称校验
     * @param simplename
     * @return
     */
    public List<BaseIssuingcompany> selectBaseIssuingcompanyBySimplename(String simplename);

    /**
     * 出单公司名称校验
     * @param simplename
     * @return
     */
    public List<BaseIssuingcompany> selectBaseIssuingcompanyByCompanyname(String simplename);
}
