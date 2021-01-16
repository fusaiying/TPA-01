package com.paic.ehis.system.service.impl;

import java.util.List;

import com.paic.ehis.system.domain.BaseIssuingcompany;
import com.paic.ehis.system.domain.BaseIssuingcompanyInvoice;
import com.paic.ehis.system.domain.dto.IssuingAndCompanyDTO;
import com.paic.ehis.system.mapper.BaseIssuingcompanyInvoiceMapper;
import com.paic.ehis.system.mapper.BaseIssuingcompanyMapper;
import com.paic.ehis.system.service.IBaseIssuingcompanyService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.system.domain.vo.IssuingCompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 出单公司信息 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIssuingcompanyServiceImpl implements IBaseIssuingcompanyService
{
    @Autowired
    private BaseIssuingcompanyMapper baseIssuingcompanyMapper;

    @Autowired
    private BaseIssuingcompanyInvoiceMapper baseIssuingcompanyInvoiceMapper;

    /**
     * 查询出单公司信息 
     * 
     * @param companyCode 出单公司信息 ID
     * @return 出单公司信息 
     */
    @Override
    public BaseIssuingcompany selectBaseIssuingcompanyById(String companyCode)
    {
        return baseIssuingcompanyMapper.selectBaseIssuingcompanyById(companyCode);
    }

    /**
     * 查询出单公司信息 列表
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 出单公司信息 
     */
    @Override
    public List<BaseIssuingcompany> selectBaseIssuingcompanyList(BaseIssuingcompany baseIssuingcompany)
    {
        return baseIssuingcompanyMapper.selectBaseIssuingcompanyList(baseIssuingcompany);
    }

    /**
     * 新增出单公司信息 
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 结果
     */
    @Override
    public int insertBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany)
    {
        //传入数据：出单公司名称companyname、出单公司简写名称simplename
        baseIssuingcompany.setStatus("Y");//状态
        baseIssuingcompany.setCreateBy(SecurityUtils.getUsername());
        baseIssuingcompany.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompany.setUpdateBy(SecurityUtils.getUsername());
        baseIssuingcompany.setUpdateTime(DateUtils.getNowDate());
        return baseIssuingcompanyMapper.insertBaseIssuingcompany(baseIssuingcompany);
    }

    /**
     * 修改出单公司信息 
     * 
     * @param baseIssuingcompany 出单公司信息 
     * @return 结果
     */
    @Override
    public int updateBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany)
    {
        baseIssuingcompany.setUpdateBy(SecurityUtils.getUsername());
        baseIssuingcompany.setUpdateTime(DateUtils.getNowDate());
        return baseIssuingcompanyMapper.updateBaseIssuingcompany(baseIssuingcompany);
    }

    /**
     * 批量删除出单公司信息 
     * 
     * @param companyCodes 需要删除的出单公司信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyByIds(String[] companyCodes)
    {
        return baseIssuingcompanyMapper.deleteBaseIssuingcompanyByIds(companyCodes);
    }

    /**
     * 删除出单公司信息 信息
     * 
     * @param companyCode 出单公司信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyById(String companyCode)
    {
        return baseIssuingcompanyMapper.deleteBaseIssuingcompanyById(companyCode);
    }

    //新增
    @Override
    public IssuingCompanyVo insertBaseIssuingcompanyTwo(IssuingAndCompanyDTO issuingAndCompanyDTO){

        String str = "C"+PubFun.createMySqlMaxNoUseCache("FILINGCODE",10,8);

        BaseIssuingcompanyInvoice baseIssuingcompanyInvoice = issuingAndCompanyDTO.getBaseIssuingcompanyInvoice();//出单开票
        BaseIssuingcompany baseIssuingcompany = issuingAndCompanyDTO.getBaseIssuingcompany();//出单

        IssuingCompanyVo issuingCompanyVo = new IssuingCompanyVo();

        baseIssuingcompanyInvoice.setCompanycode(str);
        baseIssuingcompanyInvoice.setStatus("Y");
        baseIssuingcompanyInvoice.setCreateBy(SecurityUtils.getUsername());
        baseIssuingcompanyInvoice.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompanyInvoice.setUpdateBy(SecurityUtils.getUsername());
        baseIssuingcompanyInvoice.setUpdateTime(DateUtils.getNowDate());

        baseIssuingcompany.setCompanycode(str);
        baseIssuingcompany.setStatus("Y");
        baseIssuingcompany.setCreateBy(SecurityUtils.getUsername());
        baseIssuingcompany.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompany.setUpdateBy(SecurityUtils.getUsername());
        baseIssuingcompany.setUpdateTime(DateUtils.getNowDate());

        issuingCompanyVo.setCompanycode(str);

        issuingCompanyVo.setSimplename(baseIssuingcompany.getSimplename());
        issuingCompanyVo.setCompanyname(baseIssuingcompany.getCompanyname());

        if (baseIssuingcompanyInvoice.getInvoicename() != null && !baseIssuingcompanyInvoice.getInvoicename().equals("")){//有值
            baseIssuingcompanyInvoice.setCompanycode(str);
            baseIssuingcompanyInvoiceMapper.insertBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
        }

        baseIssuingcompanyMapper.insertBaseIssuingcompany(baseIssuingcompany);//新增出单公司信息

        return issuingCompanyVo;
    }
}
