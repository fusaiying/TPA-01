package com.paic.ehis.claimflow;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.paic.ehis.common.security.annotation.EnableCustomConfig;
import com.paic.ehis.common.security.annotation.EnableSnFeignClients;
import com.paic.ehis.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author duwei
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableSnFeignClients
@SpringCloudApplication
public class ClaimFlowApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ClaimFlowApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  理赔流程模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
