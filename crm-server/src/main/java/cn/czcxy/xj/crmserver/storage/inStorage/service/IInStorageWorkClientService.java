package cn.czcxy.xj.crmserver.storage.inStorage.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.storage.inStorage.model.InStorageWork;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/inStorage/inStorageWork")
public interface IInStorageWorkClientService extends IBaseClientService<InStorageWork> {

    /**
     * 通过入口单号入库
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/inStorageByStorageId", method = RequestMethod.POST)
    public StatusModel inStorageByStorageId(@RequestParam("ids") String id) ;
}
