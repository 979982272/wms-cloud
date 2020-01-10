package cn.czcxy.xj.basicclient.organization.service;

import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.basicclient.organization.entity.EidpOrganization;

public interface IEidpOrganizationService extends IBaseService<EidpOrganization, java.lang.Integer> {

    /**
     * 查询未分配的角色
     *
     * @param id
     * @return
     * @throws Exception
     */
    DataSourceResult selectRole(Integer id) throws Exception;

    /**
     * 查询已分配的角色
     *
     * @param id
     * @return
     */
    DataSourceResult selectOrganizationRole(Integer id);

    /**
     * 保存组织机构与角色的关联关系
     *
     * @param roleIds
     * @param organizationId
     * @return
     */
    void saveOrganizationRole(Integer[] roleIds, Integer organizationId) throws Exception;

    /**
     * 删除组织机构与角色的关联关系
     *
     * @param ids
     * @param organizationId
     * @throws Exception
     */
    void deleteOrganizationRole(Integer[] ids, Integer organizationId) throws Exception;

    /**
     * 分配用户
     *
     * @param organizationId
     * @param ids
     * @throws Exception
     */
    void saveOrganizationEmp(Integer organizationId, String[] ids) throws Exception;

    /**
     * 删除分配用户
     *
     * @param organizationId
     * @param ids
     * @throws Exception
     */
    void deleteOrganizationEmp(Integer organizationId, String[] ids) throws Exception;

    /**
     * 查询未分配的用户
     *
     * @param organizationId
     * @return
     * @throws Exception
     */
    DataSourceResult selectEmp(Integer organizationId) throws Exception;

    /**
     * 查询已分配的用户
     *
     * @param organizationId
     * @return
     * @throws Exception
     */
    DataSourceResult selectOrganizationEmp(Integer organizationId) throws Exception;

}
