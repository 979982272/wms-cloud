package cn.czcxy.xj.core.platform.feign;

import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.util.ReflectionUtil;
import feign.MethodMetadata;
import feign.RequestTemplate;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自定义Feign接口解析器
 *
 * @author weihua
 * @create 2017-05-07下午 10:24
 */
public class FeignContract extends SpringMvcContract {
    // 包路径下所有包含BASEPATH注解的类
    final Set<Class<?>> CLASSSET = ReflectionUtil.getAnnotationClassSet("cn.czcxy.xj", BasePath.class);

    Map<String, String> basePathClassMap = new HashMap<>();

    @Override
    protected void processAnnotationOnClass(MethodMetadata data, Class<?> clz) {
        super.processAnnotationOnClass(data, clz);
        // 如果是有BASTPATH注解的类则存储起来
        if (CLASSSET.contains(clz)) {
            BasePath basePath = clz.getAnnotation(BasePath.class);
            basePathClassMap.put(clz.getSimpleName(), basePath.value());
        }
    }


    @Override
    protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {
        super.processAnnotationOnMethod(data, methodAnnotation, method);
        String classKey = data.configKey().split("#")[0];
        // 如果在map中存在则处理
        if (basePathClassMap.containsKey(classKey)) {
            try {
                RequestTemplate requestTemplate = data.template();
                StringBuilder url = new StringBuilder();
                url.append(basePathClassMap.get(classKey));
                url.append(requestTemplate.url());
                // 反射赋值修改url
                Field field = requestTemplate.getClass().getDeclaredField("url");
                field.setAccessible(true);
                field.set(requestTemplate, url);
            } catch (Exception e) {
                Assert.notNull(null, "在FeignContract中重写地址时出错！" + e.getMessage());
            }
        }
    }
}
