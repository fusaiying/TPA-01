package com.paic.ehis.finance.service.impl;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paic.ehis.common.core.enums.ClaimStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.finance.domain.*;
import com.paic.ehis.finance.domain.dto.TpaSettleDTO;
import com.paic.ehis.finance.mapper.*;
import com.paic.ehis.finance.service.IFinanceTpaSettleTaskService;
import com.paic.ehis.system.api.PolicyAndRiskService;
import com.paic.ehis.system.api.domain.ClaimCaseBillInfo;
import com.paic.ehis.system.api.domain.PolicyAndRiskRelation;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.BeanUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TPA服务费结算任务Service业务层处理
 *
 * @author sino
 * @date 2021-01-30
 */
@Service
public class FinanceTpaSettleTaskServiceImpl implements IFinanceTpaSettleTaskService
{
    @Autowired
    private FinanceTpaSettleTaskMapper financeTpaSettleTaskMapper;

    @Autowired
    private PolicyAndRiskService policyAndRiskService;

    @Autowired
    private BaseIssuingcompanyRiskrelaMapper baseIssuingcompanyRiskrelaMapper;

//    @Autowired
//    SysUserMapper sysUserMapper;

    @Autowired
    private BaseIssuingcompanyRuleMapper baseIssuingcompanyRuleMapper;

    @Autowired
    private FinanceTpaSettleDetailMapper financeTpaSettleDetailMapper;

    @Autowired
    FinanceSettleRecordMapper financeSettleRecordMapper;

    /**
     * 查询TPA服务费结算任务
     *
     * @param taskId TPA服务费结算任务ID
     * @return TPA服务费结算任务
     */
    @Override
    public FinanceTpaSettleTask selectFinanceTpaSettleTaskById(Long taskId)
    {
        return financeTpaSettleTaskMapper.selectFinanceTpaSettleTaskById(taskId);
    }

    /**
     * 查询TPA服务费结算任务列表
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return TPA服务费结算任务
     */
    @Override
    public List<FinanceTpaSettleTask> selectFinanceTpaSettleTaskList(FinanceTpaSettleTask financeTpaSettleTask)
    {
        return financeTpaSettleTaskMapper.selectFinanceTpaSettleTaskList(financeTpaSettleTask);
    }

    /**
     * 任务发起
     *
     * @param tpaSettleDTO TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    @Override
    public List<TpaSettleInfo> tpaTaskInitiated(TpaSettleDTO tpaSettleDTO) throws Exception {
        TpaSettleInfo tpaSettleInfo = new TpaSettleInfo();
        TpaSettleDetailInfo tpaSettleDetailInfo = new TpaSettleDetailInfo();
        PolicyAndRiskRelation policyAndRiskRelation = new PolicyAndRiskRelation();
        ArrayList<TpaSettleInfo> tpaSettleInfos=new ArrayList<>();
        ArrayList<TpaSettleDetailInfo> detailInfos=new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        CompanyRiskPolicy companyRiskPolicy = null;
        Date earliestDay=new Date();
        //TPA服务费及明细的新增
        FinanceTpaSettleTask financeTpaSettleTask = new FinanceTpaSettleTask();
        FinanceTpaSettleDetail financeTpaSettleDetail = new FinanceTpaSettleDetail();
        FinanceSettleRecord financeSettleRecord = new FinanceSettleRecord();
        //获取用户的所属机构,设置当前登录机构
       /* Long userId = SecurityUtils.getLoginUser().getUserId();
        SysUser sysUser = sysUserMapper.selectUserById(userId);*/
        financeTpaSettleTask.setSettleStatus("01");
        financeTpaSettleTask.setSettlementType(tpaSettleDTO.getSettlementType());
        financeTpaSettleTask.setSettleEndDate(tpaSettleDTO.getSettleEndDate());
        financeTpaSettleTask.setCompanyCode(tpaSettleDTO.getCompanyCode());
        financeTpaSettleTask.setStatus("Y");
//        financeTpaSettleTask.setDeptCode( sysUser.getDeptId().toString());
        financeTpaSettleTask.setDeptCode("unknown");
        financeTpaSettleTask.setCreateBy(SecurityUtils.getUsername());
        financeTpaSettleTask.setCreateTime(DateUtils.getNowDate());
        financeTpaSettleTask.setUpdateBy(SecurityUtils.getUsername());
        financeTpaSettleTask.setUpdateTime(DateUtils.getNowDate());

