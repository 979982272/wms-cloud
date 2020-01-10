package cn.czcxy.xj.core.platform.base.annotation;

import java.lang.annotation.*;

/**
 * 审核注解，审核操作时会自动填充审核时间和审核人
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditFlag {
    String auditTime() default "auditTime";

    String auditEmp() default "auditEmp";

    String auditEmpId() default "auditEmpId";
}
