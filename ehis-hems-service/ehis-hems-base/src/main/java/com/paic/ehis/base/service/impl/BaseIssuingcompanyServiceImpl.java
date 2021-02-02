package com.paic.ehis.base.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.base.domain.BaseIssuingcompany;
import com.paic.ehis.base.domain.BaseIssuingcompanyInvoice;
import com.paic.ehis.base.domain.BaseIssuingcompanyRiskrela;
import com.paic.ehis.base.domain.ClaimProduct;
import com.paic.ehis.base.domain.dto.IssuingAndCompanyDTO;
import com.paic.ehis.base.domain.vo.IssuingBaseVO;
import com.paic.ehis.base.domain.vo.IssuingCompanyVo;
import com.paic.ehis.base.mapper.BaseIssuingcompanyInvoiceMapper;
import com.paic.ehis.base.mapper.BaseIssuingcompanyMapper;
import com.paic.ehis.base.mapper.BaseIssuingcompanyRiskrelaMapper;
import com.paic.ehis.base.mapper.ClaimProductMapper;
import com.paic.ehis.base.service.IBaseIssuingcompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 出单公司信息 Service业务层处理
 *
 * @author sino
 * @date 2021-01-05
 */
@Service
public class BaseIssuingcompanyServiceImpl implements IBaseIssuingcompanyService {
    @Autowired
    private BaseIssuingcompanyMapper baseIssuingcompanyMapper;

    @Autowired
    private BaseIssuingcompanyInvoiceMapper baseIssuingcompanyInvoiceMapper;

    @Autowired
    private BaseIssuingcompanyRiskrelaMapper baseIssuingcompanyRiskrelaMapper;

    @Autowired
    private ClaimProductMapper claimProductMapper;
    @Autowired
    private SecurityUtils securityUtils;

    /**
     * 查询出单公司信息
     *
     * @param companyCode 出单公司信息 ID
     * @return 出单公司信息
     */
    @Override
    public IssuingBaseVO selectBaseIssuingcompanyById(String companyCode) {
        //查信息出单公司
        IssuingBaseVO issuingBaseVO = new IssuingBaseVO();
        String join =null;
                BaseIssuingcompany baseIssuingcompany = baseIssuingcompanyMapper.selectBaseIssuingcompanyById(companyCode);
        if (baseIssuingcompany !=null) {
            //查关联表
            List<String> objectsOne = new ArrayList<>();
            List<BaseIssuingcompanyRiskrela> baseIssuingcompanyRiskrelas = baseIssuingcompanyRiskrelaMapper.selectBaseIssuingcompanyRiskrelaByIdTwo(companyCode);
            if (baseIssuingcompanyRiskrelas.size() != 0) {//不为空
                for (BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrelasOne : baseIssuingcompanyRiskrelas) {
                    //根据产品编码risk_code查产品名
                    ClaimProduct claimProduct = claimProductMapper.selectClaimProductByIdOne(baseIssuingcompanyRiskrelasOne.getRiskCode());
                    objectsOne.add(claimProduct.getRiskName());
                }
                join = String.join(",", objectsOne);//拼接
                //完成返回前端赋值
            }
        }
        issuingBaseVO.setRiskName(join);
        issuingBaseVO.setCompanycode(baseIssuingcompany.getCompanycode());
        issuingBaseVO.setCompanyname(baseIssuingcompany.getCompanyname());
        issuingBaseVO.setSimplename(baseIssuingcompany.getSimplename());
        issuingBaseVO.setStatus(baseIssuingcompany.getStatus());

        return issuingBaseVO;
    }

    /**
     * 查询出单公司信息 列表
     *
     * @param baseIssuingcompany 出单公司信息
     * @return 出单公司信息
     */
    @Override
    public List<BaseIssuingcompany> selectBaseIssuingcompanyList(BaseIssuingcompany baseIssuingcompany) {
        return baseIssuingcompanyMapper.selectBaseIssuingcompanyList(baseIssuingcompany);
    }

