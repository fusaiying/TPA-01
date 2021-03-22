package com.paic.ehis.order.service.impl;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.order.domain.ServiceBalanceDetail;
import com.paic.ehis.order.domain.ServiceBalanceExamLog;
import com.paic.ehis.order.domain.ServiceBalanceInfo;
import com.paic.ehis.order.mapper.ServiceBalanceDetailMapper;
import com.paic.ehis.order.mapper.ServiceBalanceExamLogMapper;
import com.paic.ehis.order.mapper.ServiceBalanceInfoMapper;
import com.paic.ehis.order.mapper.ServiceBalanceInvoiceMapper;
import com.paic.ehis.order.service.IServiceBalanceInfoService;
import com.paic.ehis.order.service.enumeration.BalanceStatusEnum;
import com.paic.ehis.order.service.enumeration.StatusEnum;
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
 * service_balance_info(服务结算基本信息)Service业务层处理
 * 
 * @author chenhaiming
 * @date 2021-01-20
 */
@Service
public class ServiceBalanceInfoServiceImpl implements IServiceBalanceInfoService
{
    private Log logger = LogFactory.getLog(ServiceBalanceInfoServiceImpl.class);

    @Autowired
    private ServiceBalanceInfoMapper serviceBalanceInfoMapper;

    @Autowired
    private ServiceBalanceDetailMapper serviceBalanceDetailMapper;

    @Autowired
    private ServiceBalanceExamLogMapper serviceBalanceExamLogMapper;

    @Autowired
    private ServiceBalanceInvoiceMapper serviceBalanceInvoiceMapper;

    /**
     * 查询service_balance_info(服务结算基本信息)
     * 
     * @param serialNo service_balance_info(服务结算基本信息)ID
     * @return service_balance_info(服务结算基本信息)
     */
    @Override
    public ServiceBalanceInfo selectServiceBalanceInfoById(Long serialNo)
    {
        return serviceBalanceInfoMapper.selectServiceBalanceInfoById(serialNo);
    }

    /**
     * 查询service_balance_info(服务结算基本信息)列表
     * 
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_info(服务结算基本信息)
     */
    @Override
    public List<ServiceBalanceInfo> selectServiceBalanceInfoList(ServiceBalanceInfo serviceBalanceInfo)
    {
        return serviceBalanceInfoMapper.selectServiceBalanceInfoList(serviceBalanceInfo);
    }

    /**
     * 新增service_balance_info(服务结算基本信息)
     * 
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return 结果
     */
    @Override
    public int insertServiceBalanceInfo(ServiceBalanceInfo serviceBalanceInfo)
    {
        //结算基本信息
        String taskNo = DateUtils.dateTime() + PubFun.createMySqlMaxNoUseCache("baseBalanceSer",12,4);
        serviceBalanceInfo.setTaskNo(taskNo);
        serviceBalanceInfo.setCreateTime(DateUtils.getNowDate());
        serviceBalanceInfo.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceInfo.setCreateBy(SecurityUtils.getUsername());
        serviceBalanceInfo.setUpdateBy(SecurityUtils.getUsername());
        serviceBalanceInfo.setStatus(StatusEnum.VALID.getCode());
        serviceBalanceInfo.setIsImport(StatusEnum.INVALID.getCode());
        if (serviceBalanceInfo.getEndDate() == null) {
            serviceBalanceInfo.setEndDate(DateUtils.parseDate(DateUtils.getDate()));
        }
        serviceBalanceInfo.setBussinessStatus(BalanceStatusEnum.WAIT_CONFIRM.getCode());
        serviceBalanceInfo.setInvoiceStatus(StatusEnum.INVALID.getCode());
        //插入明细条数
        int detailNum = 0;
        //总服务费
        BigDecimal serviceAmount = BigDecimal.ZERO;
        //结算明细信息
        List<ServiceBalanceDetail> detailList =  serviceBalanceDetailMapper.selectDetailList(serviceBalanceInfo);
        for (ServiceBalanceDetail serviceBalanceDetail : detailList) {
            serviceBalanceDetail.setTaskNo(taskNo);
            serviceBalanceDetail.setCreateTime(DateUtils.getNowDate());
            serviceBalanceDetail.setUpdateTime(DateUtils.getNowDate());
            serviceBalanceDetail.setCreateBy(SecurityUtils.getUsername());
            serviceBalanceDetail.setUpdateBy(SecurityUtils.getUsername());
            serviceBalanceDetail.setStatus(StatusEnum.VALID.getCode());
            detailNum += serviceBalanceDetailMapper.insertServiceBalanceDetail(serviceBalanceDetail);
            serviceAmount = serviceAmount.add(serviceBalanceDetail.getAmount());
        }
        serviceBalanceInfo.setServiceAmount(serviceAmount);
        logger.info("插入-结算明细表行数：" + detailNum);

        //结算审核日志
        ServiceBalanceExamLog serviceBalanceExamLog = new ServiceBalanceExamLog();
        serviceBalanceExamLog.setTaskNo(serviceBalanceInfo.getTaskNo());
        serviceBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
        serviceBalanceExamLog.setBussinessStatus(serviceBalanceInfo.getBussinessStatus());
        serviceBalanceExamLog.setCreateTime(DateUtils.getNowDate());
        serviceBalanceExamLog.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceExamLog.setCreateBy(SecurityUtils.getUsername());
        serviceBalanceExamLog.setUpdateBy(SecurityUtils.getUsername());
        serviceBalanceExamLog.setRemark(BalanceStatusEnum.WAIT_CONFIRM.getMsg());
        int logNum = serviceBalanceExamLogMapper.insertServiceBalanceExamLog(serviceBalanceExamLog);
        logger.info("插入-结算审核日志表行数：" + logNum);
        serviceBalanceInfo.setLogId(serviceBalanceExamLog.getSerialNo());

        return serviceBalanceInfoMapper.insertServiceBalanceInfo(serviceBalanceInfo);
    }

