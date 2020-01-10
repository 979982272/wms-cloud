package cn.czcxy.xj.dispatchclient.base.development.controller;

import cn.czcxy.xj.basicserver.development.service.IDataBaseClientService;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.dispatchclient.base.development.service.IDataBaseService;
import cn.czcxy.xj.dispatchclient.properties.DevelopmentProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/12/24 0024.
 */
@Controller
@RequestMapping(value = "/base/dataBase")
public class DataBaseController {

    @Resource
    private IDataBaseClientService dataBaseClientService;

    @Resource
    private IDataBaseService dataBaseService;

    @RequestMapping(value = "loadData", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult loadData(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        return dataBaseClientService.loadData(dataSourceRequest);
    }

    /**
     * 创建实体
     *
     * @param ids
     * @param pack
     * @return
     */
    @RequestMapping(value = "/createEntityCode", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel createEntityCode(@RequestParam("tables") String[] ids, @RequestParam("packs") String[] pack) {
        try {
            dataBaseService.createEntityCode(ids, pack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StatusModel(true, "");
    }

}
