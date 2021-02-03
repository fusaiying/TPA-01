package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseIssuingcompanyInvoice;
import com.paic.ehis.base.mapper.BaseIssuingcompanyInvoiceMapper;
import com.paic.ehis.base.service.IBaseIssuingcompanyInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        baseIssuingcompanyInvoice.setStatus("Y");
        baseIssuingcompanyInvoice.setCreateBy(SecurityUtils.getUsername());
        baseIssuingcompanyInvoice.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompanyInvoice.setUpdateBy(SecurityUtils.getUsername());
        baseIssuingcompanyInvoice.setUpdateTime(DateUtils.getNowDate());
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
        baseIssuingcompanyInvoice.setUpdateBy(SecurityUtils.getUsername());
        baseIssuingcompanyInvoice.setUpdateTime(DateUtils.getNowDate());
        String companycode = baseIssuingcompanyInvoice.getCompanycode();
        BaseIssuingcompanyInvoice baseIssuingcompanyInvoice1 = baseIssuingcompanyInvoiceMapper.selectBaseIssuingcompanyInvoiceById(companycode);
        if (baseIssuingcompanyInvoice1 != null){//不是第一次提交
            return baseIssuingcompanyInvoiceMapper.updateBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
        }//第一次提交的话
        baseIssuingcompanyInvoice.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompanyInvoice.setCreateBy(SecurityUtils.getUsername());
        baseIssuingcompanyInvoice.setStatus("Y");
        return baseIssuingcompanyInvoiceMapper.insertBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
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