    /**
     * 新增出单公司信息
     *
     * @param baseIssuingcompany 出单公司信息
     * @return 结果
     */
    @Override
    public int insertBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany) {
        //传入数据：出单公司名称companyname、出单公司简写名称simplename
        baseIssuingcompany.setStatus("Y");//状态
        baseIssuingcompany.setCreateBy(securityUtils.getUsername());
        baseIssuingcompany.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompany.setUpdateBy(securityUtils.getUsername());
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
    public int updateBaseIssuingcompany(BaseIssuingcompany baseIssuingcompany) {
        baseIssuingcompany.setUpdateBy(securityUtils.getUsername());
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
    public int deleteBaseIssuingcompanyByIds(String[] companyCodes) {
        return baseIssuingcompanyMapper.deleteBaseIssuingcompanyByIds(companyCodes);
    }

    /**
     * 删除出单公司信息 信息
     *
     * @param companyCode 出单公司信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseIssuingcompanyById(String companyCode) {
        return baseIssuingcompanyMapper.deleteBaseIssuingcompanyById(companyCode);
    }

    //新增+修改
    @Override
    public IssuingCompanyVo insertBaseIssuingcompanyRest(IssuingAndCompanyDTO issuingAndCompanyDTO) {
        BaseIssuingcompanyInvoice baseIssuingcompanyInvoice = issuingAndCompanyDTO.getBaseIssuingcompanyInvoice();//出单公司开票
        BaseIssuingcompany baseIssuingcompany = issuingAndCompanyDTO.getBaseIssuingcompany();//出单公司

        baseIssuingcompany.setStatus("Y");
        baseIssuingcompany.setCreateBy(securityUtils.getUsername());
        baseIssuingcompany.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompany.setUpdateBy(securityUtils.getUsername());
        baseIssuingcompany.setUpdateTime(DateUtils.getNowDate());

        baseIssuingcompanyInvoice.setCompanycode(baseIssuingcompany.getCompanycode());
        baseIssuingcompanyInvoice.setStatus("Y");
        baseIssuingcompanyInvoice.setCreateBy(securityUtils.getUsername());
        baseIssuingcompanyInvoice.setCreateTime(DateUtils.getNowDate());
        baseIssuingcompanyInvoice.setUpdateBy(securityUtils.getUsername());
        baseIssuingcompanyInvoice.setUpdateTime(DateUtils.getNowDate());

        IssuingCompanyVo issuingCompanyVo = new IssuingCompanyVo();
        String str = null;

        //判断是否是第一次提交出单公司
        if (baseIssuingcompany.getCompanycode() == null || baseIssuingcompany.getCompanycode() == "") {//第一次提交出单公司
            str = "C" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 8);
            baseIssuingcompany.setCompanycode(str);
            baseIssuingcompanyMapper.insertBaseIssuingcompany(baseIssuingcompany);
        } else {//不是第一次提交出单公司

            baseIssuingcompanyMapper.updateBaseIssuingcompany(baseIssuingcompany);
        }

        if (baseIssuingcompanyInvoice.getInvoicename() != "" && baseIssuingcompanyInvoice.getInvoicename() != null) {//值不为空再操作
            baseIssuingcompanyInvoice.setCompanycode(baseIssuingcompany.getCompanycode());
            BaseIssuingcompanyInvoice baseIssuingcompanyInvoice1 = baseIssuingcompanyInvoiceMapper.selectBaseIssuingcompanyInvoiceById(baseIssuingcompanyInvoice.getCompanycode());
            //判断是否是第一次提交开票信息-不是第一次
            if (baseIssuingcompanyInvoice1 == null) {
                baseIssuingcompanyInvoiceMapper.insertBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
            } else {
                baseIssuingcompanyInvoiceMapper.updateBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
            }
        }

        issuingCompanyVo.setCompanycode(baseIssuingcompany.getCompanycode());
        issuingCompanyVo.setSimplename(baseIssuingcompany.getSimplename());
        issuingCompanyVo.setCompanyname(baseIssuingcompany.getCompanyname());

        //关联产品名称
        //根据出单公司编码查产品编码risk_code
        List<BaseIssuingcompanyRiskrela> baseIssuingcompanyRiskrelas = baseIssuingcompanyRiskrelaMapper.selectBaseIssuingcompanyRiskrelaByIdTwo(baseIssuingcompany.getCompanycode());
        if (null != baseIssuingcompanyRiskrelas || baseIssuingcompanyRiskrelas.size() != 0) {//不为空
            List<String> objects = new ArrayList<>();
            for (BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrelasOne : baseIssuingcompanyRiskrelas) {
                //根据产品编码risk_code查产品名
                ClaimProduct claimProduct = claimProductMapper.selectClaimProductById(baseIssuingcompanyRiskrelasOne.getRiskCode());
                objects.add(claimProduct.getRiskName());
            }
            String join = String.join(",", objects);
            issuingCompanyVo.setRiskName(join);
        }

        return issuingCompanyVo;
    }

}
