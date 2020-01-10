package cn.czcxy.xj.crmclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
// 设置feign扫描路径
@EnableFeignClients(basePackages = "cn.czcxy.xj.*")
// 开启mybatis扫描
@MapperScan("cn.czcxy.xj.crmclient.**.mapper")
// 扫描组件路径
@ComponentScan("cn.czcxy.xj.*")
// 开启事务管理
@EnableTransactionManagement
// 开始显示事务配置
@EnableAspectJAutoProxy(exposeProxy = true)
public class CrmClientApplication {

    /**
     * 进销存服务
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CrmClientApplication.class, args);
    }
}
