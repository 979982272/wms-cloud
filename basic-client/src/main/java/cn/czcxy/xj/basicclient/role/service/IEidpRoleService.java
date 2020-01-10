package cn.czcxy.xj.basicclient.role.service;

import cn.czcxy.xj.basicclient.role.entity.EidpRoleMenu;
import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.basicclient.role.entity.EidpRole;

import java.util.List;

public interface IEidpRoleService extends IBaseService<EidpRole,java.lang.Integer>{

    /**
     * 分配菜单
     * @param ids
     * @param roleId
     * @throws Exception
     */
    void assignMenu(Integer[] ids, Integer roleId)throws Exception;

    /**
     * 获取角色与菜单关联
     * @param roleId
     * @return
     * @throws Exception
     */
    List<EidpRoleMenu> findEidpRoleMenusByRoleId(Integer roleId)throws Exception;
}
