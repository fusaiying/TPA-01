package com.paic.ehis.system.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.paic.ehis.system.domain.*;
import com.paic.ehis.system.domain.dto.BatchDTO;
import com.paic.ehis.system.domain.dto.BatchRecordDTO;
import com.paic.ehis.system.domain.dto.ClaimBatchDTO;
import com.paic.ehis.system.domain.vo.BatchVo;
import com.paic.ehis.system.mapper.ClaimBatchMapper;
import com.paic.ehis.system.mapper.ClaimBatchRecordMapper;
import com.paic.ehis.system.mapper.ClaimCaseMapper;
import com.paic.ehis.system.mapper.ClaimCaseRecordMapper;
import com.paic.ehis.system.service.IClaimBatchService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 理赔批次 Service业务层处理
 * 
 * @author sino
 * @date 2021-01-05
 */
@Service
public class ClaimBatchServiceImpl implements IClaimBatchService
{
    @Autowired
    private ClaimBatchMapper claimBatchMapper;

    @Autowired
    private ClaimBatchRecordMapper claimBatchRecordMapper;

    @Autowired
    private ClaimCaseMapper claimCaseMapper;

    @Autowired
    private ClaimCaseRecordMapper claimCaseRecordMapper;

    /**
     * 查询理赔批次 
     * 
     * @param batchNo 理赔批次 ID
     * @return 理赔批次 
     */
    @Override
    public ClaimBatch selectClaimBatchById(String batchNo)
    {
        return claimBatchMapper.selectClaimBatchById(batchNo);
    }

    /**
     * 查询理赔批次 列表
     * 
     * @param claimBatch 理赔批次 
     * @return 理赔批次 
     */
    @Override
    public List<ClaimBatch> selectClaimBatchList(ClaimBatch claimBatch)
    {
        claimBatch.setStatus("Y");
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
        batchDTO.setStatus("Y");
        batchDTO.setBatchstatus("04");
        if (StringUtils.isNull(batchDTO.getUpdateBy())){
            batchDTO.setUpdateBy(SecurityUtils.getUsername());
        }
        return claimBatchMapper.selectBackToBatchList(batchDTO);
    }

    /**
     * 查询已处理理赔批次 列表
     *
     * @param batchDTO 理赔批次
     * @return 理赔批次 集合
     */
    @Override
    public List<BatchVo> selectDealWithBatchList(BatchDTO batchDTO) {
        if (StringUtils.isNull(batchDTO.getUpdateBy())){
            batchDTO.setUpdateBy(SecurityUtils.getUsername());
        }
        batchDTO.setStatus("Y");
        batchDTO.setBatchstatus("'02','03','05'");
        if (StringUtils.isNull(batchDTO.getUpdatestartTime())){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
            batchDTO.setUpdatestartTime(calendar.getTime());
            batchDTO.setUpdateendTime(DateUtils.parseDate(DateUtils.getTime()));
        };
        return claimBatchMapper.selectDealWithBatchList(batchDTO);

    }

    /**
     * 查询直结复核理赔批次公共池 列表
     *
     * @param batchRecordDTO 理赔批次
     * @return 交单复核理赔批次 集合
     */
    @Override
    public List<BatchVo> selectReviewPublicList(BatchRecordDTO batchRecordDTO) {
        batchRecordDTO.setStatus("Y");
        batchRecordDTO.setBatchstatus("02");
        batchRecordDTO.setOperation("04");
        List<BatchVo> batchVoList = claimBatchMapper.selectStraightAndReview(batchRecordDTO);
        List<BatchVo> list = new ArrayList<>();
        for (BatchVo batchVo : batchVoList) {
            if (StringUtils.isNull(batchVo.getUpdateBy())|| StringUtils.isEmpty(batchVo.getUpdateBy())){
                list.add(batchVo);
            }
        }
        return list;
    }

    /**
     * 查询直结复核理赔批次待处理个人池 列表
     *
     * @param
     * @return 交单复核理赔批次 集合
     */
    @Override
    public List<BatchVo> selectUntreatedPersonalList() {
        BatchRecordDTO batchRecordDTO = new BatchRecordDTO();
        batchRecordDTO.setStatus("Y");
        batchRecordDTO.setBatchstatus("02");
        batchRecordDTO.setOperation("04");
        batchRecordDTO.setUpdateBy(SecurityUtils.getUsername());
        return claimBatchMapper.selectStraightAndReview(batchRecordDTO);
    }

