package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.order.domain.BaseBalanceDetail;
import com.paic.ehis.order.domain.BaseBalanceExamLog;
import com.paic.ehis.order.domain.BaseBalanceInfo;
import com.paic.ehis.order.mapper.BaseBalanceDetailMapper;
import com.paic.ehis.order.mapper.BaseBalanceExamLogMapper;
import com.paic.ehis.order.mapper.BaseBalanceInfoMapper;
import com.paic.ehis.order.mapper.BaseBalanceInvoiceMapper;
import com.paic.ehis.order.service.IBaseBalanceInfoService;
/*import com.paic.ehis.order.service.enumeration.BalanceStatusEnum;
import com.paic.ehis.order.service.enumeration.StatusEnum;*/
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * base_balance_info(服务结算基本信息)Service业务层处理
 * 
 * @author sino
 * @date 2021-01-20
 */
@Service
public class BaseBalanceInfoServiceImpl implements IBaseBalanceInfoService 
{
    private Log logger = LogFactory.getLog(BaseBalanceInfoServiceImpl.class);

    @Autowired
    private BaseBalanceInfoMapper baseBalanceInfoMapper;

    @Autowired
    private BaseBalanceDetailMapper baseBalanceDetailMapper;

    @Autowired
    private BaseBalanceExamLogMapper baseBalanceExamLogMapper;

    @Autowired
    private BaseBalanceInvoiceMapper baseBalanceInvoiceMapper;

    // 日期格式化 获取当前日期
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 查询base_balance_info(服务结算基本信息)
     * 
     * @param serialNo base_balance_info(服务结算基本信息)ID
     * @return base_balance_info(服务结算基本信息)
     */
    @Override
    public BaseBalanceInfo selectBaseBalanceInfoById(Long serialNo)
    {
        return baseBalanceInfoMapper.selectBaseBalanceInfoById(serialNo);
    }

    /**
     * 查询base_balance_info(服务结算基本信息)列表
     * 
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return base_balance_info(服务结算基本信息)
     */
    @Override
    public List<BaseBalanceInfo> selectBaseBalanceInfoList(BaseBalanceInfo baseBalanceInfo)
    {
        return baseBalanceInfoMapper.selectBaseBalanceInfoList(baseBalanceInfo);
    }

