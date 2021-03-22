package com.paic.ehis.claimcal.controller;


import com.paic.ehis.claimcal.domain.ClaimProduct;
import com.paic.ehis.claimcal.domain.ClaimProductPlanVO;
import com.paic.ehis.claimcal.domain.vo.UserVo;
import com.paic.ehis.claimcal.domain.dto.ClaimProductDTO;
import com.paic.ehis.claimcal.domain.dto.ProcessedProductDTO;
import com.paic.ehis.claimcal.domain.dto.SysUserDTO;
import com.paic.ehis.claimcal.domain.dto.UpdateClaimProductDTO;
import com.paic.ehis.claimcal.service.IClaimProductService;
import com.paic.ehis.claimcal.service.ISysUserService;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 产品信息Controller
 * 
 * @author sino
 * @date 2021-01-14
 */
@RestController
@RequestMapping("/product")
public class ClaimProductController extends BaseController
{
    @Autowired
    private IClaimProductService claimProductService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询产品信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/list")
    public TableDataInfo list(ClaimProduct claimProduct)
    {
        startPage(claimProduct);
        List<ClaimProduct> list = claimProductService.selectClaimProductList(claimProduct);
        return getDataTable(list);
    }

    /**
     * 查询福利对象
     */
//    @PreAuthorize("@ss.hasPermi('system:product:query')")
    @GetMapping("/{riskCode}")
    public TableDataInfo query(@PathVariable String riskCode)
    {
        List<ClaimProductPlanVO> list = claimProductService.selectProductById(riskCode);
        return getDataTable(list);
    }

    /**
     * 查询产品信息公共池
     */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/publicList")
    public TableDataInfo publicList(@RequestBody ClaimProduct claimProduct)
    {
        claimProduct.setIsAsc("desc");
        claimProduct.setOrderByColumn("update_time");
        startPage(claimProduct);
        List<ClaimProduct> list = claimProductService.selectProductPublicList(claimProduct);
        return getDataTable(list);
    }

    /**
     * 查询任务改派产品信息公共池
     */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/selectClaimProductList")
    public TableDataInfo selectClaimProductList(@RequestBody ClaimProductDTO claimProductDTO)
    {
        startPage(claimProductDTO);
        List<ClaimProductDTO> list = claimProductService.selectClaimProduct(claimProductDTO);
        return getDataTable(list);
    }


    /**
     * 修改任务改派操作人
     */
//    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateClaimProductList")
    public AjaxResult updateClaimProductList(@RequestBody UpdateClaimProductDTO updateClaimProductDTO)
    {
        claimProductService.UpdateClaimProductDTO(updateClaimProductDTO);
        return AjaxResult.success();
    }

    /** 查询任务改派修改后的操作人 */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/selectSysUser")
    public AjaxResult selectSysUser(@RequestBody SysUserDTO sysUserDTO)
    {
        startPage(sysUserDTO);
        List<UserVo> list = sysUserService.selectSysUser(sysUserDTO);
        return AjaxResult.success(list);
    }


    /**
     * 查询待处理产品信息
     */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/untreatedList")
    public TableDataInfo untreatedList(@RequestBody ClaimProduct claimProduct)
    {
        claimProduct.setIsAsc("desc");
        claimProduct.setOrderByColumn("update_time");
        startPage(claimProduct);
        List<ClaimProduct> list = claimProductService.selectUntreatedProductList(claimProduct);
        return getDataTable(list);
    }

    /**
     * 查询已处理产品信息
     */
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/processedList")
    public TableDataInfo processedList(@RequestBody ProcessedProductDTO processedProductDTO)
    {
        processedProductDTO.setIsAsc("desc");
        processedProductDTO.setOrderByColumn("update_time");
        startPage(processedProductDTO);
        List<ClaimProduct> list = claimProductService.selectProcessedProductList(processedProductDTO);
        return getDataTable(list);
    }

