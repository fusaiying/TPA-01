package com.paic.ehis.system.task;

import com.paic.ehis.common.core.domain.R;
import com.paic.ehis.system.api.LognToBService;
import com.paic.ehis.system.api.RemoteUserService;
import com.paic.ehis.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void sinoNoParams() {
        R<LoginUser> userInfoR = remoteUserService.getUserInfo("admin");
        LoginUser u = userInfoR.getData();
        System.out.println("姓名："+u.getSysUser().getUserName());
    }

    public void distributeOrder() {
        lognToBService.getAutoMode();
    }
}
