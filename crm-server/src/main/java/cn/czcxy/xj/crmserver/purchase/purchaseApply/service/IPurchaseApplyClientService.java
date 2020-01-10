package cn.czcxy.xj.crmserver.purchase.purchaseApply.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.purchase.purchaseApply.model.PurchaseApply;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/purchase/purchaseApply")
public interface IPurchaseApplyClientService extends IBaseClientService<PurchaseApply> {

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditById", method = RequestMethod.POST)
    public StatusModel auditById(@RequestParam("ids") String id);

    /**
     * 取消审核
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancelAuditById", method = RequestMethod.POST)
    public StatusModel cancelAuditById(@RequestParam("ids") String id);
}
