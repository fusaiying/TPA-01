package com.paic.ehis.claimflow.service.impl;

import com.alibaba.fastjson.JSON;
import com.paic.ehis.claimflow.domain.ClaimCaseBill;
import com.paic.ehis.claimflow.domain.ClaimCaseBillDetail;
import com.paic.ehis.claimflow.domain.ClaimCaseBillDiagnosis;
import com.paic.ehis.claimflow.domain.ClaimCaseInvestigation;
import com.paic.ehis.claimflow.domain.dto.ClaimCaseDTO;
import com.paic.ehis.claimflow.domain.vo.BillAccomplishVo;
import com.paic.ehis.claimflow.domain.vo.BillProcessingVo;
import com.paic.ehis.claimflow.domain.vo.ClaimCaseBillInfoVO;
import com.paic.ehis.claimflow.domain.vo.IcdCode;
import com.paic.ehis.claimflow.mapper.*;
import com.paic.ehis.claimflow.service.IClaimCaseBillService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.ClaimCalService;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.domain.ClaimCasePolicy;
import com.paic.ehis.system.api.domain.ClaimProductFeeitem;
import com.paic.ehis.system.api.domain.SysOrganInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 案件账单明细Service业务层处理
 * 
 * @author sino
 * @date 2021-01-15
 */
@Service
public class ClaimCaseBillServiceImpl implements IClaimCaseBillService
{
    @Autowired
    private ClaimCaseBillMapper claimCaseBillMapper;
    @Autowired
    private ClaimCaseMapper claimCaseMapper;
    @Autowired
    private ClaimCaseInvestigationMapper claimCaseInvestigationMapper;
    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;
    @Autowired
    private ClaimCasePolicyMapper claimCasePolicyMapper;
    @Autowired
    private PolicyInfoMapper policyInfoMapper;
    @Autowired
    private ClaimCaseBillDiagnosisMapper claimCaseBillDiagnosisMapper;
    @Autowired
    private ClaimCaseBillDetailMapper claimCaseBillDetailMapper;
    @Autowired
    private ClaimCalService claimCalService;
    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 查询案件账单明细
     * 
     * @param billId 案件账单明细ID
     * @return 案件账单明细
     */
    @Override
    public ClaimCaseBillInfoVO selectClaimCaseBillById(Long billId)
    {
        ClaimCaseBillInfoVO billInfo = new ClaimCaseBillInfoVO();
        // 1.查询账单明细
        ClaimCaseBill claimCaseBill = claimCaseBillMapper.selectClaimCaseBillById(billId);
        // 2.查询诊断信息
        List<ClaimCaseBillDiagnosis> diagnosisList = claimCaseBillDiagnosisMapper.selectClaimCaseBillDetailByBillId(billId);
        List<IcdCode> icdCodes = new ArrayList<IcdCode>();
        for (ClaimCaseBillDiagnosis diagnosis : diagnosisList){
            if ("01".equals(diagnosis.getDigType())){
                claimCaseBill.setIcdCode(diagnosis.getIcdCode());
            } else {
                IcdCode icdCode = new IcdCode();
                icdCode.setIcdCode(diagnosis.getIcdCode());
                icdCodes.add(icdCode);
            }
        }
        if (icdCodes.size() > 0){
            claimCaseBill.setIcdCodes(icdCodes);
        }
        // 3.查询费用项明细
        List<ClaimCaseBillDetail> detailList = claimCaseBillDetailMapper.selectClaimCaseBillDetailByBillId(billId);

        billInfo.setBill(claimCaseBill);
        billInfo.setBillDetail(detailList);
        return billInfo;
    }

    /**
     * 查询案件账单明细列表
     * 
     * @param claimCaseBill 案件账单明细
     * @return 案件账单明细
     */
    @Override
    public List<ClaimCaseBill> selectClaimCaseBillList(ClaimCaseBill claimCaseBill)
    {
        return claimCaseBillMapper.selectClaimCaseBillList(claimCaseBill);
    }

