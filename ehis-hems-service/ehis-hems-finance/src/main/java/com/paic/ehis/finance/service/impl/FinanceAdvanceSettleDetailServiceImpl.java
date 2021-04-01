package com.paic.ehis.finance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtils;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.finance.mapper.*;
import com.paic.ehis.finance.service.IFinanceAdvanceSettleDetailService;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.finance.domain.*;
import com.paic.ehis.finance.domain.dto.FinanceAdvanceSettleDTO;
import com.paic.ehis.finance.domain.vo.FinanceAdvanceSettleVO;
import com.paic.ehis.system.api.ClaimFlowService;
import com.paic.ehis.system.api.PolicyAndRiskService;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.domain.CompanyRiskPolicyInfo;
import com.paic.ehis.system.api.domain.PolicyAndRiskRelation;
import com.paic.ehis.system.api.domain.SysUser;
import com.paic.ehis.system.api.domain.dto.ClaimFlowDTO;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 代垫费结算明细Service业务层处理
 *
 * @author sino
 * @date 2021-01-30
 */
@Service
public class FinanceAdvanceSettleDetailServiceImpl implements IFinanceAdvanceSettleDetailService {
    @Autowired
    private FinanceAdvanceSettleDetailMapper financeAdvanceSettleDetailMapper;

    @Autowired
    FinanceAdvanceSettleTaskMapper financeAdvanceSettleTaskMapper;

    @Autowired
    FinanceSettleRecordMapper financeSettleRecordMapper;

    @Autowired
    private RemoteUserService userService;

    @Autowired
    private BaseIssuingcompanyRiskrelaMapper baseIssuingcompanyRiskrelaMapper;

    @Autowired
    private PolicyAndRiskService policyAndRiskService;


    /**
     * 发起垫付款任务
     */
    @Override
    public List<FinanceAdvanceSettleVO> InitiateAdvancePaymentTask(FinanceAdvanceSettleDTO financeAdvanceSettleDTO) {
        FinanceAdvanceSettleDTO financeAdvanceSettleDTO1=new FinanceAdvanceSettleDTO();
        Date settleEndDate=financeAdvanceSettleDTO.getSettleEndDate();//页面录入结算止期
        String companyCode=financeAdvanceSettleDTO.getCompanyCode();
        Date settleStartDate=financeAdvanceSettleDetailMapper.selectLastendDate(companyCode);//根据出单公司查询最新的结算止期
        financeAdvanceSettleDTO1.setSettleEndDate(settleEndDate);
        financeAdvanceSettleDTO1.setSettleStartDate(settleStartDate);
        List<FinanceAdvanceSettleVO>  financeAdvanceSettleVOS=financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleVOList(financeAdvanceSettleDTO1);
        List<FinanceAdvanceSettleVO> financeAdvanceSettleVOS1=new ArrayList<>();
        if(StringUtils.isNotNull(financeAdvanceSettleVOS)) {
            for (FinanceAdvanceSettleVO financeAdvanceSettleVO : financeAdvanceSettleVOS) {
                FinanceAdvanceSettleVO financeAdvanceSettleVO1 = new FinanceAdvanceSettleVO();
                BeanUtils.copyProperties(financeAdvanceSettleVO, financeAdvanceSettleVO1);
                String batchNo = financeAdvanceSettleVO.getBatchNo();
                String sumPayAmount = financeAdvanceSettleDetailMapper.selectDiscountAmount(batchNo);//计算该案件下的任务总金额
                if(null!=sumPayAmount) {
                    financeAdvanceSettleVO1.setSumPayAmount(new BigDecimal(sumPayAmount));
                }
                else {
                    financeAdvanceSettleVO1.setSumPayAmount(new BigDecimal("0"));
                }
                financeAdvanceSettleVOS1.add(financeAdvanceSettleVO1);
            }
        }
        FinanceAdvanceSettleVO financeAdvanceSettleVO = new FinanceAdvanceSettleVO();
        //垫付款服务费及明细的新增
        FinanceAdvanceSettleTask financeAdvanceSettleTask = new FinanceAdvanceSettleTask();
        FinanceAdvanceSettleDetail financeAdvanceSettleDetail = new FinanceAdvanceSettleDetail();
        FinanceSettleRecord financeSettleRecord = new FinanceSettleRecord();
        Object data = userService.userInfo().get("data");
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(data));
        String organCode = "";
        String userName = "";
        if (null != jsonObject && jsonObject.containsKey("organCode")) {
            organCode = jsonObject.get("organCode").toString();
        }
        if (null != jsonObject && jsonObject.containsKey("userName")) {
            userName = jsonObject.get("userName").toString();
        }
        financeAdvanceSettleTask.setSettleStatus("01");
        financeAdvanceSettleTask.setSettleEndDate(financeAdvanceSettleDTO.getSettleEndDate());//录入结算止期
        financeAdvanceSettleTask.setCompanyCode(financeAdvanceSettleDTO.getCompanyCode());//录入出单公司
        financeAdvanceSettleTask.setStatus("Y");
        financeAdvanceSettleTask.setDeptCode(organCode);
        financeAdvanceSettleTask.setCreateBy(userName);
        financeAdvanceSettleTask.setCreateTime(DateUtils.getNowDate());
        financeAdvanceSettleTask.setUpdateBy(userName);
        financeAdvanceSettleTask.setUpdateTime(DateUtils.getNowDate());

