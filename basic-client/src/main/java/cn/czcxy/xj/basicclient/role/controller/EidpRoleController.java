package cn.czcxy.xj.basicclient.role.controller;

import cn.czcxy.xj.basicclient.role.entity.EidpRoleMenu;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import cn.czcxy.xj.basicclient.role.service.IEidpRoleService;
import cn.czcxy.xj.basicclient.role.entity.EidpRole;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/role")
public class EidpRoleController extends BaseCurdController<EidpRole, java.lang.Integer> {

    @Resource
    @BaseResource
    private IEidpRoleService eidpRoleService;

    /**
     * 分配菜单
     *
     * @param ids
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/assignMenu")
    public StatusModel assignMenu(@RequestParam("ids") Integer[] ids,
                                  @RequestParam("roleId") Integer roleId) {
        try {
            eidpRoleService.assignMenu(ids, roleId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "菜单分配成功!");
    }

    /**
     * 获取角色与菜单关联
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/findEidpRoleMenusByRoleId")
    List<EidpRoleMenu> findEidpRoleMenusByRoleId(@RequestParam("roleId") Integer roleId) {
        List<EidpRoleMenu> eidpRoleMenus = null;
        try {
            eidpRoleMenus=eidpRoleService.findEidpRoleMenusByRoleId(roleId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            eidpRoleMenus = new ArrayList<>();
        }
        return eidpRoleMenus;
    }
}
