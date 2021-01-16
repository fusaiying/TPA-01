package com.paic.ehis.system.service.impl;

import com.paic.ehis.system.domain.*;
import com.paic.ehis.system.domain.dto.ClaimProductDTO;
import com.paic.ehis.system.domain.dto.ProcessedProductDTO;
import com.paic.ehis.system.domain.dto.UpdateClaimProductDTO;
import com.paic.ehis.system.mapper.*;
import com.paic.ehis.system.service.IClaimProductService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.system.domain.*;
import com.paic.ehis.system.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * 产品信息Service业务层处理
 * 
 * @author sino
 * @date 2021-01-14
 */
@Service
public class ClaimProductServiceImpl implements IClaimProductService
{
    @Autowired
    private ClaimProductMapper claimProductMapper;

    @Autowired
    private BaseIssuingcompanyRiskrelaMapper baseIssuingcompanyRiskrelaMapper;

    @Autowired
    private BaseIssuingcompanyMapper baseIssuingcompanyMapper;

    @Autowired
    private ClaimRuleMapper claimRuleMapper;

    @Autowired
    private ClaimProductPlanMapper claimProductPlanMapper;

    @Autowired
    private ClaimProductDutyMapper claimProductDutyMapper;

    @Autowired
    private ClaimProductDutyDetailMapper claimProductDutyDetailMapper;

    @Autowired
    private ClaimProductFeeitemMapper claimProductFeeitemMapper;

    @Autowired
    private ClaimProductTaskLogMapper claimProductTaskLogMapper;


    /**
     * 查询产品信息
     * 
     * @param riskCode 产品信息ID
     * @return 产品信息
     */
    @Override
    public ClaimProduct selectClaimProductById(String riskCode)
    {
        return claimProductMapper.selectClaimProductById(riskCode);
    }

    /**
     * 查询福利对象
     *
     * @param riskCode 产品信息ID
     * @return 产品信息
     */
    @Override
    public List<ClaimProductPlanVO> selectProductById(String riskCode) {
        List<ClaimProductPlanVO> claimProductPlanList = claimProductMapper.getAll(riskCode);
        return claimProductPlanList;
    }

    @Override
    public ClaimProductInfo selectClaimProductInfoById(String riskCode) {
        ClaimProductInfo claimProductInfo = new ClaimProductInfo();

        //查询产品表
        ClaimProduct claimProduct = claimProductMapper.selectClaimProductById(riskCode);
        BeanUtils.copyProperties(claimProduct,claimProductInfo);

        //查询出单公司
        BaseIssuingcompanyRiskrela baseIssuingcompanyRiskrela = new BaseIssuingcompanyRiskrela();
        baseIssuingcompanyRiskrela.setRiskCode(riskCode);
        List<BaseIssuingcompanyRiskrela> companyList = baseIssuingcompanyRiskrelaMapper.selectBaseIssuingcompanyRiskrelaList(baseIssuingcompanyRiskrela);
        StringBuilder companyNameBuilder = new StringBuilder();
        for (BaseIssuingcompanyRiskrela company : companyList) {
            BaseIssuingcompany issuingcompany = baseIssuingcompanyMapper.selectBaseIssuingcompanyById(company.getCompanyCode());
            companyNameBuilder.append(issuingcompany.getCompanyname());
            companyNameBuilder.append(",");
        }
        if (companyNameBuilder.length() > 0) {
            companyNameBuilder.delete(companyNameBuilder.length()-1,companyNameBuilder.length());
        }
        claimProductInfo.setCompany(companyNameBuilder.toString());


        return claimProductInfo;
    }

    /**
     * 查询产品信息列表
     * 
     * @param claimProduct 产品信息
     * @return 产品信息
     */
    @Override
    public List<ClaimProduct> selectClaimProductList(ClaimProduct claimProduct)
    {
        claimProduct.setStatus("Y");
        return claimProductMapper.selectClaimProductList(claimProduct);
    }

    /**
     * 查询产品信息公共池 列表
     *
     * @param claimProduct 产品信息
     * @return 产品信息
     */
    @Override
    public List<ClaimProduct> selectProductPublicList(ClaimProduct claimProduct)
    {
        claimProduct.setStatus("Y");
        claimProduct.setRiskStatus("01");
        return claimProductMapper.selectClaimProductList(claimProduct);
    }
    /**查询任务改派公共池*/
    @Override
    public List<ClaimProductDTO> selectClaimProduct(ClaimProductDTO claimProductDTO)
    {
        claimProductDTO.setStatus("Y");

        return claimProductMapper.selectClaimProduct(claimProductDTO);
    }

    /**修改任务改派操作人*/
    @Override
    public void updateClaimProduct(UpdateClaimProductDTO updateClaimProductDTO)
    {
        List<String> riskCode1 = updateClaimProductDTO.getRiskCode();
        ClaimProduct claimProduct =new ClaimProduct();
        for (String risk:riskCode1){
            claimProduct.setRiskCode(risk);
            claimProduct.setUpdateBy(updateClaimProductDTO.getUpdateBy());
            claimProductMapper.updateClaimProduct(claimProduct);
        }
    }

