package cn.czcxy.xj.basicclient.development.controller;

import cn.czcxy.xj.basicclient.development.entity.DataBase;
import cn.czcxy.xj.basicclient.development.mapper.DataBaseMapper;
import cn.czcxy.xj.basicclient.development.service.IDataBaseService;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
@Controller
@RequestMapping(value = "/dataBase")
public class DataBaseController {

    @Resource
    private IDataBaseService dataBaseService;

    @Resource
    private DataBaseMapper dataBaseMapper;


    @RequestMapping(value = "loadData", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult loadData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        List<DataBase> dataBases = dataBaseMapper.showTablesProvider(dataSourceRequest);
        dataSourceResult.setData(dataBases);
        dataSourceResult.setTotal(dataBases.size());
        return dataSourceResult;
    }

}