    /**
     * 查询直结复核理赔批次已处理个人池 列表
     *
     * @param
     * @return 交单复核理赔批次 集合
     */
    @Override
    public List<BatchVo> selectProcessedPersonalList() {
        BatchRecordDTO batchRecordDTO = new BatchRecordDTO();
        batchRecordDTO.setStatus("Y");
        batchRecordDTO.setBatchstatus("'03','04','05'");
        batchRecordDTO.setClaimtype("01");
        batchRecordDTO.setOperation("04");
        batchRecordDTO.setUpdateBy(SecurityUtils.getUsername());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
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
    public int insertClaimBatch(ClaimBatch claimBatch)
    {
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
    public int updateClaimBatchstatus(String batchno)
    {
        ClaimBatch claimBatch = new ClaimBatch();
        claimBatch.setBatchno(batchno);
        claimBatch.setBatchstatus("05");
        claimBatch.setUpdateBy(SecurityUtils.getUsername());
        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        return claimBatchMapper.updateClaimBatch(claimBatch);
    }

    /**
     * 复核 处理
     * @param claimBatchDTO 理赔批次
     * @return
     */
    @Override
    public int updateClaimBatch(ClaimBatchDTO claimBatchDTO)
    {
        ClaimCase claimCase = new ClaimCase();
        ClaimCaseRecord claimCaseRecord = new ClaimCaseRecord();
        ClaimBatchRecord claimBatchRecord = claimBatchDTO.getClaimBatchRecord();
        ClaimBatch claimBatch = claimBatchDTO.getClaimBatch();
        String conclusion = claimBatchRecord.getConclusion();
        if (conclusion.equals("1")){
            claimBatch.setBatchstatus("03");//03-交单完成
            claimBatchRecord.setBatchno(claimBatch.getBatchno());//批次号
            claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
            claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
            claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatchRecord.setOperation("03");//03复核完毕
            claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);
            for (int i = 0; i < claimBatch.getCasenum(); i++) {
                String rptNo = "96"+"JGH0X"+PubFun.createMySqlMaxNoUseCache("RPTCODE",10,16);//报案号
                claimCase.setBatchNo(claimBatch.getBatchno());
                claimCase.setRptNo(rptNo);
                claimCase.setFilingNo("JGH"+DateUtils.dateTimeNow("yyyy")+"X"+PubFun.createMySqlMaxNoUseCache("FILINGCODE",10,10));//归档号
                claimCase.setStatus("Y");
                claimCase.setCaseStatus("05");
                claimCase.setCreateBy(SecurityUtils.getUsername());
                claimCase.setCreateTime(DateUtils.parseDate(DateUtils.getNowDate()));
                claimCase.setUpdateTime(DateUtils.parseDate(DateUtils.getNowDate()));
                claimCaseMapper.insertClaimCase(claimCase);

                claimCaseRecord.setRptNo(rptNo);
                claimCaseRecord.setOperator(SecurityUtils.getUsername());
                claimCaseRecord.setOperation("05");
                claimCaseRecord.setHistoryFlag("N");
                claimCaseRecord.setStatus("Y");
                claimCaseRecord.setCreateBy(SecurityUtils.getUsername());
                claimCaseRecord.setCreateTime(DateUtils.parseDate(DateUtils.getNowDate()));
                claimCaseRecord.setUpdateBy(SecurityUtils.getUsername());
                claimCaseRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getNowDate()));
                claimCaseRecordMapper.insertClaimCaseRecord(claimCaseRecord);
            }
            return claimBatchMapper.updateClaimBatch(claimBatch);
        }else if (conclusion.equals("2")){
            claimBatch.setBatchstatus("04");//04-交单退回
            claimBatchRecord.setBatchno(claimBatch.getBatchno());//批次号
            claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
            claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
            claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
            claimBatchRecord.setOperation("03");//03复核完毕
            claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

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
    public int deleteClaimBatchByIds(String[] batchNos)
    {
        return claimBatchMapper.deleteClaimBatchByIds(batchNos);
    }

    /**
     * 删除理赔批次 信息
     * k
     * @param batchNo 理赔批次 ID
     * @return 结果
     */
    @Override
    public int deleteClaimBatchById(String batchNo)
    {
        return claimBatchMapper.deleteClaimBatchById(batchNo);
    }

    /*************************************/
    /**
     * 新增理赔批次
     *
     * @param standingAndBatck 理赔批次
     * @return 结果
     */
    @Override
    public StandingAndBatck insertSysClaimBatch(StandingAndBatck standingAndBatck)//
    {
        ClaimBatch claimBatch = standingAndBatck.getClaimBatch();
        claimBatch.setBatchstatus("01");

        //批次号
//        Date date = new Date();//获取当前的日期
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//        String str = df.format(date);//获取String类型的时间
//        String str1="PCH"+str;

        String str1="JGH"+DateUtils.dateTimeNow("yyyy")+"X"+PubFun.createMySqlMaxNoUseCache("FILINGCODE",10,8);
        claimBatch.setBatchno(str1);
        claimBatch.setCreateBy(SecurityUtils.getUsername());
        claimBatch.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatch.setUpdateBy(SecurityUtils.getUsername());
        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatch.setStatus("Y");

        //新增理赔批次流程记录
        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord.setBatchno(str1);

        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setOperation("01");//01为保存
        claimBatchRecord.setStatus("Y");
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

        claimBatchMapper.insertClaimBatch(claimBatch);

        standingAndBatck.setClaimBatch(claimBatch);

        return standingAndBatck;
    }

    /**
     * 机构交单-直接 新增
     * @param claimBatch 理赔批次
     * @return
     */
    @Override
    public ClaimBatch insertSysClaimBatchTwo(ClaimBatch claimBatch) {
        claimBatch.setBatchstatus("02");//批次状态 01-处理中 02-交单复核 03-交单完成 04-交单退回 05-交单无效

        //批次号
//        Date date = new Date();//获取当前的日期
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//        String str = df.format(date);//获取String类型的时间
//        String str1="PCH"+str;

        String str1="JGH"+DateUtils.dateTimeNow("yyyy")+"X"+PubFun.createMySqlMaxNoUseCache("FILINGCODE",10,8);
        claimBatch.setBatchno(str1);

        claimBatch.setCreateBy(SecurityUtils.getUsername());
        claimBatch.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatch.setUpdateBy(SecurityUtils.getUsername());
        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));

