package com.paic.ehis.finance.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.common.core.exception.BaseException;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.finance.domain.*;
import com.paic.ehis.finance.domain.dto.ClaimCasePayDTO;
import com.paic.ehis.finance.domain.vo.*;
import com.paic.ehis.finance.mapper.*;
import com.paic.ehis.finance.service.IClaimCasePayService;
import com.paic.ehis.finance.utils.ObjectNullUtil;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.domain.BaseProviderInfo;
import com.paic.ehis.system.api.domain.BaseProviderSettle;
import com.paic.ehis.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 对公支付
 */
@Service
public class ClaimCasePayServiceImpl implements IClaimCasePayService {

    @Autowired
    private ClaimBatchMapper claimBatchMapper;
    @Autowired
    private ClaimCasePayMapper claimCasePayMapper;
    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;
    @Autowired
    private GetProviderInfoService getProviderInfoService;
    @Autowired
    private ClaimCaseMapper claimCaseMapper;
    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;
    @Autowired
    private ClaimCaseCalMapper claimCaseCalMapper;
    @Autowired
    private FinancePayInfoMapper financePayInfoMapper;
    @Autowired
    private FinancePayDetailInfoMapper financePayDetailInfoMapper;
    @Autowired
    private FinanceBorrowInfoMapper financeBorrowInfoMapper;
    @Autowired
    private RemoteUserService userService;

    /*
     * 财务appId
     **/

//    @Value("${esg.financeid}")
//    private String financeid;

    /**
     * 查询当前登录用户所属机构下支付状态不全为已支付的批次信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectInitList(String organCode) {
        String deptId = "";
        if (null != organCode) {
            deptId = organCode;
        }

        List<Map<String, Object>> initList = claimBatchMapper.selectPayBatchInit(deptId);
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        for (Map<String, Object> map : initList) {
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            baseProviderInfo.setProviderCode(map.get("hospitalCode").toString());
            // 调用微服务获取医院名称
            R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
            baseProviderSettle.setProviderCode(map.get("hospitalCode").toString());
            baseProviderSettle.setOrgFlag("02");
            List<BaseProviderSettle> baseProviderSettles = getProviderInfoService.selectsettleInfoNew(baseProviderSettle);
            if (baseProviderSettles.size() > 0) {
                String noticeDay = baseProviderSettles.get(0).getNoticeDay();
                int days = 0;
                try {
                    days = daysBetween(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("submitDate").toString()), new Date());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (days > Integer.parseInt(noticeDay)) {
                    map.put("flag", "N");//飘红显示
                } else {
                    map.put("flag", "Y");//正常显示
                }

            }
            if (R.FAIL == result.getCode()) {
                throw new BaseException(result.getMsg());
            }
            if (result.getData().size() > 0) {
                BaseProviderInfo hospital = result.getData().get(0);
                map.put("hospitalCode", hospital.getChname1());
            }
        }
        return initList;
    }

    /**
     * 查询对公支付的批次信息
     *
     * @param claimCasePayDTO
     * @return
     */
    @Override
    public List<Map<String, Object>> selectBatchList(ClaimCasePayDTO claimCasePayDTO) {
        List<Map<String, Object>> batchList = claimBatchMapper.selectPayBatchList(claimCasePayDTO);
        // 调用微服务获取医院中文名称
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        for (Map<String, Object> map : batchList) {
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            baseProviderInfo.setProviderCode(map.get("hospitalCode").toString());
            // 调用微服务获取医院名称
            R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
            baseProviderSettle.setProviderCode(map.get("hospitalCode").toString());
            baseProviderSettle.setOrgFlag("02");
            List<BaseProviderSettle> baseProviderSettles = getProviderInfoService.selectsettleInfoNew(baseProviderSettle);
            if (baseProviderSettles.size() > 0) {
                String noticeDay = baseProviderSettles.get(0).getNoticeDay();
                int days = 0;
                try {
                    days = daysBetween(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("submitDate").toString()), new Date());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (StringUtils.isNotNull(noticeDay)) {
                    if (days > Integer.parseInt(noticeDay)) {
                        map.put("flag", "N");//飘红显示
                    } else {
                        map.put("flag", "Y");//正常显示
                    }
                }


            }
            if (R.FAIL == result.getCode()) {
                throw new BaseException(result.getMsg());
            }
            if (result.getData().size() > 0) {
                BaseProviderInfo hospital = result.getData().get(0);
                map.put("hospitalCode", hospital.getChname1());
            }
        }
        return batchList;
    }