    /**
     * 新增案件账单明细
     * 
     * @param claimCaseBill 案件账单明细
     * @return 结果
     */
    @Override
    public int insertClaimCaseBill(ClaimCaseBillInfoVO claimCaseBill)
    {
        String username = SecurityUtils.getUsername();
        // 1、获取账单明细表信息
        ClaimCaseBill billInfo = claimCaseBill.getBill();
        // 生成账单ID
        String billID = PubFun.createMySqlMaxNoUseCache("billID", 12, 20);
        billInfo.setBillId(Long.parseLong(billID));
        billInfo.setStatus("Y");
        billInfo.setCreateBy(username);
        billInfo.setCreateTime(DateUtils.getNowDate());
        // 2、获取诊断信息
        ClaimCaseBillDiagnosis claimCaseBillDiagnosis = new ClaimCaseBillDiagnosis();
        // 主要诊断
        claimCaseBillDiagnosis.setBillId(Long.parseLong(billID));
        claimCaseBillDiagnosis.setRptNo(billInfo.getRptNo());
        claimCaseBillDiagnosis.setDigType("01");
        claimCaseBillDiagnosis.setIcdCode(billInfo.getIcdCode());
        claimCaseBillDiagnosis.setStatus("Y");
        claimCaseBillDiagnosis.setCreateBy(username);
        claimCaseBillDiagnosis.setCreateTime(DateUtils.getNowDate());
        claimCaseBillDiagnosisMapper.insertClaimCaseBillDiagnosis(claimCaseBillDiagnosis);
        // 次要诊断
        List<IcdCode> icdCodes = billInfo.getIcdCodes();
        for (int i=0;i<icdCodes.size();i++){
            if (!"".equals(icdCodes.get(i).getIcdCode()) && null != icdCodes.get(i).getIcdCode()) {
                ClaimCaseBillDiagnosis diagnosis = new ClaimCaseBillDiagnosis();
                diagnosis.setBillId(Long.parseLong(billID));
                diagnosis.setRptNo(billInfo.getRptNo());
                diagnosis.setDigType("02");
                diagnosis.setIcdCode(icdCodes.get(i).getIcdCode());
                diagnosis.setStatus("Y");
                diagnosis.setCreateBy(username);
                diagnosis.setCreateTime(DateUtils.getNowDate());
                claimCaseBillDiagnosisMapper.insertClaimCaseBillDiagnosis(diagnosis);
            }
        }
        // 自费金额
        BigDecimal selfAmount = new BigDecimal("0");
        // 部分自费
        BigDecimal partSelf = new BigDecimal("0");
        // 不合理金额
        BigDecimal unableAmount = new BigDecimal("0");

        // 3、获取费用项明细
        List<ClaimCaseBillDetail> billDetail = claimCaseBill.getBillDetail();
        for (ClaimCaseBillDetail detail : billDetail){
            selfAmount = selfAmount.add(detail.getSelfAmount() == null ? new BigDecimal("0.00") : detail.getSelfAmount());
            partSelf = partSelf.add(detail.getPartSelfAmount() == null ? new BigDecimal("0.00") : detail.getPartSelfAmount());
            unableAmount = unableAmount.add(detail.getUnableAmount() == null ? new BigDecimal("0.00") : detail.getUnableAmount());
            detail.setBillId(Long.parseLong(billID));
            detail.setRptNo(billInfo.getRptNo());
            detail.setStatus("Y");
            detail.setCreateBy(username);
            detail.setCreateTime(DateUtils.getNowDate());
            claimCaseBillDetailMapper.insertClaimCaseBillDetail(detail);
        }
        billInfo.setSelfAmount(selfAmount);
        billInfo.setPartSelfAmount(partSelf);
        billInfo.setUnableAmount(unableAmount);
        return claimCaseBillMapper.insertClaimCaseBill(billInfo);
    }

