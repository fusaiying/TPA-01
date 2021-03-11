package com.paic.ehis.system.task;

import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.common.core.utils.DateUtils;
import com.paic.ehis.system.api.GetProviderInfoService;
import com.paic.ehis.system.api.LognToBService;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.domain.BaseSupplierContract;
import com.paic.ehis.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务调度测试
 *
 * @author admin
 */
@Component("taskDemo")
public class TaskDemo {
    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private LognToBService lognToBService;

    @Autowired
    private GetProviderInfoService getProviderInfoService;


    public void sinoNoParams() {
        R<LoginUser> userInfoR = remoteUserService.getUserInfo("admin");
        LoginUser u = userInfoR.getData();
        System.out.println("姓名："+u.getSysUser().getUserName());
    }

    public void distributeOrder() {
        System.out.println("************");
        lognToBService.getAutoMode();
    }

    public void basesuppliercontract(@RequestBody BaseSupplierContract baseSupplierContract){
        Date nowdate=new Date();
        //转换成时间格式24小时制
        SimpleDateFormat time=new SimpleDateFormat("hh:mm:ss");
        if (time.format(nowdate).equals("24:00:00")){
            getProviderInfoService.batchTimeBaseSupplierContract(baseSupplierContract);
            System.out.println("12点通过批处理方式将合约的状态修改为失效成功！");
    }
    }
}
