package cn.czcxy.xj.basicclient.organization.service.impl;

import cn.czcxy.xj.basicclient.emp.entity.EidpEmp;
import cn.czcxy.xj.basicclient.emp.mapper.EidpEmpMapper;
import cn.czcxy.xj.basicclient.organization.entity.EidpOrganizationEmp;
import cn.czcxy.xj.basicclient.organization.entity.EidpOrganizationRole;
import cn.czcxy.xj.basicclient.organization.mapper.EidpOrganizationEmpMapper;
import cn.czcxy.xj.basicclient.organization.mapper.EidpOrganizationRoleMapper;
import cn.czcxy.xj.basicclient.role.entity.EidpRole;
import cn.czcxy.xj.basicclient.role.mapper.EidpRoleMapper;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.czcxy.xj.basicclient.organization.service.IEidpOrganizationService;
import cn.czcxy.xj.basicclient.organization.mapper.EidpOrganizationMapper;
import cn.czcxy.xj.core.platform.base.service.impl.BaseServiceImpl;
import cn.czcxy.xj.basicclient.organization.entity.EidpOrganization;
import org.springframework.transaction.annotation.Transactional;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("eidpOrganizationService")
public class EidpOrganizationServiceImpl extends BaseServiceImpl<EidpOrganization, java.lang.Integer> implements IEidpOrganizationService {

    @Resource
    @BaseResource
    private EidpOrganizationMapper eidpOrganizationMapper;

    @Resource
    private EidpOrganizationRoleMapper eidpOrganizationRoleMapper;

    @Resource
    private EidpOrganizationEmpMapper eidpOrganizationEmpMapper;

    @Resource
    private EidpRoleMapper eidpRoleMapper;

    @Resource
    private EidpEmpMapper eidpEmpMapper;

    @Override
    public DataSourceResult selectRole(Integer id) throws Exception {
        List<EidpRole> eidpRoles = eidpRoleMapper.selectRole(id);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(eidpRoles);
        dataSourceResult.setTotal(eidpRoles.size());
        return dataSourceResult;
    }

    @Override
    public DataSourceResult selectOrganizationRole(Integer id) {
        List<EidpRole> eidpRoles = eidpRoleMapper.selectOrganizationRole(id);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(eidpRoles);
        dataSourceResult.setTotal(eidpRoles.size());
        return dataSourceResult;
    }

    @Override
    public void saveOrganizationRole(Integer[] roleIds, Integer organizationId) throws Exception {
        EidpOrganizationRole eidpOrganizationRole = null;
        for (Integer id : roleIds) {
            eidpOrganizationRole = new EidpOrganizationRole();
            eidpOrganizationRole.setRoleId(id);
            eidpOrganizationRole.setOrganizationId(organizationId);
            eidpOrganizationRoleMapper.insertSelective(eidpOrganizationRole);
        }
    }

    @Override
    public void deleteOrganizationRole(Integer[] ids, Integer organizationId) throws Exception {
        Example example = new Example(EidpOrganizationRole.class);
        Example.Criteria criteria = example.createCriteria();
        List<Integer> roleIds = new ArrayList<>();
        for (Integer id : ids) {
            roleIds.add(id);
        }
        criteria.andEqualTo("organizationId", organizationId);
        criteria.andIn("roleId", roleIds);
        eidpOrganizationRoleMapper.deleteByExample(example);
    }

    @Override
    public void saveOrganizationEmp(Integer organizationId, String[] ids) throws Exception {
        EidpOrganizationEmp eidpOrganizationEmp = null;
        for (String id : ids) {
            eidpOrganizationEmp = new EidpOrganizationEmp();
            eidpOrganizationEmp.setEmpId(id);
            eidpOrganizationEmp.setOrganizationId(organizationId);
            eidpOrganizationEmpMapper.insertSelective(eidpOrganizationEmp);
        }
    }

    @Override
    public void deleteOrganizationEmp(Integer organizationId, String[] ids) throws Exception {
        Example example = new Example(EidpOrganizationEmp.class);
        Example.Criteria criteria = example.createCriteria();
        List<String> empIds = new ArrayList<>();
        for (String id : ids) {
            empIds.add(id);
        }
        criteria.andEqualTo("organizationId", organizationId);
        criteria.andIn("empId", empIds);
        eidpOrganizationEmpMapper.deleteByExample(example);
    }

    @Override
    public DataSourceResult selectEmp(Integer organizationId) throws Exception {
        List<EidpEmp> eidpRoles = eidpEmpMapper.selectEmp(organizationId);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(eidpRoles);
        dataSourceResult.setTotal(eidpRoles.size());
        return dataSourceResult;
    }

    @Override
    public DataSourceResult selectOrganizationEmp(Integer organizationId) throws Exception {
        List<EidpEmp> eidpRoles = eidpEmpMapper.selectOrganizationEmp(organizationId);
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(eidpRoles);
        dataSourceResult.setTotal(eidpRoles.size());
        return dataSourceResult;
    }
}