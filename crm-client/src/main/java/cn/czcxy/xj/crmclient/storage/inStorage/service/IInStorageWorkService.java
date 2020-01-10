package cn.czcxy.xj.crmclient.storage.inStorage.service;


import cn.czcxy.xj.core.platform.base.service.IBaseService;
import cn.czcxy.xj.crmclient.purchase.purchaseOrder.entity.PurchaseOrder;
import cn.czcxy.xj.crmclient.storage.inStorage.entity.InStorageWork;

public interface IInStorageWorkService extends IBaseService<InStorageWork,String> {

    /**
     * 通过采购订单生成入库作业单
     * @param purchaseOrder
     * @throws Exception
     */
    void buidInStorageWordkByPurchaseOrder(PurchaseOrder purchaseOrder)throws Exception;

    /**
     * 通过入库单号入库
     * @param id
     * @throws Exception
     */
    void inStorageByStorageId(String id)throws Exception;

    public void updateInStorageWorkStatus(InStorageWork storageWork) throws Exception;
}
