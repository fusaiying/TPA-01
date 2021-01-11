package com.paic.ehis.product.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.paic.ehis.product.domain.productPackageExamine;
import com.paic.ehis.product.service.IproductPackageExamineService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 产品包审核Controller
 * 
 * @author sino
 * @date 2020-09-22
 */
@RestController
@RequestMapping("/examine")
public class productPackageExamineController extends BaseController
{
    @Autowired
    private IproductPackageExamineService productPackageExamineService;

    /**
     * 查询产品包审核列表
     */
    @PreAuthorize("@ss.hasPermi('examine:examine:list')")
    @GetMapping("/list")
    public TableDataInfo list(productPackageExamine productPackageExamine)
    {
        startPage();
        List<productPackageExamine> list = productPackageExamineService.selectproductPackageExamineList(productPackageExamine);
        return getDataTable(list);
    }

    /**
     * 导出产品包审核列表
     */
    @PreAuthorize("@ss.hasPermi('examine:examine:export')")
    @Log(title = "产品包审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, productPackageExamine productPackageExamine) throws IOException
    {
        List<productPackageExamine> list = productPackageExamineService.selectproductPackageExamineList(productPackageExamine);
        ExcelUtil<productPackageExamine> util = new ExcelUtil<productPackageExamine>(productPackageExamine.class);
        util.exportExcel(response, list, "examine");
    }

    /**
     * 获取产品包审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('examine:examine:query')")
    @GetMapping(value = "/{productpackageno}")
    public AjaxResult getInfo(@PathVariable("productpackageno") String productpackageno)
    {
        return AjaxResult.success(productPackageExamineService.selectproductPackageExamineById(productpackageno));
    }

    /**
     * 新增产品包审核
     */
    @PreAuthorize("@ss.hasPermi('examine:examine:add')")
    @Log(title = "产品包审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody productPackageExamine productPackageExamine)
    {
        return toAjax(productPackageExamineService.insertproductPackageExamine(productPackageExamine));
    }

    /**
     * 修改产品包审核
     */
    @PreAuthorize("@ss.hasPermi('examine:examine:edit')")
    @Log(title = "产品包审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody productPackageExamine productPackageExamine)
    {
        return toAjax(productPackageExamineService.updateproductPackageExamine(productPackageExamine));
    }

    /**
     * 删除产品包审核
     */
    @PreAuthorize("@ss.hasPermi('examine:examine:remove')")
    @Log(title = "产品包审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productpackagenos}")
    public AjaxResult remove(@PathVariable String[] productpackagenos)
    {
        return toAjax(productPackageExamineService.deleteproductPackageExamineByIds(productpackagenos));
    }
}
