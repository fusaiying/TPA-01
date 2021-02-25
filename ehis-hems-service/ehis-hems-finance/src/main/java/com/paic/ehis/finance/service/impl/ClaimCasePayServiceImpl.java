package com.paic.ehis.finance.service.impl;

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
import com.paic.ehis.system.api.domain.BaseProviderInfo;
import com.paic.ehis.system.api.domain.BaseProviderSettle;
import com.paic.ehis.finance.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对公支付
 */
@Service
public class ClaimCasePayServiceImpl implements IClaimCasePayService
{

    @Autowired
    private ClaimBatchMapper claimBatchMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
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

    /*
    * 财务appId
    **/

    @Value("${esg.financeid}")
    private String financeid;

    /**
     * 查询当前登录用户所属机构下支付状态不全为已支付的批次信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectInitList() {
        Long userId = SecurityUtils.getUserId();
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        // 获取用户的所属机构
        String deptId = sysUser.getDeptId().toString();
        List<Map<String,Object>> initList = claimBatchMapper.selectPayBatchInit(deptId);
        for (Map<String,Object> map : initList){
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            baseProviderInfo.setProviderCode(map.get("hospitalCode").toString());
            // 调用微服务获取医院名称
            R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
            if (R.FAIL == result.getCode())
            {
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
        if (ObjectNullUtil.objectIsNull(claimCasePayDTO)){
            Long userId = SecurityUtils.getUserId();
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            // 获取用户的所属机构
            claimCasePayDTO.setOrganCode( sysUser.getDeptId().toString());
        }
        List<Map<String, Object>> batchList = claimBatchMapper.selectPayBatchList(claimCasePayDTO);
        // 调用微服务获取医院中文名称
        for (Map<String,Object> map : batchList){
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            baseProviderInfo.setProviderCode(map.get("hospitalCode").toString());
            // 调用微服务获取医院名称
            R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
            if (R.FAIL == result.getCode())
            {
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
        // 获取案件信息
        List<ClaimCasePayInfoVO> payInfoList = claimCasePayMapper.selectPayInfoList(batchNo);
        // 获取出单公司、借款金额
        for (ClaimCasePayInfoVO payInfo : payInfoList) {
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
            if (null != financeBorrowInfo){
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
        claimCasePayVO.setCaseInfoList(payInfoList);

        // 轨迹表生成数据、支付总金额、理赔总金额
        BigDecimal payAmount = new BigDecimal("0.00");
        BigDecimal calAmount = new BigDecimal("0.00");
        for (ClaimCasePayInfoVO payInfoVO : payInfoList) {
            // 1、判断案件是可支付状态
            if ("01".equals(payInfoVO.getPayStatus())){
                // 获取支付金额 理赔金额
                ClaimCaseCal claimCaseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(payInfoVO.getRptNo());
                payAmount = payAmount.add(claimCaseCal.getPayAmount());
                calAmount = calAmount.add(claimCaseCal.getCalAmount());
                // 是否已存在轨迹为“支付环节”
                ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
                claimCaseRecord.setOperation("09");
                claimCaseRecord.setRptNo(payInfoVO.getRptNo());
                claimCaseRecord.setHistoryFlag("N");
                claimCaseRecord.setStatus("Y");
                List<ClaimCaseRecord> recordList = claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
                if (recordList.size() <= 0){
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
        // 获取支付信息
        ClaimCasePaymentVO claimCasePaymentVO = new ClaimCasePaymentVO();
        // 封装支付总金额、理赔总金额
        claimCasePaymentVO.setPayAmount(payAmount);
        claimCasePaymentVO.setCalAmount(calAmount);

        ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(batchNo);
        // 封装支付币种
        claimCasePaymentVO.setCurrency(claimBatch.getCurrency());
        // 调用微服务获取医院信息
        BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
        baseProviderInfo.setProviderCode(claimBatch.getHospitalcode());
        //调用医院接口
        R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
        if (R.FAIL == result.getCode())
        {
            throw new BaseException(result.getMsg());
        }
        if (result.getData().size() > 0) {
            BaseProviderInfo hospital = result.getData().get(0);
            //BaseProviderInfo hospital = (BaseProviderInfo) tableDataInfo.getRows().get(1);
            // 封装返回对象
            claimCasePaymentVO.setBank(hospital.getAccountName());//开户行
            claimCasePaymentVO.setBankName(hospital.getBankName());//账户名
            claimCasePaymentVO.setBankNumber(hospital.getBankCode());//账户号
        }
        // 获取‘是否仅结算理赔责任’ 是01-非全赔 否02-全赔
        BaseProviderSettle baseProviderSettle = new BaseProviderSettle();
        baseProviderInfo.setProviderCode(claimBatch.getHospitalcode());
        if (getProviderInfoService.selectsettleInfoNew(baseProviderSettle).size()>0) {
            BaseProviderSettle settle = getProviderInfoService.selectsettleInfoNew(baseProviderSettle).get(0);
            claimCasePaymentVO.setClaimFlag(settle.getClaimFlag());
        }
        claimCasePayVO.setPayment(claimCasePaymentVO);
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
        if ("97".equals(caseStatus) || "98".equals(caseStatus) || "02".equals(isAppeal) || "03".equals(isAppeal)){
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
    public AjaxResult confirmPayment(ClaimCaseForeignPayVO claimCasePayVO) {
        // 批次号
        String batchNo = claimCasePayVO.getBatchNo();
        // 外币 判断该批次下所有案件审核岗赔付结论中的账单币种是否一致
        if (!"CNY".equals(claimCasePayVO.getPayment().getCurrency())){
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
            if (currFlag){
                return AjaxResult.success("批次下案件币种不统一，请核实",3);
            }
        }
        ClaimCase financeCase = new ClaimCase();
        financeCase.setBatchNo(batchNo);
        financeCase.setIsFinanceBack("Y");
        financeCase.setStatus("Y");
        List<ClaimCase> finanBackList = claimCaseMapper.selectClaimCaseList(financeCase);
        if (finanBackList.size() > 0){
            return AjaxResult.error("此批次存在回退案件，请结案后进行支付");
        } else {
            String username = SecurityUtils.getUsername();
            SysUser sysUser = sysUserMapper.selectUserById(SecurityUtils.getUserId());
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
            financePayInfo.setDeptCode(sysUser.getDeptId().toString());
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
                    financePayDetailInfo.setDeptCode(sysUser.getDeptId().toString());
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


            return AjaxResult.success("确认支付成功！",1);
        }
    }

    /**
     * 对公支付-案件借款按钮
     *
     * @param claimCasePayVO
     * @return
     */
    @Override
    public AjaxResult borrowingCase(ClaimCaseForeignPayVO claimCasePayVO) {
        // 批次号
        String batchNo = claimCasePayVO.getBatchNo();
        // 外币 判断该批次下所有案件审核岗赔付结论中的账单币种是否一致
        if (!"CNY".equals(claimCasePayVO.getPayment().getCurrency())){
            boolean currFlag = claimCasePayVO.getCaseInfoList().stream().anyMatch(m -> "N".equals(m.getFlag()));
            if (currFlag){
                return AjaxResult.success("批次下案件币种不统一，请核实",3);
            }
        }
        ClaimCase financeCase = new ClaimCase();
        financeCase.setBatchNo(batchNo);
        financeCase.setIsFinanceBack("Y");
        financeCase.setStatus("Y");
        List<ClaimCase> finanBackList = claimCaseMapper.selectClaimCaseList(financeCase);
        if (finanBackList.size() > 0){
            return AjaxResult.success("此批次存在回退案件，请结案后进行支付",2);
        } else {
            String username = SecurityUtils.getUsername();
            SysUser sysUser = sysUserMapper.selectUserById(SecurityUtils.getUserId());
            List<ClaimCaseForeignPayInfoVO> caseInfoList = claimCasePayVO.getCaseInfoList();
            for (ClaimCaseForeignPayInfoVO caseInfo : caseInfoList) {
                // 支付状态置为可支付
                ClaimCase claimCase = new ClaimCase();
                claimCase.setRptNo(caseInfo.getRptNo());
                claimCase.setPayStatus("01");
                claimCaseMapper.updateClaimCase(claimCase);
                // 借款表生成数据
                FinanceBorrowInfo financeBorrowInfo = new FinanceBorrowInfo();
                financeBorrowInfo.setBatchNo(claimCasePayVO.getBatchNo());
                financeBorrowInfo.setRptNo(caseInfo.getRptNo());
                financeBorrowInfo.setBorrowAmount(caseInfo.getDiscountedAmount());
                financeBorrowInfo.setStatus("Y");
                financeBorrowInfo.setCreateBy(username);
                financeBorrowInfo.setCreateTime(DateUtils.getNowDate());
                financeBorrowInfo.setDeptCode(sysUser.getDeptId().toString());
                financeBorrowInfoMapper.insertFinanceBorrowInfo(financeBorrowInfo);
        }
            return AjaxResult.success("借款成功！",1);
        }
    }

