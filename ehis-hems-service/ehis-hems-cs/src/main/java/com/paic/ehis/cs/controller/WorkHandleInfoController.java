package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.EditInfo;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.ReservationDealVo;
import com.paic.ehis.cs.domain.vo.ServiceProcessingVo;
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
import com.paic.ehis.cs.domain.WorkHandleInfo;
import com.paic.ehis.cs.service.IWorkHandleInfoService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 工单处理信息 Controller
 * 
 * @author sino
 * @date 2021-02-02
 */
@RestController
@RequestMapping("/info3")
public class WorkHandleInfoController extends BaseController
{
    @Autowired
    private IWorkHandleInfoService workHandleInfoService;

    /**
     * 查询工单处理信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkHandleInfo workHandleInfo)
    {
        startPage();
        List<WorkHandleInfo> list = workHandleInfoService.selectWorkHandleInfoList(workHandleInfo);
        return getDataTable(list);
    }

    /**
     * 导出工单处理信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "工单处理信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkHandleInfo workHandleInfo) throws IOException
    {
        List<WorkHandleInfo> list = workHandleInfoService.selectWorkHandleInfoList(workHandleInfo);
        ExcelUtil<WorkHandleInfo> util = new ExcelUtil<WorkHandleInfo>(WorkHandleInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取工单处理信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{handleId}")
    public AjaxResult getInfo(@PathVariable("handleId") Long handleId)
    {
        return AjaxResult.success(workHandleInfoService.selectWorkHandleInfoById(handleId));
    }

    /**
     * 新增工单处理信息 
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "工单处理信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkHandleInfo workHandleInfo)
    {
        return toAjax(workHandleInfoService.insertWorkHandleInfo(workHandleInfo));
    }

    /**
     * 修改工单处理信息 
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "工单处理信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkHandleInfo workHandleInfo)
    {
        return toAjax(workHandleInfoService.updateWorkHandleInfo(workHandleInfo));
    }

    /**
     * 删除工单处理信息 
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "工单处理信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{handleIds}")
    public AjaxResult remove(@PathVariable Long[] handleIds)
    {
        return toAjax(workHandleInfoService.deleteWorkHandleInfoByIds(handleIds));
    }

    /**
     * 查询工单业处理信息 信息需求
     */
    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/selectWorkOrder ")
    public TableDataInfo selectWorkOrder(ServiceProcessingVo serviceProcessingVo) {
        List<WorkHandleInfo> list = workHandleInfoService.selectWorkOrder(serviceProcessingVo);
        return getDataTable(list);
    }
    /**
     * 查询工单业处理信息 预约
     */
    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/selectOrderWorkOrder ")
    public TableDataInfo selectOrderWorkOrder(ReservationDealVo reservationDealVo) {
        List<WorkHandleInfo> list = workHandleInfoService.selectOrderWorkOrder(reservationDealVo);
        return getDataTable(list);
    }
    /**
     * 查询工单业处理信息 投诉
     */
    @PreAuthorize("@ss.hasPermi('system:customService:list')")
    @GetMapping("/selectComplaintWorkOrder ")
    public TableDataInfo selectComplaintWorkOrder(ComplaintDealVo complaintDealVo) {
        List<WorkHandleInfo> list = workHandleInfoService.selectComplaintWorkOrder(complaintDealVo);
        return getDataTable(list);
    }
}
