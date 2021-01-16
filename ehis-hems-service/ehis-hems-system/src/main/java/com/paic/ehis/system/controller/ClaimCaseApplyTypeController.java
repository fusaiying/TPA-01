package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.ClaimCaseApplyType;
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
import com.paic.ehis.system.service.IClaimCaseApplyTypeService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 案件申请类别Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/type")
public class ClaimCaseApplyTypeController extends BaseController
{
    @Autowired
    private IClaimCaseApplyTypeService claimCaseApplyTypeService;

    /**
     * 查询案件申请类别列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseApplyType claimCaseApplyType)
    {
        startPage();
        List<ClaimCaseApplyType> list = claimCaseApplyTypeService.selectClaimCaseApplyTypeList(claimCaseApplyType);
        return getDataTable(list);
    }

    /**
     * 导出案件申请类别列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "案件申请类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseApplyType claimCaseApplyType) throws IOException
    {
        List<ClaimCaseApplyType> list = claimCaseApplyTypeService.selectClaimCaseApplyTypeList(claimCaseApplyType);
        ExcelUtil<ClaimCaseApplyType> util = new ExcelUtil<ClaimCaseApplyType>(ClaimCaseApplyType.class);
        util.exportExcel(response, list, "type");
    }

    /**
     * 获取案件申请类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{applyId}")
    public AjaxResult getInfo(@PathVariable("applyId") Long applyId)
    {
        return AjaxResult.success(claimCaseApplyTypeService.selectClaimCaseApplyTypeById(applyId));
    }

    /**
     * 新增案件申请类别
     */
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "案件申请类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseApplyType claimCaseApplyType)
    {
        return toAjax(claimCaseApplyTypeService.insertClaimCaseApplyType(claimCaseApplyType));
    }

    /**
     * 修改案件申请类别
     */
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "案件申请类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseApplyType claimCaseApplyType)
    {
        return toAjax(claimCaseApplyTypeService.updateClaimCaseApplyType(claimCaseApplyType));
    }

    /**
     * 删除案件申请类别
     */
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "案件申请类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applyIds}")
    public AjaxResult remove(@PathVariable Long[] applyIds)
    {
        return toAjax(claimCaseApplyTypeService.deleteClaimCaseApplyTypeByIds(applyIds));
    }
}
