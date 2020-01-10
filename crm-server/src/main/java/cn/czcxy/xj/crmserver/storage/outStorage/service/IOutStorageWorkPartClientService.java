package cn.czcxy.xj.crmserver.storage.outStorage.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.storage.outStorage.model.OutStorageWorkPart;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/outStorage/outStorageWorkPart")
public interface IOutStorageWorkPartClientService extends IBaseClientService<OutStorageWorkPart> {

    /**
     * 载入明细信息
     *
     * @param dataSourceRequest
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    public DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest) ;

    /**
     * 明细出库
     *
     * @return
     */
    @RequestMapping(value = "outStorageByPart", method = RequestMethod.POST)
    public StatusModel outStorageByPart(@RequestParam(value = "id", required = true) String id,
                                        @RequestParam(value = "deliveryAmount", required = true) BigDecimal deliveryAmount) ;
}
