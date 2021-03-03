package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.EditInfo;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.vo.ComplaintDealVo;
import com.paic.ehis.cs.domain.vo.ReservationDealVo;
import com.paic.ehis.cs.domain.vo.ServiceProcessingVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    //----------------------------------------------------------------------------------------------------

    @GetMapping("/selectDealVo")
    public AjaxResult selectDealVo(WorkOrderAccept workOrderAccept) {
        String workOrderNo=workOrderAccept.getWorkOrderNo();
        String businessType=workOrderAccept.getBusinessType();
        if (businessType=="01"){
            return AjaxResult.success(workHandleInfoService.selectServiceProcessingVo(workOrderNo));
        }else if (businessType=="02"){
            return AjaxResult.success(workHandleInfoService.selectReservationDealVoByNo(workOrderNo));
        }else{
            return AjaxResult.success(workHandleInfoService.selectWorkHandleInfoByNo(workOrderNo));
        }
    }

}
