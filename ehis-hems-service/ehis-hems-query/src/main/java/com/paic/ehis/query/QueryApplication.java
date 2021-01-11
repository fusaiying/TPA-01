package com.paic.ehis.query;

import com.paic.ehis.common.security.annotation.EnableCustomConfig;
import com.paic.ehis.common.security.annotation.EnableSnFeignClients;
import com.paic.ehis.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 功能描述：综合查询模块启动类
 *
 * @author 创建人 zsy
 * @version 1.0
 * @Date 创建时间 2020/9/24  16:47
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableSnFeignClients
@SpringCloudApplication
public class QueryApplication {
    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class,args);
        System.out.println("----综合查询模块启动成功----");
    }
}
