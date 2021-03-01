package com.paic.ehis.system.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.system.domain.SysOrganInfo;
import com.paic.ehis.system.domain.dto.OrganListDTO;
import com.paic.ehis.system.service.ISysOrganInfoService;
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
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 机构信息Controller
 * 
 * @author sino
 * @date 2021-02-19
 */
@RestController
@RequestMapping("/organInfo")
public class SysOrganInfoController extends BaseController
{
    @Autowired
    private ISysOrganInfoService sysOrganInfoService;

    /**
     * 查询机构信息列表
     */
    //@PreAuthorize(hasPermi = "system:info:list")
    @GetMapping("/list")
    public TableDataInfo list(SysOrganInfo sysOrganInfo)
    {
        startPage();
        List<SysOrganInfo> list = sysOrganInfoService.selectSysOrganInfoList(sysOrganInfo);
        return getDataTable(list);
    }

    /**
     * 导出机构信息列表
     */
    //@PreAuthorize(hasPermi = "system:info:export")
    @Log(title = "机构信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOrganInfo sysOrganInfo) throws IOException
    {
        List<SysOrganInfo> list = sysOrganInfoService.selectSysOrganInfoList(sysOrganInfo);
        ExcelUtil<SysOrganInfo> util = new ExcelUtil<SysOrganInfo>(SysOrganInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取机构信息详细信息
     */
    //@PreAuthorize(hasPermi = "system:info:query")
    @GetMapping(value = "/{organId}")
    public AjaxResult getInfo(@PathVariable("organId") Long organId)
    {
        return AjaxResult.success(sysOrganInfoService.selectSysOrganInfoById(organId));
    }

    /**
     * 新增机构信息
     */
    //@PreAuthorize(hasPermi = "system:info:add")
    @Log(title = "机构信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrganInfo sysOrganInfo)
    {
        return toAjax(sysOrganInfoService.insertSysOrganInfo(sysOrganInfo));
    }

    /**
     * 修改机构信息
     */
    //@PreAuthorize(hasPermi = "system:info:edit")
    @Log(title = "机构信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrganInfo sysOrganInfo)
    {
        return toAjax(sysOrganInfoService.updateSysOrganInfo(sysOrganInfo));
    }

    /**
     * 删除机构信息
     */
    //@PreAuthorize(hasPermi = "system:info:remove")
    @Log(title = "机构信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{organIds}")
    public AjaxResult remove(@PathVariable Long[] organIds)
    {
        return toAjax(sysOrganInfoService.deleteSysOrganInfoByIds(organIds));
    }

    /**
     * 根据机构编码集合查询机构编码名称
     *
     * @param organCodes
     * @return
     */
    @PostMapping("/getOrganInfo")
    public AjaxResult getOrganInfo(@RequestBody List<String> organCodes){
        return AjaxResult.success(sysOrganInfoService.selectOrganInfoByOrganCodes(organCodes));
    }

    /**
     * 获取当前机构及下属机构详细信息
     */
    @PostMapping(value = "getOrganList")
    public TableDataInfo getInfo(@RequestBody OrganListDTO organListDTO)
    {
        startPage(organListDTO);
        List<SysOrganInfo> list = sysOrganInfoService.selectOrganListByUpOrganCode(organListDTO);
        return getDataTable(list);
    }
}
