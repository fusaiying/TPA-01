package com.paic.ehis.auth;

import com.paic.ehis.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 认证授权中心
 * 
 *
 */
@EnableRyFeignClients
@SpringCloudApplication
public class AuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AuthApplication.class, args);

    }
}
