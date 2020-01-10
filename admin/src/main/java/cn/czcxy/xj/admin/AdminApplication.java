package cn.czcxy.xj.admin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {

    /**
     * 项目管理
     * http://localhost:8088
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
