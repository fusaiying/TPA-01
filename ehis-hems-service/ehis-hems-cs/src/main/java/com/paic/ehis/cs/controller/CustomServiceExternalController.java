package com.paic.ehis.cs.controller;

import com.alibaba.fastjson.JSONObject;
import com.paic.ehis.common.core.utils.SecurityUtils;
import com.paic.ehis.common.core.utils.StringUtils;
import com.paic.ehis.common.core.web.controller.BaseController;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.common.core.web.page.TableDataInfo;
import com.paic.ehis.common.log.annotation.Log;
import com.paic.ehis.common.log.enums.BusinessType;
import com.paic.ehis.cs.domain.*;
import com.paic.ehis.cs.domain.dto.*;
import com.paic.ehis.cs.domain.vo.*;
import com.paic.ehis.cs.service.*;
import com.paic.ehis.cs.utils.CodeEnum;
import com.paic.ehis.cs.utils.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    //添加到配置文件中
    /**
     * 信息需求，投诉
     */
    private String baseTypes="informationApplication,complainApplication";
    /**
     * VIP门诊预约
     * 门诊直接结算
     * 特殊门诊直接结算服务
     * 住院直结结算
     * 门诊中医治疗直接结算
     */
    private String incrementTypes="vipClinic,clinicDirectPay";
    private String incrementOutTypes="demesticSecondCure,demesticAccidentAssitance,overseaAccidentAssitance";


    /**
     * 基础受理接口
     * By Louis
     *
     * @param request
     * @return
     */
    @Transactional
    @PostMapping("/baseAccept")
    public AjaxResult addBaseAccept(HttpServletRequest request) {
        //请求参数获取
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            logger.info("基础受理接口请求获取到参数: {}->{}", parameterName, parameterValue);
        }
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("基础受理接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.success("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String type = jsonObject.getString("type");
            if (StringUtils.isEmpty(type)) {
                return AjaxResult.success("服务类型不能为空");
            }
            String applicationCaseNo = jsonObject.getString("applicationCaseNo");
            if (StringUtils.isEmpty(applicationCaseNo)) {
                return AjaxResult.success("受理案件号不能为空");
            }
            logger.info("基础受理接口请求中type为:{}", type);
            //判断类型是否是可处理的类型
            String[] typeArr=baseTypes.split(",");
            List<String> typeList= Arrays.asList(typeArr);
            if(!typeList.contains(type)){
                return AjaxResult.success("接口请求中type为不可处理类型");
            }
            //根据类型不同进行必传条件校验


            //转换成业务入参对象
            BasicServiceAppilcation businessData =JSONObject.toJavaObject(jsonObject, BasicServiceAppilcation.class);
            return toAjax(iWorkOrderAcceptService.insertBasicServiceApplication(businessData));

        } catch (Exception e) {
            logger.error("基础受理接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.success("请求体内容不合法");
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
    @Transactional
    @PostMapping("/incrementAccept")
    public AjaxResult addIncrementAccept(HttpServletRequest request) {
        //请求参数获取
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            logger.info("增值服务受理接口请求获取到参数: {}->{}", parameterName, parameterValue);
        }
        //请求体获取
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        logger.info("增值服务受理接口请求获取到Body内容:{}", body);
        if (StringUtils.isEmpty(body)) {
            return AjaxResult.success("请求体内容不能为空");
        }
        //请求体必传信息校验，请求体是Json的
        try {
            JSONObject jsonObject = JSONObject.parseObject(body);
            String type = jsonObject.getString("type");
            if (StringUtils.isEmpty(type)) {
                return AjaxResult.success("服务类型不能为空");
            }
            String applicationCaseNo = jsonObject.getString("applicationCaseNo");
            if (StringUtils.isEmpty(applicationCaseNo)) {
                return AjaxResult.success("受理案件号不能为空");
            }
            logger.info("增值服务受理接口请求中type为:{}", type);
            //判断类型是否是可处理的类型
            String[] typeArr=incrementTypes.split(",");
            List<String> typeList= Arrays.asList(typeArr);
            String[] outTypeArr=incrementOutTypes.split(",");
            List<String> outTypeList= Arrays.asList(outTypeArr);
            if(outTypeList.contains(type)){
                //将数据转入医疗模块，待医疗提供 TODO

                return AjaxResult.success("受理成功");
            }
            if(!typeList.contains(type)){
                return AjaxResult.success("接口请求中type为不可处理类型");
            }
            //根据类型不同进行必传条件校验


            //转换成业务入参对象
            IncrementApplication businessData =JSONObject.toJavaObject(jsonObject, IncrementApplication.class);
            return toAjax(iWorkOrderAcceptService.insertIncrementServiceApplication(businessData));

        } catch (Exception e) {
            logger.error("基础受理接口，处理请请求体异常:{}", e.getMessage());
            return AjaxResult.success("请求体内容不合法");
        }

    }


}
