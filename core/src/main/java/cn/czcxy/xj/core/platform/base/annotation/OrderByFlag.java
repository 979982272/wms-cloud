package cn.czcxy.xj.core.platform.base.annotation;

import java.lang.annotation.*;

/**
 * 排序注解，标示之后会自动根据创建标示时间排序
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderByFlag {
}
