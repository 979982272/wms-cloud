package cn.czcxy.xj.core.platform.base.controller;

import cn.czcxy.xj.core.platform.base.entity.BaseEntity;
import cn.czcxy.xj.core.platform.base.inject.InjectBaseDependency;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.core.util.HttpServletRequestUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增删改查控制器
 *
 * @author weihua
 * @create 2017-04-23 9:38
 */
public abstract class BaseCurdController<T, ID extends Serializable> extends BaseController implements InitializingBean {
    public static final Logger logger = LoggerFactory.getLogger(BaseCurdController.class);
    private IBaseService<T, ID> baseService;

    public void setBaseService(IBaseService<T, ID> baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(value = "loadData", method = RequestMethod.GET)
    @ApiOperation(value = "载入数据")
    public DataSourceResult loadData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        Type t = this.getClass().getGenericSuperclass();
        String loadClass = null;
        Class c = null;
        try {
            // 获取泛型的实际类型
            if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
                for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
                    loadClass = t1.getTypeName();
                    for (Annotation a : Class.forName(loadClass).getAnnotations()) {
                        if (a instanceof Table) {
                            c = Class.forName(loadClass);
                            break;
                        }
                    }
                }
            }
            dataSourceResult = baseService.loadData(c, dataSourceRequest);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        InjectBaseDependency.findInjectBaseServiceDependency(this);
        Assert.notNull(this.baseService, "IBaseService没有设置:类名【" + this.getClass() + "】;继承了【BaseController】的类必须拥有一个继承于【IBaseService】的属性并且注解【@BaseResource】!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/selectById", method = RequestMethod.GET)
    @ApiOperation(value = "通过id查询")
    public T selectById(@PathVariable("id") ID id) {
        T t = null;
        try {
            t = baseService.selectById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有数据")
    public List<T> selectAll() {
        List<T> t = null;
        try {
            t = baseService.selectAll();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 通过主键id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/deleteById", method = RequestMethod.POST)
    @ApiOperation(value = "通过主键id删除")
    public StatusModel deleteById(@PathVariable("id") ID id) {
        try {
            baseService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "删除成功");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
    @ApiOperation(value = "批量删除")
    public StatusModel deleteBatchByIds(@RequestParam(value = "ids") ID[] ids) {
        try {
            baseService.deleteBatchByIds(ids);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "删除成功");
    }

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ApiOperation(value = "保存")
    public StatusModel create(@RequestBody T t) {
        T entity = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            entity = baseService.save(t);
            map.put("entity", entity);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "保存成功!", map);
    }

    /**
     * 更新
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "更新")
    public StatusModel update(@RequestBody T t) {
        T entity = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            entity = baseService.update(t);
            map.put("entity", entity);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "更新成功!", map);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ApiOperation(value = "通过id查询")
    public StatusModel getDataInfoById(@PathVariable(value = "id") ID id) {
        StatusModel statusModel = null;
        try {
            T t = baseService.selectById(id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("modelData", t);
            statusModel = new StatusModel(true, map);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            statusModel = new StatusModel(false, e.getMessage());
        }
        return statusModel;
    }

    /**
     * 条件搜索
     *
     * @param example
     * @return
     */
    @RequestMapping(value = "/selectByExample", method = RequestMethod.POST)
    List<T> selectByExample(@RequestBody Example example) {
        List<T> dataList = new ArrayList<>();
        try {
            dataList = baseService.selectByExample(example);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataList;
    }

}
