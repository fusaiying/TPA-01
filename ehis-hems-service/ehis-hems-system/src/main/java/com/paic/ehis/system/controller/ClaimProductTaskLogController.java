package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimProductTaskLog;
import com.paic.ehis.system.domain.ClaimProductTaskLogInfo;
import com.paic.ehis.common.security.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.system.service.IClaimProductTaskLogService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 产品状态轨迹 Controller
 * 
 * @author sino
 * @date 2021-01-07
 */
@RestController
@RequestMapping("/log")
public class ClaimProductTaskLogController extends BaseController
{
    @Autowired
    private IClaimProductTaskLogService claimProductTaskLogService;

    /**
     * 查询产品状态轨迹 列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimProductTaskLog claimProductTaskLog)
    {
        startPage();
        List<ClaimProductTaskLog> list = claimProductTaskLogService.selectClaimProductTaskLogList(claimProductTaskLog);
        return getDataTable(list);
    }

    /**
     * 导出产品状态轨迹 列表
     */
    @PreAuthorize("@ss.hasPermi('system:log:export')")
    @Log(title = "产品状态轨迹 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimProductTaskLog claimProductTaskLog) throws IOException
    {
        List<ClaimProductTaskLog> list = claimProductTaskLogService.selectClaimProductTaskLogList(claimProductTaskLog);
        ExcelUtil<ClaimProductTaskLog> util = new ExcelUtil<ClaimProductTaskLog>(ClaimProductTaskLog.class);
        util.exportExcel(response, list, "log");
    }

    /**
     * 获取产品状态轨迹 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/{riskLogNo}")
    public AjaxResult getInfo(@PathVariable("riskLogNo") String riskLogNo)
    {
        return AjaxResult.success(claimProductTaskLogService.selectClaimProductTaskLogById(riskLogNo));
    }

    /**
     * 新增产品状态轨迹 
     */
    @PreAuthorize("@ss.hasPermi('system:log:add')")
    @Log(title = "产品状态轨迹 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimProductTaskLogInfo claimProductTaskLogInfo)
    {
        claimProductTaskLogInfo.setCreateBy(SecurityUtils.getUsername());
        return toAjax(claimProductTaskLogService.insertClaimProductTaskLog(claimProductTaskLogInfo));
    }

    /**
     * 检查产品下的计划 保障区域
     */
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/check/{riskCode}")
    public AjaxResult checkInfo(@PathVariable("riskCode") String riskCode)
    {
        return AjaxResult.success(claimProductTaskLogService.checkClaimProductById(riskCode));
    }

    /**
     * 修改产品状态轨迹 
     */
    @PreAuthorize("@ss.hasPermi('system:log:edit')")
    @Log(title = "产品状态轨迹 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimProductTaskLog claimProductTaskLog)
    {
        return toAjax(claimProductTaskLogService.updateClaimProductTaskLog(claimProductTaskLog));
    }

    /**
     * 删除产品状态轨迹 
     */
    @PreAuthorize("@ss.hasPermi('system:log:remove')")
    @Log(title = "产品状态轨迹 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{riskLogNos}")
    public AjaxResult remove(@PathVariable String[] riskLogNos)
    {
        return toAjax(claimProductTaskLogService.deleteClaimProductTaskLogByIds(riskLogNos));
    }
}
