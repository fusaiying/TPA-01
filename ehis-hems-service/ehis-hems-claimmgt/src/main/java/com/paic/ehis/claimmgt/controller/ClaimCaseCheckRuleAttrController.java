package com.paic.ehis.claimmgt.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.claimmgt.domain.ClaimCaseCheckRuleAttr;
import com.paic.ehis.claimmgt.service.IClaimCaseCheckRuleAttrService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 案件抽检规则属性Controller
 * 
 * @author sino
 * @date 2021-01-30
 */
@RestController
@RequestMapping("/attr")
public class ClaimCaseCheckRuleAttrController extends BaseController
{
    @Autowired
    private IClaimCaseCheckRuleAttrService claimCaseCheckRuleAttrService;

    /**
     * 查询案件抽检规则属性列表
     */
    //@PreAuthorize("@ss.hasPermi('system:attr:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr)
    {
        startPage();
        List<ClaimCaseCheckRuleAttr> list = claimCaseCheckRuleAttrService.selectClaimCaseCheckRuleAttrList(claimCaseCheckRuleAttr);
        return getDataTable(list);
    }

    /**
     * 导出案件抽检规则属性列表
     */
    //@PreAuthorize("@ss.hasPermi('system:attr:export')")
    @Log(title = "案件抽检规则属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr) throws IOException
    {
        List<ClaimCaseCheckRuleAttr> list = claimCaseCheckRuleAttrService.selectClaimCaseCheckRuleAttrList(claimCaseCheckRuleAttr);
        ExcelUtil<ClaimCaseCheckRuleAttr> util = new ExcelUtil<ClaimCaseCheckRuleAttr>(ClaimCaseCheckRuleAttr.class);
        util.exportExcel(response, list, "attr");
    }

    /**
     * 获取案件抽检规则属性详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:attr:query')")
    @GetMapping(value = "/{attrId}")
    public AjaxResult getInfo(@PathVariable("attrId") Long attrId)
    {
        return AjaxResult.success(claimCaseCheckRuleAttrService.selectClaimCaseCheckRuleAttrById(attrId));
    }

    /**
     * 新增案件抽检规则属性
     */
    //@PreAuthorize("@ss.hasPermi('system:attr:add')")
    @Log(title = "案件抽检规则属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr)
    {
        return toAjax(claimCaseCheckRuleAttrService.insertClaimCaseCheckRuleAttr(claimCaseCheckRuleAttr));
    }

    /**
     * 修改案件抽检规则属性
     */
    //@PreAuthorize("@ss.hasPermi('system:attr:edit')")
    @Log(title = "案件抽检规则属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseCheckRuleAttr claimCaseCheckRuleAttr)
    {
        return toAjax(claimCaseCheckRuleAttrService.updateClaimCaseCheckRuleAttr(claimCaseCheckRuleAttr));
    }

    /**
     * 删除案件抽检规则属性
     */
    //@PreAuthorize("@ss.hasPermi('system:attr:remove')")
    @Log(title = "案件抽检规则属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrIds}")
    public AjaxResult remove(@PathVariable Long[] attrIds)
    {
        return toAjax(claimCaseCheckRuleAttrService.deleteClaimCaseCheckRuleAttrByIds(attrIds));
    }
}
