package cn.czcxy.xj.crmclient.purchase.purchaseOrder.mapper;

import cn.czcxy.xj.core.platform.base.mapper.CustomizeMapper;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity.PurchaseOrder;
import org.apache.ibatis.annotations.Param;

public interface PurchaseOrderMapper extends CustomizeMapper<PurchaseOrder> {
    /**
     * 通过编码查询采购订单
     *
     * @param id
     * @return
     */
    PurchaseOrder findPurchaseOrderById(@Param("id") String id);

    /**
     * 修改采购订单状态
     *
     * @param id
     * @param receivingStatus
     * @throws Exception
     */
    void updatePurchaseOrderStatusById(@Param("id") String id, @Param("receivingStatus") Integer receivingStatus) throws Exception;
}