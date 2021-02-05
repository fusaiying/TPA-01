package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseSupplierContractBak;
import com.paic.ehis.base.service.IBaseSupplierContractBakService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * base_supplier_contract_bak（供应商合约）Controller
 * 
 * @author sino
 * @date 2021-01-08
 */
@RestController
@RequestMapping("/bak")
public class BaseSupplierContractBakController extends BaseController
{
    @Autowired
    private IBaseSupplierContractBakService baseSupplierContractBakService;

    /**
     * 查询base_supplier_contract_bak（供应商合约）列表
     */
    //@PreAuthorize("@ss.hasPermi('system:bak:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseSupplierContractBak baseSupplierContractBak)
    {
        startPage(baseSupplierContractBak);
        List<BaseSupplierContractBak> list = baseSupplierContractBakService.selectBaseSupplierContractBakList(baseSupplierContractBak);
        return getDataTable(list);
    }

    /**
     * 导出base_supplier_contract_bak（供应商合约）列表
     */
    //@PreAuthorize("@ss.hasPermi('system:bak:export')")
    @Log(title = "base_supplier_contract_bak（供应商合约）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseSupplierContractBak baseSupplierContractBak) throws IOException
    {
        List<BaseSupplierContractBak> list = baseSupplierContractBakService.selectBaseSupplierContractBakList(baseSupplierContractBak);
        ExcelUtil<BaseSupplierContractBak> util = new ExcelUtil<BaseSupplierContractBak>(BaseSupplierContractBak.class);
        util.exportExcel(response, list, "bak");
    }

    /**
     * 获取base_supplier_contract_bak（供应商合约）详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:bak:query')")
    @GetMapping(value = "/{serialNo}")
    public AjaxResult getInfo(@PathVariable("serialNo") String serialNo)
    {
        return AjaxResult.success(baseSupplierContractBakService.selectBaseSupplierContractBakById(serialNo));
    }


    /**若服务商下已签订合约，合约列表中服务商对应的合约编码、合约名称、合约起止日期均有值，
     *且当服务商下存在多条合约信息时仅显示该服务商下合约终止日期最晚的一条合约信息（即服务商下创建时间最晚的一条合约信息）
     **/

    //@PreAuthorize("@ss.hasPermi('system:contract:last')")
    @GetMapping(value = "/last/{providercode}")
    public  AjaxResult lastInfo(@PathVariable("providercode") String providercode)
    {
        return AjaxResult.success(baseSupplierContractBakService.selectBaseSupplierBakLast(providercode));
    }

    //服务商合约管理主查询页面需默认显示截止当前时间合约签约时间在三个月内且合约状态为“有效”的数据
    //@PreAuthorize("@ss.hasPermi('system:contract:month')")
    @PostMapping("/month")
    public TableDataInfo lista(@RequestBody BaseSupplierContractBak baseSupplierContractBak) throws Exception
    {
        startPage(baseSupplierContractBak);
        List<BaseSupplierContractBak> month = baseSupplierContractBakService.selectBaseSupplierBakMonth(baseSupplierContractBak);
        return getDataTable(month);
    }

    //根据服务机构id获取合约信息
    //@PreAuthorize("@ss.hasPermi('system:contract:code')")
    @GetMapping(value = "/code/{providercode}")
    public AjaxResult providerCodeinfo(@PathVariable("providercode") String providercode)
    {
        return AjaxResult.success(baseSupplierContractBakService.selectBaseproviderBakCode(providercode));
    }


    /**
     * 新增base_supplier_contract_bak（供应商合约）
     */
    //@PreAuthorize("@ss.hasPermi('system:bak:add')")
    @Log(title = "base_supplier_contract_bak（供应商合约）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseSupplierContractBak baseSupplierContractBak)
    {
        return AjaxResult.success(baseSupplierContractBakService.insertBaseSupplierContractBak(baseSupplierContractBak));
    }

    /**
     * 修改base_supplier_contract_bak（供应商合约）
     */
    //@PreAuthorize("@ss.hasPermi('system:bak:edit')")
    @Log(title = "base_supplier_contract_bak（供应商合约）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSupplierContractBak baseSupplierContractBak)
    {
        return AjaxResult.success(baseSupplierContractBakService.updateBaseSupplierContractBak(baseSupplierContractBak));
    }

    /**
     *修改服务机构历史合约信息接口
     **/
    //@PreAuthorize("@ss.hasPermi('system:bak:history')")
    @Log(title = "base_supplier_contract_bak（供应商合约）", businessType = BusinessType.UPDATE)
    @PutMapping("/history")
    public AjaxResult updateHistory(@RequestBody BaseSupplierContractBak baseSupplierContractBak)
    {
        return toAjax(baseSupplierContractBakService.updateHistory(baseSupplierContractBak));
    }


    /**
     * 删除base_supplier_contract_bak（供应商合约）
     */
    //@PreAuthorize("@ss.hasPermi('system:bak:remove')")
    @Log(title = "base_supplier_contract_bak（供应商合约）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serialNos}")
    public AjaxResult remove(@PathVariable String[] serialNos)
    {
        return toAjax(baseSupplierContractBakService.deleteBaseSupplierContractBakByIds(serialNos));
    }
}
