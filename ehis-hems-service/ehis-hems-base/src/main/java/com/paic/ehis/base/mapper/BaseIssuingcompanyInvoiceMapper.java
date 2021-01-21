package com.paic.ehis.base.mapper;

import com.paic.ehis.base.domain.BaseIssuingcompanyInvoice;

import java.util.List;

/**
 * 出单公司开票信息 Mapper接口
 * 
 * @author sino
 * @date 2021-01-05
 */
public interface BaseIssuingcompanyInvoiceMapper 
{
    /**
     * 查询出单公司开票信息 
     * 
     * @param companyCode 出单公司开票信息 ID
     * @return 出单公司开票信息 
     */
    public BaseIssuingcompanyInvoice selectBaseIssuingcompanyInvoiceById(String companyCode);

    /**
     * 查询出单公司开票信息 列表
     * 
     * @param baseIssuingcompanyInvoice 出单公司开票信息 
     * @return 出单公司开票信息 集合
     */
    public List<BaseIssuingcompanyInvoice> selectBaseIssuingcompanyInvoiceList(BaseIssuingcompanyInvoice baseIssuingcompanyInvoice);

    /**
     * 新增出单公司开票信息 
     * 
     * @param baseIssuingcompanyInvoice 出单公司开票信息 
     * @return 结果
     */
    public int insertBaseIssuingcompanyInvoice(BaseIssuingcompanyInvoice baseIssuingcompanyInvoice);

    /**
     * 修改出单公司开票信息 
     * 
     * @param baseIssuingcompanyInvoice 出单公司开票信息 
     * @return 结果
     */
    public int updateBaseIssuingcompanyInvoice(BaseIssuingcompanyInvoice baseIssuingcompanyInvoice);

    /**
     * 删除出单公司开票信息 
     * 
     * @param companyCode 出单公司开票信息 ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyInvoiceById(String companyCode);

    /**
     * 批量删除出单公司开票信息 
     * 
     * @param companyCodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseIssuingcompanyInvoiceByIds(String[] companyCodes);
}
