package cn.czcxy.xj.core.platform.base.controller;

import cn.czcxy.xj.core.platform.base.model.StatusModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础控制器
 *
 * @author weihua
 * @create 2017-04-23 9:38
 */
public abstract class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public String view(String path) {
        String currentViewPrefix = "";
        RequestMapping requestMapping = (RequestMapping) AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }
        return currentViewPrefix + "/" + path;
    }

    /**
     * 设置项目路径
     *
     * @param model
     * @param request
     */
    @ModelAttribute
    public void setModel(Model model, HttpServletRequest request) {
        model.addAttribute("ctx", request.getContextPath());
        model.addAttribute("token", request.getParameter("token"));
        model.addAttribute("title", "仓储管理系统");
    }


    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        return this.view("index");
    }


    /**
     * 编辑页面与创建页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editView(Model model, @RequestParam(value = "id", required = false) String id) {
        model.addAttribute("id", id);
        return this.view("edit");
    }

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    public StatusModel getDefaultInfo() {
        Map<String, Object> info = new HashMap<>();
        StatusModel statusModel = new StatusModel(true, info);
        return statusModel;
    }
}