    /**
     * 查询当前登录用户所属机构下状态不全为已支付的批次信息
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectInitForeignList() {
        Long userId = SecurityUtils.getUserId();
        // 获取用户的所属机构
        String deptId = sysUserMapper.selectUserById(userId).getDeptId().toString();
        List<Map<String,Object>> initList = claimBatchMapper.selectPayForeignBatchInit(deptId);
        for (Map<String,Object> map : initList){
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            baseProviderInfo.setProviderCode(map.get("hospitalCode").toString());
            // 调用微服务获取医院名称
            R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
            if (R.FAIL == result.getCode())
            {
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
        if (ObjectNullUtil.objectIsNull(claimCasePayDTO)){
            Long userId = SecurityUtils.getUserId();
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            // 获取用户的所属机构
            claimCasePayDTO.setOrganCode( sysUser.getDeptId().toString());
        }
        List<Map<String, Object>> batchList = claimBatchMapper.selectPayForeignBatchList(claimCasePayDTO);
        for (Map<String,Object> map : batchList){
            BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
            baseProviderInfo.setProviderCode(map.get("hospitalCode").toString());
            // 调用微服务获取医院名称
            R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);
            if (R.FAIL == result.getCode())
            {
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
        // 获取案件信息
        List<ClaimCaseForeignPayInfoVO> payInfoList = claimCasePayMapper.selectForeignPayInfoList(batchNo);
        // 获取批次账单币种
        String batchCurrency = claimBatchMapper.selectClaimBatchById(batchNo).getCurrency();
        // 获取出单公司、借款金额
        for (ClaimCaseForeignPayInfoVO payInfo : payInfoList) {
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
            if (null != financeBorrowInfo){
                payInfo.setBorrowAmount(financeBorrowInfo.getBorrowAmount());
            } else {
                payInfo.setBorrowAmount(new BigDecimal("0"));
            }

            // 判断账单币种与批次币种是否一致
            if (batchCurrency.equals(payInfo.getBillCurrency())){
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
            if ("01".equals(payInfoVO.getPayStatus())){
                // 获取支付金额 理赔金额 外币支付总金额
                ClaimCaseCal claimCaseCal = claimCaseCalMapper.selectClaimCaseCalByRptNo(payInfoVO.getRptNo());
                payAmount = payAmount.add(claimCaseCal.getPayAmount());
                calAmount = calAmount.add(claimCaseCal.getCalAmount());
                foreignPayAmount = foreignPayAmount.add(claimCaseCal.getPayAmountForeign());
                // 是否已存在轨迹为“支付环节”
                ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
                claimCaseRecord.setOperation("09");
                claimCaseRecord.setRptNo(payInfoVO.getRptNo());
                claimCaseRecord.setHistoryFlag("N");
                claimCaseRecord.setStatus("Y");
                List<ClaimCaseRecord> recordList = claimCaseRecordMapper.selectClaimCaseRecordList(claimCaseRecord);
                if (recordList.size() < 0){
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
        // 获取支付信息
        ClaimCasePaymentVO claimCasePaymentVO = new ClaimCasePaymentVO();
        // 封装支付总金额、理赔总金额、外币支付总金额
        claimCasePaymentVO.setPayAmount(payAmount);
        claimCasePaymentVO.setCalAmount(calAmount);

        ClaimBatch claimBatch = claimBatchMapper.selectClaimBatchById(batchNo);
        // 该批次下所有账单币种是否一致，不一致时支付币种与外币支付总金额为空
        boolean currFlag = payInfoList.stream().anyMatch(m -> "N".equals(m.getFlag()));
        if (!currFlag){
            // 封装支付币种
            claimCasePaymentVO.setCurrency(claimBatch.getCurrency());
            claimCasePaymentVO.setForeignPayAmount(foreignPayAmount);
        }

        // 调用微服务获取医院信息
        BaseProviderInfo baseProviderInfo = new BaseProviderInfo();
        baseProviderInfo.setProviderCode(claimBatch.getHospitalcode());
        //调用医院接口
        R<List<BaseProviderInfo>> result = getProviderInfoService.selectOrgInfo(baseProviderInfo);//调用医院插叙接口
        if (R.FAIL == result.getCode())
        {
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
        baseProviderInfo.setProviderCode(claimBatch.getHospitalcode());
        if (getProviderInfoService.selectsettleInfoNew(baseProviderSettle).size()>0) {
            BaseProviderSettle settle = getProviderInfoService.selectsettleInfoNew(baseProviderSettle).get(0);
            claimCasePaymentVO.setClaimFlag(settle.getClaimFlag());
        }
        claimCaseForeignPayVO.setPayment(claimCasePaymentVO);
        return claimCaseForeignPayVO;
    }

}