    /**
     * 修改案件账单明细
     * 
     * @param claimCaseBill 案件账单明细
     * @return 结果
     */
    @Override
    public int updateClaimCaseBill(ClaimCaseBillInfoVO claimCaseBill)
    {
        String username = SecurityUtils.getUsername();
        // 获取账单明细表信息
        ClaimCaseBill billInfo = claimCaseBill.getBill();
        Long billId = claimCaseBill.getBill().getBillId();
        // 将之前的诊断信息置为无效
        claimCaseBillDiagnosisMapper.updateClaimCaseBillDiagnosisByBillId(billId);
        // 获取诊断信息 - 新增
        ClaimCaseBillDiagnosis claimCaseBillDiagnosis = new ClaimCaseBillDiagnosis();
        // 主要诊断
        claimCaseBillDiagnosis.setBillId(billId);
        claimCaseBillDiagnosis.setRptNo(billInfo.getRptNo());
        claimCaseBillDiagnosis.setDigType("01");
        claimCaseBillDiagnosis.setIcdCode(billInfo.getIcdCode());
        claimCaseBillDiagnosis.setStatus("Y");
        claimCaseBillDiagnosis.setCreateBy(username);
        claimCaseBillDiagnosis.setCreateTime(DateUtils.getNowDate());
        claimCaseBillDiagnosisMapper.insertClaimCaseBillDiagnosis(claimCaseBillDiagnosis);
        // 次要诊断
        List<IcdCode> icdCodes = billInfo.getIcdCodes();
        for (int i=0;i<icdCodes.size();i++){
            if (!"".equals(icdCodes.get(i).getIcdCode()) && null != icdCodes.get(i).getIcdCode()) {
                ClaimCaseBillDiagnosis diagnosis = new ClaimCaseBillDiagnosis();
                diagnosis.setBillId(billId);
                diagnosis.setRptNo(billInfo.getRptNo());
                diagnosis.setDigType("02");
                diagnosis.setIcdCode(icdCodes.get(i).getIcdCode());
                diagnosis.setStatus("Y");
                diagnosis.setCreateBy(username);
                diagnosis.setCreateTime(DateUtils.getNowDate());
                claimCaseBillDiagnosisMapper.insertClaimCaseBillDiagnosis(diagnosis);
            }
        }

        // 自费金额
        BigDecimal selfAmount = new BigDecimal("0");
        // 部分自费
        BigDecimal partSelf = new BigDecimal("0");
        // 不合理金额
        BigDecimal unableAmount = new BigDecimal("0");

        // 3、获取费用项明细
        List<ClaimCaseBillDetail> billDetail = claimCaseBill.getBillDetail();
        for (ClaimCaseBillDetail detail : billDetail){
            selfAmount = selfAmount.add(detail.getSelfAmount() == null ? new BigDecimal("0.00") : detail.getSelfAmount());
            partSelf = partSelf.add(detail.getPartSelfAmount() == null ? new BigDecimal("0.00") : detail.getPartSelfAmount());
            unableAmount = unableAmount.add(detail.getUnableAmount() == null ? new BigDecimal("0.00") : detail.getUnableAmount());
//            detail.setBillId(billId);
//            detail.setRptNo(billInfo.getRptNo());
//            detail.setStatus("Y");
            detail.setUpdateBy(username);
            detail.setUpdateTime(DateUtils.getNowDate());
            claimCaseBillDetailMapper.updateClaimCaseBillDetail(detail);
        }
        billInfo.setSelfAmount(selfAmount);
        billInfo.setPartSelfAmount(partSelf);
        billInfo.setUnableAmount(unableAmount);
        return claimCaseBillMapper.updateClaimCaseBill(billInfo);
    }

    /**
     * 批量删除案件账单明细
     * 
     * @param billIds 需要删除的案件账单明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseBillByIds(Long[] billIds)
    {
        return claimCaseBillMapper.deleteClaimCaseBillByIds(billIds);
    }

    /**
     * 删除案件账单明细信息
     * 
     * @param billId 案件账单明细ID
     * @return 结果
     */
    @Override
    public int deleteClaimCaseBillById(Long billId)
    {
        return claimCaseBillMapper.deleteClaimCaseBillById(billId);
    }

    /** 查询处理中工作池
     *
     * @param claimCaseDTO
     * @return
     */
    @Override
    public List<BillProcessingVo> selectProcessingList(ClaimCaseDTO claimCaseDTO) {
        // 06 录入状态
        claimCaseDTO.setCaseStatus("06");
        claimCaseDTO.setUpdateBy(SecurityUtils.getUsername());
        claimCaseDTO.setStatus("Y");
        // 获取批次号、报案号、案件状态、被保人姓名、停留时长、提交用户
        List<BillProcessingVo> caseList = claimCaseMapper.selectBillProcessing(claimCaseDTO);
        // 获取监控时效、是否调查、承保机构、出单公司
        for (BillProcessingVo caseInfo : caseList){
            // 监控时效
            caseInfo.setMonitorAging(claimCaseRecordMapper.selectMinAcceptTime(caseInfo.getRptNo()));
            // 是否调查
            List<ClaimCaseInvestigation> claimCaseInvestigation = claimCaseInvestigationMapper.selectClaimCaseInvestigationByIdOne(caseInfo.getRptNo());
            if (claimCaseInvestigation.size() > 0){
                caseInfo.setInvestigation("01");
            } else {
                caseInfo.setInvestigation("02");
            }
            // 出单公司、承保机构
            List<ClaimCasePolicy> claimCasePolicyList = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(caseInfo.getRptNo());
            // 出单公司拼接
            List<String> companyNameList = claimCasePolicyList.stream().map(ClaimCasePolicy::getCompanyName).collect(Collectors.toList());
            LinkedHashSet<String> companyNameSet = new LinkedHashSet<>(companyNameList);
            ArrayList<String> companyName = new ArrayList<>(companyNameSet);
            String cs = StringUtils.join(companyName, "|");
            // 承保机构 取sys_dept
            List<String> manageComList = claimCasePolicyList.stream().map(ClaimCasePolicy::getPolicyManageCom).collect(Collectors.toList());
            LinkedHashSet<String> manageComSet = new LinkedHashSet<>(manageComList);
            ArrayList<String> manageComs = new ArrayList<>(manageComSet);
            Object data = remoteUserService.getOrganInfo(manageComs).get(AjaxResult.DATA_TAG);
            List<SysOrganInfoVO> sysOrganInfoList = new ArrayList<>();
            if(data != null){
                String jsonOrganInfoStr = JSON.toJSONString(data);
                sysOrganInfoList = JSON.parseArray(jsonOrganInfoStr, SysOrganInfoVO.class);
                String ms = StringUtils.join(sysOrganInfoList, "|");
                caseInfo.setPolicyManageCom(ms);
            }
            caseInfo.setCompanyName(cs);
        }
        return caseList;
    }

