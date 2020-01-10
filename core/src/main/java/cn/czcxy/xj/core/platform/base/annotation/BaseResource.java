package cn.czcxy.xj.core.platform.base.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/2/12 0012.
 * 公共服务的资源注解，controller和service都需要用到
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseResource {
}
