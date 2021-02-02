package com.paic.ehis.product.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

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
import com.paic.ehis.product.domain.Hpproductpackageinfo;
import com.paic.ehis.product.service.IHpproductpackageinfoService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 产品包信息Controller
 * 
 * @author zkrchenzl
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/productpacketinfo")
public class HpproductpackageinfoController extends BaseController
{
    @Autowired
    private IHpproductpackageinfoService hpproductpackageinfoService;

    /**
     * 查询产品包信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Hpproductpackageinfo hpproductpackageinfo)
    {
        startPage();
        List<Hpproductpackageinfo> list = hpproductpackageinfoService.selectHpproductpackageinfoList(hpproductpackageinfo);
        return getDataTable(list);
    }

    /**
     * 导出产品包信息列表
     */
    @Log(title = "产品包信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Hpproductpackageinfo hpproductpackageinfo) throws IOException
    {
        List<Hpproductpackageinfo> list = hpproductpackageinfoService.selectHpproductpackageinfoList(hpproductpackageinfo);
        ExcelUtil<Hpproductpackageinfo> util = new ExcelUtil<Hpproductpackageinfo>(Hpproductpackageinfo.class);
        util.exportExcel(response, list, "productpacketinfo");
    }

    /**
     * 获取产品包信息详细信息
     */
    @GetMapping(value = "/{productpackageno}")
    public AjaxResult getInfo(@PathVariable("productpackageno") String productpackageno)
    {
        return AjaxResult.success(hpproductpackageinfoService.selectHpproductpackageinfoById(productpackageno));
    }

    /**
     * 新增产品包信息
     */
    @Log(title = "产品包信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Hpproductpackageinfo hpproductpackageinfo)
    {
        return toAjax(hpproductpackageinfoService.insertHpproductpackageinfo(hpproductpackageinfo));
    }

    /**
     * 修改产品包信息
     */
    @Log(title = "产品包信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Hpproductpackageinfo hpproductpackageinfo)
    {
        return toAjax(hpproductpackageinfoService.updateHpproductpackageinfo(hpproductpackageinfo));
    }

    /**
     * 删除产品包信息
     */
    @Log(title = "产品包信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productpackagenos}")
    public AjaxResult remove(@PathVariable String[] productpackagenos)
    {
        return toAjax(hpproductpackageinfoService.deleteHpproductpackageinfoByIds(productpackagenos));
    }
}