    /**
     * 新增base_balance_info(服务结算基本信息)
     * 
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return 结果
     */
    @Override
    public int insertBaseBalanceInfo(BaseBalanceInfo baseBalanceInfo)
    {
        //结算基本信息
        /*String taskNo = DateUtils.dateTime() + PubFun.createMySqlMaxNoUseCache("baseBalanceSer",12,4);
        baseBalanceInfo.setTaskNo(taskNo);
        baseBalanceInfo.setCreateTime(DateUtils.getNowDate());
        baseBalanceInfo.setUpdateTime(DateUtils.getNowDate());
        baseBalanceInfo.setCreateBy(SecurityUtils.getUsername());
        baseBalanceInfo.setUpdateBy(SecurityUtils.getUsername());
        baseBalanceInfo.setStatus(StatusEnum.VALID.getCode());
        baseBalanceInfo.setIsImport(StatusEnum.INVALID.getCode());
        if (baseBalanceInfo.getEndDate() == null) {
            baseBalanceInfo.setEndDate(DateUtils.parseDate(DateUtils.getDate()));
        }
        baseBalanceInfo.setBussinessStatus(BalanceStatusEnum.WAIT_CONFIRM.getCode());
        baseBalanceInfo.setInvoiceStatus(StatusEnum.INVALID.getCode());*/
        //插入明细条数
        int detailNum = 0;
        //总服务费
        BigDecimal serviceAmount = BigDecimal.ZERO;
        //结算明细信息
        List<BaseBalanceDetail> detailList =  baseBalanceDetailMapper.selectDetailList(baseBalanceInfo);
        /*for (BaseBalanceDetail baseBalanceDetail : detailList) {
            baseBalanceDetail.setTaskNo(taskNo);
            baseBalanceDetail.setSupplierCode(baseBalanceInfo.getSupplierCode());
            baseBalanceDetail.setServiceCode(baseBalanceInfo.getServiceCode());
            baseBalanceDetail.setCreateTime(DateUtils.getNowDate());
            baseBalanceDetail.setUpdateTime(DateUtils.getNowDate());
            baseBalanceDetail.setCreateBy(SecurityUtils.getUsername());
            baseBalanceDetail.setUpdateBy(SecurityUtils.getUsername());
            baseBalanceDetail.setStatus(StatusEnum.VALID.getCode());
            detailNum += baseBalanceDetailMapper.insertBaseBalanceDetail(baseBalanceDetail);
            serviceAmount = serviceAmount.add(baseBalanceDetail.getAmount());
        }
        baseBalanceInfo.setServiceAmount(serviceAmount);
        logger.info("插入-结算明细表行数：" + detailNum);

        //结算审核日志
        BaseBalanceExamLog baseBalanceExamLog = new BaseBalanceExamLog();
        baseBalanceExamLog.setTaskNo(baseBalanceInfo.getTaskNo());
        baseBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
        baseBalanceExamLog.setBussinessStatus(baseBalanceInfo.getBussinessStatus());
        baseBalanceExamLog.setCreateTime(DateUtils.getNowDate());
        baseBalanceExamLog.setUpdateTime(DateUtils.getNowDate());
        baseBalanceExamLog.setCreateBy(SecurityUtils.getUsername());
        baseBalanceExamLog.setUpdateBy(SecurityUtils.getUsername());
        baseBalanceExamLog.setRemark(BalanceStatusEnum.WAIT_CONFIRM.getMsg());*/
        /*int logNum = baseBalanceExamLogMapper.insertBaseBalanceExamLog(baseBalanceExamLog);
        logger.info("插入-结算审核日志表行数：" + logNum);
        baseBalanceInfo.setLogId(baseBalanceExamLog.getSerialNo());*/

        return baseBalanceInfoMapper.insertBaseBalanceInfo(baseBalanceInfo);
    }

    /**
     * 修改base_balance_info(服务结算基本信息)
     *
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return 结果
     */
    @Override
    public int updateBaseBalanceInfo(BaseBalanceInfo baseBalanceInfo)
    {
        baseBalanceInfo.setUpdateTime(DateUtils.getNowDate());
        return baseBalanceInfoMapper.updateBaseBalanceInfo(baseBalanceInfo);
    }

    /**
     * 批量删除base_balance_info(服务结算基本信息)
     *
     * @param taskNos 需要删除的base_balance_info(服务结算基本信息)taskNo
     * @return 结果
     */
    @Override
    public int deleteBaseBalanceInfoByIds(Long[] taskNos)
    {
        return baseBalanceInfoMapper.deleteBaseBalanceInfoByIds(taskNos);
    }

    /**
     * 删除base_balance_info(服务结算基本信息)信息
     *
     * @param taskNo base_balance_info(服务结算基本信息)taskNo
     * @return 结果
     */
    @Override
    public int deleteBaseBalanceInfoById(Long taskNo)
    {
        return baseBalanceInfoMapper.deleteBaseBalanceInfoById(taskNo);
    }

    /**
     * 查询base_balance_info(服务结算基本信息)列表
     *
     * @param baseBalanceInfo base_balance_info(服务结算基本信息)
     * @return base_balance_info(服务结算基本信息)
     */
    @Override
    public List<BaseBalanceInfo> selectBaseBalanceInfoList2(BaseBalanceInfo baseBalanceInfo)
    {
        return baseBalanceInfoMapper.selectBaseBalanceInfoList2(baseBalanceInfo);
    }

