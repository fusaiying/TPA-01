package com.paic.ehis.system.task;

import com.alibaba.fastjson.JSON;
import com.paic.ehis.common.core.constant.HttpStatus;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.common.core.web.domain.AjaxResult;
import com.paic.ehis.system.api.CustomServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("CustomServiceTask")
public class CustomServiceTask {
    private static Logger logger = LoggerFactory.getLogger(CustomServiceTask.class);
    @Autowired
    private CustomServiceService customServiceService;

    public void invalidWeek() {
        String invalidDate = DateUtils.getDate();
        logger.info("客服信息需求每周系统数据自动抽检开始，当前日期{}", invalidDate);

        AjaxResult ajaxResult = customServiceService.batchAcceptVo(invalidDate);
        String jsonCode = JSON.toJSONString(ajaxResult.get(AjaxResult.CODE_TAG));
        String dealCode = JSON.parseObject(jsonCode,String.class);
        if(String.valueOf(HttpStatus.SUCCESS).equals(dealCode)){
            logger.info("客服信息需求每周系统数据自动抽检结束，结果：成功！");
        }else{
            String msg=JSON.toJSONString(ajaxResult.get(AjaxResult.MSG_TAG));
            logger.error("客服信息需求每周系统数据自动抽检结束，结果：失败，原因：{}");
        }
    }
    public void invalidMonth() {
        String invalidDate = DateUtils.getDate();
        logger.info("客服信息需求每月系统数据自动抽检开始，当前日期{}", invalidDate);
        AjaxResult ajaxResult = customServiceService.batchAcceptVomonth(invalidDate);
        String jsonCode = JSON.toJSONString(ajaxResult.get(AjaxResult.CODE_TAG));
        String dealCode = JSON.parseObject(jsonCode,String.class);
        if(String.valueOf(HttpStatus.SUCCESS).equals(dealCode)){
            logger.info("客服信息需求每月系统数据自动抽检结束，结果：成功！");
        }else{
            String msg=JSON.toJSONString(ajaxResult.get(AjaxResult.MSG_TAG));
            logger.error("客服信息需求每月系统数据自动抽检结束，结果：失败，原因：{}");
        }
    }

    public void invalidteleive() {
        String invalidDate = DateUtils.getDate();
        logger.info("每天12点将预约案件已处理改为已完成开始，当前日期{}", invalidDate);
        AjaxResult ajaxResult = customServiceService.invalidAcceptDetailInfo(invalidDate);
        String jsonCode = JSON.toJSONString(ajaxResult.get(AjaxResult.CODE_TAG));
        String dealCode = JSON.parseObject(jsonCode,String.class);
        if(String.valueOf(HttpStatus.SUCCESS).equals(dealCode)){
            logger.info("每天12点将预约案件已处理改为已完成结束，结果：成功！");
        }else{
            String msg=JSON.toJSONString(ajaxResult.get(AjaxResult.MSG_TAG));
            logger.error("每天12点将预约案件已处理改为已完成，结果：失败，原因：{}");
        }
    }

    public void invalidcomplaint () {
        String invalidDate = DateUtils.getDate();
        logger.info("每周一凌晨4点将投诉案件进行抽检，当前日期{}", invalidDate);
        AjaxResult ajaxResult = customServiceService.selectInvalidQiaMondayFour(invalidDate);
        String jsonCode = JSON.toJSONString(ajaxResult.get(AjaxResult.CODE_TAG));
        String dealCode = JSON.parseObject(jsonCode,String.class);
        if(String.valueOf(HttpStatus.SUCCESS).equals(dealCode)){
            logger.info("每周一凌晨4点将投诉案件进行抽检结束，结果：成功！");
        }else{
            String msg=JSON.toJSONString(ajaxResult.get(AjaxResult.MSG_TAG));
            logger.error("每周一凌晨4点将投诉案件进行抽检已完成，结果：失败，原因：{}");
        }
    }
}
