package cn.czcxy.xj.basicserver.role.service;

import cn.czcxy.xj.basicserver.role.model.EidpRole;
import cn.czcxy.xj.basicserver.role.model.EidpRoleMenu;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色服务
 */
@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/role")
public interface IEidpRoleClientService extends IBaseClientService<EidpRole> {

    /**
     * 分配菜单
     * @param ids 菜单编码
     * @param roleId 角色编码
     * @return
     */
    @RequestMapping(value = "/assignMenu")
    StatusModel assignMenu(@RequestParam("ids") Integer[] ids,
                           @RequestParam("roleId") Integer roleId);

    /**
     * 通过角色id获取映射
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/findEidpRoleMenusByRoleId")
    List<EidpRoleMenu> findEidpRoleMenusByRoleId(@RequestParam("roleId") Integer roleId);
}
