package cn.czcxy.xj.basicserver.organization.service;

import cn.czcxy.xj.basicserver.organization.model.EidpOrganization;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/organization")
public interface IEidpOrganizationClientService extends IBaseClientService<EidpOrganization> {

    /**
     * 查询未分配的角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectRole", method = RequestMethod.POST)
    DataSourceResult selectRole(@RequestParam("id") Integer id);

    /**
     * 查询已分配的角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectOrganizationRole", method = RequestMethod.POST)
    DataSourceResult selectOrganizationRole(@RequestParam("id") Integer id);

    /**
     * 保存组织机构与角色的关联关系
     *
     * @param roleIds
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "saveOrganizationRole", method = RequestMethod.POST)
    StatusModel saveOrganizationRole(@RequestParam(value = "roleIds") Integer[] roleIds,
                                     @RequestParam(value = "organizationId") Integer organizationId);

    /**
     * 删除组织机构与角色的关联关系
     *
     * @param ids
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "/{organizationId}/deleteOrganizationRole")
    StatusModel deleteOrganizationRole(@RequestParam(value = "ids", required = true) Integer[] ids,
                                       @PathVariable("organizationId") Integer organizationId);

    /**
     * 分配用户
     *
     * @param organizationId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/{organizationId}/saveOrganizationEmp", method = RequestMethod.POST)
    StatusModel saveOrganizationEmp(@PathVariable("organizationId") Integer organizationId,
                                    @RequestParam(value = "ids", required = true) String[] ids);

    /**
     * 删除分配的用户
     *
     * @param organizationId
     * @param
     * @return
     */
    @RequestMapping(value = "/{organizationId}/deleteOrganizationEmp", method = RequestMethod.POST)
    StatusModel deleteOrganizationEmp(@PathVariable("organizationId") Integer organizationId,
                                      @RequestParam(value = "ids") String[] ids);

    /**
     * 查询未分配的用户
     *
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "selectEmp", method = RequestMethod.POST)
    DataSourceResult selectEmp(@RequestParam(value = "organizationId") Integer organizationId);

    /**
     * 查询已分配的用户
     *
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "selectOrganizationEmp", method = RequestMethod.POST)
    DataSourceResult selectOrganizationEmp(@RequestParam(value = "organizationId") Integer organizationId);
}
