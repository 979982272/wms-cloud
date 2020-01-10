package cn.czcxy.xj.dispatchclient.base.menu;

import cn.czcxy.xj.basicserver.emp.model.EidpEmp;
import cn.czcxy.xj.basicserver.menu.model.EidpMenu;
import cn.czcxy.xj.basicserver.menu.model.EidpMenuTree;
import cn.czcxy.xj.basicserver.menu.model.Node;
import cn.czcxy.xj.basicserver.menu.service.IEidpMenuClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.redis.RedisUtil;
import cn.czcxy.xj.core.util.tree.TreeUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/base/menu")
public class EidpMenuClienController extends BaseCurdClienController<EidpMenu> {

    @Autowired
    @BaseResource
    private IEidpMenuClientService eidpMenuService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取所有菜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getMenusByEmpOrOrganization", method = RequestMethod.POST)
    @ResponseBody
    public List<Node<Integer, String>> getMenusByEmpOrOrganization(@RequestParam(value = "token", required = false) String token,
                                                                   @RequestParam(value = "organizationId", required = false) Integer organizationId) throws Exception {
        List<Node<Integer, String>> nodes = (List<Node<Integer, String>>) redisUtil.get(token + "menu");
        if (CollectionUtils.isEmpty(nodes)) {
            EidpEmp eidpEmp = (EidpEmp) redisUtil.get(token);
            if (eidpEmp == null) {
                throw new RuntimeException("redis缓存异常！");
            }
            nodes = eidpMenuService.getMenusByEmpOrOrganization(eidpEmp.getId(), null, token);
        }
        return nodes;
    }


    /**
     * 载入所有数据
     *
     * @return
     */
    @RequestMapping(value = "loadAllData", method = RequestMethod.GET)
    @ResponseBody
    public String loadAllData() {
        List<EidpMenu> eidpMenus = eidpMenuService.selectAll();
        return JSON.toJSONString(eidpMenus, SerializerFeature.WriteMapNullValue);
    }

    // 添加下一级
    @RequestMapping(value = "/addNext", method = RequestMethod.GET)
    public String addNext(Model model, @RequestParam(value = "parentId", required = false) String id) {
        EidpMenu eidpMenu = eidpMenuService.selectById(id);
        model.addAttribute("parentId", id);
        model.addAttribute("level", eidpMenu.getLevel() + 1);
        return this.view("edit");
    }

    /**
     * 载入所有数据
     *
     * @return
     */
    @RequestMapping(value = "loadAllDataByChildren")
    @ResponseBody
    public String loadAllDataByChildren() {
        List<EidpMenu> eidpMenus = eidpMenuService.selectAll();
        String result = JSON.toJSONString(eidpMenus, SerializerFeature.WriteMapNullValue);
        List<EidpMenuTree> eidpMenuTrees = JSONObject.parseArray(result, EidpMenuTree.class);
        TreeUtils<EidpMenuTree, Integer> treeUtils = new TreeUtils<EidpMenuTree, Integer>();
        return JSON.toJSONString(treeUtils.getChildTreeObjects(eidpMenuTrees, null), SerializerFeature.WriteMapNullValue);
    }
}
