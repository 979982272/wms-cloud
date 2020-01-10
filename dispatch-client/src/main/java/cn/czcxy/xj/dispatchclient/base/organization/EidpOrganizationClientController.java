package cn.czcxy.xj.dispatchclient.base.organization;

import cn.czcxy.xj.basicserver.organization.model.EidpOrganization;
import cn.czcxy.xj.basicserver.organization.service.IEidpOrganizationClientService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdClienController;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.util.HttpServletRequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 组织机构
 */
@Controller
@RequestMapping("/base/organization")
public class EidpOrganizationClientController extends BaseCurdClienController<EidpOrganization> {

    @Resource
    @BaseResource
    private IEidpOrganizationClientService eidpOrganizationService;

    /**
     * 分配角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}/assignRole", method = RequestMethod.GET)
    public String assignRole(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        return this.view("assignRole");
    }

    /**
     * 查询未分配的角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectRole", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult selectRole(@RequestParam(value = "id", required = true) Integer id) {
        return eidpOrganizationService.selectRole(id);
    }

    /**
     * 查询已分配的角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectOrganizationRole", method = RequestMethod.POST)
    @ResponseBody
    public DataSourceResult selectOrganizationRole(@RequestParam(value = "id", required = true) Integer id) {
        return eidpOrganizationService.selectOrganizationRole(id);
    }

    /**
     * 保存组织机构与角色的关联关系
     *
     * @param roleIds
     * @param organizationId
     * @return
     */
    @RequestMapping(value = "saveOrganizationRole", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel saveOrganizationRole(@RequestParam(value = "roleIds", required = true) Integer[] roleIds,
                                            @RequestParam(value = "organizationId", required = true) Integer organizationId) {
        return eidpOrganizationService.saveOrganizationRole(roleIds, organizationId);
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
        return eidpOrganizationService.deleteOrganizationRole(ids, organizationId);
    }

    /**
     * 分配用户
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{organizationId}/assignEmp", method = RequestMethod.GET)
    public String assignEmp(@PathVariable("organizationId") Integer id, Model model) {
        model.addAttribute("id", id);
        return this.view("assignEmp");
    }

    /**
     * 分配用户
     *
     * @param organizationId
     * @param
     * @return
     */
    @RequestMapping(value = "/{organizationId}/saveOrganizationEmp", method = RequestMethod.POST)
    @ResponseBody
    public StatusModel saveOrganizationEmp(@PathVariable("organizationId") Integer organizationId,
                                           @RequestParam(value = "empIds", required = true) String[] ids) {
        return eidpOrganizationService.saveOrganizationEmp(organizationId, ids);
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
        return eidpOrganizationService.deleteOrganizationEmp(organizationId, ids);
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
        return eidpOrganizationService.selectEmp(organizationId);
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
        return eidpOrganizationService.selectOrganizationEmp(organizationId);
    }
}
