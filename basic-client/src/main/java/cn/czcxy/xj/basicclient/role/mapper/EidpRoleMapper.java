package cn.czcxy.xj.basicclient.role.mapper;

import cn.czcxy.xj.basicclient.emp.entity.EidpEmp;
import cn.czcxy.xj.basicclient.role.entity.EidpRole;
import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EidpRoleMapper extends CustomizeMapper<EidpRole> {
    List<EidpRole> selectRole(@Param("organizationId") Integer organizationId);

    List<EidpRole> selectOrganizationRole(@Param("organizationId") Integer id);

}