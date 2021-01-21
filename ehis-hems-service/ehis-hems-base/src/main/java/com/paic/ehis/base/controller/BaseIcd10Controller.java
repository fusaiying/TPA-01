package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseIcd10;
import com.paic.ehis.base.service.IBaseIcd10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ICD10数据 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/icd10")
public class BaseIcd10Controller extends BaseController
{
    @Autowired
    private IBaseIcd10Service baseIcd10Service;

    /**
     * 查询ICD10数据 列表
     */
    @PreAuthorize("@ss.hasPermi('system:icd10:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseIcd10 baseIcd10)
    {
        startPage();
        List<BaseIcd10> list = baseIcd10Service.selectBaseIcd10List(baseIcd10);
        return getDataTable(list);
    }

    /**
     * 导出ICD10数据 列表
     */
    @PreAuthorize("@ss.hasPermi('system:icd10:export')")
    @Log(title = "ICD10数据 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseIcd10 baseIcd10) throws IOException
    {
        List<BaseIcd10> list = baseIcd10Service.selectBaseIcd10List(baseIcd10);
        ExcelUtil<BaseIcd10> util = new ExcelUtil<BaseIcd10>(BaseIcd10.class);
        util.exportExcel(response, list, "icd10");
    }

    /**
     * 获取ICD10数据 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:icd10:query')")
    @GetMapping(value = "/{icdCode}")
    public AjaxResult getInfo(@PathVariable("icdCode") String icdCode)
    {
        return AjaxResult.success(baseIcd10Service.selectBaseIcd10ById(icdCode));
    }

    /**
     * 新增ICD10数据 
     */
    @PreAuthorize("@ss.hasPermi('system:icd10:add')")
    @Log(title = "ICD10数据 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseIcd10 baseIcd10)
    {
        String icdcode = baseIcd10.getIcdcode();
        String icdmname = baseIcd10.getIcdmname();
        BaseIcd10 baseIcd101 = baseIcd10Service.selectBaseIcd10ById(icdcode);
        BaseIcd10 baseIcd102 = baseIcd10Service.selectBaseIcd10ByName(icdmname);
        if (StringUtils.isNotNull(baseIcd101)) {
            return AjaxResult.error("新增ICD'" + icdcode + "'失败，该ICD10编码已存在!");
        }else if (StringUtils.isNotNull(baseIcd102)){
            return AjaxResult.error("新增ICD'" + icdmname + "'失败，该ICD10名称已存在!");
        }else {
            return toAjax(baseIcd10Service.insertBaseIcd10(baseIcd10));
        }
    }

    /**
     * 修改ICD10数据 
     */
    @PreAuthorize("@ss.hasPermi('system:icd10:edit')")
    @Log(title = "ICD10数据 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseIcd10 baseIcd10)
    {
        String icdmname = baseIcd10.getIcdmname();
        BaseIcd10 baseIcd102 = baseIcd10Service.selectBaseIcd10ByName(icdmname);
        if (StringUtils.isNotNull(baseIcd102)){
            return AjaxResult.error("修改ICD'" + icdmname + "'失败，该ICD10名称已存在!");
        }else {
            return toAjax(baseIcd10Service.updateBaseIcd10(baseIcd10));
        }
    }

    /**
     * 删除ICD10数据 
     */
    @PreAuthorize("@ss.hasPermi('system:icd10:remove')")
    @Log(title = "ICD10数据 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{icdCodes}")
    public AjaxResult remove(@PathVariable String[] icdCodes)
    {
        return toAjax(baseIcd10Service.deleteBaseIcd10ByIds(icdCodes));
    }

    /**
     * 单个删除
     */
    @PreAuthorize("@ss.hasPermi('system:icd10:edit')")
    @Log(title = "ICD10数据 ", businessType = BusinessType.UPDATE)
    @PostMapping("/removeOne")
    public AjaxResult removeOne(@RequestBody BaseIcd10 baseIcd10)
    {
        return toAjax(baseIcd10Service.updateBaseIcd10One(baseIcd10));
    }
}
