package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.EditInfo;
import com.paic.ehis.cs.service.IEditInfoService;
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
 * 修改信息 Controller
 * 
 * @author sino
 * @date 2021-02-05
 */
@RestController
@RequestMapping("/info")
public class EditInfoController extends BaseController
{
    @Autowired
    private IEditInfoService editInfoService;

    /**
     * 查询修改信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(EditInfo editInfo)
    {
        startPage();
        List<EditInfo> list = editInfoService.selectEditInfoList(editInfo);
        return getDataTable(list);
    }

    /**
     * 导出修改信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "修改信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EditInfo editInfo) throws IOException
    {
        List<EditInfo> list = editInfoService.selectEditInfoList(editInfo);
        ExcelUtil<EditInfo> util = new ExcelUtil<EditInfo>(EditInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取修改信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{editId}")
    public AjaxResult getInfo(@PathVariable("editId") Long editId)
    {
        return AjaxResult.success(editInfoService.selectEditInfoById(editId));
    }

    /**
     * 新增修改信息 
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "修改信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EditInfo editInfo)
    {
        return toAjax(editInfoService.insertEditInfo(editInfo));
    }

    /**
     * 修改修改信息 
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "修改信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EditInfo editInfo)
    {
        return toAjax(editInfoService.updateEditInfo(editInfo));
    }

    /**
     * 删除修改信息 
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "修改信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{editIds}")
    public AjaxResult remove(@PathVariable Long[] editIds)
    {
        return toAjax(editInfoService.deleteEditInfoByIds(editIds));
    }
}