    /**
     * 根据批次号查询案件详情
     *
     * @param batchNo
     * @return
     */
    @Override
    public ClaimCasePayVO getCaseInfo(String batchNo) {
        String username = SecurityUtils.getUsername();
        ClaimCasePayVO claimCasePayVO = new ClaimCasePayVO();
        String payFlag = "true";
        String payFlag1 = "true";
        String payFlag2 = "false";
        String borrowFlag = "false";
        // 获取案件信息
        List<ClaimCasePayInfoVO> payInfoList = claimCasePayMapper.selectPayInfoList(batchNo);
        ClaimBatch claimBatch1 = claimBatchMapper.selectClaimBatchById(batchNo);
        // 获取出单公司、借款金额
        FinancePayInfo financePayInfo = financePayInfoMapper.selectFinancePayInfoByBatchNo(batchNo);
        for (ClaimCasePayInfoVO payInfo : payInfoList) {
            //判断是否第一次支付
            if (StringUtils.isNotNull(financePayInfo)) {
                if ("01".equals(payInfo.getPayStatus())) {
                    payFlag2 = "true";
                }
            } else {
                if (!"98".equals(payInfo.getCaseStatus())){
                    if (!"01".equals(payInfo.getPayStatus()) &&  (claimBatch1.getClaimtype()!="01" && !"97".equals(payInfo.getCaseStatus()))) {
                        payFlag1 = "false";
                    }
                }
            }
            if (payInfo.getCaseStatus() != "99" || payInfo.getPayStatus() == "01") {
                borrowFlag = "true";
            }
            if ("06".equals(payInfo.getCaseStatus())) {
                ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(payInfo.getBatchNo());
                payInfo.setCurrency(claimBatch.getCurrency());
            }
            List<ClaimCasePolicy> policyList = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(payInfo.getRptNo());
            // 去重
            List<String> companyList = policyList.stream().map(ClaimCasePolicy::getCompanyName).collect(Collectors.toList());
            LinkedHashSet<String> hashSetCompany = new LinkedHashSet<>(companyList);
            ArrayList<String> companyWithoutDup = new ArrayList<>(hashSetCompany);
            // 拼接
            String companyName = StringUtils.join(companyWithoutDup, "|");
            payInfo.setCompanyName(companyName);

            // 获取借款金额
            FinanceBorrowInfo financeBorrowInfo = financeBorrowInfoMapper.selectFinanceBorrowInfoByRptNo(payInfo.getRptNo());
            if (StringUtils.isNotNull(financeBorrowInfo)) {
                payInfo.setBorrowAmount(financeBorrowInfo.getBorrowAmount());
            } else {
                payInfo.setBorrowAmount(new BigDecimal("0"));
            }
            for (ClaimCasePolicy claimCasePolicy : policyList) {
                ArrayList<String> policyNos = new ArrayList<>();
                ArrayList<String> policyItemNos = new ArrayList<>();
                policyNos.add(claimCasePolicy.getPolicyNo());
                policyItemNos.add(claimCasePolicy.getPolicyItemNo());
                payInfo.setPolicyNo(policyNos);
                payInfo.setPolicyItemNo(policyItemNos);
            }
        }
        if (StringUtils.isNotNull(financePayInfo)) {
            payFlag = payFlag2;
        } else {
            payFlag = payFlag1;
        }
        claimCasePayVO.setCaseInfoList(payInfoList);

        // 轨迹表生成数据、支付总金额、理赔总金额
        BigDecimal payAmount = new BigDecimal("0.00");
        BigDecimal calAmount = new BigDecimal("0.00");
        for (ClaimCasePayInfoVO payInfoVO : payInfoList) {
            // 1、判断案件是可支付状态
            // 理赔金额
            ClaimCaseCal claimCaseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(payInfoVO.getRptNo());
            if (StringUtils.isNotNull(claimCaseCal)) {
                //判断是否撤件
                if (!"98".equals(payInfoVO.getCaseStatus()) && !"97".equals(payInfoVO.getCaseStatus())) {
                    calAmount = calAmount.add(claimCaseCal.getCalAmount());
                }

                if ("01".equals(payInfoVO.getPayStatus())) {
                    // 获取支付金额 理赔金额
                    //判断是否撤件
                    if (!"98".equals(payInfoVO.getCaseStatus()) && !"97".equals(payInfoVO.getCaseStatus())) {
                        payAmount = payAmount.add(claimCaseCal.getPayAmount());
                    }
                    // 是否已存在轨迹为“支付环节”
                    ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
                    claimCaseRecord.setOperation("09");
                    claimCaseRecord.setRptNo(payInfoVO.getRptNo());
                    claimCaseRecord.setHistoryFlag("N");
                    claimCaseRecord.setStatus("Y");
                    List<ClaimCaseRecord> recordList = claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
                    if (recordList.size() <= 0) {
                        // 2、结案轨迹置成历史轨迹
                        claimCaseRecord.setOperator(username);
                        claimCaseRecord.setHistoryFlag("Y");
                        claimCaseRecord.setUpdateBy(username);
                        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
                        claimCaseRecord.setOperation("99");
                        claimCaseRecordMapper.updateClaimCaseRecordByRptNoAndOperator(claimCaseRecord);
                        // 3、新增“支付环节”轨迹
                        ClaimCaseRecord record = new ClaimCaseRecord();
                        record.setOperation("09");
                        record.setRptNo(payInfoVO.getRptNo());
                        record.setHistoryFlag("N");
                        record.setStatus("Y");
                        record.setCreateBy(username);
                        record.setCreateTime(DateUtils.getNowDate());
                        claimCaseRecordMapper.insertClaimCaseRecord(record);
                    }

                }

            }
            if ("98".equals(payInfoVO.getCaseStatus())) {
                payInfoVO.setTreatmentDate(null);
                payInfoVO.setName("");
                payInfoVO.setCompanyName("");
                payInfoVO.setPayStatus("");
                payInfoVO.setBillAmount(new BigDecimal(0));
                payInfoVO.setDiscountedAmount(new BigDecimal(0));
                payInfoVO.setAdvancePayment(new BigDecimal(0));
                payInfoVO.setCopay(new BigDecimal(0));
                payInfoVO.setPayAmount(new BigDecimal(0));
                payInfoVO.setDebtAmount(new BigDecimal(0));
                payInfoVO.setBorrowAmount(new BigDecimal(0));
            }
            if ("97".equals(payInfoVO.getCaseStatus())) {
                payInfoVO.setPayAmount(new BigDecimal(0));
                payInfoVO.setDebtAmount(new BigDecimal(0));
            }
        }
        // 获取支付信息
        ClaimCasePaymentVO claimCasePaymentVO = new ClaimCasePaymentVO();
        // 封装支付总金额、理赔总金额
        claimCasePaymentVO.setPayAmount(payAmount);
        claimCasePaymentVO.setCalAmount(calAmount);
        if (payAmount.compareTo(new BigDecimal("0.00")) <= 0) {
            payFlag = "false";
        }
        ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(batchNo);
        // 封装支付币种
        claimCasePaymentVO.setCurrency(claimBatch.getCurrency());
        // 调用微服务获取医院信息
        BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
        baseProviderInfo.setProviderCode(claimBatch.getHospitalcode());
        //调用医院接口
        R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
        if (R.FAIL == result.getCode()) {
            throw new BaseException(result.getMsg());
        }
        if (result.getData().size() > 0) {
            BaseProviderInfo hospital = result.getData().get(0);
            //BaseProviderInfo hospital = (BaseProviderInfo) tableDataInfo.getRows().get(1);
            // 封装返回对象
            claimCasePaymentVO.setBank(hospital.getBankName());//开户行
            claimCasePaymentVO.setBankName(hospital.getAccountName());//账户名
            claimCasePaymentVO.setBankNumber(hospital.getAccountNo());//账户号
        }
        // 获取‘是否仅结算理赔责任’ 是01-非全赔 否02-全赔
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        baseProviderSettle.setProviderCode(claimBatch.getHospitalcode());
        baseProviderSettle.setOrgFlag("02");
        if (getProviderInfoService.selectsettleInfoNew(baseProviderSettle).size() > 0) {
            BaseProviderSettle settle = getProviderInfoService.selectsettleInfoNew(baseProviderSettle).get(0);
            claimCasePaymentVO.setClaimFlag(settle.getClaimFlag());
        }
        claimCasePayVO.setPayment(claimCasePaymentVO);
        claimCasePayVO.setPayFlag(payFlag);
        claimCasePayVO.setBorrowFlag(borrowFlag);
        return claimCasePayVO;
    }

