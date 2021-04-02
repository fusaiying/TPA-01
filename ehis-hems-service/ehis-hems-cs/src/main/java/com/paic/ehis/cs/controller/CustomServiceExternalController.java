package com.paic.ehis.cs.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.cs.domain.InterfaceMessage;
import com.paic.ehis.cs.domain.WorkOrderAccept;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.service.*;
import com.paic.ehis.cs.utils.CodeEnum;
import com.paic.ehis.cs.utils.RequestWrapper;
import com.paic.ehis.system.api.ClaimFlowService;
import com.paic.ehis.system.api.domain.dto.ClaimFlowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 客服对外接口Controller
 * by Louis
 */
@RestController
@RequestMapping("/external_cs")
public class CustomServiceExternalController extends BaseController {
    /**
     * 工单受理服务
     */
    @Autowired
    private IWorkOrderAcceptService iWorkOrderAcceptService;

    @Autowired
    private IHcsModificationService iHcsModificationService;

    @Autowired
    private IInterfaceMessageService iInterfaceMessageService;

    @Autowired
    private ClaimFlowService claimFlowService;


    //添加到配置文件中
    /**
     * 信息需求，投诉
     */
    private String baseTypes="informationApplication,complainApplication";
    private String baseTypeCodes="01,03";
    /**
     * VIP门诊预约
     * 门诊直接结算
     * 特殊门诊直接结算服务
     * 住院直结结算
     * 门诊中医治疗直接结算
     */
    private String incrementTypes="vipClinic,clinicDirectPay";
    /**
     * 转医疗模块处理类型
     */
    private String incrementOutTypes="demesticSecondCure,demesticAccidentAssitance,overseaAccidentAssitance";
    /**
     * HCS接口处理类型 建议不做区分
     */
    private String modifyServiceType="modifyService,completeModifyService";


    /**
     * 基础受理接口
     * By Louis
     *
     * @param request
     * @return
     */
//    @Transactional
    @PostMapping("/baseAccept")
    public AjaxResult addBaseAccept(HttpServletRequest request) {
        //请求参数获取
        //方式一：
//        Enumeration<String> parameterNames = request.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String parameterName = parameterNames.nextElement();
//            String parameterValue = request.getParameter(parameterName);
//            logger.info("基础受理接口请求获取到参数: {}->{}", parameterName, parameterValue);
//        }
        //方式二：
        Map requestMap=request.getParameterMap();
        JSONArray jArray = new JSONArray();
        jArray.add(requestMap);
        String params = jArray.toString();
        logger.info("增值服务受理接口请求获取到参数: {}", params);
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("基础受理接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.error("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        BasicServiceAppilcation businessData=null;
        WorkOrderAccept workOrderAccept=null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String type = jsonObject.getString("type");
            if (StringUtils.isEmpty(type)) {
                return AjaxResult.error("服务类型不能为空");
            }
            logger.info("基础受理接口请求中type为:{}", type);
            String applicationCaseNo = jsonObject.getString("applicationCaseNo");
            if (StringUtils.isEmpty(applicationCaseNo)) {
                return AjaxResult.error("受理案件号不能为空");
            }

            //判断类型是否配置正确及是否可处理的类型
            if(StringUtils.isEmpty(baseTypes) || StringUtils.isEmpty(baseTypeCodes)){
                return AjaxResult.error("处理类型(编码)配置异常,请联系运维人员");
            }
            String[] typeArr=baseTypes.split(",");
            String[] codeArr=baseTypeCodes.split(",");
            if(typeArr.length!=codeArr.length){
                return AjaxResult.error("处理类型与编码个数不匹配,请联系运维人员");
            }
            List<String> typeList= Arrays.asList(typeArr);
            int index=typeList.indexOf(type);
            if(index==-1){
                return AjaxResult.error("接口请求中type为不可处理类型");
            }
            workOrderAccept=new WorkOrderAccept();
            workOrderAccept.setOtherNo(applicationCaseNo);
            workOrderAccept.setBusinessType(codeArr[index]);
            String workOrderNo=iWorkOrderAcceptService.isExistAcceptByOtherNo(workOrderAccept);
            if(StringUtils.isNotEmpty(workOrderNo)){
                return AjaxResult.error("受理案件号重复受理");
            }
            //根据类型不同进行必传条件校验
            String accepter = jsonObject.getString("accepter");
            if (StringUtils.isEmpty(accepter)) {
                return AjaxResult.error("受理人不能为空");
            }

            String acceptDate = jsonObject.getString("acceptDate");
            if (StringUtils.isEmpty(acceptDate)) {
                return AjaxResult.error("受理时间不能为空");
            }

            if ("informationApplication".equals(businessData.getType())) {
                String serviceSecondItemCode = jsonObject.getString("serviceSecondItemCode");
                if (StringUtils.isEmpty(serviceSecondItemCode)) {
                    return AjaxResult.error("二级服务项目代码不能为空");
                }
            }
//            InterfaceMessage interfaceMessage=new InterfaceMessage();
//            interfaceMessage.setParameters(params);
//            interfaceMessage.setBody(body);
//            interfaceMessage.setApplicationRole("01");
//            interfaceMessage.setCreatedBy("interface");
//            interfaceMessage.setUpdatedBy("interface");
//            interfaceMessage.setCreatedTime(DateUtils.getNowDate());
//            interfaceMessage.setUpdatedTime(DateUtils.getNowDate());
//            iInterfaceMessageService.insertInterfaceMessage(interfaceMessage);
            //转换成业务入参对象
            businessData =JSONObject.toJavaObject(jsonObject, BasicServiceAppilcation.class);
        } catch (Exception e) {
            logger.error("请求处理异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法,请检查");
        }
        try {
            return toAjax(iWorkOrderAcceptService.insertBasicServiceApplication(workOrderAccept,businessData));
        } catch (Exception e) {
            logger.error("服务器内部处理异常:{}", e.getMessage());
            return AjaxResult.error("服务器内部处理异常,请联系运维人员");
        }
    }