    /**
     * 导出产品信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:product:export')")
    @Log(title = "产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody ClaimProduct claimProduct) throws IOException
    {
        List<ClaimProduct> list = claimProductService.selectClaimProductList(claimProduct);
        ExcelUtil<ClaimProduct> util = new ExcelUtil<ClaimProduct>(ClaimProduct.class);
        util.exportExcel(response, list, "product");
    }

    /**
     * 获取产品信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:product:query')")
    @PutMapping(value = "/{riskCode}")
    public AjaxResult getInfo(@PathVariable("riskCode") String riskCode)
    {
        return AjaxResult.success(claimProductService.selectClaimProductInfoById(riskCode));
    }

    /**
     * 新增产品信息
     */
//    @PreAuthorize("@ss.hasPermi('system:product:add')")
    @Log(title = "产品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimProduct claimProduct)
    {
        return toAjax(claimProductService.insertClaimProduct(claimProduct));
    }

    /**
     * 修改产品信息
     */
//    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "产品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimProduct claimProduct)
    {
        return toAjax(claimProductService.updateClaimProduct(claimProduct));
    }

    /**
     * 获取产品信息至个人池
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "获取产品信息至个人池", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/riskCodes")
    public TableDataInfo obtainProduct(@RequestBody String[] riskCodes)
    {
        return getDataTable(claimProductService.obtainClaimProduct(riskCodes));
    }

    /**
     * 重设产品信息
     */
//    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "产品信息", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/riskCode")
    public AjaxResult resetClaimProduct(@RequestBody String riskCode)
    {
        return toAjax(claimProductService.updateClaimProductReset(riskCode));
    }

    /**
     * 删除产品信息
     */
//    @PreAuthorize("@ss.hasPermi('system:product:remove')")
    @Log(title = "产品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{riskCodes}")
    public AjaxResult remove(@PathVariable String[] riskCodes)
    {
        return toAjax(claimProductService.deleteClaimProductByIds(riskCodes));
    }

    /***************************************************/
    //公共池：数据来源-就是查询产品规则中更改者为空-且规则状态为（审核中）的数据
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/listAndPublicPool")
    public TableDataInfo listAndPublicPool(@RequestBody ClaimProduct claimProduct)
    {
        startPage(claimProduct);
        List<ClaimProduct> list = claimProductService.selectClaimProductListAndPublicPool(claimProduct);
        return getDataTable(list);
    }

    //点击获取（批量获取）：就是给选中的数据的更改者赋值为当前操作者
//    @PreAuthorize("@ss.hasPermi('system:product:edit')")
    @Log(title = "产品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/editUpdateBy")
    public TableDataInfo editUpdateBy(@RequestBody String[] riskCodes)
    {
        return getDataTable(claimProductService.updateClaimProductUpdateBy(riskCodes));
    }

    //个人池：（查询中需要加入对产品编码和产品名称的条件搜索）
    //	已处理：数据来源-查询更改者为当前操作者的数据-且risk_status为：（03-交单完成 04-交单退回 05-交单无效）的数据
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/listAndPersonalPoolFinish")
    public TableDataInfo listAndPersonalPoolFinish(@RequestBody ClaimProduct claimProduct)
    {
        startPage(claimProduct);
        List<ClaimProduct> list = claimProductService.selectClaimProductListAndPersonalPoolFinish(claimProduct);
        return getDataTable(list);
    }

    //	待处理：数据来源-查询更改者为当前操作者的数据-且risk_status（批次号）为：（02-交单复核）的数据
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/listAndPersonalPoolUnfinished")
    public TableDataInfo listAndPersonalPoolUnfinished(@RequestBody ClaimProduct claimProduct)
    {
        startPage(claimProduct);
        List<ClaimProduct> list = claimProductService.selectClaimProductListAndPersonalPoolUnfinished(claimProduct);
        return getDataTable(list);
    }
    /**************************************************************************/
    //产品查询   工作池
//    @PreAuthorize("@ss.hasPermi('system:product:list')")
    @PostMapping("/selectProductQuery")
    public TableDataInfo selectProductQuery(@RequestBody  ClaimProductDTO claimProductDTO){
        startPage(claimProductDTO);
        List<ClaimProductDTO> list = claimProductService.selectProductQuery(claimProductDTO);
        return getDataTable(list);
    }

}
