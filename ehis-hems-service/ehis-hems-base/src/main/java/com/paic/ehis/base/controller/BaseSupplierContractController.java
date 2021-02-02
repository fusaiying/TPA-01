package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseSupplierContract;
import com.paic.ehis.base.domain.BaseSupplierContractBak;
import com.paic.ehis.base.service.IBaseSupplierContractBakService;
import com.paic.ehis.base.service.IBaseSupplierContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * base_supplier_contract（供应商合约）Controller
 * 
 * @author sino
 * @date 2020-12-31
 */
@RestController
@RequestMapping("/supplier")
public class BaseSupplierContractController extends BaseController
{
    @Autowired
    private IBaseSupplierContractService baseSupplierContractService;

    @Autowired
    private IBaseSupplierContractBakService iBaseSupplierContractBakService;

    /**
     * 查询base_supplier_contract（供应商合约）列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BaseSupplierContract baseSupplierContract)
    {
        startPage();
        List<BaseSupplierContract> list = baseSupplierContractService.selectBaseSupplierContractList(baseSupplierContract);
        return getDataTable(list);
    }

    /**
     * 导出base_supplier_contract（供应商合约）列表
     */
    @Log(title = "base_supplier_contract（供应商合约）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseSupplierContract baseSupplierContract) throws IOException
    {
        List<BaseSupplierContract> list = baseSupplierContractService.selectBaseSupplierContractList(baseSupplierContract);
        ExcelUtil<BaseSupplierContract> util = new ExcelUtil<BaseSupplierContract>(BaseSupplierContract.class);
        util.exportExcel(response, list, "contract");
    }

    /**
     * 获取base_supplier_contract（供应商合约）详细信息
     */
    @GetMapping(value = "/{contractNo}")
    public AjaxResult getInfo(@PathVariable("contractNo") String contractNo)
    {
        return AjaxResult.success(baseSupplierContractService.selectBaseSupplierContractById(contractNo));
    }

   /**
    *若供应商下已签订合约，合约列表中供应商对应的合约编码、合约名称、合约起止日期均有值，
    *且当供应商下存在多条合约信息时仅显示该供应商下合约终止日期最晚的一条合约信息（即供应商下创建时间最晚的一条合约信息）
    */

    @GetMapping(value = "/last/{servcomno}")
    public  AjaxResult lastInfo(@PathVariable("servcomno") String servcomno)
    {
        return AjaxResult.success(baseSupplierContractService.selectBaseSupplierLast(servcomno));
    }

    /**
     * 供应商合约管理主查询页面需默认显示截止当前时间合约签约时间在三个月内且合约状态为“有效”的数据
     */
     @GetMapping("/month")
     public TableDataInfo lista(BaseSupplierContract baseSupplierContract) throws Exception
     {
         startPage();
         List<BaseSupplierContract> month = baseSupplierContractService.selectBaseSupplierMonth(baseSupplierContract);
         return getDataTable(month);

     }

    /**
     * 根据服务机构id查询合约信息
     */
     @GetMapping(value = "/code/{providercode}")
     public AjaxResult providerCodeinfo(@PathVariable("providercode") String providercode)
     {
         return AjaxResult.success(baseSupplierContractService.selectBaseproviderCode(providercode));
     }

    /**
     * 新增base_supplier_contract（供应商合约）
     */
    @Log(title = "base_supplier_contract（供应商合约）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseSupplierContract baseSupplierContract)
    {
        AjaxResult result = null;


        String hospContractCode=baseSupplierContract.getHospContractCode();
        if ("01".equals(hospContractCode)) {
            result = AjaxResult.success(baseSupplierContractService.insertBaseSupplierContract(baseSupplierContract));

        }else if("02".equals(hospContractCode)){
            BaseSupplierContractBak bakBean = new BaseSupplierContractBak();
            BeanUtils.copyProperties(baseSupplierContract,bakBean);
            result =  AjaxResult.success(iBaseSupplierContractBakService.insertBaseSupplierContractBak(bakBean));
        }
        return result;
    }


    /**
     * 修改base_supplier_contract（供应商合约）
     */
    @Log(title = "base_supplier_contract（供应商合约）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSupplierContract baseSupplierContract)
    {
        AjaxResult ajaxResult = null;
        String hospContractCode = baseSupplierContract.getHospContractCode();
        if ("01".equals(hospContractCode)) {
             ajaxResult = toAjax(baseSupplierContractService.updateBaseSupplierContract(baseSupplierContract));
        } else if("02".equals(hospContractCode)){
            BaseSupplierContractBak bakBean = new BaseSupplierContractBak();
            BeanUtils.copyProperties(baseSupplierContract,bakBean);
            ajaxResult = toAjax(iBaseSupplierContractBakService.updateBaseSupplierContractBak(bakBean));
        }
        return ajaxResult;
    }

    /**
     * 删除base_supplier_contract（供应商合约）
     */
    @Log(title = "base_supplier_contract（供应商合约）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contractNos}")
    public AjaxResult remove(@PathVariable String[] contractNos)
    {
        return toAjax(baseSupplierContractService.deleteBaseSupplierContractByIds(contractNos));
    }
}
