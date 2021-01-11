package com.paic.ehis.product;

import com.paic.ehis.common.security.annotation.EnableCustomConfig;
import com.paic.ehis.common.security.annotation.EnableSnFeignClients;
import com.paic.ehis.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 服务产品管理
 *
 * @author sino
 * @date 2020-09-23
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableSnFeignClients
@SpringCloudApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  服务产品管理模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
