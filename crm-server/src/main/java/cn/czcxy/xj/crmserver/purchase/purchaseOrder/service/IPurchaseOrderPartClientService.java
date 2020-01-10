package cn.czcxy.xj.crmserver.purchase.purchaseOrder.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.DataSourceRequest;
import cn.czcxy.xj.core.platform.base.model.DataSourceResult;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.purchase.purchaseOrder.model.PurchaseOrderPart;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/purchase/purchaseOrderPart")
public interface IPurchaseOrderPartClientService extends IBaseClientService<PurchaseOrderPart> {

    /**
     * 载入明细数据
     * @param dataSourceRequest
     * @return
     */
    @RequestMapping(value = "loadDetailData")
    DataSourceResult loadDetailData(@RequestBody DataSourceRequest dataSourceRequest);
}