        financeTpaSettleDetail.setStatus("Y");
//        financeTpaSettleDetail.setDeptCode( sysUser.getDeptId().toString());
        financeTpaSettleDetail.setDeptCode("unknown");
        financeTpaSettleDetail.setCreateBy(SecurityUtils.getUsername());
        financeTpaSettleDetail.setCreateTime(DateUtils.getNowDate());
        financeTpaSettleDetail.setUpdateBy(SecurityUtils.getUsername());
        financeTpaSettleDetail.setUpdateTime(DateUtils.getNowDate());

        financeSettleRecord.setTaskType("01");
        financeSettleRecord.setOperator("");
        financeSettleRecord.setHistoryFlag("N");
        financeSettleRecord.setOperation("01");
        financeSettleRecord.setStatus("Y");
//        financeSettleRecord.setDeptCode( sysUser.getDeptId().toString());
        financeSettleRecord.setDeptCode("unknown");
        financeSettleRecord.setCreateBy(SecurityUtils.getUsername());
        financeSettleRecord.setCreateTime(DateUtils.getNowDate());
        financeSettleRecord.setUpdateBy(SecurityUtils.getUsername());
        financeSettleRecord.setUpdateTime(DateUtils.getNowDate());

        FinanceTpaSettleDetail tpaSettleDetail = new FinanceTpaSettleDetail();
        //根据传回条件，查询对应险种下的关联保单信息
        policyAndRiskRelation.setCompanyCode(tpaSettleDTO.getCompanyCode());
        String taskNo = "SF" + PubFun.createMySqlMaxNoUseCache("tpa_service_settlement", 10, 10);