    /**
     * 查询已处理工作池
     *
     * @param claimCaseDTO
     * @return
     */
    @Override
    public List<BillAccomplishVo> selectAccomplishList(ClaimCaseDTO claimCaseDTO) {
        // 06 录入状态
        claimCaseDTO.setCaseStatus("06");
        claimCaseDTO.setUpdateBy(SecurityUtils.getUsername());
        claimCaseDTO.setStatus("Y");
        claimCaseDTO.setUpdateDate(PubFun.getCurrentDate());
        // 获取批次号、报案号、案件状态、被保人姓名、提交用户
        List<BillAccomplishVo> accomplishList = claimCaseMapper.selectBillAccomplish(claimCaseDTO);
        // 获取是否调查、出单公司、承保机构
        for (BillAccomplishVo accomplishVo : accomplishList){
            // 是否调查
            List<ClaimCaseInvestigation> claimCaseInvestigation = claimCaseInvestigationMapper.selectClaimCaseInvestigationByIdOne(accomplishVo.getRptNo());
            if (claimCaseInvestigation.size() > 0){
                accomplishVo.setInvestigation("01");
            } else {
                accomplishVo.setInvestigation("02");
            }
            // 出单公司、承保机构
            List<ClaimCasePolicy> claimCasePolicyList = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(accomplishVo.getRptNo());
            // 出单公司拼接
            List<String> companyNameList = claimCasePolicyList.stream().map(ClaimCasePolicy::getCompanyName).collect(Collectors.toList());
            LinkedHashSet<String> companyNameSet = new LinkedHashSet<>(companyNameList);
            ArrayList<String> companyName = new ArrayList<>(companyNameSet);
            String cs = StringUtils.join(companyName, "|");
            accomplishVo.setCompanyName(cs);
            // 承保机构 取sys_dept
            List<String> manageComList = claimCasePolicyList.stream().map(ClaimCasePolicy::getPolicyManageCom).collect(Collectors.toList());
            LinkedHashSet<String> manageComSet = new LinkedHashSet<>(manageComList);
            ArrayList<String> manageComs = new ArrayList<>(manageComSet);
            Object data = remoteUserService.getOrganInfo(manageComs).get(AjaxResult.DATA_TAG);
            List<SysOrganInfoVO> sysOrganInfoList = new ArrayList<>();
            if(data != null){
                String jsonOrganInfoStr = JSON.toJSONString(data);
                sysOrganInfoList = JSON.parseArray(jsonOrganInfoStr, SysOrganInfoVO.class);
                String ms = StringUtils.join(sysOrganInfoList, "|");
                accomplishVo.setPolicyManageCom(ms);
            }
        }
        return accomplishList;
    }

    /**
     * 根据报案号查询账单明细表
     *
     * @param rptNo
     * @return
     */
    @Override
    public ClaimCaseBill selectClaimCaseBillListByRptNo(String rptNo) {
        return claimCaseBillMapper.selectClaimCaseBillListByRptNo(rptNo);
    }

    /**
     * 根据报案号查询费用项编码、费用项名称
     *
     * @param rptNo
     * @return
     */
    @Override
    public List<ClaimProductFeeitem> selectFeeitemList(String rptNo) {
        List<ClaimCasePolicy> claimCasePolicies = claimCasePolicyMapper.selectClaimCasePolicyByRptNo(rptNo);
        List<String> policyList = claimCasePolicies.stream().map(ClaimCasePolicy::getPolicyNo).collect(Collectors.toList());
        // 调用claimcal接口
        if (policyList.size()>0){
            return claimCalService.selectFeeitemByPolicys(claimCasePolicies);
        }
        return null;
    }

    @Override
    public boolean ClaimCal(String rptNo) {
        claimCalService.Calculate(rptNo);
        return true;
    }

    /**
     * 账单删除-主表及诊断表、明细表
     *
     * @param claimCaseBill
     * @return
     */
    @Override
    public int deleteClaimCaseBill(ClaimCaseBillInfoVO claimCaseBill) {
        Long billId = claimCaseBill.getBill().getBillId();
        claimCaseBillDiagnosisMapper.updateClaimCaseBillDiagnosisByBillId(billId);
        claimCaseBillDetailMapper.updateClaimCaseBillDetailByBillId(billId);
        return claimCaseBillMapper.updateClaimCaseBillById(billId);
    }
}
