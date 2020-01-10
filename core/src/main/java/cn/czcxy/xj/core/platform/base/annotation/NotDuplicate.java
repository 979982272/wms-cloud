package cn.czcxy.xj.core.platform.base.annotation;

import java.lang.annotation.*;

/**
 * @Auther: wwh
 * @Date: 2018/7/1 0001 16:18
 * @Description: 判重注解，该注解的字段会自动判重
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotDuplicate {
}