    /**
     * 对公支付-回退按钮
     *
     * @param rptNo
     * @return
     */
    @Override
    public AjaxResult rollback(String rptNo) {
        String username = SecurityUtils.getUsername();
        ClaimCase claimCase = claimCaseMapper.selectClaimCaseById(rptNo);
        // 案件状态为撤件98、撤件可申诉97，或支付状态为支付中02、已支付03则阻断提示：“当前案件不允许进行回退，请核实”
        String caseStatus = claimCase.getCaseStatus();
        String isAppeal = claimCase.getIsAppeal();
        if ("97".equals(caseStatus) || "98".equals(caseStatus) || "02".equals(isAppeal) || "03".equals(isAppeal)) {
            return AjaxResult.error("当前案件不允许进行回退，请核实");
        }
        // TODO:区分是TPA还是核心案件
        // 核心案件：case状态改为“推送核心”，支付状态置为空，“是否财务回退”字段置为“Y”
        // 获取上一次理算操作人
        String updateBy = claimCaseRecordMapper.selectClaimCaseRecordByrptNoOne(rptNo).getOperator();
        // 更新报案表信息
        ClaimCase aCase = new ClaimCase();
        aCase.setRptNo(rptNo);
        aCase.setCaseStatus("07");
        aCase.setIsFinanceBack("Y");
        aCase.setUpdateBy(updateBy);
        aCase.setPayStatus(" ");
        int num = claimCaseMapper.updateClaimCase(aCase);
        // 更新轨迹表
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        claimCaseRecord.setRptNo(rptNo);
        claimCaseRecord.setOperation("09");
        claimCaseRecord.setHistoryFlag("Y");
        claimCaseRecord.setOperator(username);
        claimCaseRecord.setUpdateBy(username);
        claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.updateClaimCaseRecordByRptNoAndOperator(claimCaseRecord);
        ClaimCaseRecord record = new ClaimCaseRecord();
        record.setRptNo(rptNo);
        record.setOperation("07");
        record.setHistoryFlag("N");
        record.setStatus("Y");
        record.setCreateBy(username);
        record.setCreateTime(DateUtils.getNowDate());
        claimCaseRecordMapper.insertClaimCaseRecord(record);
        return AjaxResult.success(num);
    }

