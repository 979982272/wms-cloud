package cn.czcxy.xj.basicclient.organization.controller;

import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import cn.czcxy.xj.basicclient.organization.service.IEidpOrganizationService;
import cn.czcxy.xj.basicclient.organization.entity.EidpOrganization;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;


@RestController
@RequestMapping("/organization")
public class EidpOrganizationController extends BaseCurdController<EidpOrganization, java.lang.Integer> {

    @Resource
    @BaseResource
    private IEidpOrganizationService eidpOrganizationService;

    /**
     * 查询未分配的角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectRole")
    @ResponseBody
    public DataSourceResult selectRole(@RequestParam("id") Integer id) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        try {
            dataSourceResult = eidpOrganizationService.selectRole(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 查询未分配的角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectOrganizationRole")
    @ResponseBody
    public DataSourceResult selectOrganizationRole(@RequestParam("id") Integer id) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        try {
            dataSourceResult = eidpOrganizationService.selectOrganizationRole(id);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 保存组织机构与角色的关联关系
     *
     * @param roleIds
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "saveOrganizationRole", method = RequestMethod.POST)
    public StatusModel saveOrganizationRole(@RequestParam(value = "roleIds") Integer[] roleIds,
                                            @RequestParam(value = "organizationId") Integer organizationId) {
        try {
            eidpOrganizationService.saveOrganizationRole(roleIds, organizationId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "保存成功!");
    }

    /**
     * 删除组织机构与角色的关联关系
     *
     * @param ids
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "/{organizationId}/deleteOrganizationRole")
    @ResponseBody
    public StatusModel deleteOrganizationRole(@RequestParam(value = "ids", required = true) Integer[] ids,
                                              @PathVariable("organizationId") Integer organizationId) {
        try {
            eidpOrganizationService.deleteOrganizationRole(ids, organizationId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "删除成功!");
    }

    /**
     * 分配用户
     *
     * @param organizationId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/{organizationId}/saveOrganizationEmp", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel saveOrganizationEmp(@PathVariable("organizationId") Integer organizationId,
                                           @RequestParam(value = "ids", required = true) String[] ids) {
        try {
            eidpOrganizationService.saveOrganizationEmp(organizationId, ids);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "分配成功！");
    }

    /**
     * 删除分配的用户
     *
     * @param organizationId
     * @param
     * @return
     */
    @RequestMapping(value = "/{organizationId}/deleteOrganizationEmp", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel deleteOrganizationEmp(@PathVariable("organizationId") Integer organizationId,
                                             @RequestParam(value = "ids", required = true) String[] ids) {
        try {
            eidpOrganizationService.deleteOrganizationEmp(organizationId, ids);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
            return new StatusModel(false, e.getMessage());
        }
        return new StatusModel(true, "删除成功！");
    }

    /**
     * 查询未分配的用户
     *
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "selectEmp", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult selectEmp(@RequestParam(value = "organizationId", required = true) Integer organizationId) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        try {
            dataSourceResult = eidpOrganizationService.selectEmp(organizationId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }

    /**
     * 查询已分配的用户
     *
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "selectOrganizationEmp", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult selectOrganizationEmp(@RequestParam(value = "organizationId", required = true) Integer organizationId) {
        DataSourceResult dataSourceResult = new DataSourceResult();
        try {
            dataSourceResult = eidpOrganizationService.selectOrganizationEmp(organizationId);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return dataSourceResult;
    }
}
