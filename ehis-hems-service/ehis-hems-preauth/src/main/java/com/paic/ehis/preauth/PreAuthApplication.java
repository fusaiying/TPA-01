package com.paic.ehis.preauth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.paic.ehis.common.security.annotation.EnableCustomConfig;
import com.paic.ehis.common.security.annotation.EnableSnFeignClients;
import com.paic.ehis.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author admin
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableSnFeignClients
@SpringCloudApplication
public class PreAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(PreAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  预授权模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}