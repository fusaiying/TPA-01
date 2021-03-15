package com.paic.ehis.claimcal.service.impl;


import com.paic.ehis.claimcal.domain.*;
import com.paic.ehis.claimcal.domain.dto.ClaimProductDTO;
import com.paic.ehis.claimcal.domain.dto.ProcessedProductDTO;
import com.paic.ehis.claimcal.domain.dto.UpdateClaimProductDTO;
import com.paic.ehis.claimcal.mapper.*;
import com.paic.ehis.claimcal.service.IClaimProductService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;


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
        if(StringUtils.isNotEmpty(claimProductDTO.getRiskCode())
                || StringUtils.isNotEmpty(claimProductDTO.getUpdateBy())
                || StringUtils.isNotEmpty(claimProductDTO.getRiskName())
                || StringUtils.isNotEmpty(claimProductDTO.getRiskStatus())
                || StringUtils.isNotNull(claimProductDTO.getSynchronizeStartTime())
                || StringUtils.isNotNull(claimProductDTO.getSynchronizeEndTime())
                || StringUtils.isNotNull(claimProductDTO.getUpdateStartTime())
                || StringUtils.isNotNull(claimProductDTO.getUpdateEndTime())
        ){
        claimProductDTO.setStatus("Y");
        claimProductDTO.setIsHistory("N");
        List<ClaimProductDTO> claimProductDTOS1 = claimProductMapper.selectClaimProduct(claimProductDTO);
        return claimProductDTOS1;
        }
        return claimProductMapper.selectClaimProductNew(claimProductDTO);
    }

    /**修改任务改派操作人*/
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void UpdateClaimProductDTO(UpdateClaimProductDTO updateClaimProductDTO)
    {
        //获得前端传入的产品编码
        List<String> riskCode1 = updateClaimProductDTO.getRiskCode();
        ClaimProduct claimProduct =new ClaimProduct();
        ClaimProductTaskLog claimProductTaskLogNew = new ClaimProductTaskLog();
        //循环遍历，进行更改任务改派操作人
        for (String risk:riskCode1){
            //进行任务改派
            claimProduct.setRiskCode(risk);
            claimProduct.setUpdateBy(updateClaimProductDTO.getUpdateBy());
            claimProduct.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimProductMapper.updateClaimProduct(claimProduct);
            //查询产品编码对应的轨迹表
            ClaimProductTaskLog claimProductTaskLog1 = claimProductTaskLogMapper.selectClaimProductTaskLog(risk);

            BeanUtils.copyProperties(claimProductTaskLog1,claimProductTaskLogNew);
            //设置旧表
            claimProductTaskLog1.setIsHistory("Y");
            claimProductTaskLogMapper.updateClaimProductTaskLog(claimProductTaskLog1);
            //设置新表
            claimProductTaskLogNew.setRiskLogNo(PubFun.createMySqlMaxNoUseCache("riskLogNo",10,20));
            claimProductTaskLogNew.setCreateTime(DateUtils.getNowDate());
            claimProductTaskLogNew.setUpdateBy(claimProduct.getUpdateBy());
            claimProductTaskLogNew.setUpdateTime(claimProduct.getUpdateTime());
            claimProductTaskLogMapper.insertClaimProductTaskLog(claimProductTaskLogNew);
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
        processedProductDTO.setRiskStatus("'03','04'");
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
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

        claimProductTaskLog.setRiskStatus("03");
        claimProductTaskLog.setIsHistory("Y");
        ClaimProductTaskLog productResetTaskLog = claimProductTaskLogMapper.selectClaimProductTaskLogByCondition(claimProductTaskLog);
        ClaimProduct claimProduct = new ClaimProduct();
        claimProduct.setRiskCode(riskCode);
        claimProduct.setRiskStatus("02");
        claimProduct.setUpdateBy(productResetTaskLog.getCreateBy());
        claimProduct.setUpdateTime(DateUtils.getNowDate());

        ClaimProductTaskLog taskLog = new ClaimProductTaskLog();
        taskLog.setRiskLogNo(PubFun.createMySqlMaxNoUseCache("riskLogNo",10,20));
        taskLog.setRiskCode(riskCode);
        taskLog.setRiskStatus("02");
        taskLog.setIsHistory("N");
        taskLog.setCreateTime(DateUtils.getNowDate());
        taskLog.setCreateBy(SecurityUtils.getUsername());
        taskLog.setStatus("Y");
        taskLog.setUpdateBy(productResetTaskLog.getCreateBy());
        taskLog.setUpdateTime(DateUtils.getNowDate());
        claimProductTaskLogMapper.insertClaimProductTaskLog(taskLog);
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
    public ArrayList<String> obtainClaimProduct(String[] riskCodes) {
        ClaimProduct claimProduct = new ClaimProduct();
        ArrayList<String> riskCodeList = new ArrayList<>();
        int i = 0;
        for (String riskCode : riskCodes) {
            claimProduct.setRiskStatus("02");
            claimProduct.setRiskCode(riskCode);
            claimProduct.setUpdateBy(SecurityUtils.getUsername());
            claimProduct.setUpdateTime(DateUtils.getNowDate());

            ClaimProductTaskLog claimProductTaskLog = new ClaimProductTaskLog();
            claimProductTaskLog.setRiskCode(riskCode);
            claimProductTaskLog.setIsHistory("N");
            claimProductTaskLog.setRiskStatus("01");
            ClaimProductTaskLog productTaskLog = claimProductTaskLogMapper.selectClaimProductTaskLogByCondition(claimProductTaskLog);
            if (StringUtils.isNotNull(productTaskLog)) {
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
                claimProductTaskLog.setUpdateBy(claimProductTaskLog.getCreateBy());
                claimProductTaskLog.setUpdateTime(claimProductTaskLog.getCreateTime());
                claimProductTaskLogMapper.insertClaimProductTaskLog(claimProductTaskLog);
                i = claimProductMapper.updateClaimProduct(claimProduct);
            }else {
                riskCodeList.add(riskCode);
            }




        }
        return riskCodeList;
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

    /**
     * 规则审核-公共池-获取-个人池
     * @param riskCodes
     * @return
     */
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
        claimProduct.setCreateBy(SecurityUtils.getUsername());//更改者为当前操作者
        List<ClaimProduct> claimProducts = claimProductMapper.selectClaimProductListAndPersonalPoolFinish(claimProduct);
        List<ClaimProduct> claimProducts1 = new ArrayList<>();
        for (ClaimProduct claimProductsTwo : claimProducts){
            ClaimProductTaskLog claimCaseRecord = new ClaimProductTaskLog();
            claimCaseRecord.setIsHistory("Y");
            claimCaseRecord.setRiskCode(claimProductsTwo.getRiskCode());
            claimCaseRecord.setRiskStatus("03");
            claimCaseRecord.setStatus("Y");
            List<ClaimProductTaskLog> claimProductTaskLogs = claimProductTaskLogMapper.selectClaimProductTaskLogByMany(claimCaseRecord);
            if (claimProductTaskLogs!=null && !claimProductTaskLogs.isEmpty()){//不为空时
                claimProducts1.add(claimProductsTwo);
            }
        }
        return claimProducts1;
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
        claimProductDTO.setIsHistory("N");
        claimProductDTO.setStatus("Y");
        if (
                StringUtils.isNull(claimProductDTO.getSynchronizeEndTime())
                        &&StringUtils.isEmpty(claimProductDTO.getRiskStatus())
                        &&StringUtils.isEmpty(claimProductDTO.getRiskCode())
                        &&StringUtils.isEmpty(claimProductDTO.getRiskName())
                        &&StringUtils.isNull(claimProductDTO.getUpdateEndTime())
                        &&StringUtils.isEmpty(claimProductDTO.getUpdateBy())
        ) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) -30);
            claimProductDTO.setSynchronizeStartTime(calendar.getTime());
            claimProductDTO.setSynchronizeEndTime(DateUtils.parseDate(DateUtils.getTime()));
            return  claimProductMapper.selectProductQuery(claimProductDTO);
        }else{
            return claimProductMapper.selectProductQuery(claimProductDTO);

        }
    }
}
