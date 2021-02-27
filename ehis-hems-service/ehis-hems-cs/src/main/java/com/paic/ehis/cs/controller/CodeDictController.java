package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.dto.CodeEnumDTO;
import com.paic.ehis.cs.domain.vo.CodeEnumVo;
import com.paic.ehis.cs.service.ICodeDictService;
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
 * 业务码 Controller
 * 
 * @author sino
 * @date 2021-02-27
 */
@RestController
@RequestMapping("/dict")
public class CodeDictController extends BaseController
{
    @Autowired
    private ICodeDictService codeDictService;

    /**
     * 查询业务码 列表
     */
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(CodeDict codeDict)
    {
        startPage();
        List<CodeDict> list = codeDictService.selectCodeDictList(codeDict);
        return getDataTable(list);
    }

    /**
     * 导出业务码 列表
     */
    @PreAuthorize("@ss.hasPermi('system:dict:export')")
    @Log(title = "业务码 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CodeDict codeDict) throws IOException
    {
        List<CodeDict> list = codeDictService.selectCodeDictList(codeDict);
        ExcelUtil<CodeDict> util = new ExcelUtil<CodeDict>(CodeDict.class);
        util.exportExcel(response, list, "dict");
    }


    /**
     * 导出投诉分类 列表
     */
    @PreAuthorize("@ss.hasPermi('system:enum:export')")
    @Log(title = "业务码 ", businessType = BusinessType.EXPORT)
    @PostMapping("/exportNew")
    public void export(HttpServletResponse response, CodeEnumDTO codeEnumDTO) throws IOException
    {
        List<CodeEnumVo> list = codeDictService.selectCodeEnumVo(codeEnumDTO);
        ExcelUtil<CodeEnumVo> util = new ExcelUtil<CodeEnumVo>(CodeEnumVo.class);
        util.exportExcel(response, list, "enum");
    }


    @GetMapping("/updateCodeEnumVo")
    public AjaxResult updateCodeEnumVo(List<CodeEnumVo> list){
        return AjaxResult.success(codeDictService.updateCodeEnumVo(list));
    }


    /**
     * 获取业务码 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{codeType}")
    public AjaxResult getInfo(@PathVariable("codeType") String codeType)
    {
        return AjaxResult.success(codeDictService.selectCodeDictById(codeType));
    }

    /**
     * 新增业务码 
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "业务码 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CodeDict codeDict)
    {
        return toAjax(codeDictService.insertCodeDict(codeDict));
    }

    /**
     * 修改业务码 
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "业务码 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CodeDict codeDict)
    {
        return toAjax(codeDictService.updateCodeDict(codeDict));
    }

    /**
     * 删除业务码 
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "业务码 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{codeTypes}")
    public AjaxResult remove(@PathVariable String[] codeTypes)
    {
        return toAjax(codeDictService.deleteCodeDictByIds(codeTypes));
    }
}
