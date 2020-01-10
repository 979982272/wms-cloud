package cn.czcxy.xj.core.platform.base.inject;/**
 * Created by Administrator on 2017/2/16 0016.
 */

import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import tk.mybatis.mapper.common.Mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解基础依赖工具类
 *
 * @author wwh
 * @create 2017-02-16 21:58
 */
public class InjectBaseDependency<T> {

    /**
     * 注入IBaseService
     *
     * @param baseController
     */
    public static void findInjectBaseServiceDependency(BaseCurdController<?,?> baseController) {
        Object result = findInjectBaseServiceDependency(baseController.getClass().getDeclaredFields(), baseController);
        baseController.setBaseService((IBaseService) result);
    }

    /**
     * 实现依赖注入
     *
     * @param
     * @return
     */
    private static Object findInjectBaseServiceDependency(Field[] fields, Object taget) {
        Object result = null;
        List baseResources = new ArrayList();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof BaseResource) {
                        Object o = ReflectionUtils.getField(field, taget);
                        baseResources.add(o);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.notNull(null, "依赖注入失败!" + e.getMessage());
        }

        if (CollectionUtils.isNotEmpty(baseResources)) {
            if (baseResources.size() > 1) {
                throw new IllegalArgumentException("IBaseService没有设置:类名【" + taget + "】;继承了【BaseController】的类只能拥有一个继承于【IBaseService】并且注解【@BaseResource】的属性!");
            }
            // 这里暂时没有去判断添加了BaseResource注解的属性类型
            result = baseResources.get(0);
        }
        return result;
    }

    /**
     * 注入IBaseFeignService
     *
     * @param baseController
     */
    public static void findInjectBaseServiceDependency(BaseCurdClienController<?> baseController) {
        Object result = findInjectBaseServiceDependency(baseController.getClass().getDeclaredFields(), baseController);
        baseController.setBaseService((IBaseClientService) result);
    }

    /**
     * 判断mybatis注入
     *
     * @param baseService
     */
    public static void findInjectBaseMapperDependency(BaseServiceImpl<?, ?> baseService) {
        // InitializingBean 的实现类，在spring容器初始化完成之后将注解资源注入
        Field[] fields = baseService.getClass().getDeclaredFields();
        List baseResources = new ArrayList();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof BaseResource) {
                    Object o = ReflectionUtils.getField(field, baseService);
                    baseResources.add(o);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(baseResources)) {
            if (baseResources.size() > 1) {
                throw new IllegalArgumentException("Mapper没有设置:类名【" + baseService.getClass() + "】;继承了【IBaseService】的类只能拥有一个继承于【Mapper】并且注解【@BaseResource】的属性!");
            }
            // 这里暂时没有去判断添加了BaseResource注解的属性类型
            baseService.setMapper((CustomizeMapper) baseResources.get(0));
        }

    }
}
