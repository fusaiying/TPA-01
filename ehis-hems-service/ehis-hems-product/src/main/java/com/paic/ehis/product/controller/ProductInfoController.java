package com.paic.ehis.product.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.product.domain.*;
import com.paic.ehis.product.service.IBaseManualInfoService;
import com.paic.ehis.product.service.IProductCheckInfoService;
import com.paic.ehis.product.service.IProductServiceInfoService;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.domain.dto.BaseManualInfo;
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
import com.paic.ehis.product.service.IProductInfoService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.system.api.domain.BaseServiceInfo;


/**
 * base_product_info(服务产品)Controller
 *
 * @author sino
 * @date 2021-01-15
 */
@RestController
@RequestMapping("/info")
public class ProductInfoController extends BaseController
{
    @Autowired
    private IProductInfoService productInfoService;

    @Autowired
    private IProductServiceInfoService productServiceInfoService;

    @Autowired
    private IBaseManualInfoService baseManualInfoService;

    @Autowired
    private IProductCheckInfoService productCheckInfoService;

    @Autowired
    private GetProviderInfoService getProviderInfoService;
    /**
     * 查询base_product_info(服务产品)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductInfo productInfo) throws Exception
    {
        startPage();
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo) ;
        return getDataTable(list);
    }


    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/listNull")
    public TableDataInfo list() throws Exception
    {
        startPage();
        List<ProductInfo> list = productInfoService.selectProductInfoListNull() ;
        return getDataTable(list);
    }


    /**
     * 导出base_product_info(服务产品)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "base_product_info(服务产品)", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductInfo productInfo) throws IOException, Exception
    {
        List<ProductInfo> list = productInfoService.selectProductInfoList(productInfo);
        ExcelUtil<ProductInfo> util = new ExcelUtil<ProductInfo>(ProductInfo.class);
        util.exportExcel(response, list, "info");
    }

    /**
     * 获取base_product_info(服务产品)详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getProductInfo")
    public AjaxResult getProductInfo(@RequestBody  ProductInfo productInfo)
    {
        return AjaxResult.success(productInfoService.selectProductInfoById(productInfo));
    }

    /**
     * 新增base_product_info(服务产品)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "base_product_info(服务产品)", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductInfo productInfo)
    {
        return toAjax(productInfoService.insertProductInfo(productInfo));
    }

    /**
     * 修改base_product_info(服务产品)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "base_product_info(服务产品)", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductInfo productInfo)
    {
        return toAjax(productInfoService.updateProductInfo(productInfo));
    }

    /**
     * 删除base_product_info(服务产品)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "base_product_info(服务产品)", businessType = BusinessType.DELETE)
    @DeleteMapping("/{serialNos}")
    public AjaxResult remove(@PathVariable String[] serialNos)
    {
        return toAjax(productInfoService.deleteProductInfoByIds(serialNos));
    }

    /**
     * 删除base_product_info(服务产品)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:remove')")
    @PostMapping("/deleteProductInfo")
    public AjaxResult deleteProductInfo(@RequestBody String productCode)
    {
        return toAjax(productInfoService.deleteProductInfoById(productCode));
    }


    /**
     * 删除base_product_info(服务产品)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/getServiceInfo")
    public List<BaseServiceInfo> getServiceInfo()
    {
        return getProviderInfoService.getServiceInfo();
    }


    /**
     * 新增base_product_service_info(服务产品项目关联)
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "base_product_service_info(服务产品项目关联)", businessType = BusinessType.INSERT)
    @PostMapping("/addProductService")
    public AjaxResult addProductService(@RequestBody ProductServiceInfoVo productServiceInfoVo)
    {
        return toAjax(productServiceInfoService.insertProductServiceInfo(productServiceInfoVo));
    }

    /**
     * 产品定义保存按钮
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("/saveProductInfo")
    public AjaxResult saveProductInfo(@RequestBody ProductSaveInfoVo productSaveInfoVo)
    {
        return toAjax(productServiceInfoService.saveProductInfo(productSaveInfoVo));
    }


    /**
     * 保存产品信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("/insertProductInfo")
    public AjaxResult insertProductInfo(@RequestBody ProductInfo productInfo)
    {
        return AjaxResult.success(productInfoService.insertInfo(productInfo));
    }



    /**
     * 提交审核
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("/checkProductInfo")
    public AjaxResult checkProductInfo(@RequestBody ProductSaveInfoVo productSaveInfoVo)
    {
        return toAjax(productServiceInfoService.checkProductInfo(productSaveInfoVo));
    }


    /**
     * 查询base_product_service_info(服务产品项目关联)列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/getProductServiceList")
    public TableDataInfo getProductServiceList(@RequestBody ProductServiceInfo productServiceInfo)
    {
        startPage();
        List<ProductServiceInfo> list = productServiceInfoService.selectProductServiceInfoList(productServiceInfo);
        return getDataTable(list);
    }


    /**
     * 查询服务手册列表
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/getManualInfoList")
    public TableDataInfo getManualInfoList(BaseManualInfo baseManualInfo)
    {
        return getProviderInfoService.getlist(baseManualInfo);
    }

    /**
     * 查询base_product_info(服务产品)列表待审核状态
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/checkList")
    public TableDataInfo checkList(ProductInfo productInfo)
    {
        startPage();
        List<ProductInfo> list = productInfoService.selectCheckList(productInfo) ;
        return getDataTable(list);
    }


    /**
     * 记录审核信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @PostMapping("/insertCheckInfo")
    public AjaxResult insertCheckInfo(@RequestBody  ProductCheckInfo productCheckInfo)
    {
        return toAjax(productInfoService.insertCheckInfo(productCheckInfo));
    }

    /**
     * 查询核信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/selectCheckLog")
    public AjaxResult selectCheckLog(@RequestBody ProductCheckInfo productCheckInfo)
    {
        return AjaxResult.success(productCheckInfoService.selectProductCheckInfoList(productCheckInfo));
    }


    /**
     * 查询base_product_info(服务产品)列表待审核状态
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/mangerList")
    public TableDataInfo mangerList(ProductInfo productInfo)
    {
        startPage();
        List<ProductInfo> list = productInfoService.mangerList(productInfo) ;
        return getDataTable(list);
    }

    /**
     * 查询base_product_info(服务产品)列表待审核状态 无参
     * @return
     */
    //@PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/mangerListNull")
    public TableDataInfo mangerList()  throws Exception
    {
        startPage();
        List<ProductInfo> list = productInfoService.mangerListNull() ;
        return getDataTable(list);
    }

