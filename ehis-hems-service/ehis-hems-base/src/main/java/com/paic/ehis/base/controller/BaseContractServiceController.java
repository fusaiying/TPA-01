package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseContractService;
import com.paic.ehis.base.service.IBaseContractServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * base_contract_service（合约服务项目）Controller
 * 
 * @author sino
 * @date 2020-12-31
 */
@RestController
@RequestMapping("/service")
public class BaseContractServiceController extends BaseController
{
    @Autowired
    private IBaseContractServiceService baseContractServiceService;

    /**
     * 查询base_contract_service（合约服务项目）列表
     */
    //@PreAuthorize("@ss.hasPermi('system:service:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseContractService baseContractService)
    {
        startPage();
        List<BaseContractService> list = baseContractServiceService.selectBaseContractServiceList(baseContractService);
        return getDataTable(list);
    }

    /**
     * 导出base_contract_service（合约服务项目）列表
     */
    //@PreAuthorize("@ss.hasPermi('system:service:export')")
    @Log(title = "base_contract_service（合约服务项目）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseContractService baseContractService) throws IOException
    {
        List<BaseContractService> list = baseContractServiceService.selectBaseContractServiceList(baseContractService);
        ExcelUtil<BaseContractService> util = new ExcelUtil<BaseContractService>(BaseContractService.class);
        util.exportExcel(response, list, "service");
    }

    /**
     * 获取base_contract_service（合约服务项目）详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:service:query')")
    @GetMapping(value = "/{contractNo}")
    public AjaxResult getInfo(@PathVariable("contractNo") String contractNo)
    {
        return AjaxResult.success(baseContractServiceService.selectBaseContractServiceById(contractNo));
    }

    /**
     * 新增base_contract_service（合约服务项目）
     */
    //@PreAuthorize("@ss.hasPermi('system:service:add')")
    @Log(title = "base_contract_service（合约服务项目）", businessType = BusinessType.INSERT)
    @PostMapping("add")
    public AjaxResult add(@RequestBody BaseContractService baseContractService)
    {
        return AjaxResult.success(baseContractServiceService.insertBaseContractService(baseContractService));
    }

    //@PreAuthorize("@ss.hasPermi('system:service:addlist')")

    /**
     * 批量新增
     */
    @PostMapping("/addList")
    public AjaxResult addlist(@RequestBody List<BaseContractService> baseContractServiceList)
    {
        return toAjax(baseContractServiceService.insertForeach(baseContractServiceList));
    }

    /**
     * 修改base_contract_service（合约服务项目）
     */
    //@PreAuthorize("@ss.hasPermi('system:service:edit')")
    @Log(title = "base_contract_service（合约服务项目）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseContractService baseContractService)
    {
        return toAjax(baseContractServiceService.updateBaseContractService(baseContractService));
    }

    /**
     * 删除base_contract_service（合约服务项目）
     */
    //@PreAuthorize("@ss.hasPermi('system:service:remove')")
    @Log(title = "base_contract_service（合约服务项目）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contractNos}")
    public AjaxResult remove(@PathVariable String[] contractNos)
    {
        return toAjax(baseContractServiceService.deleteBaseContractServiceByIds(contractNos));
    }

    /**
     * 根据合约编码删除base_contract_service
     */
    //@PreAuthorize("@ss.hasPermi('system:service:serialNo')")
    @DeleteMapping("/once/{serialNo}")
    public AjaxResult removeById(@PathVariable String serialNo)
    {
        return toAjax(baseContractServiceService.deleteBaseContractServiceById(serialNo));
    }
}
