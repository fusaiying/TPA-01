package com.paic.ehis.claimapt.service.Impl;


import com.paic.ehis.claimapt.domain.*;
import com.paic.ehis.claimapt.domain.DTO.BatchDTO;
import com.paic.ehis.claimapt.domain.DTO.BatchRecordDTO;
import com.paic.ehis.claimapt.domain.DTO.ClaimBatchDTO;
import com.paic.ehis.claimapt.domain.Vo.BatchVo;
import com.paic.ehis.claimapt.mapper.*;
import com.paic.ehis.claimapt.service.IClaimBatchService;
import com.paic.ehis.common.core.enums.ClaimStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * 理赔批次 Service业务层处理
 *
 * @author sino
 * @date 2021-01-05
 */
@Service
public class ClaimBatchServiceImpl implements IClaimBatchService {
    @Autowired
    private ClaimBatchMapper claimBatchMapper;

    @Autowired
    private ClaimBatchRecordMapper claimBatchRecordMapper;

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;

    @Autowired
    private ClaimBatchInvoiceFilingMapper claimBatchInvoiceFilingMapper;

    @Autowired SysUserMapper sysUserMapper;

    /**
     * 查询理赔批次
     *
     * @param batchNo 理赔批次 ID
     * @return 理赔批次
     */
    @Override
    public ClaimBatch selectClaimBatchById(String batchNo) {
        return claimBatchMapper.selectClaimBatchById(batchNo);
    }

    /**
     * 查询理赔批次 列表
     *
     * @param claimBatch 理赔批次
     * @return 理赔批次
     */
    @Override
    public List<ClaimBatch> selectClaimBatchList(ClaimBatch claimBatch) {
        claimBatch.setStatus(ClaimStatus.DATAYES.getCode());
        return claimBatchMapper.selectClaimBatchList(claimBatch);
    }


    /**
     * 查询已退回理赔批次 列表
     *
     * @param batchDTO 理赔批次
     * @return 理赔批次 集合
     */
    @Override
    public List<BatchVo> selectBackToBatchList(BatchDTO batchDTO) {
        batchDTO.setStatus(ClaimStatus.DATAYES.getCode());
        batchDTO.setBatchstatus(ClaimStatus.BATCHRETURN.getCode());
        if (StringUtils.isNull(batchDTO.getUpdateBy())) {
            batchDTO.setUpdateBy(SecurityUtils.getUsername());
        }
        return claimBatchMapper.selectDirectQueryList(batchDTO);
    }

