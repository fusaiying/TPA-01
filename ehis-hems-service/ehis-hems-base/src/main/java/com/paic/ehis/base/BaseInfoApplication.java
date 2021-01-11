package com.paic.ehis.base;

import com.paic.ehis.common.security.annotation.EnableCustomConfig;
import com.paic.ehis.common.security.annotation.EnableSnFeignClients;
import com.paic.ehis.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 供应商管理Controller
 *
 * @author sino
 * @date 2020-09-23
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableSnFeignClients
@SpringCloudApplication
public class BaseInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseInfoApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  供应商管理模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
