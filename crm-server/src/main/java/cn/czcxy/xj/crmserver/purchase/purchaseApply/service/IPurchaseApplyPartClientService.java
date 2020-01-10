package cn.czcxy.xj.crmserver.purchase.purchaseApply.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.purchase.purchaseApply.model.PurchaseApplyPart;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/purchase/purchaseApplyPart")
public interface IPurchaseApplyPartClientService extends IBaseClientService<PurchaseApplyPart> {

}