        financeAdvanceSettleDetail.setStatus("Y");
        financeAdvanceSettleDetail.setDeptCode(organCode);
        financeAdvanceSettleDetail.setCompanyCode(financeAdvanceSettleDTO.getCompanyCode());//录入出单公司
        financeAdvanceSettleDetail.setCreateBy(userName);
        financeAdvanceSettleDetail.setCreateTime(DateUtils.getNowDate());
        financeAdvanceSettleDetail.setUpdateBy(userName);
        financeAdvanceSettleDetail.setUpdateTime(DateUtils.getNowDate());

        financeSettleRecord.setTaskType("02");
        financeSettleRecord.setOperator(userName);
        financeSettleRecord.setHistoryFlag("N");
        financeSettleRecord.setOperation("01");
        financeSettleRecord.setStatus("Y");
        financeSettleRecord.setDeptCode(organCode);
        financeSettleRecord.setCreateBy(userName);
        financeSettleRecord.setCreateTime(DateUtils.getNowDate());
        financeSettleRecord.setUpdateBy(userName);
        financeSettleRecord.setUpdateTime(DateUtils.getNowDate());

        if (StringUtils.isNotNull(financeAdvanceSettleDTO.getSettleTaskNo())) {
            //设值 结算表的结算起期
            financeAdvanceSettleDetailMapper.updateFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);
            financeAdvanceSettleTaskMapper.updateFinanceAdvanceSettleTask(financeAdvanceSettleTask);
            financeSettleRecordMapper.insertFinanceSettleRecord(financeSettleRecord);
        } else {
            List<FinanceAdvanceSettleDetail> financeAdvanceSettleDetails = financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleDetailList(financeAdvanceSettleDetail);
            //设值 结算表的结算起期
            if (StringUtils.isNotEmpty(financeAdvanceSettleDetails)) {
                FinanceAdvanceSettleTask advanceSettleTask = new FinanceAdvanceSettleTask();
                advanceSettleTask.setSettleTaskNo(financeAdvanceSettleDetails.get(0).getSettleTaskNo());
                List<FinanceAdvanceSettleTask> financeAdvanceSettleTasks = financeAdvanceSettleTaskMapper.selectFinanceAdvanceSettleTaskList(financeAdvanceSettleTask);
                financeAdvanceSettleTask.setSettleStartDate(financeAdvanceSettleTasks.get(0).getSettleEndDate());
            }
            financeAdvanceSettleDetailMapper.updateFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);
            financeAdvanceSettleTaskMapper.updateFinanceAdvanceSettleTask(financeAdvanceSettleTask);
            financeSettleRecordMapper.insertFinanceSettleRecord(financeSettleRecord);
            financeAdvanceSettleVOS.add(financeAdvanceSettleVO);
        }
        return financeAdvanceSettleVOS1;
    }

    /*导入垫付款清单*/
    @Override
    public int importAdvanceSettleTask(MultipartFile file) {
        //先获取导入前的数据，然后查询导入后的数据进行比对
        FinanceAdvanceSettleTask financeAdvanceSettleTask = new FinanceAdvanceSettleTask();
        try {
            InputStream is = file.getInputStream();
            Workbook wb = ExcelUtils.getWorkbook(is, file.getName());
            int sheetNum = wb.getNumberOfSheets();
            ExcelUtils<FinanceAdvanceSettleVO> utils = new ExcelUtils<FinanceAdvanceSettleVO>(FinanceAdvanceSettleVO.class);
            FinanceAdvanceSettleDetail financeAdvanceSettleDetail = new FinanceAdvanceSettleDetail();
            FinanceAdvanceSettleVO financeAdvanceSettleVO = new FinanceAdvanceSettleVO();

            for (int j = 0; j < sheetNum; j++) {
                Sheet sheet = wb.getSheetAt(j);
                if (1 == j) {//如果excel表里只有一条数据
                    List<FinanceAdvanceSettleVO> taskList = utils.importExcel(sheet);
                    BeanUtils.copyProperties(taskList.get(0), financeAdvanceSettleVO);
                    String taskNo = "AS" + PubFun.createMySqlMaxNoUseCache("finance_advance_settle_detail", 10, 10);
                    financeAdvanceSettleDetail.setSettleTaskNo(taskNo);
                    financeAdvanceSettleTask.setSettleStatus("01");
                    financeAdvanceSettleDetail.setAdvanceAmount(financeAdvanceSettleVO.getAdvanceAmount());//修改金额
                    financeAdvanceSettleDetail.setRemark(financeAdvanceSettleVO.getRemark());//修改状态
                    financeAdvanceSettleTaskMapper.updateFinanceAdvanceSettleTask(financeAdvanceSettleTask);
                    financeAdvanceSettleDetailMapper.updateFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);//单个修改
                } else {//如果excel表里不止一条数据
                    List<FinanceAdvanceSettleVO> taskList = utils.importExcel(sheet);
                    List<FinanceAdvanceSettleVO> updateList = new ArrayList<>();//用来存有任务号的数据
                    List<FinanceAdvanceSettleVO> insertList = new ArrayList<>();//用来存excel中没有任务号的数据
                    for (int i = 0; i < taskList.size(); i++) {
                        if (StringUtils.isNotEmpty(taskList.get(i).getSettleTaskNo())) {
                            updateList.add(taskList.get(i));//取出excel集合中存在任务号的数据
                            BeanUtils.copyProperties(taskList.get(i), financeAdvanceSettleVO);
                            List<FinanceAdvanceSettleVO> financeAdvanceSettleVOS = financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleVOInfo(taskList.get(0).getSettleTaskNo());
                            for (int p = 0; p < financeAdvanceSettleVOS.size(); p++) {
                                String[] settleTaskNos = financeAdvanceSettleVOS.get(p).getSettleTaskNos();
                                financeAdvanceSettleDetailMapper.deleteFinanceSettleDetailsettleTaskNos(settleTaskNos);//批量删除工作池的数据(修改状态不显示)
                            }
                            financeAdvanceSettleTask.setSettleStatus("01");//设置为待确认状态
                            financeAdvanceSettleDetail.setStatus("Y");
                            financeAdvanceSettleTask.setStatus("Y");
                            financeAdvanceSettleDetail.setAdvanceAmount(financeAdvanceSettleVO.getAdvanceAmount());//修改金额
                            financeAdvanceSettleDetail.setRemark(financeAdvanceSettleVO.getRemark());//修改状态
                            financeAdvanceSettleTaskMapper.updateFinanceAdvanceSettleTask(financeAdvanceSettleTask);//单个修改
                            financeAdvanceSettleDetailMapper.updateFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);//单个修改
                        } else {
                            insertList.add(taskList.get(i));//取出excel集合中没有任务号的数据
                            BeanUtils.copyProperties(taskList.get(i), financeAdvanceSettleVO);//新增没有任务号的数据
                            List<FinanceAdvanceSettleVO> financeAdvanceSettleVOS = financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleVOInfo(taskList.get(0).getSettleTaskNo());
                            for (int p = 0; p < financeAdvanceSettleVOS.size(); p++) {
                                String[] settleTaskNos = financeAdvanceSettleVOS.get(p).getSettleTaskNos();
                                financeAdvanceSettleDetailMapper.deleteFinanceSettleDetailsettleTaskNos(settleTaskNos);//批量删除工作池的数据(修改状态不显示)
                            }
                            String taskNo = "AS" + PubFun.createMySqlMaxNoUseCache("finance_advance_settle_detail", 10, 10);
                            financeAdvanceSettleDetail.setSettleTaskNo(taskNo);
                            financeAdvanceSettleTask.setSettleStatus("01");
                            financeAdvanceSettleDetail.setStatus("Y");
                            financeAdvanceSettleTask.setStatus("Y");
                            financeAdvanceSettleDetail.setAdvanceAmount(financeAdvanceSettleVO.getAdvanceAmount());//修改金额
                            financeAdvanceSettleDetail.setRemark(financeAdvanceSettleVO.getRemark());//修改状态
                            financeAdvanceSettleTaskMapper.updateFinanceAdvanceSettleTask(financeAdvanceSettleTask);
                            financeAdvanceSettleDetailMapper.updateFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);//单个修改
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return financeAdvanceSettleTaskMapper.updateFinanceAdvanceSettleTask(financeAdvanceSettleTask);
    }

    /**
     * 查询代垫费结算明细
     *
     * @param detailId 代垫费结算明细ID
     * @return 代垫费结算明细
     */
    @Override
    public FinanceAdvanceSettleDetail selectFinanceAdvanceSettleDetailById(Long detailId) {
        return financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleDetailById(detailId);
    }

    /**
     * 查询代垫费结算明细列表
     *
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 代垫费结算明细
     */
    @Override
    public List<FinanceAdvanceSettleDetail> selectFinanceAdvanceSettleDetailList(FinanceAdvanceSettleDetail financeAdvanceSettleDetail) {
        return financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleDetailList(financeAdvanceSettleDetail);
    }

    /**
     * 新增代垫费结算明细
     *
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 结果
     */
    @Override
    public int insertFinanceAdvanceSettleDetail(FinanceAdvanceSettleDetail financeAdvanceSettleDetail) {
        financeAdvanceSettleDetail.setCreateTime(DateUtils.getNowDate());
        return financeAdvanceSettleDetailMapper.insertFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);
    }

    /**
     * 修改代垫费结算明细
     *
     * @param financeAdvanceSettleDetail 代垫费结算明细
     * @return 结果
     */
    @Override
    public int updateFinanceAdvanceSettleDetail(FinanceAdvanceSettleDetail financeAdvanceSettleDetail) {
        financeAdvanceSettleDetail.setUpdateTime(DateUtils.getNowDate());
        return financeAdvanceSettleDetailMapper.updateFinanceAdvanceSettleDetail(financeAdvanceSettleDetail);
    }

    /**
     * 批量删除代垫费结算明细
     *
     * @param detailIds 需要删除的代垫费结算明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceAdvanceSettleDetailByIds(Long[] detailIds) {
        return financeAdvanceSettleDetailMapper.deleteFinanceAdvanceSettleDetailByIds(detailIds);
    }

    /**
     * 删除代垫费结算明细信息
     *
     * @param detailId 代垫费结算明细ID
     * @return 结果
     */
    @Override
    public int deleteFinanceAdvanceSettleDetailById(Long detailId) {
        return financeAdvanceSettleDetailMapper.deleteFinanceAdvanceSettleDetailById(detailId);
    }


    /**
     * 查询代垫费结算明细列表
     */
    @Override
    public List<FinanceAdvanceSettleVO> selectFinanceAdvanceSettleVOList(FinanceAdvanceSettleDTO financeAdvanceSettleDTO) {
        List<FinanceAdvanceSettleVO>  financeAdvanceSettleVOS=financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleVOList(financeAdvanceSettleDTO);
        List<FinanceAdvanceSettleVO> financeAdvanceSettleVOS1=new ArrayList<>();
        for(FinanceAdvanceSettleVO financeAdvanceSettleVO:financeAdvanceSettleVOS) {
            FinanceAdvanceSettleVO financeAdvanceSettleVO1 = new FinanceAdvanceSettleVO();
            BeanUtils.copyProperties(financeAdvanceSettleVO, financeAdvanceSettleVO1);
            String batchNo=financeAdvanceSettleVO.getBatchNo();
            String sumPayAmount=financeAdvanceSettleDetailMapper.selectDiscountAmount(batchNo);//计算该案件下的任务总金额
            if(null!=sumPayAmount) {
                financeAdvanceSettleVO1.setSumPayAmount(new BigDecimal(sumPayAmount));
            }
            else {
              financeAdvanceSettleVO1.setSumPayAmount(new BigDecimal("0"));
            }
            financeAdvanceSettleVOS1.add(financeAdvanceSettleVO1);
        }
    return financeAdvanceSettleVOS;
    }

    /**
     * 根据结算任务号查询代垫费案件结算明细
     */
    @Override
    public List<FinanceAdvanceSettleVO> selectFinanceAdvanceSettleVOInfo(String settleTaskNo) {
        List<FinanceAdvanceSettleVO> financeAdvanceSettleVO = financeAdvanceSettleDetailMapper.selectFinanceAdvanceSettleVOInfo(settleTaskNo);
        List<FinanceAdvanceSettleVO> financeAdvanceSettleVOS = new ArrayList<>();
        for (FinanceAdvanceSettleVO financeAdvanceSettleVO1 : financeAdvanceSettleVO) {
            /*获取折后金额，根据报案号获取折后金额*/
            String discountamount = financeAdvanceSettleDetailMapper.selectDiscountAmount(financeAdvanceSettleVO1.getRptNo());
            /*获取账单总金额*/
            String billAmount = financeAdvanceSettleDetailMapper.selectBillAmount(financeAdvanceSettleVO1.getRptNo());
            FinanceAdvanceSettleVO financeAdvanceSettleVO2 = new FinanceAdvanceSettleVO();
            BeanUtils.copyProperties(financeAdvanceSettleVO1, financeAdvanceSettleVO2);
            financeAdvanceSettleVO2.setBillAmount(new BigDecimal(billAmount));   //赋值给账单总金额
            financeAdvanceSettleVO2.setDiscountedAmount(new BigDecimal(discountamount)); //给折后金额赋值
            financeAdvanceSettleVO2.setAdvanceAmount(new BigDecimal(discountamount));  //结算金额默认为折后金额
            financeAdvanceSettleVOS.add(financeAdvanceSettleVO2);
        }
        return financeAdvanceSettleVOS;
    }

    /**
     * 删除按钮修改状态为无效
     */
    @Override
    public int deletefinanceinfo(String settleTaskNo) {
        return financeAdvanceSettleDetailMapper.deletefinanceinfo(settleTaskNo);
    }

    /**
     * 任务确认环节确认按钮，将结算状态由待确认改为待核销(待结算)
     */
    @Override
    public int updateSettleStatus1(String settleTaskNo) {
        return financeAdvanceSettleDetailMapper.updateSettleStatus1(settleTaskNo);
    }

    /**
     * 核销按钮将结算状态由待核销改为已结算
     */
    @Override
    public int updateSettleStatus2(String settleTaskNo) {
        return financeAdvanceSettleDetailMapper.updateSettleStatus2(settleTaskNo);
    }

}
