package cn.czcxy.xj.core.platform.base.mapper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import tk.mybatis.mapper.annotation.Version;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Auther: wwh
 * @Date: 2018/7/2 0002 20:55
 * @Description:
 */
public interface CustomizeMapper<T> extends Mapper<T> {

    @Cacheable(value = "DataSourceResult", keyGenerator = "selectByExampleCache")
    default List<T> selectByExampleCache(Object example) {
        return selectByExample(example);
    }

    @CacheEvict(value = "DataSourceResult", allEntries = true)
    default int deleteByPrimaryKeyCache(Object var1) {
        return deleteByPrimaryKey(var1);
    }

    @CacheEvict(value = "DataSourceResult", allEntries = true)
    default int deleteWithVersion(T t) {
        int result = delete(t);
        if (result == 0) {
            throw new RuntimeException("数据版本已更新请刷新重试!");
        }
        return result;
    }

    default void insertSelectiveWithVersion(T t) {
        insertSelective(t);
        getVersion(t);
    }

    default int updateByPrimaryKeyWithVersion(T t) {
        int result = updateByPrimaryKey(t);
        if (result == 0) {
            throw new RuntimeException("数据版本已更新请刷新重试!");
        }
        getVersion(t);
        return result;
    }

    // 取回最新版本
    default void getVersion(T t) {
        try {
            Class c = t.getClass();
            // 如果取不出泛型的实际类型直接返回
            if (null == c) {
                return;
            }
            // 取出 @id注解的属性/@version注解的属性
            Field[] fields = c.getDeclaredFields();
            Field version = null;
            Field id = null;
            for (Field field : fields) {
                field.setAccessible(true);
                if (null != field.getAnnotation(Id.class)) {
                    id = field;
                }
                if (null != field.getAnnotation(Version.class)) {
                    version = field;
                }
            }
            // 如果存在这两个属性
            if (null == version || id == null) {
                return;
            }
            // 取回最新version
            T newObj = selectByPrimaryKey(id.get(t));
            if (null != newObj) {
                version.set(t, version.get(newObj));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default int updateByExampleWithVersion(T record, Object example) {
        int result = updateByExample(record, example);
        if (result == 0) {
            throw new RuntimeException("数据版本已更新请刷新重试!");
        }
        getVersion(record);
        return result;
    }

    default int updateByExampleSelectiveWithVersion(T record, Object example) {
        int result = updateByExampleSelective(record, example);
        if (result == 0) {
            throw new RuntimeException("数据版本已更新请刷新重试!");
        }
        getVersion(record);
        return result;
    }

    default int updateByPrimaryKeySelectiveWithVersion(T record) {
        int result = updateByPrimaryKeySelective(record);
        if (result == 0) {
            throw new RuntimeException("数据版本已更新请刷新重试!");
        }
        getVersion(record);
        return result;
    }

}
