package com.paic.ehis.policy.controller;

import com.paic.ehis.policy.domain.PolicyInsured;
import com.paic.ehis.policy.service.IPolicyInsuredService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 被保人信息Controller
 *
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/policyInsured")
public class PolicyInsuredController extends BaseController
{
    @Autowired
    private IPolicyInsuredService policyInsuredService;

    /**
     * 查询被保人信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:insured:list')")
    @GetMapping("/list")
    public TableDataInfo list(PolicyInsured policyInsured)
    {
        startPage();
        List<PolicyInsured> list = policyInsuredService.selectPolicyInsuredList(policyInsured);
        return getDataTable(list);
    }

    /**
     * 导出被保人信息列表
     */
    //@PreAuthorize("@ss.hasPermi('system:insured:export')")
    @Log(title = "被保人信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PolicyInsured policyInsured) throws IOException
    {
        List<PolicyInsured> list = policyInsuredService.selectPolicyInsuredList(policyInsured);
        ExcelUtil<PolicyInsured> util = new ExcelUtil<PolicyInsured>(PolicyInsured.class);
        util.exportExcel(response, list, "insured");
    }

    /**
     * 获取被保人信息详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:insured:query')")
    @GetMapping(value = "/{insuredNo}")
    public AjaxResult getInfo(@PathVariable("insuredNo") String insuredNo)
    {
        return AjaxResult.success(policyInsuredService.selectPolicyInsuredById(insuredNo));
    }

    /**
     * 新增被保人信息
     */
    //@PreAuthorize("@ss.hasPermi('system:insured:add')")
    @Log(title = "被保人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PolicyInsured policyInsured)
    {
        return toAjax(policyInsuredService.insertPolicyInsured(policyInsured));
    }

    /**
     * 修改被保人信息
     */
    //@PreAuthorize("@ss.hasPermi('system:insured:edit')")
    @Log(title = "被保人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PolicyInsured policyInsured)
    {
        return toAjax(policyInsuredService.updatePolicyInsured(policyInsured));
    }

    /**
     * 删除被保人信息
     */
    //@PreAuthorize("@ss.hasPermi('system:insured:remove')")
    @Log(title = "被保人信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{insuredNos}")
    public AjaxResult remove(@PathVariable String[] insuredNos)
    {
        return toAjax(policyInsuredService.deletePolicyInsuredByIds(insuredNos));
    }


    /**
     * 根据5要素查询被保人信息 ，如果没查询到则新增一条数据
     * modify: hjw
     * time:2021-3-8
     */
    @GetMapping(value = "/getInfoByElement")
    public AjaxResult getInfoByElement(PolicyInsured bean)
    {
        return AjaxResult.success(policyInsuredService.getInfoByElement(bean));
    }

}