        //新增理赔批次流程记录
        //一条
        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        //批次号一样setBatchno
        claimBatchRecord.setBatchno(str1);
        //流程记录ID不一样
        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setOperation("02");//02为提交
        claimBatchRecord.setStatus("Y");
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

        //二条
        ClaimBatchRecord claimBatchRecord1 = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord1.setBatchno(str1);
        claimBatchRecord1.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord1.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord1.setUpdateBy(null);
        claimBatchRecord1.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord1.setOperation("04");//04为公共
        claimBatchRecord1.setStatus("Y");
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord1);

        claimBatch.setStatus("Y");
        claimBatchMapper.insertClaimBatch(claimBatch);
        return claimBatch;
    }

    /**
     * 机构交单-直接 改值
     * @param claimBatch
     * @return
     */
    @Override
    public ClaimBatch updateClaimBatchTwo(ClaimBatch claimBatch) {//
        claimBatch.setBatchstatus("02");

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
        claimBatchRecord.setOperation("02");//02为提交
        claimBatchRecord.setStatus("Y");
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

        claimBatch.setStatus("Y");
        claimBatchMapper.updateClaimBatch(claimBatch);
        return claimBatch;
    }

    /**
     *
     * @param standingAndBatck 理赔批次
     * @return 结果
     */
    @Override
    public StandingAndBatck updateSysClaimBatch(StandingAndBatck standingAndBatck)
    {
        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord.setBatchno(standingAndBatck.getClaimBatch().getBatchno());
        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setOperation("01");//01为保存
        claimBatchRecord.setStatus("Y");
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

        standingAndBatck.getClaimBatch().setUpdateBy(SecurityUtils.getUsername());
        standingAndBatck.getClaimBatch().setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        standingAndBatck.getClaimBatch().setStatus("Y");
        claimBatchMapper.updateClaimBatch(standingAndBatck.getClaimBatch());
        return standingAndBatck;
    }

    @Override
    public StandingAndBatck updateSysClaimBatchPresent(StandingAndBatck standingAndBatck)
    {
        ClaimBatch claimBatch = standingAndBatck.getClaimBatch();

        ClaimBatchRecord claimBatchRecord = new ClaimBatchRecord();
        //批次号一样
        claimBatchRecord.setBatchno(claimBatch.getBatchno());
        claimBatchRecord.setCreateBy(SecurityUtils.getUsername());
        claimBatchRecord.setCreateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setUpdateBy(SecurityUtils.getUsername());
        claimBatchRecord.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatchRecord.setOperation("02");//02为提交
        claimBatchRecord.setStatus("Y");
        claimBatchRecordMapper.insertClaimBatchRecord(claimBatchRecord);

        claimBatch.setUpdateBy(SecurityUtils.getUsername());
        claimBatch.setUpdateTime(DateUtils.parseDate(DateUtils.getTime()));
        claimBatch.setBatchstatus("03");// 03-交单完成
        claimBatch.setStatus("Y");
        claimBatchMapper.updateClaimBatch(claimBatch);

        return standingAndBatck;
    }
}