    /**
     * 查询待处理产品信息 列表
     *
     * @param claimProduct 产品信息
     * @return 产品信息
     */
    @Override
    public List<ClaimProduct> selectUntreatedProductList(ClaimProduct claimProduct)
    {
        claimProduct.setStatus("Y");
        claimProduct.setRiskStatus("02");
        claimProduct.setUpdateBy(SecurityUtils.getUsername());
        return claimProductMapper.selectClaimProductList(claimProduct);
    }

    /**
     * 查询已处理产品信息 列表
     *
     * @param processedProductDTO 产品信息
     * @return 产品信息
     */
    @Override
    public List<ClaimProduct> selectProcessedProductList(ProcessedProductDTO processedProductDTO)
    {
        processedProductDTO.setRiskLogStatus("02");
        processedProductDTO.setIsHistory("Y");
        processedProductDTO.setStatus("Y");
        processedProductDTO.setUpdateBy(SecurityUtils.getUsername());
        return claimProductMapper.selectClaimProductProcessedList(processedProductDTO);
    }

    /**
     * 新增产品信息
     * 
     * @param claimProduct 产品信息
     * @return 结果
     */
    @Override
    public int insertClaimProduct(ClaimProduct claimProduct)
    {
        claimProduct.setCreateTime(DateUtils.getNowDate());
        return claimProductMapper.insertClaimProduct(claimProduct);
    }

    /**
     * 修改产品信息
     * 
     * @param claimProduct 产品信息
     * @return 结果
     */
    @Override
    public int updateClaimProduct(ClaimProduct claimProduct)
    {
        claimProduct.setUpdateTime(DateUtils.getNowDate());
        return claimProductMapper.updateClaimProduct(claimProduct);
    }

    /**
     * 重设产品信息
     *
     * @param riskCode 产品信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateClaimProductReset(String riskCode) {
        ClaimProductTaskLog claimProductTaskLog = new ClaimProductTaskLog();
        claimProductTaskLog.setRiskCode(riskCode);
        claimProductTaskLog.setIsHistory("N");
        ClaimProductTaskLog productTaskLog = claimProductTaskLogMapper.selectClaimProductTaskLogByCondition(claimProductTaskLog);
        productTaskLog.setIsHistory("Y");
        productTaskLog.setUpdateBy(SecurityUtils.getUsername());
        productTaskLog.setUpdateTime(DateUtils.getNowDate());
        claimProductTaskLogMapper.updateClaimProductTaskLog(productTaskLog);

        claimProductTaskLog.setRiskLogNo(PubFun.createMySqlMaxNoUseCache("riskLogNo",10,20));
        claimProductTaskLog.setIsHistory("N");
        claimProductTaskLog.setRiskStatus("02");
        claimProductTaskLog.setCreateTime(DateUtils.getNowDate());
        claimProductTaskLog.setCreateBy(SecurityUtils.getUsername());
        claimProductTaskLog.setStatus("Y");
        claimProductTaskLogMapper.insertClaimProductTaskLog(claimProductTaskLog);

        ClaimProduct claimProduct = new ClaimProduct();
        claimProduct.setRiskCode(riskCode);
        claimProduct.setRiskStatus("02");
        claimProduct.setUpdateBy(SecurityUtils.getUsername());
        claimProduct.setUpdateTime(DateUtils.getNowDate());
        return claimProductMapper.updateClaimProductReset(claimProduct);
    }

    /**
     * 获取产品信息至个人池
     *
     * @param riskCodes 产品信息
     * @return 结果
     */
    @Transactional
    @Override
    public int obtainClaimProduct(String[] riskCodes) {
        ClaimProduct claimProduct = new ClaimProduct();
        int i = 0;
        for (String riskCode : riskCodes) {
            claimProduct.setRiskStatus("02");
            claimProduct.setRiskCode(riskCode);
            claimProduct.setUpdateBy(SecurityUtils.getUsername());
            claimProduct.setUpdateTime(DateUtils.getNowDate());

            ClaimProductTaskLog claimProductTaskLog = new ClaimProductTaskLog();
            claimProductTaskLog.setRiskCode(riskCode);
            claimProductTaskLog.setIsHistory("N");
            ClaimProductTaskLog productTaskLog = claimProductTaskLogMapper.selectClaimProductTaskLogByCondition(claimProductTaskLog);
            if (StringUtils.isNotNull(productTaskLog)) {
                productTaskLog.setIsHistory("Y");
                productTaskLog.setUpdateBy(SecurityUtils.getUsername());
                productTaskLog.setUpdateTime(DateUtils.getNowDate());
                claimProductTaskLogMapper.updateClaimProductTaskLog(productTaskLog);
            }
            claimProductTaskLog.setRiskLogNo(PubFun.createMySqlMaxNoUseCache("riskLogNo",10,20));
            claimProductTaskLog.setIsHistory("N");
            claimProductTaskLog.setRiskStatus("02");
            claimProductTaskLog.setCreateTime(DateUtils.getNowDate());
            claimProductTaskLog.setCreateBy(SecurityUtils.getUsername());
            claimProductTaskLog.setStatus("Y");
            claimProductTaskLogMapper.insertClaimProductTaskLog(claimProductTaskLog);
            i = claimProductMapper.updateClaimProduct(claimProduct);
        }
        return i;
    }

