package com.paic.ehis.claimflow.controller;

import com.paic.ehis.claimflow.domain.dto.ClaimFlowDTO;
import com.paic.ehis.claimflow.domain.vo.GCCPolicyListVo;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/external_claimflow")
public class ClaimFlowController extends BaseController {
    @Autowired
    private IClaimFlowService claimFlowService;

    /**
     * 客服模块：内部使用保单列表
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/selectPolicyList")
    public TableDataInfo selectPolicyList(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("查询保单列表入参：{}",claimFlowDTO);
        startPage(claimFlowDTO);
        List<PolicyListVo> policyListVos = claimFlowService.selectPolicyList(claimFlowDTO);
        logger.info("查询保单列表结果：{}",policyListVos);
        return getDataTable(policyListVos);
    }
    /**
     * 客服模块：内部使用保单信息
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/getPolicyInfo")
    public AjaxResult getPolicyInfoBy(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("查询保单信息入参：{}",claimFlowDTO);
        return AjaxResult.success(claimFlowService.getPolicyInfoBy(claimFlowDTO));
    }

    /***********************************************   保单类  ***************************************************/

    /**
     * 客服模块：提供GCC保单列表接口 后期 转 policy模块
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/queryPolicyList")
    public AjaxResult queryPolicyList(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("提供GCC保单列表接口入参：{}",claimFlowDTO);
        List<GCCPolicyListVo> list=claimFlowService.queryPolicyListToGCC(claimFlowDTO);
        return AjaxResult.success(list);
    }

    /**
     * 客服模块：提供GCC保单信息资料接口 后期 转 policy模块
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/queryPolicyInfo")
    public AjaxResult queryPolicyInfo(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("提供GCC保单信息资料接口入参：{}",claimFlowDTO);
        return AjaxResult.success(claimFlowService.queryPolicyInfoToGCC(claimFlowDTO));
    }

    /**
     *  客服模块：提供GCC分单责任查询接口 后期 转 policy模块
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/queryDutyInfo")
    public AjaxResult queryDutyInfo(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("提供GCC分单责任查询接口入参：{}",claimFlowDTO);
        return AjaxResult.success(claimFlowService.queryDutyInfoToGCC(claimFlowDTO));
    }


    /**
     * 客服模块：通过GCC服务信息查询接口
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/queryHealthServiceInfo")
    public AjaxResult queryHealthServiceInfo(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("提供GCC服务信息查询接口入参：{}",claimFlowDTO);
        return AjaxResult.success(claimFlowService.queryHealthServiceToGCC(claimFlowDTO));
    }

    /***********************************************   理赔类  ***************************************************/
    /**
     * 客服模块：提供GCC理赔信息查询接口
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/queryClaimList")
    public AjaxResult queryClaimList(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("提供GCC理赔信息查询接口入参：{}",claimFlowDTO);
        return AjaxResult.success(claimFlowService.queryClaimListToGCC(claimFlowDTO));
    }

    /**
     * 客服模块：提供GCC理赔受理信息查询接口
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/queryClaimAcceptList")
    public AjaxResult queryClaimAcceptList(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("提供GCC理赔受理信息查询接口入参：{}",claimFlowDTO);
        return AjaxResult.success(claimFlowService.queryClaimAcceptListToGCC(claimFlowDTO));
    }

    /**
     * 客服模块：通过GCC理赔历史账单明细查询接口
     * @param claimFlowDTO
     * @return
     */
    @PostMapping("/queryClaimBillDetail")
    public AjaxResult queryClaimBillDetail(@RequestBody ClaimFlowDTO claimFlowDTO){
        logger.info("提供GCC理赔受理信息查询接口入参：{}",claimFlowDTO);
        return AjaxResult.success(claimFlowService.queryClaimBillDetailToGCC(claimFlowDTO));
    }

}