        if (StringUtils.isEmpty(tpaSettleDTO.getRiskCode())){
            tpaSettleDetail.setCompanyCode(tpaSettleDTO.getCompanyCode());
            tpaSettleDetail.setStatus(ClaimStatus.DATAYES.getCode());
            List<FinanceTpaSettleDetail> financeTpaSettleDetails = financeTpaSettleDetailMapper.selectFinanceTpaSettleDetailList(tpaSettleDetail);
            //设值 结算表的结算起期
            if (StringUtils.isNotEmpty(financeTpaSettleDetails)){
                FinanceTpaSettleTask tpaSettleTask = new FinanceTpaSettleTask();
                tpaSettleTask.setSettleTaskNo(financeTpaSettleDetails.get(0).getSettleTaskNo());
                List<FinanceTpaSettleTask> financeTpaSettleTasks = financeTpaSettleTaskMapper.selectFinanceTpaSettleTaskList(tpaSettleTask);
                if (StringUtils.isNotEmpty(financeTpaSettleTasks)) {
                    financeTpaSettleTask.setSettleStartDate(financeTpaSettleTasks.get(0).getSettleEndDate());
                }
            }
            List<BaseIssuingcompanyRule> baseIssuingRules = baseIssuingcompanyRiskrelaMapper.selectCompanyRiskrelaRiskByTpa(tpaSettleDTO);
            for (BaseIssuingcompanyRule baseIssuingRule : baseIssuingRules) {
                //通过险种、保单关联得到对应的保单数据
                policyAndRiskRelation.setRiskCode(baseIssuingRule.getRiskcode());
                policyAndRiskRelation.setStartTime(financeTpaSettleTask.getSettleStartDate());
                policyAndRiskRelation.setEndTime(financeTpaSettleTask.getSettleEndDate());
                policyAndRiskRelation.setCompanyCode(financeTpaSettleTask.getCompanyCode());

                TableDataInfo relationCompanyList = policyAndRiskService.getRelationCompanyList(policyAndRiskRelation);
                if (StringUtils.isNotEmpty(relationCompanyList.getRows())) {
                    for (Object row : relationCompanyList.getRows()) {
                        companyRiskPolicy = objectMapper.convertValue(row, CompanyRiskPolicy.class);
                        //子页面 下拉列表数据
                        BeanUtils.copyProperties(companyRiskPolicy, tpaSettleDetailInfo);
                        BeanUtils.copyProperties(companyRiskPolicy, financeTpaSettleDetail);
                        financeTpaSettleDetail.setRiskCode(companyRiskPolicy.getRiskCode());
                        tpaSettleDetailInfo.setRiskName(baseIssuingRule.getRiskName());
                        if ("01".equals(tpaSettleDTO.getSettlementType())) {//保费比例
                            tpaSettleDetailInfo.setPremiumRatio(baseIssuingRule.getSettlementvalue().divide(new BigDecimal(String.valueOf(100)),2,BigDecimal.ROUND_HALF_UP));
                            tpaSettleDetailInfo.setServiceAmount(companyRiskPolicy.getPrem().multiply(tpaSettleDetailInfo.getPremiumRatio()));
                        }
                        if ("02".equals(tpaSettleDTO.getSettlementType())){
                            tpaSettleDetailInfo.setServiceAmount(baseIssuingRule.getSettlementvalue());
                        }
                        financeTpaSettleDetail.setSettleTaskNo(taskNo);
                        financeTpaSettleDetail.setServiceAmount(tpaSettleDetailInfo.getServiceAmount());
                        detailInfos.add(tpaSettleDetailInfo);
                        financeTpaSettleDetailMapper.insertFinanceTpaSettleDetail(financeTpaSettleDetail);
                        policyAndRiskRelation.setSettleFlag("Y");
                        policyAndRiskService.settledPolicy(policyAndRiskRelation);
                    }
                }else{
                    throw new Exception("该出单公司在该截止日期前已结算完毕！");
                }
                tpaSettleInfo.setDetailInfos(detailInfos);
                //子页面 列表数据
                assert companyRiskPolicy != null;
                BeanUtils.copyProperties(companyRiskPolicy, tpaSettleInfo);
                tpaSettleInfo.setRiskCode(tpaSettleDTO.getRiskCode());
                tpaSettleInfo.setRiskName(baseIssuingRule.getRiskName());
                tpaSettleInfo.setSettlementType(tpaSettleDTO.getSettlementType());
                //设值 页面展示的服务费结算总金额
                if (StringUtils.isNotNull(tpaSettleInfo.getServiceSettleAmount()) && 0 < new BigDecimal(String.valueOf(0)).compareTo(tpaSettleInfo.getServiceSettleAmount())){
                    tpaSettleInfo.setServiceSettleAmount(new BigDecimal(String.valueOf(0)));
                }
                if ("01".equals(tpaSettleDTO.getSettlementType())) {
                    tpaSettleInfo.setServiceSettleAmount((baseIssuingRule.getSettlementvalue().multiply(new BigDecimal(companyRiskPolicy.getTotalPeople())))
                            .add(tpaSettleInfo.getServiceSettleAmount()));
                }
                if ("02".equals(tpaSettleDTO.getSettlementType())) {
                    tpaSettleInfo.setServiceSettleAmount((baseIssuingRule.getSettlementvalue().multiply(companyRiskPolicy.getSumPerm()))
                            .add(tpaSettleInfo.getServiceSettleAmount()));
                }
                financeTpaSettleTask.setServiceSettleAmount(tpaSettleInfo.getServiceSettleAmount());
                tpaSettleInfos.add(tpaSettleInfo);
                earliestDay=companyRiskPolicy.getValidStartDate().before(earliestDay)?companyRiskPolicy.getValidStartDate():earliestDay;
            }
            if (StringUtils.isNotNull(financeTpaSettleTask.getSettleStartDate())) {
                financeTpaSettleTask.setSettleStartDate(earliestDay);
            }
            financeTpaSettleTask.setSettleTaskNo(taskNo);
            financeTpaSettleTaskMapper.insertFinanceTpaSettleTask(financeTpaSettleTask);
            financeSettleRecord.setSettleTaskNo(taskNo);
            financeSettleRecordMapper.insertFinanceSettleRecord(financeSettleRecord);
        }else {
            tpaSettleDetail.setCompanyCode(tpaSettleDTO.getCompanyCode());
            tpaSettleDetail.setRiskCode(tpaSettleDTO.getRiskCode());
            List<FinanceTpaSettleDetail> financeTpaSettleDetails = financeTpaSettleDetailMapper.selectFinanceTpaSettleDetailList(financeTpaSettleDetail);
            //设值 结算表的结算起期
            if (StringUtils.isNotEmpty(financeTpaSettleDetails)){
                FinanceTpaSettleTask tpaSettleTask = new FinanceTpaSettleTask();
                tpaSettleTask.setSettleTaskNo(financeTpaSettleDetails.get(0).getSettleTaskNo());
                List<FinanceTpaSettleTask> financeTpaSettleTasks = financeTpaSettleTaskMapper.selectFinanceTpaSettleTaskList(tpaSettleTask);
                if (StringUtils.isNotEmpty(financeTpaSettleTasks)) {
                    financeTpaSettleTask.setSettleStartDate(financeTpaSettleTasks.get(0).getSettleEndDate());
                }
            }
            //查询得到险种对应的信息
            BaseIssuingcompanyRule baseIssuingcompanyRule = new BaseIssuingcompanyRule();
            baseIssuingcompanyRule.setCompanycode(tpaSettleDTO.getCompanyCode());
            baseIssuingcompanyRule.setRiskcode(tpaSettleDTO.getRiskCode());
            baseIssuingcompanyRule.setStatus(ClaimStatus.DATAYES.getCode());
            BaseIssuingcompanyRule companyRule = baseIssuingcompanyRuleMapper.selectBaseIssuingcompanyRule(baseIssuingcompanyRule);

            //通过险种、保单关联得到对应的保单数据
            policyAndRiskRelation.setRiskCode(tpaSettleDTO.getRiskCode());
            policyAndRiskRelation.setStartTime(financeTpaSettleTask.getSettleStartDate());
            policyAndRiskRelation.setEndTime(financeTpaSettleTask.getSettleEndDate());
            policyAndRiskRelation.setCompanyCode(financeTpaSettleTask.getCompanyCode());
            TableDataInfo relationCompanyList = policyAndRiskService.getRelationCompanyList(policyAndRiskRelation);
            if (StringUtils.isNotEmpty(relationCompanyList.getRows())) {
                for (Object row : relationCompanyList.getRows()) {
                    companyRiskPolicy = objectMapper.convertValue(row, CompanyRiskPolicy.class);
                    //子页面下拉数据
                    BeanUtils.copyProperties(companyRiskPolicy, tpaSettleDetailInfo);
                    tpaSettleDetailInfo.setRiskName(companyRule.getRiskName());
                    if ("01".equals(tpaSettleDTO.getSettlementType())) {//保费比例
                        tpaSettleDetailInfo.setPremiumRatio(companyRule.getSettlementvalue().divide(new BigDecimal(String.valueOf(100)),2,BigDecimal.ROUND_HALF_UP));
                        tpaSettleDetailInfo.setServiceAmount(companyRiskPolicy.getPrem().multiply(tpaSettleDetailInfo.getPremiumRatio()));
                    }
                    if ("02".equals(tpaSettleDTO.getSettlementType())){
                        tpaSettleDetailInfo.setServiceAmount(companyRule.getSettlementvalue());
                    }
                    financeTpaSettleDetail.setSettleTaskNo(taskNo);
                    financeTpaSettleDetail.setServiceAmount(tpaSettleDetailInfo.getServiceAmount());
                    financeTpaSettleDetailMapper.insertFinanceTpaSettleDetail(financeTpaSettleDetail);
                    policyAndRiskRelation.setSettleFlag("Y");
                    policyAndRiskService.settledPolicy(policyAndRiskRelation);
                    detailInfos.add(tpaSettleDetailInfo);
                }
            }
            tpaSettleInfo.setDetailInfos(detailInfos);
            assert companyRiskPolicy != null;
            BeanUtils.copyProperties(companyRiskPolicy,tpaSettleInfo);
            tpaSettleInfo.setRiskCode(tpaSettleDTO.getRiskCode());
            tpaSettleInfo.setRiskName(companyRule.getRiskName());
            tpaSettleInfo.setSettlementType(tpaSettleDTO.getSettlementType());
            if ("02".equals(tpaSettleDTO.getSettlementType())){
                tpaSettleInfo.setServiceSettleAmount(companyRule.getSettlementvalue().multiply(new BigDecimal(companyRiskPolicy.getTotalPeople())));
            }
            if ("01".equals(tpaSettleDTO.getSettlementType())){
                tpaSettleInfo.setServiceSettleAmount(companyRule.getSettlementvalue().multiply(companyRiskPolicy.getSumPerm()));
            }
            if (!StringUtils.isNotNull(financeTpaSettleTask.getSettleStartDate())) {
                financeTpaSettleTask.setSettleStartDate(companyRiskPolicy.getValidStartDate());
            }
            financeTpaSettleTask.setServiceSettleAmount(tpaSettleInfo.getServiceSettleAmount());
            financeTpaSettleTask.setSettleTaskNo(taskNo);
            financeTpaSettleTaskMapper.insertFinanceTpaSettleTask(financeTpaSettleTask);
            financeSettleRecord.setSettleTaskNo(taskNo);
            financeSettleRecordMapper.insertFinanceSettleRecord(financeSettleRecord);
            tpaSettleInfos.add(tpaSettleInfo);
        }
        return tpaSettleInfos;
    }

    /**
     * 多页面 查询TPA服务费结算任务 列表
     *
     * @param tpaSettleDTO TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    @Override
    public  List<FinanceTpaSettleTask>  selectTpaSettleTaskList(TpaSettleDTO tpaSettleDTO) {
        tpaSettleDTO.setStatus("Y");
        return financeTpaSettleTaskMapper.selectTpaSettleTaskList(tpaSettleDTO);
    }

    /**
     * 查询TPA服务费结算 子页面列表
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return TPA服务费结算任务集合
     */
    @Override
    public List<TpaSettleInfo> selectFinanceTpaSettleTaskViewDetail(FinanceTpaSettleTask financeTpaSettleTask) {
        return financeTpaSettleTaskMapper.selectFinanceTpaSettleTaskViewDetail(financeTpaSettleTask);
    }

    /**
     * 导入TPA服务费结算
     *
     * @param file TPA服务费结算任务
     * @return TPA服务费结算任务Map
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int importTpaSettleTask(MultipartFile file) {
        FinanceTpaSettleTask tpaSettleTask = new FinanceTpaSettleTask();
        try {
            InputStream is = file.getInputStream();
            Workbook wb = ExcelUtils.getWorkbook(is,file.getName());
            int sheetNum = wb.getNumberOfSheets();
            BigDecimal feeTotalAmount = BigDecimal.valueOf(0);
            for (int j = 0; j < sheetNum; j++) {
                Sheet sheet = wb.getSheetAt(j);
                if (1==j) {
                    ExcelUtils<FinanceTpaSettleTask> utils = new ExcelUtils<FinanceTpaSettleTask>(FinanceTpaSettleTask.class);
                    List<FinanceTpaSettleTask> taskList = utils.importExcel(sheet);
                    BeanUtils.copyProperties(taskList.get(0),tpaSettleTask);
                }else {
                    boolean flag=false;
                    ExcelUtils<FinanceTpaSettleDetail> utils = new ExcelUtils<FinanceTpaSettleDetail>(FinanceTpaSettleDetail.class);
                    List<FinanceTpaSettleDetail> detailList = utils.importExcel(sheet);
                    List<FinanceTpaSettleDetail> financeTpaSettleDetails = financeTpaSettleDetailMapper.selectFinanceTpaSettleDetailBySettleTaskNo(detailList.get(0).getSettleTaskNo());
                    if (detailList.size()==financeTpaSettleDetails.size()){
                        financeTpaSettleDetailMapper.removeFinanceTpaSettleDetail(detailList);
                    }else {
                        PolicyAndRiskRelation policyAndRiskRelation = new PolicyAndRiskRelation();
                        policyAndRiskRelation.setSettleFlag("N");
                        for (FinanceTpaSettleDetail tpaSettleDetail : financeTpaSettleDetails) {
                            for (FinanceTpaSettleDetail financeTpaSettleDetail : detailList) {
                                if (tpaSettleDetail.getPolicyNo().equals(financeTpaSettleDetail.getPolicyNo())){
                                    financeTpaSettleDetail.setUpdateBy(SecurityUtils.getUsername());
                                    financeTpaSettleDetail.setUpdateTime(DateUtils.getNowDate());
                                    financeTpaSettleDetailMapper.editFinanceTpaSettleDetail(financeTpaSettleDetail);
                                    feeTotalAmount=feeTotalAmount.add(financeTpaSettleDetail.getServiceAmount());
                                    flag=true;
                                }
                            }
                            if (!flag){
                                financeTpaSettleDetailMapper.neutralizeFinanceTpaSettleDetail(tpaSettleDetail.getSettleTaskNo());
                                policyAndRiskRelation.setPolicyNo(tpaSettleDetail.getPolicyNo());
                                policyAndRiskRelation.setCompanyCode(tpaSettleDetail.getCompanyCode());
                                policyAndRiskRelation.setRiskCode(tpaSettleDetail.getRiskCode());
                                policyAndRiskService.settledPolicy(policyAndRiskRelation);
                            }
                        }
                    }
                }
            }
            tpaSettleTask.setServiceSettleAmount(feeTotalAmount);
            tpaSettleTask.setUpdateBy(SecurityUtils.getUsername());
            tpaSettleTask.setUpdateTime(DateUtils.getNowDate());

        }catch (Exception e){
            e.printStackTrace();
        }
        return financeTpaSettleTaskMapper.updateFinanceTpaSettleTask(tpaSettleTask);
    }

    /**
     * 根据companyCode查询收款核销列表
     *
     * @param tpaSettleDTO 收款核销
     * @return 收款核销集合
     */
    @Override
    public List<FinanceCollectionInfo> selectTpaTaskReceiptColList(TpaSettleDTO tpaSettleDTO) {
        return financeTpaSettleTaskMapper.selectTpaTaskReceiptColList(tpaSettleDTO);
    }


    /**
     * 新增TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    @Override
    public int insertFinanceTpaSettleTask(FinanceTpaSettleTask financeTpaSettleTask)
    {
        financeTpaSettleTask.setCreateTime(DateUtils.getNowDate());
        return financeTpaSettleTaskMapper.insertFinanceTpaSettleTask(financeTpaSettleTask);
    }

    /**
     * 修改TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    @Override
    public int updateFinanceTpaSettleTask(FinanceTpaSettleTask financeTpaSettleTask)
    {
        financeTpaSettleTask.setUpdateTime(DateUtils.getNowDate());
        return financeTpaSettleTaskMapper.updateFinanceTpaSettleTask(financeTpaSettleTask);
    }

    /**
     * 确认TPA服务费结算任务
     *
     * @param settleTaskNo TPA服务费结算任务
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int confirmTpaSettleTask(String settleTaskNo) {
        FinanceTpaSettleTask financeTpaSettleTask = new FinanceTpaSettleTask();
        FinanceSettleRecord settleRecord = new FinanceSettleRecord();

        financeTpaSettleTask.setSettleTaskNo(settleTaskNo);
        financeTpaSettleTask.setSettleStatus("03");
        financeTpaSettleTask.setUpdateBy(SecurityUtils.getUsername());
        financeTpaSettleTask.setUpdateTime(DateUtils.getNowDate());

        FinanceSettleRecord financeSettleRecord = financeSettleRecordMapper.selectNRecordBySettleTaskNo(settleTaskNo);
        financeSettleRecord.setHistoryFlag("Y");
        financeSettleRecord.setOperator(SecurityUtils.getUsername());
        financeSettleRecord.setUpdateBy(SecurityUtils.getUsername());
        financeSettleRecord.setUpdateTime(DateUtils.getNowDate());
        financeSettleRecordMapper.updateFinanceSettleRecord(financeSettleRecord);
        settleRecord.setOperator("");
        settleRecord.setTaskType("01");
        settleRecord.setHistoryFlag("N");
        settleRecord.setSettleTaskNo(financeSettleRecord.getSettleTaskNo());
        settleRecord.setOperation("03");
        settleRecord.setOrgRecordId(financeSettleRecord.getRecordId());
        settleRecord.setStatus("Y");
        settleRecord.setCreateBy(SecurityUtils.getUsername());
        settleRecord.setCreateTime(DateUtils.getNowDate());
        settleRecord.setUpdateBy(SecurityUtils.getUsername());
        settleRecord.setUpdateTime(DateUtils.getNowDate());
//        Long userId = SecurityUtils.getLoginUser().getUserId();
//        SysUser sysUser = sysUserMapper.selectUserById(userId);
//        settleRecord.setDeptCode( sysUser.getDeptId().toString());
        settleRecord.setDeptCode("unknown");
        financeSettleRecordMapper.insertFinanceSettleRecord(settleRecord);
        return financeTpaSettleTaskMapper.updateFinanceTpaSettleTask(financeTpaSettleTask);
    }


    /**
     * 删除TPA服务费结算任务
     *
     * @param financeTpaSettleTask TPA服务费结算任务
     * @return 结果
     */
    @Override
    public int modifyTaskSettle(FinanceTpaSettleTask financeTpaSettleTask)
    {
        FinanceTpaSettleDetail financeTpaSettleDetail = new FinanceTpaSettleDetail();
        financeTpaSettleDetail.setSettleTaskNo(financeTpaSettleTask.getSettleTaskNo());
        PolicyAndRiskRelation policyAndRiskRelation = new PolicyAndRiskRelation();
        policyAndRiskRelation.setCompanyCode(financeTpaSettleTask.getCompanyCode());
        policyAndRiskRelation.setSettleFlag("N");
        List<FinanceTpaSettleDetail> financeTpaSettleDetails = financeTpaSettleDetailMapper.selectFinanceTpaSettleDetailList(financeTpaSettleDetail);
        for (FinanceTpaSettleDetail tpaSettleDetail : financeTpaSettleDetails) {
            tpaSettleDetail.setStatus("N");
            financeTpaSettleDetailMapper.updateFinanceTpaSettleDetail(tpaSettleDetail);
//            policyAndRiskService ————————此处需要跨库修改保单状态
            policyAndRiskRelation.setRiskCode(tpaSettleDetail.getRiskCode());
            policyAndRiskRelation.setPolicyNo(tpaSettleDetail.getPolicyNo());
            policyAndRiskService.settledPolicy(policyAndRiskRelation);
        }
        financeTpaSettleTask.setUpdateBy(SecurityUtils.getUsername());
        financeTpaSettleTask.setUpdateTime(DateUtils.getNowDate());
        financeSettleRecordMapper.removeFinanceSettleRecords(financeTpaSettleTask.getSettleTaskNo());
        return financeTpaSettleTaskMapper.modifyTaskSettle(financeTpaSettleTask);
    }

    /**
     * 批量删除TPA服务费结算任务
     *
     * @param taskIds 需要删除的TPA服务费结算任务ID
     * @return 结果
     */
    @Override
    public int deleteFinanceTpaSettleTaskByIds(Long[] taskIds)
    {
        return financeTpaSettleTaskMapper.deleteFinanceTpaSettleTaskByIds(taskIds);
    }

    /**
     * 删除TPA服务费结算任务信息
     *
     * @param taskId TPA服务费结算任务ID
     * @return 结果
     */
    @Override
    public int deleteFinanceTpaSettleTaskById(Long taskId)
    {
        return financeTpaSettleTaskMapper.deleteFinanceTpaSettleTaskById(taskId);
    }
}
