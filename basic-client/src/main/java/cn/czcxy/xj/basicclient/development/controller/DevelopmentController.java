package cn.czcxy.xj.basicclient.development.controller;

import cn.czcxy.xj.basicclient.development.entity.DataBase;
import cn.czcxy.xj.basicclient.development.entity.Development;
import cn.czcxy.xj.basicclient.development.mapper.DataBaseMapper;
import cn.czcxy.xj.basicclient.development.mapper.DevelopmentMapper;
import cn.czcxy.xj.basicclient.development.service.IDevelopmentService;
import cn.czcxy.xj.core.platform.base.controller.BaseController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 开发管理
 *
 * @author weihua
 * @create 2017-04-23 10:04
 */
@RestController
@RequestMapping(value = "/development")
public class DevelopmentController extends BaseController {

    @Resource
    private DataBaseMapper dataBaseMapper;

    @Resource
    private DevelopmentMapper developmentMapper;

    @Resource
    private IDevelopmentService developmentService;


    /**
     * 查询所有数据库表
     *
     * @return
     */
    @RequestMapping(value = "findDataBaseCombo")
    public List<ComboModel> findDataBaseCombo(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        List<DataBase> dataBases = dataBaseMapper.showTables();
        String[][] array = new String[dataBases.size() + 1][dataBases.size() + 1];
        for (int i = 0; i < dataBases.size(); i++) {
            array[i][0] = dataBases.get(i).getTableName();
            array[i][1] = dataBases.get(i).getTableName();
        }
        return ComboModel.convert(array, true);
    }

    /**
     * 查询表中的所有字段
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "findColumnInfoByTable")
    public DataSourceResult findColumnInfoByTable(@RequestParam(value = "table", required = true) String table) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        List<Development> developments = developmentMapper.findAllColumn(table);
        int sort = 10;
        for (Development development : developments) {
            development.setWidth(120);
            development.setSort(sort);
            sort = sort + 10;
        }
        dataSourceResult.setData(developments);
        dataSourceResult.setTotal(developments.size());
        return dataSourceResult;
    }

    /**
     * 查询表的主键信息
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "findPriByTable", method = RequestMethod.POST)
    public Map<String, Object> findPriByTable(@RequestParam(value = "table", required = true) String table) {
        return developmentMapper.findPriByTable(table);
    }

}
