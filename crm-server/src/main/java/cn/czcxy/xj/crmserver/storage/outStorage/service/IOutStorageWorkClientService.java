package cn.czcxy.xj.crmserver.storage.outStorage.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.storage.outStorage.model.OutStorageWork;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/outStorage/outStorageWork")
public interface IOutStorageWorkClientService extends IBaseClientService<OutStorageWork> {

    /**
     * 通过出库单整单出库
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/outStorageByStorageId", method = RequestMethod.POST)
    public StatusModel outStorageByStorageId(@RequestParam("ids") String id) ;
}
