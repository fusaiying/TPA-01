package com.paic.ehis.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.paic.ehis.common.security.annotation.EnableSnFeignClients;

/**
 * 认证授权中心
 *
 * @author admin
 */
@EnableSnFeignClients
@SpringCloudApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  授权中心启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
