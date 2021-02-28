package com.paic.ehis.cs.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.CodeDict;
import com.paic.ehis.cs.domain.dto.CodeDictDTO;
import com.paic.ehis.cs.domain.vo.CodeDictVo;
import com.paic.ehis.cs.service.ICodeDictService;
import com.paic.ehis.cs.utils.CodeTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 业务码 Controller
 * 
 * @author sino
 * @date 2021-02-27
 */
@RestController
@RequestMapping("/dict/internal")
public class CodeDictController extends BaseController
{
    @Autowired
    private ICodeDictService codeDictService;

    /**
     * 查询业务码 列表
     */
    @GetMapping("/listWithParent")
    public TableDataInfo listWithParent(CodeDictDTO codeDictDTO)
    {
        startPage();
        codeDictDTO.setInsuranceSourceType(CodeTypeEnum.CS_INSURANCE_SOURCE.getCodeType());
        codeDictDTO.setComplaintBusinessType(CodeTypeEnum.CS_COMPLAINT_BUSINESS_ITEM.getCodeType());
        List<CodeDictVo> list = codeDictService.selectCodeDictVoList(codeDictDTO);
        return getDataTable(list);
    }

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
    public void export(HttpServletResponse response, CodeDictDTO codeDictDTO) throws IOException
    {
        List<CodeDictVo> list = codeDictService.selectCodeEnumVo(codeDictDTO);
        ExcelUtil<CodeDictVo> util = new ExcelUtil<CodeDictVo>(CodeDictVo.class);
        util.exportExcel(response, list, "投诉业务类别");
    }


    @GetMapping("/updateCodeEnumVo")
    public AjaxResult updateCodeEnumVo(List<CodeDictVo> list){
        return AjaxResult.success(codeDictService.updateCodeDictVo(list));
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
