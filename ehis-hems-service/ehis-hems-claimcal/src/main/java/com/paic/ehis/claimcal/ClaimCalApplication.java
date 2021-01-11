package com.paic.ehis.claimcal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.paic.ehis.common.security.annotation.EnableCustomConfig;
import com.paic.ehis.common.security.annotation.EnableSnFeignClients;
import com.paic.ehis.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author admin
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableSnFeignClients
@SpringCloudApplication
public class ClaimCalApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ClaimCalApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  理算及规则设置模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
