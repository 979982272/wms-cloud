package cn.czcxy.xj.dispatchclient.base.role;

import cn.czcxy.xj.basicserver.menu.model.Node;
import cn.czcxy.xj.basicserver.menu.service.IEidpMenuClientService;
import cn.czcxy.xj.basicserver.role.model.EidpRole;
import cn.czcxy.xj.basicserver.role.model.EidpRoleMenu;
import cn.czcxy.xj.basicserver.role.service.IEidpRoleClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/base/role")
public class EidpRoleClientController extends BaseCurdClienController<EidpRole> {

    @Resource
    @BaseResource
    private IEidpRoleClientService eidpRoleService;

    @Autowired
    private IEidpMenuClientService eidpMenuService;

    /**
     * 分配菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}/assignMenu", method = RequestMethod.GET)
    public String assignMenu(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return this.view("assignMenu");
    }

    /**
     * 分配菜单
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/{roleId}/assignMenu", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel assignMenuAction(@RequestParam("ids") Integer[] ids, @PathVariable("roleId") Integer roleId) {
        return eidpRoleService.assignMenu(ids, roleId);
    }

    /**
     * 获取菜单
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{roleId}/getMenus", method = RequestMethod.POST)
    @ResponseBody
    public List<Node<Integer, String>> getMenus(@PathVariable("roleId") Integer roleId) throws Exception {
        List<Node<Integer, String>> nodes = eidpMenuService.getMenus();
        List<EidpRoleMenu> eidpRoleMenus = eidpRoleService.findEidpRoleMenusByRoleId(roleId);
        Map<Integer, Integer> roleMenuMap = new HashMap<>();
        for (EidpRoleMenu eidpRoleMenu : eidpRoleMenus) {
            roleMenuMap.put(eidpRoleMenu.getMenuId(), eidpRoleMenu.getRoleId());
        }
        setNodeParam(nodes, roleMenuMap);
        return nodes;
    }

    /**
     * 设置结点参数
     *
     * @param nodes
     * @param roleMenuMap
     */
    private void setNodeParam(List<Node<Integer, String>> nodes, Map<Integer, Integer> roleMenuMap) {
        if (nodes != null) {
            for (Node<Integer, String> node : nodes) {
                if (roleMenuMap.containsKey(node.getId())) {
                    node.setExpanded(true);
                    node.setChecked("checked");
                }
                setNodeParam(node.getItems(), roleMenuMap);
            }
        }
    }
}
