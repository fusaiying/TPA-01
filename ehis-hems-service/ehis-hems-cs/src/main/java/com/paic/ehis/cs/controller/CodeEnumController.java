package com.paic.ehis.cs.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.cs.domain.CodeEnum;
import com.paic.ehis.cs.domain.dto.CodeEnumDTO;
import com.paic.ehis.cs.domain.vo.CodeEnumVo;
import com.paic.ehis.cs.service.ICodeEnumService;
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
 * @date 2021-02-23
 */
@RestController
@RequestMapping("/enum")
public class CodeEnumController extends BaseController
{
    @Autowired
    private ICodeEnumService codeEnumService;

    /**
     * 查询业务码 列表
     */
    @PreAuthorize("@ss.hasPermi('system:enum:list')")
    @GetMapping("/list")
    public TableDataInfo list(CodeEnum codeEnum)
    {
        startPage();
        List<CodeEnum> list = codeEnumService.selectCodeEnumList(codeEnum);
        return getDataTable(list);
    }

    /**
     * 导出业务码 列表
     */
    @PreAuthorize("@ss.hasPermi('system:enum:export')")
    @Log(title = "业务码 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CodeEnum codeEnum) throws IOException
    {
        List<CodeEnum> list = codeEnumService.selectCodeEnumList(codeEnum);
        ExcelUtil<CodeEnum> util = new ExcelUtil<CodeEnum>(CodeEnum.class);
        util.exportExcel(response, list, "enum");
    }


    /**
     * 导出投诉分类 列表
     */
    @PreAuthorize("@ss.hasPermi('system:enum:export')")
    @Log(title = "业务码 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CodeEnumDTO codeEnumDTO) throws IOException
    {
        List<CodeEnumVo> list = codeEnumService.selectCodeEnumVo(codeEnumDTO);
        ExcelUtil<CodeEnumVo> util = new ExcelUtil<CodeEnumVo>(CodeEnumVo.class);
        util.exportExcel(response, list, "enum");
    }

    public List<CodeEnumVo> updateCodeEnumVo(List<CodeEnumVo> list){
        return codeEnumService.updateCodeEnumVo(list);
    }



    /**
     * 获取业务码 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:enum:query')")
    @GetMapping(value = "/{codeId}")
    public AjaxResult getInfo(@PathVariable("codeId") Long codeId)
    {
        return AjaxResult.success(codeEnumService.selectCodeEnumById(codeId));
    }

    /**
     * 新增业务码 
     */
    @PreAuthorize("@ss.hasPermi('system:enum:add')")
    @Log(title = "业务码 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CodeEnum codeEnum)
    {
        return toAjax(codeEnumService.insertCodeEnum(codeEnum));
    }

    /**
     * 修改业务码 
     */
    @PreAuthorize("@ss.hasPermi('system:enum:edit')")
    @Log(title = "业务码 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CodeEnum codeEnum)
    {
        return toAjax(codeEnumService.updateCodeEnum(codeEnum));
    }

    /**
     * 删除业务码 
     */
    @PreAuthorize("@ss.hasPermi('system:enum:remove')")
    @Log(title = "业务码 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{codeIds}")
    public AjaxResult remove(@PathVariable Long[] codeIds)
    {
        return toAjax(codeEnumService.deleteCodeEnumByIds(codeIds));
    }
}
