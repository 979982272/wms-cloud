package cn.czcxy.xj.core.platform.base.service;

import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


public interface IBaseClientService<T> {

    /**
     * 载入数据
     *
     * @param dataSourceRequest
     * @return
     */
    @RequestMapping(value = "/loadData", method = RequestMethod.GET)
    public DataSourceResult loadData(@RequestBody DataSourceRequest dataSourceRequest);

    /**
     * 查询所有数据
     *
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public List<T> selectAll();

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/selectById", method = RequestMethod.GET)
    public T selectById(@PathVariable(value = "id") String id);

    /**
     * 通过主键id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/deleteById", method = RequestMethod.POST)
    public StatusModel deleteById(@PathVariable("id") String id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteBatchByIds", method = RequestMethod.POST)
    public StatusModel deleteBatchByIds(@RequestParam(value = "ids") String[] ids);

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public StatusModel save(@RequestBody T t);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public StatusModel update(@RequestBody T t);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/getDataInfoById", method = RequestMethod.POST)
    public StatusModel getDataInfoById(@PathVariable(value = "id") String id);

    /**
     * 条件搜索
     *
     * @param example
     * @return
     */
    @RequestMapping(value = "/selectByExample", method = RequestMethod.POST)
    List<T> selectByExample(@RequestBody Example example);

}