    /**
     * 对公支付-确认支付按钮
     *
     * @param claimCasePayVO
     * @return
     */
    @Override
    public int confirmPayment(ClaimCaseForeignPayVO claimCasePayVO) {
        String username = SecurityUtils.getUsername();
        // 批次号
        String batchNo = claimCasePayVO.getBatchNo();
        // 外币 判断该批次下所有案件审核岗赔付结论中的账单币种是否一致
        if (!"CNY".equals(claimCasePayVO.getPayment().getCurrency())) {
//            List<ClaimCaseCal> calList = claimCaseCalMapper.selectClaimCaseCalByBatchNo(batchNo);
//            // 去重
//            List<String> billCurrList = calList.stream().map(ClaimCaseCal::getBillCurrency).collect(Collectors.toList());
//            LinkedHashSet<String> hashSetCurren = new LinkedHashSet<>(billCurrList);
//            ArrayList<String> billCurren = new ArrayList<>(hashSetCurren);
//            // 拼接
//            String currency = StringUtils.join(billCurren, "|");
//            if (-1 != currency.indexOf("|")){
//                return AjaxResult.error("批次下案件币种不统一，请核实");
//            }
            boolean currFlag = claimCasePayVO.getCaseInfoList().stream().anyMatch(m -> "N".equals(m.getFlag()));
            if (currFlag) {
                // return AjaxResult.success("批次下案件币种不统一，请核实",3);
                return 3;
            }
        }
        ClaimCase financeCase = new ClaimCase();
        financeCase.setBatchNo(batchNo);
        financeCase.setIsFinanceBack("Y");
        financeCase.setStatus("Y");
        List<ClaimCase> finanBackList = claimCaseMapper.selectClaimCaseList(financeCase);
        if (finanBackList.size() > 0) {
            //return AjaxResult.success("此批次存在回退案件，请结案后进行支付",2);
            return 2;
        } else {
            // 支付信息
            ClaimCasePaymentVO payment = claimCasePayVO.getPayment();
            // 案件信息
            List<ClaimCaseForeignPayInfoVO> caseInfoList = claimCasePayVO.getCaseInfoList();
            // 生成支付信息-主表
            FinancePayInfo financePayInfo = new FinancePayInfo();
            // 付款批次号
            String payBatchNo = "HEMS" + PubFun.createMySqlMaxNoUseCache("pay_seq", 12, 11);
            financePayInfo.setPayBatchNo(payBatchNo);
            financePayInfo.setBatchNo(batchNo);
            financePayInfo.setPayCurrency(payment.getCurrency());
            financePayInfo.setSumPayAmount(payment.getPayAmount());
            financePayInfo.setSumClaimAmount(payment.getCalAmount());
            financePayInfo.setPayeeBank(payment.getBank());
            financePayInfo.setAccNo(payment.getBankNumber());
            financePayInfo.setAccName(payment.getBankName());
            financePayInfo.setPayStatus("02");
            financePayInfo.setStatus("Y");
            financePayInfo.setCreateBy(username);
            financePayInfo.setCreateTime(DateUtils.getNowDate());
            financePayInfo.setDeptCode(claimCasePayVO.getOrganCode());
            financePayInfo.setTransactionCode(payment.getTransactionCode());
            financePayInfo.setInternationalCompletedBy(payment.getInternationalCompletedBy());
            financePayInfo.setInternationalCompletedPhone(payment.getInternationalCompletedPhone());
            financePayInfo.setTransactionPostscript(payment.getTransactionPostscript());
            // 01-非全赔 02-全赔
            String claimFlag = payment.getClaimFlag();

            ArrayList<RecordDetail> recordDetails = new ArrayList<>();
            // 生成支付信息-附表
            for (ClaimCaseForeignPayInfoVO caseInfo : caseInfoList) {
                if ("01".equals(caseInfo.getPayStatus())) {
                    FinancePayDetailInfo financePayDetailInfo = new FinancePayDetailInfo();
                    financePayDetailInfo.setPayBatchNo(payBatchNo);
                    financePayDetailInfo.setBatchNo(batchNo);
                    financePayDetailInfo.setRptNo(caseInfo.getRptNo());
                    if ("01".equals(claimFlag)) {
                        financePayDetailInfo.setPayAmount(caseInfo.getPayAmount());
                    } else if ("02".equals(claimFlag)) {
                        financePayDetailInfo.setPayAmount(caseInfo.getDiscountedAmount());
                    }
                    financePayDetailInfo.setClaimAmount(caseInfo.getPayAmount());
                    financePayDetailInfo.setStatus("Y");
                    financePayDetailInfo.setCreateBy(username);
                    financePayDetailInfo.setCreateTime(DateUtils.getNowDate());
                    financePayDetailInfo.setDeptCode(claimCasePayVO.getOrganCode());
                    financePayDetailInfoMapper.insertFinancePayDetailInfo(financePayDetailInfo);
                    // 修改案件信息支付状态为‘支付中’
                    ClaimCase claimCase = new ClaimCase();
                    claimCase.setRptNo(caseInfo.getRptNo());
                    claimCase.setPayStatus("02");
                    claimCaseMapper.updateClaimCase(claimCase);
                }
                //--------------对公支付明细推送接口--明细数据
                RecordDetail recordDetail = new RecordDetail();
                recordDetail.setBranchCode(caseInfo.getOrganCode());
                recordDetail.setBusiSrcType(caseInfo.getSource());
                recordDetail.setBusinessNo(caseInfo.getRptNo());
                recordDetail.setCertno(caseInfo.getPolicyItemNo());
                recordDetail.setCurno(caseInfo.getCurrency());
                recordDetail.setDeptno("");
                recordDetail.setDetailTradAmount("");
                recordDetail.setPayMode("");
                recordDetail.setPlanCode(null);
                recordDetail.setPolno(caseInfo.getPolicyNo());
                recordDetail.setRegionCode("");
                recordDetail.setClientno(caseInfo.getInsuredNo());
                recordDetail.setBranchCode(caseInfo.getName());
                recordDetail.setLiabType("");
                recordDetail.setIsPresent("");
                recordDetails.add(recordDetail);
            }
            financePayInfoMapper.insertFinancePayInfo(financePayInfo);

            //--------------对公支付明细推送接口--明细数据
            PayableRecordReq payableRecordReq = new PayableRecordReq();
            payableRecordReq.setSceneCode("");
            payableRecordReq.setSystemCode("");
            payableRecordReq.setBusiApplyNo(claimCasePayVO.getBatchNo());
            payableRecordReq.setBusiOrderId("");
            payableRecordReq.setBusiType("");
            payableRecordReq.setRecordDetails(recordDetails);
            //--------------调用财务应付接口


            //return AjaxResult.success("确认支付成功！",1);
            return 1;
        }
    }

