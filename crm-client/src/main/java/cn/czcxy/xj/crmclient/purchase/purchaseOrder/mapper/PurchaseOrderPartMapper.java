package cn.czcxy.xj.crmclient.purchase.purchaseOrder.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity.PurchaseOrderPart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseOrderPartMapper extends CustomizeMapper<PurchaseOrderPart> {
    /**
     * 通过采购订单号查询采购订单明细
     *
     * @param id
     * @return
     */
    List<PurchaseOrderPart> findPurchaseOrderPartByPurchaseId(@Param("id") String id);

    /**
     * 通过明细单号查询整个采购单明细
     *
     * @param id
     * @return
     */
    List<PurchaseOrderPart> findPurchaseOrderPartByPartId(String id);

    /**
     * 删除采购订单
     * @param orderId
     */
    void deletePurchaseOrderPartByOrderId(@Param("orderId") String orderId);
}