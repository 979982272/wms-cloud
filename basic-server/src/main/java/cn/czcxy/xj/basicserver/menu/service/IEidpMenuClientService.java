package cn.czcxy.xj.basicserver.menu.service;


import cn.czcxy.xj.basicserver.menu.model.EidpMenu;
import cn.czcxy.xj.basicserver.menu.model.Node;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/menu")
public interface IEidpMenuClientService extends IBaseClientService<EidpMenu> {

    /**
     * 查找出所有菜单/排序
     *
     * @throws Exception
     */
    @RequestMapping(value = "/getMenus", method = RequestMethod.POST)
    List<Node<Integer, String>> getMenus() throws Exception;

    /**
     * 获取用户菜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMenusByEmpOrOrganization", method = RequestMethod.POST)
    public List<Node<Integer, String>> getMenusByEmpOrOrganization(@RequestParam(value = "empId") String empId,
                                                                   @RequestParam(value = "organizationId") Integer organizationId,
                                                                   @RequestParam(value = "token") String token);
}
