package com.paic.ehis.claimmgt.controller;

import com.paic.ehis.claimmgt.domain.ClaimUserTakeOn;
import com.paic.ehis.claimmgt.domain.dto.UserTakeOnDTO;
import com.paic.ehis.claimmgt.service.IClaimUserTakeOnService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 理赔用户工作承接Controller
 * 
 * @author sino
 * @date 2021-01-22
 */
@RestController
@RequestMapping("/on")
public class ClaimUserTakeOnController extends BaseController
{
    @Autowired
    private IClaimUserTakeOnService claimUserTakeOnService;

    /**
     * 查询理赔用户工作承接列表
     */
   // @PreAuthorize("@ss.hasPermi('system:on:list')")
    @GetMapping("/listt")
    public TableDataInfo list(ClaimUserTakeOn claimUserTakeOn)
    {
        startPage();
        List<ClaimUserTakeOn> list = claimUserTakeOnService.selectClaimUserTakeOnList(claimUserTakeOn);
        return getDataTable(list);
    }

    /**
     * 查询理赔用户工作承接列表
     */
   // @PreAuthorize("@ss.hasPermi('system:on:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserTakeOnDTO userTakeOnDTO)
    {
        startPage();
        List<UserTakeOnDTO> list = claimUserTakeOnService.selectClaimUserTakeOnListOne(userTakeOnDTO);
        return getDataTable(list);
    }


    /**
     * 导出理赔用户工作承接列表
     */
   // @PreAuthorize("@ss.hasPermi('system:on:export')")
    @Log(title = "理赔用户工作承接", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimUserTakeOn claimUserTakeOn) throws IOException
    {
        List<ClaimUserTakeOn> list = claimUserTakeOnService.selectClaimUserTakeOnList(claimUserTakeOn);
        ExcelUtil<ClaimUserTakeOn> util = new ExcelUtil<ClaimUserTakeOn>(ClaimUserTakeOn.class);
        util.exportExcel(response, list, "on");
    }

    /**
     * 获取理赔用户工作承接详细信息
     */
   // @PreAuthorize("@ss.hasPermi('system:on:query')")
    @GetMapping(value = "/{takeOnId}")
    public AjaxResult getInfo(@PathVariable("takeOnId") String takeOnId)
    {
        return AjaxResult.success(claimUserTakeOnService.selectClaimUserTakeOnById(takeOnId));
    }

    /**
     * 新增理赔用户工作承接
     */
   //@PreAuthorize("@ss.hasPermi('system:on:add')")
    @Log(title = "理赔用户工作承接", businessType = BusinessType.INSERT)
    @PostMapping("add")
    public AjaxResult add(@RequestBody ClaimUserTakeOn claimUserTakeOn)
    {
        return toAjax(claimUserTakeOnService.insertClaimUserTakeOn(claimUserTakeOn));
    }

    /**
     * 修改理赔用户工作承接
     */
    //@PreAuthorize("@ss.hasPermi('system:on:edit')")
    @Log(title = "理赔用户工作承接", businessType = BusinessType.UPDATE)
    @PutMapping("update")
    public AjaxResult edit(@RequestBody ClaimUserTakeOn claimUserTakeOn)
    {
        return toAjax(claimUserTakeOnService.updateClaimUserTakeOn(claimUserTakeOn));
    }

    /**
     * 删除理赔用户工作承接
     */
   // @PreAuthorize("@ss.hasPermi('system:on:remove')")
    @Log(title = "理赔用户工作承接", businessType = BusinessType.DELETE)
	@DeleteMapping("/{takeOnIds}")
    public AjaxResult remove(@PathVariable String[] takeOnIds)
    {
        return toAjax(claimUserTakeOnService.deleteClaimUserTakeOnByIds(takeOnIds));
    }
}