    /**
     * 增值服务受理接口:
     * VIP门诊预约
     * 特殊门诊直接结算服务
     * 门诊直接结算
     * 住院直结结算
     * 门诊中医治疗直接结算
     * 除了上面类型外的增值服务受理转医疗
     * By Louis
     *
     * @param request
     * @return
     */
//    @Transactional
    @PostMapping("/incrementAccept")
    public AjaxResult addIncrementAccept(HttpServletRequest request) {
        //请求参数获取:
        //方式一：
//        Enumeration<String> parameterNames = request.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String parameterName = parameterNames.nextElement();
//            String parameterValue = request.getParameter(parameterName);
//            logger.info("增值服务受理接口请求获取到参数: {}->{}", parameterName, parameterValue);
//        }
        //方式二：
        Map requestMap=request.getParameterMap();
        JSONArray jArray = new JSONArray();
        jArray.add(requestMap);
        String params = jArray.toString();
        logger.info("增值服务受理接口请求获取到参数: {}", params);
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("增值服务受理接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.error("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        IncrementApplication businessData=null;
        WorkOrderAccept workOrderAccept=null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String type = jsonObject.getString("type");
            if (StringUtils.isEmpty(type)) {
                return AjaxResult.error("服务类型不能为空");
            }
            logger.info("增值服务受理接口请求中type为:{}", type);
            String applicationCaseNo = jsonObject.getString("applicationCaseNo");
            if (StringUtils.isEmpty(applicationCaseNo)) {
                return AjaxResult.error("受理案件号不能为空");
            }
            //判断是否外转其他模块
            if(StringUtils.isNotEmpty(incrementOutTypes)){
                String[] outTypeArr=incrementOutTypes.split(",");
                List<String> outTypeList= Arrays.asList(outTypeArr);
                if(outTypeList.contains(type)){
                    //将数据转入医疗模块，待医疗提供 TODO
                    InterfaceMessage interfaceMessage=new InterfaceMessage();
                    interfaceMessage.setInterfaceCode("GCC002");
                    interfaceMessage.setApplicationRole("01");
                    interfaceMessage.setParameters(params);
                    interfaceMessage.setBody(body);
                    interfaceMessage.setCreatedBy("interface");
                    interfaceMessage.setCreatedTime(DateUtils.getNowDate());
                    return AjaxResult.success("受理成功");
                }
            }
            //判断类型是否配置正确及是否可处理的类型
            if(StringUtils.isEmpty(incrementTypes)){
                return AjaxResult.error("处理类型配置异常,请联系运维人员");
            }
            String[] typeArr=incrementTypes.split(",");
            List<String> typeList= Arrays.asList(typeArr);
            if(!typeList.contains(type)){
                return AjaxResult.error("接口请求中type为不可处理类型");
            }
            workOrderAccept=new WorkOrderAccept();
            workOrderAccept.setOtherNo(applicationCaseNo);
            workOrderAccept.setBusinessType(CodeEnum.BUSINESS_TYPE_02.getCode());
            String workOrderNo=iWorkOrderAcceptService.isExistAcceptByOtherNo(workOrderAccept);
            if(StringUtils.isNotEmpty(workOrderNo)){
                return AjaxResult.error("受理案件号重复受理");
            }
            //根据类型不同进行必传条件校验

            //转换成业务入参对象
            businessData =JSONObject.toJavaObject(jsonObject, IncrementApplication.class);
        } catch (Exception e) {
            logger.error("增值服务受理接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法");
        }

        try {
            return toAjax(iWorkOrderAcceptService.insertIncrementServiceApplication(businessData));
        }catch (Exception e){
            logger.error("服务器内部处理异常:{}", e.getMessage());
            return AjaxResult.error("服务器内部处理异常,请联系运维人员");
        }
    }

