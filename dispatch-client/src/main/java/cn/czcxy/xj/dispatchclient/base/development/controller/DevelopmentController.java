package cn.czcxy.xj.dispatchclient.base.development.controller;

import cn.czcxy.xj.basicserver.development.model.Development;
import cn.czcxy.xj.basicserver.development.service.IDevelopmentClientService;
import cn.czcxy.xj.core.platform.base.controller.BaseController;
import cn.czcxy.xj.core.platform.base.model.ComboModel;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.dispatchclient.base.development.service.IDevelopmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 开发管理
 *
 * @author weihua
 * @create 2017-04-23 10:04
 */
@Controller
@RequestMapping(value = "/base/development")
public class DevelopmentController extends BaseController {

    @Resource
    private IDevelopmentClientService developmentClientService;

    @Resource
    private IDevelopmentService developmentService;

    /**
     * 查询所有数据库表
     *
     * @return
     */
    @RequestMapping(value = "findDataBaseCombo")
    @ResponseBody
    public List<ComboModel> findDataBaseCombo(@RequestBody DataSourceRequest dataSourceRequest, HttpServletRequest request) {
        return developmentClientService.findDataBaseCombo(dataSourceRequest);
    }

    /**
     * 查询表中的所有字段
     *
     * @param table
     * @return
     */
    @RequestMapping(value = "findColumnInfoByTable")
    @ResponseBody
    public DataSourceResult findColumnInfoByTable(@RequestParam(value = "table", required = true) String table) {
        return developmentClientService.findColumnInfoByTable(table);
    }

    /**
     * 创建首页表格
     *
     * @param developments
     * @return
     */
    @RequestMapping(value = "/{htmlName}/createGridHtml", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel createGridHtml(@RequestBody List<Development> developments, @RequestParam(value = "src", required = true) String src, @PathVariable("htmlName") String htmlName) {
        try {
            developmentService.createGridHtml(developments, src, htmlName);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "创建成功!");
    }

    /**
     * 创建编辑页面
     *
     * @param developments
     * @param src
     * @param htmlName
     * @return
     */
    @RequestMapping(value = "/{htmlName}/createFormHtml", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel createFormHtml(@RequestBody List<Development> developments, @RequestParam(value = "src", required = true) String src, @PathVariable("htmlName") String htmlName) {
        try {
            developmentService.createFormHtml(developments, src, htmlName);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "创建成功!");
    }

    /**
     * 创建首页表格
     *
     * @param developments
     * @return
     */
    @RequestMapping(value = "/createGridVue", method = RequestMethod.POST)
    public ResponseEntity<byte[]> createGridVue(@RequestBody List<Development> developments, @RequestParam(value = "src", required = true) String src,
                                                @RequestParam("vueName") String vueName, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> responseEntity = null;
        try {
            responseEntity = developmentService.createGridVue(developments, src, vueName, request, response);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return responseEntity;
    }

    /**
     * 创建表单
     *
     * @param developments
     * @return
     */
    @RequestMapping(value = "/createFormVue", method = RequestMethod.POST)
    public ResponseEntity<byte[]> createFormVue(@RequestBody List<Development> developments, @RequestParam(value = "src", required = true) String src,
                                                @RequestParam("vueName") String vueName, HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> responseEntity = null;
        try {
            responseEntity = developmentService.createFormVue(developments, src, vueName, request, response);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return responseEntity;
    }
}
