package cn.czcxy.xj.core.platform.base.annotation;

import java.lang.annotation.*;

/**
 * feign类中的基础路径
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BasePath {
    String value();
}
