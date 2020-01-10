package cn.czcxy.xj.basicclient.menu.mapper;

import cn.czcxy.xj.basicclient.menu.entity.EidpMenu;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EidpMenuMapper extends CustomizeMapper<EidpMenu> {

    /**
     * 通过等级查询
     *
     * @param level
     * @return
     */
    List<EidpMenu> findAllMenusByLevel(Integer level);

    /**
     * 通过父级id查询列表
     *
     * @param id
     * @return
     */
    List<EidpMenu> findMenusByParentId(Integer id);

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    EidpMenu findMenuById(Integer id);

    /**
     * 通过用户编码和组织机构编码查询
     * @param empId
     * @param organizationId
     * @return
     */
    List<EidpMenu> findMenuByEmpIdAndOrganizationId(@Param("empId") String empId, @Param("organizationId") Integer organizationId);

    List<EidpMenu> findEidpMenu();
}