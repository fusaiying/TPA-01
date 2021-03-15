package com.paic.ehis.claimmgt.controller;


import com.paic.ehis.claimmgt.domain.ClaimCaseStanding;
import com.paic.ehis.claimmgt.domain.dto.ClaimCaseStandingDTO;
import com.paic.ehis.claimmgt.domain.vo.ClaimCaseStandingVo1;
import com.paic.ehis.claimmgt.mapper.SysUserMapper;
import com.paic.ehis.claimmgt.service.IClaimCaseStandingService;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.poi.ExcelUtil;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 报案台账信息 Controller
 * 
 * @author sino
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/standing")
public class ClaimCaseStandingController extends BaseController
{
    @Autowired
    private IClaimCaseStandingService claimCaseStandingService;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询报案台账信息 列表
     */
  //  @PreAuthorize("@ss.hasPermi('system:standing:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimCaseStanding claimCaseStanding)
    {
        startPage();
        List<ClaimCaseStanding> list = claimCaseStandingService.selectClaimCaseStandingList(claimCaseStanding);
        return getDataTable(list);
    }

    /**
     * 根据批次号 查询报案台账信息 列表
     */
    @PreAuthorize(hasAnyPermi = "@ss.hasPermi('system:standing:list')")
    @GetMapping("value = /{batchNo}")
    public TableDataInfo listByBatchNo(@PathVariable("batchNo") String batchNo)
    {
        startPage();
        List<ClaimCaseStanding> list = claimCaseStandingService.selectCaseStandingListByBatchNo(batchNo);
        return getDataTable(list);
    }
    /**
     * 查询台账
     * */
    @PostMapping("/listNew")
    public TableDataInfo listNew(@RequestBody ClaimCaseStandingDTO claimCaseStandingDTO)
    {
        startPage(claimCaseStandingDTO);
        List<ClaimCaseStandingVo1> list = claimCaseStandingService.selectClaimCaseStandingListNew(claimCaseStandingDTO);
        return getDataTable(list);
    }

    /**
     * 初始化查询台账
     * */
    @GetMapping("/listFirst")
    public TableDataInfo listFirst(ClaimCaseStandingDTO claimCaseStandingDTO)
    {
        startPage();
        List<ClaimCaseStandingVo1> list = claimCaseStandingService.selectClaimCaseStandingListFirst(claimCaseStandingDTO);
        return getDataTable(list);
    }

    /**
     * 导出报案台账信息 列表
     */
  //  @PreAuthorize("@ss.hasPermi('system:standing:export')")
    @Log(title = "报案台账信息 ", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClaimCaseStanding claimCaseStanding) throws IOException
    {
        List<ClaimCaseStanding> list = claimCaseStandingService.selectClaimCaseStandingList(claimCaseStanding);
        ExcelUtil<ClaimCaseStanding> util = new ExcelUtil<ClaimCaseStanding>(ClaimCaseStanding.class);
        util.exportExcel(response, list, "standing");
    }

    /**
     * 导出报案台账信息 列表
     */
    @PostMapping("/exportNew")
    public void exportNew(HttpServletResponse response, ClaimCaseStandingDTO claimCaseStandingDTO) throws IOException
    {
        List<ClaimCaseStandingVo1> list = claimCaseStandingService.selectClaimCaseStandingListNew(claimCaseStandingDTO);
        ExcelUtil<ClaimCaseStandingVo1> util = new ExcelUtil<ClaimCaseStandingVo1>(ClaimCaseStandingVo1.class);
        util.exportExcel(response, list, "standing");
    }

    /**
     * 获取报案台账信息 详细信息
     */
 //   @PreAuthorize("@ss.hasPermi('system:standing:query')")
    @GetMapping(value = "/{rptNo}")
    public AjaxResult getInfo(@PathVariable("rptNo") String rptNo)
    {
        return AjaxResult.success(claimCaseStandingService.selectClaimCaseStandingById(rptNo));
    }

    /**
     * 新增报案台账信息 
     */
//    @PreAuthorize("@ss.hasPermi('system:standing:add')")
    @Log(title = "报案台账信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClaimCaseStanding claimCaseStanding)
    {
        return toAjax(claimCaseStandingService.insertClaimCaseStanding(claimCaseStanding));
    }

    /**
     * 修改报案台账信息 
     */
 //   @PreAuthorize("@ss.hasPermi('system:standing:edit')")
    @Log(title = "报案台账信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClaimCaseStanding claimCaseStanding)
    {
        return toAjax(claimCaseStandingService.updateClaimCaseStanding(claimCaseStanding));
    }

    /**
     * 修改报案台账信息
     */
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody ClaimCaseStandingVo1 claimCaseStandingVo1)
    {
        return toAjax(claimCaseStandingService.updateClaimCaseStandingVo1(claimCaseStandingVo1));
    }

    /**
     * 删除报案台账信息 
     */
  //  @PreAuthorize("@ss.hasPermi('system:standing:remove')")
    @Log(title = "报案台账信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rptNos}")
    public AjaxResult remove(@PathVariable String[] rptNos)
    {
        return toAjax(claimCaseStandingService.deleteClaimCaseStandingByIds(rptNos));
    }
}