    /**
     * 查询已处理理赔批次 列表
     *
     * @param batchDTO 理赔批次
     * @return 理赔批次 集合
     */
    @Override
    public List<BatchVo> selectDealWithBatchList(BatchDTO batchDTO) {
        if (StringUtils.isNotNull(batchDTO.getSubmitstartdate()) || StringUtils.isNotEmpty(batchDTO.getOrgancode())
                || StringUtils.isNotEmpty(batchDTO.getHospitalname()) || StringUtils.isNotNull(batchDTO.getUpdatestartTime())
                || StringUtils.isNotEmpty(batchDTO.getBatchno()) || StringUtils.isNotEmpty(batchDTO.getClaimtype()) || StringUtils.isNotEmpty(batchDTO.getUpdateBy())) {
//            机构层级  查询 暂未是实现
            Long userId = SecurityUtils.getUserId();
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            // 获取用户的所属机构
            batchDTO.setOrgancode( sysUser.getDeptId().toString());
        } else {
            batchDTO.setUpdateBy(SecurityUtils.getUsername());
        }
        batchDTO.setStatus("Y");
        batchDTO.setBatchstatus("'01','02','03','05'");
        if (StringUtils.isNull(batchDTO.getUpdatestartTime())) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DATE) - 30);
            batchDTO.setUpdatestartTime(calendar.getTime());
            batchDTO.setUpdateendTime(DateUtils.parseDate(DateUtils.getTime()));
        }
        return claimBatchMapper.selectDealWithBatchList(batchDTO);

    }

    /**
     * 查询直结复核理赔批次公共池 列表
     *
     * @param batchDTO 理赔批次
     * @return 交单复核理赔批次 集合
     */
    @Override
    public List<BatchVo> selectReviewPublicList(BatchDTO batchDTO) {
        Long userId = SecurityUtils.getUserId();
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        // 获取用户的所属机构
        batchDTO.setOrgancode( sysUser.getDeptId().toString());
        batchDTO.setClaimtype("01");
        batchDTO.setStatus(ClaimStatus.DATAYES.getCode());
        batchDTO.setBatchstatus(ClaimStatus.BATCHTENDER.getCode());
        return claimBatchMapper.selectDirectQueryList(batchDTO);
    }

    /**
     * 查询直结复核理赔批次待处理个人池 列表
     *
     * @param
     * @return 交单复核理赔批次 集合
     */
    @Override
    public List<BatchVo> selectUntreatedPersonalList(BatchDTO batchDTO) {
        batchDTO.setStatus(ClaimStatus.DATAYES.getCode());
        batchDTO.setBatchstatus(ClaimStatus.BATCHREVIEW.getCode());
        batchDTO.setUpdateBy(SecurityUtils.getUsername());
        return claimBatchMapper.selectDirectQueryList(batchDTO);
    }

    /**
     * 查询直结复核理赔批次已处理个人池 列表
     *
     * @param
     * @return 交单复核理赔批次 集合
     */
    @Override
    public List<BatchVo> selectProcessedPersonalList(BatchRecordDTO batchRecordDTO) {
        batchRecordDTO.setStatus(ClaimStatus.DATAYES.getCode());
        batchRecordDTO.setBatchstatus("'03','04','05'");
        batchRecordDTO.setClaimtype("01");
        batchRecordDTO.setOperation(ClaimStatus.BATCHRETURN.getCode());
        batchRecordDTO.setUpdateBy(SecurityUtils.getUsername());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DATE) - 1);
        batchRecordDTO.setSubmitstartdate(DateUtils.parseDate(calendar.getTime()));
        batchRecordDTO.setSubmitenddate(DateUtils.parseDate(DateUtils.getTime()));
        return claimBatchMapper.selectStraightAndReview(batchRecordDTO);
    }

    /**
     * 新增理赔批次
     *
     * @param claimBatch 理赔批次
     * @return 结果
     */
    @Override
    public int insertClaimBatch(ClaimBatch claimBatch) {
        claimBatch.setCreateTime(DateUtils.getNowDate());
        return claimBatchMapper.insertClaimBatch(claimBatch);
    }

    /**
     * 交单复核无效化处理理赔批次
     *
     * @param batchno 理赔批次
     * @return 结果
     */
    @Override
    public int updateClaimBatchstatus(String batchno) {
        ClaimBatch claimBatch = new ClaimBatch();
        claimBatch.setBatchno(batchno);
        claimBatch.setBatchstatus(ClaimStatus.BATCHINVALID.getCode());
        claimBatch.setUpdateBy(SecurityUtils.getUsername());
        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));


        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        claimBatchRecord.setBatchno(batchno);
        claimBatchRecord.setOperation(ClaimStatus.BATCHINVALID.getCode());
        claimBatchRecord.setStatus(ClaimStatus.DATAYES.getCode());
        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.getNowDate());
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.getNowDate());
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
        return claimBatchMapper.updateClaimBatch(claimBatch);
    }

    /**
     * 直接交单复核-直结-处理
     *
     * @param claimBatchDTO 理赔批次
     * @return
     */
    @Override
    public int updateClaimBatch(ClaimBatchDTO claimBatchDTO) {

        //生成案件信息
        ClaimCase claimCase = new ClaimCase();
        //案件操作记录表
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        //得到前端传过来的理赔流程批次表
        ClaimBatchRecord claimBatchRecord = claimBatchDTO.getClaimBatchRecord();
        //得到前端传过来的批次信息
        ClaimBatch claimBatch = claimBatchDTO.getClaimBatch();
        //将原有的理赔批次流程记录表记录变为N
        List<ClaimBatchRecord> claimBatchRecords = claimBatchRecordMapper.selectSysClaimBatchRecordListByBatchnoOne(claimBatch.getBatchno());
        if (claimBatchRecords.size() != 0) {
            for (ClaimBatchRecord claimBatchRecordsOne : claimBatchRecords) {
                ClaimBatchRecord claimBatchRecord1 = new ClaimBatchRecord();
                claimBatchRecord1.setRecordid(claimBatchRecordsOne.getRecordid());
                claimBatchRecord1.setStatus(ClaimStatus.DATANO.getCode());//N
                claimBatchRecordMapper.updateClaimBatchRecord(claimBatchRecord1);
            }
        }
        //得到复核结论
        String conclusion = claimBatchRecord.getConclusion();
        if (conclusion.equals("01")) {
            //结论为通过时
            claimBatch.setBatchstatus(ClaimStatus.BATCHFINISH.getCode());//03
            //新增批次信息轨迹
            claimBatchRecord.setBatchno(claimBatch.getBatchno());//批次号
            claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
            claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
            claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatchRecord.setOperation(ClaimStatus.BATCHFINISH.getCode());//03
            claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
            //生成对应的案件信息数据（报案号）
            for (int i = 0; i < claimBatch.getCasenum(); i++) {
                //新增案件信息数据
                String rptNo = "96" + "JGH0X" + PubFun.createMySqlMaxNoUseCache("RPTCODE", 10, 10);//报案号
                claimCase.setBatchNo(claimBatch.getBatchno());
                claimCase.setRptNo(rptNo);
                claimCase.setFilingNo("JGHDQQW" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 10));//归档号
                claimCase.setStatus(ClaimStatus.DATAYES.getCode());//Y
                claimCase.setCaseStatus(ClaimStatus.CASEACCEPTED.getCode());//05
                claimCase.setCreateBy(SecurityUtils.getUsername());
                claimCase.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
                claimCase.setUpdateBy(SecurityUtils.getUsername());
                claimCase.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
                claimCaseMapper.insertClaimCase(claimCase);
                //生成新的案件操作记录表
                claimCaseRecord.setRptNo(rptNo);
                claimCaseRecord.setOperator(SecurityUtils.getUsername());//操作人
                claimCaseRecord.setOperation(ClaimStatus.CASEACCEPTED.getCode());//操作节点05-受理
                claimCaseRecord.setHistoryFlag(ClaimStatus.DATANO.getCode());//N
                claimCaseRecord.setStatus(ClaimStatus.DATAYES.getCode());//Y
                claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
                claimCaseRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
                claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
                claimCaseRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
                claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
            }
            //将批次信息改变为：交单完成
            return claimBatchMapper.updateClaimBatch(claimBatch);
        } else if (conclusion.equals("02")) {
            //结论为不通过时
            claimBatch.setBatchstatus(ClaimStatus.BATCHRETURN.getCode());//04-交单退回
            //生成新的理赔批次流程记录表
            claimBatchRecord.setBatchno(claimBatch.getBatchno());//批次号
            claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
            claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
            claimBatchRecord.setOperation(ClaimStatus.DATAYES.getCode());//Y
            claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatchRecord.setOperation(ClaimStatus.BATCHRETURN.getCode());//04
            claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
            //将批次信息改变为：交单退回
            return claimBatchMapper.updateClaimBatch(claimBatch);
        }
        return 0;
    }


    /**
     * 批量删除理赔批次
     *
     * @param batchNos 需要删除的理赔批次 ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchByIds(String[] batchNos) {
        return claimBatchMapper.deleteClaimBatchByIds(batchNos);
    }

    /**
     * 删除理赔批次 信息
     * k
     *
     * @param batchNo 理赔批次 ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchById(String batchNo) {
        return claimBatchMapper.deleteClaimBatchById(batchNo);
    }

    /*************************************/
    /**
     * 直结交单-新增-新增理赔批次
     *
     * @param standingAndBatck 理赔批次
     * @return 结果
     */
    @Override
    public StandingAndBatck insertSysClaimBatch(StandingAndBatck standingAndBatck)//
    {
        ClaimBatch claimBatch = standingAndBatck.getClaimBatch();
        claimBatch.setBatchstatus(ClaimStatus.BATCHTENDER.getCode());//01
        //批次号
        String str1 = "JGH" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 8);
        claimBatch.setBatchno(str1);
        claimBatch.setCreateBy(SecurityUtils.getUsername());
        claimBatch.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatch.setUpdateBy(SecurityUtils.getUsername());
        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatch.setStatus(ClaimStatus.DATAYES.getCode());//Y

        //新增理赔批次流程记录
        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord.setBatchno(str1);

        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setOperation(ClaimStatus.BATCHTENDER.getCode());//01
        claimBatchRecord.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

        claimBatchMapper.insertClaimBatch(claimBatch);

        standingAndBatck.setClaimBatch(claimBatch);

        return standingAndBatck;
    }

    /**
     * 机构交单-直结-新增
     *
     * @param claimBatch 理赔批次
     * @return
     */
    @Override
    public ClaimBatch insertSysClaimBatchTwo(ClaimBatch claimBatch) {
        claimBatch.setBatchstatus(ClaimStatus.BATCHTENDER.getCode());//01
        //批次号
        String str1 = "JGH" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 8);
        claimBatch.setBatchno(str1);

        claimBatch.setCreateBy(SecurityUtils.getUsername());
        claimBatch.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatch.setUpdateBy(SecurityUtils.getUsername());
        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));

        //二条
        ClaimBatchRecord claimBatchRecord1 = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord1.setBatchno(str1);
        claimBatchRecord1.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord1.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord1.setUpdateBy("");
        claimBatchRecord1.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord1.setOperation(ClaimStatus.BATCHREVIEW.getCode());//02
        claimBatchRecord1.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord1);

        claimBatch.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimBatchMapper.insertClaimBatch(claimBatch);

        //生成发表归档信息
        ClaimBatchInvoiceFiling claimBatchInvoiceFiling = new ClaimBatchInvoiceFiling();
        claimBatchInvoiceFiling.setBatchNo(str1);
        String billrecevieflag = claimBatch.getBillrecevieflag();
        if(StringUtils.isBlank(billrecevieflag)) {
            claimBatchInvoiceFiling.setIsFiling("02");
        } else {
            claimBatchInvoiceFiling.setIsFiling(billrecevieflag);
        }
        claimBatchInvoiceFiling.setStatus("Y");
        claimBatchInvoiceFiling.setCreateBy(SecurityUtils.getUsername());
        claimBatchInvoiceFiling.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchInvoiceFiling.setUpdateBy(SecurityUtils.getUsername());
        claimBatchInvoiceFiling.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchInvoiceFilingMapper.insertClaimBatchInvoiceFiling(claimBatchInvoiceFiling);

        return claimBatch;
    }

    /**
     * 机构交单-直结 改值
     *
     * @param claimBatch
     * @return
     */
    @Override
    public ClaimBatch updateClaimBatchTwo(ClaimBatch claimBatch) {//
        claimBatch.setBatchstatus(ClaimStatus.BATCHREVIEW.getCode());//02

        claimBatch.setUpdateBy(SecurityUtils.getUsername());
        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));

        //新增理赔批次流程记录
        //一条
        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord.setBatchno(claimBatch.getBatchno());
        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setOperation(ClaimStatus.BATCHREVIEW.getCode());//02为提交
        claimBatchRecord.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

        claimBatch.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimBatchMapper.updateClaimBatch(claimBatch);
        return claimBatch;
    }

    /**
     * @param standingAndBatck 理赔批次
     * @return 结果
     */
    @Override
    public StandingAndBatck updateSysClaimBatch(StandingAndBatck standingAndBatck) {
        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord.setBatchno(standingAndBatck.getClaimBatch().getBatchno());
        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setOperation(ClaimStatus.BATCHTENDER.getCode());//01
        claimBatchRecord.setStatus(ClaimStatus.DATAYES.getCode());//Y
        //查询之前的记录
        ClaimBatchRecord claimBatchRecord1 = claimBatchRecordMapper.selectClaimBatchRecordByBatch(claimBatchRecord);
        if (claimBatchRecord1 != null) {
            claimBatchRecord.setRecordid(claimBatchRecord1.getRecordid());
            claimBatchRecordMapper.updateClaimBatchRecord(claimBatchRecord);

            claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
            claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
            standingAndBatck.getClaimBatch().setUpdateBy(SecurityUtils.getUsername());
            standingAndBatck.getClaimBatch().setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            standingAndBatck.getClaimBatch().setStatus(ClaimStatus.DATAYES.getCode());//Y
            claimBatchMapper.updateClaimBatch(standingAndBatck.getClaimBatch());
        }
        return standingAndBatck;
    }

    /**
     *
     * @param standingAndBatck 理赔批次
     * @return
     */
    @Override
    public StandingAndBatck updateSysClaimBatchPresent(StandingAndBatck standingAndBatck) {
        ClaimBatch claimBatch = standingAndBatck.getClaimBatch();

        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord.setBatchno(claimBatch.getBatchno());
        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setOperation(ClaimStatus.BATCHTENDER.getCode());//01为提交
        claimBatchRecord.setStatus(ClaimStatus.DATAYES.getCode());//Y
        ClaimBatchRecord claimBatchRecord1 = claimBatchRecordMapper.selectClaimBatchRecordByBatch(claimBatchRecord);
        if (claimBatchRecord1 != null) {
            claimBatchRecord.setRecordid(claimBatchRecord1.getRecordid());
            claimBatchRecord.setStatus(ClaimStatus.DATAYES.getCode());//Y
            claimBatchRecordMapper.updateClaimBatchRecord(claimBatchRecord);

            claimBatchRecord.setOperation(ClaimStatus.BATCHFINISH.getCode());//03
            claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

            claimBatch.setUpdateBy(SecurityUtils.getUsername());
            claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatch.setBatchstatus(ClaimStatus.BATCHFINISH.getCode());// 03-交单完成
            claimBatch.setStatus(ClaimStatus.DATAYES.getCode());//Y
            claimBatchMapper.updateClaimBatch(claimBatch);
        }

        //生成发表归档信息
        ClaimBatchInvoiceFiling claimBatchInvoiceFiling = new ClaimBatchInvoiceFiling();
        claimBatchInvoiceFiling.setBatchNo(claimBatch.getBatchno());
        claimBatchInvoiceFiling.setStatus("Y");
        claimBatchInvoiceFiling.setCreateBy(SecurityUtils.getUsername());
        claimBatchInvoiceFiling.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchInvoiceFiling.setUpdateBy(SecurityUtils.getUsername());
        claimBatchInvoiceFiling.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchInvoiceFilingMapper.insertClaimBatchInvoiceFiling(claimBatchInvoiceFiling);

        return standingAndBatck;
    }

}
