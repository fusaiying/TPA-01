package com.paic.ehis.order.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.order.service.IHmpOrdOwnInfoService;

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
import com.paic.ehis.order.domain.HmpOrdOwnInfo;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 个人订单Controller
 * 
 * @author sino
 * @date 2020-09-24
 */
@RestController
@RequestMapping("/personal")
public class HmpOrdOwnInfoController extends BaseController
{
    @Autowired
    private IHmpOrdOwnInfoService hmpordowninfoService;

    /**
     * 查询个人订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:personal:list')")
    @GetMapping("/list")
    public TableDataInfo list(HmpOrdOwnInfo hmpordowninfo)
    {
        startPage();
        List<HmpOrdOwnInfo> list = hmpordowninfoService.selectHmpOrdOwnInfoList(hmpordowninfo);
        return getDataTable(list);
    }

    /**
     * 导出个人订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:personal:export')")
    @Log(title = "个人订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HmpOrdOwnInfo hmpordowninfo) throws IOException
    {
        List<HmpOrdOwnInfo> list = hmpordowninfoService.selectHmpOrdOwnInfoList(hmpordowninfo);
        ExcelUtil<HmpOrdOwnInfo> util = new ExcelUtil<HmpOrdOwnInfo>(HmpOrdOwnInfo.class);
        util.exportExcel(response, list, "personal");
    }

    /**
     * 获取个人订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:personal:query')")
    @GetMapping(value = "/{personalorderno}")
    public AjaxResult getInfo(@PathVariable("personalorderno") String personalorderno)
    {
        return AjaxResult.success(hmpordowninfoService.selectHmpOrdOwnInfoById(personalorderno));
    }

    /**
     * 新增个人订单
     */
    @PreAuthorize("@ss.hasPermi('order:personal:add')")
    @Log(title = "个人订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HmpOrdOwnInfo hmpordowninfo)
    {
        return toAjax(hmpordowninfoService.insertHmpOrdOwnInfo(hmpordowninfo));
    }

    /**
     * 修改个人订单
     */
    @PreAuthorize("@ss.hasPermi('order:personal:edit')")
    @Log(title = "个人订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HmpOrdOwnInfo hmpordowninfo)
    {
        return toAjax(hmpordowninfoService.updateHmpOrdOwnInfo(hmpordowninfo));
    }

    /**
     * 删除个人订单
     */
    @PreAuthorize("@ss.hasPermi('order:personal:remove')")
    @Log(title = "个人订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{appnos}")
    public AjaxResult remove(@PathVariable String[] appnos)
    {
        return toAjax(hmpordowninfoService.deleteHmpOrdOwnInfoByIds(appnos));
    }
}
