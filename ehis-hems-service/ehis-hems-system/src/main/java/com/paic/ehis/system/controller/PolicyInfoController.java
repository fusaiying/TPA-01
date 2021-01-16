package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.PolicyInfo;
import com.paic.ehis.system.domain.dto.PolicyDTO;
import com.paic.ehis.system.domain.vo.PolicyVo;
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
import com.paic.ehis.system.service.IPolicyInfoService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 保单信息Controller
 * 
 * @author sino
 * @date 2021-01-09
 */
@RestController
@RequestMapping("/info")
public class PolicyInfoController extends BaseController
{
    @Autowired
    private IPolicyInfoService policyInfoService;

    /**
     * 查询保单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(PolicyInfo policyInfo)
    {
        startPage();
        List<PolicyInfo> list = policyInfoService.selectPolicyInfoList(policyInfo);
        return getDataTable(list);
    }

    /**
     * 导出保单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "保单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PolicyInfo policyInfo) throws IOException
    {
        List<PolicyInfo> list = policyInfoService.selectPolicyInfoList(policyInfo);
        ExcelUtil<PolicyInfo> util = new ExcelUtil<PolicyInfo>(PolicyInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取保单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{policyNo}")
    public AjaxResult getInfo(@PathVariable("policyNo") String policyNo)
    {
        return AjaxResult.success(policyInfoService.selectPolicyInfoById(policyNo));
    }

    /**
     * 新增保单信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "保单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PolicyInfo policyInfo)
    {
        return toAjax(policyInfoService.insertPolicyInfo(policyInfo));
    }

    /**
     * 修改保单信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "保单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PolicyInfo policyInfo)
    {
        return toAjax(policyInfoService.updatePolicyInfo(policyInfo));
    }

    /**
     * 删除保单信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "保单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{policyNos}")
    public AjaxResult remove(@PathVariable String[] policyNos)
    {
        return toAjax(policyInfoService.deletePolicyInfoByIds(policyNos));
    }
    /**
     * 被保人保单信息页面
     */

    @PreAuthorize("@ss.hasPermi('system:insured:list')")
    @GetMapping("/selectInsured")
    public AjaxResult  selectInsuredList(PolicyDTO policyDTO)
    {
        startPage();
        //PolicyVo policyList = policyInfoService.selectInsuredList(policyDTO);
        List<PolicyVo> policyList=policyInfoService.selectInsuredList(policyDTO);
        return AjaxResult.success(policyList);
    }
}
