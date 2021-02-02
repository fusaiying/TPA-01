package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.PubFun;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseManualInfo;
import com.paic.ehis.base.service.IBaseManualInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 服务手册 Controller
 * 
 * @author sino
 * @date 2021-01-10
 */
@RestController
@RequestMapping("/manual")
public class BaseManualInfoController extends BaseController
{
    @Autowired
    private IBaseManualInfoService baseManualInfoService;

    /**
     * 查询服务手册列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BaseManualInfo baseManualInfo)
    {
        startPage();
        List<BaseManualInfo> list = baseManualInfoService.selectBaseManualInfoList(baseManualInfo);
        return getDataTable(list);
    }

    /**
     * 导出服务手册列表
     */
    @Log(title = "服务手册", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseManualInfo baseManualInfo) throws IOException
    {
        List<BaseManualInfo> list = baseManualInfoService.selectBaseManualInfoList(baseManualInfo);
        ExcelUtil<BaseManualInfo> util = new ExcelUtil<BaseManualInfo>(BaseManualInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取服务手册详细信息
     */
    @GetMapping(value = "/{manualCode}")
    public AjaxResult getInfo(@PathVariable("manualCode") String manualCode)
    {
        return AjaxResult.success(baseManualInfoService.selectBaseManualInfoById(manualCode));
    }

    /**
     * 新增服务手册
     */
    @Log(title = "服务手册", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseManualInfo baseManualInfo)
    {
        String username = SecurityUtils.getUsername();
        baseManualInfo.setSerialNo(PubFun.createMySqlMaxNoUseCache("manualSer",10,20));
        baseManualInfo.setManualCode("FW"+PubFun.getCurrentDate()+PubFun.createMySqlMaxNoUseCache("manualCode",10,10));
        baseManualInfo.setCreateBy(username);
        baseManualInfo.setStatus("Y");
        return toAjax(baseManualInfoService.insertBaseManualInfo(baseManualInfo));
    }

    /**
     * 修改服务手册
     */
    @Log(title = "服务手册", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseManualInfo baseManualInfo)
    {
        return toAjax(baseManualInfoService.updateBaseManualInfo(baseManualInfo));
    }

    /**
     * 删除服务手册
     */
    @Log(title = "服务手册", businessType = BusinessType.DELETE)
	@DeleteMapping("/{manualCode}")
    public AjaxResult remove(@PathVariable String manualCode)
    {
        return toAjax(baseManualInfoService.deleteBaseManualInfoById(manualCode));
    }
}
