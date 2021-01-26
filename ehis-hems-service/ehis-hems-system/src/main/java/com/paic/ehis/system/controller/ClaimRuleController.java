package com.paic.ehis.system.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.utils.SecurityUtils;
import com.paic.ehis.system.domain.ClaimRule;
import com.paic.ehis.system.domain.ClaimRuleInfo;
import com.paic.ehis.system.service.IClaimRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 福利规则 Controller
 * 
 * @author sino
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/productRule")
public class ClaimRuleController extends BaseController
{
    @Autowired
    private IClaimRuleService claimRuleService;

    /**
     * 查询福利规则 列表
     */
    @PreAuthorize("@ss.hasPermi('system:rule:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimRule claimRule)
    {
        startPage();
        claimRule.setStatus("Y");
        return claimRuleService.selectClaimRuleInfoList(claimRule);
    }

    /**
     * 导出福利规则 列表
     */
    @PreAuthorize("@ss.hasPermi('system:rule:export')")
    @Log(title = "福利规则 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimRule claimRule) throws IOException
    {
        List<ClaimRule> list = claimRuleService.selectClaimRuleList(claimRule);
        ExcelUtil<ClaimRule> util = new ExcelUtil<ClaimRule>(ClaimRule.class);
        util.exportExcel(response, list, "rule");
    }

    /**
     * 获取福利规则 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rule:query')")
    @GetMapping(value = "/{ruleNo}")
    public AjaxResult getInfo(@PathVariable("ruleNo") String ruleNo)
    {
        return AjaxResult.success(claimRuleService.selectClaimRuleInfoById(ruleNo));
    }

    /**
     * 新增福利规则 
     */
    @PreAuthorize("@ss.hasPermi('system:rule:add')")
    @Log(title = "福利规则 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimRuleInfo claimRuleInfo)
    {
        claimRuleInfo.setCreateBy(SecurityUtils.getUsername());
        return toAjax(claimRuleService.insertClaimRuleInfo(claimRuleInfo));
    }

    /**
     * 修改福利规则 
     */
    @PreAuthorize("@ss.hasPermi('system:rule:edit')")
    @Log(title = "福利规则 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimRuleInfo claimRuleInfo)
    {
        claimRuleInfo.setCreateBy(SecurityUtils.getUsername());
        return toAjax(claimRuleService.updateClaimRuleInfo(claimRuleInfo));
    }

    /**
     * 删除福利规则 
     */
    @PreAuthorize("@ss.hasPermi('system:rule:update')")
    @Log(title = "福利规则 ", businessType = BusinessType.DELETE)
	@PostMapping("/ruleNos")
    public AjaxResult remove(@RequestBody String[] ruleNos)
    {
        return toAjax(claimRuleService.deleteClaimRuleInfoByIds(ruleNos));
    }
}