    /**
     * 受理GCC预约修改、信息需求服务重新激活
     * @param request
     * @return
     */
    @PostMapping("modifyServiceApplication")
    public AjaxResult addModifyServiceAccept(HttpServletRequest request){
        //请求参数获取
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            logger.info("受理GCC预约修改、信息需求服务重新激活接口请求获取到参数: {}->{}", parameterName, parameterValue);
        }
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("受理GCC预约修改、信息需求服务重新激活接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.success("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
//            String type = jsonObject.getString("type");
//            if (StringUtils.isEmpty(type)) {
//                return AjaxResult.error("修改处理类型不能为空");
//            }
//            logger.info("受理GCC预约修改、信息需求服务重新激活接口请求中type为:{}", type);
            String applicationCaseNo = jsonObject.getString("applicationCaseNo");
            if (StringUtils.isEmpty(applicationCaseNo)) {
                return AjaxResult.error("受理案件号不能为空");
            }
            WorkOrderAccept workOrderAccept=new WorkOrderAccept();
            workOrderAccept.setOtherNo(applicationCaseNo);
            String workOrderNo=iWorkOrderAcceptService.isExistAcceptByOtherNo(workOrderAccept);
            if(StringUtils.isEmpty(workOrderNo)){
                return AjaxResult.error("暂无此受理号相关信息，请确定案件已被TPA受理");
            }
            //判断类型是否是可处理的类型
//            String[] typeArr=modifyServiceType.split(",");
//            List<String> typeList= Arrays.asList(typeArr);
//            if(!typeList.contains(type)){
//                return AjaxResult.error("接口请求中type为不可处理类型");
//            }
            //根据类型不同进行必传条件校验


            //转换成业务入参对象
            ModifyServiceApplication businessData =JSONObject.toJavaObject(jsonObject, ModifyServiceApplication.class);
            businessData.setWorkOrderNo(workOrderNo);
            return toAjax(iHcsModificationService.addModifyServiceAccept(businessData));

        } catch (Exception e) {
            logger.error("受理GCC预约修改、信息需求服务重新激活接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法");
        }
    }

    /**
     * 保单列表查询接口
     * @param request
     * @return
     */
    @PostMapping("queryPolicyListService")
    public AjaxResult queryPolicyListService(HttpServletRequest request){
//方式二：
        Map requestMap=request.getParameterMap();
        JSONArray jArray = new JSONArray();
        jArray.add(requestMap);
        String params = jArray.toString();
        logger.info("保单列表查询接口请求获取到参数: {}", params);
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("保单列表查询接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.error("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        ClaimFlowDTO businessData=null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String customerNo = jsonObject.getString("customerNo");
            if (StringUtils.isEmpty(customerNo)) {
                return AjaxResult.error("客户号不能为空");
            }
            //转换成业务入参对象
            businessData =JSONObject.toJavaObject(jsonObject, ClaimFlowDTO.class);
        } catch (Exception e) {
            logger.error("保单列表查询接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法");
        }

        try {
            return claimFlowService.queryPolicyList(businessData);
        }catch (Exception e){
            logger.error("服务器内部处理异常:{}", e.getMessage());
            return AjaxResult.error("服务器内部处理异常,请联系运维人员");
        }
    }

    /**
     * 保单信息资料查询接口
     * @param request
     * @return
     */
    @PostMapping("queryPolicyInfoService")
    public AjaxResult queryPolicyInfoService(HttpServletRequest request){

        //方式二：
        Map requestMap=request.getParameterMap();
        JSONArray jArray = new JSONArray();
        jArray.add(requestMap);
        String params = jArray.toString();
        logger.info("保单信息资料查询接口请求获取到参数: {}", params);
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("保单信息资料查询接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.error("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        ClaimFlowDTO businessData=null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String policyNo = jsonObject.getString("policyNo");
            if (StringUtils.isEmpty(policyNo)) {
                return AjaxResult.error("保单号不能为空");
            }
            //转换成业务入参对象
            businessData =JSONObject.toJavaObject(jsonObject, ClaimFlowDTO.class);
        } catch (Exception e) {
            logger.error("保单信息资料查询接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法");
        }

        try {
            return claimFlowService.queryPolicyInfo(businessData);
        }catch (Exception e){
            logger.error("服务器内部处理异常:{}", e.getMessage());
            return AjaxResult.error("服务器内部处理异常,请联系运维人员");
        }
    }

    /**
     * 分单责任信息查询
     * @param request
     * @return
     */
    @PostMapping("queryDutyInfoService")
    public AjaxResult queryDutyInfoService(HttpServletRequest request){
        //方式二：
        Map requestMap=request.getParameterMap();
        JSONArray jArray = new JSONArray();
        jArray.add(requestMap);
        String params = jArray.toString();
        logger.info("分单责任信息查询接口请求获取到参数: {}", params);
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("分单责任信息查询接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.error("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        ClaimFlowDTO businessData=null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String certno = jsonObject.getString("certno");
            if (StringUtils.isEmpty(certno)) {
                return AjaxResult.error("分单号不能为空");
            }
            //转换成业务入参对象
            businessData =JSONObject.toJavaObject(jsonObject, ClaimFlowDTO.class);
        } catch (Exception e) {
            logger.error("分单责任信息查询接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法");
        }

        try {
            return claimFlowService.queryDutyInfo(businessData);
        }catch (Exception e){
            logger.error("服务器内部处理异常:{}", e.getMessage());
            return AjaxResult.error("服务器内部处理异常,请联系运维人员");
        }
    }

    /**
     * 理赔信息查询
     * @param request
     * @return
     */
    @PostMapping("queryClaimInfoService")
    public AjaxResult queryClaimInfoService(HttpServletRequest request){
        //方式二：
        Map requestMap=request.getParameterMap();
        JSONArray jArray = new JSONArray();
        jArray.add(requestMap);
        String params = jArray.toString();
        logger.info("理赔信息查询接口请求获取到参数: {}", params);
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("理赔信息查询接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.error("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        ClaimFlowDTO businessData=null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String certno = jsonObject.getString("certno");
            if (StringUtils.isEmpty(certno)) {
                return AjaxResult.error("分单号不能为空");
            }
            //转换成业务入参对象
            businessData =JSONObject.toJavaObject(jsonObject, ClaimFlowDTO.class);
        } catch (Exception e) {
            logger.error("理赔信息查询接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法");
        }

        try {
            return claimFlowService.queryClaimListToGCC(businessData);
        }catch (Exception e){
            logger.error("服务器内部处理异常:{}", e.getMessage());
            return AjaxResult.error("服务器内部处理异常,请联系运维人员");
        }
    }
    /**
     * 理赔受理信息查询
     * @param request
     * @return
     */
    @PostMapping("queryClaimAcceptService")
    public AjaxResult queryClaimAcceptService(HttpServletRequest request){
        //方式二：
        Map requestMap=request.getParameterMap();
        JSONArray jArray = new JSONArray();
        jArray.add(requestMap);
        String params = jArray.toString();
        logger.info("理赔受理信息查询接口请求获取到参数: {}", params);
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("理赔受理信息查询接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.error("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        ClaimFlowDTO businessData=null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String docuno = jsonObject.getString("docuno");
            if (StringUtils.isEmpty(docuno)) {
                return AjaxResult.error("理赔案件号不能为空");
            }
            //转换成业务入参对象
            businessData =JSONObject.toJavaObject(jsonObject, ClaimFlowDTO.class);
        } catch (Exception e) {
            logger.error("理赔受理信息查询接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法");
        }

        try {
            return claimFlowService.queryClaimAcceptList(businessData);
        }catch (Exception e){
            logger.error("服务器内部处理异常:{}", e.getMessage());
            return AjaxResult.error("服务器内部处理异常,请联系运维人员");
        }
    }
    /**
     * 理赔历史账单明细查询
     * @param request
     * @return
     */
    @PostMapping("queryClaimBillService")
    public AjaxResult queryClaimBillService(HttpServletRequest request){

        //方式二：
        Map requestMap=request.getParameterMap();
        JSONArray jArray = new JSONArray();
        jArray.add(requestMap);
        String params = jArray.toString();
        logger.info("理赔历史账单明细查询接口请求获取到参数: {}", params);
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("理赔历史账单明细查询接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.error("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        ClaimFlowDTO businessData=null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String applicationCaseNo = jsonObject.getString("seqno");
            if (StringUtils.isEmpty(applicationCaseNo)) {
                return AjaxResult.error("账单序号不能为空");
            }
            //转换成业务入参对象
            businessData =JSONObject.toJavaObject(jsonObject, ClaimFlowDTO.class);
        } catch (Exception e) {
            logger.error("理赔历史账单明细查询接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.error("请求体内容不合法");
        }

        try {
            return claimFlowService.queryClaimBillDetail(businessData);
        }catch (Exception e){
            logger.error("服务器内部处理异常:{}", e.getMessage());
            return AjaxResult.error("服务器内部处理异常,请联系运维人员");
        }
    }

    /**
     * 服务项目信息查询
     * @param request
     * @return
     */
    @PostMapping("queryHealthService")
    public AjaxResult queryHealthService(HttpServletRequest request){

        return null;
    }

//    /**
//     * 案件概要信息同步接口
//     * 同步TPA受理的案件信息给GCC
//     * @param request
//     * @return
//     */
//    @PostMapping("synchronizeCaseService")
//    public AjaxResult synchronizeCaseService(HttpServletRequest request){
//
//        return null;
//    }
//
//    /**
//     * 案件状态同步接口和HCS预约修改的同步
//     * @param request
//     * @return
//     */
//    @PostMapping("synchronizeCaseStateService")
//    public AjaxResult synchronizeCaseStateService(HttpServletRequest request){
//
//        return null;
//    }

}
