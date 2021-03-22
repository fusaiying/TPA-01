package com.paic.ehis.order.controller;

import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.order.domain.ServiceBalanceDetail;
import com.paic.ehis.order.domain.ServiceBalanceInfo;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailCaseVO;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailContVO;
import com.paic.ehis.order.domain.vo.ServiceBalanceDetailPriceVO;
import com.paic.ehis.order.service.IServiceBalanceDetailService;
import com.paic.ehis.order.service.IServiceBalanceInfoService;
import com.paic.ehis.order.service.enumeration.BalanceStatusEnum;
import com.paic.ehis.order.service.enumeration.SettleTypeEnum;
import com.paic.ehis.order.service.enumeration.StatusEnum;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.domain.BaseContractService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * service_balance_detail(服务结算明细信息)Controller
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/balanceDetail")
public class ServiceBalanceDetailController extends BaseController
{
    @Autowired
    private IServiceBalanceDetailService serviceBalanceDetailService;

    @Autowired
    private IServiceBalanceInfoService serviceBalanceInfoService;

    @Autowired
    private GetProviderInfoService getProviderInfoService;

    /**
     * 查询service_balance_detail(服务结算明细信息)列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ServiceBalanceDetail serviceBalanceDetail)
    {
        startPage(serviceBalanceDetail);
        List<ServiceBalanceDetail> list = serviceBalanceDetailService.selectServiceBalanceDetailList(serviceBalanceDetail);
        return getDataTable(list);
    }

    /**
     * 导出service_balance_detail(服务结算明细信息)列表
     */
    @Log(title = "service_balance_detail(服务结算明细信息)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceBalanceInfo serviceBalanceInfo) throws IOException
    {
        //按case
        if (SettleTypeEnum.SETTLE_TYPE_CASE.getCode().equals(serviceBalanceInfo.getSettleType())) {
            List<ServiceBalanceDetailCaseVO> list = serviceBalanceDetailService.selectServiceBalanceDetailList_case(serviceBalanceInfo);
            BaseContractService baseContractService = new BaseContractService();
            baseContractService.setStatus(StatusEnum.VALID.getCode());
            List<BaseContractService> contractServiceList = getProviderInfoService.selectBaseContractServiceInfo(baseContractService);
            for (ServiceBalanceDetailCaseVO serviceBalanceDetailCaseVO : list) {
                /*if (StringUtils.isNotEmpty(serviceBalanceDetailCaseVO.getSupplierServiceName())) {
                    for (BaseContractService contractService : contractServiceList) {
                        if (contractService.getSupplierServiceName().equals(serviceBalanceDetailCaseVO.getSupplierServiceName())) {
                            serviceBalanceDetailCaseVO.setSupplierServiceName(contractService.getSupplierServiceName());
                            break;
                        }
                    }
                }*/
            }
            ExcelUtil<ServiceBalanceDetailCaseVO> util = new ExcelUtil<ServiceBalanceDetailCaseVO>(ServiceBalanceDetailCaseVO.class);
            util.exportExcel(response, list, "按case（工单）");
        } else if (SettleTypeEnum.SETTLE_TYPE_CONT.getCode().equals(serviceBalanceInfo.getSettleType())) {
            //按人头（保单）
            List<ServiceBalanceDetailContVO> list = serviceBalanceDetailService.selectServiceBalanceDetailList_cont(serviceBalanceInfo);
            ExcelUtil<ServiceBalanceDetailContVO> util = new ExcelUtil<ServiceBalanceDetailContVO>(ServiceBalanceDetailContVO.class);
            util.exportExcel(response, list, "按人头（保单）");
        } else if (SettleTypeEnum.SETTLE_TYPE_PRICE.getCode().equals(serviceBalanceInfo.getSettleType())) {
            //按一口价
            List<ServiceBalanceDetailPriceVO> list = serviceBalanceDetailService.selectServiceBalanceDetailList_price(serviceBalanceInfo);
            ExcelUtil<ServiceBalanceDetailPriceVO> util = new ExcelUtil<ServiceBalanceDetailPriceVO>(ServiceBalanceDetailPriceVO.class);
            util.exportExcel(response, list, "按一口价");
        }
    }

    /**
     * 导入service_balance_detail(服务结算明细信息)列表
     * @param file
     * @return
     */
    @PostMapping("/importData")
    public AjaxResult importData(HttpServletResponse response,MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String[] fileNameArr = fileName.split("_");
        if (fileNameArr.length != 3) {
            return AjaxResult.error("文件名称不规范，请以“balance_任务号_时间戳”命名导入！");
        }
        String taskNo = fileNameArr[1];
        ServiceBalanceInfo serviceBalanceInfo = serviceBalanceInfoService.selectServiceBalanceInfoByTaskNo(taskNo);
        if (serviceBalanceInfo == null
                || (null != serviceBalanceInfo && !StatusEnum.VALID.getCode().equals(serviceBalanceInfo.getStatus()))) {
            return AjaxResult.error("任务“" + taskNo + "”不存在，请先发起结算！");
        }

        ServiceBalanceDetail serviceBalanceDetail = new ServiceBalanceDetail();
        serviceBalanceDetail.setTaskNo(taskNo);
        serviceBalanceDetail.setStatus(StatusEnum.VALID.getCode());
        List<ServiceBalanceDetail> dataList = serviceBalanceDetailService.selectServiceBalanceDetailList(serviceBalanceDetail);
        if (dataList.size() == 0) {
            return AjaxResult.error("任务号“" + taskNo + "”不存在，请先发起结算！");
        }

        StringBuffer error = new StringBuffer();
        StringBuffer errorTmp = new StringBuffer();
        //按case
        if (SettleTypeEnum.SETTLE_TYPE_CASE.getCode().equals(serviceBalanceInfo.getSettleType())) {
            ExcelUtil<ServiceBalanceDetailCaseVO> util = new ExcelUtil<ServiceBalanceDetailCaseVO>(ServiceBalanceDetailCaseVO.class);
            List<ServiceBalanceDetailCaseVO> excelCaseList = null;
            try {
                excelCaseList = util.importExcel(file.getInputStream());
            }  catch (Exception ex) {
                return AjaxResult.error("表格解析失败，请检查数据，再进行上传！");
            }
            if (excelCaseList.size() == 0) {
                return AjaxResult.error("上传失败，Excel中数据为空！");
            }
            if (dataList.size() < excelCaseList.size()) {
                return AjaxResult.error("结算明细有" + dataList.size() + "条，Excel中有" + excelCaseList.size() + "条!");
            }
            for (int i = 0; i < dataList.size(); i++) {
                //先置为无效
                dataList.get(i).setStatus(StatusEnum.INVALID.getCode());
                for (ServiceBalanceDetailCaseVO excelCaseDetail : excelCaseList) {
                    if (dataList.get(i).getOrderCode().equals(excelCaseDetail.getOrderCode())) {
                        errorTmp = new StringBuffer();
                        //匹配到置为有效
                        dataList.get(i).setStatus(StatusEnum.VALID.getCode());
                        if ("".equals(excelCaseDetail.getActualAmount()) || excelCaseDetail.getActualAmount() == null) {
                            errorTmp.append("，实际服务费为空");
                        }else{
                            //表格实际服务费与数据库服务费一致存到数据库实际服务费
                            if (dataList.get(i).getAmount().compareTo(excelCaseDetail.getActualAmount()) == 0) {
                                dataList.get(i).setActualAmount(excelCaseDetail.getActualAmount());
                            } else if (dataList.get(i).getAmount().compareTo(excelCaseDetail.getActualAmount()) != 0) {
                                //表格实际服务费<数据库服务费时，原因必填
                                if (StringUtils.isNotEmpty(excelCaseDetail.getReason())) {
                                    dataList.get(i).setActualAmount(excelCaseDetail.getActualAmount());
                                    dataList.get(i).setReason(excelCaseDetail.getReason());
                                } else {
                                    errorTmp.append("，服务费不一致，原因未填");
                                }
                            } /*else {
                             *//*errorTmp.append("，实际服务费不能低于服务费");*//*
                            dataList.get(i).setActualAmount(excelCaseDetail.getActualAmount());
                        }*/
                        }

                        if (errorTmp.length() > 0) {
                            error.append("第" + (i + 2) + "行");
                            error.append(errorTmp);
                        }
                        break;
                    }
                }
            }
            if (error.length() > 0) {
                return AjaxResult.error(error.toString());
            }
        } else if (SettleTypeEnum.SETTLE_TYPE_CONT.getCode().equals(serviceBalanceInfo.getSettleType())) {
            //按人头（保单）
            error.append("暂不支持！");
            if (error.length() > 0) {
                return AjaxResult.error(error.toString());
            }
        } else if (SettleTypeEnum.SETTLE_TYPE_PRICE.getCode().equals(serviceBalanceInfo.getSettleType())) {
            //按一口价
            error.append("暂不支持！");
            if (error.length() > 0) {
                return AjaxResult.error(error.toString());
            }
        }

        //插入明细条数
        int detailNum = 0;
        //总服务费
        BigDecimal serviceAmount = BigDecimal.ZERO;
        for (ServiceBalanceDetail dataDetail : dataList) {
            dataDetail.setUpdateTime(DateUtils.getNowDate());
            dataDetail.setUpdateBy(SecurityUtils.getUsername());
            detailNum = serviceBalanceDetailService.updateServiceBalanceDetail(dataDetail);
            serviceAmount = serviceAmount.add(dataDetail.getActualAmount());
        }
        logger.info("插入-结算明细表行数：" + detailNum);
        serviceBalanceInfo.setServiceAmount(serviceAmount);
        serviceBalanceInfo.getParams().put("type", BalanceStatusEnum.TYPE_IMPORT.getCode());
        return serviceBalanceInfoService.updateServiceBalanceInfoByTaskNo(serviceBalanceInfo);
    }

    /**
     * 获取service_balance_detail(服务结算明细信息)详细信息
     */
    @GetMapping(value = "/{serialNo}")
    public AjaxResult getInfo(@PathVariable("serialNo") Long serialNo)
    {
        return AjaxResult.success(serviceBalanceDetailService.selectServiceBalanceDetailById(serialNo));
    }

    /**
     * 新增service_balance_detail(服务结算明细信息)
     */
    @Log(title = "service_balance_detail(服务结算明细信息)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceBalanceDetail serviceBalanceDetail)
    {
        return toAjax(serviceBalanceDetailService.insertServiceBalanceDetail(serviceBalanceDetail));
    }

    /**
     * 修改service_balance_detail(服务结算明细信息)
     */
    @Log(title = "service_balance_detail(服务结算明细信息)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceBalanceDetail serviceBalanceDetail)
    {
        return toAjax(serviceBalanceDetailService.updateServiceBalanceDetail(serviceBalanceDetail));
    }

    /**
     * 删除service_balance_detail(服务结算明细信息)
     */
    @Log(title = "service_balance_detail(服务结算明细信息)", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serialNos}")
    public AjaxResult remove(@PathVariable Long[] serialNos)
    {
        return toAjax(serviceBalanceDetailService.deleteServiceBalanceDetailByIds(serialNos));
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表导出
     */
    @Log(title = "service_balance_detail(服务结算明细信息)", businessType = BusinessType.EXPORT)
    @PostMapping("/export2")
    public void export2(HttpServletResponse response, ServiceBalanceDetail serviceBalanceDetail) throws IOException
    { List<ServiceBalanceDetail> list = serviceBalanceDetailService.selectServiceBalanceDetailList2(serviceBalanceDetail);
        ExcelUtil<ServiceBalanceDetail> util = new ExcelUtil<ServiceBalanceDetail>(ServiceBalanceDetail.class);
        util.exportExcel(response, list, "服务结算明细信息");
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表
     */
    @PostMapping("/list2")
    public TableDataInfo list2(@RequestBody ServiceBalanceDetail serviceBalanceDetail)
    {
        startPage(serviceBalanceDetail);
        List<ServiceBalanceDetail> list = serviceBalanceDetailService.selectServiceBalanceDetailList2(serviceBalanceDetail);
        return getDataTable(list);
    }

    /**
     * 查询service_balance_detail(服务结算明细信息)列表30天的默认数据
     */
    @GetMapping("/list3")
    public TableDataInfo list3() throws Exception
    {
        startPage();
        List<ServiceBalanceDetail> list = serviceBalanceDetailService.selectServiceBalanceDetailList3();
        return getDataTable(list);
    }

    /**
     * 按case结算明细列表
     */
    @PostMapping("/detailList")
    public TableDataInfo detailList(@RequestBody ServiceBalanceInfo serviceBalanceInfo)
    {
        startPage(serviceBalanceInfo);
        List<ServiceBalanceDetail> list = serviceBalanceDetailService.selectDetailList(serviceBalanceInfo);
        return getDataTable(list);
    }
}