    /**
     * 对公支付-案件借款按钮
     *
     * @param claimCasePayVO
     * @return
     */
    @Override
    public int borrowingCase(ClaimCaseForeignPayVO claimCasePayVO) {

        String username = SecurityUtils.getUsername();
        // 批次号
        String batchNo = claimCasePayVO.getBatchNo();
        // 外币 判断该批次下所有案件审核岗赔付结论中的账单币种是否一致
        if (!"CNY".equals(claimCasePayVO.getPayment().getCurrency())) {
            boolean currFlag = claimCasePayVO.getCaseInfoList().stream().anyMatch(m -> "N".equals(m.getFlag()));
            if (currFlag) {
                return 3;
            }
        }
        ClaimCase financeCase = new ClaimCase();
        financeCase.setBatchNo(batchNo);
        financeCase.setIsFinanceBack("Y");
        financeCase.setStatus("Y");
        List<ClaimCase> finanBackList = claimCaseMapper.selectClaimCaseList(financeCase);
        if (finanBackList.size() > 0) {
            return 2;
        } else {
            List<ClaimCaseForeignPayInfoVO> caseInfoList = claimCasePayVO.getCaseInfoList();
            for (ClaimCaseForeignPayInfoVO caseInfo : caseInfoList) {
                if (!"99".equals(caseInfo.getCaseStatus()) && !"98".equals(caseInfo.getCaseStatus()) && !("05".equals(caseInfo.getCaseStatus()) && caseInfo.getDiscountedAmount().compareTo(new BigDecimal(0)) == 0) && !"04".equals(caseInfo.getCaseStatus()) && !"06".equals(caseInfo.getCaseStatus())
                        && !"02".equals(caseInfo.getPayStatus()) && !"03".equals(caseInfo.getPayStatus())) {
                    // 支付状态置为可支付
                    ClaimCase claimCase = new ClaimCase();
                    claimCase.setRptNo(caseInfo.getRptNo());
                    claimCase.setPayStatus("01");
                    claimCaseMapper.updateClaimCase(claimCase);
                    // 借款表生成数据
                    FinanceBorrowInfo financeBorrowInfo = financeBorrowInfoMapper.selectFinanceBorrowInfoByRptNo(caseInfo.getRptNo());
                    if (StringUtils.isNull(financeBorrowInfo)) {
                        FinanceBorrowInfo financeBorrowInfo1 = new FinanceBorrowInfo();
                        financeBorrowInfo1.setBatchNo(claimCasePayVO.getBatchNo());
                        financeBorrowInfo1.setRptNo(caseInfo.getRptNo());
                        financeBorrowInfo1.setBorrowAmount(caseInfo.getDiscountedAmount());
                        financeBorrowInfo1.setStatus("Y");
                        financeBorrowInfo1.setCreateBy(username);
                        financeBorrowInfo1.setCreateTime(DateUtils.getNowDate());
                        financeBorrowInfo1.setDeptCode(claimCasePayVO.getOrganCode());
                        financeBorrowInfoMapper.insertFinanceBorrowInfo(financeBorrowInfo1);
                    } else {
                        financeBorrowInfo.setBorrowAmount(caseInfo.getDiscountedAmount());
                        financeBorrowInfo.setUpdateBy(username);
                        financeBorrowInfo.setUpdateTime(DateUtils.getNowDate());
                        financeBorrowInfoMapper.updateFinanceBorrowInfo(financeBorrowInfo);
                    }
                }
            }
            return 1;
        }
    }

