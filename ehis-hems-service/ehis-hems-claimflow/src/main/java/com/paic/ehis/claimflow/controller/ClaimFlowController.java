package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.dto.ClaimFlowDTO;
import com.paic.ehis.claimflow.domain.vo.PolicyListVo;
import com.paic.ehis.claimflow.service.IClaimFlowService;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/external_claimflow")
public class ClaimFlowController extends BaseController {
    @Autowired
    private IClaimFlowService claimFlowService;

    @PostMapping("/selectPolicyList")
    public TableDataInfo selectPolicyList(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("查询保单列表入参：{}",claimFlowDTO);
        startPage(claimFlowDTO);
        List<PolicyListVo> policyListVos = claimFlowService.selectPolicyList(claimFlowDTO);
        logger.info("查询保单列表结果：{}",policyListVos);
        return getDataTable(policyListVos);
    }

    @PostMapping("/getPolicyInfo")
    public AjaxResult getPolicyInfoBy(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("查询保单信息入参：{}",claimFlowDTO);
        return AjaxResult.success(claimFlowService.getPolicyInfoBy(claimFlowDTO));
    }
}
