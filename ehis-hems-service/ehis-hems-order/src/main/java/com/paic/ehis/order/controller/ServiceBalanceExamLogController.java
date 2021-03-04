package com.paic.ehis.order.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.order.domain.ServiceBalanceExamLog;
import com.paic.ehis.order.service.IServiceBalanceExamLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * service_balance_exam_log(服务结算审核日志)Controller
 * 
 * @author chenhaiming
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/balanceExamLog")
public class ServiceBalanceExamLogController extends BaseController
{
    @Autowired
    private IServiceBalanceExamLogService serviceBalanceExamLogService;

    /**
     * 查询service_balance_exam_log(服务结算审核日志)列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ServiceBalanceExamLog serviceBalanceExamLog)
    {
        startPage(serviceBalanceExamLog);
        List<ServiceBalanceExamLog> list = serviceBalanceExamLogService.selectServiceBalanceExamLogList(serviceBalanceExamLog);
        return getDataTable(list);
    }

    /**
     * 导出service_balance_exam_log(服务结算审核日志)列表
     */
    @Log(title = "service_balance_exam_log(服务结算审核日志)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceBalanceExamLog serviceBalanceExamLog) throws IOException
    {
        List<ServiceBalanceExamLog> list = serviceBalanceExamLogService.selectServiceBalanceExamLogList(serviceBalanceExamLog);
        ExcelUtil<ServiceBalanceExamLog> util = new ExcelUtil<ServiceBalanceExamLog>(ServiceBalanceExamLog.class);
        util.exportExcel(response, list, "log");
    }

    /**
     * 获取service_balance_exam_log(服务结算审核日志)详细信息
     */
    @GetMapping(value = "/{serialNo}")
    public AjaxResult getInfo(@PathVariable("serialNo") Long serialNo)
    {
        return AjaxResult.success(serviceBalanceExamLogService.selectServiceBalanceExamLogById(serialNo));
    }

    /**
     * 新增service_balance_exam_log(服务结算审核日志)
     */
    @Log(title = "service_balance_exam_log(服务结算审核日志)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceBalanceExamLog serviceBalanceExamLog)
    {
        return toAjax(serviceBalanceExamLogService.insertServiceBalanceExamLog(serviceBalanceExamLog));
    }

    /**
     * 修改service_balance_exam_log(服务结算审核日志)
     */
    @Log(title = "service_balance_exam_log(服务结算审核日志)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceBalanceExamLog serviceBalanceExamLog)
    {
        return toAjax(serviceBalanceExamLogService.updateServiceBalanceExamLog(serviceBalanceExamLog));
    }

    /**
     * 删除service_balance_exam_log(服务结算审核日志)
     */
    @Log(title = "service_balance_exam_log(服务结算审核日志)", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serialNos}")
    public AjaxResult remove(@PathVariable Long[] serialNos)
    {
        return toAjax(serviceBalanceExamLogService.deleteServiceBalanceExamLogByIds(serialNos));
    }
}
