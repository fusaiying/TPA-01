package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.CallAgain;
import com.paic.ehis.cs.service.ICallAgainService;
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
 * 再次来电信息 Controller
 * 
 * @author sino
 * @date 2021-01-21
 */
@RestController
@RequestMapping("/again")
public class CallAgainController extends BaseController
{
    @Autowired
    private ICallAgainService callAgainService;

    /**
     * 查询再次来电信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:again:list')")
    @GetMapping("/list")
    public TableDataInfo list(CallAgain callAgain)
    {
        startPage();
        List<CallAgain> list = callAgainService.selectCallAgainList(callAgain);
        return getDataTable(list);
    }

    /**
     * 导出再次来电信息 列表
     */
//    @PreAuthorize("@ss.hasPermi('system:again:export')")
    @Log(title = "再次来电信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CallAgain callAgain) throws IOException
    {
        List<CallAgain> list = callAgainService.selectCallAgainList(callAgain);
        ExcelUtil<CallAgain> util = new ExcelUtil<CallAgain>(CallAgain.class);
        util.exportExcel(response, list, "again");
    }

    /**
     * 获取再次来电信息 详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:again:query')")
    @GetMapping(value = "/{callSerialNum}")
    public AjaxResult getInfo(@PathVariable("callSerialNum") String callSerialNum)
    {
        return AjaxResult.success(callAgainService.selectCallAgainById(callSerialNum));
    }

    /**
     * 新增再次来电信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:again:add')")
    @Log(title = "再次来电信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CallAgain callAgain)
    {
        return toAjax(callAgainService.insertCallAgain(callAgain));
    }

    /**
     * 修改再次来电信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:again:edit')")
    @Log(title = "再次来电信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CallAgain callAgain)
    {
        return toAjax(callAgainService.updateCallAgain(callAgain));
    }

    /**
     * 删除再次来电信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:again:remove')")
    @Log(title = "再次来电信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{callSerialNums}")
    public AjaxResult remove(@PathVariable String[] callSerialNums)
    {
        return toAjax(callAgainService.deleteCallAgainByIds(callSerialNums));
    }
    /**
     * 查询再次来电工单提醒
     */
//    @PreAuthorize("@ss.hasPermi('system:again:list')")
    @GetMapping("/selectCallAgain")
    public TableDataInfo selectCallAgain(CallAgain callAgain)
    {
        startPage();
        List<CallAgain> list = callAgainService.selectCallAgain(callAgain);
        return getDataTable(list);
    }


}
