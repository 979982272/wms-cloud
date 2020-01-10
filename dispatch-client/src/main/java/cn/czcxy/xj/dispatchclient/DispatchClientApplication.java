package cn.czcxy.xj.dispatchclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "cn.czcxy.xj.*")
//@MapperScan("cn.czcxy.xj.**.mapper")
@ComponentScan("cn.czcxy.xj.*")
public class DispatchClientApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception{
        SpringApplication springApplication = new SpringApplication(DispatchClientApplication.class);
        springApplication.run(args);
        // 修改成tomcat启动
        // 程序启动入口
//        Properties properties = new Properties();
//        InputStream in = DispatchClientApplication.class.getClassLoader().getResourceAsStream("bootstrap.yml");
//        properties.load(in);
//        SpringApplication app = new SpringApplication(DispatchClientApplication.class);
//        app.setDefaultProperties(properties);
//        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(this.getClass());
        return super.configure(builder);
    }
}
