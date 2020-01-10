package cn.czcxy.xj.core.platform.base.service.impl;

import cn.czcxy.xj.core.platform.base.annotation.NotDuplicate;
import cn.czcxy.xj.core.platform.base.inject.InjectBaseDependency;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.core.platform.redis.RedisUtil;
import cn.czcxy.xj.core.util.EntityUtil;
import cn.czcxy.xj.core.util.ReflectionUtil;
import cn.czcxy.xj.core.util.SpringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.Assert;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
public abstract class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, ID>, InitializingBean {
    public static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    private CustomizeMapper<T> mapper;

    public void setMapper(CustomizeMapper<T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        InjectBaseDependency.findInjectBaseMapperDependency(this);
        Assert.notNull(this, "Mapper没有设置:类名【" + this.getClass() + "】;继承了【IBaseService】的类必须拥有一个继承于【Mapper】的属性并且注解【@BaseResource】!");
    }

    @Override
    public T selectById(ID id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(ID id) throws Exception {
        // 判断类是否有deleteFlag表示，如果有则修改servcer_flag字段
        mapper.deleteByPrimaryKeyCache(id);
    }

    @Override
    public T save(T t) throws Exception {
        EntityUtil.validRequired(t);
        EntityUtil.setEntityCreateInfo(t);
        judgeDuplicate(t);
        mapper.insertSelectiveWithVersion(t);
        return t;
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public T update(T t) throws Exception {
        EntityUtil.validRequired(t);
        EntityUtil.setEntityModifyInfo(t);
        updateValidId(t);
        mapper.updateByPrimaryKeyWithVersion(t);
        return t;
    }


    @Override
    public void deleteBatchByIds(ID[] ids) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            deleteById(ids[i]);
        }
    }


    @Override
    public DataSourceResult loadData(Class c, DataSourceRequest dataSourceRequest) {
        Example example = new Example(c);
        Page<T> page = PageHelper.startPage(dataSourceRequest.getPage(), dataSourceRequest.getPageSize());
        // 将实体的属性与数据库属性进行映射
        EntityUtil.buidSqlByRequest(dataSourceRequest, example);
        // 执行查询之后会自动把值设置到page中
        RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);
        page = (Page<T>) mapper.selectByExampleCache(example);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(page);
        dataSourceResult.setTotal(page.getTotal());
        return dataSourceResult;
    }

    /**
     * 判重
     *
     * @param entity
     * @throws Exception
     */
    private void judgeDuplicate(Object entity) throws Exception {
        String entityName = entity.getClass().getName();
        Class entityClass = Class.forName(entityName);
        Map<String, Object> judgeDuplicateFiledMap = new HashMap<>();
        Field[] fields = entityClass.getDeclaredFields();
        // 取出主键和NotDuplicate注解的字段
        for (Field f : fields) {
            f.setAccessible(true);
            if (null != f.getAnnotation(Id.class)) {
                judgeDuplicateFiledMap.put(f.getName(), f.get(entity));
            }
            if (null != f.getAnnotation(NotDuplicate.class)) {
                judgeDuplicateFiledMap.put(f.getName(), f.get(entity));
            }
        }
        // 循环判断
        for (Map.Entry<String, Object> m : judgeDuplicateFiledMap.entrySet()) {
            if (null != m.getValue() && CollectionUtils.isNotEmpty(selectSaveValidT(entityClass, m.getValue(), m.getKey()))) {
                throw new Exception("值重复！列【" + m.getKey() + "】,值【" + m.getValue() + "】");
            }
        }

    }

    /**
     * 更新的时候验证是否存在这条记录
     *
     * @param t
     * @throws Exception
     */
    public void updateValidId(Object t) throws Exception {
        String entityName = t.getClass().getName();
        Class entityClass = Class.forName(entityName);
        Map<String, Object> judgeDuplicateFiledMap = new HashMap<>();
        Field[] fields = entityClass.getDeclaredFields();
        Object idValue = null;
        String idColumn = null;
        // 取出主键和NotDuplicate注解的字段
        for (Field f : fields) {
            f.setAccessible(true);
            if (null != f.getAnnotation(Id.class)) {
                idValue = f.get(t);
                idColumn = f.getName();
            }
            if (null != f.getAnnotation(NotDuplicate.class)) {
                f.getAnnotation(Column.class);
                judgeDuplicateFiledMap.put(f.getName(), f.get(t));
            }
        }
        List list = selectSaveValidT(entityClass, idValue, idColumn);
        if (CollectionUtils.isEmpty(list)) {
            throw new Exception("通过【" + idValue + "】查询不到对应的记录！");
        }
        for (Map.Entry<String, Object> m : judgeDuplicateFiledMap.entrySet()) {
            if (null != m.getValue() && CollectionUtils.isNotEmpty(selectUpdateValidT(entityClass, m.getValue(), m.getKey(), idValue, idColumn))) {
                throw new Exception("值重复！列【" + m.getKey() + "】,值【" + m.getValue() + "】");
            }
        }
    }

    /**
     * 查询更新时候的字段验证
     *
     * @param entityClass
     * @param value
     * @param column
     * @param idValue
     * @param idColumn
     * @return
     */
    private List selectUpdateValidT(Class entityClass, Object value, String column, Object idValue, String idColumn) {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(column, value);
        criteria.andNotEqualTo(idColumn, idValue);
        List list = mapper.selectByExample(example);
        return list;
    }

    /**
     * 通过id进行查询
     *
     * @param entityClass
     * @param value
     * @return
     * @throws Exception
     */
    private List selectSaveValidT(Class entityClass, Object value, String column) throws Exception {
        Example example = new Example(entityClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(column, value);
        List list = mapper.selectByExample(example);
        return list;
    }

    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }
}
