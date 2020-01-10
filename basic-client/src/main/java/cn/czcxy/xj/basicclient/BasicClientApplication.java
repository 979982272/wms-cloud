package cn.czcxy.xj.basicclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.czcxy.xj.basicclient.**.mapper")
@ComponentScan("cn.czcxy.xj.*")
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true)
// @EnableCaching
// 暂不启用redis缓存
public class BasicClientApplication {

    /**
     * 基础服务
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(BasicClientApplication.class, args);
    }
}
