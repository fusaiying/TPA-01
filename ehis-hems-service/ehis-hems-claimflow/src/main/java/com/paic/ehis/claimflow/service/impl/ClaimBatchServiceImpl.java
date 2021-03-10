package com.paic.ehis.claimflow.service.impl;


import com.paic.ehis.claimflow.domain.ClaimBatch;
import com.paic.ehis.claimflow.domain.dto.BatchNoRptNoDTO;
import com.paic.ehis.claimflow.domain.vo.BatchNoAndCaseNo;
import com.paic.ehis.claimflow.mapper.ClaimBatchMapper;
import com.paic.ehis.claimflow.service.IClaimBatchService;
import com.paic.ehis.common.core.enums.ClaimStatus;
import com.paic.ehis.common.core.enums.PbwOnlineP;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

//    /**
//     * 查询理赔批次 列表
//     *
//     * @param claimBatch 理赔批次
//     * @return 理赔批次
//     */
//    @Override
//    public List<ClaimBatch> selectClaimBatchList(ClaimBatch claimBatch) {
//        claimBatch.setStatus("Y");
//        return claimBatchMapper.selectClaimBatchList(claimBatch);
//    }
//
//
//    /**
//     * 查询已退回理赔批次 列表
//     *
//     * @param batchDTO 理赔批次
//     * @return 理赔批次 集合
//     */
//    @Override
//    public List<BatchVo> selectBackToBatchList(BatchDTO batchDTO) {
//        batchDTO.setStatus("Y");
//        batchDTO.setBatchstatus("04");
//        if (StringUtils.isNull(batchDTO.getUpdateBy())) {
//            batchDTO.setUpdateBy(SecurityUtils.getUsername());
//        }
//        return claimBatchMapper.selectBackToBatchList(batchDTO);
//    }
//
//    /**
//     * 查询已处理理赔批次 列表
//     *
//     * @param batchDTO 理赔批次
//     * @return 理赔批次 集合
//     */
//    @Override
//    public List<BatchVo> selectDealWithBatchList(BatchDTO batchDTO) {
//        if (StringUtils.isNull(batchDTO.getUpdateBy())) {
//            batchDTO.setUpdateBy(SecurityUtils.getUsername());
//        }
//        batchDTO.setStatus("Y");
//        batchDTO.setBatchstatus("'02','03','05'");
//        if (StringUtils.isNull(batchDTO.getUpdatestartTime())) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
//            batchDTO.setUpdatestartTime(calendar.getTime());
//            batchDTO.setUpdateendTime(DateUtils.parseDate(DateUtils.getTime()));
//        }
//        ;
//        return claimBatchMapper.selectDealWithBatchList(batchDTO);
//
//    }
//
//    /**
//     * 查询直结复核理赔批次公共池 列表
//     *
//     * @param batchRecordDTO 理赔批次
//     * @return 交单复核理赔批次 集合
//     */
//    @Override
//    public List<BatchVo> selectReviewPublicList(BatchRecordDTO batchRecordDTO) {
//        batchRecordDTO.setStatus("Y");
//        batchRecordDTO.setBatchstatus("02");
//        batchRecordDTO.setOperation("04");
//        batchRecordDTO.setUpdateBy("");
//        return claimBatchMapper.selectStraightAndReview(batchRecordDTO);
//    }
//
//    /**
//     * 查询直结复核理赔批次待处理个人池 列表
//     *
//     * @param
//     * @return 交单复核理赔批次 集合
//     */
//    @Override
//    public List<BatchVo> selectUntreatedPersonalList() {
//        BatchRecordDTO batchRecordDTO = new BatchRecordDTO();
//        batchRecordDTO.setStatus("Y");
//        batchRecordDTO.setBatchstatus("02");
//        batchRecordDTO.setOperation("04");
//        batchRecordDTO.setUpdateBy(SecurityUtils.getUsername());
//        return claimBatchMapper.selectStraightAndReview(batchRecordDTO);
//    }
//
//    /**
//     * 查询直结复核理赔批次已处理个人池 列表
//     *
//     * @param
//     * @return 交单复核理赔批次 集合
//     */
//    @Override
//    public List<BatchVo> selectProcessedPersonalList() {
//        BatchRecordDTO batchRecordDTO = new BatchRecordDTO();
//        batchRecordDTO.setStatus("Y");
//        batchRecordDTO.setBatchstatus("'03','04','05'");
//        batchRecordDTO.setClaimtype("01");
//        batchRecordDTO.setOperation("04");
//        batchRecordDTO.setUpdateBy(SecurityUtils.getUsername());
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
//        batchRecordDTO.setSubmitstartdate(DateUtils.parseDate(calendar.getTime()));
//        batchRecordDTO.setSubmitenddate(DateUtils.parseDate(DateUtils.getTime()));
//        return claimBatchMapper.selectStraightAndReview(batchRecordDTO);
//    }
//
    /**
     * 新增理赔批次
     *
     * @param claimBatch 理赔批次
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int insertClaimBatch(ClaimBatch claimBatch) {
        claimBatch.setCreateTime(DateUtils.getNowDate());
        return claimBatchMapper.insertClaimBatch(claimBatch);
    }

    @Override
    public int updateClaimBatchBybatchNo(String batchNo) {
        ClaimBatch claimBatch = new ClaimBatch();
        claimBatch.setBatchno(batchNo);
        claimBatch.setStatus(ClaimStatus.DATANO.getCode());//N
        return claimBatchMapper.updateClaimBatch(claimBatch);
    }

    /**
     * 新增理赔批次
     *
     * @param batchNoRptNoDTO 理赔批次
     * @return 结果
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public BatchNoAndCaseNo insertClaimBatchPbw(BatchNoRptNoDTO batchNoRptNoDTO) {

        String batchCount = batchNoRptNoDTO.getBatchCount();
        int i1 = Integer.parseInt(batchCount);

        String branchRegion = batchNoRptNoDTO.getBranchRegion();//机构交单编码

        //批次号-取前三位
        String substring = branchRegion.substring(0, 3);

        //报案号-取三四位
        String substring1 = branchRegion.substring(2, 4);

        //报案号取值
        String caseFlag = batchNoRptNoDTO.getCaseFlag();
        if (caseFlag == null || caseFlag == "") {
            caseFlag = PbwOnlineP.BatchSources0.getStatus();
        }

        BatchNoAndCaseNo batchNoRptNoVO = new BatchNoAndCaseNo();
        List<String> rptNoList = new ArrayList<>();

        for (int i = 0; i < i1; i++) {
            //报案号
            String bahtime = "96" + substring1 + caseFlag + "X" + PubFun.createMySqlMaxNoUseCache("RPTCODE", 10, 10);
            rptNoList.add(bahtime);
        }

        //批次号
        String str1 = substring + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 8);

        batchNoRptNoVO.setBatchNo(str1);//批次号
        batchNoRptNoVO.setDocunoList(rptNoList);//报案号集合
        Date nowDate = DateUtils.getNowDate();
        batchNoRptNoVO.setCreateBatchTime(nowDate);//批次生成日期

        ClaimBatch claimBatch = new ClaimBatch();
        claimBatch.setBatchno(str1);//批次号
        if (PbwOnlineP.BatchSourcesD.getStatus().equals(batchNoRptNoDTO.getCaseFlag())) {
            claimBatch.setSource(PbwOnlineP.P_sourcesOne.getStatus());//交单来源（01-在线交单，02-E结算）01
        } else {
            claimBatch.setSource(PbwOnlineP.P_sourcesTwo.getStatus());//交单来源（01-在线交单，02-E结算）02
        }
        claimBatch.setHospitalcode(batchNoRptNoDTO.getProvider());//医院编码
        claimBatch.setClaimtype(PbwOnlineP.Claim_typeOne.getStatus());//理赔类型(01-直结，02-事后)01
        claimBatch.setSubmitdate(batchNoRptNoDTO.getReceiveDate());//收单日期
        claimBatch.setCasenum(i1);//案件数量
        BigDecimal bd = new BigDecimal(batchNoRptNoDTO.getBatchAmount());
        claimBatch.setBatchtotal(bd);//批次总金额
        claimBatch.setOrgancode(batchNoRptNoDTO.getBranchRegion());//交单机构编码
        claimBatch.setBatchstatus(ClaimStatus.BATCHFINISH.getCode());//03
        claimBatch.setReceivedate(batchNoRptNoDTO.getReceiveDate());//接单日期
        claimBatch.setStatus(ClaimStatus.DATAYES.getCode());//Y
        claimBatch.setDirectReceiptSign(batchNoRptNoDTO.getDirectReceiptSign());//批次是否单张发票
        claimBatch.setCaseFlag(batchNoRptNoDTO.getCaseFlag());//案件第五位标识码
        claimBatch.setCreateBy(SecurityUtils.getUsername());
        claimBatch.setCreateTime(nowDate);
        //claimBatch.setUpdateBy(SecurityUtils.getUsername());
        //claimBatch.setUpdateTime(nowDate);
        claimBatchMapper.insertClaimBatchPbw(claimBatch);

        return batchNoRptNoVO;
    }
//
//    /**
//     * 交单复核无效化处理理赔批次
//     *
//     * @param batchno 理赔批次
//     * @return 结果
//     */
//    @Override
//    public int updateClaimBatchstatus(String batchno) {
//        ClaimBatch claimBatch = new ClaimBatch();
//        claimBatch.setBatchno(batchno);
//        claimBatch.setBatchstatus("05");
//        claimBatch.setUpdateBy(SecurityUtils.getUsername());
//        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        return claimBatchMapper.updateClaimBatch(claimBatch);
//    }
//
//    /**
//     * 直接交单复核-直结-处理
//     *
//     * @param claimBatchDTO 理赔批次
//     * @return
//     */
//    @Override
//    public int updateClaimBatch(ClaimBatchDTO claimBatchDTO) {
//
//        //生成案件信息
//        ClaimCase claimCase = new ClaimCase();
//        //案件操作记录表
//        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
//        //得到前端传过来的理赔流程批次表
//        ClaimBatchRecord claimBatchRecord = claimBatchDTO.getClaimBatchRecord();
//        //得到前端传过来的批次信息
//        ClaimBatch claimBatch = claimBatchDTO.getClaimBatch();
//        //将原有的理赔批次流程记录表记录变为N
//        List<ClaimBatchRecord> claimBatchRecords = claimBatchRecordMapper.selectSysClaimBatchRecordListByBatchnoOne(claimBatch.getBatchno());
//        if (claimBatchRecords.size() != 0) {
//            for (ClaimBatchRecord claimBatchRecordsOne : claimBatchRecords) {
//                ClaimBatchRecord claimBatchRecord1 = new ClaimBatchRecord();
//                claimBatchRecord1.setRecordid(claimBatchRecordsOne.getRecordid());
//                claimBatchRecord1.setStatus("N");
//                claimBatchRecordMapper.updateClaimBatchRecord(claimBatchRecord1);
//            }
//        }
//        //得到复核结论
//        String conclusion = claimBatchRecord.getConclusion();
//        if (conclusion.equals("01")) {
//            //结论为通过时
//            claimBatch.setBatchstatus("04");//04-交单完成
//            //新增案件批次信息
//            claimBatchRecord.setBatchno(claimBatch.getBatchno());//批次号
//            claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
//            claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//            claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
//            claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//            claimBatchRecord.setOperation("03");//03-复核完毕
//            claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
//            //生成对应的案件信息数据（报案号）
//            for (int i = 0; i < claimBatch.getCasenum(); i++) {
//                //新增案件信息数据
//                String rptNo = "96" + "JGH0X" + PubFun.createMySqlMaxNoUseCache("RPTCODE", 10, 10);//报案号
//                claimCase.setBatchNo(claimBatch.getBatchno());
//                claimCase.setRptNo(rptNo);
//                claimCase.setFilingNo("JGHDQQW" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 10));//归档号
//                claimCase.setStatus("Y");
//                claimCase.setCaseStatus("05");
//                claimCase.setCreateBy(SecurityUtils.getUsername());
//                claimCase.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//                claimCase.setUpdateBy(SecurityUtils.getUsername());
//                claimCase.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//                claimCaseMapper.insertClaimCase(claimCase);
//                //生成新的案件操作记录表
//                claimCaseRecord.setRptNo(rptNo);
//                claimCaseRecord.setOperator(SecurityUtils.getUsername());//操作人
//                claimCaseRecord.setOperation("05");//操作节点05-受理
//                claimCaseRecord.setHistoryFlag("N");
//                claimCaseRecord.setStatus("Y");
//                claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
//                claimCaseRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//                claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
//                claimCaseRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//                claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
//            }
//            //将批次信息改变为：交单完成
//            return claimBatchMapper.updateClaimBatch(claimBatch);
//        } else if (conclusion.equals("02")) {
//            //结论为不通过时
//            claimBatch.setBatchstatus("02");//02-交单退回
//            //生成新的理赔批次流程记录表
//            claimBatchRecord.setBatchno(claimBatch.getBatchno());//批次号
//            claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
//            claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//            claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
//            claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//            claimBatchRecord.setOperation("03");//03复核完毕
//            claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
//            //将批次信息改变为：交单退回
//            return claimBatchMapper.updateClaimBatch(claimBatch);
//        }
//        return 0;
//    }
//
//
//    /**
//     * 批量删除理赔批次
//     *
//     * @param batchNos 需要删除的理赔批次 ID
//     * @return 结果
//     */
//    @Override
//    public int deleteClaimBatchByIds(String[] batchNos) {
//        return claimBatchMapper.deleteClaimBatchByIds(batchNos);
//    }
//
//    /**
//     * 删除理赔批次 信息
//     * k
//     *
//     * @param batchNo 理赔批次 ID
//     * @return 结果
//     */
//    @Override
//    public int deleteClaimBatchById(String batchNo) {
//        return claimBatchMapper.deleteClaimBatchById(batchNo);
//    }
//
//    /*************************************/
//    /**
//     * 直结交单-新增-新增理赔批次
//     *
//     * @param standingAndBatck 理赔批次
//     * @return 结果
//     */
//    @Override
//    public StandingAndBatck insertSysClaimBatch(StandingAndBatck standingAndBatck)//
//    {
//        ClaimBatch claimBatch = standingAndBatck.getClaimBatch();
//        claimBatch.setBatchstatus("01");
//        //批次号
//        String str1 = "JGH" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 8);
//        claimBatch.setBatchno(str1);
//        claimBatch.setCreateBy(SecurityUtils.getUsername());
//        claimBatch.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatch.setUpdateBy(SecurityUtils.getUsername());
//        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatch.setStatus("Y");
//
//        //新增理赔批次流程记录
//        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
//        //批次号一样
//        claimBatchRecord.setBatchno(str1);
//
//        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setOperation("01");//01为保存
//        claimBatchRecord.setStatus("Y");
//        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
//
//        claimBatchMapper.insertClaimBatch(claimBatch);
//
//        standingAndBatck.setClaimBatch(claimBatch);
//
//        return standingAndBatck;
//    }
//
//    /**
//     * 机构交单-直结-新增
//     *
//     * @param claimBatch 理赔批次
//     * @return
//     */
//    @Override
//    public ClaimBatch insertSysClaimBatchTwo(ClaimBatch claimBatch) {
//        claimBatch.setBatchstatus("02");//批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效
//        //批次号
//        String str1 = "JGH" + DateUtils.dateTimeNow("yyyy") + "X" + PubFun.createMySqlMaxNoUseCache("FILINGCODE", 10, 8);
//        claimBatch.setBatchno(str1);
//
//        claimBatch.setCreateBy(SecurityUtils.getUsername());
//        claimBatch.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatch.setUpdateBy(SecurityUtils.getUsername());
//        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//
//        //新增理赔批次流程记录
//        //一条
//        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
//        //批次号一样setBatchno
//        claimBatchRecord.setBatchno(str1);
//        //流程记录ID不一样
//        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setOperation("02");//02为交单复核
//        claimBatchRecord.setStatus("Y");
//        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
//
//        //二条
//        ClaimBatchRecord claimBatchRecord1 = new ClaimBatchRecord();
//        //批次号一样
//        claimBatchRecord1.setBatchno(str1);
//        claimBatchRecord1.setCreateBy(SecurityUtils.getUsername());
//        claimBatchRecord1.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord1.setUpdateBy("");
//        claimBatchRecord1.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord1.setOperation("04");//04为公共
//        claimBatchRecord1.setStatus("Y");
//        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord1);
//
//        claimBatch.setStatus("Y");
//        claimBatchMapper.insertClaimBatch(claimBatch);
//        return claimBatch;
//    }
//
//    /**
//     * 机构交单-直结 改值
//     *
//     * @param claimBatch
//     * @return
//     */
//    @Override
//    public ClaimBatch updateClaimBatchTwo(ClaimBatch claimBatch) {//
//        claimBatch.setBatchstatus("02");
//
//        claimBatch.setUpdateBy(SecurityUtils.getUsername());
//        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//
//        //新增理赔批次流程记录
//        //一条
//        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
//        //批次号一样
//        claimBatchRecord.setBatchno(claimBatch.getBatchno());
//        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setOperation("02");//02为提交
//        claimBatchRecord.setStatus("Y");
//        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
//
//        claimBatch.setStatus("Y");
//        claimBatchMapper.updateClaimBatch(claimBatch);
//        return claimBatch;
//    }
//
//    /**
//     * @param standingAndBatck 理赔批次
//     * @return 结果
//     */
//    @Override
//    public StandingAndBatck updateSysClaimBatch(StandingAndBatck standingAndBatck) {
//        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
//        //批次号一样
//        claimBatchRecord.setBatchno(standingAndBatck.getClaimBatch().getBatchno());
//        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setOperation("01");//01为保存
//        claimBatchRecord.setStatus("Y");
//        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
//
//        standingAndBatck.getClaimBatch().setUpdateBy(SecurityUtils.getUsername());
//        standingAndBatck.getClaimBatch().setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        standingAndBatck.getClaimBatch().setStatus("Y");
//        claimBatchMapper.updateClaimBatch(standingAndBatck.getClaimBatch());
//        return standingAndBatck;
//    }
//
//    @Override
//    public StandingAndBatck updateSysClaimBatchPresent(StandingAndBatck standingAndBatck) {
//        ClaimBatch claimBatch = standingAndBatck.getClaimBatch();
//
//        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
//        //批次号一样
//        claimBatchRecord.setBatchno(claimBatch.getBatchno());
//        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
//        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatchRecord.setOperation("02");//02为提交
//        claimBatchRecord.setStatus("Y");
//        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
//
//        claimBatch.setUpdateBy(SecurityUtils.getUsername());
//        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
//        claimBatch.setBatchstatus("03");// 03-交单完成
//        claimBatch.setStatus("Y");
//        claimBatchMapper.updateClaimBatch(claimBatch);
//
//        return standingAndBatck;
//    }
}
