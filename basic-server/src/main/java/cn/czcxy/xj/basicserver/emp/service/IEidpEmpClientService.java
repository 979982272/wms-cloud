package cn.czcxy.xj.basicserver.emp.service;

import cn.czcxy.xj.basicserver.emp.model.EidpEmp;
import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: wwh
 * @Date: 2018/5/27 0027 23:50
 * @Description:
 */
@FeignClient(value = "basic-client", configuration = FeignConfig.class)
@BasePath("/emp")
public interface IEidpEmpClientService extends IBaseClientService<EidpEmp> {
    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    @RequestMapping(value = "/findEidpEmpByName",method = RequestMethod.POST)
    EidpEmp findEidpEmpByName(@RequestParam(value = "userName") String userName);

    /**
     * 初始化
     *
     * @return
     */
    @RequestMapping(value = "/getDefaultInfo", method = RequestMethod.POST)
    public StatusModel getDefaultInfo() ;

    /**
     * 重置密码
     * @param split
     * @return
     */
    @RequestMapping(value = "/reSetPassword", method = RequestMethod.POST)
    StatusModel reSetPassword(@RequestParam(value = "ids")String[] split);
}