    /**
     * 查询当前登录用户所属机构下状态不全为已支付的批次信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectInitForeignList(String organCode) {
        String deptId = "";
        if (null != organCode) {
            deptId = organCode;
        }
        List<Map<String, Object>> initList = claimBatchMapper.selectPayForeignBatchInit(deptId);
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        for (Map<String, Object> map : initList) {
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            baseProviderInfo.setProviderCode(map.get("hospitalCode").toString());
            // 调用微服务获取医院名称
            R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
            baseProviderSettle.setProviderCode(map.get("hospitalCode").toString());
            baseProviderSettle.setOrgFlag("02");
            List<BaseProviderSettle> baseProviderSettles = getProviderInfoService.selectsettleInfoNew(baseProviderSettle);
            if (baseProviderSettles.size() > 0) {
                String noticeDay = baseProviderSettles.get(0).getNoticeDay();
                int days = 0;
                try {
                    days = daysBetween(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("submitDate").toString()), new Date());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (days > Integer.parseInt(noticeDay)) {
                    map.put("flag", "N");//飘红显示
                } else {
                    map.put("flag", "Y");//正常显示
                }

            }
            if (R.FAIL == result.getCode()) {
                throw new BaseException(result.getMsg());
            }
            if (result.getData().size() > 0) {
                BaseProviderInfo hospital = result.getData().get(0);
                map.put("hospitalCode", hospital.getChname1());
            }
        }
        return initList;
    }

    /**
     * 对公外币支付-查询
     *
     * @param claimCasePayDTO
     * @return
     */
    @Override
    public List<Map<String, Object>> selectForeignBatchList(ClaimCasePayDTO claimCasePayDTO) {
        List<Map<String, Object>> batchList = claimBatchMapper.selectPayForeignBatchList(claimCasePayDTO);
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        for (Map<String, Object> map : batchList) {
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            baseProviderInfo.setProviderCode(map.get("hospitalCode").toString());
            // 调用微服务获取医院名称
            R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
            baseProviderSettle.setProviderCode(map.get("hospitalCode").toString());
            baseProviderSettle.setOrgFlag("02");
            List<BaseProviderSettle> baseProviderSettles = getProviderInfoService.selectsettleInfoNew(baseProviderSettle);
            if (baseProviderSettles.size() > 0) {
                String noticeDay = baseProviderSettles.get(0).getNoticeDay();
                int days = 0;
                try {
                    days = daysBetween(new SimpleDateFormat("yyyy-MM-dd").parse(map.get("submitDate").toString()), new Date());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (days > Integer.parseInt(noticeDay)) {
                    map.put("flag", "N");//飘红显示
                } else {
                    map.put("flag", "Y");//正常显示
                }

            }
            if (R.FAIL == result.getCode()) {
                throw new BaseException(result.getMsg());
            }
            if (result.getData().size() > 0) {
                BaseProviderInfo hospital = result.getData().get(0);
                map.put("hospitalCode", hospital.getChname1());
            }
        }
        return batchList;
    }

    /**
     * 根据批次号查询对公支付外币案件信息
     *
     * @param batchNo
     * @return
     */
    @Override
    public ClaimCaseForeignPayVO getForeignCase(String batchNo) {
        String username = SecurityUtils.getUsername();
        ClaimCaseForeignPayVO claimCaseForeignPayVO = new ClaimCaseForeignPayVO();
        String payFlag = "true";
        String borrowFlag = "false";
        // 获取案件信息
        List<ClaimCaseForeignPayInfoVO> payInfoList = claimCasePayMapper.selectForeignPayInfoList(batchNo);
        // 获取批次账单币种
        String batchCurrency = claimBatchMapper.selectClaimBatchById(batchNo).getCurrency();
        // 获取出单公司、借款金额
        for (ClaimCaseForeignPayInfoVO payInfo : payInfoList) {
            if (!"01".equals(payInfo.getPayStatus())) {
                payFlag = "false";
            }
            if (payInfo.getCaseStatus() != "99" || payInfo.getPayStatus() == "01") {
                borrowFlag = "true";
            }
            if ("06".equals(payInfo.getCaseStatus())) {
                ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(payInfo.getBatchNo());
                payInfo.setCurrency(claimBatch.getCurrency());
            }
            List<ClaimCasePolicy> policyList = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(payInfo.getRptNo());
            // 去重
            List<String> companyList = policyList.stream().map(ClaimCasePolicy::getCompanyName).collect(Collectors.toList());
            LinkedHashSet<String> hashSetCompany = new LinkedHashSet<>(companyList);
            ArrayList<String> companyWithoutDup = new ArrayList<>(hashSetCompany);
            // 拼接
            String companyName = StringUtils.join(companyWithoutDup, "|");
            payInfo.setCompanyName(companyName);
            // 获取借款金额
            FinanceBorrowInfo financeBorrowInfo = financeBorrowInfoMapper.selectFinanceBorrowInfoByRptNo(payInfo.getRptNo());
            if (null != financeBorrowInfo) {
                payInfo.setBorrowAmount(financeBorrowInfo.getBorrowAmount());
            } else {
                payInfo.setBorrowAmount(new BigDecimal("0"));
            }

            // 判断账单币种与批次币种是否一致
            if (batchCurrency.equals(payInfo.getBillCurrency())) {
                payInfo.setFlag("Y");
            } else {
                payInfo.setFlag("N");
            }
        }
        claimCaseForeignPayVO.setCaseInfoList(payInfoList);

        // 轨迹表生成数据、支付总金额、理赔总金额、外币支付总金额
        BigDecimal payAmount = new BigDecimal("0.00");
        BigDecimal calAmount = new BigDecimal("0.00");
        BigDecimal foreignPayAmount = new BigDecimal("0.00");
        for (ClaimCaseForeignPayInfoVO payInfoVO : payInfoList) {
            // 1、判断案件是可支付状态
            if ("01".equals(payInfoVO.getPayStatus())) {
                // 获取支付金额 理赔金额 外币支付总金额
                ClaimCaseCal claimCaseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(payInfoVO.getRptNo());
                if (!"98".equals(payInfoVO.getCaseStatus()) || !"97".equals(payInfoVO.getCaseStatus())) {
                    payAmount = payAmount.add(claimCaseCal.getPayAmount());
                    calAmount = calAmount.add(claimCaseCal.getCalAmount());
                }
                foreignPayAmount = foreignPayAmount.add(claimCaseCal.getPayAmountForeign());
                // 是否已存在轨迹为“支付环节”
                ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
                claimCaseRecord.setOperation("09");
                claimCaseRecord.setRptNo(payInfoVO.getRptNo());
                claimCaseRecord.setHistoryFlag("N");
                claimCaseRecord.setStatus("Y");
                List<ClaimCaseRecord> recordList = claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
                if (recordList.size() < 0) {
                    // 2、结案轨迹置成历史轨迹
                    claimCaseRecord.setOperator(username);
                    claimCaseRecord.setHistoryFlag("Y");
                    claimCaseRecord.setUpdateBy(username);
                    claimCaseRecord.setUpdateTime(DateUtils.getNowDate());
                    claimCaseRecord.setOperation("99");
                    claimCaseRecordMapper.updateClaimCaseRecordByRptNoAndOperator(claimCaseRecord);
                    // 3、新增“支付环节”轨迹
                    ClaimCaseRecord record = new ClaimCaseRecord();
                    record.setOperation("09");
                    record.setRptNo(payInfoVO.getRptNo());
                    record.setHistoryFlag("N");
                    record.setStatus("Y");
                    record.setCreateBy(username);
                    record.setCreateTime(DateUtils.getNowDate());
                    claimCaseRecordMapper.insertClaimCaseRecord(record);
                }
            }
            if ("98".equals(payInfoVO.getCaseStatus())) {
                payInfoVO.setTreatmentDate(null);
                payInfoVO.setName("");
                payInfoVO.setCompanyName("");
                payInfoVO.setPayStatus("");
                payInfoVO.setBillAmount(new BigDecimal(0));
                payInfoVO.setDiscountedAmount(new BigDecimal(0));
                payInfoVO.setAdvancePayment(new BigDecimal(0));
                payInfoVO.setCopay(new BigDecimal(0));
                payInfoVO.setPayAmount(new BigDecimal(0));
                payInfoVO.setDebtAmount(new BigDecimal(0));
                payInfoVO.setBorrowAmount(new BigDecimal(0));
            }
            if ("97".equals(payInfoVO.getCaseStatus())) {
                payInfoVO.setPayStatus("");
                payInfoVO.setPayAmount(new BigDecimal(0));
                payInfoVO.setDebtAmount(new BigDecimal(0));
            }
        }
        // 获取支付信息
        ClaimCasePaymentVO claimCasePaymentVO = new ClaimCasePaymentVO();
        //通过批次号查询finance_pay_info表最近一次的数据
        FinancePayInfo financePayInfo = financePayInfoMapper.selectFinancePayInfoByBatchNo(batchNo);
        if (StringUtils.isNotNull(financePayInfo)) {
            claimCasePaymentVO.setTransactionCode(financePayInfo.getTransactionCode());
            claimCasePaymentVO.setInternationalCompletedBy(financePayInfo.getInternationalCompletedBy());
            claimCasePaymentVO.setInternationalCompletedPhone(financePayInfo.getInternationalCompletedPhone());
            claimCasePaymentVO.setTransactionPostscript(financePayInfo.getTransactionPostscript());
        }
        // 封装支付总金额、理赔总金额、外币支付总金额
        claimCasePaymentVO.setPayAmount(payAmount);
        claimCasePaymentVO.setCalAmount(calAmount);
        if (payAmount.compareTo(new BigDecimal("0.00")) <= 0) {
            payFlag = "false";
        }
        ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(batchNo);
        // 该批次下所有账单币种是否一致，不一致时支付币种与外币支付总金额为空
        boolean currFlag = payInfoList.stream().anyMatch(m -> "N".equals(m.getFlag()));
        if (!currFlag) {
            // 封装支付币种
            claimCasePaymentVO.setCurrency(claimBatch.getCurrency());
            claimCasePaymentVO.setForeignPayAmount(foreignPayAmount);
        } else {//币种不一致
            borrowFlag = "false";
        }

        // 调用微服务获取医院信息
        BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
        baseProviderInfo.setProviderCode(claimBatch.getHospitalcode());
        //调用医院接口
        R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);//调用医院插叙接口
        if (R.FAIL == result.getCode()) {
            throw new BaseException(result.getMsg());
        }
        if (result.getData().size() > 0) {
            BaseProviderInfo hospital = result.getData().get(0);  // 封装返回对象
            claimCasePaymentVO.setBank(hospital.getAccountName());//开户行
            claimCasePaymentVO.setBankName(hospital.getBankName());//账户名
            claimCasePaymentVO.setBankNumber(hospital.getBankCode());//账户号
        }
        // 获取‘是否仅结算理赔责任’ 是01-非全赔 否02-全赔
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        baseProviderSettle.setProviderCode(claimBatch.getHospitalcode());
        baseProviderSettle.setOrgFlag("02");
        if (getProviderInfoService.selectsettleInfoNew(baseProviderSettle).size() > 0) {
            BaseProviderSettle settle = getProviderInfoService.selectsettleInfoNew(baseProviderSettle).get(0);
            claimCasePaymentVO.setClaimFlag(settle.getClaimFlag());
        }
        claimCaseForeignPayVO.setPayment(claimCasePaymentVO);
        claimCaseForeignPayVO.setPayFlag(payFlag);
        claimCaseForeignPayVO.setBorrowFlag(borrowFlag);
        return claimCaseForeignPayVO;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

}
