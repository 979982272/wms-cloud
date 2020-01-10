package cn.czcxy.xj.core.platform.base.annotation;

import java.lang.annotation.*;

/**
 * 字段排序注解，开启排序注解之后标示在字段之上，默认倒叙
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderByFieldFlag {
    // 加在需要排序字段的注解上
    String sortType() default "desc";
}
