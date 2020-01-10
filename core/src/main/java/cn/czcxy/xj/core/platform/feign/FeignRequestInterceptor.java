package cn.czcxy.xj.core.platform.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 请求之前的拦截器
//        System.out.println("请求:"+requestTemplate.url());
//        System.out.println("请求方式:"+requestTemplate.method());
    }
}