    /**
     * 产品下线
     */
   // @PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("/insertMangerInfo")
    public AjaxResult insertMangerInfo(@RequestBody ProductManagerLog productManagerLog)
    {
        return toAjax(productInfoService.insertMangerInfo(productManagerLog));
    }


    /**
     * 字段校验接口
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("/checkField")
    public AjaxResult checkField(@RequestBody ProductInfo productInfo)
    {
        return AjaxResult.success(productInfoService.checkField(productInfo));
    }



    /**
     * 供应商信息查询接口
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/selectSupplier")
    public AjaxResult selectSupplier(@RequestBody com.paic.ehis.system.api.domain.ProductInfo productInfo)
    {
        /*startPage();
        List<ProductSupplierInfo> list = productInfoService.selectSupplier(productInfo);
        return getDataTable(list);*/
        return getProviderInfoService.selectSupplier(productInfo);
    }


    /**
     * 配置供应商信息
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("/insertSupplier")
    public AjaxResult insertSupplier(@RequestBody com.paic.ehis.system.api.domain.ProductSupplierInfoVo productSupplierInfoVo)
    {
        return toAjax(productInfoService.insertSupplier(productSupplierInfoVo));
    }


    /**
     * 已经配置的供应商信息置为失效
     */
    //@PreAuthorize("@ss.hasPermi('system:info:add')")
    @PostMapping("/updateSupplierStatus")
    public AjaxResult updateSupplierStatus(@RequestBody com.paic.ehis.system.api.domain.ProductSupplierInfoVo productSupplierInfoVo)
    {
        return toAjax(productInfoService.updateSupplierStatus(productSupplierInfoVo));
    }


    /**
     * 供应商信息查询接口
     */
    //@PreAuthorize("@ss.hasPermi('system:info:query')")
    @PostMapping("/selectProductSupplier")
    public AjaxResult selectProductSupplier(@RequestBody ProductInfo productInfo)
    {
        /*startPage();
        List<ProductSupplierInfo> list = productInfoService.selectSupplier(productInfo);
        return getDataTable(list);*/
        return AjaxResult.success(productInfoService.selectProductSupplier(productInfo));
    }

}
