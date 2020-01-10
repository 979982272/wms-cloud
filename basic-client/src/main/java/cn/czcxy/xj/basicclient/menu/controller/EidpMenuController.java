package cn.czcxy.xj.basicclient.menu.controller;

import cn.czcxy.xj.basicclient.menu.entity.EidpMenu;
import cn.czcxy.xj.basicclient.menu.entity.Node;
import cn.czcxy.xj.basicclient.menu.mapper.EidpMenuMapper;
import cn.czcxy.xj.basicclient.menu.service.IEidpMenuService;
import cn.czcxy.xj.core.platform.base.annotation.BaseResource;
import cn.czcxy.xj.core.platform.base.controller.BaseCurdController;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@Api("菜单服务")
public class EidpMenuController extends BaseCurdController<EidpMenu, Integer> {

    @Resource
    @BaseResource
    private IEidpMenuService eidpMenuService;

    @Resource
    private EidpMenuMapper eidpMenuMapper;


    @RequestMapping(value = "getMenus", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取所有菜单", notes = "获取所有菜单")
    public List<Node<Integer, String>> getMenus() throws Exception {
        List<EidpMenu> eidpMenus = eidpMenuMapper.findEidpMenu();
        Node<Integer, String> node = new Node<Integer, String>();
        for (EidpMenu menu : eidpMenus) {
            node.put(menu.getId(), menu.getParentId(), menu.getText(), menu.getUrl(), menu.getIcon());
        }
        return node.toCollection();
    }

    /**
     * 通过用户或者组织机构获取菜单
     *
     * @param empId
     * @param organizationId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getMenusByEmpOrOrganization", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取菜单", notes = "获取菜单")
    public List<Node<Integer, String>> getMenusByEmpOrOrganization(@RequestParam(value = "empId", required = false) String empId,
                                                                   @RequestParam(value = "organizationId", required = false) Integer organizationId,
                                                                   @RequestParam(value = "token") String token) throws Exception {
        List<EidpMenu> eidpMenus = eidpMenuMapper.findMenuByEmpIdAndOrganizationId(empId, organizationId);
        Node<Integer, String> node = new Node<Integer, String>();
        String url = null;
        for (EidpMenu menu : eidpMenus) {
            url = menu.getUrl();
            if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(token)) {
                url = url + "?token=" + token;
            }
            node.put(menu.getId(), menu.getParentId(), menu.getText(), url, menu.getIcon());
        }
        return node.toCollection();
    }


}
