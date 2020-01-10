package cn.czcxy.xj.dispatchclient.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 适配器
 */
// 暂不启用拦截器配置
@Configuration
public class DispatchConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/ftl/**")
                .addResourceLocations("/static/**")
                .addResourceLocations("/page/**");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DispatchInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/loginOut")
                .excludePathPatterns("/loginStatus");
        super.addInterceptors(registry);
    }
}