    /**
     * 修改service_balance_info(服务结算基本信息)
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return 结果
     */
    @Override
    public int updateServiceBalanceInfo(ServiceBalanceInfo serviceBalanceInfo)
    {
        serviceBalanceInfo.setUpdateTime(DateUtils.getNowDate());
        serviceBalanceInfo.setUpdateBy(SecurityUtils.getUsername());
        return serviceBalanceInfoMapper.updateServiceBalanceInfo(serviceBalanceInfo);
    }

    /**
     * 批量删除service_balance_info(服务结算基本信息)
     *
     * @param serialNos 需要删除的service_balance_info(服务结算基本信息)serialNo
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceInfoByIds(Long[] serialNos)
    {
        return serviceBalanceInfoMapper.deleteServiceBalanceInfoByIds(serialNos);
    }

    /**
     * 删除service_balance_info(服务结算基本信息)信息
     *
     * @param serialNo service_balance_info(服务结算基本信息)serialNo
     * @return 结果
     */
    @Override
    public int deleteServiceBalanceInfoById(Long serialNo)
    {
        return serviceBalanceInfoMapper.deleteServiceBalanceInfoById(serialNo);
    }

    /**
     * 查询service_balance_info(服务结算基本信息)
     *
     * @param taskNo service_balance_info(服务结算基本信息)taskNo
     * @return service_balance_info(服务结算基本信息)
     */
    @Override
    public ServiceBalanceInfo selectServiceBalanceInfoByTaskNo(String taskNo) {
        return serviceBalanceInfoMapper.selectServiceBalanceInfoByTaskNo(taskNo);
    }

    /**
     * 查询service_balance_info(服务结算基本信息)列表
     *
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息)
     * @return service_balance_info(服务结算基本信息)
     */
    @Override
    public List<ServiceBalanceInfo> selectServiceBalanceInfoList2(ServiceBalanceInfo serviceBalanceInfo)
    {
        return serviceBalanceInfoMapper.selectServiceBalanceInfoList2(serviceBalanceInfo);
    }

    /**
     * 查询service_balance_info(服务结算基本信息)列表 默认
     * @return service_balance_info(服务结算基本信息)
     */
    @Override
    public List<ServiceBalanceInfo> selectServiceBalanceInfoList2Default() {
        ServiceBalanceInfo serviceBalanceInfo = new ServiceBalanceInfo();
        serviceBalanceInfo.setStatus("Y");
        return serviceBalanceInfoMapper.selectServiceBalanceInfoList2Default(serviceBalanceInfo);
    }

