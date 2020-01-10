package cn.czcxy.xj.basicclient.emp.mapper;

import cn.czcxy.xj.basicclient.emp.entity.EidpEmp;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EidpEmpMapper extends CustomizeMapper<EidpEmp> {
    /**
     * 获取数量
     *
     * @return
     */
    Integer getEidpMaxCount();

    /**
     * 通过名称查询
     *
     * @param name
     * @return
     */
    List<EidpEmp> findEidpEmpByName(String name);

    /**
     * 通过名称查询，id不重复的
     *
     * @param name
     * @param id
     * @return
     */
    List<EidpEmp> findEidpEmpByNameAndNotId(@Param("name") String name, @Param("id") String id);

    /**
     * 验证用户名，密码
     *
     * @param username
     * @param password
     * @return
     */
    List<EidpEmp> validByUserAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 验证用户
     *
     * @param username
     * @return
     */
    int validByUser(@Param("username") String username);

    /**
     * 查询未分配给组织机构的用户
     *
     * @param organizationId
     * @return
     */
    List<EidpEmp> selectEmp(@Param("organizationId") Integer organizationId);

    /**
     * 查询已分配的用户
     *
     * @param organizationId
     * @return
     */
    List<EidpEmp> selectOrganizationEmp(@Param("organizationId") Integer organizationId);
}