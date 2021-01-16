package com.paic.ehis.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.paic.ehis.system.domain.BaseIssuingcompany;
import com.paic.ehis.system.domain.BaseIssuingcompanyInvoice;
import com.paic.ehis.system.domain.dto.IssuingAndCompanyDTO;
import com.paic.ehis.system.domain.vo.IssuingCompanyVo;
import com.paic.ehis.system.service.IBaseIssuingcompanyInvoiceService;
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
import com.paic.ehis.system.service.IBaseIssuingcompanyService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.page.TableDataInfo;

/**
 * 出单公司信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/issuingcompany")
public class BaseIssuingcompanyController extends BaseController
{
    @Autowired
    private IBaseIssuingcompanyService baseIssuingcompanyService;

    @Autowired
    private IBaseIssuingcompanyInvoiceService iBaseIssuingcompanyInvoiceService;

    /**
     * 查询出单公司信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseIssuingcompany baseIssuingcompany)
    {
        startPage();
        List<BaseIssuingcompany> list = baseIssuingcompanyService.selectBaseIssuingcompanyList(baseIssuingcompany);
        return getDataTable(list);
    }

    /**
     * 导出出单公司信息 列表
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:export')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BaseIssuingcompany baseIssuingcompany) throws IOException
    {
        List<BaseIssuingcompany> list = baseIssuingcompanyService.selectBaseIssuingcompanyList(baseIssuingcompany);
        ExcelUtil<BaseIssuingcompany> util = new ExcelUtil<BaseIssuingcompany>(BaseIssuingcompany.class);
        util.exportExcel(response, list, "issuingcompany");
    }

    /**
     * 获取出单公司信息 详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:query')")
    @GetMapping(value = "/{companycode}")
    public AjaxResult getInfo(@PathVariable("companycode") String companyCode)
    {
        return AjaxResult.success(baseIssuingcompanyService.selectBaseIssuingcompanyById(companyCode));
    }

    /**使用
     * 新增出单公司信息
     * 传入数据：出单公司名称companyname、出单公司简写名称simplename
     * 传出数据：出单公司名称companyname、出单公司简写名称simplename、出单公司编码companycode
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:add')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.INSERT)
    @PostMapping("/addissuingAndCompanyDTO")
    public AjaxResult add(@RequestBody IssuingAndCompanyDTO issuingAndCompanyDTO) {
//        //IssuingAndCompanyDTO
//        BaseIssuingcompany baseIssuingcompany = issuingAndCompanyDTO.getBaseIssuingcompany();//出单公司
//        BaseIssuingcompanyInvoice baseIssuingcompanyInvoice = issuingAndCompanyDTO.getBaseIssuingcompanyInvoice();//出单公司开票
//
//        IssuingCompanyVo issuingCompanyVo = new IssuingCompanyVo();
//
//        String str = "C" + PubFun.createMySqlMaxNoUseCache("FILINGCODE",10,5);
//        issuingCompanyVo.setCompanycode(str);//公司出单编码
//        issuingCompanyVo.setSimplename(baseIssuingcompany.getSimplename());
//        issuingCompanyVo.setCompanyname(baseIssuingcompany.getCompanyname());
//
//        baseIssuingcompany.setCompanycode(issuingCompanyVo.getCompanycode());
//        baseIssuingcompanyService.insertBaseIssuingcompany(baseIssuingcompany);
//
//        BaseIssuingcompanyInvoice baseIssuingcompanyInvoice1 = issuingAndCompanyDTO.getBaseIssuingcompanyInvoice();
//        //String invoicename = baseIssuingcompanyInvoice1.getInvoicename();
//        //if(invoicename != null && invoicename != ""){//非空-有值
//        //Objects.nonNull(baseIssuingcompanyInvoice1) && Objects.equals(baseIssuingcompanyInvoice1,"")
//        if (Objects.nonNull(baseIssuingcompanyInvoice1) && Objects.equals(baseIssuingcompanyInvoice1,"")){
//            System.out.println(str+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11");
//            baseIssuingcompanyInvoice.setCompanycode(issuingCompanyVo.getCompanycode());
//            int i = iBaseIssuingcompanyInvoiceService.insertBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
//        }
//
//        return AjaxResult.success(issuingCompanyVo);
        IssuingCompanyVo issuingCompanyVo = baseIssuingcompanyService.insertBaseIssuingcompanyTwo(issuingAndCompanyDTO);
        return AjaxResult.success(issuingCompanyVo);
    }

    /**
     * 修改出单公司信息 
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:edit')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IssuingAndCompanyDTO issuingAndCompanyDTO)
    {
        //issuingAndCompanyDTO
        BaseIssuingcompanyInvoice baseIssuingcompanyInvoice = issuingAndCompanyDTO.getBaseIssuingcompanyInvoice();//出单公司开票
        BaseIssuingcompany baseIssuingcompany = issuingAndCompanyDTO.getBaseIssuingcompany();//出单公司

        if(baseIssuingcompanyInvoice.getInvoicename()!=null || baseIssuingcompanyInvoice.getInvoicename() != ""){//非空-有值
            baseIssuingcompanyInvoice.setCompanycode(baseIssuingcompany.getCompanycode());
            iBaseIssuingcompanyInvoiceService.updateBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
        }else {
            baseIssuingcompanyInvoice.setCompanycode(baseIssuingcompany.getCompanycode());
            iBaseIssuingcompanyInvoiceService.insertBaseIssuingcompanyInvoice(baseIssuingcompanyInvoice);
        }

        return toAjax(baseIssuingcompanyService.updateBaseIssuingcompany(baseIssuingcompany));
    }

    /**
     * 删除出单公司信息 
     */
    @PreAuthorize("@ss.hasPermi('system:issuingcompany:remove')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyCodes}")
    public AjaxResult remove(@PathVariable String[] companyCodes)
    {
        return toAjax(baseIssuingcompanyService.deleteBaseIssuingcompanyByIds(companyCodes));
    }
}