    /**
     * 更新service_balance_info(服务结算基本信息)信息
     * @param serviceBalanceInfo service_balance_info(服务结算基本信息) status-删除，isImport-导入，bussinessStatus-流程
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateServiceBalanceInfoByTaskNo(ServiceBalanceInfo serviceBalanceInfo) {
        Date nowDate = DateUtils.getNowDate();
        String userName = SecurityUtils.getUsername();
        if (BalanceStatusEnum.TYPE_DELETE.getCode().equals(serviceBalanceInfo.getParams().get("type"))) {
            //设置失效时，明细也要置为失效
            serviceBalanceInfo.setStatus(StatusEnum.INVALID.getCode());

            ServiceBalanceDetail serviceBalanceDetail = new ServiceBalanceDetail();
            serviceBalanceDetail.setUpdateTime(nowDate);
            serviceBalanceDetail.setUpdateBy(userName);
            serviceBalanceDetail.setTaskNo(serviceBalanceInfo.getTaskNo());
            serviceBalanceDetail.setStatus(StatusEnum.INVALID.getCode());
            int detailNum = serviceBalanceDetailMapper.updateServiceBalanceDetailByTaskNo(serviceBalanceDetail);
            logger.info("更新-结算明细表行数：" + detailNum);

            ServiceBalanceExamLog serviceBalanceExamLog = new ServiceBalanceExamLog();
            serviceBalanceExamLog.setUpdateTime(nowDate);
            serviceBalanceExamLog.setUpdateBy(userName);
            serviceBalanceExamLog.setTaskNo(serviceBalanceInfo.getTaskNo());
            serviceBalanceExamLog.setStatus(StatusEnum.INVALID.getCode());
            int logNum = serviceBalanceExamLogMapper.updateServiceBalanceExamLogByTaskNo(serviceBalanceExamLog);
            logger.info("更新-结算审核日志表行数：" + logNum);

        } else if (BalanceStatusEnum.TYPE_IMPORT.getCode().equals(serviceBalanceInfo.getParams().get("type"))) {
            //导入（业务状态为发起结算/发起结算（退回），导入状态）
            serviceBalanceInfo.setIsImport(StatusEnum.VALID.getCode());

            ServiceBalanceExamLog serviceBalanceExamLog = new ServiceBalanceExamLog();
            serviceBalanceExamLog.setTaskNo(serviceBalanceInfo.getTaskNo());
            serviceBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            serviceBalanceExamLog.setBussinessStatus(serviceBalanceInfo.getBussinessStatus());
            serviceBalanceExamLog.setCreateTime(nowDate);
            serviceBalanceExamLog.setUpdateTime(nowDate);
            serviceBalanceExamLog.setCreateBy(userName);
            serviceBalanceExamLog.setUpdateBy(userName);
            serviceBalanceExamLog.setRemark("导入清单");
            int logNum = serviceBalanceExamLogMapper.insertServiceBalanceExamLog(serviceBalanceExamLog);
            logger.info("导入插入-结算审核日志表行数：" + logNum);
            serviceBalanceInfo.setLogId(serviceBalanceExamLog.getSerialNo());
        } else if (BalanceStatusEnum.TYPE_CONFIRM.getCode().equals(serviceBalanceInfo.getParams().get("type"))) {
            //清单确认时
            serviceBalanceInfo.setBussinessStatus(BalanceStatusEnum.ALREADY_CONFIRM.getCode());
            ServiceBalanceDetail serviceBalanceDetail = new ServiceBalanceDetail();
            serviceBalanceDetail.setUpdateTime(nowDate);
            serviceBalanceDetail.setUpdateBy(userName);
            serviceBalanceDetail.setTaskNo(serviceBalanceInfo.getTaskNo());
            List<String> orderInfos = serviceBalanceDetailMapper.orderCodeList(serviceBalanceDetail);
            serviceBalanceDetailMapper.updateOrderInfo(orderInfos);
            ServiceBalanceExamLog serviceBalanceExamLog = new ServiceBalanceExamLog();
            serviceBalanceExamLog.setTaskNo(serviceBalanceInfo.getTaskNo());
            serviceBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            serviceBalanceExamLog.setBussinessStatus(serviceBalanceInfo.getBussinessStatus());
            serviceBalanceExamLog.setCreateTime(nowDate);
            serviceBalanceExamLog.setUpdateTime(nowDate);
            serviceBalanceExamLog.setCreateBy(userName);
            serviceBalanceExamLog.setUpdateBy(userName);
            serviceBalanceExamLog.setRemark(BalanceStatusEnum.ALREADY_CONFIRM.getMsg());
            int logNum = serviceBalanceExamLogMapper.insertServiceBalanceExamLog(serviceBalanceExamLog);
            logger.info("确认插入-结算审核日志表行数：" + logNum);
            serviceBalanceInfo.setLogId(serviceBalanceExamLog.getSerialNo());
        } else if (BalanceStatusEnum.TYPE_EXAMIN.getCode().equals(serviceBalanceInfo.getParams().get("type"))) {
            //清单审核
            //同意/不同意（导入状态为退回）

            ServiceBalanceExamLog serviceBalanceExamLog = new ServiceBalanceExamLog();
            serviceBalanceExamLog.setTaskNo(serviceBalanceInfo.getTaskNo());
            serviceBalanceExamLog.setExamineConclusion(serviceBalanceInfo.getBussinessStatus());
            serviceBalanceExamLog.setExamineOpinion(serviceBalanceInfo.getRemark());
            serviceBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            serviceBalanceExamLog.setBussinessStatus(serviceBalanceInfo.getBussinessStatus());
            serviceBalanceExamLog.setCreateTime(nowDate);
            serviceBalanceExamLog.setUpdateTime(nowDate);
            serviceBalanceExamLog.setCreateBy(userName);
            serviceBalanceExamLog.setUpdateBy(userName);
            if (BalanceStatusEnum.ALREADY_EXAMINE.getCode().equals(serviceBalanceInfo.getBussinessStatus())) {
                serviceBalanceExamLog.setRemark(BalanceStatusEnum.ALREADY_EXAMINE.getMsg());
            } else {
                serviceBalanceExamLog.setRemark(BalanceStatusEnum.WAIT_CONFIRM_BACK.getMsg());
                serviceBalanceInfo.setIsImport(StatusEnum.BACKVALID.getCode());
            }
            int logNum = serviceBalanceExamLogMapper.insertServiceBalanceExamLog(serviceBalanceExamLog);
            logger.info("审核插入-结算审核日志表行数：" + logNum);
            serviceBalanceInfo.setLogId(serviceBalanceExamLog.getSerialNo());
        } else if (BalanceStatusEnum.TYPE_WRITE.getCode().equals(serviceBalanceInfo.getParams().get("type"))) {
            //结算核销
            serviceBalanceInfo.setBussinessStatus(BalanceStatusEnum.ALREADY_WRITE.getCode());

            ServiceBalanceExamLog serviceBalanceExamLog = new ServiceBalanceExamLog();
            serviceBalanceExamLog.setTaskNo(serviceBalanceInfo.getTaskNo());
            serviceBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            serviceBalanceExamLog.setBussinessStatus(serviceBalanceInfo.getBussinessStatus());
            serviceBalanceExamLog.setCreateTime(nowDate);
            serviceBalanceExamLog.setUpdateTime(nowDate);
            serviceBalanceExamLog.setCreateBy(userName);
            serviceBalanceExamLog.setUpdateBy(userName);
            serviceBalanceExamLog.setRemark(BalanceStatusEnum.ALREADY_WRITE.getMsg());
            int logNum = serviceBalanceExamLogMapper.insertServiceBalanceExamLog(serviceBalanceExamLog);
            logger.info("核销插入-结算审核日志表行数：" + logNum);
            serviceBalanceInfo.setLogId(serviceBalanceExamLog.getSerialNo());
        } else if (BalanceStatusEnum.TYPE_INVOICE.getCode().equals(serviceBalanceInfo.getParams().get("type"))) {
            //发票核销
            serviceBalanceInfo.setInvoiceStatus(StatusEnum.VALID.getCode());
            ServiceBalanceExamLog serviceBalanceExamLog = new ServiceBalanceExamLog();
            serviceBalanceExamLog.setTaskNo(serviceBalanceInfo.getTaskNo());
            serviceBalanceExamLog.setStatus(StatusEnum.VALID.getCode());
            serviceBalanceExamLog.setBussinessStatus(serviceBalanceInfo.getBussinessStatus());
            serviceBalanceExamLog.setCreateTime(nowDate);
            serviceBalanceExamLog.setUpdateTime(nowDate);
            serviceBalanceExamLog.setCreateBy(userName);
            serviceBalanceExamLog.setUpdateBy(userName);
            serviceBalanceExamLog.setRemark(BalanceStatusEnum.TYPE_INVOICE.getMsg());
            int logNum = serviceBalanceExamLogMapper.insertServiceBalanceExamLog(serviceBalanceExamLog);
            logger.info("核销插入-结算审核日志表行数：" + logNum);
            serviceBalanceInfo.setLogId(serviceBalanceExamLog.getSerialNo());
        }
        serviceBalanceInfo.setUpdateTime(nowDate);
        serviceBalanceInfo.setUpdateBy(userName);
        serviceBalanceInfoMapper.updateServiceBalanceInfoByTaskNo(serviceBalanceInfo);
        return AjaxResult.success();
    }


}