    /**
     * 批量删除产品信息
     * 
     * @param riskCodes 需要删除的产品信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductByIds(String[] riskCodes)
    {
        return claimProductMapper.deleteClaimProductByIds(riskCodes);
    }

    /**
     * 删除产品信息信息
     * 
     * @param riskCode 产品信息ID
     * @return 结果
     */
    @Override
    public int deleteClaimProductById(String riskCode)
    {
        return claimProductMapper.deleteClaimProductById(riskCode);
    }

    /*****************************/
    /**数据来源-就是查询产品规则中更改者为空-且规则状态为（审核中）的数据
     * 公共池-数据查询
     * @param claimProduct
     * @return
     */
    @Override
    public List<ClaimProduct> selectClaimProductListAndPublicPool(ClaimProduct claimProduct){
        List<ClaimProduct> claimProductList = claimProductMapper.selectClaimProductListAndPublicPool(claimProduct);
        return claimProductList;
    }
//获取
    @Override
    public int updateClaimProductUpdateBy(String[] riskCodes){
        int i=0;
        for (String riskCode : riskCodes) {
            ClaimProductTaskLog claimProductTaskLogInfo = new ClaimProductTaskLog();
            claimProductTaskLogInfo.setStatus("Y");
            claimProductTaskLogInfo.setRiskStatus("03");
            claimProductTaskLogInfo.setRiskCode(riskCode);
            claimProductTaskLogInfo.setIsHistory("N");
            List<ClaimProductTaskLog> claimProductTaskLogs = claimProductTaskLogMapper.selectClaimProductTaskLogList(claimProductTaskLogInfo);
            for (ClaimProductTaskLog claimProductTaskLogs2:claimProductTaskLogs){
                ClaimProductTaskLog claimProductTaskLog = new ClaimProductTaskLog();
                claimProductTaskLog.setRiskLogNo( claimProductTaskLogs2.getRiskLogNo());
                claimProductTaskLog.setUpdateBy(SecurityUtils.getUsername());
                claimProductTaskLog.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
                i=claimProductTaskLogMapper.updateClaimProductTaskLog(claimProductTaskLog);
            }
        }
        return i;
    }

//已处理：数据来源-查询更改者为当前操作者的数据-且risk_status（产品状态）为：
    /**
     * 公共池-已处理-数据查询
     * @param claimProduct
     * @return
     */
    @Override
    public List<ClaimProduct> selectClaimProductListAndPersonalPoolFinish(ClaimProduct claimProduct){
        return claimProductMapper.selectClaimProductListAndPersonalPoolFinish(claimProduct);
    }

    //	待处理：数据来源-查询更改者为当前操作者的数据-且risk_status（产品状态）为：审核中
    /**
     * 公共池-待处理-数据查询
     * @param claimProduct
     * @return
     */
    @Override
    public List<ClaimProduct> selectClaimProductListAndPersonalPoolUnfinished(ClaimProduct claimProduct){
        claimProduct.setRiskStatus("03");//审核中
        claimProduct.setUpdateBy(SecurityUtils.getUsername());//更改者为当前操作者
        claimProduct.setStatus("Y");
        return claimProductMapper.selectClaimProductListAndPersonalPoolUnfinished(claimProduct);
    }
    /*****************************/
    //产品查询   工作池
    @Override
    public List<ClaimProductDTO> selectProductQuery(ClaimProductDTO claimProductDTO) {
        if (StringUtils.isNull(claimProductDTO.getUpdateEndTime())
                && StringUtils.isNull(claimProductDTO.getRiskCode())
                && StringUtils.isNull(claimProductDTO.getRiskName())
                && StringUtils.isNull(claimProductDTO.getSynchronizeEndTime())
                && StringUtils.isNull(claimProductDTO.getRiskStatus())
                && StringUtils.isNull(claimProductDTO.getUpdateBy())
        ) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            claimProductDTO.setSynchronizeStartTime(calendar.getTime());
            claimProductDTO.setSynchronizeEndTime(DateUtils.parseDate(DateUtils.getTime()));
           // return claimProductMapper.selectProductQuery(productDTO);
        }
        claimProductDTO.setStatus("Y");
            return claimProductMapper.selectProductQuery(claimProductDTO);
    }
}
