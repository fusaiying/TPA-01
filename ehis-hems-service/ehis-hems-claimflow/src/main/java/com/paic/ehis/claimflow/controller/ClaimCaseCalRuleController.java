package com.paic.ehis.claimflow.controller;


import com.paic.ehis.claimflow.domain.ClaimCaseCalRule;
import com.paic.ehis.claimflow.domain.vo.TheAdjustmentSubsidiaryOneVO;
import com.paic.ehis.claimflow.service.IClaimCaseCalRuleService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 案件赔付规则记录Controller
 * 
 * @author sino
 * @date 2021-01-20
 */
@RestController
@RequestMapping("/calRule")
public class ClaimCaseCalRuleController extends BaseController
{
    @Autowired
    private IClaimCaseCalRuleService claimCaseCalRuleService;


    /**
     * 查询理算明细使用规则列表
     */
//    @PreAuthorize("@ss.hasPermi('system:rule:list')")
    @GetMapping("/listByRptNo")
    public TableDataInfo listByRptNo(String rptNo)
    {
        startPage();
        List<TheAdjustmentSubsidiaryOneVO> list = claimCaseCalRuleService.selectClaimCaseCalRuleListByRptNo(rptNo);
        return getDataTable(list);
    }

    //不参与理算按钮-就是新增一条不参与理算的数据/将本身变为无效
//    @PreAuthorize("@ss.hasPermi('system:rule:edit')")
//    @Log(title = "不参与理算规则", businessType = BusinessType.UPDATE)
    @PostMapping("/editAdjustmentSubsidiaryNo")
    public AjaxResult editAdjustmentSubsidiaryNo(ClaimCaseCalRule claimCaseCalRule)
    {
        return toAjax(claimCaseCalRuleService.updateAdjustmentSubsidiaryNo(claimCaseCalRule));
    }

}
