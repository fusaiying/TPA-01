package com.paic.ehis.system.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.system.domain.ClaimRuleRelation;
import com.paic.ehis.system.service.IClaimRuleRelationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 规则对象关联 Controller
 * 
 * @author sino
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/relation")
public class ClaimRuleRelationController extends BaseController
{
    @Autowired
    private IClaimRuleRelationService claimRuleRelationService;

    /**
     * 查询规则对象关联 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimRuleRelation claimRuleRelation)
    {
        startPage();
        List<ClaimRuleRelation> list = claimRuleRelationService.selectClaimRuleRelationList(claimRuleRelation);
        return getDataTable(list);
    }

    /**
     * 导出规则对象关联 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:export')")
    @Log(title = "规则对象关联 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimRuleRelation claimRuleRelation) throws IOException
    {
        List<ClaimRuleRelation> list = claimRuleRelationService.selectClaimRuleRelationList(claimRuleRelation);
        ExcelUtil<ClaimRuleRelation> util = new ExcelUtil<ClaimRuleRelation>(ClaimRuleRelation.class);
        util.exportExcel(response, list, "relation");
    }

    /**
     * 获取规则对象关联 详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:query')")
    @GetMapping(value = "/{relationNo}")
    public AjaxResult getInfo(@PathVariable("relationNo") String relationNo)
    {
        return AjaxResult.success(claimRuleRelationService.selectClaimRuleRelationById(relationNo));
    }

    /**
     * 新增规则对象关联 
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:add')")
    @Log(title = "规则对象关联 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimRuleRelation claimRuleRelation)
    {
        return toAjax(claimRuleRelationService.insertClaimRuleRelation(claimRuleRelation));
    }

    /**
     * 修改规则对象关联 
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:edit')")
    @Log(title = "规则对象关联 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimRuleRelation claimRuleRelation)
    {
        return toAjax(claimRuleRelationService.updateClaimRuleRelation(claimRuleRelation));
    }

    /**
     * 删除规则对象关联 
     */
    //@PreAuthorize("@ss.hasPermi('system:relation:remove')")
    @Log(title = "规则对象关联 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relationNos}")
    public AjaxResult remove(@PathVariable String[] relationNos)
    {
        return toAjax(claimRuleRelationService.deleteClaimRuleRelationByIds(relationNos));
    }
}
