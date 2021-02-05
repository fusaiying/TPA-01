package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.AcceptDetailInfo;
import com.paic.ehis.cs.service.IAcceptDetailInfoService;
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
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 受理详情 Controller
 * 
 * @author sino
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/info1")
public class AcceptDetailInfoController extends BaseController
{
    @Autowired
    private IAcceptDetailInfoService acceptDetailInfoService;

    /**
     * 查询受理详情 列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(AcceptDetailInfo acceptDetailInfo)
    {
        startPage();
        List<AcceptDetailInfo> list = acceptDetailInfoService.selectAcceptDetailInfoList(acceptDetailInfo);
        return getDataTable(list);
    }



    /**
     * 导出受理详情 列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "受理详情 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AcceptDetailInfo acceptDetailInfo) throws IOException
    {
        List<AcceptDetailInfo> list = acceptDetailInfoService.selectAcceptDetailInfoList(acceptDetailInfo);
        ExcelUtil<AcceptDetailInfo> util = new ExcelUtil<AcceptDetailInfo>(AcceptDetailInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取受理详情 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{workOrderNo}")
    public AjaxResult getInfo(@PathVariable("workOrderNo") String workOrderNo)
    {
        return AjaxResult.success(acceptDetailInfoService.selectAcceptDetailInfoById(workOrderNo));
    }

    /**
     * 新增受理详情 
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "受理详情 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AcceptDetailInfo acceptDetailInfo)
    {
        return toAjax(acceptDetailInfoService.insertAcceptDetailInfo(acceptDetailInfo));
    }

    /**
     * 修改受理详情 
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "受理详情 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AcceptDetailInfo acceptDetailInfo)
    {
        return toAjax(acceptDetailInfoService.updateAcceptDetailInfo(acceptDetailInfo));
    }

    /**
     * 删除受理详情 
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "受理详情 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workOrderNos}")
    public AjaxResult remove(@PathVariable String[] workOrderNos)
    {
        return toAjax(acceptDetailInfoService.deleteAcceptDetailInfoByIds(workOrderNos));
    }
}
