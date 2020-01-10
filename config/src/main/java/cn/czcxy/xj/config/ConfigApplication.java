package cn.czcxy.xj.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

    /**
     * 配置中心
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
