package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseContractAnnex;
import com.paic.ehis.base.service.IBaseContractAnnexService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * base_contract_annex（合约附件）Controller
 * 
 * @author sino
 * @date 2020-12-31
 */
@RestController
@RequestMapping("/annex")
public class BaseContractAnnexController extends BaseController
{
    @Autowired
    private IBaseContractAnnexService baseContractAnnexService;

    /**
     * 查询base_contract_annex（合约附件）列表
     */
    //@PreAuthorize("@ss.hasPermi('system:annex:list')")
    @GetMapping("/list")
    public TableDataInfo list( BaseContractAnnex baseContractAnnex)
    {
        startPage();
        List<BaseContractAnnex> list = baseContractAnnexService.selectBaseContractAnnexList(baseContractAnnex);
        return getDataTable(list);
    }

    /**
     * 导出base_contract_annex（合约附件）列表
     */
    //@PreAuthorize("@ss.hasPermi('system:annex:export')")
    @Log(title = "base_contract_annex（合约附件）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseContractAnnex baseContractAnnex) throws IOException
    {
        List<BaseContractAnnex> list = baseContractAnnexService.selectBaseContractAnnexList(baseContractAnnex);
        ExcelUtil<BaseContractAnnex> util = new ExcelUtil<BaseContractAnnex>(BaseContractAnnex.class);
        util.exportExcel(response, list, "annex");
    }

    /**
     * 获取base_contract_annex（合约附件）详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:annex:query')")
    @GetMapping(value = "/{contractNo}")
    public AjaxResult getInfo(@PathVariable("contractNo") String contractNo)
    {
        return AjaxResult.success(baseContractAnnexService.selectBaseContractAnnexById(contractNo));
    }

    /**
     * 新增base_contract_annex（合约附件）
     */
    //@PreAuthorize("@ss.hasPermi('system:annex:add')")
    @Log(title = "base_contract_annex（合约附件）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseContractAnnex baseContractAnnex)
    {
        return toAjax(baseContractAnnexService.insertBaseContractAnnex(baseContractAnnex));
    }

    /**
     * 修改base_contract_annex（合约附件）
     */
    //@PreAuthorize("@ss.hasPermi('system:annex:edit')")
    @Log(title = "base_contract_annex（合约附件）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseContractAnnex baseContractAnnex)
    {
        return toAjax(baseContractAnnexService.updateBaseContractAnnex(baseContractAnnex));
    }

    /**
     * 删除base_contract_annex（合约附件）
     */
    //@PreAuthorize("@ss.hasPermi('system:annex:remove')")
    @Log(title = "base_contract_annex（合约附件）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contractNos}")
    public AjaxResult remove(@PathVariable String[] contractNos)
    {
        return toAjax(baseContractAnnexService.deleteBaseContractAnnexByIds(contractNos));
    }
}
