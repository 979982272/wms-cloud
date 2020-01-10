package cn.czcxy.xj.core.platform.feign;

import feign.Contract;
import feign.Feign;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: wwh
 * @Date: 2018/5/5 0005 23:48
 * @Description:
 */
@Configuration
public class FeignConfig {
    @Bean
    public Retryer feugnRetryer() {
        return new Retryer.Default(100, 1000, 5);
    }

    /**
     * 添加请求之前的拦截器
     *
     * @return
     */
    @Bean
    public Feign.Builder feignBuilder() {
        // BasicAuthRequestInterceptor 登陆的验证的
        return Feign.builder().requestInterceptor(new FeignRequestInterceptor());
    }

    /**
     * 自定义接口解析器
     *
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new FeignContract();
    }

}
