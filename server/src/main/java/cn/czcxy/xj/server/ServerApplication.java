package cn.czcxy.xj.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerApplication {

    /**
     * 注册中心
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
