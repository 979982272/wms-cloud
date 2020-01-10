package cn.czcxy.xj.core.platform.base.controller;

import cn.czcxy.xj.core.platform.base.inject.InjectBaseDependency;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.util.HttpServletRequestUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: wwh
 * @Date: 2018/6/10 0010 11:43
 * @Description:
 */
public abstract class BaseCurdClienController<T> extends BaseController implements InitializingBean {

    private IBaseClientService<T> baseService;

    public void setBaseService(IBaseClientService<T> baseService) {
        this.baseService = baseService;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        InjectBaseDependency.findInjectBaseServiceDependency(this);
        Assert.notNull(this.baseService, "IBaseFeignService没有设置:类名【" + this.getClass() + "】;继承了【BaseCurdClienController】的类必须拥有一个继承于【IBaseService】的属性并且注解【@BaseResource】!");
    }

    @RequestMapping(value = "loadData", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult loadData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        HttpServletRequestUtils.transRequestToDataSourceRequest(dataSourceRequest, request);
        return baseService.loadData(dataSourceRequest);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/selectById", method = RequestMethod.GET)
    @ResponseBody
    public T selectById(@PathVariable("id") String id) {
        return baseService.selectById(id);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    @ResponseBody
    public List<T> selectAll() {
        return baseService.selectAll();
    }

    /**
     * 通过主键id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/deleteById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel deleteById(@PathVariable(value = "id") String id) {
        return baseService.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel deleteBatchByIds(@RequestParam(value = "ids") String ids) {
        return baseService.deleteBatchByIds(ids.split(","));
    }

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel create(@RequestBody T t) {
        return baseService.save(t);
    }

    /**
     * 更新
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel update(@RequestBody T t) {
        return baseService.update(t);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id) {
        return baseService.getDataInfoById(id);
    }
}
