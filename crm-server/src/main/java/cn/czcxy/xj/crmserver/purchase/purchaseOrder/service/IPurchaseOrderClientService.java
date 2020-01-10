package cn.czcxy.xj.crmserver.purchase.purchaseOrder.service;


import cn.czcxy.xj.core.platform.base.annotation.BasePath;
import cn.czcxy.xj.core.platform.base.model.StatusModel;
import cn.czcxy.xj.core.platform.base.service.IBaseClientService;
import cn.czcxy.xj.core.platform.feign.FeignConfig;
import cn.czcxy.xj.crmserver.purchase.purchaseOrder.model.PurchaseOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "crm-client", configuration = FeignConfig.class)
@BasePath("/purchase/purchaseOrder")
public interface IPurchaseOrderClientService extends IBaseClientService<PurchaseOrder> {
    /**
     * 通过采购申请单获取采购订单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getPurchaseOrder", method = RequestMethod.POST)
    StatusModel getPurchaseOrder(@RequestParam("id") String id);

    /**
     * 通过下推采购申请保存采购订单
     *
     * @return
     */
    @RequestMapping(value = "savePurchaseOrderByApplyPush", method = RequestMethod.POST)
    StatusModel savePurchaseOrderByApplyPush(PurchaseOrder purchaseOrder);

    /**
     * 保存
     *
     * @param t
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    StatusModel savePurchaseOrder(PurchaseOrder t);

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "/auditById", method = RequestMethod.POST)
    StatusModel auditById(@RequestParam("ids") String id);

}