    /**
     * 更新base_balance_info(服务结算基本信息)信息
     * @param baseBalanceInfo base_balance_info(服务结算基本信息) status-删除，isImport-导人，bussinessStatus-流程
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateBaseBalanceInfoByTaskNo(BaseBalanceInfo baseBalanceInfo) {
        Date nowDate = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();
      /*  if (BalanceStatusEnum.TYPE_DELETE.getCode().equals(baseBalanceInfo.getParams().get("type"))) {
            //设置失效时，明细也要置为失效
            baseBalanceInfo.setStatus(StatusEnum.INVALID.getCode());

            BaseBalanceDetail baseBalanceDetail = new BaseBalanceDetail();
            baseBalanceDetail.setUpdateTime(nowDate);
            baseBalanceDetail.setUpdateBy(userName);
            baseBalanceDetail.setTaskNo(baseBalanceInfo.getTaskNo());
            baseBalanceDetail.setStatus(StatusEnum.INVALID.getCode());
            int detailNum = baseBalanceDetailMapper.updateBaseBalanceDetailByTaskNo(baseBalanceDetail);
            logger.info("更新-结算明细表行数：" + detailNum);

            BaseBalanceExamLog baseBalanceExamLog = new BaseBalanceExamLog();
            baseBalanceExamLog.setUpdateTime(nowDate);
            baseBalanceExamLog.setUpdateBy(userName);
            baseBalanceExamLog.setTaskNo(baseBalanceInfo.getTaskNo());
            baseBalanceExamLog.setStatus(StatusEnum.INVALID.getCode());
            int logNum = baseBalanceExamLogMapper.updateBaseBalanceExamLogByTaskNo(baseBalanceExamLog);
            logger.info("更新-结算审核日志表行数：" + logNum);

        } else if (BalanceStatusEnum.TYPE_IMPORT.getCode().equals(baseBalanceInfo.getParams().get("type"))) {
            //导入（业务状态为发起结算/发起结算（退回），导入状态）
            baseBalanceInfo.setIsImport(StatusEnum.VALID.getCode());

            BaseBalanceExamLog baseBalanceExamLog = new BaseBalanceExamLog();
            baseBalanceExamLog.setTaskNo(baseBalanceInfo.getTaskNo());
            baseBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            baseBalanceExamLog.setBussinessStatus(baseBalanceInfo.getBussinessStatus());
            baseBalanceExamLog.setCreateTime(nowDate);
            baseBalanceExamLog.setUpdateTime(nowDate);
            baseBalanceExamLog.setCreateBy(userName);
            baseBalanceExamLog.setUpdateBy(userName);
            baseBalanceExamLog.setRemark("导人清单");
            int logNum = baseBalanceExamLogMapper.insertBaseBalanceExamLog(baseBalanceExamLog);
            logger.info("导人插入-结算审核日志表行数：" + logNum);
            baseBalanceInfo.setLogId(baseBalanceExamLog.getSerialNo());
        } else if (BalanceStatusEnum.TYPE_CONFIRM.getCode().equals(baseBalanceInfo.getParams().get("type"))) {
            //清单确认时
            baseBalanceInfo.setBussinessStatus(BalanceStatusEnum.ALREADY_CONFIRM.getCode());

            BaseBalanceExamLog baseBalanceExamLog = new BaseBalanceExamLog();
            baseBalanceExamLog.setTaskNo(baseBalanceInfo.getTaskNo());
            baseBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            baseBalanceExamLog.setBussinessStatus(baseBalanceInfo.getBussinessStatus());
            baseBalanceExamLog.setCreateTime(nowDate);
            baseBalanceExamLog.setUpdateTime(nowDate);
            baseBalanceExamLog.setCreateBy(userName);
            baseBalanceExamLog.setUpdateBy(userName);
            baseBalanceExamLog.setRemark(BalanceStatusEnum.ALREADY_CONFIRM.getMsg());
            int logNum = baseBalanceExamLogMapper.insertBaseBalanceExamLog(baseBalanceExamLog);
            logger.info("确认插入-结算审核日志表行数：" + logNum);
            baseBalanceInfo.setLogId(baseBalanceExamLog.getSerialNo());
        } else if (BalanceStatusEnum.TYPE_EXAMIN.getCode().equals(baseBalanceInfo.getParams().get("type"))) {
            //清单审核
            //同意/不同意（导人状态为退回）

            BaseBalanceExamLog baseBalanceExamLog = new BaseBalanceExamLog();
            baseBalanceExamLog.setTaskNo(baseBalanceInfo.getTaskNo());
            baseBalanceExamLog.setExamineConclusion(baseBalanceInfo.getBussinessStatus());
            baseBalanceExamLog.setExamineOpinion(baseBalanceInfo.getRemark());
            baseBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            baseBalanceExamLog.setBussinessStatus(baseBalanceInfo.getBussinessStatus());
            baseBalanceExamLog.setCreateTime(nowDate);
            baseBalanceExamLog.setUpdateTime(nowDate);
            baseBalanceExamLog.setCreateBy(userName);
            baseBalanceExamLog.setUpdateBy(userName);
            if (BalanceStatusEnum.ALREADY_EXAMINE.getCode().equals(baseBalanceInfo.getBussinessStatus())) {
                baseBalanceExamLog.setRemark(BalanceStatusEnum.ALREADY_EXAMINE.getMsg());
            } else {
                baseBalanceExamLog.setRemark(BalanceStatusEnum.WAIT_CONFIRM_BACK.getMsg());
                baseBalanceInfo.setIsImport(StatusEnum.BACKVALID.getCode());
            }
            int logNum = baseBalanceExamLogMapper.insertBaseBalanceExamLog(baseBalanceExamLog);
            logger.info("审核插入-结算审核日志表行数：" + logNum);
            baseBalanceInfo.setLogId(baseBalanceExamLog.getSerialNo());
        } else if (BalanceStatusEnum.TYPE_WRITE.getCode().equals(baseBalanceInfo.getParams().get("type"))) {
            //结算核销
            baseBalanceInfo.setBussinessStatus(BalanceStatusEnum.ALREADY_WRITE.getCode());

            BaseBalanceExamLog baseBalanceExamLog = new BaseBalanceExamLog();
            baseBalanceExamLog.setTaskNo(baseBalanceInfo.getTaskNo());
            baseBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            baseBalanceExamLog.setBussinessStatus(baseBalanceInfo.getBussinessStatus());
            baseBalanceExamLog.setCreateTime(nowDate);
            baseBalanceExamLog.setUpdateTime(nowDate);
            baseBalanceExamLog.setCreateBy(userName);
            baseBalanceExamLog.setUpdateBy(userName);
            baseBalanceExamLog.setRemark(BalanceStatusEnum.ALREADY_WRITE.getMsg());
            int logNum = baseBalanceExamLogMapper.insertBaseBalanceExamLog(baseBalanceExamLog);
            logger.info("核销插入-结算审核日志表行数：" + logNum);
            baseBalanceInfo.setLogId(baseBalanceExamLog.getSerialNo());
        } else if (BalanceStatusEnum.TYPE_INVOICE.getCode().equals(baseBalanceInfo.getParams().get("type"))) {
            //发票核销
            baseBalanceInfo.setInvoiceStatus(StatusEnum.VALID.getCode());
            BaseBalanceExamLog baseBalanceExamLog = new BaseBalanceExamLog();
            baseBalanceExamLog.setTaskNo(baseBalanceInfo.getTaskNo());
            baseBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            baseBalanceExamLog.setBussinessStatus(baseBalanceInfo.getBussinessStatus());
            baseBalanceExamLog.setCreateTime(nowDate);
            baseBalanceExamLog.setUpdateTime(nowDate);
            baseBalanceExamLog.setCreateBy(userName);
            baseBalanceExamLog.setUpdateBy(userName);
            baseBalanceExamLog.setRemark(BalanceStatusEnum.TYPE_INVOICE.getMsg());
            int logNum = baseBalanceExamLogMapper.insertBaseBalanceExamLog(baseBalanceExamLog);
            logger.info("核销插入-结算审核日志表行数：" + logNum);
            baseBalanceInfo.setLogId(baseBalanceExamLog.getSerialNo());
        }*/
        baseBalanceInfo.setUpdateTime(nowDate);
        baseBalanceInfo.setUpdateBy(userName);
        baseBalanceInfoMapper.updateBaseBalanceInfoByTaskNo(baseBalanceInfo);
        return AjaxResult.success();
    }


}
