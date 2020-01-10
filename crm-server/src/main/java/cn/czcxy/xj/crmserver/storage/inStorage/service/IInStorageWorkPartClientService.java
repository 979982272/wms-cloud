package cn.czcxy.xj.crmserver.storage.inStorage.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.storage.inStorage.model.InStorageWorkPart;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/inStorage/inStorageWorkPart")
public interface IInStorageWorkPartClientService extends IBaseClientService<InStorageWorkPart> {

    /**
     * 载入明细信息
     *
     * @param dataSourceRequest
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    @ResponseBody
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest) ;

    /**
     * 明细入库
     *
     * @return
     */
    @RequestMapping(value = "inStorageByPart", method = RequestMethod.POST)
    public StatusModel inStorageByPart(@RequestParam(value = "id", required = true) String id,
                                       @RequestParam(value = "receivingAmount", required = true) BigDecimal receivingAmount) ;
}
