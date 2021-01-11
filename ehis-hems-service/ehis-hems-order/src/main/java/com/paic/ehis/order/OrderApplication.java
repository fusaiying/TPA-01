package com.paic.ehis.order;

import com.paic.ehis.common.security.annotation.EnableCustomConfig;
import com.paic.ehis.common.security.annotation.EnableSnFeignClients;
import com.paic.ehis.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 服务订单管理模块
 *
 * @author admin
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableSnFeignClients
@SpringCloudApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  服务订单管理模块   ლ(´ڡ`ლ)ﾞ");
    }
}
