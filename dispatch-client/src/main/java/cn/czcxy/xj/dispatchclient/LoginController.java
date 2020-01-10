package cn.czcxy.xj.dispatchclient;

import cn.czcxy.xj.core.platform.base.controller.BaseController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Auther: wwh
 * @Date: 2018/5/22 0022 21:31
 * @Description:
 */
@Controller
@RequestMapping
public class LoginController extends BaseController {

    /**
     * 默认的登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return this.view("index");
    }


    /**
     * 登陆成功后跳转的页面,框架会记住拦截之前请求的页面
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Override
    public String index(Model model, HttpServletRequest request) {
        StatusModel statusModel = new StatusModel(true, "登陆成功");
        return JSONObject.toJSON(statusModel).toString();
    }

    /**
     * 登陆状态跳转
     *
     * @return
     */
    @RequestMapping(value = "/loginStatus")
    @ResponseBody
    public StatusModel loginStatus(@RequestParam("status") boolean status,
                                   @RequestParam(value = "token", required = false) String token,
                                   @RequestParam(value = "userName", required = false) String userName) {
        // 如果失败的话带出失败信息；如果成功就直接返回了
        StatusModel statusModel = new StatusModel(status);
        if (status) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("userName", userName);
            statusModel.setOther(map);
        } else {
            String msg = "登陆失败！请检查账号密码";
            statusModel.setMsg(msg);
        }
        return statusModel;
    }


    /**
     * 系统页面
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "system", method = RequestMethod.GET)
    public String system(Model model, HttpServletRequest request,
                         @RequestParam(value = "token", required = false) String token,
                         @RequestParam(value = "userName", required = false) String userName) {
        model.addAttribute("token", token);
        model.addAttribute("userName", userName);
        return this.view("system");
    }

}
