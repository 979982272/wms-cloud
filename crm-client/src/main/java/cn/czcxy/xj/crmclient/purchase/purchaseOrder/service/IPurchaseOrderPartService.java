package cn.czcxy.xj.crmclient.purchase.purchaseOrder.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity.PurchaseOrderPart;

import java.util.List;

public interface IPurchaseOrderPartService extends IBaseService<PurchaseOrderPart, String> {


    void savePurchaseApplyPart(List<PurchaseOrderPart> purchaseOrderParts, String id)throws Exception;

    /**
     * 删除采购单明细
     * @param orderId
     * @throws Exception
     */
    void deletePurchaseOrderPartByOrderId(String orderId)throws Exception;
}
