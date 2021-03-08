package com.paic.ehis.base.controller;

import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.base.domain.BaseIssuingcompany;
import com.paic.ehis.base.domain.dto.IssuingAndCompanyDTO;
import com.paic.ehis.base.domain.vo.IssuingCompanyVo;
import com.paic.ehis.base.service.IBaseIssuingcompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 出单公司信息 Controller
 *
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/issuingcompany")
public class BaseIssuingcompanyController extends BaseController {
    @Autowired
    private IBaseIssuingcompanyService baseIssuingcompanyService;


    /**
     * 查询出单公司信息 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:issuingcompany:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody BaseIssuingcompany baseIssuingcompany) {
        startPage(baseIssuingcompany);
        List<BaseIssuingcompany> list = baseIssuingcompanyService.selectBaseIssuingcompanyList(baseIssuingcompany);
        return getDataTable(list);
    }

    /**
     * 导出出单公司信息 列表
     */
    //@PreAuthorize("@ss.hasPermi('system:issuingcompany:export')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody HttpServletResponse response, BaseIssuingcompany baseIssuingcompany) throws IOException {
        BaseIssuingcompany baseIssuingcompany1 = new BaseIssuingcompany();
        String companycode = baseIssuingcompany.getCompanycode();
        String companyname = baseIssuingcompany.getCompanyname();
        String simplename = baseIssuingcompany.getSimplename();

        String utf8Code = new String(companycode.getBytes("UTF-8"));
        String utf8Name = new String(companyname.getBytes("UTF-8"));
        String utf8SimpleName = new String(simplename.getBytes("UTF-8"));

        baseIssuingcompany1.setCompanycode(utf8Code);
        baseIssuingcompany1.setCompanyname(utf8Name);
        baseIssuingcompany1.setSimplename(utf8SimpleName);

        List<BaseIssuingcompany> list = baseIssuingcompanyService.selectBaseIssuingcompanyList(baseIssuingcompany1);
        ExcelUtil<BaseIssuingcompany> util = new ExcelUtil<BaseIssuingcompany>(BaseIssuingcompany.class);
        util.exportExcel(response, list, "出单公司信息表");
    }

    /**
     * 获取出单公司信息 详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:issuingcompany:query')")
    @GetMapping(value = "/{companycode}")
    public AjaxResult getInfo(@PathVariable("companycode") String companyCode) {
        return AjaxResult.success(baseIssuingcompanyService.selectBaseIssuingcompanyById(companyCode));
    }

    /**
     * 使用
     * 新增出单公司信息+修改
     * 传入数据：出单公司名称companyname、出单公司简写名称simplename
     * 传出数据：出单公司名称companyname、出单公司简写名称simplename、出单公司编码companycode
     */
    //@PreAuthorize("@ss.hasPermi('system:issuingcompany:add')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.INSERT)
    @PostMapping("/addissuingAndCompanyDTO")
    public AjaxResult add(@RequestBody IssuingAndCompanyDTO issuingAndCompanyDTO) {
//        //出单公司简称-重复校验
//        List<BaseIssuingcompany> baseIssuingcompanies = baseIssuingcompanyService.selectBaseIssuingcompanyBySimplename(issuingAndCompanyDTO.getBaseIssuingcompany().getSimplename());
//        //出单公司名称-重复校验
//        List<BaseIssuingcompany> baseIssuingcompanies1 = baseIssuingcompanyService.selectBaseIssuingcompanyByCompanyname(issuingAndCompanyDTO.getBaseIssuingcompany().getCompanyname());
//        if (baseIssuingcompanies.size() != 0 && baseIssuingcompanies1.size()==0) {
//            return AjaxResult.success(1);//简称重复
//        } else if(baseIssuingcompanies.size() != 0 && baseIssuingcompanies1.size()==0){
//            return AjaxResult.success(2);//名称重复
//        }else if(baseIssuingcompanies.size() == 0 && baseIssuingcompanies1.size()==0){
//            return AjaxResult.success(3);//都重复
//        } else {
            IssuingCompanyVo issuingCompanyVo = baseIssuingcompanyService.insertBaseIssuingcompanyRest(issuingAndCompanyDTO);
            return AjaxResult.success(issuingCompanyVo);
//        }
    }

    /**
     * 修改出单公司信息
     */
    //@PreAuthorize("@ss.hasPermi('system:issuingcompany:edit')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IssuingAndCompanyDTO issuingAndCompanyDTO) {
        BaseIssuingcompany baseIssuingcompany = issuingAndCompanyDTO.getBaseIssuingcompany();
        return toAjax(baseIssuingcompanyService.updateBaseIssuingcompany(baseIssuingcompany));
    }

    /**
     * 删除出单公司信息
     */
    //@PreAuthorize("@ss.hasPermi('system:issuingcompany:remove')")
    @Log(title = "出单公司信息 ", businessType = BusinessType.DELETE)
    @DeleteMapping("/{companyCodes}")
    public AjaxResult remove(@PathVariable String[] companyCodes) {
        return toAjax(baseIssuingcompanyService.deleteBaseIssuingcompanyByIds(companyCodes));
    }
}
