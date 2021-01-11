package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.BaseIssuingcompanyInvoice;
import com.paic.ehis.system.mapper.BaseIssuingcompanyInvoiceMapper;
import com.paic.ehis.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paic.ehis.system.service.IBaseIssuingcompanyInvoiceService;

/**
 * 出单公司开票信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIssuingcompanyInvoiceServiceImpl implements IBaseIssuingcompanyInvoiceService 
{
    @Autowired
    private BaseIssuingcompanyInvoiceMapper baseIssuingcompanyInvoiceMapper;

    /**
     * 查询出单公司开票信息 
     * 
     * @param companyCode 出单公司开票信息 ID
     * @return 出单公司开票信息 
     */
    @Override
    public BaseIssuingcompanyInvoice selectBaseIssuingcompanyInvoiceById(String companyCode)
    {
        return baseIssuingcompanyInvoiceMapper.selectBaseIssuingcompanyInvoiceById(companyCode);
    }

    /**
     * 查询出单公司开票信息 列表
     * 
     * @param baseIssuingcompanyInvoice 出单公司开票信息 
     * @return 出单公司开票信息 
     */
    @Override
    public List<BaseIssuingcompanyInvoice> selectBaseIssuingcompanyInvoiceList(BaseIssuingcompanyInvoice baseIssuingcompanyInvoice)
    {
        return baseIssuingcompanyInvoiceMapper.selectBaseIssuingcompanyInvoiceList(baseIssuingcompanyInvoice);
    }

    /**
     * 新增出单公司开票信息 
     * 
     * @param baseIssuingcompanyInvoice 出单公司开票信息 
     * @return 结果
     */
    @Override
    public int insertBaseIssuingcompanyInvoice(BaseIssuingcompanyInvoice baseIssuingcompanyInvoice)
    {
        baseIssuingcompanyInvoice.setCreateTime(DateUtils.getNowDate());
        return baseIssuingcompanyInvoiceMapper.insertBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
    }

    /**
     * 修改出单公司开票信息 
     * 
     * @param baseIssuingcompanyInvoice 出单公司开票信息 
     * @return 结果
     */
    @Override
    public int updateBaseIssuingcompanyInvoice(BaseIssuingcompanyInvoice baseIssuingcompanyInvoice)
    {
        baseIssuingcompanyInvoice.setUpdateTime(DateUtils.getNowDate());
        return baseIssuingcompanyInvoiceMapper.updateBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
    }

    /**
     * 批量删除出单公司开票信息 
     * 
     * @param companyCodes 需要删除的出单公司开票信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyInvoiceByIds(String[] companyCodes)
    {
        return baseIssuingcompanyInvoiceMapper.deleteBaseIssuingcompanyInvoiceByIds(companyCodes);
    }

    /**
     * 删除出单公司开票信息 信息
     * 
     * @param companyCode 出单公司开票信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyInvoiceById(String companyCode)
    {
        return baseIssuingcompanyInvoiceMapper.deleteBaseIssuingcompanyInvoiceById(companyCode);
    }
}
